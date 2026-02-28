<template>
  <div id="app">
    <router-view v-if="isLoggedIn" />
    <login v-else />
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useStore } from 'pinia'
import Login from './views/Login.vue'

const store = useStore()
const isLoggedIn = ref(false)

onMounted(() => {
  const token = localStorage.getItem('token')
  if (token) {
    isLoggedIn.value = true
  }
})
</script>

<style>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

body {
  font-family: 'Helvetica Neue', Helvetica, 'PingFang SC', 'Hiragino Sans GB', 'Microsoft YaHei', Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  background-color: #f5f7fa;
}

#app {
  height: 100vh;
}
</style>