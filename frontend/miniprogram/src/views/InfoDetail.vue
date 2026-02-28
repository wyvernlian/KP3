<template>
  <div class="info-detail-container">
    <van-nav-bar title="资讯详情" left-text="返回" @click-left="goBack" />
    
    <div class="info-content">
      <h1 class="info-title">{{ info.title }}</h1>
      <div class="info-meta">
        <span class="info-category">{{ info.categoryName }}</span>
        <span class="info-time">{{ info.createTime }}</span>
      </div>
      
      <!-- 资讯内容 -->
      <div class="info-body">
        <img :src="info.coverImage" :alt="info.title" class="info-image" />
        <div class="info-text">{{ info.content }}</div>
        <!-- 视频 -->
        <div class="info-video" v-if="info.videoUrl">
          <video :src="info.videoUrl" controls></video>
        </div>
      </div>
      
      <!-- 互动信息 -->
      <div class="info-interaction">
        <div class="interaction-item">
          <van-icon name="eye-o" />
          <span>{{ info.viewCount }}</span>
        </div>
        <div class="interaction-item" @click="likeInfo">
          <van-icon :name="liked ? 'like' : 'like-o'" :color="liked ? '#ff4d4f' : ''" />
          <span>{{ info.likeCount }}</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRouter, useRoute } from 'vue-router'
import { showToast } from 'vant'

const router = useRouter()
const route = useRoute()

const info = ref({
  id: 1,
  title: '人工智能的发展历程',
  categoryId: 1,
  categoryName: '科技新闻',
  content: '人工智能（Artificial Intelligence，简称AI）是研究、开发用于模拟、延伸和扩展人的智能的理论、方法、技术及应用系统的一门新的技术科学。\n\n人工智能的发展历程可以分为以下几个阶段：\n\n1. 孕育期（1950-1956）：这一时期主要是人工智能的理论准备阶段，图灵提出了著名的“图灵测试”，为人工智能的发展奠定了理论基础。\n\n2. 诞生期（1956-1970）：1956年，达特茅斯会议的召开标志着人工智能的正式诞生。在这一时期，人工智能的研究取得了重大突破，如逻辑理论机、通用问题求解器等。\n\n3. 低谷期（1970-1980）：由于人工智能的发展遇到了瓶颈，如计算能力不足、知识表示困难等，导致人工智能的研究进入了低谷期。\n\n4. 复兴期（1980-1990）：专家系统的出现使得人工智能的研究重新受到重视，这一时期人工智能在医疗、金融等领域得到了广泛应用。\n\n5. 繁荣期（1990至今）：随着计算机技术的飞速发展，特别是深度学习的出现，人工智能的研究取得了突破性进展，如AlphaGo战胜人类围棋冠军、自动驾驶技术的发展等。\n\n未来，人工智能将继续朝着更加智能化、人性化的方向发展，为人类社会的进步做出更大的贡献。',
  coverImage: 'https://example.com/info1.jpg',
  videoUrl: 'https://example.com/video1.mp4',
  viewCount: 1000,
  likeCount: 100,
  createTime: '2026-02-27'
})

const liked = ref(false)

const likeInfo = () => {
  if (liked.value) {
    showToast('已经点赞过了')
    return
  }
  liked.value = true
  info.value.likeCount++
  // 这里应该调用API记录点赞
  console.log('点赞资讯', info.value.id)
}

const goBack = () => {
  router.back()
}

onMounted(() => {
  const infoId = route.query.id
  // 这里应该调用API根据ID获取资讯详情
  console.log('获取资讯详情', infoId)
  // 这里应该调用API记录浏览
  console.log('记录浏览', infoId)
})
</script>

<style scoped>
.info-detail-container {
  min-height: 100vh;
  background-color: #f5f5f5;
}

.info-content {
  background-color: white;
  padding: 15px;
}

.info-title {
  font-size: 18px;
  font-weight: bold;
  color: #333;
  margin-bottom: 15px;
  line-height: 1.4;
}

.info-meta {
  display: flex;
  gap: 10px;
  font-size: 12px;
  color: #999;
  margin-bottom: 20px;
}

.info-body {
  margin-bottom: 20px;
}

.info-image {
  width: 100%;
  height: 200px;
  object-fit: cover;
  border-radius: 8px;
  margin-bottom: 15px;
}

.info-text {
  font-size: 14px;
  line-height: 1.6;
  color: #333;
  white-space: pre-line;
}

.info-video {
  margin-top: 20px;
}

.info-video video {
  width: 100%;
  height: 200px;
  object-fit: cover;
  border-radius: 8px;
}

.info-interaction {
  display: flex;
  justify-content: space-around;
  padding: 15px 0;
  border-top: 1px solid #f0f0f0;
}

.interaction-item {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 5px;
  font-size: 12px;
  color: #666;
}

.interaction-item .van-icon {
  font-size: 20px;
}
</style>