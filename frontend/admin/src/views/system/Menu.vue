<template>
  <div class="menu-container">
    <el-card>
      <template #header>
        <div class="card-header">
          <span>菜单管理</span>
          <el-button type="primary" @click="addMenu">添加菜单</el-button>
        </div>
      </template>
      <el-tree
        :data="menus"
        node-key="id"
        default-expand-all
        :expand-on-click-node="false"
      >
        <template #default="{ node, data }">
          <span class="custom-tree-node">
            <span>{{ data.menuName }}</span>
            <span class="tree-node-actions">
              <el-button type="primary" size="small" @click="editMenu(data)">编辑</el-button>
              <el-button type="success" size="small" @click="addSubMenu(data)">添加子菜单</el-button>
              <el-button type="danger" size="small" @click="deleteMenu(data.id)">删除</el-button>
            </span>
          </span>
        </template>
      </el-tree>
    </el-card>
    
    <!-- 添加/编辑菜单对话框 -->
    <el-dialog
      :title="dialogTitle"
      v-model="dialogVisible"
      width="500px"
    >
      <el-form :model="form" :rules="rules" ref="formRef" label-width="80px">
        <el-form-item label="菜单名称" prop="menuName">
          <el-input v-model="form.menuName" placeholder="请输入菜单名称"></el-input>
        </el-form-item>
        <el-form-item label="菜单编码" prop="menuCode">
          <el-input v-model="form.menuCode" placeholder="请输入菜单编码"></el-input>
        </el-form-item>
        <el-form-item label="路由地址" prop="url">
          <el-input v-model="form.url" placeholder="请输入路由地址"></el-input>
        </el-form-item>
        <el-form-item label="图标" prop="icon">
          <el-input v-model="form.icon" placeholder="请输入图标名称"></el-input>
        </el-form-item>
        <el-form-item label="父菜单" prop="parentId">
          <el-select v-model="form.parentId" placeholder="请选择父菜单">
            <el-option label="顶级菜单" value="0"></el-option>
            <el-option
              v-for="menu in menuOptions"
              :key="menu.id"
              :label="menu.menuName"
              :value="menu.id"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="排序" prop="sort">
          <el-input v-model.number="form.sort" placeholder="请输入排序号"></el-input>
        </el-form-item>
        <el-form-item label="菜单类型" prop="type">
          <el-select v-model="form.type" placeholder="请选择菜单类型">
            <el-option label="目录" value="0"></el-option>
            <el-option label="菜单" value="1"></el-option>
            <el-option label="按钮" value="2"></el-option>
          </el-select>
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
          <el-button type="primary" @click="saveMenu">确定</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, onMounted, computed } from 'vue'

const menus = ref([
  {
    id: 1,
    menuName: '系统管理',
    menuCode: 'system',
    url: '/system',
    icon: 'setting',
    parentId: 0,
    sort: 1,
    type: 0,
    status: 1,
    children: [
      {
        id: 2,
        menuName: '用户管理',
        menuCode: 'user',
        url: '/system/user',
        icon: 'user',
        parentId: 1,
        sort: 1,
        type: 1,
        status: 1
      },
      {
        id: 3,
        menuName: '角色管理',
        menuCode: 'role',
        url: '/system/role',
        icon: 'avatar',
        parentId: 1,
        sort: 2,
        type: 1,
        status: 1
      },
      {
        id: 4,
        menuName: '菜单管理',
        menuCode: 'menu',
        url: '/system/menu',
        icon: 'menu',
        parentId: 1,
        sort: 3,
        type: 1,
        status: 1
      }
    ]
  },
  {
    id: 5,
    menuName: '运营机构管理',
    menuCode: 'organization',
    url: '/organization',
    icon: 'office-building',
    parentId: 0,
    sort: 2,
    type: 0,
    status: 1
  },
  {
    id: 10,
    menuName: '场馆和活动管理',
    menuCode: 'venue',
    url: '/venue',
    icon: 'location',
    parentId: 0,
    sort: 3,
    type: 0,
    status: 1
  },
  {
    id: 14,
    menuName: '预约管理',
    menuCode: 'booking',
    url: '/booking',
    icon: 'ticket',
    parentId: 0,
    sort: 4,
    type: 0,
    status: 1
  },
  {
    id: 18,
    menuName: '科普资讯管理',
    menuCode: 'info',
    url: '/info',
    icon: 'document',
    parentId: 0,
    sort: 5,
    type: 0,
    status: 1
  },
  {
    id: 21,
    menuName: '数据分析',
    menuCode: 'analysis',
    url: '/analysis',
    icon: 'data-analysis',
    parentId: 0,
    sort: 6,
    type: 0,
    status: 1
  }
])

const menuOptions = computed(() => {
  // 递归获取所有菜单作为选项
  const options = []
  const flattenMenus = (menuList) => {
    menuList.forEach(menu => {
      options.push(menu)
      if (menu.children) {
        flattenMenus(menu.children)
      }
    })
  }
  flattenMenus(menus.value)
  return options
})

const dialogVisible = ref(false)
const dialogTitle = ref('添加菜单')
const form = ref({})
const formRef = ref(null)

const rules = {
  menuName: [{ required: true, message: '请输入菜单名称', trigger: 'blur' }],
  menuCode: [{ required: true, message: '请输入菜单编码', trigger: 'blur' }],
  url: [{ required: true, message: '请输入路由地址', trigger: 'blur' }],
  parentId: [{ required: true, message: '请选择父菜单', trigger: 'blur' }],
  sort: [{ required: true, message: '请输入排序号', trigger: 'blur' }],
  type: [{ required: true, message: '请选择菜单类型', trigger: 'blur' }],
  status: [{ required: true, message: '请选择状态', trigger: 'blur' }]
}

const addMenu = () => {
  form.value = {
    parentId: 0,
    sort: 0,
    type: 0,
    status: 1
  }
  dialogTitle.value = '添加菜单'
  dialogVisible.value = true
}

const addSubMenu = (parentMenu) => {
  form.value = {
    parentId: parentMenu.id,
    sort: 0,
    type: 1,
    status: 1
  }
  dialogTitle.value = `添加${parentMenu.menuName}的子菜单`
  dialogVisible.value = true
}

const editMenu = (menu) => {
  form.value = { ...menu }
  dialogTitle.value = '编辑菜单'
  dialogVisible.value = true
}

const saveMenu = async () => {
  if (!formRef.value) return
  
  await formRef.value.validate(async (valid) => {
    if (valid) {
      dialogVisible.value = false
      // 这里应该调用API保存菜单
      console.log('保存菜单', form.value)
    }
  })
}

const deleteMenu = (id) => {
  // 这里应该调用API删除菜单
  console.log('删除菜单', id)
}

onMounted(() => {
  // 这里应该调用API获取菜单列表
})
</script>

<style scoped>
.menu-container {
  padding: 20px;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.custom-tree-node {
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100%;
}

.tree-node-actions {
  display: flex;
  gap: 5px;
}
</style>