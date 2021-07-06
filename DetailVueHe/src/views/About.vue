<template>
  <div
      class="main-ruleForm">
    <el-menu
        :default-active="activeIndex"
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
      <el-menu-item index="5" >用户管理</el-menu-item>
    </el-menu>
    <el-table
        :data="tableData"
        style="width: 100%; height: 100%"
        height="250">
      <el-table-column
          prop="icon"
          label="图片"
          width="150">
        <template slot-scope="scope">
          <img :src="scope.row.icon" />
        </template>
      </el-table-column>
      <el-table-column
          fixed
          prop="id"
          label="商品编号"
          width="150">
      </el-table-column>
      <el-table-column
          prop="name"
          label="商品名称"
          width="150">
      </el-table-column>
      <el-table-column
          prop="type"
          label="商品类别"
          width="150">
      </el-table-column>
      <el-table-column
          prop="quantity"
          label="数量"
          width="150">
      </el-table-column>
      <el-table-column
          prop="price"
          label="商品价格"
          width="150">
      </el-table-column>
    </el-table>
  </div>
</template>


<script>
export default {
  data() {
    return {
      activeIndex: '1',
      activeIndex2: '2',
      activeIndex3: '3',
      activeIndex4: '4',
      activeIndex41: '41',
      activeIndex42: '42',
      activeIndex5: '5',

    };
  },
  methods: {
    handleSelect(key, keyPath) {
      if(key==5)
        this.$router.push('/table')
      else if(key==41)
        this.$router.push('/message')
      else if(key==1)
        this.$router.push('/main')
    }
  }
}
</script>

<style>
.main-ruleForm{
  width: 100%;
  height: 100%;
  position:fixed;
  margin-top: -10px;/*上边距*/
  margin-left:-400px;/*左边距*/
  background-size:100% 100%;

}
</style>
