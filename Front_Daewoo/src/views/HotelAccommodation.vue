<template>
  <CommonLayout>
    <div v-if="accommodation">

      <div class="accommodation-container">
        <div class="hotel-info-section">
          <div class="hotel-header-details">
            <div class="breadcrumb">
              {{ accommodation.location}} > {{ accommodation.category }} > {{ accommodation.comTitle }}
            </div>
            <div class="hotel-title-and-rating">
              <h1 class="hotel-title">{{ accommodation.comTitle }}</h1>
              <div class="hotel-rating">
                <span class="stars">{{ '★'.repeat(accommodation.star) }}{{ '☆'.repeat(5 - accommodation.star) }}</span>
                {{ accommodation.star }} Star Hotel
              </div>
            </div>
            <div class="hotel-address">
              <i class="fa-solid fa-location-dot"></i> {{ accommodation.comAddress }}
            </div>
            <div class="hotel-reviews">
              <span class="rating-score">{{ accommodation.reviewAvg }}</span>
              <span class="review-text">{{ getReviewText(accommodation.reviewAvg) }}</span>
              <span class="review-count">{{ accommodation.reviewCount }} reviews</span>
            </div>
          </div>
          <div class="price-and-actions">
            <div class="price-container">
              <span class="price">₩{{ (accommodation.price || 0).toLocaleString() }}</span><span class="per-night">{{accommodation.price}} ₩</span></div>
            <div class="action-buttons">
              <button class="icon-button"><i class="fa-regular fa-heart"></i></button>
              <button class="icon-button"><i class="fa-solid fa-share-nodes"></i></button>
              <button class="book-now-button">Book now</button>
            </div>
          </div>
        </div>

        <div class="hotel-images-section">
          <div class="main-image">
            <img :src="'/hotel-images/' + accommodation.mainImage" alt="메인 호텔 이미지" @click="openLightbox(accommodation.mainImage)" />
          </div>
          <div class="thumbnail-images">
            <img
              v-for="(image, index) in accommodation.subImage.slice(0, 4)"
              :key="index"
              :src="'/hotel-images/' + image"
              :alt="`호텔 썸네일 ${index + 1}`"
              @click="openLightbox(image)"
            />
            <button class="view-all-button" @click="showModal = true">View all photos</button>
          </div>
        </div>

        <div v-if="showLightbox" class="lightbox-overlay" @click.self="closeLightbox">
          <button class="lightbox-close-button" @click="closeLightbox">&times;</button>
          <div class="lightbox-content">
            <img :src="'/hotel-images/' + currentLightboxImage" class="lightbox-image" alt="Enlarged image" />
          </div>
        </div>

      </div>

      <hr>

      <div class="overview-section">
        <h2 class="section-title">Overview</h2>
        <p class="overview-texts">
          {{ accommodation.comDescription }}
        </p>
      </div>
      <hr>

      <div class="rooms-section">
        <div class="room-list">
          <h2 class="section-title-acc">잔여 객실</h2>
          <div v-if="accommodation && accommodation.rooms">
            <div class="room-item" v-for="room in accommodation.rooms" :key="room.accId">
              <div class="room-details">
                <img v-if="room && room.roomType && room.roomType.parlorImage"
                     :src="`/parlorimage/${room.roomType.parlorImage}`"
                     :alt="`${room.roomType.roomTypeName || 'Room'} Image`"
                     class="room-image" />
                <div v-else class="room-image placeholder-image">
                  <span>이미지 없음</span>
                </div>

                <div class="room-info">
                  <div class="room-type">{{ (room.roomType && room.roomType.roomTypeName) ? room.roomType.roomTypeName : '알 수 없는 객실' }}</div>
                </div>
              </div>
              <div class="room-price-container">
                <div class="room-price">
                  <template v-if="room.discountedPrice && room.discountedPrice < room.price">
              <span class="original-price" style="text-decoration: line-through; color: grey; font-size: 0.9em; margin-right: 5px;">
                ₩{{ room.price.toLocaleString() }}
              </span>
                    <span class="price-value" style="color: red;">
                ₩{{ room.discountedPrice.toLocaleString() }}
              </span>
                  </template>
                  <template v-else>
                    <span class="price-value">₩{{ room.price.toLocaleString() }}</span>
                  </template>
                  <span class="price-unit">/night</span>
                </div>
                <button class="book-now-button">Book now</button>
              </div>
            </div>
          </div>
          <div v-else>
            객실 정보를 불러오는 중입니다...
          </div>
        </div>
      </div>
      <hr>

      <MapSection
        apiKey="AIzaSyALY6qdom7_04POAxT18afRvHK84daK4FM"
        :address="accommodation.comAddress"
      />
      <hr>


      <AmenitiesAndReviews
        :amenities="accommodation.amenities"
        :review-avg="accommodation.reviewAvg"
        :review-count="accommodation.reviewCount"
        @review-submitted="fetchAccommodationData"
        @attempt-review="handleReviewAttempt"
        @review-deleted="fetchAccommodationData"
      />
    </div>

    <div v-if="showModal" class="image-modal-overlay" @click.self="showModal = false">
      <div class="image-modal-content">
        <button class="modal-close-button" @click="showModal = false">&times;</button>
        <h3>All Photos ({{ allImages.length }})</h3>
        <div class="modal-image-grid">
          <img v-for="(image, index) in allImages"
               :key="index"
               :src="'/hotel-images/' + image"
               :alt="`Image ${index + 1}`"
               @click="openLightbox(image)" />
        </div>
      </div>
    </div>

  </CommonLayout>
