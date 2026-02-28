<template>
  <div class="role-container">
    <el-card>
      <template #header>
        <div class="card-header">
          <span>角色管理</span>
          <el-button type="primary" @click="addRole">添加角色</el-button>
        </div>
      </template>
      <el-table :data="roles" style="width: 100%">
        <el-table-column prop="id" label="ID" width="80"></el-table-column>
        <el-table-column prop="roleName" label="角色名称"></el-table-column>
        <el-table-column prop="roleCode" label="角色编码"></el-table-column>
        <el-table-column prop="description" label="描述"></el-table-column>
        <el-table-column prop="status" label="状态">
          <template #default="scope">
            <el-tag :type="scope.row.status === 1 ? 'success' : 'danger'">
              {{ scope.row.status === 1 ? '启用' : '禁用' }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="createTime" label="创建时间"></el-table-column>
        <el-table-column label="操作" width="200">
          <template #default="scope">
            <el-button type="primary" size="small" @click="editRole(scope.row)">编辑</el-button>
            <el-button type="success" size="small" @click="setPermission(scope.row)">权限设置</el-button>
            <el-button type="danger" size="small" @click="deleteRole(scope.row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <div class="pagination" style="margin-top: 20px;">
        <el-pagination
          layout="prev, pager, next"
          :total="total"
          :page-size="10"
          @current-change="handleCurrentChange"
        ></el-pagination>
      </div>
    </el-card>
    
    <!-- 添加/编辑角色对话框 -->
    <el-dialog
      :title="dialogTitle"
      v-model="dialogVisible"
      width="500px"
    >
      <el-form :model="form" :rules="rules" ref="formRef" label-width="80px">
        <el-form-item label="角色名称" prop="roleName">
          <el-input v-model="form.roleName" placeholder="请输入角色名称"></el-input>
        </el-form-item>
        <el-form-item label="角色编码" prop="roleCode">
          <el-input v-model="form.roleCode" placeholder="请输入角色编码"></el-input>
        </el-form-item>
        <el-form-item label="描述" prop="description">
          <el-input v-model="form.description" type="textarea" placeholder="请输入描述"></el-input>
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
          <el-button type="primary" @click="saveRole">确定</el-button>
        </span>
      </template>
    </el-dialog>
    
    <!-- 权限设置对话框 -->
    <el-dialog
      title="权限设置"
      v-model="permissionDialogVisible"
      width="800px"
    >
      <el-tree
        :data="menus"
        show-checkbox
        node-key="id"
        default-expand-all
        v-model="checkedKeys"
      ></el-tree>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="permissionDialogVisible = false">取消</el-button>
          <el-button type="primary" @click="savePermission">确定</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'

const roles = ref([
  {
    id: 1,
    roleName: '管理员',
    roleCode: 'admin',
    description: '系统管理员',
    status: 1,
    createTime: '2026-02-27 10:00:00'
  },
  {
    id: 2,
    roleName: '运营人员',
    roleCode: 'operator',
    description: '运营机构人员',
    status: 1,
    createTime: '2026-02-27 10:00:00'
  }
])

const total = ref(2)
const dialogVisible = ref(false)
const permissionDialogVisible = ref(false)
const dialogTitle = ref('添加角色')
const form = ref({})
const formRef = ref(null)
const menus = ref([
  {
    id: 1,
    label: '系统管理',
    children: [
      { id: 2, label: '用户管理' },
      { id: 3, label: '角色管理' },
      { id: 4, label: '菜单管理' }
    ]
  },
  {
    id: 5,
    label: '运营机构管理',
    children: [
      { id: 6, label: '运营机构管理' },
      { id: 7, label: '运营机构用户管理' },
      { id: 8, label: '预约用户管理' },
      { id: 9, label: '预约团队管理' }
    ]
  },
  {
    id: 10,
    label: '场馆和活动管理',
    children: [
      { id: 11, label: '场馆管理' },
      { id: 12, label: '活动分类' },
      { id: 13, label: '活动管理' }
    ]
  },
  {
    id: 14,
    label: '预约管理',
    children: [
      { id: 15, label: '个人预约' },
      { id: 16, label: '团队预约' },
      { id: 17, label: '核销和到访登记' }
    ]
  },
  {
    id: 18,
    label: '科普资讯管理',
    children: [
      { id: 19, label: '资讯分类' },
      { id: 20, label: '资讯管理' }
    ]
  },
  {
    id: 21,
    label: '数据分析',
    children: [
      { id: 22, label: '流量统计' },
      { id: 23, label: '内容访问流量统计' },
      { id: 24, label: '场馆数据看板' }
    ]
  }
])
const checkedKeys = ref([])

const rules = {
  roleName: [{ required: true, message: '请输入角色名称', trigger: 'blur' }],
  roleCode: [{ required: true, message: '请输入角色编码', trigger: 'blur' }],
  status: [{ required: true, message: '请选择状态', trigger: 'blur' }]
}

const addRole = () => {
  form.value = {}
  dialogTitle.value = '添加角色'
  dialogVisible.value = true
}

const editRole = (role) => {
  form.value = { ...role }
  dialogTitle.value = '编辑角色'
  dialogVisible.value = true
}

const saveRole = async () => {
  if (!formRef.value) return
  
  await formRef.value.validate(async (valid) => {
    if (valid) {
      dialogVisible.value = false
      // 这里应该调用API保存角色
      console.log('保存角色', form.value)
    }
  })
}

const deleteRole = (id) => {
  // 这里应该调用API删除角色
  console.log('删除角色', id)
}

const setPermission = (role) => {
  // 这里应该获取角色的权限并设置checkedKeys
  console.log('设置权限', role)
  permissionDialogVisible.value = true
}

const savePermission = () => {
  // 这里应该调用API保存权限
  console.log('保存权限', checkedKeys.value)
  permissionDialogVisible.value = false
}

const handleCurrentChange = (current) => {
  // 分页逻辑
  console.log('Current page:', current)
}

onMounted(() => {
  // 这里应该调用API获取角色列表
})
</script>

<style scoped>
.role-container {
  padding: 20px;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.pagination {
  display: flex;
  justify-content: flex-end;
}
</style>