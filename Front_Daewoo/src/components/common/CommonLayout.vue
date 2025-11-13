<template>
  <Header></Header>

  <div class="template-container">

    <!--✅ 넣고 싶은 컨텐츠를 외부에서 넣는다 -->
    <slot></slot>

  </div>

  <Footer></Footer>
</template>

<script>
import Footer from './Footer.vue';
import Header from './Header.vue';

export default {
  components: {Footer, Header}
}
</script>

<style scoped lang="scss">
.template-container {
  max-width: 1232px;
  margin: 0 auto;
}
</style>