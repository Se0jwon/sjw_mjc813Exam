<template>
  <div>
    <!-- 헤더 -->
    <header>
      <nav class="nav-container">
        <!-- 왼쪽 -->
        <nav class="header_tabs">
          <div class="nav-left">
            <div class="tab active">
              <i class="fa-solid fa-bed"></i>
              hotel
            </div>
          </div>
        </nav>

        <!-- 오른쪽 -->
        <div class="item">
          <div class="flex vertical-center">
            <i class="fa-solid fa-heart"></i>
            <span>찜하기</span>
            |
            <div class="circle">
              <div class="mini-circle">
                <div class="check"></div>
              </div>
            </div>
            <span>Tomhoon</span>
          </div>
        </div>
      </nav>
    </header>

    <!-- Hero 섹션 -->
    <section class="hero">
      <div class="image">
        <img src="@/assets/img/header.jpg" alt="메인" />
      </div>

      <div class="hero_content">
        <div class="box">
          <h2>플러스 호텔 및 다양한<br />숙소를 확인하세요!</h2>
          <p>
            검색을 통해 요금을 비교하고 무료 취소를<br />
            포함한 특가도 확인하세요!
          </p>
        </div>
      </div>

      <!-- 검색 박스 -->
      <div class="search_box">
        <h3 class="search_title">Where are you staying?</h3>

        <div class="search_inputs">
          <div class="input-group">
            <label for="destination">Enter Destination</label>
            <input
                type="text"
                id="destination"
                v-model="destination"
                placeholder="Enter Destination"
            />
          </div>

          <div class="input-group">
            <label for="checkin">Check-in</label>
            <input type="date" id="checkin" v-model="checkin" />
          </div>

          <div class="input-group">
            <label for="checkout">Check-out</label>
            <input type="date" id="checkout" v-model="checkout" />
          </div>


          <!-- Rooms & Guests -->
          <div class="input-group dropdown-container">
            <label>Rooms & Guests</label>
            <button class="number_people" @click="openPeopleModal">
              {{ rooms }} Room, {{ guests }} Guest
            </button>
          </div>

          <button class="search_icon" @click="handleSearch">
            <i class="fa-solid fa-magnifying-glass"></i>
          </button>

        </div>
      </div>
    </section>

    <!-- Rooms & Guests 모달 -->
    <transition name="slide-up">
      <div v-if="showPeopleModal" class="people_modal" @click.self="closePeopleModal">
        <div class="people_content">
          <h3>방 개수와 인원수 선택</h3>

          <div class="counter">
            <span>Rooms</span>
            <div class="controls">
              <button @click="decrease('room')">-</button>
              <span>{{ rooms }}</span>
              <button @click="increase('room')">+</button>
            </div>
          </div>

          <div class="counter">
            <span>Guests</span>
            <div class="controls">
              <button @click="decrease('guest')">-</button>
              <span>{{ guests }}</span>
              <button @click="increase('guest')">+</button>
            </div>
          </div>

          <div class="warning" v-if="guests < 2">
            <i class="fa-solid fa-circle-exclamation"></i>
            <p>최소 2명이상 선택해주세요.</p>
          </div>

          <div class="modal-actions">
            <button @click="closePeopleModal" class="close_btn">Close</button>
            <button @click="applyPeople" class="apply_btn">Check</button>
          </div>
        </div>
      </div>
    </transition>

    <!-- 여행 섹션 -->
    <section class="travel_container">
      <h3>여행에 빠지다</h3>
      <p>특가상품으로 진행하는 여행을 예약해보세요.</p>

      <div class="travel_box">
        <div class="slider">
          <div class="card" v-for="(item, i) in travelItems" :key="i">
            <img :src="require(`@/assets/img/${item.image}`)" :alt="item.city" />
            <div class="card_text">
              <h4>{{ item.city }}</h4>
              <p class="setting">{{ item.desc }}</p>
              <p>{{ item.price }}</p>
              <button>Book a Hotel</button>
            </div>
          </div>
        </div>
      </div>
    </section>

    <!-- 여행 더보기 -->
    <section class="tour_container">
      <h3>여행 더보기</h3>
      <p>
        Going somewhere to celebrate this season? Whether you’re going home or somewhere to roam,
        we’ve got the travel tools to get you to your destination.
      </p>
      <div class="tour_box">
        <div class="tour_images">
          <div class="one_block">
            <img src="@/assets/img/malacca_img.jpg" alt="말라카1" />
            <img src="@/assets/img/malacca_img2.jpg" alt="말라카2" />
          </div>
          <div class="two_block">
            <img src="@/assets/img/malacca_img3.jpg" alt="말라카3" />
            <img src="@/assets/img/malacca_img4.jpg" alt="말라카4" />
          </div>
        </div>

        <div class="tour_text_box">
          <div class="tour_header">
            <h1>말라카 투어</h1>
            <div class="free">
              <p>From<br /><b>$700</b></p>
            </div>
          </div>
          <p>
            오래된 시간의 숨결이 머무는 도시, 말라카(Melaka).<br>
            말레이시아의 작은 보석 같은 이 도시는 동서양 문화가 만나는 관문이자, 세계문화유산으로<br>
            지정된 매혹적인 여행지입니다. 단 하루만 머물러도 그 깊은 매력에 빠지고,<br>
            며칠을 살아보면 다시 찾고 싶은 마음이 샘솟는 곳. 이제 저희 여행사가 준비한 특별한 말라카 투어상품과 함께 그 여정을 시작해 보세요.
          </p>
          <button>Book Flight</button>
        </div>
      </div>
    </section>

    <!-- footer -->
    <footer>
      <section class="subscription-box">
        <div class="subscription-content">
          <h1>구독서비스<br /> 신청해보세요</h1>
          <p>The Travel</p>
          <p>구독하고 무료, 최신 여행 정보를 받아보세요</p>
          <div class="subscription-form-container">
            <input type="email" placeholder="Your email address" />
            <button type="submit">Subscribe</button>
          </div>
        </div>
        <div class="subscription-image">
          <img src="@/assets/img/letter-box.png" height="330" width="400" alt="" />
        </div>
      </section>

      <!-- footer-bottom -->
      <div class="footer-bottom">
        <div class="footer-letter">
          <span>
            <i class="fa-brands fa-facebook"></i>
            <i class="fa-brands fa-twitter"></i>
            <i class="fa-brands fa-youtube"></i>
            <i class="fa-brands fa-instagram"></i>
          </span>
        </div>
        <div class="footer-letter">
          <span class="footer-span">Our Destinations</span>
          <span class="footer-span-details">
            <span>Canada</span>
            <span>Alaska</span>
            <span>France</span>
            <span>Iceland</span>
          </span>
        </div>
        <div class="footer-letter">
          <span class="footer-span">Our Activities</span>
          <span class="footer-span-details">
            <span>Northern Lights</span>
            <span>Cruising & sailing</span>
            <span>Multi-activities</span>
            <span>Kayaing</span>
          </span>
        </div>
        <div class="footer-letter">
          <span class="footer-span">Travel Blogs</span>
          <span class="footer-span-details">
            <span>Bali Travel Guide</span>
            <span>Sri Lanka Travel Guide</span>
            <span>Peru Travel Guide</span>
          </span>
        </div>
        <div class="footer-letter">
          <span class="footer-span">About Us</span>
          <span class="footer-span-details">
            <span>Our Story</span>
            <span>Work with us</span>
          </span>
        </div>
        <div class="footer-letter">
          <span class="footer-span">Contact Us</span>
          <span class="footer-span-details">
            <span>Email</span>
            <span>Support</span>
          </span>
        </div>
      </div>
    </footer>
  </div>
