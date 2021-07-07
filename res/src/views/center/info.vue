<template>
  <a-row class='cen-box'>
    <a-col :span='12'>
      <h2>修改个人信息</h2>
      <a-form-model ref='form' :model="form" :rules='rules'>
        <a-form-model-item prop='psw'>
          <a-input v-model='form.psw' block type='学号' disabled placeholder='请输入学号'>
            <a-icon slot="prefix" type="user" style="color:rgba(0,0,0,.25)" />
          </a-input>
        </a-form-model-item>
        <a-form-model-item  prop='phone'>
          <a-input v-model='form.psw1' block type='password' placeholder='请输入手机号'>
            <a-icon slot="prefix" type="phone" style="color:rgba(0,0,0,.25)" />
          </a-input>
        </a-form-model-item>
        <a-form-model-item prop='email'>
          <a-input v-model='form.psw2' block type='password' placeholder='请输入邮箱'>
            <a-icon slot="prefix" type="mail" style="color:rgba(0,0,0,.25)" />
          </a-input>
        </a-form-model-item>
        <a-form-model-item prop='verifyCode'>
        </a-form-model-item>
        <a-form-model-item>
          <a-button type="primary" block @click='submit'>确认修改</a-button>
        </a-form-model-item>
      </a-form-model>
    </a-col>
  </a-row>
</template>

<script>
  export default {
    data () {
      return {
        wrapperCol: { span: 20 },
        form: {
          name: '',
          psw: '',
          psw1: '',
          psw2: ''
        },
        rules: {
          psw: {
            required: true,
            message: '请输入原登录密码',
            trigger: 'blur'
          },
          psw1: {
            required: true,
            message: '请输入新密码',
            trigger: 'blur'
          },
          psw2: {
            required: true,
            message: '请再次输入新密码',
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
          }
        })
      }
    }
  }
</script>
<style lang='less'>
  @import "~@/style/center.less";
</style>
