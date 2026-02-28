<template>
  <div class="my-container">
    <van-nav-bar title="个人中心" />
    
    <!-- 个人信息 -->
    <div class="user-info" @click="navigateTo('/profile')">
      <div class="user-avatar">
        <img src="https://example.com/avatar.jpg" alt="头像" />
      </div>
      <div class="user-details">
        <h3 class="user-name">{{ user.name }}</h3>
        <p class="user-phone">{{ user.phone }}</p>
      </div>
      <van-icon name="arrow" />
    </div>
    
    <!-- 功能列表 -->
    <div class="function-list">
      <van-cell-group>
        <van-cell title="个人预约历史" @click="navigateTo('/booking-history?type=personal')">
          <template #right>
            <van-icon name="arrow" />
          </template>
        </van-cell>
        <van-cell title="团队预约历史" @click="navigateTo('/booking-history?type=team')">
          <template #right>
            <van-icon name="arrow" />
          </template>
        </van-cell>
        <van-cell title="亲友信息" @click="navigateTo('/friends')">
          <template #right>
            <van-icon name="arrow" />
          </template>
        </van-cell>
      </van-cell-group>
      
      <van-cell-group style="margin-top: 10px;">
        <van-cell title="设置" @click="showSetting">
          <template #right>
            <van-icon name="arrow" />
          </template>
        </van-cell>
        <van-cell title="关于我们" @click="showAbout">
          <template #right>
            <van-icon name="arrow" />
          </template>
        </van-cell>
      </van-cell-group>
    </div>
    
    <!-- 退出登录 -->
    <div style="margin: 20px;">
      <van-button type="default" block @click="logout">退出登录</van-button>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { showToast, showDialog } from 'vant'

const router = useRouter()

const user = ref({
  name: '张三',
  phone: '138****8000'
})

const navigateTo = (url) => {
  router.push(url)
}

const showSetting = () => {
  // 显示设置页面
  console.log('显示设置')
}

const showAbout = () => {
  // 显示关于我们页面
  console.log('显示关于我们')
}

const logout = () => {
  showDialog({
    title: '退出登录',
    message: '确定要退出登录吗？',
    confirmButtonText: '确定',
    cancelButtonText: '取消'
  }).then(() => {
    // 这里应该调用API退出登录
    console.log('退出登录')
    router.push('/login')
  })
}

onMounted(() => {
  // 这里应该调用API获取用户信息
})
</script>

<style scoped>
.my-container {
  min-height: 100vh;
  background-color: #f5f5f5;
}

.user-info {
  display: flex;
  align-items: center;
  background-color: white;
  padding: 20px;
  margin-bottom: 10px;
}

.user-avatar {
  width: 60px;
  height: 60px;
  border-radius: 50%;
  overflow: hidden;
  margin-right: 15px;
}

.user-avatar img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.user-details {
  flex: 1;
}

.user-name {
  font-size: 16px;
  font-weight: bold;
  color: #333;
  margin-bottom: 5px;
}

.user-phone {
  font-size: 14px;
  color: #666;
  margin: 0;
}

.function-list {
  margin-bottom: 20px;
}
</style>