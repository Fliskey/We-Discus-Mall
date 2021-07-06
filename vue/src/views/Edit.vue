<template>
  <el-form ref="form" :rules="rules" :model="UmUser" label-width="80px" style="width: 600px">
    <el-form-item label="编号">
      <el-input v-model="UmUser.id" readonly></el-input>
    </el-form-item>
    <el-form-item label="姓名" prop="name">
      <el-input v-model="UmUser.name"></el-input>
    </el-form-item>
    <el-form-item label="密码" prop="password">
      <el-input v-model.number="UmUser.password"></el-input>
    </el-form-item>
    <el-form-item label="电话" prop="telNumber">
      <el-input v-model="UmUser.telNumber"></el-input>
    </el-form-item>
    <el-form-item label="邮箱" prop="email">
      <el-input v-model="UmUser.email"></el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="onSubmit('form')">立即修改</el-button>
      <el-button>取消</el-button>
    </el-form-item>
  </el-form>
</template>

<script>
export default {
  name: "Edit",
  data() {
    return {
      UmUser: {
        id: this.$route.query.id,
        name: '',
        password: '',
        telNumber: '',
        email: ''
      },
      rules:{
        name:[
          { required: true, message: '请输入用户名称', trigger: 'blur' }
        ],
        password:[
          { required: true, message: '请输入密码', trigger: 'blur' }
        ],
        telNumber:[
          { required: true, message: '请输入电话', trigger: 'blur' }
        ],
        email:[
          { required: true, message: '请输入邮箱', trigger: 'blur' }
        ]
      }
    }
  },
  created() {
    let id = this.$route.query.id
    let _this = this
    axios.get('http://localhost:8181/umUser/find/'+id).then(function (response) {
      _this.umUser = response.data
    })
  },
  methods: {
    onSubmit(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          let _this = this
          axios.put('http://localhost:8181/umUser/update/',this.umUser).then(function (response) {
            if(response.data){
              _this.$alert(_this.umUser.name+'修改成功！', '修改数据', {
                confirmButtonText: '确定',
                callback: action => {
                  //跳转到/table
                  _this.$router.push('/table')
                }
              });
            }
          })
        }
      });
    }
  }
}
</script>

<style scoped>

</style>
