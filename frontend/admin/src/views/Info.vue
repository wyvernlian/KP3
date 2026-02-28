<template>
  <div class="info-container">
    <el-card>
      <template #header>
        <div class="card-header">
          <span>科普资讯管理</span>
        </div>
      </template>
      <el-tabs v-model="activeTab">
        <el-tab-pane label="资讯分类" name="category">
          <div class="tab-header">
            <el-button type="primary" @click="addCategory">添加资讯分类</el-button>
          </div>
          <el-table :data="categories" style="width: 100%">
            <el-table-column prop="id" label="ID" width="80"></el-table-column>
            <el-table-column prop="categoryName" label="分类名称"></el-table-column>
            <el-table-column prop="categoryCode" label="分类编码"></el-table-column>
            <el-table-column prop="sort" label="排序"></el-table-column>
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
        <el-tab-pane label="资讯管理" name="info">
          <div class="tab-header">
            <el-button type="primary" @click="addInfo">添加资讯</el-button>
          </div>
          <el-table :data="infos" style="width: 100%">
            <el-table-column prop="id" label="ID" width="80"></el-table-column>
            <el-table-column prop="title" label="标题"></el-table-column>
            <el-table-column prop="categoryName" label="分类"></el-table-column>
            <el-table-column prop="viewCount" label="浏览量"></el-table-column>
            <el-table-column prop="likeCount" label="点赞数"></el-table-column>
            <el-table-column prop="status" label="状态">
              <template #default="scope">
                <el-tag :type="scope.row.status === 1 ? 'success' : 'danger'">
                  {{ scope.row.status === 1 ? '发布' : '未发布' }}
                </el-tag>
              </template>
            </el-table-column>
            <el-table-column prop="createTime" label="创建时间"></el-table-column>
            <el-table-column label="操作" width="150">
              <template #default="scope">
                <el-button type="primary" size="small" @click="editInfo(scope.row)">编辑</el-button>
                <el-button type="danger" size="small" @click="deleteInfo(scope.row.id)">删除</el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-tab-pane>
      </el-tabs>
    </el-card>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'

const activeTab = ref('category')
const categories = ref([
  {
    id: 1,
    categoryName: '科技新闻',
    categoryCode: 'tech',
    sort: 1,
    status: 1,
    createTime: '2026-02-27 10:00:00'
  },
  {
    id: 2,
    categoryName: '科普知识',
    categoryCode: 'science',
    sort: 2,
    status: 1,
    createTime: '2026-02-27 10:00:00'
  }
])

const infos = ref([
  {
    id: 1,
    title: '人工智能的发展历程',
    categoryId: 1,
    categoryName: '科技新闻',
    viewCount: 1000,
    likeCount: 100,
    status: 1,
    createTime: '2026-02-27 10:00:00'
  },
  {
    id: 2,
    title: '黑洞的奥秘',
    categoryId: 2,
    categoryName: '科普知识',
    viewCount: 800,
    likeCount: 80,
    status: 1,
    createTime: '2026-02-27 10:00:00'
  }
])

const addCategory = () => {
  // 添加资讯分类
  console.log('添加资讯分类')
}

const editCategory = (category) => {
  // 编辑资讯分类
  console.log('编辑资讯分类', category)
}

const deleteCategory = (id) => {
  // 删除资讯分类
  console.log('删除资讯分类', id)
}

const addInfo = () => {
  // 添加资讯
  console.log('添加资讯')
}

const editInfo = (info) => {
  // 编辑资讯
  console.log('编辑资讯', info)
}

const deleteInfo = (id) => {
  // 删除资讯
  console.log('删除资讯', id)
}

onMounted(() => {
  // 这里应该调用API获取资讯分类和资讯列表
})
</script>

<style scoped>
.info-container {
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