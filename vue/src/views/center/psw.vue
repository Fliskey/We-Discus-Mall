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
      const validatorPsw = (rule,value,callback) =>{
        if (!value){
          callback(new Error('请输入原密码！'))
        }else {
          callback()
        }
      }
      const validatorPsw1 = (rule,value,callback) =>{
        if (!value){
          callback(new Error('请输入修改后的密码！'))
        }else {
          if (!/^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$/.test(value)){
            callback(new Error('密码格式不正确！'))
          }else {
            callback()
          }
        }
      }

      const validatorPsw2 = (rule,value,callback) =>{
        if (!value){
          callback(new Error('请再次输入修改后的密码！'))
        }else {
          if (value !== this.form.psw1){
            callback(new Error('两次输入的密码不一致！'))
          }else {
            callback()
          }
        }
      }

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
        confirmUser:{
          id:'',
          password:''
        },
        salt:'',
        rules: {
          psw: [
            {require:true, validator:validatorPsw, trigger:'blur'}
          ],
          psw1: [
            {require:true, validator:validatorPsw1, trigger:'blur'}
          ],
          psw2: [
            {require:true, validator:validatorPsw2, trigger:'blur'}
          ],
        },
      }
    },
    mounted() {
      let _this = this
      if(this.$cookies.isKey('vid') === false)
        this.$router.push('login')
    },
    methods: {
      submit () {
        this.$refs.form.validate(valid => {
          if (valid){
            this.changePsw()
          }else {
            alert('无效修改！')
            return false;
          }
        })
      },
      changePsw(){
        let _this = this
        this.umLogin.id = this.$cookies.get('vid')
        console.log(this.umLogin.id)
        //验证原密码
        axios.post('http://localhost:8181/umLogin/querySalt/', this.umLogin.id).then(function (response) {
          _this.salt = response.data
          _this.umLogin.salt = response.data
          _this.confirmUser.id = _this.umLogin.id
          _this.confirmUser.password = _this.$sha256(_this.form.psw + _this.salt)

          axios.put('http://localhost:8181/umLogin/login/',_this.confirmUser).then(function (response){
            if (!response.data) {
              alert('原始密码不正确！')
            }
            else {
              if (_this.umLogin.salt === '') {
                alert("修改密码失败！")
                _this.$router.push({
                  path: '/visitor/center/info'
                })
              } else {
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
            }
          })

        })
      }
    }
  }
</script>
