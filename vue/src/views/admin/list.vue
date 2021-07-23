<template>
  <div>
    <a-descriptions bordered :size="size">
      <a-descriptions-item label="管理员编号">
        {{this.admin.id}}
      </a-descriptions-item>
      <a-descriptions-item label="姓名">
        {{this.admin.name}}
      </a-descriptions-item>
      <a-descriptions-item label="手机号">
        {{this.admin.telNumber}}
      </a-descriptions-item>
    </a-descriptions>
    <br>
    <a-form layout="horizontal">
      <a-row >
        <a-col :md="8" :sm="24" >
          <a-form-item
            label="姓名"
            :labelCol="{span: 5}"
            :wrapperCol="{span: 18, offset: 1}"
          >
            <a-input placeholder="请输入姓名" v-model='search.name' />
          </a-form-item>
        </a-col>
        <a-col :md="8" :sm="24" >
          <a-form-item
            style='padding-left:30px'
            label="学号"
            :labelCol="{span: 5}"
            :wrapperCol="{span: 19}">
            <a-input v-model='search.no' placeholder="请输入学号" />
          </a-form-item>
        </a-col>
        <a-col  :md="2" :sm="24">
          <a-form-item style='padding-left:30px'>
            <a-button type="primary" @click='filterData'>检索</a-button>
          </a-form-item>
        </a-col>
        <a-col :md="2" :sm="24">
          <a-form-item style="padding-left:30px">
            <a-button type="primary" @click='showModal'>添加用户</a-button>
          </a-form-item>
        </a-col>
      </a-row>
    </a-form>
    <a-modal
      title="添加用户"
      :visible="addVisiable"
      @ok="addUser"
      @cancel="addCancel"
    >
      <a-form-model ref='umUser' :model="pageData" :rules='rules' class='login-form register'>
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
      </a-form-model>
    </a-modal>
    <a-table
      :columns="columns"
      :data-source="data"
      :loading="loading"
      row-key='no'
      bordered>
      <template slot="operation" slot-scope="text, record, index">
        <a @click='find(index)'>编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <a v-if="record.status !== 3" @click='remove(record, index)'>删除</a>
      </template>
    </a-table>
  </div>
</template>
<script>
import userModal from './editMessage'

const columns = [
  {
    title: '编号',
    customRender: (text, record, index) => `${index + 1}`,
    width: '15%'
  },
  {
    title: '姓名',
    width: '25%',
    dataIndex: 'name'
  },
  {
    title: '电话',
    dataIndex: 'telNumber',
    key: 'cardId',
    width: '25%'
  },
  {
    title: '邮箱',
    dataIndex: 'email',
    width: '25%'
  },
  {
    title: '操作',
    dataIndex: 'operation',
    width: '30%',
    scopedSlots: { customRender: 'operation' }
  }
]

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
          callback(new Error('密码格式不正确！'))
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
    return {
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
        ]
      },
      addVisiable:false,
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
      admin:{
        id:this.$cookies.get('aid'),
        name:'',
        telNumber:'',
      },
      data: [],
      pagination: {},
      loading: false,
      columns,
      show: false,
      search: {}
    }
  },
  components: {
    userModal
  },
  mounted () {
    if(this.$cookies.isKey('aid') === false)
      this.$router.push('login')
    this.fetch()
  },
  methods: {
    showModal(){
      this.addVisiable=true
    },
    addCancel(){
      this.addVisiable=false
    },
    addUser(){
      let _this = this
      axios.get('http://localhost:8181/umLogin/getSalt').then(function (response){
        _this.umLogin.salt = response.data
        _this.pageData.password = _this.$sha256(_this.pageData.password+_this.umLogin.salt)
        _this.umLogin.password = _this.pageData.password
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
          })
        })
      })
    },
    find (index) {
      this.$router.push('/admin/user/editMessage?id=' +this.data[index].id)
      //this.$refs['modal'].show()
    },
    fetch () {
      let _this = this
      this.loading = true
      this.http.get('http://localhost:8181/umUser/list').then(res => {
        this.loading = false
        this.data = res.data
      })
      axios.post('http://localhost:8181/admin/getAdmin',_this.admin.id).then(function (response){
        _this.admin = response.data
        console.log(_this.admin)
      })
    },
    filterData () {
      if (!this.search.name && !this.search.no) {
        this.$message.error('请输入姓名或学号进行查询！')
      } else {
        const data1 = this.tempData.filter(item => {
          return item.name.indexOf(this.search.name) !== -1
        })
        const data2 = this.tempData.filter(item => {
          return item.cardId.indexOf(this.search.cardId) !== -1
        })
        this.data = data1.concat(data2)
      }
    },
    remove (item, index) {
      let _this = this
      this.$confirm({
        title: '确定删除'+_this.data[index].name+'吗？',
        okText: '确定',
        okType: 'danger',
        cancelText: '取消',
        onOk() {
          axios.delete('http://localhost:8181/umUser/delete/'+_this.data[index].id).then(function(response) {
            if(response.data)
            {
              alert('删除成功！')
              location.reload()
            }
          })
        },
        onCancel() {
          console.log('Cancel');
        },
      });
      //axios.delete('http://localhost:8181/umUser/delete/'+_this.data[index].id)
      // this.$confirm({
      //   title: '确认提醒',
      //   content: '是否确定要删除'+_this.data[index].name+'?',
      //   okText: '确认',
      //   cancelText: '取消',
      // }).then(()=>{
      //   alert('开始删除！')
      //   axios.delete('http://localhost:8181/umUser/delete/'+_this.data[index].id).then(function(response) {
      //     if(response.data)
      //     {
      //       alert('删除成功！')
      //     }
      //   })
      // }).catch(()=>{
      //
      // });
    }
  }
}
</script>
