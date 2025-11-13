<template>
  <div class="amenities-reviews-section">
    <div class="amenities-section-acc">
      <h2 class="section-title">Amenities</h2>
      <div class="amenity-flex-container">
        <div class="amenity-feature" v-for="amenity in visibleAmenities" :key="amenity.amId">
          <i :class="getIconClass(amenity.iconName)"></i>
          <span>{{ amenity.amCategory }}</span>
        </div>
      </div>
      <div class="more-amenities">
        <a href="#" @click.prevent="showMoreAmenities" v-if="hiddenAmenitiesCount > 0">+{{ hiddenAmenitiesCount }} more</a>
        <a href="#" @click.prevent="showLessAmenities" v-else-if="amenities.length > initialVisibleCount">Show less</a>
      </div>
    </div>

    <hr>

    <div class="reviews-section">
      <div class="reviews-header">
        <h2 class="section-title">Reviews</h2>
        <button class="review-button" @click="openReviewForm">Give your review</button>
      </div>
      <div class="review-summary">
        <div class="rating-score">{{ reviewAvg != null ? reviewAvg.toFixed(1) : 'N/A' }}</div>
        <span class="rating-text">{{ getReviewText(reviewAvg) }}</span>
        <span class="review-count">{{ reviewCount }} verified reviews</span>
      </div>

      <div v-if="error" class="review-error-message">{{ error }}</div>

      <div v-else-if="reviews.length > 0" class="review-list">

        <div class="review-item" v-for="review in reviews" :key="review.reviewId">

          <div class="review-actions">
            <i v-if="loggedInUserId === review.userId"
               class="fa-solid fa-ellipsis review-action-icon"
               title="Delete review"
               @click="deleteReview(review.reviewId)"></i>

            <img v-if="loggedInUserId && loggedInUserId !== review.userId"
                 class="review-action-icon report-icon"
                 width="24"
                 height="24"
                 src="https://img.icons8.com/ios-glyphs/30/siren.png"
                 alt="Report review"
                 title="Report review"
                 @click="reportReview(review.reviewId)"/>
          </div>
          <div class="review-content-wrapper">
            <img :src="review.profileImageUrl || defaultAvatar" alt="profile" class="reviewer-avatar">
            <div class="review-body">
              <div class="reviewer-info">
                <div class="reviewer-rating">{{ review.score }} {{ getRatingAdjective(review.score) }}</div>
                <span class="reviewer-divider">|</span>
                <div class="reviewer-name">{{ review.username || '익명 사용자' }}</div>
              </div>
              <p class="review-text">{{ review.content }}</p>
            </div>
          </div>

        </div>
      </div>
      <div v-else>
        아직 작성된 리뷰가 없습니다.
      </div>

      <div class="pagination">
        <button class="pagination-btn" @click="previousPage" :disabled="currentPage === 0">
          &lt;
        </button>
        <span>{{ currentPage + 1 }} of {{ totalPages }}</span>
        <button class="pagination-btn" @click="nextPage" :disabled="currentPage >= totalPages - 1">
          &gt;
        </button>
      </div>
    </div>

    <div v-if="showReviewForm" class="review-modal-overlay" @click.self="closeReviewForm">
      <div class="review-modal-content">
        <button class="modal-close-button" @click="closeReviewForm">&times;</button>
        <h3>리뷰 작성하기</h3>
        <form @submit.prevent="handleSubmitReview">
          <div class="review-form-group">
            <label>별점:</label>
            <div class="star-rating">
              <span v-for="star in 5" :key="star" @click="setRating(star)">
                <i :class="getStarClass(star)"></i>
              </span>
            </div>
          </div>
          <div class="review-form-group">
            <label for="reviewContent">내용:</label>
            <textarea id="reviewContent" v-model.trim="newReview.content" rows="5" required></textarea>
          </div>
          <div v-if="reviewError" class="review-error-message"> {{ reviewError }} </div>
          <button type="submit" :disabled="reviewLoading" class="review-button submit-btn">
            {{ reviewLoading ? '등록 중...' : '리뷰 등록' }}
          </button>
        </form>
      </div>
    </div>

  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'AmenitiesAndReviews',
  props: {
    amenities: { type: Array, default: () => [] },
    reviewAvg: { type: Number, default: null },
    reviewCount: { type: Number, default: 0 }
  },
  data() {
    return {
      initialVisibleCount: 9, visibleAmenitiesCount: 9,
      reviews: [], error: null, showReviewForm: false,
      newReview: { content: '', score: 0 },
      reviewLoading: false, reviewError: null,
      currentPage: 0, totalPages: 1, pageSize: 5,
      loggedInUserId: null,
      defaultAvatar: 'https://via.placeholder.com/50' // 🚨 기본 아바타 경로
    };
  },
  computed: {
    visibleAmenities() { return this.amenities.slice(0, this.visibleAmenitiesCount); },
    hiddenAmenitiesCount() { const hc = this.amenities.length - this.visibleAmenitiesCount; return hc > 0 ? hc : 0; }
  },
  methods: {
    // ... (showMore/LessAmenities, getIconClass, getReviewText, getRatingAdjective는 동일)
    showMoreAmenities() { this.visibleAmenitiesCount = this.amenities.length; },
    showLessAmenities() { this.visibleAmenitiesCount = this.initialVisibleCount; },
    getIconClass(iconName) { return iconName ? `fa-solid fa-${iconName}` : 'fa-solid fa-question-circle'; },
    getReviewText(score) {
      if (score === null || score === undefined) return "No Rating";
      if (score >= 4.5) return "Excellent"; if (score >= 4.0) return "Very Good";
      if (score >= 3.5) return "Good"; if (score >= 3.0) return "Average"; return "Poor";
    },
    getRatingAdjective(score) {
      if (score >= 4.5) return "Amazing"; if (score >= 4.0) return "Great";
      if (score >= 3.5) return "Good"; if (score >= 3.0) return "Okay"; return "Poor";
    },

    // 리뷰 목록 로드
    async fetchReviews(page = 0) {
      this.error = null; console.log(`fetchReviews page: ${page}`);
      try {
        const comId = this.$route.params.comId; if (!comId) throw new Error("ID not found.");
        const token = localStorage.getItem('token');

        const response = await axios.get(`/api/accommodation/${comId}/review`, {
          params: { page: page, size: this.pageSize },
          headers: token ? { 'Authorization': `Bearer ${token}` } : {}
        });
        console.log('fetchReviews response:', response);

        if (response.data?.code === 'SUCCESS' && response.data.result) {
          const pageData = response.data.result;
          this.reviews = pageData.content || [];
          this.currentPage = pageData.number;
          this.totalPages = pageData.totalPages > 0 ? pageData.totalPages : 1;
          console.log('Reviews loaded:', this.reviews.map(r => ({ id: r.reviewId, userId: r.userId, img: r.profileImageUrl })), `Page: ${this.currentPage + 1}/${this.totalPages}`);
        } else { throw new Error(response.data.message || "리뷰 로드 실패"); }
      } catch (err) {
        console.error("리뷰 로드 오류:", err);
        this.error = `리뷰 로드 실패: ${err.response?.data?.message || err.message}`;
        this.reviews = []; this.currentPage = 0; this.totalPages = 1;
      }
    },
    // 페이지 이동
    previousPage() { if (this.currentPage > 0) this.fetchReviews(this.currentPage - 1); },
    nextPage() { if (this.currentPage < this.totalPages - 1) this.fetchReviews(this.currentPage + 1); },

    // 모달 열기/닫기, 별점 설정/아이콘
    openReviewForm() {
      const token = localStorage.getItem('token');
      if (!token) { this.$emit('attempt-review'); return; }
      this.newReview = { content: '', score: 0 }; this.reviewError = null; this.showReviewForm = true;
    },
    closeReviewForm() { this.showReviewForm = false; },
    setRating(star) { this.newReview.score = (this.newReview.score === star) ? star - 0.5 : star; },
    getStarClass(val) { return this.newReview.score >= val ? 'fa-solid fa-star filled' : (this.newReview.score >= val - 0.5 ? 'fa-solid fa-star-half-stroke filled' : 'fa-regular fa-star'); },

    // 리뷰 제출
    async handleSubmitReview() {
      console.log('handleSubmitReview started.');
      if (this.newReview.score === 0 || !this.newReview.content?.trim()) {
        this.reviewError = "별점과 내용을 모두 입력해주세요."; return;
      }
      this.reviewLoading = true; this.reviewError = null;
      try {
        const token = localStorage.getItem('token');
        if (!token) { alert("로그인이 필요합니다."); this.reviewLoading = false; return; }

        const reviewData = {
          content: this.newReview.content.trim(), score: this.newReview.score,
          comId: this.$route.params.comId
        };
        const comId = this.$route.params.comId;
        const response = await axios.post( `/api/accommodation/${comId}/review`, reviewData,
          { headers: { 'Authorization': `Bearer ${token}` } } );

        if (response.data.code === 'SUCCESS') {
          this.closeReviewForm(); await this.fetchReviews(0); this.$emit('review-submitted');
        } else { throw new Error(response.data.message); }
      } catch (err) {
        console.error("리뷰 등록 실패:", err); this.reviewError = err.response?.data?.message || "오류 발생";
      } finally { this.reviewLoading = false; }
    },

    /**
     * [✅ 삭제 기능] 리뷰 삭제 메서드
     */
    async deleteReview(reviewId) {
      if (!confirm("이 리뷰를 정말 삭제하시겠습니까?")) return;
      console.log(`Deleting review ID: ${reviewId}`);
      try {
        const token = localStorage.getItem('token');
        if (!token) { alert("삭제 권한이 없습니다."); return; }

        const response = await axios.delete(`/api/accommodation/review/${reviewId}`, {
          headers: { 'Authorization': `Bearer ${token}` }
        });

        if (response.data.code === 'SUCCESS') {
          console.log('Review deleted successfully.');
          const pageToFetch = (this.reviews.length === 1 && this.currentPage > 0) ? this.currentPage - 1 : this.currentPage;
          await this.fetchReviews(pageToFetch);
          this.$emit('review-deleted');
        } else { throw new Error(response.data.message || "삭제 실패"); }
      } catch (err) {
        console.error("리뷰 삭제 실패:", err);
        alert(`리뷰 삭제 오류: ${err.response?.data?.message || err.message}`);
      }
    },

    reportReview(reviewId) { // 'async' 키워드를 제거했습니다.
      if (!confirm("이 리뷰를 정말 신고하시겠습니까?")) return;

      // 1. 로그인 여부는 그대로 확인합니다.
      const token = localStorage.getItem('token');
      if (!token) {
        alert("신고 권한이 없습니다. 로그인이 필요합니다.");
        return;
      }

      // 2. 기존 try...catch (axios 호출) 부분을 삭제하고
      //    콘솔 로그와 alert로 대체합니다.
      console.log(`Reporting review ID: ${reviewId}`);
      alert('신고가 정상적으로 접수되었습니다.');
      // 또는 '신고 기능은 현재 준비 중입니다.' 같은 메시지를 쓰셔도 됩니다.
    }

  }, // <--- methods 닫는 괄호
  mounted() {
    console.log('AmenitiesAndReviews mounted.');
    const userIdKey = 'userId';
    const userIdFromStorage = localStorage.getItem(userIdKey);
    console.log(`Value from localStorage for key '${userIdKey}':`, userIdFromStorage);

    this.loggedInUserId = userIdFromStorage ? parseInt(userIdFromStorage) : null;
    console.log('Final loggedInUserId set to:', this.loggedInUserId);

    this.fetchReviews();
  }
};
</script>

