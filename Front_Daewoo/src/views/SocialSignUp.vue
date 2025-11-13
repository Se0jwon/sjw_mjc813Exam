<template>
  <div class="container">
    <div class="login-box">
      <div class="lefts">
        <h4 class="centered-text">SocialSignUp</h4>
        <p class="centered-text">추가 정보를 입력해 주세요</p>
        <div class="log_input">
          <form @submit.prevent="handleFormSubmit">

            <div class="row-input">
              <input v-model="name" type="text" placeholder="Name" required/>
            </div>

            <div class="row-input email-verify-row">
              <input v-model="email" type="email" placeholder="Email" readonly/>
            </div>

            <div class="passwordbox">
              <input
                  type="password"
                  v-model="password"
                  placeholder="Password"
                  readonly
              />
              <i class="fa-solid fa-lock left-icon"></i>
            </div>

            <div class="row-input">
              <input v-model="dateOfBirth" type="date" placeholder="Date of Birth" required/>
            </div>

            <div class="row-input">
              <input v-model="address" type="text" placeholder="Address" readonly required/>
              <button type="button" class="search-address-btn" @click="searchAddress">주소찾기</button>
            </div>

            <div class="row-input">
              <input v-model="phone" type="text" placeholder="Phone Number" required/>
            </div>

            <div class="checkbox">
              <label>
                <input type="checkbox" v-model="agree" required/>동의하기
              </label>
            </div>

            <button
                type="submit"
                class="loginbox"
                :class="{ disabled: !isFormValid }"
                :disabled="!isFormValid"
            >
              회원가입 완료
            </button>
          </form>
        </div>


      </div>

      <div class="righter">
        <img src="@/assets/img/img.jpg" alt="hotel image" class="slide" :class="{ active: currentSlide === 0 }" />
        <img src="@/assets/img/img2.jpg" alt="hotel image" class="slide" :class="{ active: currentSlide === 1 }" />
        <img src="@/assets/img/img3.jpg" alt="hotel image" class="slide" :class="{ active: currentSlide === 2 }" />

        <div class="dots">
          <span
              v-for="(dot, i) in 3"
              :key="i"
              class="dot"
              :class="{ active: currentSlide === i }"
              @click="showSlide(i)"
          ></span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: "SocialSignUp",
  data() {
    return {
      // 쿼리 파라미터에서 가져올 정보 (소셜 인증 정보)
      oauthId: '',
      registrationId: '',
      email: '',
      name: '',

      // 사용자 입력 정보
      password: '소셜로그인 임시 비밀번호', // 사용자에게 보여주기 위한 임시 텍스트
      dateOfBirth: '',
      address: '',
      phone: '',
      agree: false,

      // 슬라이드 관련 데이터 추가
      currentSlide: 0,
      slides: [
        require("@/assets/img/img.jpg"),
        require("@/assets/img/img2.jpg"),
        require("@/assets/img/img3.jpg"),
      ],
    };
  },
  computed: {
    // 모든 필수 필드가 채워졌는지 확인하는 유효성 검사
    isFormValid() {
      return (
          this.oauthId &&
          this.registrationId &&
          this.email &&
          this.name &&
          this.dateOfBirth &&
          this.address &&
          this.phone &&
          this.agree
      );
    },
    routeData() {
      // URL 쿼리 파라미터 파싱
      const urlParams = new URLSearchParams(window.location.search);
      return {
        oauthId: urlParams.get('oauthId'),
        registrationId: urlParams.get('registrationId'),
        // URL-encoded 된 값을 디코딩
        email: decodeURIComponent(urlParams.get('email')),
        nickname: decodeURIComponent(urlParams.get('nickname')),
      };
    },
  },
  mounted() {
    this.loadSocialData();
    // 슬라이드 쇼 시작 함수 호출 추가
    this.startSlideShow();
  },
  methods: {
    loadSocialData() {
      // 🚨 백엔드(OAuth2SuccessHandler)에서 쿼리 파라미터로 보낸 정보를 가져와 채웁니다.
      this.oauthId = this.routeData.oauthId || '';
      this.registrationId = this.routeData.registrationId || '';
      this.email = this.routeData.email || '';
      this.name = this.routeData.nickname || '';

      if (!this.oauthId || !this.registrationId) {
        alert("소셜 인증 정보가 누락되었습니다. 다시 로그인해주세요.");
        this.$router.push('/');
      }
    },

    searchAddress() {
      // 'window.daum.Postcode'가 index.html에 로드되었는지 확인
      if (window.daum && window.daum.Postcode) {
        new window.daum.Postcode({
          oncomplete: (data) => {
            let fullAddress = '';
            let extraAddress = '';

            if (data.userSelectedType === 'R') {
              fullAddress = data.roadAddress;
            } else {
              fullAddress = data.jibunAddress;
            }

            if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
              extraAddress += data.bname;
            }
            if (data.buildingName !== '' && data.apartment === 'Y') {
              extraAddress += (extraAddress !== '' ? ', ' + data.buildingName : data.buildingName);
            }
            if (extraAddress !== '') {
              fullAddress += ' (' + extraAddress + ')';
            }

            this.address = fullAddress;
          }
        }).open();
      } else {
        alert("주소 찾기 서비스가 준비되지 않았습니다. 잠시 후 다시 시도해주세요.");
      }
    },

    async handleFormSubmit() {
      if (!this.isFormValid) {
        alert("필수 정보를 모두 입력하고 동의해주세요.");
        return;
      }

      // 🚨 [디버깅] 전송 직전의 데이터 확인
      console.log("전송 데이터:", {
        oauthId: this.oauthId,
        registrationId: this.registrationId,
        userEmail: this.email,
        username: this.name,
        userAddress: this.address,
        userPhone: this.phone,
        userBirth: this.dateOfBirth,
      });

      try {
        // 💡 새로운 최종 회원가입 엔드포인트에 데이터 전송
        const response = await axios.post('/api/user/complete-social-signup', {
          // 소셜 연동에 필수적인 정보
          oauthId: this.oauthId,
          registrationId: this.registrationId,
          // 추가 입력 정보
          userEmail: this.email,
          username: this.name,
          userAddress: this.address,
          userPhone: this.phone,
          userBirth: this.dateOfBirth,
          // 비밀번호는 백엔드에서 생성한 임시 값을 사용하므로, 프론트에서 보내지 않습니다.
        });

        if (response.data.code === 'SUCCESS') {
          // 백엔드에서 JWT 토큰을 응답으로 받았을 때 저장 로직 추가 (필요 시)
          // const token = response.data.data;
          alert("회원가입이 완료되었습니다. 로그인해주세요.");
          this.$router.push('/');
        } else {
          alert(response.data.message);
        }
      }catch (error) {
        console.error("최종 회원가입 실패:", error);
        // 🚨 백엔드에서 받은 응답 메시지를 정확히 출력합니다.
        if (error.response && error.response.data && error.response.data.message) {
          alert("회원가입 실패: " + error.response.data.message);
        } else {
          alert("회원가입 중 오류가 발생했습니다.");
        }
      }
    },
    // 슬라이드 쇼 관련 메서드 추가
    startSlideShow() {
      setInterval(() => {
        this.nextSlide();
      }, 3000);
    },
    nextSlide() {
      this.currentSlide = (this.currentSlide + 1) % this.slides.length;
    },
    showSlide(i) {
      this.currentSlide = i;
    },
  },
};
</script>

<style scoped>
@import '~@/assets/css/SocialSignUp.css';
</style>