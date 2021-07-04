
<template>
  <el-table
      :data="tableData"
      border
      style="width: 100%">
    <el-table-column
        fixed
        prop="id"
        label="编号"
        width="120">
    </el-table-column>
    <el-table-column
        prop="name"
        label="姓名"
        width="120">
    </el-table-column>
    <el-table-column
        prop="password"
        label="密码"
        width="120">
    </el-table-column>
    <el-table-column
        prop="telNumber"
        label="电话"
        width="120">
    </el-table-column>
    <el-table-column
        prop="email"
        label="邮箱"
        width="200">
    </el-table-column>
    <el-table-column
        label="操作"
        width="100">
      <template #default="scope">
        <el-button @click="userDelete(scope.row)" type="text" size="small">删除</el-button>
        <el-button @click="findUser(scope.row)" type="text" size="small">编辑</el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
export default {
  methods: {
    findUser(object)
    {
        this.$router.push('/edit?id=' +object.id)
    },
    userDelete(object)
    {
      let _this = this

      this.$confirm('是否确定要删除'+object.name+'?','删除数据',{
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
      }).then(()=>{
        axios.delete('http://localhost:8181/umUser/delete/'+object.id).then(function(response) {
          if(response.data)
          {
            _this.$alert(object.name+'删除成功！','删除数据',{
              confirmButtonText: '确定',
              callback: action => {
                location.reload()
              }
            });
          }
      })
      }).catch(()=>{

      });
    }
  },
  created()
  {
      let _this = this
      axios.get('http://localhost:8181/umUser/list').then(function(response){
          _this.tableData = response.data
      })
  },
  data() {
    return {
      tableData: null
    }
  }
}
</script>

<style>

</style>
