<template>
  <div class="analysis-container">
    <el-card>
      <template #header>
        <div class="card-header">
          <span>数据分析</span>
        </div>
      </template>
      <el-tabs v-model="activeTab">
        <el-tab-pane label="流量统计" name="traffic">
          <el-card>
            <template #header>
              <div class="chart-header">
                <span>流量趋势</span>
                <el-date-picker
                  v-model="dateRange"
                  type="daterange"
                  range-separator="至"
                  start-placeholder="开始日期"
                  end-placeholder="结束日期"
                  @change="getTrafficData"
                ></el-date-picker>
              </div>
            </template>
            <div id="trafficChart" style="width: 100%; height: 400px;"></div>
          </el-card>
        </el-tab-pane>
        <el-tab-pane label="内容访问流量统计" name="content">
          <el-card>
            <template #header>
              <div class="chart-header">
                <span>内容访问排行</span>
              </div>
            </template>
            <el-table :data="contentData" style="width: 100%">
              <el-table-column prop="id" label="ID" width="80"></el-table-column>
              <el-table-column prop="title" label="标题"></el-table-column>
              <el-table-column prop="viewCount" label="浏览量"></el-table-column>
              <el-table-column prop="likeCount" label="点赞数"></el-table-column>
            </el-table>
            <div id="contentChart" style="width: 100%; height: 400px; margin-top: 20px;"></div>
          </el-card>
        </el-tab-pane>
        <el-tab-pane label="场馆数据看板" name="venue">
          <div class="venue-stats">
            <el-card class="stat-card">
              <div class="stat-item">
                <div class="stat-value">{{ venueStats.totalBookings }}</div>
                <div class="stat-label">总预约数</div>
              </div>
            </el-card>
            <el-card class="stat-card">
              <div class="stat-item">
                <div class="stat-value">{{ venueStats.todayBookings }}</div>
                <div class="stat-label">今日预约数</div>
              </div>
            </el-card>
            <el-card class="stat-card">
              <div class="stat-item">
                <div class="stat-value">{{ venueStats.totalVisits }}</div>
                <div class="stat-label">总访问量</div>
              </div>
            </el-card>
            <el-card class="stat-card">
              <div class="stat-item">
                <div class="stat-value">{{ venueStats.todayVisits }}</div>
                <div class="stat-label">今日访问量</div>
              </div>
            </el-card>
            <el-card class="stat-card">
              <div class="stat-item">
                <div class="stat-value">{{ venueStats.utilizationRate }}</div>
                <div class="stat-label">场馆利用率</div>
              </div>
            </el-card>
          </div>
          <el-card style="margin-top: 20px;">
            <template #header>
              <div class="chart-header">
                <span>热门活动</span>
              </div>
            </template>
            <div id="hotActivitiesChart" style="width: 100%; height: 400px;"></div>
          </el-card>
        </el-tab-pane>
      </el-tabs>
    </el-card>
  </div>
</template>

<script setup>
import { ref, onMounted, watch } from 'vue'
import * as echarts from 'echarts'

const activeTab = ref('traffic')
const dateRange = ref([])
const trafficChart = ref(null)
const contentChart = ref(null)
const hotActivitiesChart = ref(null)

const contentData = ref([
  {
    id: 1,
    title: '人工智能的发展历程',
    viewCount: 1000,
    likeCount: 100
  },
  {
    id: 2,
    title: '黑洞的奥秘',
    viewCount: 800,
    likeCount: 80
  },
  {
    id: 3,
    title: '量子计算的未来',
    viewCount: 1200,
    likeCount: 120
  }
])

const venueStats = ref({
  totalBookings: 1200,
  todayBookings: 50,
  totalVisits: 10000,
  todayVisits: 300,
  utilizationRate: '85%',
  hotActivities: [
    { id: 1, name: '参观科技馆', bookingCount: 500 },
    { id: 2, name: '科普实验', bookingCount: 300 }
  ]
})

const getTrafficData = () => {
  // 这里应该调用API获取流量数据
  console.log('获取流量数据', dateRange.value)
  initTrafficChart()
}

