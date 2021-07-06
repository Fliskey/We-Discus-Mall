<template>
  <div
      class="main-ruleForm">
    <el-menu
        :default-active="activeIndex41"
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
    <el-row class="demo-avatar demo-basic"
            style="left: 14cm; top: 1.5cm">
      <el-col :span="12">
        <div class="demo-basic--circle">
          <div class="block"><el-avatar
              :size="100" :src="circleUrl"></el-avatar></div>
          <div class="block" v-for="size in sizeList" :key="size">
            <el-avatar :size="size" :src="circleUrl"></el-avatar>
          </div>
        </div>
      </el-col>
    </el-row>
    <el-input
        style="width: 12cm; left: 10cm; top: 2cm"
        placeholder="编号"
        v-model="input"
        :disabled="true">
    </el-input>
    <el-input
        style="width: 12cm; left: -2cm; top: 4cm"
        placeholder="姓名"
        v-model="input"
        :disabled="true">
    </el-input>
    <el-input
        style="width: 12cm; left: 10cm; top: 5cm"
        placeholder="电话"
        v-model="input"
        :disabled="true">
    </el-input>
    <el-input
        style="width: 12cm; left: -2cm; top: 7cm"
        placeholder="邮箱"
        v-model="input"
        :disabled="true">
    </el-input>
    <el-row
        style="left: 13cm;top: 7.3cm">
      <el-button
          type="primary" round>修改信息</el-button>
    </el-row>

  </div>
</template>


<script>
export default {
  data () {
    return {
      circleUrl: "public/imag/touxiang.png",
      sizeList: ["large", "medium", "small"]
    }
  }
}
</script>

<style>

</style>



<script>
export default {
  data() {
    return {
      circleUrl: 'public/imag/touxiang.png',
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
