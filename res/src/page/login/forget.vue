<template>
  <div class='form-box'>
    <a-form-model ref='form' :model="form" :rules='rules' class='login-form' :wrapper-col="wrapperCol">
      <h2>找回密码</h2>
      <a-form-model-item prop='account'>
        <a-input placeholder='请输入学号' v-model='form.account'>
          <a-icon slot="prefix" type="user" style="color:rgba(0,0,0,.25)" />
        </a-input>
      </a-form-model-item>
      <a-form-model-item prop='phone'>
        <a-input v-model='form.phone' placeholder='请输入手机号'>
          <a-icon slot="prefix" type="mobile" style="color:rgba(0,0,0,.25)" />
        </a-input>
      </a-form-model-item>
      <a-form-model-item prop='verifyCode'>
        <a-row :gutter="8" style="margin: 0 -4px">
          <a-col :span="16">
            <a-input v-model='form.verifyCode' placeholder="请输入验证码">
              <a-icon slot="prefix" type="mail" style="color:rgba(0,0,0,.25)" />
            </a-input>
          </a-col>
          <a-col :span="8" style="padding-left: 4px">
            <a-button style="width: 100%" class="captcha-button">获取验证码</a-button>
          </a-col>
        </a-row>
      </a-form-model-item>
      <a-form-model-item prop='psw'>
        <a-input v-model='form.psw' type='password' placeholder='请输入密码'>
          <a-icon slot="prefix" type="lock" style="color:rgba(0,0,0,.25)" />
        </a-input>
      </a-form-model-item>
      <a-form-model-item :wrapper-col="wrapperCol">
        <a-button type="primary" block @click='submit'>登录</a-button>
      </a-form-model-item>
      <a-form-model-item :wrapper-col="wrapperCol" class='check'>
        <a-row type='flex' justify='space-between'>
          <a class='alink' @click='change("login")'>返回登录</a>
        </a-row>
    </a-form-model-item>
    </a-form-model>
  </div>
</template>

<script>
  export default {
    data () {
      return {
        wrapperCol: { span: 24 },
        form: {
          account: '',
          psw: '',
          verifyCode: ''
        },
        rules: {
          account: {
            required: true,
            message: '请输入学号',
            trigger: 'blur'
          },
          phone: {
            required: true,
            message: '请输入手机号',
            trigger: 'blur'
          },
          verifyCode: {
            required: true,
            message: '请输入验证码',
            trigger: 'blur'
          },
          psw: {
            required: true,
            message: '请输入登录密码',
            trigger: 'blur'
          }
        }
      }
    },
    methods: {
      submit () {
        this.$refs['form'].validate((valid) => {
          if (valid) {
            console.log(valid)
            // 路由注册
            this.$router.push({
              path: '/admin/user'
            })
          }
        })
      },
      change (type) {
        this.$emit('changeType', type)
      }
    }
  }
</script>
