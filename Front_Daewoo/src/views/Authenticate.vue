<template>
  <div class="container">
    <div class="login-box">
      <div class="left">
        <div class="back" @click="goBack">
          <i class="fa-solid fa-chevron-left"></i>
          <p class="font" @click="$router.push('/')">Back to Login</p>
        </div>

        <h4>인증하기</h4>
        <p>이메일로 받은 인증번호를 입력해주세요</p>

        <div class="log_input">
          <div class="codebox">
            <input
                :type="showPassword ? 'text' : 'password'"
                v-model="code"
                placeholder="code"
            />
            <i
                class="fa-solid"
                :class="showPassword ? 'fa-eye-slash' : 'fa-eye'"
                @click="togglePassword"
            ></i>
          </div>

          <button type="button" class="resend" @click="resendCode">
            재전송하기
          </button>
        </div>

        <button class="certified" @click="handleAuthenticate">인증하기</button>
      </div>

      <div class="right">
        <img
            v-for="(slide, i) in slides"
            :key="i"
            :src="slide"
            alt="hotel image"
            class="slide"
            :class="{ active: i === index }"
        />
        <div class="dots">
          <span
              v-for="(dot, i) in slides"
              :key="'dot-' + i"
              class="dot"
              :class="{ active: i === index }"
              @click="showSlide(i)"
          ></span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import "@/assets/css/Authenticate.css";

export default {
  name: "AuthenticatePage",
  data() {
    return {
      code: "",
      showPassword: false,
      index: 0,
      slides: [
        require("@/assets/img/img.jpg"),
        require("@/assets/img/img2.jpg"),
        require("@/assets/img/img3.jpg"),
      ],
      timer: null,
      userEmail: this.$route.params.userEmail
    };
  },
  methods: {
    togglePassword() {
      this.showPassword = !this.showPassword;
    },
    async resendCode() {
      try {
        const response = await axios.post('/api/user/verify-reset-code', {
          userEmail: this.userEmail
        });

        if (response.data.code === 'SUCCESS') {
          alert("인증 코드가 이메일로 재전송되었습니다. 메일을 확인해주세요.");
        } else {
          alert(response.data.message);
        }
      } catch (error) {
        console.error("인증 코드 재전송 실패:", error);
        alert("인증 코드 재전송 중 오류가 발생했습니다. 잠시 후 다시 시도해주세요.");
      }
    },
    goBack() {
      this.$router.push("/findPassword");
    },
    async handleAuthenticate() {
      if (!this.code.trim()) {
        alert("인증번호를 입력해주세요.");
        return;
      }

      try {
        // 새로 추가된 비밀번호 재설정 전용 API를 호출합니다.
        const response = await axios.post('/api/user/verify-reset-code', {
          userEmail: this.userEmail,
          verificationCode: this.code,
        });

        if (response.data.code === 'SUCCESS') {
          alert("이메일 인증이 완료되었습니다. 새로운 비밀번호를 설정해주세요.");

          // 인증 성공 시에만 Password_Setting 페이지로 이동
          this.$router.push({
            name: 'Password_Setting',
            params: {
              userEmail: this.userEmail,
              verificationCode: this.code
            }
          });
        } else {
          alert(response.data.message);
        }
      } catch (error) {
        console.error("인증번호 확인 실패:", error);
        if (error.response && error.response.data && error.response.data.message) {
          alert(error.response.data.message);
        } else {
          alert("인증 중 오류가 발생했습니다. 잠시 후 다시 시도해주세요.");
        }
      }
    },
    showSlide(n) {
      this.index = n;
    },
    nextSlide() {
      this.index = (this.index + 1) % this.slides.length;
    },
  },
  mounted() {
    this.timer = setInterval(this.nextSlide, 3000);
  },
  beforeUnmount() {
    clearInterval(this.timer);
  },
};
</script>
<style scoped>
@import "@/assets/css/Authenticate.css";
</style>