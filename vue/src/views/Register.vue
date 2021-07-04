<template>
    <el-form ref="userRules" :model="umUser" :rules="rules" label-width="80px" style="width: 600px">
        <el-form-item label="编号" prop="id">
            <el-input v-model="umUser.id"></el-input>
        </el-form-item>
        <el-form-item label="姓名" prop="name">
            <el-input v-model.number="umUser.name"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
            <el-input v-model="umUser.password"></el-input>
        </el-form-item>
      <el-form-item label="电话" prop="telNumber">
        <el-input v-model="umUser.telNumber"></el-input>
      </el-form-item>
      <el-form-item label="邮箱" prop="email">
        <el-input v-model="umUser.email"></el-input>
      </el-form-item>
        <el-form-item>
            <el-button type="primary" @click="onSubmit('userRules')">立即注册</el-button>
            <el-button>取消</el-button>
        </el-form-item>
    </el-form>
</template>

<script>
    export default {
        name: "Add",
        data() {
            return {
                umUser: {
                    id: '',
                    name: '',
                    password: '',
                    telNumber: '',
                    email: ''
                },
                rules:{
                    id:[
                        { required: true, message: '请输入编号', trigger: 'blur' }
                    ],
                    name:[
                      { required: true, message: '请输入姓名', trigger: 'blur' }
                    ],
                    password:[
                        { required: true, message: '请输入密码', trigger: 'blur' }

                    ],
                    terNumber:[
                        { required: true, message: '请输入电话', trigger: 'blur' }
                    ],
                    email:[
                      { required: true, message: '请输入邮箱', trigger: 'blur' }
                    ]
                }
            }
        },
        methods: {
            onSubmit(formName){
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        let _this = this
                        axios.post('http://localhost:8181/umUser/add',this.umUser).then(function (response) {
                            if(response.data){

                                _this.$alert(_this.umUser.name+'添加成功！', '添加数据', {
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