<template>
  <!-- footer -->
  <footer>
    <section class="subscription-box">
      <div class="subscription-content">
        <div style="font-size: 44px; font-weight: bold; color: #112211">
          구독서비스<br />
          신청해보세요
        </div>
        <p style="text-align: left; font-weight: bold; margin-top: 15px; font-size: 20px; color: #112211">The Travel</p>
        <p style="text-align: left; margin-top: 5px; font-size: 16px; color: #112211">구독하고 무료, 최신 여행 정보를 받아보세요</p>
        <div class="subscription-form-container">
          <input type="email" placeholder="Your email address" />
          <button type="submit">Subscribe</button>
        </div>
      </div>
      <div class="subscription-image">
        <img
          src="@/assets/img/letter-box.png"
          height="330"
          width="350"
          alt=""
        />
      </div>
    </section>

    <!-- footer-bottom -->
    <div class="footer-bottom">
      <div class="footer-letter">
        <span style="display: flex; gap: 10px">
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
</template>

<script>

</script>

<style lang="scss" scoped>
@use "@/assets/css/Footer.scss";

</style>
