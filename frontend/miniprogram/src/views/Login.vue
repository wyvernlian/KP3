<template>
  <div class="login-container">
    <div class="login-form">
      <div class="logo">
        <img src="https://example.com/logo.png" alt="科普科技馆" />
        <h1>科普科技馆预约</h1>
      </div>
      <van-form @submit="onSubmit">
        <van-cell-group>
          <van-field
            v-model="form.phone"
            name="phone"
            label="手机号"
            placeholder="请输入手机号"
            :rules="[{ required: true, message: '请输入手机号' }]"
          />
          <van-field
            v-model="form.code"
            name="code"
            label="验证码"
            placeholder="请输入验证码"
            :rules="[{ required: true, message: '请输入验证码' }]"
          >
            <template #button>
              <van-button type="primary" size="small" @click="getCode" :disabled="counting">{{ codeText }}</van-button>
            </template>
          </van-field>
        </van-cell-group>
        <div style="margin: 16px;">
          <van-button type="primary" block native-type="submit">登录/注册</van-button>
        </div>
      </van-form>
      <div class="other-login">
        <van-button type="default" block @click="wechatLogin" style="margin-top: 10px;">
          <van-icon name="wechat" /> 微信一键登录
        </van-button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, reactive } from 'vue'
import { useRouter } from 'vue-router'
import { showToast } from 'vant'

const router = useRouter()
const form = reactive({
  phone: '',
  code: ''
})

const counting = ref(false)
const count = ref(60)
const codeText = ref('获取验证码')

const getCode = () => {
  if (!form.phone) {
    showToast('请输入手机号')
    return
  }
  
  counting.value = true
  codeText.value = `${count.value}秒后重新获取`
  
  const timer = setInterval(() => {
    count.value--
    codeText.value = `${count.value}秒后重新获取`
    if (count.value <= 0) {
      clearInterval(timer)
      counting.value = false
      count.value = 60
      codeText.value = '获取验证码'
    }
  }, 1000)
  
  // 这里应该调用API发送验证码
  console.log('发送验证码', form.phone)
}

const onSubmit = () => {
  // 这里应该调用API登录/注册
  console.log('登录/注册', form)
  router.push('/home')
}

const wechatLogin = () => {
  // 这里应该调用微信授权登录
  console.log('微信登录')
  router.push('/home')
}
</script>

<style scoped>
.login-container {
  height: 100vh;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  display: flex;
  justify-content: center;
  align-items: center;
  padding: 20px;
}

.login-form {
  width: 100%;
  max-width: 400px;
  background: white;
  border-radius: 12px;
  padding: 30px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
}

.logo {
  text-align: center;
  margin-bottom: 30px;
}

.logo img {
  width: 80px;
  height: 80px;
  margin-bottom: 10px;
}

.logo h1 {
  font-size: 20px;
  font-weight: bold;
  color: #333;
}

.other-login {
  margin-top: 20px;
}
</style>