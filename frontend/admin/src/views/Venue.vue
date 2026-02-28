<template>
  <div class="venue-container">
    <el-card>
      <template #header>
        <div class="card-header">
          <span>场馆和活动管理</span>
        </div>
      </template>
      <el-tabs v-model="activeTab">
        <el-tab-pane label="场馆管理" name="venue">
          <div class="tab-header">
            <el-button type="primary" @click="addVenue">添加场馆</el-button>
          </div>
          <el-table :data="venues" style="width: 100%">
            <el-table-column prop="id" label="ID" width="80"></el-table-column>
            <el-table-column prop="venueName" label="场馆名称"></el-table-column>
            <el-table-column prop="address" label="地址"></el-table-column>
            <el-table-column prop="status" label="状态">
              <template #default="scope">
                <el-tag :type="scope.row.status === 1 ? 'success' : 'danger'">
                  {{ scope.row.status === 1 ? '启用' : '禁用' }}
                </el-tag>
              </template>
            </el-table-column>
            <el-table-column prop="createTime" label="创建时间"></el-table-column>
            <el-table-column label="操作" width="150">
              <template #default="scope">
                <el-button type="primary" size="small" @click="editVenue(scope.row)">编辑</el-button>
                <el-button type="danger" size="small" @click="deleteVenue(scope.row.id)">删除</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>
        <el-tab-pane label="活动分类" name="category">
          <div class="tab-header">
            <el-button type="primary" @click="addCategory">添加活动分类</el-button>
          </div>
          <el-table :data="categories" style="width: 100%">
            <el-table-column prop="id" label="ID" width="80"></el-table-column>
            <el-table-column prop="categoryName" label="分类名称"></el-table-column>
            <el-table-column prop="categoryCode" label="分类编码"></el-table-column>
            <el-table-column prop="sort" label="排序"></el-table-column>
            <el-table-column prop="showInHome" label="显示在首页">
              <template #default="scope">
                <el-tag :type="scope.row.showInHome === 1 ? 'success' : 'info'">
                  {{ scope.row.showInHome === 1 ? '是' : '否' }}
                </el-tag>
              </template>
            </el-table-column>
            <el-table-column prop="status" label="状态">
              <template #default="scope">
                <el-tag :type="scope.row.status === 1 ? 'success' : 'danger'">
                  {{ scope.row.status === 1 ? '启用' : '禁用' }}
                </el-tag>
              </template>
            </el-table-column>
            <el-table-column prop="createTime" label="创建时间"></el-table-column>
            <el-table-column label="操作" width="150">
              <template #default="scope">
                <el-button type="primary" size="small" @click="editCategory(scope.row)">编辑</el-button>
                <el-button type="danger" size="small" @click="deleteCategory(scope.row.id)">删除</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>
        <el-tab-pane label="活动管理" name="activity">
          <div class="tab-header">
            <el-button type="primary" @click="addActivity">添加活动</el-button>
          </div>
          <el-table :data="activities" style="width: 100%">
            <el-table-column prop="id" label="ID" width="80"></el-table-column>
            <el-table-column prop="activityName" label="活动名称"></el-table-column>
            <el-table-column prop="categoryName" label="分类"></el-table-column>
            <el-table-column prop="type" label="活动类型"></el-table-column>
            <el-table-column prop="timeType" label="预约时间类型"></el-table-column>
            <el-table-column prop="startDate" label="开始日期"></el-table-column>
            <el-table-column prop="endDate" label="结束日期"></el-table-column>
            <el-table-column prop="status" label="状态">
              <template #default="scope">
                <el-tag :type="scope.row.status === 1 ? 'success' : 'danger'">
                  {{ scope.row.status === 1 ? '启用' : '禁用' }}
                </el-tag>
              </template>
            </el-table-column>
            <el-table-column prop="createTime" label="创建时间"></el-table-column>
            <el-table-column label="操作" width="150">
              <template #default="scope">
                <el-button type="primary" size="small" @click="editActivity(scope.row)">编辑</el-button>
                <el-button type="danger" size="small" @click="deleteActivity(scope.row.id)">删除</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>
      </el-tabs>
    </el-card>
    
    <!-- 添加/编辑场馆对话框 -->
    <el-dialog
      :title="dialogTitle"
      v-model="dialogVisible"
      width="500px"
    >
      <el-form :model="form" :rules="rules" ref="formRef" label-width="80px">
        <el-form-item label="场馆名称" prop="venueName">
          <el-input v-model="form.venueName" placeholder="请输入场馆名称"></el-input>
        </el-form-item>
        <el-form-item label="地址" prop="address">
          <el-input v-model="form.address" placeholder="请输入地址"></el-input>
        </el-form-item>
        <el-form-item label="描述" prop="description">
          <el-input v-model="form.description" type="textarea" placeholder="请输入描述"></el-input>
        </el-form-item>
        <el-form-item label="图片URL" prop="imageUrl">
          <el-input v-model="form.imageUrl" placeholder="请输入图片URL"></el-input>
        </el-form-item>
        <el-form-item label="3D展示URL" prop="threeDUrl">
          <el-input v-model="form.threeDUrl" placeholder="请输入3D展示URL"></el-input>
        </el-form-item>
        <el-form-item label="状态" prop="status">
          <el-select v-model="form.status" placeholder="请选择状态">
            <el-option label="启用" value="1"></el-option>
            <el-option label="禁用" value="0"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogVisible = false">取消</el-button>
          <el-button type="primary" @click="saveVenue">确定</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'

