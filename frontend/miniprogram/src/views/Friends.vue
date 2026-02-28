<template>
  <div class="friends-container">
    <van-nav-bar title="亲友信息" left-text="返回" @click-left="goBack" />
    
    <!-- 添加亲友按钮 -->
    <div style="padding: 15px;">
      <van-button type="primary" block @click="addFriend">添加亲友</van-button>
    </div>
    
    <!-- 亲友列表 -->
    <div class="friends-list">
      <div class="friend-item" v-for="(friend, index) in friends" :key="index">
        <div class="friend-info">
          <h3 class="friend-name">{{ friend.name }}</h3>
          <p class="friend-idcard">{{ friend.idCard }}</p>
          <p class="friend-phone">{{ friend.phone }}</p>
        </div>
        <div class="friend-actions">
          <van-button type="primary" size="small" @click="editFriend(friend)">编辑</van-button>
          <van-button type="danger" size="small" @click="deleteFriend(friend.id)">删除</van-button>
        </div>
      </div>
    </div>
    
    <!-- 添加/编辑亲友对话框 -->
    <van-popup v-model:show="showFriendDialog" position="bottom" :style="{ height: '80%' }">
      <div class="friend-dialog">
        <div class="dialog-header">
          <h3>{{ dialogTitle }}</h3>
          <van-icon name="close" @click="showFriendDialog = false" />
        </div>
        <van-form @submit="saveFriend">
          <van-cell-group>
            <van-field
              v-model="friendForm.name"
              name="name"
              label="姓名"
              placeholder="请输入姓名"
              :rules="[{ required: true, message: '请输入姓名' }]"
            />
            <van-field
              v-model="friendForm.idCard"
              name="idCard"
              label="身份证号"
              placeholder="请输入身份证号"
              :rules="[{ required: true, message: '请输入身份证号' }]"
            />
            <van-field
              v-model="friendForm.phone"
              name="phone"
              label="手机号"
              placeholder="请输入手机号"
              :rules="[{ required: true, message: '请输入手机号' }]"
            />
          </van-cell-group>
          <div style="margin: 15px;">
            <van-button type="primary" block native-type="submit">保存</van-button>
          </div>
        </van-form>
      </div>
    </van-popup>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { showToast, showDialog } from 'vant'

const router = useRouter()

const friends = ref([
  {
    id: 1,
    name: '张三',
    idCard: '110101199001011234',
    phone: '13800138000'
  },
  {
    id: 2,
    name: '李四',
    idCard: '110101199001011235',
    phone: '13900139000'
  }
])

const showFriendDialog = ref(false)
const dialogTitle = ref('添加亲友')
const friendForm = reactive({
  id: '',
  name: '',
  idCard: '',
  phone: ''
})

const addFriend = () => {
  friendForm.id = ''
  friendForm.name = ''
  friendForm.idCard = ''
  friendForm.phone = ''
  dialogTitle.value = '添加亲友'
  showFriendDialog.value = true
}

const editFriend = (friend) => {
  friendForm.id = friend.id
  friendForm.name = friend.name
  friendForm.idCard = friend.idCard
  friendForm.phone = friend.phone
  dialogTitle.value = '编辑亲友'
  showFriendDialog.value = true
}

const saveFriend = () => {
  if (!friendForm.name) {
    showToast('请输入姓名')
    return
  }
  if (!friendForm.idCard) {
    showToast('请输入身份证号')
    return
  }
  if (!friendForm.phone) {
    showToast('请输入手机号')
    return
  }
  
  if (friendForm.id) {
    // 编辑亲友
    const index = friends.value.findIndex(f => f.id === friendForm.id)
    if (index !== -1) {
      friends.value[index] = { ...friendForm }
    }
  } else {
    // 添加亲友
    friendForm.id = friends.value.length + 1
    friends.value.push({ ...friendForm })
  }
  
  showFriendDialog.value = false
  showToast('保存成功')
  // 这里应该调用API保存亲友信息
  console.log('保存亲友信息', friendForm)
}

const deleteFriend = (id) => {
  showDialog({
    title: '删除亲友',
    message: '确定要删除该亲友吗？',
    confirmButtonText: '确定',
    cancelButtonText: '取消'
  }).then(() => {
    const index = friends.value.findIndex(f => f.id === id)
    if (index !== -1) {
      friends.value.splice(index, 1)
    }
    showToast('删除成功')
    // 这里应该调用API删除亲友信息
    console.log('删除亲友', id)
  })
}

const goBack = () => {
  router.back()
}

onMounted(() => {
  // 这里应该调用API获取亲友列表
})
</script>

<style scoped>
.friends-container {
  min-height: 100vh;
  background-color: #f5f5f5;
}

.friends-list {
  padding: 15px;
}

.friend-item {
  background-color: white;
  border-radius: 8px;
  padding: 15px;
  margin-bottom: 15px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.friend-info {
  margin-bottom: 10px;
}

.friend-name {
  font-size: 16px;
  font-weight: bold;
  color: #333;
  margin-bottom: 5px;
}

.friend-idcard {
  font-size: 14px;
  color: #666;
  margin-bottom: 5px;
}

.friend-phone {
  font-size: 14px;
  color: #666;
  margin: 0;
}

.friend-actions {
  display: flex;
  gap: 10px;
  justify-content: flex-end;
  padding-top: 10px;
  border-top: 1px solid #f0f0f0;
}

.friend-dialog {
  height: 100%;
  display: flex;
  flex-direction: column;
}

.dialog-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 15px;
  border-bottom: 1px solid #f0f0f0;
}

.dialog-header h3 {
  margin: 0;
  font-size: 16px;
  font-weight: bold;
}
</style>