<template>
  <CommonLayout>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
    <div class="main-container">

      <section class="profile-section">
        <div class="cover-image-container">
          <img
              v-for="(imageSrc, index) in coverImages"
              :key="index"
              :src="imageSrc"
              alt="Cover photo"
              class="cover-image"
              :class="{ active: index === currentCoverIndex }"
          />
        </div>
        <div class="profile-info-container">
          <div class="profile-image-wrapper">
            <img :src="user.profileImage" :alt="`${user.name}'s profile picture`" class="profile-image"/>

            <input type="file"
                   ref="profileImageInput"
                   @change="handleProfileImageUpload"
                   style="display: none;"
                   accept="image/*">
            <button class="edit-profile-btn" @click="triggerProfileImageUpload">
              <i class="fa-solid fa-pen"></i>
            </button>
          </div>
          <h1 class="user-name">{{ user.name }}</h1>
          <p class="user-email">{{ user.email }}</p>
        </div>
      </section>

      <div class="main-content">
        <nav class="tabs">
          <a href="#" class="tab" :class="{ active: activeTab === 'account' }"
             @click.prevent="activeTab = 'account'">계정</a>
          <a href="#" class="tab" :class="{ active: activeTab === 'history' }"
             @click.prevent="activeTab = 'history'">내역</a>
          <a href="#" class="tab" :class="{ active: activeTab === 'payment' }"
             @click.prevent="activeTab = 'payment'">결제수단</a>
        </nav>

        <div v-if="activeTab === 'account'" class="tab-content active" id="account-tab">
          <h1 class="content-title">Account</h1>
          <div v-for="field in accountFields" :key="field.key" class="info-group">
            <label class="info-label">{{ field.label }}</label>
            <div class="info-value-wrapper">
              <template v-if="editingField !== field.key">
                <span class="info-value">{{ user[field.key] }}</span>
                <button v-if="field.key !== 'password' && field.key !== 'email' && field.key !== 'dob'" @click="startEditing(field.key)" class="change-btn">
                  <img src="../assets/img/Edit.svg" alt="Edit" class="edit-icon" style="position: relative; top: -1px">Change
                </button>
              </template>
              <template v-else>
                <input :type="field.type || 'text'" v-model="tempUser[field.key]" class="edit-input" @keyup.enter="saveChanges(field.key)" @keyup.esc="cancelEdit">
                <div class="edit-actions">
                  <button @click="saveChanges(field.key)" class="save-btn">Save</button>
                  <button @click="cancelEdit" class="cancel-btn">Cancel</button>
                </div>
              </template>
            </div>
          </div>
        </div>

        <div v-if="activeTab === 'history'" class="tab-content active" id="history-tab">
          <div class="booking-header">
            <h2 class="content-title">예약 내역</h2>
            <div class="filter-wrapper" ref="filterWrapper">
              <div class="filter-options" @click="isFilterOpen = !isFilterOpen" :class="{ expanded: isFilterOpen }">
                <span>{{ selectedFilter }}</span>
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" viewBox="0 0 24 24" fill="none"
                     stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
                  <polyline points="6 9 12 15 18 9"></polyline>
                </svg>
              </div>
              <div v-if="isFilterOpen" class="filter-dropdown show">
                <div class="dropdown-item" @click="selectFilter('upcoming')">Upcoming</div>
                <div class="dropdown-item" @click="selectFilter('latest')">최신순</div>
                <div class="dropdown-item" @click="selectFilter('oldest')">오래된순</div>
              </div>
            </div>
          </div>
          <div class="room-section">
            <img src="../assets/img/bed.png" alt="" style="position: relative; top: 1px">
            객실
          </div>
          <div class="booking-list">
            <div v-if="filteredBookings.length === 0">
              <p style="text-align:center; color: #888;">예약 내역이 없습니다.</p>
            </div>
            <div v-else v-for="booking in filteredBookings" :key="booking.id" class="booking-item">
              <div class="booking-card">
                <div class="card-left">
                  <div class="hotel-logo-container"><img :src="booking.roomImageSrc" alt="Hotel Logo" class="profile-hotel-logo">
                  </div>
                  <div class="checkin-checkout-dates">
                    <div class="date-section-wrapper">
                      <div class="date-info-group">
                        <span class="date-label">Check-In</span>
                        <span class="date-value">{{ booking.checkInDisplay }}</span>
                      </div>
                      <div class="dash-separator"></div>
                      <div class="date-info-group">
                        <span class="date-label">Check Out</span>
                        <span class="date-value">{{ booking.checkOutDisplay }}</span>
                      </div>
                    </div>
                    <div class="profile-history-divider"></div>

                    <div class="checkIO-container">

                      <div class="checkIO">

                        <div class="checkIO-time">
                          <div class="profile-icon-wrapper">
                            <i class="fa-solid fa-clock"></i>
                          </div>
                          <div class =checkIO-details>
                            <span class="checkIO-label"> 체크인 </span>
                            <span class="checkIO-value"> 12:00pm </span>
                          </div>
                        </div>

                        <div class="checkIO-time">
                          <div class="profile-icon-wrapper">
                            <i class="fa-solid fa-clock"></i>
                          </div>
                          <div class =checkIO-details>
                            <span class="checkIO-label"> 체크아웃 </span>
                            <span class="checkIO-value"> 11:00am </span>
                          </div>
                        </div>

                      </div>

                      <div class="checkIO">
                        <div class="checkIO-time">
                          <div class="profile-icon-wrapper">
                            <i class="fa-solid fa-door-open "></i>
                          </div>
                          <div class =checkIO-details>
                            <span class="checkIO-label"> 방번호 </span>
                            <span class="checkIO-value"> On arrival </span>
                          </div>
                        </div>
                      </div>
                    </div>

                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

      <div v-if="activeTab === 'payment'" class="tab-content active" id="payment-tab">
        <div class="content-title" style= "font-size: 32px; text-align: left">결제수단</div>
        <div class="card-container" ref="slider" @mousedown="handleMouseDown" @mouseleave="handleMouseLeave"
             @mouseup="handleMouseUp" @mousemove="handleMouseMove">
          <div v-for="card in cards" :key="card.id" class="existing-card">
            <div style="text-align: left">
              <span style="font-size: 24px; font-weight: bold;">**** **** ****</span><br>
              <span style="font-size: 32px; font-weight: bold;">{{ card.lastFour }}</span>
            </div>
            <img src="../assets/img/trash.png" @click="deleteCard(card.id)" alt="Delete Card" class="delete-card-btn"
                 style="cursor: pointer; width: 21px; height: 18px">
            <div style="display: flex; justify-content: space-between; align-content: center;">
              <div>
                <span>valid thru</span><br>
                <span style="font-size: 20px; font-weight: bold;">{{ card.expDate }}</span>
              </div>
              <div style="align-content: center">
                <img src="../assets/img/visa.png" height="33" width="52" alt=""/>
              </div>
            </div>
          </div>
          <div class="add-card" @click="openModal">
            <img style="position: absolute; z-index: 1; transform: translate(-50%, -50%); top: 50%; left: 50%" src="../assets/img/circle.png" alt="">
            <img style="position: absolute; z-index: 2; transform: translate(-50%, -50%); top: 50%; left: 50%" src="../assets/img/plus.png" alt="">
          </div>
        </div>
      </div>
    </div>

    <div v-if="isModalOpen" class="modal-overlay show" @click.self="closeModal">
      <div class="modal-content">
        <div class="card_all">
          <p style="font-size: 40px; font-weight: bold; margin-bottom: 20px">카드 추가</p>
          <div class="card_input">
            <label for="cardNumber">Card Number</label>
            <input type="text" id="cardNumber" v-model="newCard.number" placeholder="1234 5678 9101 1121">
          </div>
          <div class="form-row">
            <div class="card_input half-width">
              <label for="expDate">Exp. Date</label>
              <input type="text" id="expDate" v-model="newCard.expDate" placeholder="MM/YY">
            </div>
            <div class="card_input half-width">
              <label for="CVC">CVC</label>
              <input type="text" id="CVC" v-model="newCard.cvc" placeholder="123">
            </div>
          </div>
          <div class="card_input">
            <label for="nameOnCard">Name on Card</label>
            <input type="text" id="nameOnCard" v-model="newCard.name" placeholder="John Doe">
          </div>
          <div class="card_input">
            <label for="country-select">Country or Region</label>
            <div class="select-wrapper">
              <select id="country-select" v-model="selectedCountry">
                <option v-for="country in countries" :key="country.code" :value="country.name">
                  {{ country.name }}
                </option>
              </select>
            </div>
          </div>
          <div class="card_input">
            <div class="checkbox-container">
              <input type="checkbox" id="save-info-checkbox" v-model="newCard.saveInfo">
              <label for="save-info-checkbox">정보 저장하기</label>
            </div>
          </div>
        </div>
        <button @click="addCard"
                style="width: 512px; height: 48px; font-weight: bold; background-color: #8dd3bb; border-radius: 5px; font-size: 14px; border: none;">
          Add Card
        </button>
      </div>
    </div>
  </CommonLayout>
