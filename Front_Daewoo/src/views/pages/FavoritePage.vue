<template>
  <CommonLayout>
    <div class="favorite-container">
      <h2 class="title">Favorite</h2>

      <ul class="favorite-tabs">
        <li :class="{ active: activeTab === 'flights'}"
          @click="activeTab = 'flights'">

          <p>Flights</p>
          <span>2 marked</span>
        </li>
        <div class="profile-history-divider"></div>
        <li :class="{ active: activeTab === 'places'}"
            @click="activeTab = 'places'">
          <p>Places</p>
          <span>{{placeCount}} marked</span>
        </li>
      </ul>

      <div v-if="isLoading" class="loading-message">
        <p>목록을 불러오는 중입니다...</p>
      </div>
      <div v-else-if="error" class="error-message">
        <p>{{ error }}</p>
      </div>
      <div
        v-else-if="currentList.length === 0"
        class="empty-list-message"
      >
        <p>즐겨찾기에 추가된 항목이 없습니다.</p>
      </div>
      <FavoriteList v-else :list="currentList" @unlike="handleUnlike" />

    </div>
  </CommonLayout>
</template>

<script>
import CommonLayout from '@/components/common/CommonLayout.vue';
import FavoriteList from '@/components/favorite/FavoriteList.vue';
import {bTeamApi} from '@/util/axios';

export default {
  components: { CommonLayout, FavoriteList },
  data() {
    return {
      activeTab: 'places',
      // 즐겨찾기리스트 조회 후 list 대신 쓰면됨
      flightList: [],
      placeList: [],
      isLoading: false,
      error: null,
    };
  },
  computed: {
   //찜 목록의 개수를 계산하는 computed
    flightCount(){
      return this.flightList.length;
    },
    placeCount(){
      return this.placeList.length;
    },
    currentList(){
      if (this.activeTab === 'flights'){
        return this.flightList;
      } else {
        return this.placeList;
      }
    }
  },
  methods: {
    // [변경점] 5. API를 호출하는 메소드
    async fetchPlaceFavorites() {
      this.isLoading = true; // 로딩 상태 시작
      this.error = null;     // 에러 상태 초기화
      this.placeList = [];

      try {
        const response = await bTeamApi.get('/api/wish');
        console.log('API Response:', response);
        //API 응답 성공시
        if (response.data && response.data.code === 'SUCCESS') {
          const rawList = response.data.result;
          console.log('Row List Data:', rawList);
          if (Array.isArray(rawList)) {
            this.placeList = rawList.map(wishItem => {
              const accom = wishItem.accommodationAllDto;
              // null 체크 추가 (혹시 accommodationAllDto 자체가 없을 경우 대비)
              if (!accom) {
                console.warn('Wish item missing accommodationAllDto:', wishItem);
                return null; // 또는 기본 객체 반환
              }

              let imageUrl = accom.image;

              if (imageUrl && !imageUrl.startsWith('http')) {
                const baseUrl = process.env.VUE_APP_API_URL;
                imageUrl = `${baseUrl}/api/accommodation/images/${imageUrl}`;
              }
              return {
                fno: wishItem.wishId,
                price: accom.price,
                stars: accom.star,
                amenities: accom.amenitiesCount,
                reviewAvg: accom.reviewAvg,
                reviews: accom.reviewCount,
                location: accom.comAddress,
                name: accom.comTitle,
                imageURL: imageUrl,
              };
            }).filter(item => item !== null);
          } else {
            this.error = `찜 목록을 불러오는데 실패했습니다: ${response.data.message || '알 수 없는 오류'}`;
            this.placeList = []; // 오류 시 목록 비우기
          }
        }
      } catch (error) {
        if (error.response && error.response.status === 401) {
          this.error = '인증되지 않은 사용자입니다. 다시 로그인해주세요.';
          // 필요시 로그인 페이지로 리다이렉트
          // this.$router.push('/login');
        } else {
          console.error('Error fetching place favorites:', error);
          // 더 상세한 오류 메시지 제공
          this.error = `찜 목록을 불러오는 중 오류가 발생했습니다. (${error.message || '네트워크 오류'})`;
        }
        this.placeList = []; // 오류 시 목록 비우기
      }
        finally {
        this.isLoading = false; // 로딩 상태 종료 (성공/실패 무관)
      }
    },
    async handleUnlike(fno){
      try {
        //백엔드에게 delete 요청
        await bTeamApi.delete(`/api/wish/${fno}`);
        //delete 요청이 성공하면 화면 목록에서도 삭제
        this.placeList = this.placeList.filter(item => item.fno !== fno);
      } catch (error){
        console.error('찜 삭제 중 오류 발생:', error);
      }
    },
  },
  mounted() {
    // Places 찜 목록을 불러오는 함수 호출
    this.fetchPlaceFavorites();
    // 만약 Flights API도 있다면 여기서 호출
    // this.fetchFlightFavorites();
  }

  };

</script>

<style scoped lang="scss">
.favorite-container {
  margin-top: 48px;

  &.profile-history-divider{
    height: 50px; /* 세로선의 높이 (필요에 따라 픽셀 값 조정) */
    width: 1px; /* 너비는 좁게 유지 */

    /* 왼쪽 테두리를 얇고 실선으로 지정 */
    border-left: 1px solid #ccc; /* 색상은 이미지에 맞게 연한 회색으로 조정 */
  }

  & .title {
    text-align: left;
  }

  .empty-list-message {
    padding: 64px 0; /* 위아래로 넉넉한 여백을 줍니다 */
    text-align: center; /* 텍스트를 수평 가운데 정렬합니다 */
    & p {
      font-size: 30px;
      color: #888; /* 글씨 색을 약간 연하게 (선택 사항) */
      font-weight: 500;
      text-align: center; /* 텍스트를 수평 가운데 정렬합니다 */
      opacity: 0.3
    }
  }

  & .favorite-tabs {
    margin-top: 24px;
    display: flex;
    width: 100%;
    box-shadow: 0 4px 16px rgba(0, 0, 0, 0.1);
    padding: 17px 24px;
    position: relative;
    background-color: white;
    gap: 20px;

    & .active::after {
      content: '';
      position: absolute;
      width: 104%;
      height: 3px;
      background-color: rgb(141, 211, 187);
      bottom: -17px;
      left: 50%;
      transform: translateX(-50%);
      border-radius: 4px;
    }

    & li {
      flex: 1;
      text-align: left;
      position: relative;
      cursor: pointer;

      & p {
        font-size: 16px;
        font-weight: 500;
        color: #000000;
        text-align: left; // 임시로 전역 수정 전까지만...
      }
      & span {
        font-size: 14px;
        color: rgb(153 155 153);
      }
    }
  }
}
</style>
