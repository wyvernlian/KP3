<template>
  <div class="booking-history-container">
    <van-nav-bar title="预约历史" left-text="返回" @click-left="goBack" />
    
    <!-- 标签切换 -->
    <van-tabs v-model="activeTab">
      <van-tab title="个人预约">
        <div class="booking-list">
          <div class="booking-item" v-for="(booking, index) in personalBookings" :key="index">
            <div class="booking-header">
              <h3 class="booking-title">{{ booking.activityName }}</h3>
              <van-tag :type="getStatusType(booking.status)">{{ getStatusText(booking.status) }}</van-tag>
            </div>
            <div class="booking-info">
              <p><van-icon name="calendar" /> {{ booking.bookingDate }} {{ booking.bookingTime }}</p>
              <p><van-icon name="user" /> {{ booking.name }} ({{ booking.numberOfPeople }}人)</p>
              <p><van-icon name="phone" /> {{ booking.phone }}</p>
            </div>
            <div class="booking-actions" v-if="booking.status === 0">
              <van-button type="default" size="small" @click="cancelBooking(booking.id)">取消预约</van-button>
            </div>
          </div>
        </div>
      </van-tab>
      <van-tab title="团队预约">
        <div class="booking-list">
          <div class="booking-item" v-for="(booking, index) in teamBookings" :key="index">
            <div class="booking-header">
              <h3 class="booking-title">{{ booking.activityName }}</h3>
              <van-tag :type="getTeamStatusType(booking.status)">{{ getTeamStatusText(booking.status) }}</van-tag>
            </div>
            <div class="booking-info">
              <p><van-icon name="calendar" /> {{ booking.bookingDate }} {{ booking.bookingTime }}</p>
              <p><van-icon name="group" /> {{ booking.teamName }} ({{ booking.numberOfPeople }}人)</p>
              <p><van-icon name="user" /> {{ booking.contactName }} {{ booking.contactPhone }}</p>
            </div>
            <div class="booking-actions" v-if="booking.status === 0">
              <van-button type="default" size="small" @click="cancelTeamBooking(booking.id)">取消预约</van-button>
            </div>
          </div>
        </div>
      </van-tab>
    </van-tabs>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRouter, useRoute } from 'vue-router'
import { showToast, showDialog } from 'vant'

const router = useRouter()
const route = useRoute()

const activeTab = ref(0)

const personalBookings = ref([
  {
    id: 1,
    activityName: '参观科技馆',
    bookingDate: '2026-03-01',
    bookingTime: '09:00-11:00',
    numberOfPeople: 2,
    name: '张三',
    phone: '13800138000',
    status: 0
  }
])

const teamBookings = ref([
  {
    id: 1,
    activityName: '参观科技馆',
    bookingDate: '2026-03-02',
    bookingTime: '14:00-16:00',
    teamName: 'XX小学',
    numberOfPeople: 50,
    contactName: '李老师',
    contactPhone: '13900139000',
    status: 0
  }
])

const getStatusType = (status) => {
  switch (status) {
    case 0: return 'warning'
    case 1: return 'success'
    case 2: return 'danger'
    default: return 'info'
  }
}

const getStatusText = (status) => {
  switch (status) {
    case 0: return '待核销'
    case 1: return '已核销'
    case 2: return '已取消'
    default: return '未知'
  }
}

const getTeamStatusType = (status) => {
  switch (status) {
    case 0: return 'warning'
    case 1: return 'success'
    case 2: return 'danger'
    case 3: return 'info'
    default: return 'info'
  }
}

const getTeamStatusText = (status) => {
  switch (status) {
    case 0: return '待审核'
    case 1: return '已通过'
    case 2: return '已拒绝'
    case 3: return '已取消'
    default: return '未知'
  }
}

const cancelBooking = (id) => {
  showDialog({
    title: '取消预约',
    message: '确定要取消预约吗？',
    confirmButtonText: '确定',
    cancelButtonText: '取消'
  }).then(() => {
    // 这里应该调用API取消预约
    console.log('取消个人预约', id)
    showToast('取消成功')
  })
}

const cancelTeamBooking = (id) => {
  showDialog({
    title: '取消预约',
    message: '确定要取消预约吗？',
    confirmButtonText: '确定',
    cancelButtonText: '取消'
  }).then(() => {
    // 这里应该调用API取消预约
    console.log('取消团队预约', id)
    showToast('取消成功')
  })
}

const goBack = () => {
  router.back()
}

onMounted(() => {
  const type = route.query.type
  if (type === 'team') {
    activeTab.value = 1
  }
  // 这里应该调用API获取预约历史
})
</script>

<style scoped>
.booking-history-container {
  min-height: 100vh;
  background-color: #f5f5f5;
}

.booking-list {
  padding: 15px;
}

.booking-item {
  background-color: white;
  border-radius: 8px;
  padding: 15px;
  margin-bottom: 15px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.booking-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 10px;
}

.booking-title {
  font-size: 16px;
  font-weight: bold;
  color: #333;
  margin: 0;
}

.booking-info {
  margin-bottom: 10px;
}

.booking-info p {
  font-size: 14px;
  color: #666;
  margin: 5px 0;
  display: flex;
  align-items: center;
  gap: 5px;
}

.booking-actions {
  display: flex;
  justify-content: flex-end;
  margin-top: 10px;
  padding-top: 10px;
  border-top: 1px solid #f0f0f0;
}
</style>