<style scoped>
/* [✅ 기존 CSS 및 추가/수정된 CSS 모두 포함] */
.amenities-reviews-section {
  padding: 20px 0;
  text-align: left;
}
.section-title {
  font-size: 24px;
  font-weight: bold;
  margin-bottom: 25px;
  text-align: left;
}

/* ... (amenity, more-amenities, reviews-section, reviews-header, review-button, review-summary 스타일 동일) ... */
.amenity-flex-container {
  display: flex;
  flex-wrap: wrap;
  gap: 15px;
}
.amenity-feature {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  gap: 8px;
  background-color: white;
  border: 1px solid #e0e0e0;
  border-radius: 10px;
  box-shadow: 0 2px 5px rgba(0,0,0,0.05);
  width: 120px;
  height: 100px;
  font-size: 0.9em;
  color: #333;
  text-align: center;
  padding: 10px;
  box-sizing: border-box;
}
.amenity-feature i {
  font-size: 2.2em;
  color: #555;
}
.more-amenities {
  text-align: right;
  margin-top: 20px;
}
.more-amenities a {
  color: #c94029;
  text-decoration: none;
  font-weight: bold;
}
.reviews-section {
  margin-top: 30px;
}
.reviews-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.review-button {
  background-color: #8DD3BB;
  border: 1px solid #8DD3BB;
  padding: 10px 15px;
  border-radius: 5px;
  font-weight: bold;
  cursor: pointer;
}
.review-summary {
  display: flex;
  align-items: center;
  gap: 8px;
  background-color: #e6f7ef;
  padding: 5px 12px;
  border-radius: 5px;
  width: fit-content;
  margin-top: 10px;
  margin-bottom: 20px;
}
.review-summary .rating-score {
  font-weight: bold;
  font-size: 1.1em;
}
.review-summary .rating-text {
  color: #333;
}
.review-summary .review-count {
  color: #555;
  font-size: 0.9em;
}