</template>

<script>
// NOTE: 컴포넌트 경로 문제는 사용자가 직접 해결해야 하는 부분이므로,
// 현재 파일의 import는 사용자 파일에서 정상 동작했다고 가정하고 유지합니다.
import MapSection from './MapSection.vue';
import AmenitiesAndReviews from './AmenitiesAndReviews.vue';
// API 호출을 위해 axios 임포트
import axios from 'axios';
import CommonLayout from '@/components/common/CommonLayout.vue';

export default {
  name: "HotelAccommodation",
  components: {
    CommonLayout,
    MapSection,
    AmenitiesAndReviews
  },
  data() {
    return {
      accommodation: null,
      error: null,
      showModal: false,
      showLightbox: false,
      currentLightboxImage: null
    };
  },
  computed: { // [✅ computed 속성 추가]
    allImages() {
      if (!this.accommodation) return [];
      // 메인 이미지와 서브 이미지 배열을 합쳐서 반환
      return [this.accommodation.mainImage, ...this.accommodation.subImage];
    }
  },
  methods: {
    openLightbox(image) {
      this.currentLightboxImage = image;
      this.showLightbox = true;
    },
    closeLightbox() {
      this.showLightbox = false;
      this.currentLightboxImage = null;
    },
    async fetchAccommodationData() {
      try {
        const comId = this.$route.params.comId;
        const checkIn = this.$route.query.checkIn;
        const checkOut = this.$route.query.checkOut;

        let apiUrl = `/api/accommodation/${comId}`;

        const params = new URLSearchParams();
        if (checkIn) params.append('checkIn', checkIn);
        if (checkOut) params.append('checkOut', checkOut);

        if (params.toString()) {
          apiUrl += `?${params.toString()}`;
        }

        console.log(`Fetching data from: ${apiUrl}`);

        // API 호출
        const response = await axios.get(apiUrl);

        if (response.data && response.data.code === 'SUCCESS') {
          this.accommodation = response.data.result;
        } else {
          throw new Error(response.data.message || "데이터 로드 실패");
        }
      } catch (err) {
        console.error("숙소 정보를 불러오는 중 오류가 발생했습니다:", err);
        this.error = "데이터를 불러오는 데 실패했습니다.";
      }
    },

    // (선택) 리뷰 점수에 따라 텍스트 반환
    getReviewText(score) {
      if (score >= 4.5) return "Excellent";
      if (score >= 4.0) return "Very Good";
      if (score >= 3.5) return "Good";
      if (score >= 3.0) return "Average";
      return "Poor";
    },

    // (필수) AccRoomTypeDto에 이미지가 없으므로 임시 이미지 반환
    getRoomPlaceholderImage(roomId) {
      return `https://placehold.co/100x80?text=Room+${roomId}`;
    },

    // 🚨 [추가] 하위 컴포넌트에서 로그인 필요 시 호출되는 핸들러
    handleReviewAttempt() {
      alert("리뷰를 작성하려면 로그인 후 이용해주세요."); // 사용자가 원한 알림창
      // 로그인 페이지로 이동 (index.js에 따르면 '/'가 LoginPage입니다.)
      this.$router.push('/');
    }
  },
  mounted() {
    // 컴포넌트가 로드될 때 API 호출
    this.fetchAccommodationData();
  },
  watch: {
    // (선택) 라우트 파라미터(checkIn/checkOut 등)가 변경될 때 데이터를 새로고침
    '$route': 'fetchAccommodationData'
  }
};
</script>

<style scoped>
@import '@/assets/css/HotelAccommodation.css';
</style>