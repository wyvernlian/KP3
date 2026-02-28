<template>
  <div class="organization-container">
    <el-card>
      <template #header>
        <div class="card-header">
          <span>运营机构管理</span>
          <el-button type="primary" @click="addOrganization">添加运营机构</el-button>
        </div>
      </template>
      <el-tabs v-model="activeTab">
        <el-tab-pane label="运营机构管理" name="organization">
          <el-table :data="organizations" style="width: 100%">
            <el-table-column prop="id" label="ID" width="80"></el-table-column>
            <el-table-column prop="orgName" label="机构名称"></el-table-column>
            <el-table-column prop="address" label="地址"></el-table-column>
            <el-table-column prop="contactName" label="联系人"></el-table-column>
            <el-table-column prop="contactPhone" label="联系电话"></el-table-column>
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
                <el-button type="primary" size="small" @click="editOrganization(scope.row)">编辑</el-button>
                <el-button type="danger" size="small" @click="deleteOrganization(scope.row.id)">删除</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>
        <el-tab-pane label="运营机构用户管理" name="orgUser">
          <el-table :data="orgUsers" style="width: 100%">
            <el-table-column prop="id" label="ID" width="80"></el-table-column>
            <el-table-column prop="username" label="用户名"></el-table-column>
            <el-table-column prop="name" label="姓名"></el-table-column>
            <el-table-column prop="orgName" label="所属机构"></el-table-column>
            <el-table-column prop="phone" label="手机号"></el-table-column>
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
                <el-button type="primary" size="small" @click="editOrgUser(scope.row)">编辑</el-button>
                <el-button type="danger" size="small" @click="deleteOrgUser(scope.row.id)">删除</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>
        <el-tab-pane label="预约用户管理" name="bookingUser">
          <el-table :data="bookingUsers" style="width: 100%">
            <el-table-column prop="id" label="ID" width="80"></el-table-column>
            <el-table-column prop="name" label="姓名"></el-table-column>
            <el-table-column prop="idCard" label="身份证号"></el-table-column>
            <el-table-column prop="phone" label="手机号"></el-table-column>
            <el-table-column prop="createTime" label="创建时间"></el-table-column>
            <el-table-column label="操作" width="100">
              <template #default="scope">
                <el-button type="primary" size="small" @click="viewBookingUser(scope.row)">查看</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>
        <el-tab-pane label="预约团队管理" name="bookingTeam">
          <el-table :data="bookingTeams" style="width: 100%">
            <el-table-column prop="id" label="ID" width="80"></el-table-column>
            <el-table-column prop="teamName" label="团队名称"></el-table-column>
            <el-table-column prop="teamType" label="团队类型"></el-table-column>
            <el-table-column prop="contactName" label="联系人"></el-table-column>
            <el-table-column prop="contactPhone" label="联系电话"></el-table-column>
            <el-table-column prop="createTime" label="创建时间"></el-table-column>
            <el-table-column label="操作" width="100">
              <template #default="scope">
                <el-button type="primary" size="small" @click="viewBookingTeam(scope.row)">查看</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>
      </el-tabs>
    </el-card>
    
    <!-- 添加/编辑运营机构对话框 -->
    <el-dialog
      :title="dialogTitle"
      v-model="dialogVisible"
      width="500px"
    >
      <el-form :model="form" :rules="rules" ref="formRef" label-width="80px">
        <el-form-item label="机构名称" prop="orgName">
          <el-input v-model="form.orgName" placeholder="请输入机构名称"></el-input>
        </el-form-item>
        <el-form-item label="地址" prop="address">
          <el-input v-model="form.address" placeholder="请输入地址"></el-input>
        </el-form-item>
        <el-form-item label="联系人" prop="contactName">
          <el-input v-model="form.contactName" placeholder="请输入联系人"></el-input>
        </el-form-item>
        <el-form-item label="联系电话" prop="contactPhone">
          <el-input v-model="form.contactPhone" placeholder="请输入联系电话"></el-input>
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
          <el-button type="primary" @click="saveOrganization">确定</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'

const activeTab = ref('organization')
const organizations = ref([
  {
    id: 1,
    orgName: '科普科技馆',
    address: '北京市海淀区中关村南大街5号',
    contactName: '张三',
    contactPhone: '13800138000',
    status: 1,
    createTime: '2026-02-27 10:00:00'
  }
])

const orgUsers = ref([
  {
    id: 1,
    username: 'orguser1',
    name: '李四',
    orgName: '科普科技馆',
    phone: '13900139000',
    status: 1,
    createTime: '2026-02-27 10:00:00'
  }
])

const bookingUsers = ref([
  {
    id: 1,
    name: '王五',
    idCard: '110101199001011234',
    phone: '13700137000',
    createTime: '2026-02-27 10:00:00'
  }
])

const bookingTeams = ref([
  {
    id: 1,
    teamName: 'XX小学',
    teamType: '学校',
    contactName: '李老师',
    contactPhone: '13600136000',
    createTime: '2026-02-27 10:00:00'
  }
])

const dialogVisible = ref(false)
const dialogTitle = ref('添加运营机构')
const form = ref({})
const formRef = ref(null)

const rules = {
  orgName: [{ required: true, message: '请输入机构名称', trigger: 'blur' }],
  address: [{ required: true, message: '请输入地址', trigger: 'blur' }],
  contactName: [{ required: true, message: '请输入联系人', trigger: 'blur' }],
  contactPhone: [{ required: true, message: '请输入联系电话', trigger: 'blur' }],
  status: [{ required: true, message: '请选择状态', trigger: 'blur' }]
}

const addOrganization = () => {
  form.value = {
    status: 1
  }
  dialogTitle.value = '添加运营机构'
  dialogVisible.value = true
}

const editOrganization = (organization) => {
  form.value = { ...organization }
  dialogTitle.value = '编辑运营机构'
  dialogVisible.value = true
}

const saveOrganization = async () => {
  if (!formRef.value) return
  
  await formRef.value.validate(async (valid) => {
    if (valid) {
      dialogVisible.value = false
      // 这里应该调用API保存运营机构
      console.log('保存运营机构', form.value)
    }
  })
}

const deleteOrganization = (id) => {
  // 这里应该调用API删除运营机构
  console.log('删除运营机构', id)
}

const editOrgUser = (user) => {
  // 编辑运营机构用户
  console.log('编辑运营机构用户', user)
}

const deleteOrgUser = (id) => {
  // 删除运营机构用户
  console.log('删除运营机构用户', id)
}

const viewBookingUser = (user) => {
  // 查看预约用户
  console.log('查看预约用户', user)
}

const viewBookingTeam = (team) => {
  // 查看预约团队
  console.log('查看预约团队', team)
}

onMounted(() => {
  // 这里应该调用API获取运营机构列表
})
</script>

<style scoped>
.organization-container {
  padding: 20px;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
</style>