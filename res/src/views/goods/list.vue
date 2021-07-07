<template>
  <div>
    <!-- <search-form /> -->
    <a-card :bordered="false">
      <a-row type='flex' align='middle' justify='space-between'>
        <a-input-search class="search-ipt" style="width: 522px" placeholder="请输入..." size="large" enterButton="搜索" />
        <a href='/#/admin/goods/public'>立即发布闲置</a>
      </a-row>
      <a-list itemLayout="vertical">
        <a-list-item :key="n" v-for="n in 10" @click='jumpTo'>
          <a-list-item-meta>
            <div slot="title">二手书籍转卖&nbsp;&nbsp;&nbsp;&nbsp;￥123.45</div>
            <div slot="description">
              <a-tag >书籍</a-tag>
              <a-tag >全新</a-tag>
              <a-tag >自提</a-tag>
            </div>
          </a-list-item-meta>
          <div class="content">
            <a-row class="detail" type='flex'>
              <img width='100px' height='120px' src='/img/1.jpg'>
              <p>经典全集正统油画技法入门教程临摹教材书籍新手零基础自学手绘人物静物风景画册杨建飞主编经典全集正统油画技法入门教程临摹教材书籍新手零基础自学手绘人物静物风景画册杨建飞主编经典全集正统油画技法入门教程临摹教材书籍新手零基础自学手绘人物静物风景画册杨建飞主编经典全集正统油画技法籍新手零基础自学手绘人物静物风景画册杨建飞主编经典全集正统油画技法籍新手零基础自学手绘人物静物风景画册杨建飞主编经典全集正统油画技法籍新手零基础自学手绘人物静物风景画册杨建飞主编经典全集正统油画技法</p>
            </a-row>
            <div class="author">
              <a-avatar size="small" src="/img/user.png" />
              <a>于丽丽</a>发布于
              <em>2018-08-05 22:23</em>
            </div>
          </div>
          <span slot="actions"><a-icon style="margin-right: 8px" type="eye" />1563</span>
          <span slot="actions"><a-icon style="margin-right: 8px" type="star" />112</span>
          <span slot="actions"><a-icon style="margin-right: 8px" type="message" />4</span>
        </a-list-item>
      </a-list>
    </a-card>
    <div>
      <a-pagination
        show-size-changer
        :default-current="3"
        :total="5"
        :hideOnSinglePage="true"
        @showSizeChange="onShowSizeChange"
      />
    </div>
  </div>
</template>

<script>
  export default {
    name: 'ArticleList',
    mounted () {
      this.getList()
    },
    data() {
      return {
        pageSize: 5,
        current: 4,
      };
    },
    watch: {
      pageSize(val) {
        console.log('pageSize', val);
      },
      current(val) {
        console.log('current', val);
      },
    },
    methods: {
      getList () {
        this.http.get('http://localhost:8181/gmGoods/list').then(res => {
          console.log(res)
        })
      },
      onShowSizeChange(current, pageSize) {
        console.log(current, pageSize);
      },
      jumpTo () {
        this.$router.push({
          path: '/admin/goods/detail'
        })
      }
    }
  }
</script>

<style lang="less" scoped>
  .extra{
    width: 272px;
    height: 1px;
  }
  .content {
    .detail {
      line-height: 22px;
      max-width: 720px;
      flex-wrap:nowrap;
      > img{
        flex: 1;
        margin-right: 15px;
      }
      > p{
        max-height:110px;
        overflow: hidden;
        text-overflow: ellipsis;
        cursor:pointer;
        display: -webkit-box;
        -webkit-line-clamp: 5;
        -webkit-box-orient: vertical;
      }
    }
    .author {
      color: #999;
      margin-top: 16px;
      line-height: 22px;
      & > :global(.ant-avatar) {
          vertical-align: top;
          margin-right: 8px;
          width: 20px;
          height: 20px;
          position: relative;
          top: 1px;
        }
      & > a{
        padding:0 10px;
      }
      & > em {
          color: #666;
          font-style: normal;
        }
    }
  }
</style>
