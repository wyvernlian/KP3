<template>
  <div class="venue-detail-container">
    <van-nav-bar title="场馆详情" left-text="返回" @click-left="goBack" />
    
    <div class="venue-content">
      <!-- 场馆图片 -->
      <div class="venue-images">
        <img :src="venue.imageUrl" :alt="venue.venueName" class="venue-image" />
      </div>
      
      <!-- 场馆信息 -->
      <div class="venue-info">
        <h2 class="venue-name">{{ venue.venueName }}</h2>
        <p class="venue-address">{{ venue.address }}</p>
        <div class="venue-description">{{ venue.description }}</div>
      </div>
      
      <!-- 3D展示 -->
      <div class="venue-3d">
        <h3>3D展示</h3>
        <div class="3d-container">
          <iframe :src="venue.threeDUrl" class="3d-iframe" frameborder="0"></iframe>
        </div>
        <p class="3d-tip">点击进入3D场馆，体验沉浸式参观</p>
      </div>
      
      <!-- 预约按钮 -->
      <div style="margin: 20px;">
        <van-button type="primary" block @click="navigateTo('/personal-booking')">立即预约</van-button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRouter, useRoute } from 'vue-router'

const router = useRouter()
const route = useRoute()

const venue = ref({
  id: 1,
  venueName: '科普科技馆',
  address: '北京市海淀区中关村南大街5号',
  description: '科普科技馆是一座现代化的科学技术博物馆，旨在通过互动展览、科学实验和教育活动，向公众普及科学知识，激发科学兴趣。馆内设有多个主题展厅，包括物理、化学、生物、天文等多个领域的展览，适合各个年龄段的观众参观。',
  imageUrl: 'https://example.com/venue1.jpg',
  threeDUrl: 'https://example.com/3d/venue1'
})

const navigateTo = (url) => {
  router.push(url)
}

const goBack = () => {
  router.back()
}

onMounted(() => {
  const venueId = route.query.id
  // 这里应该调用API根据ID获取场馆详情
  console.log('获取场馆详情', venueId)
})
</script>

<style scoped>
.venue-detail-container {
  min-height: 100vh;
  background-color: #f5f5f5;
}

.venue-content {
  background-color: white;
  padding-bottom: 20px;
}

.venue-images {
  width: 100%;
  height: 200px;
  overflow: hidden;
}

.venue-image {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.venue-info {
  padding: 15px;
}

.venue-name {
  font-size: 18px;
  font-weight: bold;
  margin-bottom: 10px;
  color: #333;
}

.venue-address {
  font-size: 14px;
  color: #666;
  margin-bottom: 15px;
}

.venue-description {
  font-size: 14px;
  line-height: 1.5;
  color: #333;
}

.venue-3d {
  padding: 15px;
  margin-top: 10px;
  background-color: white;
}

.venue-3d h3 {
  font-size: 16px;
  font-weight: bold;
  margin-bottom: 10px;
  color: #333;
}

.3d-container {
  width: 100%;
  height: 300px;
  border: 1px solid #f0f0f0;
  border-radius: 8px;
  overflow: hidden;
  margin-bottom: 10px;
}

.3d-iframe {
  width: 100%;
  height: 100%;
}

.3d-tip {
  font-size: 12px;
  color: #999;
  text-align: center;
}
</style>