<template>
  <div class="team-booking-container">
    <van-nav-bar title="团队预约" left-text="返回" @click-left="goBack" />
    
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
      
      <!-- 团队类型 -->
      <van-cell-group>
        <van-field
          v-model="form.teamType"
          name="teamType"
          label="团队类型"
          placeholder="请选择团队类型"
          readonly
          @click="showTeamTypePicker = true"
        />
        <van-popup v-model:show="showTeamTypePicker" position="bottom">
          <van-picker
            :columns="teamTypes"
            @confirm="onTeamTypeConfirm"
            @cancel="showTeamTypePicker = false"
          />
        </van-popup>
      </van-cell-group>
      
      <!-- 团队名称 -->
      <van-cell-group>
        <van-field
          v-model="form.teamName"
          name="teamName"
          label="团队名称"
          placeholder="请输入团队名称"
        />
      </van-cell-group>
      
      <!-- 预约人数 -->
      <van-cell-group>
        <van-field
          v-model="form.numberOfPeople"
          name="numberOfPeople"
          label="预约人数"
          placeholder="请输入预约人数"
          type="number"
        />
      </van-cell-group>
      
      <!-- 联系人信息 -->
      <van-cell-group>
        <van-field
          v-model="form.contactName"
          name="contactName"
          label="联系人"
          placeholder="请输入联系人姓名"
        />
        <van-field
          v-model="form.contactPhone"
          name="contactPhone"
          label="联系电话"
          placeholder="请输入联系电话"
        />
      </van-cell-group>
      
      <!-- 需求备注 -->
      <van-cell-group>
        <van-field
          v-model="form.remarks"
          name="remarks"
          label="需求备注"
          placeholder="请输入需求备注"
          type="textarea"
        />
      </van-cell-group>
      
      <!-- 附件上传 -->
      <van-cell-group>
        <van-cell title="附件上传" @click="showUploader = true" />
        <van-uploader
          v-model="form.attachment"
          :max-count="1"
          accept="file"
          v-if="showUploader"
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

// 表单数据
const form = reactive({
  activityId: '',
  activityName: '',
  bookingDate: '',
  bookingTime: '',
  teamType: '',
  teamName: '',
  numberOfPeople: '',
  contactName: '',
  contactPhone: '',
  remarks: '',
  attachment: []
})

// 弹窗控制
const showActivityPicker = ref(false)
const showDatePicker = ref(false)
const showTimePicker = ref(false)
const showTeamTypePicker = ref(false)
const showUploader = ref(false)

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

// 团队类型
const teamTypes = ref([
  { text: '学校', value: '学校' },
  { text: '企业', value: '企业' },
  { text: '社团', value: '社团' },
  { text: '其他', value: '其他' }
])

// 日期范围
const minDate = new Date()
const maxDate = new Date()
maxDate.setMonth(maxDate.getMonth() + 1)

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

// 团队类型选择确认
const onTeamTypeConfirm = (value) => {
  form.teamType = value
  showTeamTypePicker.value = false
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
  if (!form.teamType) {
    showToast('请选择团队类型')
    return
  }
  if (!form.teamName) {
    showToast('请输入团队名称')
    return
  }
  if (!form.numberOfPeople) {
    showToast('请输入预约人数')
    return
  }
  if (!form.contactName) {
    showToast('请输入联系人姓名')
    return
  }
  if (!form.contactPhone) {
    showToast('请输入联系电话')
    return
  }
  
  // 这里应该调用API提交预约
  console.log('提交团队预约', form)
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
.team-booking-container {
  min-height: 100vh;
  background-color: #f5f5f5;
}

.booking-content {
  padding-bottom: 20px;
}
</style>