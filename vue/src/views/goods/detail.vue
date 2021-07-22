<template>
  <div :data-source="gooditem" :loading="loading">
    <div class="detail">
      <a-page-header :title="gooditem.name" :sub-title="'商品单号'+id" @back="() => $router.go(-1)" >
        <template slot="tags">
          <a-tag color="blue">
            {{gooditem.type}}
          </a-tag>
        </template>
        <template slot="extra">

          <a-button  @click='insertToLike' >
            <a-icon type="star" />
            预定
          </a-button>
          <a-popover placement="bottom" trigger="click">
            <template slot="content">
              <p>单号：{{gooditem.id}}</p>
              <p>商品：{{gooditem.name}}</p>
            </template>
            <template slot="title">
              <span>该商品已加入购物车</span>
            </template>
            <a-button type="primary" key="1" @click="insertTrolley">
              <a-icon type="shopping" />
              加入购物车
            </a-button>
          </a-popover>
          <a-button type="primary" @click='buyNow(gooditem.id)' >
            <a-icon type="shopping" />
            立即购买
          </a-button>

        </template>
        <div class="content">
          <div class="main">
            <a-descriptions size="small" :column="2">
              <a-descriptions-item label="创建人">
                {{ this.userName }}
              </a-descriptions-item>
              <a-descriptions-item label="创建时间">
                <!--                <a>421421</a>-->
              </a-descriptions-item>
              <a-descriptions-item label="商品标题">
                {{gooditem.name}}
              </a-descriptions-item>
              <a-descriptions-item label="商品类型">
                {{gooditem.type}}
              </a-descriptions-item>
              <a-descriptions-item label="备注">
                {{gooditem.description}}
              </a-descriptions-item>
            </a-descriptions>
          </div>
          <div class="extra">
            <div
              :style="{
              display: 'flex',
              width: 'max-content',
              justifyContent: 'flex-end',
            }"
            >
              <a-statistic
                title="状态"
                value="在售"
                :style="{
                marginRight: '64px'
              }"
                valueStyle="color:#faad14"
              />
              <a-statistic title="单价" prefix="￥" :style="{marginRight: '64px'}" :value="gooditem.price" />
              <a-statistic title="数量" prefix="" :style="{marginRight: '64px'}" :value="gooditem.quantity" />
              <a-statistic title="预订数量" :value="likeQuantity" style="margin-right: 50px">
                <template #suffix>
                  <a-icon type="like" />
                </template>
              </a-statistic>
            </div>
          </div>
        </div>
      </a-page-header>
    </div>
    <a-row  class='img-box' type='flex'>
      <span>商品图片：</span>
      <a-row class='img' :gutter='[20]'>
        <a-col :span='6'><img width="10%" height="240px" :src=gooditem.imageUrl alt="加载失败"></a-col>
        <a-col :span='6'><img width="10%" height="240px" :src=gooditem.imageUrl alt="加载失败"></a-col>
        <a-col :span='6'><img width="10%" height="240px" :src=gooditem.imageUrl alt="加载失败"></a-col>
        <a-col :span='6'><img width="10%" height="240px" :src=gooditem.imageUrl alt="加载失败"></a-col>
      </a-row>
    </a-row>
  </div>
</template>

<script>
export default {
  name:'',
  data(){
    return {
      pageUserId: '',
      id:'',
      gooditem:{
        id: '',
        userId:'',
        name:'',
        type:'',
        quantity:'',
        price:'',
        imageUrl:'',
        description:''
      },
      likeQuantity:'',
      isSame:0,
    }
  },
  mounted() {
    if(this.$cookies.isKey('vid') === false)
      this.$router.push('login')
    this.pageUserId = this.$cookies.get('vid')
    this.getParams()
    let Gid = this.$route.params.id
    let _this = this
    axios.get('http://localhost:8181/gmGoods/find/'+Gid).then(function (response){
      console.log(response)
      _this.gooditem = response.data
      // alert("正确的"+response.data.userId)
    })
    axios.get('http://localhost:8181/wantGoods/countLike/'+Gid).then(function (response){
      console.log("测试预定数量接口")
      console.log(response)
      _this.likeQuantity = response.data
    })

  },
  methods:{
    insertTrolley(){
      var myTrolley = {
        id:0,
        userId:this.pageUserId,
        goodsId:this.id,
        quantity:1
      }
      axios.post('http://localhost:8181/purchaseGoods/add',myTrolley).then(function (response){

      })
    },
    getParams(){
      //取到路由传过来的参数
      //将数据放在当前组件的数据内
      this.id = this.$route.params.id
    },
    insertToLike(){
      console.log("当前浏览者的id是  "+this.pageUserId);
      console.log("该商品发布者的id是  "+this.gooditem.userId);
      if(this.pageUserId==this.gooditem.userId){
        console.log("我预定我自己：error！")
        alert("这个是您自己发布的商品哦，不可以预定哦~")
        this.isSame = 1

        return;
      }
      var mylike = {id:0,userId:this.pageUserId,goodsId:this.id}
      //assert.notEqual(this.pageUserId, this.gooditem.userId, '预期二者不相等')
      // alert("您已预定了"+mylike.goodsId)
      axios.post('http://localhost:8181/wantGoods/add',mylike).then(function (response){
         if (response.data){
           alert("完成预定")
         }
         else
           alert("您已经预定了该商品！")
      })
    },
    buyNow(gid)
    {
      this.$router.push('/visitor/goods/purchase/'+gid)
    }

  },

  watch:{
    //检测路由变化，只要变化了就调用获取路由组件参数方法将数据存储到本组件即可
    '$route':'getParams'
  }

}
</script>

<style lang="less" scoped>
.img-box{
  flex-wrap:nowrap;
  .img{
    flex:1;
  }
  img{
    width:100%;
  }
}
.detail{
  border-bottom: 1px solid #e8e8e8;
  padding-bottom:20px;
  margin-bottom:30px;
  .title{
    font-size: 20px;
    margin: 0 0 16px;
  }
  &-item{
    padding-bottom: 8px;
    color:#999;
    line-height: 20px;
    span{
      color:#333;
    }
  }

  .avatar {
    margin:0 24px 0 0;
  }
  .main{
    width: 100%;
    .content{
      display: flex;
      flex-wrap: wrap;
      color: #666;
    }
    .extra{
      display: flex;
    }
  }
}
.head-info{
  text-align: center;
  padding: 0 24px;
  align-self: center;
  span{
    color: #666;
    display: inline-block;
    font-size: 14px;
    margin-bottom: 4px;
  }
  p{
    color: #333;
    font-size: 24px;
    margin: 0;
  }
  tr:last-child td {
    padding-bottom: 0;
  }
  #components-page-header-demo-responsive .content {
    display: flex;
  }
  #components-page-header-demo-responsive .ant-statistic-content {
    font-size: 20px;
    line-height: 28px;
  }
  @media (max-width: 576px) {
    #components-page-header-demo-responsive .content {
      display: block;
    }

    #components-page-header-demo-responsive .main {
      width: 100%;
      margin-bottom: 12px;
    }

    #components-page-header-demo-responsive .extra {
      width: 100%;
      margin-left: 0;
      text-align: left;
    }
  }
}
</style>
