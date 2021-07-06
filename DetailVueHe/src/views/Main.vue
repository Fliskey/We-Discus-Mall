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
      tableData: [{
        id: '1',
        name: '苹果',
        type: '水果',
        quantity: '3',
        price: '4',
        icon: 'https://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=%E8%8B%B9%E6%9E%9C&step_word=&hs=0&pn=3&spn=0&di=2970&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&istype=2&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=-1&cs=3270113679%2C1267106557&os=3200358449%2C594920463&simid=3553006736%2C283098130&adpicid=0&lpn=0&ln=1679&fr=&fmq=1625474201513_R&fm=result&ic=&s=undefined&hd=&latest=&copyright=&se=&sme=&tab=0&width=&height=&face=undefined&ist=&jit=&cg=&bdtype=0&oriquery=&objurl=https%3A%2F%2Fgimg2.baidu.com%2Fimage_search%2Fsrc%3Dhttp%3A%2F%2Fimg1.gtimg.com%2Fln%2Fpics%2Fhv1%2F32%2F34%2F2073%2F134805527.jpg%26refer%3Dhttp%3A%2F%2Fimg1.gtimg.com%26app%3D2002%26size%3Df9999%2C10000%26q%3Da80%26n%3D0%26g%3D0n%26fmt%3Djpeg%3Fsec%3D1628066204%26t%3D5c978442c6c2453dd14532ec5715a163&fromurl=ippr_z2C%24qAzdH3FAzdH3Fsg_z%26e3Bqq_z%26e3Bv54AzdH3Fzpda8mAzdH3Fv32v45ktsjAzdH3Fdm_z%26e3Bip4&gsm=3&rpstart=0&rpnum=0&islist=&querylist=&nojc=undefined'
      }, {
        id: '2',
        name: '手机',
        type: '电子产品',
        quantity: '1',
        price: '333',
      }, {
        id: '3',
        name: '雨伞',
        type: '生活用品',
        quantity: '1',
        price: '10',
      },{
        id: '4',
        name: '鼠标',
        type: '电子产品',
        quantity: '1',
        price: '20',
      }],
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
