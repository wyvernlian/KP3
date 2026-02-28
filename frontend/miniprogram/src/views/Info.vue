<template>
  <div class="info-container">
    <van-nav-bar title="科普资讯" left-text="返回" @click-left="goBack" />
    
    <!-- 资讯分类 -->
    <div class="info-categories">
      <div 
        class="category-item" 
        v-for="(category, index) in categories" 
        :key="index"
        :class="{ active: activeCategory === category.id }"
        @click="selectCategory(category.id)"
      >
        {{ category.categoryName }}
      </div>
    </div>
    
    <!-- 资讯列表 -->
    <div class="info-list">
      <div class="info-item" v-for="(info, index) in infos" :key="index" @click="navigateTo(`/info-detail?id=${info.id}`)">
        <img :src="info.coverImage" :alt="info.title" class="info-image" />
        <div class="info-info">
          <h3 class="info-title">{{ info.title }}</h3>
          <div class="info-meta">
            <span class="info-category">{{ info.categoryName }}</span>
            <span class="info-view">{{ info.viewCount }}浏览</span>
            <span class="info-time">{{ formatDate(info.createTime) }}</span>
          </div>
        </div>
      </div>
    </div>
    
    <!-- 加载更多 -->
    <div class="load-more">
      <van-loading type="spinner" color="#409eff" />
      <span>加载中...</span>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()

const activeCategory = ref(0)
const categories = ref([
  { id: 0, categoryName: '全部' },
  { id: 1, categoryName: '科技新闻' },
  { id: 2, categoryName: '科普知识' }
])

const infos = ref([
  {
    id: 1,
    title: '人工智能的发展历程',
    categoryId: 1,
    categoryName: '科技新闻',
    coverImage: 'https://example.com/info1.jpg',
    viewCount: 1000,
    createTime: '2026-02-27'
  },
  {
    id: 2,
    title: '黑洞的奥秘',
    categoryId: 2,
    categoryName: '科普知识',
    coverImage: 'https://example.com/info2.jpg',
    viewCount: 800,
    createTime: '2026-02-26'
  },
  {
    id: 3,
    title: '量子计算的未来',
    categoryId: 1,
    categoryName: '科技新闻',
    coverImage: 'https://example.com/info3.jpg',
    viewCount: 1200,
    createTime: '2026-02-25'
  }
])

const selectCategory = (categoryId) => {
  activeCategory.value = categoryId
  // 这里应该调用API根据分类获取资讯列表
  console.log('选择分类', categoryId)
}

const navigateTo = (url) => {
  router.push(url)
}

const goBack = () => {
  router.back()
}

const formatDate = (date) => {
  return date
}

onMounted(() => {
  // 这里应该调用API获取资讯分类和资讯列表
})
</script>

<style scoped>
.info-container {
  min-height: 100vh;
  background-color: #f5f5f5;
}

.info-categories {
  display: flex;
  background-color: white;
  padding: 10px 0;
  overflow-x: auto;
  margin-bottom: 10px;
}

.category-item {
  padding: 0 15px;
  height: 32px;
  line-height: 32px;
  font-size: 14px;
  color: #666;
  white-space: nowrap;
}

.category-item.active {
  color: #409eff;
  border-bottom: 2px solid #409eff;
}

.info-list {
  padding: 15px;
}

.info-item {
  display: flex;
  gap: 10px;
  background-color: white;
  padding: 15px;
  border-radius: 8px;
  margin-bottom: 15px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.info-image {
  width: 100px;
  height: 70px;
  object-fit: cover;
  border-radius: 4px;
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
  margin-bottom: 8px;
}

.info-meta {
  display: flex;
  gap: 10px;
  font-size: 12px;
  color: #999;
}

.load-more {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 20px 0;
  color: #999;
  font-size: 14px;
}
</style>