const activeTab = ref('venue')
const venues = ref([
  {
    id: 1,
    venueName: '科普科技馆',
    address: '北京市海淀区中关村南大街5号',
    description: '一座现代化的科普科技馆，展示各种科学知识和科技成果',
    imageUrl: 'https://example.com/venue1.jpg',
    threeDUrl: 'https://example.com/3d/venue1',
    status: 1,
    createTime: '2026-02-27 10:00:00'
  }
])

const categories = ref([
  {
    id: 1,
    categoryName: '参观预约',
    categoryCode: 'visit',
    sort: 1,
    showInHome: 1,
    status: 1,
    createTime: '2026-02-27 10:00:00'
  },
  {
    id: 2,
    categoryName: '科普活动',
    categoryCode: 'science',
    sort: 2,
    showInHome: 1,
    status: 1,
    createTime: '2026-02-27 10:00:00'
  },
  {
    id: 3,
    categoryName: '科普研学',
    categoryCode: 'research',
    sort: 3,
    showInHome: 1,
    status: 1,
    createTime: '2026-02-27 10:00:00'
  }
])

const activities = ref([
  {
    id: 1,
    activityName: '参观科技馆',
    categoryId: 1,
    categoryName: '参观预约',
    type: '参观预约',
    timeType: '选日期',
    startDate: '2026-03-01',
    endDate: '2026-03-31',
    status: 1,
    createTime: '2026-02-27 10:00:00'
  },
  {
    id: 2,
    activityName: '科普实验',
    categoryId: 2,
    categoryName: '科普活动',
    type: '科普活动',
    timeType: '按排期',
    startDate: '2026-03-01',
    endDate: '2026-03-31',
    status: 1,
    createTime: '2026-02-27 10:00:00'
  }
])

const dialogVisible = ref(false)
const dialogTitle = ref('添加场馆')
const form = ref({})
const formRef = ref(null)

const rules = {
  venueName: [{ required: true, message: '请输入场馆名称', trigger: 'blur' }],
  address: [{ required: true, message: '请输入地址', trigger: 'blur' }],
  status: [{ required: true, message: '请选择状态', trigger: 'blur' }]
}

const addVenue = () => {
  form.value = {
    status: 1
  }
  dialogTitle.value = '添加场馆'
  dialogVisible.value = true
}

const editVenue = (venue) => {
  form.value = { ...venue }
  dialogTitle.value = '编辑场馆'
  dialogVisible.value = true
}

const saveVenue = async () => {
  if (!formRef.value) return
  
  await formRef.value.validate(async (valid) => {
    if (valid) {
      dialogVisible.value = false
      // 这里应该调用API保存场馆
      console.log('保存场馆', form.value)
    }
  })
}

const deleteVenue = (id) => {
  // 这里应该调用API删除场馆
  console.log('删除场馆', id)
}

const addCategory = () => {
  // 添加活动分类
  console.log('添加活动分类')
}

const editCategory = (category) => {
  // 编辑活动分类
  console.log('编辑活动分类', category)
}

const deleteCategory = (id) => {
  // 删除活动分类
  console.log('删除活动分类', id)
}

const addActivity = () => {
  // 添加活动
  console.log('添加活动')
}

const editActivity = (activity) => {
  // 编辑活动
  console.log('编辑活动', activity)
}

const deleteActivity = (id) => {
  // 删除活动
  console.log('删除活动', id)
}

onMounted(() => {
  // 这里应该调用API获取场馆列表
})
</script>

<style scoped>
.venue-container {
  padding: 20px;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.tab-header {
  margin-bottom: 20px;
}
</style>