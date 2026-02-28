<template>
  <div class="activity-booking-container">
    <van-nav-bar title="活动预约" left-text="返回" @click-left="goBack" />
    
    <div class="activity-detail">
      <img :src="activity.image" :alt="activity.title" class="activity-image" />
      <div class="activity-info">
        <h2 class="activity-title">{{ activity.title }}</h2>
        <div class="activity-meta">
          <span class="activity-category">{{ activity.category }}</span>
          <span class="activity-time">{{ activity.time }}</span>
        </div>
        <div class="activity-description">{{ activity.description }}</div>
        <div class="activity-notice">
          <h3>预约须知</h3>
          <p>{{ activity.notice }}</p>
        </div>
      </div>
    </div>
    
    <div class="booking-form">
      <!-- 日期选择 -->
      <van-cell-group>
        <van-field
          v-model="form.bookingDate"
          name="bookingDate"
          label="预约日期"
          placeholder="请选择日期"
          readonly
          @click="showDatePicker = true"
        />
        <van-popup v-model:show="showDatePicker" position="bottom">
          <van-datetime-picker
            type="date"
            :min-date="minDate"
            :max-date="maxDate"
            @confirm="onDateConfirm"
            @cancel="showDatePicker = false"
          />
        </van-popup>
      </van-cell-group>
      
      <!-- 时间段选择 -->
      <van-cell-group>
        <van-field
          v-model="form.bookingTime"
          name="bookingTime"
          label="预约时间"
          placeholder="请选择时间段"
          readonly
          @click="showTimePicker = true"
        />
        <van-popup v-model:show="showTimePicker" position="bottom">
          <van-picker
            :columns="timeSlots"
            @confirm="onTimeConfirm"
            @cancel="showTimePicker = false"
          />
        </van-popup>
      </van-cell-group>
      
      <!-- 预约人数 -->
      <van-cell-group>
        <van-field
          v-model="form.numberOfPeople"
          name="numberOfPeople"
          label="预约人数"
          placeholder="请输入预约人数"
          type="number"
          :max="maxPeople"
        />
        <div class="tip">最多可预约{{ maxPeople }}人</div>
      </van-cell-group>
      
      <!-- 预约人员信息 -->
      <van-cell-group>
        <van-cell title="预约人员信息" />
        <van-field
          v-model="form.name"
          name="name"
          label="姓名"
          placeholder="请输入姓名"
        />
        <van-field
          v-model="form.idCard"
          name="idCard"
          label="身份证号"
          placeholder="请输入身份证号"
        />
        <van-field
          v-model="form.phone"
          name="phone"
          label="手机号"
          placeholder="请输入手机号"
        />
      </van-cell-group>
      
      <!-- 附加信息 -->
      <van-cell-group v-if="hasAdditionalInfo">
        <van-cell title="附加信息" />
        <van-field
          v-model="form.additionalInfo"
          name="additionalInfo"
          label="备注"
          placeholder="请输入备注信息"
          type="textarea"
        />
      </van-cell-group>
      
      <!-- 提交按钮 -->
      <div style="margin: 20px;">
        <van-button type="primary" block @click="submitBooking">提交预约</van-button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { showToast } from 'vant'

const router = useRouter()

// 活动详情
const activity = reactive({
  id: 1,
  title: '科普实验体验',
  category: '科普活动',
  time: '2026-03-01 10:00',
  image: 'https://example.com/activity1.jpg',
  description: '参与有趣的科普实验，动手实践科学原理，了解科学知识的奥秘。本活动适合8-16岁的青少年参加，需要家长陪同。',
  notice: '1. 请提前15分钟到达活动现场；2. 请穿着舒适的衣服和鞋子；3. 服从工作人员的安排；4. 注意安全，不要随意触摸实验器材。'
})

// 表单数据
const form = reactive({
  bookingDate: '',
  bookingTime: '',
  numberOfPeople: 1,
  name: '',
  idCard: '',
  phone: '',
  additionalInfo: ''
})

// 弹窗控制
const showDatePicker = ref(false)
const showTimePicker = ref(false)

// 时间段
const timeSlots = ref([
  { text: '10:00-11:30 (剩余名额: 5)', value: '10:00-11:30' },
  { text: '14:00-15:30 (剩余名额: 8)', value: '14:00-15:30' }
])

// 日期范围
const minDate = new Date()
const maxDate = new Date()
maxDate.setMonth(maxDate.getMonth() + 1)

// 最大预约人数
const maxPeople = ref(3)

// 是否有附加信息
const hasAdditionalInfo = ref(true)

// 日期选择确认
const onDateConfirm = (date) => {
  form.bookingDate = date.toISOString().split('T')[0]
  showDatePicker.value = false
}

// 时间选择确认
const onTimeConfirm = (value) => {
  form.bookingTime = value
  showTimePicker.value = false
}

// 提交预约
const submitBooking = () => {
  // 验证表单
  if (!form.bookingDate) {
    showToast('请选择预约日期')
    return
  }
  if (!form.bookingTime) {
    showToast('请选择预约时间')
    return
  }
  if (!form.numberOfPeople) {
    showToast('请输入预约人数')
    return
  }
  if (!form.name) {
    showToast('请输入姓名')
    return
  }
  if (!form.idCard) {
    showToast('请输入身份证号')
    return
  }
  if (!form.phone) {
    showToast('请输入手机号')
    return
  }
  
  // 这里应该调用API提交预约
  console.log('提交活动预约', form)
  showToast('预约成功')
  router.push('/my')
}

// 返回
const goBack = () => {
  router.back()
}

onMounted(() => {
  // 这里应该调用API获取活动详情
})
</script>

<style scoped>
.activity-booking-container {
  min-height: 100vh;
  background-color: #f5f5f5;
}

.activity-detail {
  background-color: white;
  margin-bottom: 10px;
}

.activity-image {
  width: 100%;
  height: 200px;
  object-fit: cover;
}

.activity-info {
  padding: 15px;
}

.activity-title {
  font-size: 18px;
  font-weight: bold;
  margin-bottom: 10px;
  color: #333;
}

.activity-meta {
  display: flex;
  gap: 10px;
  margin-bottom: 15px;
}

.activity-category {
  background-color: #f0f0f0;
  padding: 2px 8px;
  border-radius: 4px;
  font-size: 12px;
  color: #666;
}

.activity-time {
  font-size: 12px;
  color: #999;
}

.activity-description {
  font-size: 14px;
  line-height: 1.5;
  color: #333;
  margin-bottom: 15px;
}

.activity-notice {
  background-color: #f9f9f9;
  padding: 10px;
  border-radius: 8px;
}

.activity-notice h3 {
  font-size: 14px;
  font-weight: bold;
  margin-bottom: 5px;
  color: #333;
}

.activity-notice p {
  font-size: 12px;
  line-height: 1.4;
  color: #666;
  margin: 0;
}

.booking-form {
  background-color: white;
  padding-bottom: 20px;
}

.tip {
  font-size: 12px;
  color: #999;
  margin-left: 15px;
  margin-top: 5px;
}
</style>