<template>
  <transition-group name="list-fade" tag="div">
    <FavoriteItem
      v-for="(item, idx) in list" :key="item.fno"
      :info="item"
      @unlike="onUnlikeClick"
    />
  </transition-group>
</template>

<script>
import FavoriteItem from './FavoriteItem.vue';

export default {
  components: {FavoriteItem},
  props: {
    list: {
      type: Array,
      default: []
    },
  },
  methods: {
    //favoriteItem에서 unlike를 받아서 favorite page에 전달
    onUnlikeClick(fno){
      this.$emit('unlike', fno);
    },
  },
};
</script>

<style scoped scss>

.list-fade-leave-active{
  transition: all 0.5s ease;
  position: absolute;
  width: 100%;
}
.list-fade-leave-to{
  opacity: 0;
}
.list-fade-move{
  transition: all 0.5s ease;
}
</style>