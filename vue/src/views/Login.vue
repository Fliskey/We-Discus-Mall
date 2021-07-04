
<template>
  <div
    class="demo-ruleForm">
  <el-form ref="form" :model="form" class="login" label-width="50px" style="width: 400px" label-length="15">
    <el-header height="60px">
      <h2>账号登录</h2>
    </el-header>
    <el-form-item label="账号">
      <el-input v-model="form.name"></el-input>
    </el-form-item>
    <el-form-item label="密码">
      <el-input v-model="form.password"></el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="userLogin('numberValidateForm')">登录</el-button>
    </el-form-item>
    <el-button type="text" @click="userRegister('numberValidateForm')">立即注册</el-button>
  </el-form>
  </div>

</template>


<script>
export default {
  data() {
    return {
      form: {
        name: '',
        password: ''
      }
    }
  },
  methods: {
    userRegister() {
      this.$router.push('/register')
    },
    userLogin () {
      // 登录前校验
        // 发起登录请求
        let _this = this
        axios.put('http://localhost:8181/umUser/login/',this.form).then(function (response){
          if(!response.data)
            _this.$alert('用户名或密码错误，请重新输入！')
          else
            _this.$alert(_this.form.name+'登录成功！', {
              confirmButtonText: '确定',
              callback: action => {
                //跳转到/table
                _this.$router.push('/table')
              }
            })
        })
        //await this.$router.push('/table')
    },
  }
}







</script>

<style>

.demo-ruleForm{
  background:url("../../public/imag/bg.jpg");
  width: 100%;
  height: 100%;
  position:fixed;
  margin-top: -10px;/*上边距*/
  margin-left:-400px;/*左边距*/
  background-size:100% 100%;

}


.login{
  border: 1px solid #DCDFE6;
  width: 350px;
  margin: 180px auto;
  padding: 35px 120px 15px 25px;
  border-radius: 5px;
  -webkit-border-radius: 5px;
  -moz-border-radius: 5px;
  box-shadow: 0 0 25px #909399;
  background-image: url("../../public/imag/bg1.jpg");
  width: 200%;
  height: 50%;
  position: center;
  background-size:100% 150%;

}
</style>

</script>

<style>

</style>
