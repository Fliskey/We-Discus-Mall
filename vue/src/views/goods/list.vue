<template>
  <div>
    <!-- <search-form /> -->
    <a-card :bordered="false"
            :data-source="data">
      <a-row type='flex' align='middle' justify='space-between'>
        <a-input-search class="search-ipt" style="width: 522px" placeholder="请输入..." size="large" enterButton="搜索" />
        <a-select
          v-model="selected"
          placeholder='所有类别'
          style="width: 120px"
          :defaultActiveFirstOption='true'
          notFoundContent='下拉列表为空,检查数据库type'
          @change='selectList'
        >
          <a-select-option v-for="item in lists" :key="item.value">{{ item.label }}</a-select-option>
        </a-select>

        <a-select
          placeholder='排序'
          style="width: 120px"
          :defaultActiveFirstOption='true'
          @change="priceSort"
        >
          <a-select-option value='upSort'>
            价格升序
          </a-select-option>
          <a-select-option value='downSort'>
            价格降序
          </a-select-option>
          <a-select-option value='cancelSort'>
            默认排序
          </a-select-option>
        </a-select>


        <a href='/#/visitor/goods/public'>立即发布闲置</a>
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
    if(this.$cookies.isKey('vid') === false)
      this.$router.push('login')
    console.log(this.$cookies.get('vid'))
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

  created () {
    this.lists = [
      {
        label: '办公文具',
        value: 1
      },
      {
        label: '蔬菜水果',
        value: 2
      },
      {
        label: '电子产品',
        value: 3
      },
      {
        label: '日常杂货',
        value: 4
      },
      {
        label: '家具',
        value: 5
      },
      {
        label: '健身器材',
        value: 6
      },
      {
        label: '所有类型',
        value: 7
      }
    ]
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
    selectList(val) {
      if (val == 7) { // todo 此处没有做到对getList的复用 之后可以改进
        this.loading = true
        console.log('展示所有商品类型');
        this.http.get('http://localhost:8181/gmGoods/list').then(res => {
          console.log(res)
          this.loading = false
          this.data = res.data
          return;
        })
      } else {
        this.loading = true
        val = val - 1  //保留
        this.http.get(`http://localhost:8181/gmGoods/queryGoodsByType/${this.lists[val].label}`).then(res => {
          console.log(res)
          this.loading = false
          this.data = res.data
          console.log(this.data[1])
        })
      }
    },
    onShowSizeChange(current, pageSize) {
      console.log(current, pageSize);
    },
    onChange(checked) {
      console.log(`a-switch to ${checked}`);
    },
    priceSort(value) {
      console.log(value);
      console.log(this.data[1].price);
      if(value=="upSort"){
        this.data.sort((a,b)=>{
          return a.price - b.price
        })
      }
      else if(value=="downSort"){
        this.data.sort((a,b)=>{
          return b.price-a.price
        })
      }
      else if(value=="cancelSort"){
        this.data.sort((a,b)=>{
          return a.id-b.id
        })
      }
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
