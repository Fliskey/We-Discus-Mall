<template>
  <div>
    <!-- <search-form /> -->
    <a-card :bordered="false"
            :data-source="data">
      <a-row type='flex' align='middle' justify='space-between'>
        <a-input-search class="search-ipt" style="width: 522px" placeholder="请输入..." size="large" enterButton="搜索" />
        <a href='/#/admin/goods/public'>立即发布闲置</a>
      </a-row>
      <a-list itemLayout="vertical" >
        <a-list-item v-for="(v,n) in data" :key="v.id">
          <router-link :to="'/visitor/goods/detail/'+v.id">
            <a-list-item-meta >
              <div slot="title">商品名：{{v.name}}&nbsp;&nbsp;&nbsp;&nbsp;价格：￥{{v.price}}</div>
              <div slot="description">
                <a-tag >
                  {{v.type}}
                </a-tag>
                <a-tag >全新</a-tag>
                <a-tag >自提</a-tag>
              </div>
            </a-list-item-meta>
          </router-link>
          <div class="content">
            <a-row class="detail" type='flex' style="width: 4cm; height: 4cm">
              <img width='10%' height='120px' :src=v.imageUrl>
            </a-row>
            <p>{{v.description}}</p>
            <div class="author">
              <a-avatar size="small" src="/img/user.png" />
              <a>于丽丽</a>发布于
              <em>2018-08-05 22:23</em>
            </div>
          </div>
          <span slot="actions"><a-icon style="margin-right: 8px" type="eye" />1563</span>
          <span slot="actions"><a-icon style="margin-right: 8px" type="star" />112</span>
          <span slot="actions"><a-icon style="margin-right: 8px" type="message" />4</span>
          {{v.id}}
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
    //this.num = this.data.length
  },
  data() {
    return {
      data: [],
      index: 0,
      num: 10,
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
      this.loading = true
      this.http.get('http://localhost:8181/gmGoods/list').then(res => {
        console.log(res)
        this.loading = false
        this.data = res.data
      })
    },
    onShowSizeChange(current, pageSize) {
      console.log(current, pageSize);
    },
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
