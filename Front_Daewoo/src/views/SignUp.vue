<template>
  <div class="container">
    <div class="login-box">
      <div class="lefts">
        <h4 class="centered-text">Sign up</h4>
        <p class="centered-text">회원가입</p>
        <div class="log_input">
          <form @submit.prevent="handleFormSubmit">
            <div class="row-input">
              <input v-model="name" type="text" placeholder="Name" />
            </div>

            <div class="row-input email-verify-row">
              <input v-model="email" type="email" placeholder="Email" />
              <button
                type="button"
                class="send-code-btn"
                @click="sendVerificationCode"
                :disabled="isVerificationSent"
              >
                {{ isVerificationSent ? '재전송' : '인증번호 보내기' }}
              </button>
            </div>

            <div class="passwordbox" v-if="isVerificationSent">
              <input
                :type="showCode ? 'text' : 'password'"
                v-model="verificationCode"
                placeholder="Verification Code"
              />
              <i
                :class="['fa-solid', showCode ? 'fa-eye' : 'fa-eye-slash']"
                @click="toggleCodeVisibility"
              ></i>
            </div>

            <div class="row-input">
              <input v-model="dateOfBirth" type="date" placeholder="Date of Birth" />
            </div>

            <div class="row-input">
              <input v-model="address" type="text" placeholder="Address" readonly />
              <button type="button" class="search-address-btn" @click="searchAddress">주소찾기</button>
            </div>

            <div class="row-input">
              <input v-model="phone" type="text" placeholder="Phone Number" />
            </div>

            <div class="passwordbox">
              <input
                :type="showPassword ? 'text' : 'password'"
                v-model="password"
                placeholder="8자리 이상 영문, 숫자, 특수기호 포함"
              />
              <i
                :class="['fa-solid', showPassword ? 'fa-eye' : 'fa-eye-slash']"
                @click="togglePassword('password')"
              ></i>
            </div>

            <div class="passwordbox">
              <input
                :type="showConfirmPassword ? 'text' : 'password'"
                v-model="confirmPassword"
                placeholder="Confirm Password"
                :class="{ 'error-border': confirmPassword && password !== confirmPassword }"
              />
              <i
                :class="['fa-solid', showConfirmPassword ? 'fa-eye' : 'fa-eye-slash']"
                @click="togglePassword('confirm')"
              ></i>
            </div>

            <div
              v-if="confirmPassword && password !== confirmPassword"
              class="error-box"
            >
              <i class="fa-solid fa-circle-exclamation"></i>
              <span>비밀번호가 일치하지 않습니다.</span>
            </div>

            <div class="checkbox">
              <label>
                <input type="checkbox" v-model="agree" />동의하기
              </label>
            </div>

            <button
              type="submit"
              class="loginbox"
              :class="{ disabled: !isFormValid }"
              :disabled="!isFormValid"
            >
              {{ isVerificationSent ? '인증하고 회원가입 완료' : '회원가입 진행' }}
            </button>
          </form>
        </div>
      </div>

      <div class="righting">
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
  name: "SignUpPage",
  data() {
    return {
      name: "",
      email: "",
      phone: "",
      address: "",
      dateOfBirth: "",
      password: "",
      confirmPassword: "",
      verificationCode: "",
      isVerificationSent: false,
      agree: false,
      showPassword: false,
      showConfirmPassword: false,
      showCode: false,
      currentSlide: 0,
      slides: [
        require("@/assets/img/img.jpg"),
        require("@/assets/img/img2.jpg"),
        require("@/assets/img/img3.jpg"),
      ],
    };
  },
  computed: {
    isPasswordValid() {
      const regex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^&*()_+\-={}[\]:;"'<>,.?/]).{8,}$/;
      return regex.test(this.password) && this.password === this.confirmPassword;
    },
    isFormValid() {
      const baseValid =
        this.name &&
        this.email &&
        this.phone &&
        this.address &&
        this.dateOfBirth &&
        this.isPasswordValid &&
        this.agree;

      return this.isVerificationSent ? (baseValid && this.verificationCode) : baseValid;
    },
  },
  mounted() {
    this.startSlideShow();
  },
  methods: {
    togglePassword(type) {
      if (type === "password") {
        this.showPassword = !this.showPassword;
      } else {
        this.showConfirmPassword = !this.showConfirmPassword;
      }
    },
    toggleCodeVisibility() {
      this.showCode = !this.showCode;
    },
    async sendVerificationCode() {
      if (!this.email || !this.isPasswordValid) {
        alert("이메일과 유효한 비밀번호를 먼저 입력해주세요.");
        return;
      }
      try {
        const response = await axios.post('/api/user', {
          userEmail: this.email,
          password: this.password,
          username: this.name,
          userAddress: this.address,
          userPhone: this.phone,
          userBirth: this.dateOfBirth,
        });
        if (response.data.code === 'SUCCESS') {
          alert(response.data.message);
          this.isVerificationSent = true;
        } else {
          alert(response.data.message);
        }
      } catch (error) {
        console.error("인증번호 전송 실패:", error);
        if (error.response && error.response.data && error.response.data.message) {
          alert(error.response.data.message);
        } else {
          alert("인증번호 전송 중 오류가 발생했습니다.");
        }
      }
    },
    async handleVerify() {
      if (!this.verificationCode) {
        alert("인증번호를 입력해주세요.");
        return;
      }
      try {
        // Step 1: Verify the email with the verification code.
        const verificationResponse = await axios.post('/api/user/verify-email', {
          userEmail: this.email,
          verificationCode: this.verificationCode,
        });

        // Step 2: Check if email verification was successful.
        if (verificationResponse.data.code === 'SUCCESS') {
          alert('이메일 인증 성공! 회원가입을 완료합니다.');
          this.$router.push('/'); // Redirect to main page after successful verification and signup.

        } else {
          // Alert if the verification code was incorrect.
          alert(verificationResponse.data.message);
        }
      } catch (error) {
        console.error("이메일 인증 실패:", error);
        alert("이메일 인증 중 오류가 발생했습니다.");
      }
    },
    async handleFormSubmit() {
      if (!this.isVerificationSent) {
        await this.sendVerificationCode();
      } else {
        await this.handleVerify();
      }
    },
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
    searchAddress() {
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
    },
  },
};
</script>

<style scoped>
@import '~@/assets/css/SignUp.css';
</style>