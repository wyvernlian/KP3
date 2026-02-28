<template>
  <div class="home-container">
    <!-- 轮播图 -->
    <van-swipe :autoplay="3000" indicator-color="#fff">
      <van-swipe-item v-for="(item, index) in swipes" :key="index">
        <img :src="item.image" :alt="item.title" class="swipe-image" />
      </van-swipe-item>
    </van-swipe>
    
    <!-- 目录 -->
    <div class="menu-section">
      <div class="menu-grid">
        <div class="menu-item" @click="navigateTo('/personal-booking')">
          <div class="menu-icon">
            <van-icon name="user" size="24" />
          </div>
          <div class="menu-text">个人预约</div>
        </div>
        <div class="menu-item" @click="navigateTo('/team-booking')">
          <div class="menu-icon">
            <van-icon name="group" size="24" />
          </div>
          <div class="menu-text">团队预约</div>
        </div>
        <div class="menu-item" @click="navigateTo('/venues')">
          <div class="menu-icon">
            <van-icon name="location" size="24" />
          </div>
          <div class="menu-text">线上场馆</div>
        </div>
        <div class="menu-item" @click="navigateTo('/info')">
          <div class="menu-icon">
            <van-icon name="document" size="24" />
          </div>
          <div class="menu-text">科普资讯</div>
        </div>
      </div>
    </div>
    
    <!-- 科普研学 -->
    <div class="section">
      <div class="section-header">
        <h2>科普研学</h2>
        <a @click="navigateTo('/activity-booking')" class="more">更多</a>
      </div>
      <div class="activity-list">
        <div class="activity-item" v-for="(item, index) in activities" :key="index" @click="navigateTo('/activity-booking')">
          <img :src="item.image" :alt="item.title" class="activity-image" />
          <div class="activity-info">
            <h3 class="activity-title">{{ item.title }}</h3>
            <p class="activity-desc">{{ item.description }}</p>
            <div class="activity-time">{{ item.time }}</div>
          </div>
        </div>
      </div>
    </div>
    
    <!-- 科普资讯 -->
    <div class="section">
      <div class="section-header">
        <h2>科普资讯</h2>
        <a @click="navigateTo('/info')" class="more">更多</a>
      </div>
      <div class="info-list">
        <div class="info-item" v-for="(item, index) in infos" :key="index" @click="navigateTo(`/info-detail?id=${item.id}`)">
          <img :src="item.image" :alt="item.title" class="info-image" />
          <div class="info-info">
            <h3 class="info-title">{{ item.title }}</h3>
            <div class="info-meta">
              <span class="info-view">{{ item.viewCount }}浏览</span>
              <span class="info-time">{{ item.time }}</span>
            </div>
          </div>
        </div>
      </div>
    </div>
    
    <!-- 底部导航 -->
    <van-tabbar v-model="active" route>
      <van-tabbar-item icon="home-o" to="/home">首页</van-tabbar-item>
      <van-tabbar-item icon="calendar-o" to="/personal-booking">预约</van-tabbar-item>
      <van-tabbar-item icon="document-o" to="/info">资讯</van-tabbar-item>
      <van-tabbar-item icon="user-o" to="/my">我的</van-tabbar-item>
    </van-tabbar>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()
const active = ref(0)

const swipes = ref([
  { image: 'https://example.com/swipe1.jpg', title: '科普科技馆' },
  { image: 'https://example.com/swipe2.jpg', title: '科普活动' },
  { image: 'https://example.com/swipe3.jpg', title: '科普研学' }
])

const activities = ref([
  {
    title: '科普实验体验',
    description: '参与有趣的科普实验，动手实践科学原理',
    time: '2026-03-01 10:00',
    image: 'https://example.com/activity1.jpg'
  },
  {
    title: '科技馆参观',
    description: '参观科技馆的常设展览，了解科学知识',
    time: '2026-03-02 09:00',
    image: 'https://example.com/activity2.jpg'
  }
])

const infos = ref([
  {
    id: 1,
    title: '人工智能的发展历程',
    viewCount: 1000,
    time: '2026-02-27',
    image: 'https://example.com/info1.jpg'
  },
  {
    id: 2,
    title: '黑洞的奥秘',
    viewCount: 800,
    time: '2026-02-26',
    image: 'https://example.com/info2.jpg'
  },
  {
    id: 3,
    title: '量子计算的未来',
    viewCount: 1200,
    time: '2026-02-25',
    image: 'https://example.com/info3.jpg'
  }
])

const navigateTo = (url) => {
  router.push(url)
}
</script>

<style scoped>
.home-container {
  min-height: 100vh;
  background-color: #f5f5f5;
}

.swipe-image {
  width: 100%;
  height: 200px;
  object-fit: cover;
}

.menu-section {
  background-color: white;
  padding: 20px 0;
  margin-bottom: 10px;
}

.menu-grid {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  gap: 10px;
  padding: 0 10px;
}

.menu-item {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 10px;
}

.menu-icon {
  width: 50px;
  height: 50px;
  background-color: #f0f0f0;
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  margin-bottom: 10px;
}

.menu-text {
  font-size: 12px;
  color: #333;
}

.section {
  background-color: white;
  padding: 15px;
  margin-bottom: 10px;
}

.section-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 15px;
}

.section-header h2 {
  font-size: 16px;
  font-weight: bold;
  color: #333;
}

.more {
  font-size: 12px;
  color: #999;
}

.activity-list {
  display: flex;
  gap: 10px;
  overflow-x: auto;
  padding-bottom: 10px;
}

.activity-item {
  width: 200px;
  flex-shrink: 0;
}

.activity-image {
  width: 100%;
  height: 120px;
  object-fit: cover;
  border-radius: 8px;
  margin-bottom: 10px;
}

.activity-title {
  font-size: 14px;
  font-weight: bold;
  margin-bottom: 5px;
  color: #333;
}

.activity-desc {
  font-size: 12px;
  color: #666;
  margin-bottom: 5px;
  line-height: 1.4;
}

.activity-time {
  font-size: 11px;
  color: #999;
}

.info-list {
  display: flex;
  flex-direction: column;
  gap: 15px;
}

.info-item {
  display: flex;
  gap: 10px;
}

.info-image {
  width: 80px;
  height: 80px;
  object-fit: cover;
  border-radius: 8px;
  flex-shrink: 0;
}

.info-info {
  flex: 1;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}

.info-title {
  font-size: 14px;
  font-weight: bold;
  color: #333;
  line-height: 1.4;
}

.info-meta {
  display: flex;
  justify-content: space-between;
  font-size: 11px;
  color: #999;
  margin-top: 5px;
}
</style>