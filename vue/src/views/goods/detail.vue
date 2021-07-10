<template>
  <div :data-source="gooditem" :loading="loading">
    <div class="detail">
      <div class="main">
        <a-row type='flex' justify='space-between'>
          <h1 class='title'>单号：{{id}}</h1>
          <div class='action'>
            <!--            <a-button-group style="margin-right: 8px;">-->
            <!--              <a-button><a-icon type="star" />收藏</a-button>-->
            <!--            </a-button-group>-->
            <a-popover placement="bottom" @click="insertToLike" style="margin-right: 8px">
              <template slot="content">
                <p>Content</p>
                <p>Content</p>
              </template>
              <template slot="title">
                <span>Title</span>
              </template>
              <a-button><a-icon type="star" />收藏</a-button>
            </a-popover>
            <a-button type="primary" ><a-icon type="shopping" />立即购买</a-button>
          </div>
        </a-row>
        <div class="detail-list">
          <a-row class='detail-item'>
            <a-col :span='12'><span>创建人：</span>{{gooditem.userId}}</a-col>
            <a-col :span='12'><span>创建时间：</span>{{releasetime}}</a-col>
          </a-row>
          <a-row class='detail-item'>
            <a-col :span='12'><span>商品标题：</span>{{gooditem.name}}</a-col>
            <a-col :span='12'><span>物品类型：</span>{{gooditem.type}}</a-col>
          </a-row>
          <a-row class='detail-item'>
            <a-col :span='24'><span>备注：</span>{{gooditem.description}}</a-col>
          </a-row>
        </div>
        <a-row type='flex'>
          <div class="head-info">
            <span>状态</span>
            <p style='color:#faad14'>在售</p>
          </div>
          <div class="head-info">
            <span>单价</span>
            <p>￥{{gooditem.price}}</p>
          </div>
          <div class="head-info">
            <span>数量</span>
            <p>{{gooditem.quantity}}</p>
          </div>
        </a-row>
      </div>
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
  },
  methods:{
    getParams(){
      //取到路由传过来的参数
      //将数据放在当前组件的数据内
      this.id = this.$route.params.id
    },
    insertToLike(){
      var mylike = {id:0,userId:this.pageUserId,goodsId:this.id}
      alert("您已预定了"+mylike.goodsId)
      axios.post('http://localhost:8181/wantGoods/add',mylike).then(function (response){
        if (response.data){
          alert("完成预定")
        }
        else
          alert("收藏失败！")
      })
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
}
</style>
