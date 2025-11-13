  <template>
    <CommonLayout>
      <!-- 카드 등록시 떠 있을 토스트 알림 -->
      <transition name="toast-fade">
        <div v-if="showSuccessToast" class="success-toast">
          ✅ 카드가 성공적으로 등록되었습니다.
        </div>
      </transition>

      <div class="channel">{{paymentAccommodation.location}} &nbsp; &gt; <span
        style="font-size: 20px; color: black"> &nbsp; {{ comTitle }} </span></div>
      <div class="payment-body">

        <div class="payment-big">
          <div class="big-roomInfo">

            <div class="Reservation_Info">
              <div class="roomInfo">
                <span class="room">{{ roomType.roomTypeName }}</span>
                <span class="roomPrice">₩{{ formatPrice(paymentAccommodation.price) }}/night</span>
              </div>
              <div class="hotel-link">
                <div style="display: flex; justify-content: center;">
                  <div>
                    <img class="payment-hotel-logo" src="../assets/img/cvk_hotel_logo.png" alt="">
                  </div>
                  <div class="hotel-link-details">
                    <div style="font-size: 20px;">
                      {{ comTitle }}
                    </div>
                    <div style="font-size: 14px; color: #888888; text-align: left">
                      <img class="location-logo" src="../assets/img/location.png" alt="">
                      {{ paymentAccommodation.comAddress }}
                    </div>
                  </div>
                </div>
              </div>
              <div class="Reservation_date">
                <div class="date">
                  <span class="day">{{ checkIn }}</span>
                  <span class="check-IO">Check-In</span>
                </div>
                <div class="Reservation_image">
                  <img src="../assets/img/left_line.png" height="7" width="39" alt="" style="margin-right: 30px" />
                  <img src="../assets/img/hotel_image.png" height="45" width="42" alt="" style="margin-right: 30px" />
                  <img src="../assets/img/right_line.png" height="7" width="39" alt="" />
                </div>
                <div class="date">
                  <span class="day">{{ checkOut }}</span>
                  <span class="check-IO">Check-Out</span>
                </div>
              </div>
            </div>

            <div class="payment-type">
              <div
                v-for="option in paymentOptions"
                :key="option.key"
                class="option"
                :class="{ selected: selectedPayment === option.key }"
                @click="option.key === 'simple' ? requestTossPayment() : selectPayment(option.key)"
              >
                <div class="text-group">
                  <span class="paymentTitle">{{ option.title }}</span>
                  <span class="paymentSubtitle">{{ option.subtitle }}</span>
                </div>
                <div class="radio-icon"></div>
              </div>
            </div>

            <div class="card-chest" v-if="selectedPayment === 'card'">
              <div style="font-size: 32px; font-weight: bold; margin-bottom: 25px; text-align: left">카드등록</div>
              <div class="card-container" ref="slider" @mousedown="handleMouseDown" @mouseleave="handleMouseLeave"
                   @mouseup="handleMouseUp" @mousemove="handleMouseMove">
                <div v-for="card in cards" :key="card.id" class="existing-card">
                  <div style="text-align: left">
                    <span style="font-size: 24px; font-weight: bold;">**** **** ****</span><br>
                    <span style="font-size: 32px; font-weight: bold;">{{ card.lastFour }}</span>
                  </div>
                  <img src="../assets/img/trash.png" @click="deleteCard(card.id)" alt="Delete Card"
                       class="delete-card-btn"
                       style="cursor: pointer; width: 21px; height: 18px">
                  <div style="display: flex; justify-content: space-between; align-content: center;">
                    <div style="text-align: left">
                      <span>valid thru</span><br>
                      <span style="font-size: 20px; font-weight: bold;">{{ card.expDate }}</span>
                    </div>
                    <div style="align-content: center">
                      <img src="../assets/img/visa.png" height="33" width="52" alt="" />
                    </div>
                  </div>

                </div>
                <div class="add-card" @click="openModal">
                  <img style="position: absolute; z-index: 1; transform: translate(-50%, -50%); top: 50%; left: 50%"
                       src="../assets/img/circle.png" alt="">
                  <img style="position: absolute; z-index: 2; transform: translate(-50%, -50%); top: 50%; left: 50%"
                       src="../assets/img/plus.png" alt="">
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
                        <div v-if="expDateError" class="card-input-error">
                          {{ expDateError }}
                        </div>
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
                          style="width: 512px; height: 48px; font-weight: bold; background-color: #8dd3bb; border-radius: 5px; font-size: 14px; border: none; cursor: pointer">
                    Add Card
                  </button>
                </div>
              </div>
            </div>
            <div class="Reservation_sign" v-if="selectedPayment === 'simple'">

              <div class="social-login-buttons">
                <div class="social-button-box">
                  <img src="../assets/img/login_facebook.png" alt="">
                </div>
                <div class="social-button-box">
                  <img src="../assets/img/login_google.png" alt="">
                </div>
                <div class="social-button-box">
                  <img src="../assets/img/login_apple.png" alt="">
                </div>
              </div>
            </div>

          </div>
        </div>
        <!--    <div class="Reservation_sign">-->
        <!--      <span style="font-size: 20px; font-weight: bold; display: block; padding: 15px 0">Login or Sign up to book</span>-->
        <!--      <input class="Reservation_phoneNumber" placeholder="Phone Number" v-model="phoneNumber">-->
        <!--      <span style="font-size: 14px; display: block; padding: 15px 0">예약확인 문자/전화를 위해 전화번호를 남겨주세요</span>-->
        <!--      <button class="continue" :disabled="!isContinueButtonEnabled">continue</button>-->
        <!--      <div class="boundary_line_1">-->
        <!--        <p>Or</p>-->
        <!--      </div>-->
        <!--      <div class="social-login-buttons">-->
        <!--        <div class="social-button-box">-->
        <!--          <img src="@/assets/login_facebook.png" alt="">-->
        <!--        </div>-->
        <!--        <div class="social-button-box">-->
        <!--          <img src="@/assets/login_google.png" alt="">-->
        <!--        </div>-->
        <!--        <div class="social-button-box">-->
        <!--          <img src="@/assets/login_apple.png" alt="">-->
        <!--        </div>-->
        <!--      </div>-->
        <!--      <div class="continue-with-email">-->
        <!--        <img src="@/assets/email_icon.png" alt="">-->
        <!--        <span>Continue with email</span>-->
        <!--      </div>-->
        <!--    </div>-->

        <div class="payment-small">
          <div class="payment-small-body">
            <div class="hotel-details">
              <img src={{paymentAccommodation.mainImage}} height="120" width="121" alt="" />
              <div>
                <div style="display: block; font-size: 16px; color: #888888; text-align: left">{{ comTitle }}</div>
                <div style="font-size: 20px; font-weight: bold; text-align: left; margin-bottom: 15px">
                  {{ roomType.roomTypeName }}
                </div>
                <div class="mini-review">
                  <div class="review-box">{{ paymentAccommodation.reviewAvg }}</div>
                  <div style="position: relative; bottom: 1px">
                    <span style="font-weight: bold; font-size: 14px">Very good</span>
                    <span style="font-size: 12px; margin-left: 10px">{{ paymentAccommodation.reviewCount }} reviews</span>
                  </div>
                </div>
              </div>
            </div>
            <div class="boundary_line">
            </div>
            <div class="reservation-protect">
              Your booking is protected by <span style="font-weight: bold">golobe</span>
            </div>
            <div class="boundary_line">
            </div>
            <div class="price-details">
              <div style="font-size: 16px; font-weight: bold; text-align: left; margin-bottom: 5px">Price Details</div>
              <div class="price-type">
                <span>Base Fare</span>
                <span class="detail-amount">₩{{ formatPrice(paymentAccommodation.price) }}</span>
              </div>
              <div class="price-type">
                <span>Discount</span>
                <span class="detail-amount">₩{{ formatPrice(paymentAccommodation.discount) }}</span>
              </div>
              <div class="price-type">
                <span>Taxes</span>
                <span class="detail-amount">₩{{ formatPrice(paymentAccommodation.price * 0.1) }}</span>
              </div>
              <div class="price-type">
                <span>Service Fee</span>
                <span class="detail-amount">₩5,000</span>
              </div>
            </div>
            <div class="boundary_line">
            </div>
            <div class="price-type">
              <span class="price-type">Total</span>
              <span class="detail-amount">₩{{ formatPrice(TotalPrice) }}</span>
            </div>
          </div>
        </div>
      </div>
    </CommonLayout>
  </template>

  <script setup lang="js">
  import { ref, watch, onMounted, computed } from 'vue'; // (1) onMounted 추가
  import { useRoute } from 'vue-router'; // (2) useRoute 추가
  import axios from 'axios'; // (3) axios 추가
  import CommonLayout from '../components/common/CommonLayout.vue';

  // ----- (4) API 데이터를 저장할 ref 변수 선언 -----
  const comTitle = ref('');
  const roomType = ref({}); // 객실 정보 (이름, 가격 등)
  const paymentAccommodation = ref({}); // 숙소 정보 (주소, 이름 등)
  const checkIn = ref('');
  const checkOut = ref('');
  // (가격 정보 등 추가 ref)

  // ▼▼▼ [ 1. 토스트 알림용 ref 추가 ] ▼▼▼
  const showSuccessToast = ref(false); // 토스트 표시 여부
  const toastTimer = ref(null);      // 토스트 타이머

  let tossPayments = null;
  let tossPaymentsLoadPromise = null;

  // --- Toss Payments SDK 로드 ---
  const loadTossPayments = () => {
    if (tossPaymentsLoadPromise) {
      return tossPaymentsLoadPromise;
    }

    tossPaymentsLoadPromise = new Promise((resolve, reject) => {
      const script = document.createElement('script');
      script.src = 'https://js.tosspayments.com/v1/payment.js';
      script.crossOrigin = 'anonymous';
      script.onload = () => {
        try {
          const clientKey = 'test_ck_EP59LybZ8BLeQDq27EjbV6GYo7pR';
          tossPayments = window.TossPayments(clientKey);
          resolve(tossPayments);
        } catch (error) {
          reject(error);
        }
      };
      script.onerror = (error) => {
        tossPaymentsLoadPromise = null; // Allow retrying
        reject(error);
      };
      document.head.appendChild(script);
    });

    return tossPaymentsLoadPromise;
  };

  onMounted(() => {
    loadTossPayments().catch(error => {
      console.error('Failed to load Toss Payments SDK:', error);
      alert('결제 모듈을 불러오는 데 실패했습니다. 페이지를 새로고침하거나 다시 시도해주세요.');
    });
  });


  // --- 토스 결제 요청 ---
  const requestTossPayment = async () => {
    selectedPayment.value = 'simple';
    try {
      await loadTossPayments();
      tossPayments.requestPayment('토스페이', {
        amount: 265000,
        orderId: `order_${new Date().getTime()}`,
        orderName: 'Superior room - 1 더블베드 or 2 트윈 베드',
        customerName: 'Tomhoon',
        successUrl: `${window.location.origin}/payment/success`,
        failUrl: `${window.location.origin}/payment/fail`,
      }).catch(error => {
        console.error('결제 요청 실패:', error);
        alert('결제 요청에 실패했습니다. 다시 시도해주세요.');
      });
    } catch (error) {
      console.error('결제 모듈 로드 또는 결제 요청 실패:', error);
      alert('결제 중 오류가 발생했습니다. 다시 시도해주세요.');
    }
  };


  const priceDetails = ref({
    baseFare: 0,
    discount: 0,
    taxes: 0,
    serviceFee: 0,
    total: 0,
  });

  // (5) vue-router의 useRoute 훅을 사용해 현재 URL 정보에 접근
  const route = useRoute();

  // --- 기존 카드 관련 로직 ---
  const cards = ref([]); // (수정) API에서 가져와 채울 빈 배열로 시작
  const paymentOptions = ref([
    // ... (기존과 동일)
    {
      key: 'card',
      title: '카드결제',
      subtitle: '카드 등록 후 결제가 진행됩니다.',
    },
    {
      key: 'simple',
      title: '간편결제',
      subtitle: '카드 등록 없이 간편인증으로 결제가 진행됩니다.',
    },
  ]);
  const selectedPayment = ref(null);
  const selectPayment = (key) => {
    selectedPayment.value = key;
  };
  const isModalOpen = ref(false);
  const newCard = ref({
    number: '', expDate: '', cvc: '', name: '', country: 'us', saveInfo: false,
  });
  const expDateError = ref('');
  const selectedCountry = ref('United States');
  const countries = ref([
    { code: 'US', name: 'United States' },
    { code: 'CA', name: 'Canada' },
    { code: 'KR', name: 'South Korea' },
    { code: 'JP', name: 'Japan' },
    { code: 'GB', name: 'United Kingdom' },
  ]);
  const slider = ref(null);
  const isDown = ref(false);
  const startX = ref(0);
  const scrollLeft = ref(0);

  function openModal() {
    isModalOpen.value = true;
  }

  function closeModal() {
    isModalOpen.value = false;
    newCard.value = { number: '', expDate: '', cvc: '', name: '', country: 'us', saveInfo: false };
  }

  // ----- (6) 컴포넌트가 마운트될 때 (화면에 그려질 때) 실행될 로직 -----
  onMounted(() => {
    // 1. URL 쿼리에서 정보 추출 및 ref에 할당
      // (예: /payment?accId=12&checkIn=2025-11-20&checkOut=2025-11-21&location=서울)
    const accId = route.query.accId;
    checkIn.value = route.query.checkIn || '날짜 없음';
    checkOut.value = route.query.checkOut || '날짜 없음';

    // 2. URL에서 가져온 ID로 API 호출
    if (accId) {
      fetchPaymentData(accId);
    } else {
      console.error('URL에 accId가 없습니다.');
      alert('객실 정보를 불러올 수 없습니다.');
    }

    // 3. (로그인된 사용자의) 카드 목록 불러오기
    fetchCards();
  });

  // ----- (7) (추가) 결제 상세 정보를 API로 가져오는 함수 -----
  async function fetchPaymentData(accId) {
    try {
      // '/api/room/{id}' 엔드포인트를 호출합니다.
      const response = await axios.get(`/api/room/${accId}`);

      // 백엔드가 ResponseDto로 감쌌기 때문에 response.data.result 사용
      if (response.data.code === 'SUCCESS') {
        const result = response.data.result;

        // API 응답(PaymentAccRoomTypeDto)을 기반으로 ref 변수 채우기
        paymentAccommodation.value = result.paymentAccommodation;
        roomType.value = result.roomType;

        // 템플릿 상단의 comTitle도 채워줍니다.
        comTitle.value = result.paymentAccommodation.comTitle;

        // (가정) 가격 정보도 API에서 받아와서 설정
        // AccRoomTypeDto에 가격이 있다고 가정
        // priceDetails.value.baseFare = result.roomType.price || 240000;
        // priceDetails.value.taxes = (result.roomType.price || 240000) * 0.1; // 예시: 10%
        // priceDetails.value.serviceFee = 5000; // 예시
        // priceDetails.value.total = priceDetails.value.baseFare + priceDetails.value.taxes + priceDetails.value.serviceFee;

      } else {
        throw new Error(response.data.message);
      }
    } catch (error) {
      console.error('결제 정보 로딩 실패:', error);
      alert('결제 정보를 불러오는 데 실패했습니다.');
    }
  }

  // ----- (8) (수정) 카드 목록을 API로 가져오는 함수 -----
  // (line 300 근처)
  async function fetchCards() {
    // 1. 로컬 스토리지에서 토큰 가져오기
    const token = localStorage.getItem('token');

    if (!token) {
      console.error('토큰이 없어 카드 목록을 가져올 수 없습니다.');
      return; // 토큰이 없으면 요청 중단
    }

    try {
      // 2. 'axios.get'의 두 번째 인자로 config 객체(headers 포함) 전달
      const response = await axios.get('/api/card', {
        headers: {
          'Authorization': `Bearer ${token}`, // ✨ 이 헤더가 필수입니다!
        },
      });

      if (response.data.code === 'SUCCESS') {
        // API 응답(List<CardDto>)을 cards ref에 할당
        cards.value = response.data.result.map(card => ({
          id: card.cardId,
          lastFour: card.cardNumber.slice(-4),
          expDate: card.expDate,
        }));
      } else {
        throw new Error(response.data.message);
      }
    } catch (error) {
      console.error('카드 목록 로딩 실패:', error);
      // (로그인 안 했을 수도 있으니 alert 대신 콘솔 로그만)
    }
  }

  function validateExpDate(){
    const value = newCard.value.expDate;

    if(!value || value.trim().length === 0){
      expDateError.value = '유효기간을 입력해주세요.';
      return false;
    }

    if (!/^(0[1-9]|1[0-2])\/\d{2}$/.test(value)) {
      expDateError.value = '*카드 유효기간을 다시 확인해주세요.';
      return false;
    }

    // --- 형식 통과 시, 날짜 검사 ---
    const [expMonthStr, expYearStr] = value.split('/');
    const expMonth = parseInt(expMonthStr);
    const expYearYY = parseInt(expYearStr);
    const expYearYYYY = 2000 + expYearYY; // "28" -> 2028

    const now = new Date();
    const currentYearYYYY = now.getFullYear(); // 예: 2025
    const currentMonth = now.getMonth() + 1; // 예: 10 (10월)
    const maxYearYYYY = currentYearYYYY + 10;  // 예: 2035

    // 3. 년도 범위 검사 (현재년도 ~ +10년)
    if (expYearYYYY < currentYearYYYY || expYearYYYY > maxYearYYYY) {
      expDateError.value = '*카드 유효기간을 다시 확인해주세요.';
      return false;
    }

    // 4. 만료일 검사 (올해 만료되는 경우)
    if (expYearYYYY === currentYearYYYY && expMonth < currentMonth) {
      expDateError.value = '카드 유효기간을 다시 확인해주세요.';
      return false;
    }

    // 5. 모든 검증 통과
    expDateError.value = ''; // 오류 메시지 제거
    return true;
  }

  // ----- (9) (수정) 카드 추가 API 호출 -----
  async function addCard() {
    if (!validateExpDate()){
      return;
    }

    const token = localStorage.getItem('token');

    if (newCard.value.number.length < 19) {
      alert('올바른 카드 번호를 입력하세요.');
      return;
    }

    if (!newCard.value.cvc || newCard.value.cvc.length < 3) {
      alert('CVC 번호를 3자리 모두 입력하세요.');
      return;
    }

    // [수정] 이름 유효성 검사 추가 (비어있는지 확인)
    if (!newCard.value.name.trim()) {
      alert('카드 소유자 이름을 입력하세요.');
      return;
    }

    // CardDto 형식에 맞게 데이터 준비
    const cardData = {
      cardNumber: newCard.value.number.replace(/\s/g, ''), // 공백 제거
      expDate: newCard.value.expDate,
      cvc: parseInt(newCard.value.cvc),
      name: newCard.value.name,
      country: selectedCountry.value,
    };

    try {
      // 'POST /api/card' 엔드포인트 호출
      const response = await axios.post('api/card', cardData, {
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${token}`,
        },
      });

      if (response.data.code === 'SUCCESS') {
        // 1. 등록 성공
        console.log('카드 등록 성공:', response.data);
        // 2. 모달을 닫습니다.
        closeModal();
        // 3. 카드 목록을 새로고침하여 방금 추가한 카드를 표시합니다.
        await fetchCards();

        // 1. 혹시 이전 토스트 타이머가 남아있다면 제거
        if (toastTimer.value) {
          clearTimeout(toastTimer.value);
        }

        // 2. 토스트 보이기
        showSuccessToast.value = true;

        // 3. 2초 뒤에 토스트 숨기기
        toastTimer.value = setTimeout(() => {
          showSuccessToast.value = false;
          toastTimer.value = null; // 타이머 ID 초기화
        }, 2000); // 2000ms = 2초
      } else {
        // (API가 200으로 응답했지만, 백엔드에서 실패 메시지를 보낸 경우)
        throw new Error(response.data.message || '카드 등록에 실패했습니다.');
      }
    } catch (error) {
      // (여기서 500 에러 등 네트워크 오류가 발생)
      console.error('카드 등록 실패:', error);
      alert(error.message || '카드 등록 중 오류가 발생했습니다.');
    }
  }

  // ----- (10) (수정) 카드 삭제 API 호출 -----
  async function deleteCard(cardId) {
    const token = localStorage.getItem('token');
    if (confirm('정말 이 카드를 삭제하시겠습니까?')) {
      try {

        // 'DELETE /api/card/{cardId}' 엔드포인트 호출
        const response = await axios.delete(`/api/card/${cardId}`, {
          headers: {
            'Authorization': `Bearer ${token}`, // ✨ 이 헤더가 필수입니다!
          },
        });
        if (response.data.code === 'SUCCESS') {
          // API 성공 시, 로컬 목록에서 즉시 제거
          cards.value = cards.value.filter(card => card.id !== cardId);
        } else {
          throw new Error(response.data.message);
        }
      } catch (error) {
        console.error('카드 삭제 실패:', error);
        alert('카드 삭제에 실패했습니다.');
      }
    }
  }

  // --- 기존 드래그 스크롤 함수 (변경 없음) ---
  function handleMouseDown(e) {
    isDown.value = true;
    slider.value.style.cursor = 'grabbing';
    startX.value = e.pageX - slider.value.offsetLeft;
    scrollLeft.value = slider.value.scrollLeft;
  }

  function handleMouseLeave() {
    isDown.value = false;
    if (slider.value) slider.value.style.cursor = 'grab';
  }

  function handleMouseUp() {
    isDown.value = false;
    if (slider.value) slider.value.style.cursor = 'grab';
  }

  function handleMouseMove(e) {
    if (!isDown.value) return;
    e.preventDefault();
    const x = e.pageX - slider.value.offsetLeft;
    const walk = (x - startX.value) * 2;
    slider.value.scrollLeft = scrollLeft.value - walk;
  }

  // --- 기존 입력값 자동 서식 (변경 없음) ---
  watch(() => newCard.value.number, (newValue) => {
    const cleaned = newValue.replace(/[^\d]/g, '').slice(0, 16);
    let formatted = cleaned.replace(/(\d{4})(?=\d)/g, '$1 ');
    newCard.value.number = formatted;
  });
  watch(() => newCard.value.expDate, (newValue) => {
    let cleaned = newValue.replace(/[^\d]/g, '').slice(0, 4);
    const formatted = cleaned.length > 2 ? `${cleaned.slice(0, 2)}/${cleaned.slice(2)}` : cleaned;

    if(newValue !== formatted){
      newCard.value.expDate = formatted;
    }

    if(formatted.length === 5){
      validateExpDate();
    }
    else if (expDateError.value){
      expDateError.value = '';
    }
  });
  watch(() => newCard.value.cvc, (newValue) => {
    newCard.value.cvc = newValue.replace(/[^\d]/g, '').slice(0, 3);
  });
  watch(() => newCard.value.name, (newValue) => {
    newCard.value.name = newValue.replace(/[^a-zA-Z\s]/g, '').toUpperCase();
  });

  const formatPrice = (price) => {
    if (price === undefined || price === null) return '0';
    return Number(price).toLocaleString('ko-KR');
  };

  // TotalPrice 계산 속성 (숫자로 반환)
  const TotalPrice = computed(() => {
    const basePrice = Number(paymentAccommodation.value.price) || 0;
    const discount = Number(paymentAccommodation.value.discount) || 0;
    const tax = basePrice * 0.1;
    const serviceFee = 5000;
    return basePrice - discount + tax + serviceFee;
  });

  </script>

  <style scoped>
  @import '../assets/css/PaymentBody.css';

  </style>