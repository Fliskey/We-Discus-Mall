
<template>
  <div
      class="main-ruleForm">
    <el-menu
        :default-active="activeIndex5"
        class="el-menu-demo"
        mode="horizontal"
        @select="handleSelect"
        background-color="#545c64"
        text-color="#fff"
        active-text-color="#ffd04b">
      <el-menu-item index="1">商品中心</el-menu-item>
      <el-menu-item index="2">订单管理</el-menu-item>
      <el-menu-item index="3" >消息中心</el-menu-item>
      <el-submenu index="4">
        <template #title>个人中心</template>
        <el-menu-item index="41">个人信息</el-menu-item>
        <el-menu-item index="42">修改密码</el-menu-item>
      </el-submenu>
      <el-menu-item index="5" ><a href="http://localhost:8080/table" >用户管理</a></el-menu-item>

    </el-menu>
  <el-table
      :data="tableData"
      border
      style="width: 200%;left: 0.0cm">
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
        width="110">
      <template #default="scope">
        <el-button @click="userDelete(scope.row)" type="text" size="small">删除</el-button>
        <el-button @click="findUser(scope.row)" type="text" size="small">编辑</el-button>
      </template>
    </el-table-column>
  </el-table>
  </div>
</template>

<script>
export default {
  methods: {
    findUser(object)
    {
        this.$router.push('/edit?id=' +object.id)
    },
    handleSelect(key, keyPath) {
      if(key==5)
        this.$router.push('/table')
      else if(key==41)
        this.$router.push('/message')
      else if(key==1)
        this.$router.push('/main')


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
      activeIndex: '1',
      activeIndex2: '2',
      activeIndex3: '3',
      activeIndex4: '4',
      activeIndex41: '41',
      activeIndex42: '42',
      activeIndex5: '5',
      tableData: null
    }
  }
}
</script>

<style>

</style>
