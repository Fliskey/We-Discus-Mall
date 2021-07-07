<template>
  <a-form-model ref='form' :model="umUser" :rules='rules' class='login-form' :wrapper-col="wrapperCol">
    <h2>登录</h2>
    <a-form-model-item prop='account'>
      <a-input placeholder='姓名' v-model='umUser.name' />
    </a-form-model-item>
    <a-form-model-item prop='psw'>
      <a-input v-model='umUser.password' type='password' placeholder='密码' />
    </a-form-model-item>
    <a-form-model-item class='check'>
        <span @click='change("forget")'>忘记密码</span>
    </a-form-model-item>
    <a-form-model-item class='center'>
      <a-button type="primary" block @click='userLogin'>登录</a-button>
    </a-form-model-item>
  </a-form-model>
</template>

<script>
  export default {
    data () {
      return {
        wrapperCol: { span: 24 },
        umUser: {
          name: '',
          password: ''
        },
        rules: {
          name: {
            required: true,
            message: '请输入学号',
            trigger: 'blur'
          },
          password: {
            required: true,
            message: '请输入登录密码',
            trigger: 'blur'
          }
        }
      }
    },
    methods: {
      userLogin () {
        // 登录前校验
        // 发起登录请求
        let _this = this
        axios.put('http://localhost:8181/umUser/login/',this.umUser).then(function (response){
          if(!response.data)
            alert('用户名或密码错误，请重新输入！')
          else
            _this.http.get('data.json').then(() => {
              // 路由注册
              alert('登录成功！')
              _this.$router.push({
                path: '/admin/center/info'
              })
        })
        //await this.$router.push('/table')
      })
      },
      change (type) {
        this.$emit('changeType', type)
      }
    },
    submit () {
      this.$refs['form'].validate((valid) => {
        if (valid) {
          console.log(valid)
          // 虚拟接口，需改成实际接口
          this.http.get('data.json').then(() => {
            // 路由注册
            this.$router.push({
              path: '/admin/center/info'
            })
          })
        }
      })
    },
  }
</script>
