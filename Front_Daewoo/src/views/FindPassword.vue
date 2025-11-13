<template>
  <div class="container">
    <div class="login-box">
      <!-- 왼쪽 -->
      <div class="left">
        <div class="back" @click="goBack">
          <i class="fa-solid fa-chevron-left"></i>
          <p class="font">
            <a href="/">Back to Login</a>
          </p>
        </div>

        <h4>비밀번호 찾기</h4>
        <p>비밀번호를 찾아보세요</p>

        <div class="log_input">
          <div class="passwordbox">
            <input type="text" v-model="email" placeholder="Email" />
          </div>
        </div>

        <button class="loginbox" @click="handleSubmit">제출</button>

        <!-- SNS 로그인 경계선 -->
        <div class="boundary_line">
          <p>Or login with</p>
        </div>

        <!-- SNS 로그인 -->
        <div class="snsbox">
          <div class="facebook">
            <i class="fa-brands fa-facebook"></i>
          </div>
          <div class="google">
            <img
                width="20"
                height="20"
                src="https://img.icons8.com/color/48/google-logo.png"
                alt="google-logo"
            />
          </div>
          <div class="apple">
            <i class="fa-brands fa-apple"></i>
          </div>
        </div>
      </div>

      <!-- 오른쪽 이미지 -->
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
import "@/assets/css/FindPassword.css";

export default {
  name: "FindPasswordPage",
  data() {
    return {
      email: "",
      index: 0,
      slides: [
        require("@/assets/img/img.jpg"),
        require("@/assets/img/img2.jpg"),
        require("@/assets/img/img3.jpg"),
      ],
      timer: null,
    };
  },
  methods: {
    async handleSubmit() {
      if (!this.email) {
        alert("이메일을 입력해주세요.");
        return;
      }
      try {
        const response = await axios.post('/api/user/send-reset-code', {
          userEmail: this.email,
        });

        // 🚨 [수정된 부분] 🚨
        const responseData = response.data;
        const alertMessage = responseData.message || "비밀번호 재설정 코드가 발송되었습니다."; // message가 없으면 기본 메시지 사용

        if (responseData.code === 'SUCCESS') {
          alert(alertMessage);
          this.$router.push(`/authenticate/${this.email}`);
        } else {
          // ERROR 코드가 왔을 때도 message 필드가 없으면 기본 메시지 사용
          alert(alertMessage);
        }
      } catch (error) {
        console.error("비밀번호 찾기 실패:", error);
        if (error.response && error.response.data && error.response.data.message) {
          alert(error.response.data.message);
        } else {
          alert("비밀번호 찾기 중 오류가 발생했습니다. 서버 또는 네트워크 상태를 확인해주세요.");
        }
      }
    },
    goBack() {
      this.$router.push("/");
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
/* 오른쪽 이미지 영역 */
.right {
  width: 400px;
  height: 380px;
  margin-top: 18px;
  flex: 1;
  position: relative;
  overflow: hidden;
  margin-right: 35px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.dots {
  position: absolute;
  bottom: 15px;
  left: 50%;
  bottom: 4%;
  transform: translateX(-50%);
  display: flex;
  gap: 8px;
  z-index: 2;
}

.snsbox div {
  flex: 1;
  border: 1px solid #8DD3BB;
  text-align: center;
  border-radius: 4px;
  height: 40px;
  padding-top: 5px;
  font-size: 15px;
  cursor: pointer;
}
</style>