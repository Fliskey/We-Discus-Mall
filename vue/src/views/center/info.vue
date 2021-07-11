<template>
  <a-row class='cen-box'>
    <a-col :span='12'>
      <h2>修改个人信息</h2>
      <a-form-model ref='form' :model="UmUser" :rules='rules'>
        <a-form-model-item prop='id'>
          <a-input v-model='UmUser.id' block type='学号' disabled placeholder='请输入学号'>
            <a-icon slot="prefix" type="user" style="color:rgba(0,0,0,.25)" />
          </a-input>
        </a-form-model-item>
        <a-form-model-item prop='name'>
          <a-input v-model='UmUser.name' block type='姓名' disabled placeholder='请输入姓名'>
            <a-icon slot="prefix" type="user" style="color:rgba(0,0,0,.25)" />
          </a-input>
        </a-form-model-item>
        <a-form-model-item  prop='telNumber'>
          <a-input v-model='UmUser.telNumber' block type='手机号' placeholder='请输入手机号'>
            <a-icon slot="prefix" type="telNumber" style="color:rgba(0,0,0,.25)" />
          </a-input>
        </a-form-model-item>
        <a-form-model-item prop='email'>
          <a-input v-model='UmUser.email'  placeholder='请输入邮箱'>
            <a-icon slot="prefix" type="email" style="color:rgba(0,0,0,.25)" />
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
        UmUser: {
          id:'',
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
    mounted() {
      if(this.$cookies.isKey('vid') === false)
        this.$router.push('login')
      let id = this.$cookies.get('vid')
      let _this = this
      axios.get('http://localhost:8181/umUser/find/'+id).then(function (response) {
        _this.UmUser = response.data
      })
    },
    methods: {
      submit () {
        let _this = this
        axios.put('http://localhost:8181/umUser/update/',this.UmUser).then(function (response) {
          if(response.data){
            alert('修改成功！')
            _this.$router.push({
              path: '/admin/center/info?id='+this.id
            })
          }
        })
      }
    }
  }
</script>
<style lang='less'>
  @import "~@/style/center.less";
</style>
