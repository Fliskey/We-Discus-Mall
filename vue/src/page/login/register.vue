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
      <a-input placeholder='邮箱' block v-model='pageData.email' type="email"/>
    </a-form-model-item>
    <a-form-model-item prop='telNumber'>
      <a-input placeholder='电话' block v-model='pageData.telNumber' />
    </a-form-model-item>
    <a-form-model-item prop='password'>
      <a-input v-model='pageData.password' block type='password' placeholder='密码' />
    </a-form-model-item>
    <a-form-model-item prop='reconfirmPsw'>
      <a-input v-model='pageData.reconfirmPsw' block type='password' placeholder='确认密码' />
    </a-form-model-item>
    <a-form-model-item class='center' style="margin: 0 auto 80px auto">
      <a-button type="primary" block @click='onSubmit'>注册</a-button>
    </a-form-model-item>
  </a-form-model>
</template>



<script>
  import {notification} from 'ant-design-vue'

  export default {
    data () {

      const validatorName = (rule,value,callback) =>{
        if(!value){
          callback(new Error('请输入姓名！'))
        }
        else{
          callback()
        }
      }

      const validatorPhone = (rule,value,callback) =>{
        if (!value){
          callback(new Error('请输入手机号码！'))
        } else{
          if (!/^1\d{10}$/.test(value)) {
            callback(new Error('手机号码格式不正确！'))
          } else {
            callback()
          }
        }
      }
      const validatorPsw = (rule,value,callback) =>{
        if (!value){
          callback(new Error('请输入密码！'))
        } else{
          if (!/^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$/.test(value)) {
            callback(new Error('密码格式不正确！(应为8-16位且包含字母和数字)'))
          } else {
            callback()
          }
        }
      }
      const validatorEmail = (rule,value,callback) =>{
        if (!value){
          callback(new Error('请输入邮箱！'))
        } else{
          if (!/^[0-9a-zA-Z_.-]+[@][0-9a-zA-Z_.-]+([.][a-zA-Z]+){1,2}$/.test(value)) {
            callback(new Error('邮箱格式不正确！'))
          } else {
            callback()
          }
        }
      }
      const validatorReconfirm = (rule,value,callback) =>{
        if (!value){
          callback(new Error('请再次输入密码！'))
        } else {
          if (value !== this.pageData.password){
            callback(new Error('两次输入的密码不一致！'))
          }else {
            callback()
          }
        }
      }
      return {
        pageData:{
          id: 0,
          name: '',
          password: '',
          telNumber: '',
          email: '',
          reconfirmPsw:'',
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
          email: [
            {required: true, validator:validatorEmail, trigger: 'blur'}
          ],
          name: [
            {required: true, validator:validatorName,trigger: 'blur'}
          ],
          password: [
            {required:true ,validator:validatorPsw,trigger:'blur'}
          ],
          telNumber: [
            {required:true ,validator:validatorPhone,trigger:'blur'}
          ],
          reconfirmPsw: [
            {require:true ,validator:validatorReconfirm,trigger:'blur'}
          ]
        },
      }
    },
    methods: {
      onSubmit(){
        this.$refs.umUser.validate(valid =>{
          if (valid){
            // alert('submit!');
            this.addNewUser();
          }else {
            // alert('error submit!');
            return false;
          }
        })
      },
      openNotificationWithIcon(type) {
        this.$notification[type]({
          message: '该号码可能已经被注册！',
          description:
            '请使用一个未注册的电话号码.',
        });
      },
      addNewUser(){
        //加盐哈希注册，By@Fliskey
        let _this = this

        axios.get('http://localhost:8181/umLogin/getSalt').then(function (response){
          _this.umLogin.salt = response.data
          _this.umLogin.password = _this.$sha256(_this.pageData.password+_this.umLogin.salt)
          axios.post('http://localhost:8181/umLogin/addUser',_this.umLogin).then(function (response){
            _this.umLogin.id = response.data
            _this.umUser.id = _this.umLogin.id
            _this.umUser.name = _this.pageData.name
            _this.umUser.telNumber = _this.pageData.telNumber
            _this.umUser.email = _this.pageData.email
            axios.post('http://localhost:8181/umUser/add',_this.umUser).then(function (response) {
              if(response.data){
                alert('注册成功！')
                _this.$router.push({
                  path: '/login'
                })
              }
              else
                alert('注册失败！')
            }).catch(function (error){
                notification.open({
                  message: '注册失败！',
                  description: '该手机号码可能已经被注册，请更换一个！',
                  icon: <a-icon type="close-circle" style="color:red"/>
                })
                console.log(error.response.status)
              })
          })
        })
      },
      change (type) {
        this.$emit('changeType', type)
      }
    }
  }
</script>
