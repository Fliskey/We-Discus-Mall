<template>
  <div>
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
        <a-col  :md="8" :sm="24">
          <a-form-item style='padding-left:30px'>
            <a-button type="primary" @click='filterData'>检索</a-button>
          </a-form-item>
        </a-col>
      </a-row>
    </a-form>
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
    dataIndex: 'id',
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
    return {
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
    this.fetch()
  },
  methods: {
    find (index) {
      this.$router.push('/admin/user/editMessage?id=' +this.data[index].id)
      //this.$refs['modal'].show()
    },
    fetch () {
      this.loading = true
      this.http.get('http://localhost:8181/umUser/list').then(res => {
        this.loading = false
        this.data = res.data
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
