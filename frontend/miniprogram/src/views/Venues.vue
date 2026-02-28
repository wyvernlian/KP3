<template>
  <div class="venues-container">
    <van-nav-bar title="线上场馆" left-text="返回" @click-left="goBack" />
    
    <div class="venues-list">
      <div class="venue-item" v-for="(venue, index) in venues" :key="index" @click="navigateTo(`/venue-detail?id=${venue.id}`)">
        <img :src="venue.imageUrl" :alt="venue.venueName" class="venue-image" />
        <div class="venue-info">
          <h3 class="venue-name">{{ venue.venueName }}</h3>
          <p class="venue-address">{{ venue.address }}</p>
          <p class="venue-description">{{ venue.description }}</p>
          <van-button type="primary" size="small" class="view-button">查看详情</van-button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()

const venues = ref([
  {
    id: 1,
    venueName: '科普科技馆',
    address: '北京市海淀区中关村南大街5号',
    description: '一座现代化的科普科技馆，展示各种科学知识和科技成果',
    imageUrl: 'https://example.com/venue1.jpg',
    threeDUrl: 'https://example.com/3d/venue1'
  }
])

const navigateTo = (url) => {
  router.push(url)
}

const goBack = () => {
  router.back()
}

onMounted(() => {
  // 这里应该调用API获取场馆列表
  // 如果只有一个场馆，直接跳转到场馆详情页面
  if (venues.value.length === 1) {
    router.push(`/venue-detail?id=${venues.value[0].id}`)
  }
})
</script>

<style scoped>
.venues-container {
  min-height: 100vh;
  background-color: #f5f5f5;
}

.venues-list {
  padding: 15px;
}

.venue-item {
  background-color: white;
  border-radius: 8px;
  overflow: hidden;
  margin-bottom: 15px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.venue-image {
  width: 100%;
  height: 180px;
  object-fit: cover;
}

.venue-info {
  padding: 15px;
}

.venue-name {
  font-size: 16px;
  font-weight: bold;
  margin-bottom: 8px;
  color: #333;
}

.venue-address {
  font-size: 14px;
  color: #666;
  margin-bottom: 8px;
}

.venue-description {
  font-size: 14px;
  line-height: 1.4;
  color: #333;
  margin-bottom: 12px;
}

.view-button {
  width: 100%;
}
</style>