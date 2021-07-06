<template>
  <el-container>
    <el-header>
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
        <el-menu-item index="6" >登录</el-menu-item>
      </el-menu>
    </el-header>
    <el-main>
      <el-carousel :interval="4000" type="card" height="200px">
        <el-carousel-item v-for="item in 6" :key="item">
          <h3 class="medium">{{ item }}</h3>
        </el-carousel-item>
      </el-carousel>
      <!--卡片-->

      <el-row>
        <el-col :span="8" v-for="(o, index) in 3" :key="o" :offset="index > 0 ? 3 : 0">
          <el-card :body-style="{ padding: '0px' }">
            <img src="https://shadow.elemecdn.com/app/element/hamburger.9cf7b091-55e9-11e9-a976-7f4d0b07eef6.png" class="image">
            <div style="padding: 14px;">
              <span>好吃的汉堡</span>
              <div class="bottom clearfix">
                <time class="time">{{ currentDate }}</time>
                <el-button type="text" class="button">操作按钮</el-button>
              </div>
            </div>
          </el-card>
        </el-col>
      </el-row>
      <!--图片-->
      <div class="demo-image">
        <div class="block" v-for="fit in fits" :key="fit">
          <span class="demonstration">{{ fit }}</span>
          <el-image
              style="width: 100px; height: 100px"
              :src="url"
              :fit="fit"></el-image>
        </div>
      </div>
      <!--图片-->
      <div class="users">
        <div v-for="item in users" :key="item" class="item">
          <img :src="item" alt="">
        </div>
      </div>
    </el-main>
  </el-container>

</template>

<script>
export default {
  name: "Dawenhao",
  data() {
    return {
      fits: ['fill', 'contain', 'cover', 'none', 'scale-down'],
      url: 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg',
      activeIndex: '1',
      activeIndex2: '2',
      activeIndex3: '3',
      activeIndex4: '4',
      activeIndex41: '41',
      activeIndex42: '42',
      activeIndex5: '5',
      activeIndex6:'6',
      circleUrl: "public/imag/touxiang.png",
      sizeList: ["large", "medium", "small"],
      users: [
        '/imag/xiaohui.jpg',
        '/imag/xiaohui.jpg',
        '/imag/xiaohui.jpg'
      ]
    }
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
  margin-left:-900px;/*左边距*/
  background-size:100% 100%;

}

.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 200px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n+1) {
  background-color: #d3dce6;
}

</style>
