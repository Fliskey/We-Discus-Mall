<template>
  <div>
    <div class="detail">
      <div class="main">
        <a-row type='flex' justify='space-between'>
          <h1 class='title'>单号：234231029431</h1>
          <div class='action'>
            <a-button-group style="margin-right: 8px;">
            <a-button><a-icon type="star" />收藏</a-button>
            </a-button-group>
            <a-button type="primary" ><a-icon type="shopping" />立即购买</a-button>
          </div>
        </a-row>
        <div class="detail-list">
          <a-row class='detail-item'>
            <a-col :span='12'><span>创建人：</span>于丽丽</a-col>
            <a-col :span='12'><span>创建时间：</span>2021-03-12</a-col>
          </a-row>
          <a-row class='detail-item'>
            <a-col :span='12'><span>商品标题：</span>二手书籍转卖</a-col>
            <a-col :span='12'><span>物品类型：</span>书籍</a-col>
          </a-row>
          <a-row class='detail-item'>
            <a-col :span='24'><span>备注：</span>二手书籍转卖</a-col>
          </a-row>
        </div>
        <a-row type='flex'>
          <div class="head-info">
            <span>状态</span>
            <p style='color:#faad14'>在售</p>
          </div>
          <div class="head-info">
            <span>单价</span>
            <p>￥123.45</p>
          </div>
          <div class="head-info">
            <span>数量</span>
            <p>12</p>
          </div>
        </a-row>
      </div>
    </div>
    <a-row  class='img-box' type='flex'>
      <span>商品图片：</span>
      <a-row class='img' :gutter='[20]'>
        <a-col :span='6'><img src='/img/1.jpg'></a-col>
        <a-col :span='6'><img src='/img/1.jpg'></a-col>
        <a-col :span='6'><img src='/img/1.jpg'></a-col>
        <a-col :span='6'><img src='/img/1.jpg'></a-col>
      </a-row>
    </a-row>
  </div>
</template>

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
