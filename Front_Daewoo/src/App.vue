<template>
  <router-view />
</template>

<script>
export default {
  name: "App",
  mounted() {
	// fetch('/api/user/all');
  }
}
</script>

<style>
#app {
  font-family: "Noto Sans", sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}

html, body, #app {
  padding: 0;
  margin: 0;
  width: 100%;
}
* {
  padding: 0;
  margin: 0;
  box-sizing: border-box;
}
button {
  border: none;
  background-color: transparent;

}
ul, li {
  list-style: none;
  margin: 0;
  padding: 0;
}
</style>
