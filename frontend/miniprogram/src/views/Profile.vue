<template>
  <div class="profile-container">
    <van-nav-bar title="个人信息" left-text="返回" @click-left="goBack" />
    
    <div class="profile-content">
      <!-- 头像 -->
      <div class="avatar-section">
        <div class="avatar-container">
          <img :src="user.avatar" :alt="user.name" class="avatar" />
          <div class="avatar-upload" @click="uploadAvatar">
            <van-icon name="camera" />
          </div>
        </div>
      </div>
      
      <!-- 个人信息表单 -->
      <van-cell-group>
        <van-field
          v-model="user.name"
          name="name"
          label="姓名"
          placeholder="请输入姓名"
        />
        <van-field
          v-model="user.idCard"
          name="idCard"
          label="身份证号"
          placeholder="请输入身份证号"
        />
        <van-field
          v-model="user.phone"
          name="phone"
          label="手机号"
          placeholder="请输入手机号"
          readonly
        />
        <van-field
          v-model="user.email"
          name="email"
          label="邮箱"
          placeholder="请输入邮箱"
        />
      </van-cell-group>
      
      <!-- 保存按钮 -->
      <div style="margin: 20px;">
        <van-button type="primary" block @click="saveProfile">保存修改</van-button>
      </div>
      
      <!-- 实名认证状态 -->
      <div class="verification-status">
        <van-cell title="实名认证状态">
          <template #right>
            <van-tag :type="user.verified ? 'success' : 'warning'">
              {{ user.verified ? '已认证' : '未认证' }}
            </van-tag>
          </template>
        </van-cell>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { showToast } from 'vant'

const router = useRouter()

const user = reactive({
  name: '张三',
  idCard: '110101199001011234',
  phone: '13800138000',
  email: 'zhangsan@example.com',
  avatar: 'https://example.com/avatar.jpg',
  verified: true
})

const uploadAvatar = () => {
  // 这里应该调用API上传头像
  console.log('上传头像')
}

const saveProfile = () => {
  // 验证表单
  if (!user.name) {
    showToast('请输入姓名')
    return
  }
  if (!user.idCard) {
    showToast('请输入身份证号')
    return
  }
  
  // 这里应该调用API保存个人信息
  console.log('保存个人信息', user)
  showToast('保存成功')
  router.back()
}

const goBack = () => {
  router.back()
}

onMounted(() => {
  // 这里应该调用API获取用户信息
})
</script>

<style scoped>
.profile-container {
  min-height: 100vh;
  background-color: #f5f5f5;
}

.profile-content {
  background-color: white;
  padding-bottom: 20px;
}

.avatar-section {
  display: flex;
  justify-content: center;
  padding: 30px 0;
  border-bottom: 1px solid #f0f0f0;
}

.avatar-container {
  position: relative;
}

.avatar {
  width: 100px;
  height: 100px;
  border-radius: 50%;
  object-fit: cover;
}

.avatar-upload {
  position: absolute;
  bottom: 0;
  right: 0;
  width: 30px;
  height: 30px;
  background-color: #409eff;
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  color: white;
}

.verification-status {
  margin-top: 10px;
  background-color: white;
}
</style>