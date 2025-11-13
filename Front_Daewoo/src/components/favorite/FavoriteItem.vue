<template>
  <div class="card">
    <div class="card-left">
      <img :src="info.imageURL" alt="" style="width: 440px; height: 273.5px; "/>
    </div>

    <div class="card-right">
      <div class="card-right-upper">
        <section class="desc-area">
          <h2 class="title">{{ info.name }}</h2>
          <p class="location" style="display: flex; gap: 5px">
            <img src="../../assets/img/icon/location.png" alt="" />
            {{ info.location }}
          </p>
          <ul class="desc-subInfo">
            <!-- ✅ 몇성 호텔 -->
            <li style="display: flex; justify-content: center; align-items: center;">
              <template v-for="star in info.stars">
                <img src="../../assets/img/icon/star.png" alt="" style="width: 15px; height: 14px;"/>
              </template>
              <div style="font-size: 12px ; margin-left: 5px; font-weight: bold"> {{ info.stars }} Star Hotel </div>
            </li>

            <!-- ✅ 어메니티 -->
            <li style="display: flex; justify-content: center; align-items: center; gap: 3px">
              <img src="../../assets/img/icon/coffee.png" alt="" style="width: 13px; height: 12px; border-radius: 0;"/>
              <template v-if="info.amenities >= 20">
                <span class="amenity-counts"> 20+ </span>
              </template>
              <template v-else>
                <span class="amenity-counts">
                  {{ info.amenities }}
                </span>
              </template>
              Amenities
            </li>
          </ul>
          <div class="review-summary">
            <span class="review-score">{{ info.reviewAvg }}</span>
            <strong class="average-review">Very Good</strong>
            <span class="reviews">{{ info.reviews }} reviews</span>
          </div>
        </section>

        <section class="price-area">
          <span class="price-guide1">starting from</span>
          <span class="favorite-price">
            <strong>₩{{ formatPrice(info.price) }}</strong>
            /night
          </span>
          <span class="price-guide2">excl. tax</span>
        </section>
      </div>

      <div class="card-right-bottom">
        <button class="like-btn" @click="onUnlikeClick">
          <img src="../../../src/assets/img/icon/blackheart.png" alt="" style="border-radius: 0; width: 16.25px; height: 15px" />
        </button>
        <button class="go-price-btn" @click="gotoDetailPage">View Price</button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    info: {
      type: Object,
      default: null,
    },
  },
  methods: {
    formatPrice(price) {
      if (price === undefined || price === null) return '0';
      return Number(price).toLocaleString('ko-KR');
    },
    //나중에 api를 상세 페이지로 쏠 때 쓸거임
    gotoDetailPage() {
      this.$router.push(`/accommodation`);
    },
    //찜하기 누르면 빠지게
    onUnlikeClick(){
      this.$emit('unlike', this.info.fno);
    },
  },
};
</script>

<style scoped lang="scss">
img{ width: 10px; height: 14px}

.card-left img{
  border-bottom-right-radius: 0;
  border-top-right-radius: 0;
}

.card-right-bottom::before {
  left: 50%;
  content: '';
  width: 94%;
  top: -20px;
  position: absolute;
  height: 1px;
  background-color:rgb(189 195 189);
  transform: translateX(-50%);
}

.card {
  display: flex;
  margin-top: 40px;
  background-color: white;
  box-shadow: 0 4px 16px rgba(0, 0, 0, 0.1);

  & .card-right {
    width: 100%;
    display: flex;
    flex-direction: column;
    height: 273.5px;
    gap: 40px;



    & .card-right-upper{
      padding: 24px 24px 0 24px;

    }
    .card-right-bottom {
      padding: 4px 24px 0 24px;
    }

    & .card-right-upper {
      display: flex;
      justify-content: space-between;

      p {
        text-align: left;
      }

      & .desc-area {
        text-align: left;
        display: flex;
        flex-direction: column;
        gap: 11px;
        & .title {
          font-size: 20px;
          font-weight: 700;
        }
        & .location {
          display: flex;
          font-size: 12px;
          color: rgba(17, 34, 17, 1);
        }

        & .desc-subInfo {
          display: flex;
          gap: 32px;
          font-size: 12px;

          & .amenity-counts {
            font-size: 12px;
            font-weight: 700;
          }
        }

        & .review-summary {
          display: flex;
          gap: 5px;
          font-size: 12px;
          align-items: center;

          & .average-review {
            font-weight: 700;
          }

          & .review-score {
            padding: 8px 11px;
            border: 1px solid rgba(141, 211, 187, 1);
            border-radius: 6px;
          }
        }
      }

      & .price-area {
        display: flex;
        flex-direction: column;
        & .price-guide1 {
          text-align: left;
          font-size: 12px;
        }
        & .price-guide2 {
          text-align: right;
          font-size: 12px;
        }

        & .favorite-price {
          color: rgba(255, 134, 130, 1);
          font-size: 14px;
          & strong {
            font-size: 24px;
            font-weight: 700;
          }
        }
      }
    }

    & .card-right-bottom {
      position: relative;
      display: flex;
      gap: 16px;

      & .like-btn {
        width: 48px;
        height: 48px;
        border: 1px solid rgba(141, 211, 187, 1);
        justify-items: center;
        flex-shrink: 0;
        border-radius: 5px;
        cursor: pointer;
      }

      & .go-price-btn {
        width: 680px;
        height: 48px;
        padding: 15px 300px;
        font-size: 14px;
        font-weight: 600;
        background-color: rgba(141, 211, 187, 1);
        border-radius: 5px;
        cursor: pointer;
      }
    }

    & .info {
      display: flex;
    }
  }
}

</style>