</template>

<script setup>
import CommonLayout from '../components/common/CommonLayout.vue';
import { ref, reactive, computed, watch, onMounted, onBeforeUnmount } from 'vue';
import axios from '../util/axios.js'; // ⭐️ 우리가 설정한 axios 인스턴스

// --- ⭐️ Cover Image Slider Logic START ⭐️ ---
// public/img/cover/ 경로에 이미지 4장이 있다고 가정합니다.
const coverImages = ref([
  '/cover/animal_trip1.jpg',
  '/cover/animal_trip2.jpg',
  '/cover/animal_trip3.jpg',
  '/cover/animal_trip4.jpg'
]);
const currentCoverIndex = ref(0);
// --- ⭐️ Cover Image Slider Logic END ⭐️ ---

const activeTab = ref('account');

// --- 계정 탭 관련 로직 ---

const user = reactive({
  name: '',
  email: '',
  phone: '',
  password: '••••••••••',
  address: '',
  dob: '',
  profileImage: 'https://picsum.photos/seed/profile/120/120'
});

const editingField = ref(null);
const tempUser = reactive({});

const accountFields = ref([
  { key: 'name', label: 'Name' },
  { key: 'email', label: 'Email', type: 'email' },
  { key: 'password', label: 'Password', type: 'password' },
  { key: 'phone', label: 'Phone Number', type: 'tel' },
  { key: 'address', label: 'Address' },
  { key: 'dob', label: 'Date of birth' }
]);