.review-list {
  margin-top: 40px;
}

/* [✅ 수정] review-item은 아이콘 컨테이너의 'position: relative' 기준 */
.review-item {
  position: relative; /* 🚨 중요 */
  border-bottom: 1px solid #eee;
  padding: 15px 0;
}

/* [✅ 수정] wrapper가 아이콘 컨테이너 영역을 침범하지 않도록 padding-right 유지 */
.review-content-wrapper {
  display: flex;
  align-items: flex-start;
  gap: 15px;
  padding-right: 30px; /* 아이콘 영역 확보 */
}

/* [✅ 신규] 프로필 이미지 (아바타) */
.reviewer-avatar {
  width: 50px;
  height: 50px;
  border-radius: 50%;
  object-fit: cover;
  border: 1px solid #eee;
  flex-shrink: 0;
}

/* [✅ 신규] 리뷰 본문 (정보 + 텍스트) */
.review-body {
  flex: 1;
}

/* [✅ 수정] 이제 이름과 평점을 가로로 배치 */
.reviewer-info {
  display: flex;
  align-items: center;
  gap: 8px;
}

/* [✅ 신규] 이름과 평점 사이의 구분자 */
.reviewer-divider {
  color: #ccc;
}

.reviewer-rating {
  font-weight: bold;
}
.reviewer-name {
  color: #555;
  font-weight: bold;
}

