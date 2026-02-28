<template>
  <div class="personal-booking-container">
    <van-nav-bar title="个人预约" left-text="返回" @click-left="goBack" />
    
    <div class="booking-content">
      <!-- 活动选择 -->
      <van-cell-group>
        <van-field
          v-model="form.activityId"
          name="activityId"
          label="活动"
          placeholder="请选择活动"
          readonly
          @click="showActivityPicker = true"
        />
        <van-popup v-model:show="showActivityPicker" position="bottom">
          <van-picker
            :columns="activities"
            @confirm="onActivityConfirm"
            @cancel="showActivityPicker = false"
          />
        </van-popup>
      </van-cell-group>
      
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
      
      <!-- 亲友信息 -->
      <van-cell-group>
        <van-cell title="亲友信息" @click="showFriends = true" />
        <div class="friends-list" v-if="selectedFriends.length > 0">
          <div class="friend-item" v-for="(friend, index) in selectedFriends" :key="index">
            <span>{{ friend.name }} - {{ friend.idCard }}</span>
            <van-icon name="close" @click="removeFriend(index)" />
          </div>
        </div>
      </van-cell-group>
      
      <!-- 提交按钮 -->
      <div style="margin: 20px;">
        <van-button type="primary" block @click="submitBooking">提交预约</van-button>
      </div>
    </div>
    
    <!-- 亲友选择弹窗 -->
    <van-popup v-model:show="showFriends" position="right" :style="{ width: '80%' }">
      <div class="friends-popup">
        <div class="popup-header">
          <h3>选择亲友</h3>
          <van-icon name="close" @click="showFriends = false" />
        </div>
        <div class="friends-list-popup">
          <div class="friend-item-popup" v-for="(friend, index) in friends" :key="index">
            <div class="friend-info">
              <div class="friend-name">{{ friend.name }}</div>
              <div class="friend-idcard">{{ friend.idCard }}</div>
            </div>
            <van-checkbox v-model="friend.checked" />
          </div>
        </div>
        <div class="popup-footer">
          <van-button type="primary" block @click="confirmFriends">确定</van-button>
        </div>
      </div>
    </van-popup>
  </div>
</template>

<script setup>
import { ref, reactive, computed, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { showToast } from 'vant'

const router = useRouter()

// 表单数据
const form = reactive({
  activityId: '',
  activityName: '',
  bookingDate: '',
  bookingTime: '',
  numberOfPeople: 1,
  name: '',
  idCard: '',
  phone: '',
  additionalInfo: ''
})

// 弹窗控制
const showActivityPicker = ref(false)
const showDatePicker = ref(false)
const showTimePicker = ref(false)
const showFriends = ref(false)

// 活动列表
const activities = ref([
  { text: '参观科技馆', value: '1' },
  { text: '科普实验', value: '2' }
])

// 时间段
const timeSlots = ref([
  { text: '09:00-11:00', value: '09:00-11:00' },
  { text: '14:00-16:00', value: '14:00-16:00' }
])

// 日期范围
const minDate = new Date()
const maxDate = new Date()
maxDate.setMonth(maxDate.getMonth() + 1)

// 最大预约人数
const maxPeople = ref(5)

// 是否有附加信息
const hasAdditionalInfo = ref(true)

// 亲友列表
const friends = ref([
  { id: 1, name: '张三', idCard: '110101199001011234', checked: false },
  { id: 2, name: '李四', idCard: '110101199001011235', checked: false }
])

const selectedFriends = ref([])

// 活动选择确认
const onActivityConfirm = (value) => {
  form.activityId = value
  form.activityName = activities.value.find(item => item.value === value).text
  showActivityPicker.value = false
}

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

// 确认亲友选择
const confirmFriends = () => {
  selectedFriends.value = friends.value.filter(friend => friend.checked)
  showFriends.value = false
}

// 移除亲友
const removeFriend = (index) => {
  selectedFriends.value.splice(index, 1)
  // 同时更新亲友列表的选中状态
  const friendId = selectedFriends.value[index]?.id
  if (friendId) {
    const friend = friends.value.find(f => f.id === friendId)
    if (friend) {
      friend.checked = false
    }
  }
}

// 提交预约
const submitBooking = () => {
  // 验证表单
  if (!form.activityId) {
    showToast('请选择活动')
    return
  }
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
  console.log('提交预约', form, selectedFriends.value)
  showToast('预约成功')
  router.push('/my')
}

// 返回
const goBack = () => {
  router.back()
}

onMounted(() => {
  // 这里应该调用API获取活动列表、时间段等数据
})
</script>

<style scoped>
.personal-booking-container {
  min-height: 100vh;
  background-color: #f5f5f5;
}

.booking-content {
  padding-bottom: 20px;
}

.tip {
  font-size: 12px;
  color: #999;
  margin-left: 15px;
  margin-top: 5px;
}

.friends-list {
  padding: 0 15px;
}

.friend-item {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 10px 0;
  border-bottom: 1px solid #f0f0f0;
}

.friends-popup {
  height: 100%;
  display: flex;
  flex-direction: column;
}

.popup-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 15px;
  border-bottom: 1px solid #f0f0f0;
}

.popup-header h3 {
  margin: 0;
  font-size: 16px;
  font-weight: bold;
}

.friends-list-popup {
  flex: 1;
  overflow-y: auto;
  padding: 15px;
}

.friend-item-popup {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 15px 0;
  border-bottom: 1px solid #f0f0f0;
}

.friend-info {
  flex: 1;
}

.friend-name {
  font-size: 14px;
  font-weight: bold;
  margin-bottom: 5px;
}

.friend-idcard {
  font-size: 12px;
  color: #666;
}

.popup-footer {
  padding: 15px;
  border-top: 1px solid #f0f0f0;
}
</style>