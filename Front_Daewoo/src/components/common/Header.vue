<template>
  <header>
    <nav class="nav-container">
      <div class="nav-left">
        <div class="nav-item-container"
             @mouseenter="hoveredTab = 'hotel'"
             @mouseleave="hoveredTab = null"
             @click="navigateTo('/hotelsearch'); activeTab = 'hotel';"
             :class="{ active: activeTab === 'hotel' || hoveredTab === 'hotel' }">
          <div class="tab">
            <i class="fa-solid fa-bed"></i>
            hotel
          </div>
        </div>
      </div>

      <div class="item">
        <div class="flex vertical-center">

          <div class="nav-item-container"
               @mouseenter="hoveredTab = 'wishlist'"
               @mouseleave="hoveredTab = null"
               @click="navigateTo('/favorite'); activeTab = 'wishlist';"
               :class="{ active: activeTab === 'wishlist' || hoveredTab === 'wishlist' }">
            <div class="tab">
              <i class="fa-solid fa-heart" style="position: relative; right: 3px"></i>
              <span style="position: relative; bottom: 1px; right: 3px">찜하기</span>
            </div>
          </div>

          <span class="separator">|</span>

          <div class="nav-item-container"
               ref="profileWrapper"
               @mouseenter="hoveredTab = 'profile'"
               @mouseleave="hoveredTab = null"
               :class="{ active: activeTab === 'profile' || hoveredTab === 'profile' }">

            <div class="window"
                 style="display: flex; align-items: center; gap: 7px; position: relative; top: 6px;"
                 @click.stop="toggleProfileDropdown">

              <img v-if="profileImageUrl" :src="profileImageUrl" alt="profile" class="header-profile-image-sm">
              <div v-else class="circle">
                <div class="mini-circle">
                  <div class="check_box"></div>
                </div>
              </div>
              <span>{{ userName }}</span>

            </div>

            <transition name="fade">
              <div v-if="activeTab === 'profile'" class="profile-dropdown">
                <div class="dropdown-profile">

                  <img v-if="profileImageUrl" :src="profileImageUrl" alt="avatar" class="profile-avatar">
                  <div v-else class="profile-avatar"></div>
                  <div class="profile-info">
                    <span class="profile-name">{{ userName }}</span>
                    <span class="profile-status">Online</span>
                  </div>
                </div>
                <hr class="divider">
                <div class="dropdown-menu">
                  <div class="menu-item" :class="{ active: activeDropdownTab === 'account' }" @click="navigateTo('/profile')">
                    <i class="fa-solid fa-user"></i>
                    <span>계정</span>
                    <i class="fa-solid fa-chevron-right arrow"></i>
                  </div>
                  <div class="menu-item" :class="{ active: activeDropdownTab === 'payment' }" @click="navigateTo('/profile')">
                    <i class="fa-solid fa-credit-card"></i>
                    <span>결제내역</span>
                    <i class="fa-solid fa-chevron-right arrow"></i>
                  </div>
                  <div class="menu-item" :class="{ active: activeDropdownTab === 'settings' }" @click="setActiveDropdownTab('settings')">
                    <i class="fa-solid fa-gear"></i>
                    <span>설정</span>
                    <i class="fa-solid fa-chevron-right arrow"></i>
                  </div>
                </div>
                <hr class="divider">
                <div class="dropdown-logout">
                  <div class="menu-item" @click="logout">
                    <i class="fa-solid fa-arrow-right-from-bracket"></i>
                    <span>로그아웃</span>
                  </div>
                </div>
              </div>
            </transition>
          </div>

        </div>

      </div>
    </nav>
    <transition name="fade">
      <div v-if="isDropdownVisible" class="profile-dropdown" ref="dropdownMenu">
        <div class="dropdown-profile">

          <img v-if="profileImageUrl" :src="profileImageUrl" alt="avatar" class="profile-avatar">
          <div v-else class="profile-avatar"></div>
          <div class="profile-info">
            <span class="profile-name">{{ userName }}</span>
            <span class="profile-status">Online</span>
          </div>
        </div>
        <hr class="divider">
        <div class="dropdown-menu">
          <div class="menu-item" :class="{ active: activeTab === 'account' }" @click="navigateTo('/my-page')">
            <i class="fa-solid fa-user"></i>
            <span>계정</span>
            <i class="fa-solid fa-chevron-right arrow"></i>
          </div>
          <div class="menu-item" :class="{ active: activeTab === 'payment' }" @click="setActiveTab('payment')">
            <i class="fa-solid fa-credit-card"></i>
            <span>결제내역</span>
            <i class="fa-solid fa-chevron-right arrow"></i>
          </div>
          <div class="menu-item" :class="{ active: activeTab === 'settings' }" @click="setActiveTab('settings')">
            <i class="fa-solid fa-gear"></i>
            <span>설정</span>
            <i class="fa-solid fa-chevron-right arrow"></i>
          </div>
        </div>
        <hr class="divider">
        <div class="dropdown-logout">
          <div class="menu-item" @click="logout">
            <i class="fa-solid fa-arrow-right-from-bracket"></i>
            <span>로그아웃</span>
          </div>
        </div>
      </div>
    </transition>
  </header>
</template>

<script>
import axios from '@/util/axios';