</template>

<script>
import "@/assets/css/HotelSearch.css";
export default {
  name: "HotelSearch",
  data() {
    return {
      destination: "",
      checkin: "",
      checkout: "",
      rooms: 1,
      guests: 2,
      showPeopleModal: false,
      travelItems: [
        { city: "멜버른", desc: "Amazing journey", price: "₩130,000", image: "melbourne.jpg" },
        { city: "파리", desc: "A Paris Adventure", price: "₩150,000", image: "paris.jpg" },
        { city: "런던", desc: "London eye adventure", price: "₩130,000", image: "london.jpg" },
        { city: "콜롬비아", desc: "Amazing streets", price: "₩130,000", image: "colombia.jpg" },
        { city: "도쿄", desc: "Amazing Country", price: "₩200,000", image: "Tokyo.jpg" },
        { city: "뉴욕", desc: "Amazing Country", price: "₩400,000", image: "New_York.jpg" },
        { city: "시드니", desc: "Amazing Country", price: "₩300,000", image: "Sydney.jpg" },
        { city: "두바이", desc: "Amazing Country", price: "₩220,000", image: "Dubai.jpg" },
        { city: "타이베이", desc: "Amazing Country", price: "₩140,000", image: "Taipei.jpg" },
      ],
    };
  },
  methods: {
    openPeopleModal() {
      this.showPeopleModal = true;
    },
    closePeopleModal() {
      this.showPeopleModal = false;
    },
    increase(type) {
      if (type === "room") this.rooms++;
      if (type === "guest") this.guests++;
    },
    decrease(type) {
      if (type === "room" && this.rooms > 1) this.rooms--;
      if (type === "guest" && this.guests > 0) this.guests--;
    },
    applyPeople() {
      this.closePeopleModal();
    },
    handleSearch() {
      if (!this.destination) {
        alert("목적지를 입력해주세요.");
        return;
      }
      if (!this.checkin) {
        alert("체크인 날짜를 선택해주세요.");
        return;
      }
      if (!this.checkout) {
        alert("체크아웃 날짜를 선택해주세요.");
        return;
      }
      if (this.guests < 1) {
        alert("최소 1명 이상 선택해주세요.");
        return;
      }

      // 모든 값이 입력되면 HotelListing.vue로 이동
      this.$router.push({
        name: "HotelListing",
        query: {
          destination: this.destination,
          checkin: this.checkin,
          checkout: this.checkout,
          rooms: this.rooms,
          guests: this.guests,
        },
      });
    },


  },
};
</script>
<style>
@import "@/assets/css/HotelSearch.css";
.search-container h1 {
  margin-bottom: 20px;
  font-size: 24px;
  text-align: center;
  color: #333;
}

.form-group label {
  margin-bottom: 5px;
  font-size: 14px;
  font-weight: bold;
  color: #444;
}

.form-group input {
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 6px;
  font-size: 14px;
}
</style>