const initTrafficChart = () => {
  const chartDom = document.getElementById('trafficChart')
  if (!chartDom) return
  
  trafficChart.value = echarts.init(chartDom)
  const option = {
    tooltip: {
      trigger: 'axis'
    },
    legend: {
      data: ['PV', 'UV']
    },
    grid: {
      left: '3%',
      right: '4%',
      bottom: '3%',
      containLabel: true
    },
    xAxis: {
      type: 'category',
      boundaryGap: false,
      data: ['2026-02-21', '2026-02-22', '2026-02-23', '2026-02-24', '2026-02-25', '2026-02-26', '2026-02-27']
    },
    yAxis: {
      type: 'value'
    },
    series: [
      {
        name: 'PV',
        type: 'line',
        data: [1200, 1500, 1800, 1600, 2000, 2200, 1900]
      },
      {
        name: 'UV',
        type: 'line',
        data: [800, 1000, 1200, 1100, 1400, 1500, 1300]
      }
    ]
  }
  trafficChart.value.setOption(option)
}

const initContentChart = () => {
  const chartDom = document.getElementById('contentChart')
  if (!chartDom) return
  
  contentChart.value = echarts.init(chartDom)
  const option = {
    tooltip: {
      trigger: 'axis',
      axisPointer: {
        type: 'shadow'
      }
    },
    legend: {
      data: ['浏览量', '点赞数']
    },
    grid: {
      left: '3%',
      right: '4%',
      bottom: '3%',
      containLabel: true
    },
    xAxis: {
      type: 'category',
      data: ['人工智能的发展历程', '黑洞的奥秘', '量子计算的未来']
    },
    yAxis: {
      type: 'value'
    },
    series: [
      {
        name: '浏览量',
        type: 'bar',
        data: [1000, 800, 1200]
      },
      {
        name: '点赞数',
        type: 'bar',
        data: [100, 80, 120]
      }
    ]
  }
  contentChart.value.setOption(option)
}

const initHotActivitiesChart = () => {
  const chartDom = document.getElementById('hotActivitiesChart')
  if (!chartDom) return
  
  hotActivitiesChart.value = echarts.init(chartDom)
  const option = {
    tooltip: {
      trigger: 'item'
    },
    legend: {
      top: '5%',
      left: 'center'
    },
    series: [
      {
        name: '预约数',
        type: 'pie',
        radius: ['40%', '70%'],
        avoidLabelOverlap: false,
        itemStyle: {
          borderRadius: 10,
          borderColor: '#fff',
          borderWidth: 2
        },
        label: {
          show: false,
          position: 'center'
        },
        emphasis: {
          label: {
            show: true,
            fontSize: '18',
            fontWeight: 'bold'
          }
        },
        labelLine: {
          show: false
        },
        data: [
          { value: 500, name: '参观科技馆' },
          { value: 300, name: '科普实验' },
          { value: 200, name: '科普研学' },
          { value: 200, name: '其他活动' }
        ]
      }
    ]
  }
  hotActivitiesChart.value.setOption(option)
}

const handleTabChange = (tab) => {
  activeTab.value = tab
  setTimeout(() => {
    if (tab === 'traffic') {
      initTrafficChart()
    } else if (tab === 'content') {
      initContentChart()
    } else if (tab === 'venue') {
      initHotActivitiesChart()
    }
  }, 100)
}

watch(activeTab, handleTabChange)

onMounted(() => {
  initTrafficChart()
  // 这里应该调用API获取数据分析数据
})
</script>

<style scoped>
.analysis-container {
  padding: 20px;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.chart-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.venue-stats {
  display: grid;
  grid-template-columns: repeat(5, 1fr);
  gap: 20px;
}

.stat-card {
  height: 120px;
}

.stat-item {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 100%;
}

.stat-value {
  font-size: 24px;
  font-weight: bold;
  color: #409eff;
  margin-bottom: 10px;
}

.stat-label {
  font-size: 14px;
  color: #606266;
}
</style>