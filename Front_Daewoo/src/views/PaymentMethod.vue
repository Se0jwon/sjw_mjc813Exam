<template>
  <div class="container">
    <div class="login-box">
      <!-- 왼쪽 결제수단 -->
      <div class="left">
        <h4>결제수단 추가</h4>
        <p>결제수단을 추가해주세요</p>

        <div class="log_input">
          <!-- 카드번호 입력 -->
          <div class="row-input cardbox">
            <input
                type="text"
                v-model="cardNumber"
                @input="formatCardNumber"
                placeholder="Card Number"
            />
            <i class="fa-brands fa-cc-visa"></i>
          </div>

         <!-- 카드날짜 & cvc -->
          <div class="row-input">
            <input type="text" v-model="expDate" placeholder="Exp.Date (MM/YY)" />
            <input type="text" v-model="cvc" placeholder="cvc" />
          </div>

          <!-- 카드 이름 -->
          <div class="namebox">
            <input type="text" v-model="cardName" placeholder="Name on Card" />
          </div>

          <!-- 국적 -->
          <div class="county">
            <div class="select-wrapper">
              <select v-model="country">
                <option disabled value="">Country or Region</option>
                <option v-for="(c, i) in countries" :key="i" :value="c">
                  {{ c }}
                </option>
              </select>
<!--              <i class="fa-solid fa-chevron-down"></i>-->
            </div>
          </div>
        </div>

        <div class="check_box2">
          <label>
            <input type="checkbox" v-model="remember" /> 저장하고 One 터치로 결제하기
          </label>
        </div>

        <button class="Pay_Add" @click="handleSubmit">결제수단 추가</button>

        <p class="description">
          By confirming your subscription, you allow The Outdoor Inn Crowd Limited to
          charge your card for this payment and future payments in accordance with their
          terms, You can always cancel your subscription.
        </p>
      </div>

      <!-- 오른쪽 이미지 -->
      <div class="right">
        <img
            v-for="(img, i) in images"
            :key="i"
            :src="img"
            alt="hotel image"
            class="slide"
            :class="{ active: i === currentIndex }"
        />
        <div class="dots">
          <span
              v-for="(dot, i) in images"
              :key="i"
              class="dot"
              :class="{ active: i === currentIndex }"
              @click="showSlide(i)"
          ></span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "PaymentMethod",
  data() {
    return {
      cardNumber: "",
      expDate: "",
      cvc: "",
      cardName: "",
      country: "",
      remember: false,
      images: [
        require("@/assets/img/img.jpg"),
        require("@/assets/img/img2.jpg"),
        require("@/assets/img/img3.jpg"),
      ],
      countries: [
        "네덜란드", "노르웨이", "뉴질랜드", "대한민국", "덴마크",
        "독일", "러시아", "멕시코", "말레이시아", "미국", "베트남",
        "벨기에", "브라질", "스웨덴", "스위스", "스페인", "싱가포르",
        "아르헨티나", "영국", "오스트리아", "이탈리아", "인도", "일본",
        "캐나다", "콜롬비아", "칠레", "체코", "중국", "터키", "태국",
        "페루", "폴란드", "프랑스", "필리핀", "헝가리", "호주", "그리스",
        "남아프리카공화국", "포르투갈", "핀란드"
      ],
      currentIndex: 0,
      intervalId: null,
    };
  },
  methods: {
    formatCardNumber() {
      let value = this.cardNumber.replace(/\D/g, ""); // 숫자만
      value = value.substring(0, 16); // 최대 16자리
      this.cardNumber = value.replace(/(\d{4})(?=\d)/g, "$1 ");
    },
    handleSubmit() {
      const cNumber = this.cardNumber.replace(/\s/g, "").trim();
      const expDate = this.expDate.trim();
      const cvc = this.cvc.trim();
      const name = this.cardName.trim();
      const country = this.country.trim();

      if (!cNumber || !expDate || !cvc || !name || !country) {
        alert("모든 항목을 입력해주세요.");
        return;
      }
      if (!/^\d{16}$/.test(cNumber)) {
        alert("카드번호는 숫자 16자리여야 합니다.");
        return;
      }
      if (!/^\d{3}$/.test(cvc)) {
        alert("CVC는 숫자 3자리여야 합니다.");
        return;
      }
      if (!/^(0[1-9]|1[0-2])\/\d{2}$/.test(expDate)) {
        alert("알 수 없는 날짜 형식입니다. MM/YY 형식으로 입력해주세요. (예: 03/25)");
        return;
      }
      if (!/^[a-zA-Z\s]+$/.test(name)) {
        alert("카드 이름은 영문으로 입력해주세요.");
        return;
      }
      if (!this.remember) {
        alert("약관에 동의 해주세요.");
        return;
      }

      alert("회원가입이 완료되었습니다.");
      this.$router.push("/");
    },
    showSlide(n) {
      this.currentIndex = n;
    },
    nextSlide() {
      this.currentIndex = (this.currentIndex + 1) % this.images.length;
    },
  },
  mounted() {
    this.intervalId = setInterval(this.nextSlide, 3000);
  },
  beforeUnmount() {
    clearInterval(this.intervalId);
  },
};
</script>

<style scoped>
@import "@/assets/css/PaymentMethod.css";
/* 오른쪽 이미지 영역 */
.right {
  width: 400px;
  height: 500px;
  flex: 1;
  position: relative;
  overflow: hidden;
  margin-top: 11px;
  margin-right: 35px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.dots {
  position: absolute;
  bottom: 15px;
  left: 50%;
  bottom: 3%;
  transform: translateX(-50%);
  display: flex;
  gap: 8px;
  z-index: 2;
}

</style>


