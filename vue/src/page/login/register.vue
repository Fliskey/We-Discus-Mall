<template>
  <a-form-model ref='umUser' :model="umUser" :rules='rules' class='login-form register'>
    <h2>注册(加密测试)</h2>
<!--    <a-form-model-item prop='id'>
      <a-input placeholder='学号' block v-model='umUser.id' />
    </a-form-model-item>-->
    <a-form-model-item prop='name'>
      <a-input placeholder='姓名' block v-model='umUser.name' />
    </a-form-model-item>
    <a-form-model-item prop='email'>
      <a-input placeholder='邮箱' block v-model='umUser.email' />
    </a-form-model-item>
    <a-form-model-item prop='telNumber'>
      <a-input placeholder='电话' block v-model='umUser.telNumber' />
    </a-form-model-item>
    <a-form-model-item prop='password'>
      <a-input v-model='umLogin.password' block type='password' placeholder='密码' />
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
        umUser: {
          id: '',
          name: '',
          password:'',
          telNumber: '',
          email: '',
          //type: 1
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
          /*id: {
            required: true,
            message: '请输入学号',
            trigger: 'blur'
          },*/
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
        let _this = this

        axios.post('http://localhost:8181/umUser/add',this.umUser).then(function (response) {
          if(response.data){
            alert('注册成功！')
            this.$router.push({
              path: '/visitor/user'
            })
          }
          else
            alert('注册失败！')
        })

      },
      submit () {
        // this.$axios.pos("http://autumnfish.cn/api/joke/list?num=3")
        //   .then(res => {
        //     console.log(res)
        //   })

        this.$refs['form'].validate((valid) => {
          if (valid) {
            console.log(valid)
            // 路由注册
            this.$router.push({
              //path: '/admin/user'
              path:'/login'
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
