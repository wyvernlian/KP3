<template>
  <div class="booking-container">
    <el-card>
      <template #header>
        <div class="card-header">
          <span>预约管理</span>
        </div>
      </template>
      <el-tabs v-model="activeTab">
        <el-tab-pane label="个人预约" name="personal">
          <el-table :data="personalBookings" style="width: 100%">
            <el-table-column prop="id" label="ID" width="80"></el-table-column>
            <el-table-column prop="activityName" label="活动名称"></el-table-column>
            <el-table-column prop="bookingDate" label="预约日期"></el-table-column>
            <el-table-column prop="bookingTime" label="预约时间"></el-table-column>
            <el-table-column prop="numberOfPeople" label="预约人数"></el-table-column>
            <el-table-column prop="name" label="姓名"></el-table-column>
            <el-table-column prop="phone" label="手机号"></el-table-column>
            <el-table-column prop="status" label="状态">
              <template #default="scope">
                <el-tag :type="getStatusType(scope.row.status)">
                  {{ getStatusText(scope.row.status) }}
                </el-tag>
              </template>
            </el-table-column>
            <el-table-column prop="createTime" label="创建时间"></el-table-column>
            <el-table-column label="操作" width="150">
              <template #default="scope">
                <el-button type="primary" size="small" @click="verifyBooking(scope.row.id)">核销</el-button>
                <el-button type="danger" size="small" @click="cancelBooking(scope.row.id)">取消</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>
        <el-tab-pane label="团队预约" name="team">
          <el-table :data="teamBookings" style="width: 100%">
            <el-table-column prop="id" label="ID" width="80"></el-table-column>
            <el-table-column prop="activityName" label="活动名称"></el-table-column>
            <el-table-column prop="bookingDate" label="预约日期"></el-table-column>
            <el-table-column prop="bookingTime" label="预约时间"></el-table-column>
            <el-table-column prop="teamName" label="团队名称"></el-table-column>
            <el-table-column prop="teamType" label="团队类型"></el-table-column>
            <el-table-column prop="numberOfPeople" label="预约人数"></el-table-column>
            <el-table-column prop="contactName" label="联系人"></el-table-column>
            <el-table-column prop="contactPhone" label="联系电话"></el-table-column>
            <el-table-column prop="status" label="状态">
              <template #default="scope">
                <el-tag :type="getTeamStatusType(scope.row.status)">
                  {{ getTeamStatusText(scope.row.status) }}
                </el-tag>
              </template>
            </el-table-column>
            <el-table-column prop="createTime" label="创建时间"></el-table-column>
            <el-table-column label="操作" width="200">
              <template #default="scope">
                <el-button type="primary" size="small" @click="approveTeamBooking(scope.row.id)">通过</el-button>
                <el-button type="warning" size="small" @click="rejectTeamBooking(scope.row.id)">拒绝</el-button>
                <el-button type="danger" size="small" @click="cancelTeamBooking(scope.row.id)">取消</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>
        <el-tab-pane label="核销和到访登记" name="verification">
          <div class="verification-header">
            <el-button type="primary" @click="addVisitRecord">添加到访登记</el-button>
          </div>
          <el-table :data="verificationRecords" style="width: 100%">
            <el-table-column prop="id" label="ID" width="80"></el-table-column>
            <el-table-column prop="bookingId" label="预约ID"></el-table-column>
            <el-table-column prop="type" label="类型">
              <template #default="scope">
                <el-tag :type="scope.row.type === 'verification' ? 'success' : 'info'">
                  {{ scope.row.type === 'verification' ? '核销' : '到访登记' }}
                </el-tag>
              </template>
            </el-table-column>
            <el-table-column prop="name" label="姓名"></el-table-column>
            <el-table-column prop="phone" label="手机号"></el-table-column>
            <el-table-column prop="verifyTime" label="处理时间"></el-table-column>
            <el-table-column prop="operator" label="操作人"></el-table-column>
          </el-table>
        </el-tab-pane>
      </el-tabs>
    </el-card>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'

const activeTab = ref('personal')
const personalBookings = ref([
  {
    id: 1,
    activityName: '参观科技馆',
    bookingDate: '2026-03-01',
    bookingTime: '09:00-11:00',
    numberOfPeople: 2,
    name: '张三',
    phone: '13800138000',
    status: 0,
    createTime: '2026-02-27 10:00:00'
  }
])

const teamBookings = ref([
  {
    id: 1,
    activityName: '参观科技馆',
    bookingDate: '2026-03-02',
    bookingTime: '14:00-16:00',
    teamName: 'XX小学',
    teamType: '学校',
    numberOfPeople: 50,
    contactName: '李老师',
    contactPhone: '13900139000',
    status: 0,
    createTime: '2026-02-27 10:00:00'
  }
])

const verificationRecords = ref([
  {
    id: 1,
    bookingId: 1,
    type: 'verification',
    name: '张三',
    phone: '13800138000',
    verifyTime: '2026-03-01 09:30:00',
    operator: '管理员'
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

const verifyBooking = (id) => {
  // 核销预约
  console.log('核销预约', id)
}

const cancelBooking = (id) => {
  // 取消预约
  console.log('取消预约', id)
}

const approveTeamBooking = (id) => {
  // 批准团队预约
  console.log('批准团队预约', id)
}

const rejectTeamBooking = (id) => {
  // 拒绝团队预约
  console.log('拒绝团队预约', id)
}

const cancelTeamBooking = (id) => {
  // 取消团队预约
  console.log('取消团队预约', id)
}

const addVisitRecord = () => {
  // 添加到访登记
  console.log('添加到访登记')
}

onMounted(() => {
  // 这里应该调用API获取预约列表
})
</script>

<style scoped>
.booking-container {
  padding: 20px;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.verification-header {
  margin-bottom: 20px;
}
</style>