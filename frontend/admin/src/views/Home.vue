<template>
  <div class="home-container">
    <el-container>
      <el-header height="60px" class="header">
        <div class="header-left">
          <h1 class="logo">科普科技馆预约平台</h1>
        </div>
        <div class="header-right">
          <el-dropdown>
            <span class="user-info">
              {{ user.name }} <el-icon class="el-icon--right"><arrow-down /></el-icon>
            </span>
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item>个人中心</el-dropdown-item>
                <el-dropdown-item @click="logout">退出登录</el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
        </div>
      </el-header>
      <el-container>
        <el-aside width="200px" class="aside">
          <el-menu
            :default-active="activeMenu"
            class="el-menu-vertical-demo"
            @select="handleMenuSelect"
          >
            <el-menu-item index="/home">
              <el-icon><home /></el-icon>
              <span>首页</span>
            </el-menu-item>
            <el-sub-menu index="/system">
              <template #title>
                <el-icon><setting /></el-icon>
                <span>系统管理</span>
              </template>
              <el-menu-item index="/system/user">用户管理</el-menu-item>
              <el-menu-item index="/system/role">角色管理</el-menu-item>
              <el-menu-item index="/system/menu">菜单管理</el-menu-item>
            </el-sub-menu>
            <el-menu-item index="/organization">
              <el-icon><office-building /></el-icon>
              <span>运营机构管理</span>
            </el-menu-item>
            <el-menu-item index="/venue">
              <el-icon><location /></el-icon>
              <span>场馆和活动管理</span>
            </el-menu-item>
            <el-menu-item index="/booking">
              <el-icon><ticket /></el-icon>
              <span>预约管理</span>
            </el-menu-item>
            <el-menu-item index="/info">
              <el-icon><document /></el-icon>
              <span>科普资讯管理</span>
            </el-menu-item>
            <el-menu-item index="/analysis">
              <el-icon><data-analysis /></el-icon>
              <span>数据分析</span>
            </el-menu-item>
          </el-menu>
        </el-aside>
        <el-main class="main">
          <router-view />
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import axios from 'axios'

const router = useRouter()
const activeMenu = ref('/home')
const user = ref({})

onMounted(() => {
  const userStr = localStorage.getItem('user')
  if (userStr) {
    user.value = JSON.parse(userStr)
  }
})

const handleMenuSelect = (key) => {
  activeMenu.value = key
  router.push(key)
}

const logout = async () => {
  try {
    await axios.post('/api/auth/logout')
    localStorage.removeItem('token')
    localStorage.removeItem('user')
    router.push('/login')
  } catch (error) {
    console.error('登出失败', error)
  }
}
</script>

<style scoped>
.home-container {
  height: 100vh;
}

.header {
  background-color: #409eff;
  color: white;
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0 20px;
}

.logo {
  font-size: 18px;
  font-weight: bold;
  margin: 0;
}

.user-info {
  cursor: pointer;
  color: white;
}

.aside {
  background-color: #f0f2f5;
  box-shadow: 2px 0 6px rgba(0, 21, 41, 0.35);
}

.main {
  background-color: #f5f7fa;
  padding: 20px;
}
</style>