<template>
  <div class='login-box'>
    <div class='bg-box'>
      <div class='bg bg1' :class='active === 1 ? "active" : "" '></div>
      <div class='bg bg2' :class='active === 2 ? "active" : "" '></div>
      <div class='bg bg3' :class='active === 3 ? "active" : "" '></div>
    </div>
    <div>
      <div  class='form-box' :class='type==="register"? "factive":""'>
        <div class='login-form left'>
          <a-button block type='primary' @click="changeType('register')">{{type==="login"?"注册":"登录"}}</a-button>
        </div>
        <div class='right'>
          <forget  v-if='type=="forget"' />
          <login v-if='type=="login"' />
          <register v-if='type=="register"' />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
  import forget from './forget'
  import login from './login'
  import register from './register'
  export default {
    data () {
      return {
        wrapperCol: { span: 24 },
        form: {
          account: '',
          psw: ''
        },
        rules: {
          account: {
            required: true,
            message: '请输入登录账号',
            trigger: 'blur'
          },
          psw: {
            required: true,
            message: '请输入登录密码',
            trigger: 'blur'
          }
        },
        active: 1,
        type: 'login'
      }
    },
    components: {
      forget,
      login,
      register
    },
    mounted () {
      //延时15秒执行
      this.changeImg()
    },
    methods: {
      changeImg () {
        //轮播图总张数
        const total = 3
        setTimeout (() => {
          this.active = this.active < total ? ++this.active : 1
          this.changeImg()
        }, 15000)
      },
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
      changeType () {
        this.type = this.type ==='login' ? 'register' : 'login'
      }
    }
  }
</script>

<style lang='less'>
  @import "~@/style/login.less";
</style>
