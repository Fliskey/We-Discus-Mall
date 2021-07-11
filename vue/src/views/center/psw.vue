<template>
  <a-row class='cen-box'>
    <a-col :span='12'>
      <a-form-model ref='form' :model="form" :rules='rules'>
        <h2>修改密码</h2>
<!--        <a-form-model-item prop='psw'>
          <a-input v-model='form.psw' block type='password' placeholder='请输入原密码'>
            <a-icon slot="prefix" type="lock" style="color:rgba(0,0,0,.25)" />
          </a-input>
        </a-form-model-item>-->
        <a-form-model-item  prop='psw1'>
          <a-input v-model='form.psw1' block type='password' placeholder='请输入新密码'>
            <a-icon slot="prefix" type="lock" style="color:rgba(0,0,0,.25)" />
          </a-input>
        </a-form-model-item>
<!--        <a-form-model-item prop='psw2'>
          <a-input v-model='form.psw2' block type='password' placeholder='请再次输入新密码'>
            <a-icon slot="prefix" type="lock" style="color:rgba(0,0,0,.25)" />
          </a-input>
        </a-form-model-item>-->
        <a-form-model-item>
          <a-button type="primary" block @click='submit'>确认修改</a-button>
        </a-form-model-item>
<!--        <a-form-model-item style='margin-bottom:10px'>
          <a-radio-group
            :default-value="1"
            v-model="userMode">
            <a-radio :style="radioStyle" :value="1">老用户</a-radio>
            <a-radio :style="radioStyle" :value="2">新用户</a-radio>
          </a-radio-group>
        </a-form-model-item>-->
      </a-form-model>
    </a-col>
  </a-row>
</template>

<script>
  export default {
    data () {
      return {
        userMode: '',
        wrapperCol: { span: 20 },
        form: {
          name: '',
          psw: '',
          psw1: '',
          psw2: ''
        },
        umLogin:{
          id:'',
          password:'',
          salt:''
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
        },
        radioStyle: {
          display: 'block',
          height: '30px',
          lineHeight: '30px'
        },
      }
    },
    mounted() {
      if(this.$cookies.isKey('vid') === false)
        this.$router.push('login')
    },
    methods: {
      submit () {
        /*this.$refs['form'].validate((valid) => {
          if (valid) {
            console.log(valid)
          }
        })*/
        let _this = this
        this.umLogin.id = this.$cookies.get('vid')
        console.log(this.umLogin.id)
        /*if(this.userMode === 1){
          console.log(this.userMode)
        }*/
        //else if(this.userMode === 2) {
          axios.post('http://localhost:8181/umLogin/querySalt/', this.umLogin.id).then(function (response) {
            _this.umLogin.salt = response.data
            _this.umLogin.password = _this.$sha256(_this.form.psw1 + _this.umLogin.salt)
            if(_this.umLogin.salt == ''){
              alert("修改密码失败！")
              _this.$router.push({
                path: '/visitor/center/info'
              })
            }
            else {
              _this.umLogin.password = _this.$sha256(_this.form.psw1 + _this.umLogin.salt)
              axios.post('http://localhost:8181/umLogin/update', _this.umLogin).then(function (response) {
                if (response.data) {
                  alert("修改密码成功")
                  _this.$route.push({
                    path: '/visitor/center/info'
                  })
                } else {
                  alert("修改密码失败")
                  _this.$route.push({
                    path: '/visitor/center/psw'
                  })
                }
              })
            }
          })

        //}
      }
    }
  }
</script>
<style lang='less'>
  @import "~@/style/center.less";
</style>
