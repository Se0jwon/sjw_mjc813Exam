<template>
  <div class="map-section">
    <h2 class="section-title">위치</h2>
    <div class="map-container">
      <iframe
          width="100%"
          height="450"
          style="border:0"
          loading="lazy"
          allowfullscreen
          referrerpolicy="no-referrer-when-downgrade"
          :src="mapSrc"
      ></iframe>
      <div class="hotel-address">
        <i class="fa-solid fa-location-dot"></i> {{address}}
      </div>
      <a :href="googleMapsLink" target="_blank" class="view-on-google-maps">구글 맵에서 보기</a>
    </div>
  </div>
</template>

<script>
  export default {
  name: 'MapSection',
  props: {
  apiKey: {
  type: String,
  required: true
},
  address: {
  type: String,
  required: true
}
},
  computed: {
  encodedAddress() {
  return encodeURIComponent(this.address);
},
  mapSrc() {
  // 수정된 Google Maps Embed API URL입니다.
  // `q` 파라미터에 주소를 전달합니다.
  return `https://www.google.com/maps/embed/v1/place?q=${this.encodedAddress}&key=${this.apiKey}`;
},
  googleMapsLink() {
  // 수정된 Google Maps 공식 링크입니다.
  return `https://www.google.com/maps/search/?api=1&query=${this.encodedAddress}`;
}
}
};
</script>

<style scoped>
.map-section {
  margin-bottom: 20px;
}
.map-container {
  position: relative;
  border-radius: 10px;
  overflow: hidden;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}
.hotel-address {
  background-color: white;
  padding: 10px;
  border-bottom-left-radius: 10px;
  border-bottom-right-radius: 10px;
  font-size: 14px;
  color: #555;
  display: flex;
  align-items: center;
  gap: 8px;
}
.view-on-google-maps {
  position: absolute;
  top: 15px;
  right: 15px;
  background-color: white;
  color: #007bff;
  padding: 8px 12px;
  border-radius: 5px;
  text-decoration: none;
  font-size: 14px;
  font-weight: bold;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.2);
}
</style>