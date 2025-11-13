<template>
  <div>
    <HelloWorld/>
    <div v-for="item in list" :key="item">
      {{item.title}}
    </div>
  </div>
</template>


<script>
import HelloWorld from './components/HelloWorld.vue'
import axios from "axios";

export default {
  name: 'App',
  components: {
    HelloWorld
  },
  
  data() {
    return {
     list: [] 
    }
  },
  
  async mounted() {
    const 결과 = await axios.get('https://jsonplaceholder.typicode.com/posts');
    this.list = 결과.data;
  }
  
}
</script>



<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
