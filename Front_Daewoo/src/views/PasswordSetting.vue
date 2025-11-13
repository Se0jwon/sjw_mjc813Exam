<template>
  <div class="container">
    <div class="login_box">

      <div class="left">
        <h4>비밀번호 설정</h4>
        <p>비밀번호 다시 설정하기</p>

        <div class="form-area">
          <form @submit.prevent="handleSubmit">
            <div class="passwordbox">
              <input
                  :type="showPassword ? 'text' : 'password'"
                  id="password"
                  placeholder="Create Password"
                  v-model="password"
              />
              <i
                  :class="showPassword ? 'fa-solid fa-eye-slash' : 'fa-solid fa-eye'"
                  @click="togglePassword('password')"
              ></i>
            </div>

            <div class="passwordbox">
              <input
                  :type="showConfirm ? 'text' : 'password'"
                  id="confirmPassword"
                  placeholder="Re-enter Password"
                  v-model="confirmPassword"
                  :class="{ 'error-border': confirmPassword && !passwordsMatch }"
              />
              <i
                  :class="showConfirm ? 'fa-solid fa-eye-slash' : 'fa-solid fa-eye'"
                  @click="togglePassword('confirm')"
              ></i>
            </div>
          </form>
        </div>

        <div v-if="confirmPassword && !passwordsMatch" class="error-box">
          <i class="fa-solid fa-circle-exclamation"></i>
          <span>비밀번호가 일치하지 않습니다.</span>
        </div>

        <button class="submit" @click="handleSubmit">비밀번호 설정</button>
      </div>

      <div class="right">
        <img
            v-for="(image, i) in images"
            :key="i"
            :src="image"
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
import axios from 'axios';

export default {
  name: "PasswordSettingPage",
  data() {
    return {
      password: "",
      confirmPassword: "",
      showPassword: false,
      showConfirm: false,
      images: [
        require("@/assets/img/img.jpg"),
        require("@/assets/img/img2.jpg"),
        require("@/assets/img/img3.jpg"),
      ],
      currentIndex: 0,
      intervalId: null,
      userEmail: this.$route.params.userEmail,
      verificationCode: this.$route.params.verificationCode,
    };
  },
  computed: {
    passwordsMatch() {
      // Return true if passwords match, false otherwise.
      // The condition `this.password && this.confirmPassword` ensures the check only runs when both fields have content.
      return this.password === this.confirmPassword;
    },
    isPasswordValid() {
      // This is a simple example. You can add more complex validation here
      const regex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^&*()_+\-={}[\]:;"'<>,.?/]).{8,}$/;
      return regex.test(this.password) && this.passwordsMatch;
    }
  },
  methods: {
    togglePassword(target) {
      if (target === "password") {
        this.showPassword = !this.showPassword;
      } else if (target === "confirm") {
        this.showConfirm = !this.showConfirm;
      }
    },
    async handleSubmit() {
      if (!this.password || !this.confirmPassword) {
        alert("비밀번호를 입력해주세요.");
        return;
      } else if (!this.passwordsMatch) {
        alert("비밀번호가 일치하지 않습니다.");
        return;
      } else if (!this.isPasswordValid) {
        alert("비밀번호는 8자리 이상, 영문, 숫자, 특수기호를 포함해야 합니다.");
        return;
      }

      try {
        const response = await axios.patch('/api/user/reset-password', {
          userEmail: this.userEmail,
          verificationCode: this.verificationCode,
          newPassword: this.password
        });

        if (response.data.code === 'SUCCESS') {
          alert(response.data.message);
          this.$router.push("/");
        } else {
          alert(response.data.message);
        }
      } catch (error) {
        console.error("비밀번호 재설정 실패:", error);
        alert("전에 입력한 비밀번호와 같습니다.");
      }
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
@import "@/assets/css/PasswordSetting.css";
/* 오른쪽 이미지 영역 */
.right {
  width: 400px;
  height: 290px;
  flex: 1;
  position: relative;
  overflow: hidden;
  margin-top: 18px;
  margin-right: 35px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.dots {
  position: absolute;
  bottom: 5%;
  left: 50%;
  transform: translateX(-50%);
  display: flex;
  gap: 8px;
  z-index: 2;
}

/* 아래 스타일을 추가해주세요 */
.error-box {
  display: flex;
  align-items: center;
  gap: 6px;
  font-size: 12px;
  color: red;
  margin-top: 4px;
}

.error-box i {
  font-size: 13px;
}

.error-border {
  border: 1px solid red !important;
}
</style>