// ⭐️ 프로필 이미지 업로드 로직
const profileImageInput = ref(null);

function triggerProfileImageUpload(){
  profileImageInput.value.click();
}

// ⭐️ [수정된 함수] 백엔드 업로드 로직 추가
async function handleProfileImageUpload(event){
  const file = event.target.files[0];
  if(!file){
    return;
  }

  // 1. (기존 로직) 즉시 미리보기 보여주기
  const reader = new FileReader();
  reader.onload = (e) => {
    user.profileImage = e.target.result; // 미리보기 업데이트
  };
  reader.readAsDataURL(file);

  // 2. (⭐️ 신규 추가) 서버에 실제 업로드
  const formData = new FormData();
  // 'ApiUserController'에서 @RequestParam("image")로 설정했으므로 키(key)는 "image"여야 합니다.
  formData.append('image', file);

  try {
    // 3. (⭐️ 신규 추가) 백엔드 API 호출
    // axios가 JWT 토큰을 헤더에 자동으로 포함하여 전송합니다.
    const response = await axios.post('/api/user/profile-image', formData, {
      headers: {
        'Content-Type': 'multipart/form-data'
      }
    });

    // 4. (⭐️ 신규 추가) 서버로부터 받은 실제 이미지 경로(webPath)로
    //    미리보기 URL을 덮어씁니다.
    // ApiUserController는 response.data.result에 이미지 URL을 담아 반환합니다.
    user.profileImage = response.data.result;
    alert('프로필 이미지가 성공적으로 변경되었습니다.');

  } catch (error) {
    console.error('프로필 이미지 업로드 실패:', error);
    alert('이미지 업로드 중 오류가 발생했습니다.');
    // TODO: 업로드 실패 시, 이전에 보여준 미리보기를 원래 이미지로 되돌리는 로직 추가 (선택)
  }
}