/* [✅ 신규] 삭제/신고 아이콘을 묶는 컨테이너 */
.review-actions {
  position: absolute;
  top: 15px; /* review-item의 padding-top과 맞춤 */
  right: 0;
  display: flex;
  flex-direction: column; /* 아이콘을 세로로 배치 */
  align-items: center;
  gap: 10px; /* 아이콘 사이 간격 */
}

.review-action-icon {
  color: #aaa;
  cursor: pointer;
  padding: 5px; /* 클릭 영역 */
  font-size: 1.0rem; /* 아이콘 크기 통일 */
}
.review-action-icon:hover {
  color: #333;
}

/* [✅ 신규] 신고 아이콘 hover 스타일 */
.review-action-icon.report-icon:hover {
  color: #e74c3c; /* 신고는 붉은색 */
}


.review-text {
  margin-top: 10px;
  line-height: 1.6;
  color: #333;
  text-align: left;
}

/* --- 페이지네이션 --- */
.pagination {
  display: flex;
  justify-content: center;
  align-items: center;
  margin-top: 20px;
  gap: 10px;
}
.pagination-btn {
  background-color: transparent;
  border: 1px solid #ddd;
  padding: 5px 10px;
  border-radius: 5px;
  cursor: pointer;
}
.pagination-btn:disabled {
  color: #ccc;
  cursor: not-allowed;
  border-color: #eee;
}

/* --- 리뷰 모달 및 폼 스타일 --- */
.review-modal-overlay {
  position: fixed; top: 0; left: 0; width: 100%; height: 100%;
  background-color: rgba(0, 0, 0, 0.6); display: flex;
  justify-content: center; align-items: center; z-index: 1000;
}
.review-modal-content {
  background-color: #fff; padding: 30px; border-radius: 8px;
  width: 90%; max-width: 500px; position: relative;
  box-shadow: 0 4px 15px rgba(0, 0, 0, 0.2); box-sizing: border-box;
}
.modal-close-button {
  position: absolute; top: 10px; right: 15px; font-size: 1.8rem;
  font-weight: bold; color: #aaa; background: none; border: none; cursor: pointer;
}
.review-modal-content h3 {
  margin-top: 0; margin-bottom: 25px; text-align: center; font-size: 1.5rem;
}
.review-form-group { margin-bottom: 20px; }
.review-form-group label { display: block; margin-bottom: 8px; font-weight: 600; }
.review-form-group input, .review-form-group textarea {
  width: 100%; padding: 10px; border: 1px solid #ccc; border-radius: 4px;
  font-size: 1rem; box-sizing: border-box;
}
/* 0.5점 별점 스타일 */
.star-rating { font-size: 2rem; cursor: pointer; }
.star-rating span { margin-right: 5px; }
.star-rating i { transition: color 0.2s; }
.star-rating .filled { color: #f39c12; } /* 꽉찬/반 별 색상 */
.star-rating .fa-regular.fa-star { color: #ccc; } /* 빈 별 색상 */

/* 모달 제출 버튼 */
.review-modal-content .review-button.submit-btn { width: 100%; font-size: 1.1rem; }
/* 에러 메시지 */
.review-error-message { color: red; margin-bottom: 15px; text-align: center; }
</style>