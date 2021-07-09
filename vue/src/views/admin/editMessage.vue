<template>
  <a-form :form="form" :model="UmUser" :label-col="{ span: 5 }" :wrapper-col="{ span: 12 }" @submit="handleSubmit">
    <a-form-item label="编号">
      <a-input
        v-decorator="['id', { rules: [{ required: true, message: 'Please input your note!' }] }]"
        placeholder="请输入编号"
        v-model='UmUser.id'
        readOnly
      />
    </a-form-item>
    <a-form-item label="姓名">
      <a-input
        v-decorator="['name', { rules: [{ required: true, message: 'Please input your note!' }] }]"
        placeholder="请输入姓名"
        v-model='UmUser.name'
      />
    </a-form-item>
    <a-form-item label="电话">
      <a-input
        v-decorator="['telNumber', { rules: [{ required: true, message: 'Please input your note!' }] }]"
        placeholder="请输入电话"
        v-model='UmUser.telNumber'
      />
    </a-form-item>
    <a-form-item label="邮箱">
      <a-input
        v-decorator="['email', { rules: [{ required: true, message: 'Please input your note!' }] }]"
        placeholder="请输入邮箱"
        v-model='UmUser.email'
      />
    </a-form-item>

    <a-form-item :wrapper-col="{ span: 12, offset: 5 }">
      <a-button type="primary" html-type="submit">
        立即修改
      </a-button>
    </a-form-item>
  </a-form>
</template>

<script>
export default {
  data() {
    return {
      formLayout: 'horizontal',
      UmUser: {
        id: '',
        name: '',
        telNumber: '',
        email: ''
      }
    };
  },
  mounted() {
    if(this.$cookies.isKey('aid') === false)
      this.$router.push('login')
    let id = this.$route.query.id
    let _this = this
    axios.get('http://localhost:8181/umUser/find/'+id).then(function (response) {
      _this.UmUser = response.data
    })
  },
  methods: {
    handleSubmit(e) {
          let _this = this
          axios.put('http://localhost:8181/umUser/update/',this.UmUser).then(function (response) {
            if(response.data){
              alert('修改成功！')
              _this.$router.push({
                path: '/admin/list'
              })
            }
          })

    },
  },
};
</script>