// ⭐️ onMounted 훅 (기존 2개로 나뉘어 있던 것을 1개로 병합)
onMounted(async () => {
  // 1. 사용자 정보 불러오기
  try {
    const response = await axios.get('/api/user/profile');
    const userData = response.data;

    user.name = userData.username;
    user.email = userData.userEmail;
    user.phone = userData.userPhone;
    user.address = userData.userAddress;
    user.dob = userData.userBirth;
    user.profileImage = userData.imageUrl || 'https://picsum.photos/seed/profile/120/120';

  } catch (error) {
    console.error('사용자 정보를 불러오는 데 실패했습니다:', error);
    alert('사용자 정보를 불러오는 중 오류가 발생했습니다.');
  }

  // 2. ⭐️ [수정] 페이지 로드 시 랜덤 커버 이미지 설정
  currentCoverIndex.value = Math.floor(Math.random() * coverImages.value.length);

  // 3. ⭐️ [이동] 외부 클릭 리스너 등록
  document.addEventListener('click', handleClickOutside);
});


function startEditing(fieldKey) {
  Object.assign(tempUser, user);
  editingField.value = fieldKey;
}

async function saveChanges(fieldKey) {
  if (fieldKey === 'email') {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(tempUser.email)) {
      alert('올바른 이메일 형식을 입력해주세요.');
      return;
    }
  }

  try {
    const backendFieldMap = {
      name: 'username',
      email: 'userEmail',
      phone: 'userPhone',
      address: 'userAddress',
      dob: 'userBirth'
    };

    const payload = {};
    payload[backendFieldMap[fieldKey] || fieldKey] = tempUser[fieldKey];

    await axios.patch('/api/user', payload);

    user[fieldKey] = tempUser[fieldKey];
    editingField.value = null;
    alert('정보가 성공적으로 수정되었습니다.');

  } catch (error) {
    console.error('정보 수정에 실패했습니다:', error);
    alert('정보 수정 중 오류가 발생했습니다.');
  }
}

function cancelEdit() {
  editingField.value = null;
}

// --- 내역 탭 관련 로직 ---
const allBookings = ref([
  { id: 1, roomImageSrc: "https://picsum.photos/seed/room1/80/80", checkInDate: "2025-12-08", checkInDisplay: "Thur, Dec 8", checkOutDate: "2025-12-09", checkOutDisplay: "Fri, Dec 9" },
  { id: 2, roomImageSrc: "https://picsum.photos/seed/room2/80/80", checkInDate: "2026-01-15", checkInDisplay: "Mon, Jan 15", checkOutDate: "2026-01-18", checkOutDisplay: "Thur, Jan 18" },
  { id: 3, roomImageSrc: "https://picsum.photos/seed/room3/80/80", checkInDate: "2025-09-20", checkInDisplay: "Mon, Nov 20", checkOutDate: "2025-09-22", checkOutDisplay: "Wed, Nov 22" },
]);
const selectedFilter = ref('upcoming');
const isFilterOpen = ref(false);
const filterWrapper = ref(null);

const filteredBookings = computed(() => {
  const todayStr = new Date().toISOString().split('T')[0];
  switch (selectedFilter.value) {
    case 'upcoming': return allBookings.value.filter(b => b.checkInDate >= todayStr);
    case 'latest': return [...allBookings.value].sort((a, b) => new Date(b.checkInDate) - new Date(a.checkInDate));
    case 'oldest': return [...allBookings.value].sort((a, b) => new Date(a.checkInDate) - new Date(b.checkInDate));
    default: return [];
  }
});

