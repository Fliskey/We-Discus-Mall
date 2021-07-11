<template>
<!--  <a-form-model ref='umUser' :model="umUser" :rules='rules' class='login-form register'>-->
  <a-form-model ref='umUser' :model="pageData" :rules='rules' class='login-form register'>
    <h2>注册</h2>
<!--    <a-form-model-item prop='id'>
      <a-input placeholder='学号' block v-model='umUser.id' />
    </a-form-model-item>-->
    <a-form-model-item prop='name'>
      <a-input placeholder='姓名' block v-model='pageData.name' />
    </a-form-model-item>
    <a-form-model-item prop='email'>
      <a-input placeholder='邮箱' block v-model='pageData.email' />
    </a-form-model-item>
    <a-form-model-item prop='telNumber'>
      <a-input placeholder='电话' block v-model='pageData.telNumber' />
    </a-form-model-item>
    <a-form-model-item prop='password'>
      <a-input v-model='pageData.password' block type='password' placeholder='密码' />
    </a-form-model-item>
    <a-form-model-item class='center'>
      <a-button type="primary" block @click='onSubmit'>注册</a-button>
    </a-form-model-item>
  </a-form-model>
</template>

<script>
  export default {
    data () {
      return {
        pageData:{
          id: 0,
          name: '',
          password: '',
          telNumber: '',
          email: ''
        },
        umUser: {
          id: '',
          name: '',
          telNumber: '',
          email: '',
        },
        umLogin:{
          id: 0,
          password: '',
          salt: '',
        },
        radioStyle: {
          display: 'block',
          height: '30px',
          lineHeight: '30px'
        },
        rules: {
          email: {
            required: true,
            message: '请输入邮箱',
            trigger: 'blur'
          },
          name: {
            required: true,
            message: '请输入姓名',
            trigger: 'blur'
          },
          password: {
            required: true,
            message: '请输入登录密码',
            trigger: 'blur'
          },
          telNumber: {
            required: true,
            message: '请输入手机号',
            trigger: 'blur'
          }
        }
      }
    },
    methods: {
      onSubmit(){
        //加盐哈希注册，By@Fliskey
        let _this = this

        axios.get('http://localhost:8181/umLogin/getSalt').then(function (response){
          _this.umLogin.salt = response.data
          _this.pageData.password = _this.$sha256(_this.pageData.password+_this.umLogin.salt)
          _this.umLogin.password = _this.pageData.password
        })
        axios.post('http://localhost:8181/umLogin/addUser',this.umLogin).then(function (response){
          _this.umUser.id = response.data
          _this.umUser.id = _this.umUser.id+1 //我也不知道为什么要+1
          _this.umUser.name = _this.pageData.name
          _this.umUser.telNumber = _this.pageData.telNumber
          _this.umUser.email = _this.pageData.email
        })
        axios.post('http://localhost:8181/umUser/add',this.umUser).then(function (response) {
          if(response.data){
            alert('注册成功！')
            _this.$router.push({
              path: '/login'
            })
          }
          else
            alert('注册失败！')
        })
      },
      change (type) {
        this.$emit('changeType', type)
      }
    }
  }
</script>
