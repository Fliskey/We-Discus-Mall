<template>
  <a-row class='cen-box'>
    <a-col :span='12'>
      <a-form-model ref='form' :model="form" :rules='rules'>
        <h2>修改密码</h2>
        <a-form-model-item prop='psw'>
          <a-input v-model='form.psw' block type='password' placeholder='请输入原密码'>
            <a-icon slot="prefix" type="lock" style="color:rgba(0,0,0,.25)" />
          </a-input>
        </a-form-model-item>
        <a-form-model-item  prop='psw1'>
          <a-input v-model='form.psw1' block type='password' placeholder='请输入新密码'>
            <a-icon slot="prefix" type="lock" style="color:rgba(0,0,0,.25)" />
          </a-input>
        </a-form-model-item>
        <a-form-model-item prop='psw2'>
          <a-input v-model='form.psw2' block type='password' placeholder='请再次输入新密码'>
            <a-icon slot="prefix" type="lock" style="color:rgba(0,0,0,.25)" />
          </a-input>
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