function selectFilter(filter) {
  selectedFilter.value = filter;
  isFilterOpen.value = false;
}

// =================== 결제수단 관련 로직 START ===================
const cards = ref([]);
const isModalOpen = ref(false);
const newCard = ref({ number: '', expDate: '', cvc: '', name: '', country: 'us', saveInfo: false });
const selectedCountry = ref('United States');
const countries = ref([
  { code: 'US', name: 'United States' }, { code: 'CA', name: 'Canada' }, { code: 'KR', name: 'South Korea' }, { code: 'JP', name: 'Japan' }, { code: 'GB', name: 'United Kingdom' },
]);

const slider = ref(null);
const isDown = ref(false);
const startX = ref(0);
const scrollLeft = ref(0);

function openModal() { isModalOpen.value = true; }
function closeModal() {
  isModalOpen.value = false;
  newCard.value = {number: '', expDate: '', cvc: '', name: '', country: 'us', saveInfo: false};
}

function addCard() {
  if (newCard.value.number.length < 19) {
    alert('올바른 카드 번호를 입력하세요.');
    return;
  }
  const cardToAdd = { id: Date.now(), lastFour: newCard.value.number.slice(-4), expDate: newCard.value.expDate };
  cards.value.unshift(cardToAdd);
  if (!newCard.value.saveInfo) { closeModal(); } else { isModalOpen.value = false; }
}

function deleteCard(cardId) {
  if (confirm("정말 이 카드를 삭제하시겠습니까?")) {
    cards.value = cards.value.filter(card => card.id !== cardId);
  }
}

function handleMouseDown(e) {
  isDown.value = true;
  slider.value.style.cursor = 'grabbing';
  startX.value = e.pageX - slider.value.offsetLeft;
  scrollLeft.value = slider.value.scrollLeft;
}
function handleMouseLeave() {
  isDown.value = false;
  if(slider.value) slider.value.style.cursor = 'grab';
}
function handleMouseUp() {
  isDown.value = false;
  if(slider.value) slider.value.style.cursor = 'grab';
}
function handleMouseMove(e) {
  if (!isDown.value) return;
  e.preventDefault();
  const x = e.pageX - slider.value.offsetLeft;
  const walk = (x - startX.value) * 2;
  slider.value.scrollLeft = scrollLeft.value - walk;
}

watch(() => newCard.value.number, (newValue) => {
  const cleaned = newValue.replace(/[^\d]/g, '').slice(0, 16);
  newCard.value.number = cleaned.replace(/(\d{4})(?=\d)/g, '$1 ');
});
watch(() => newCard.value.expDate, (newValue) => {
  const cleaned = newValue.replace(/[^\d]/g, '').slice(0, 4);
  newCard.value.expDate = cleaned.length > 2 ? `${cleaned.slice(0, 2)}/${cleaned.slice(2)}` : cleaned;
});
watch(() => newCard.value.cvc, (newValue) => {
  newCard.value.cvc = newValue.replace(/[^\d]/g, '').slice(0, 3);
});
watch(() => newCard.value.name, (newValue) => {
  newCard.value.name = newValue.replace(/[^a-zA-Z\s]/g, '').toUpperCase();
});
// =================== 결제수단 관련 로직 END =====================

// --- 공통 라이프사이클 훅 ---
const handleClickOutside = (event) => {
  if (filterWrapper.value && !filterWrapper.value.contains(event.target)) {
    isFilterOpen.value = false;
  }
};

// ⭐️ [수정] onBeforeUnmount 훅
onBeforeUnmount(() => {
  // 1. 외부 클릭 리스너 제거
  document.removeEventListener('click', handleClickOutside);

  // 2. ⭐️ [삭제] 슬라이더 타이머 정리 로직 삭제
  /*
  if (coverInterval) {
    clearInterval(coverInterval);
  }
  */
});
</script>

<style scoped>
@import "../assets/css/UserProfile.css";
</style>