export default {
  data() {
    return {
      activeTab: 'hotel',
      hoveredTab: null,
      activeDropdownTab: 'account',
      isDropdownVisible: false,
      isLoggedIn: false,
      userName: 'Guest', // 로그인하지 않았을 때 기본값
      profileImageUrl: null, // ⭐️ [추가] 프로필 이미지 URL
    };
  },

  methods: {
    toggleProfileDropdown() {
      this.activeTab = this.activeTab === 'profile' ? null : 'profile';
    },
    setActiveDropdownTab(tabName) {
      this.activeDropdownTab = tabName;
    },
    /**
     * 💡 변경점 2: 로그아웃 로직 구현
     * 로컬 스토리지 토큰 삭제 및 로그인 페이지로 리디렉션
     */
    logout() {
      console.log("로그아웃 처리");
      // 1. 로컬 스토리지에서 토큰을 제거합니다.
      localStorage.removeItem('token');
      // 2. 컴포넌트의 로그인 상태를 업데이트합니다.
      this.isLoggedIn = false;
      this.userName = 'Guest'; // 사용자 이름을 기본값으로 변경
      this.profileImageUrl = null; // ⭐️ [추가] 로그아웃 시 이미지 초기화
      // 3. Vue Router를 사용하여 로그인 페이지('/')로 이동합니다.
      this.$router.push('/');
    },
    /**
     * 💡 변경점 3: 페이지 이동을 위한 메소드 추가
     * @param {string} path - 이동할 경로
     */
    navigateTo(path) {
      this.activeTab = null; // 드롭다운 메뉴를 닫습니다.
      this.$router.push(path); // 지정된 경로로 이동합니다.
    },
    handleOutsideClick(event) {
      if (this.$refs.profileWrapper && !this.$refs.profileWrapper.contains(event.target)) {
        if (this.activeTab === 'profile') {
          this.activeTab = null;
        }
      }
    }
  },
  watch: {
    activeTab(newTab, oldTab) {
      if (newTab === 'profile') {
        document.addEventListener('click', this.handleOutsideClick);
      } else if (oldTab === 'profile') {
        document.removeEventListener('click', this.handleOutsideClick);
      }
    }
  },
  beforeUnmount() {
    document.removeEventListener('click', this.handleOutsideClick);
  },
  async mounted() {
    // 참고: 기존 코드에 있던 handleClickOutside 리스너가 중복으로 보여서 하나로 정리했습니다.
    const token = localStorage.getItem('token');
    if (token) {
      this.isLoggedIn = true;
      try {
        const response = await axios.get('/api/user/profile');
        if (response.data) { // ⭐️ [수정] null 체크
          this.userName = response.data.username;
          this.profileImageUrl = response.data.imageUrl; // ⭐️ [추가] 이미지 URL 저장
        }
      } catch (error) {
        console.error("헤더에서 사용자 정보를 가져오는데 실패했습니다:", error);
        // 토큰이 유효하지 않을 경우 로그아웃 처리
        localStorage.removeItem('token');
        this.isLoggedIn = false;
      }
    }
  },
};
</script>

<style scoped lang="scss">
/* 스타일은 변경되지 않았으므로 그대로 사용하시면 됩니다. */
@use "@/assets/css/Header.scss";

/* ▼▼▼ 아래는 추가/수정된 스타일입니다 ▼▼▼ */
.item {
  position: relative;
}

/* ⭐️ [추가] 헤더의 작은 프로필 이미지 ⭐️ */
.header-profile-image-sm {
  width: 50px; /* .circle 크기에 맞춰 조절 */
  height: 50px; /* .circle 크기에 맞춰 조절 */
  border-radius: 50%;
  object-fit: cover; /* 이미지가 찌그러지지 않게 */
  border: 1px solid #eee;
  position: relative;
  top: -6px; /* 아이콘과 정렬 맞춤 */
}

.window {
  position: relative;
  cursor: pointer;
  padding-bottom: 5px;
}
.window.active-dropdown::after {
  content: '';
  position: absolute;
  bottom: -21px;
  left: 0;
  width: 100%;
  height: 3px;
  background: #46bd7b;
  border-radius: 8px 8px 0 0;
}
.profile-dropdown {
  position: absolute;
  top: 68px;
  right: -1px;
  width: 280px;
  background-color: #ffffff;
  border-radius: 12px;
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
  border: 1px solid #eee;
  z-index: 1100;
  padding: 10px 0;
}
.dropdown-profile {
  display: flex;
  align-items: center;
  padding: 10px 20px;
  gap: 15px;
}

/* ⭐️ [수정] <img> 태그가 이 클래스를 사용할 것을 대비해 object-fit 추가 ⭐️ */
.profile-avatar {
  width: 70px;
  height: 70px;
  border-radius: 50%;
  background-color: #d9d9d9;
  object-fit: cover; /* ⭐️ 추가 */
}

.profile-info {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
}
.profile-name {
  font-weight: 600;
  font-size: 1.1em;
}
.profile-status {
  font-size: 0.9em;
  color: #666;
}
.divider {
  border: none;
  border-top: 1px solid #f0f0f0;
  margin: 10px 0;
}
.dropdown-menu, .dropdown-logout {
  padding: 0 10px;
}
.menu-item {
  display: flex;
  align-items: center;
  padding: 12px 10px;
  border-radius: 8px;
  cursor: pointer;
  gap: 15px;
  font-size: 1em;
  color: #333;
  transition: background-color 0.2s ease;
}
.menu-item:hover {
  background-color: #f5f5f5;
}
.menu-item.active {
  background-color: #eef7f4;
  color: #46bd7b;
  font-weight: 600;
}
.menu-item i {
  width: 20px;
  text-align: center;
}
.menu-item .arrow {
  margin-left: auto;
  font-size: 0.8em;
  color: #aaa;
}
.menu-item.active .arrow {
  color: #46bd7b;
}
.fade-enter-active, .fade-leave-active {
  transition: opacity 0.2s ease, transform 0.2s ease;
}
.fade-enter-from, .fade-leave-to {
  opacity: 0;
  transform: translateY(-10px);
}
</style>