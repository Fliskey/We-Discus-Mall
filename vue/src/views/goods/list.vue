<template>
  <div>
    <!-- <search-form /> -->
    <a-card :bordered="false">
      <a-row type='flex' align='middle' justify='space-between'>
        <a-input-search  @search="onSearch" class="search-ipt" style="width: 522px" placeholder="请输入查找内容..." size="large" enterButton="搜索" />
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
      <br/>
      <a-list :grid="{ gutter:16, column: 2 }" itemLayout="vertical" :data-source="data" :pagination="pagination" bordered>
        <div>
          <a-list-item v-for="(v,n) in data" :key="v.id" style="height: 5cm; margin-top: 22px" bordered >
            <div class="content">
              <a-row class="detail" style="width: 12cm; height: 3.4cm">
                <img width='120px' height='120px' style="float: left; border-radius: 7px" :src=v.imageUrl>
                <router-link :to="'/visitor/goods/detail/'+v.id">
                  <a-list-item-meta>
                    <div slot="title" style="height: 2px;font-size: large">{{v.name}}
                      <a-tag color="red" style="font-weight: normal">
                        {{v.type}}
                      </a-tag>
                      <a-tag color="red" style="font-weight: normal">全新</a-tag>
                      <a-tag color="red" style="font-weight: normal">自提</a-tag>
                    </div>
                  </a-list-item-meta>
                </router-link>
                <p style="
                height: 40px">{{v.description}}</p>
                <a-list-item-meta>
                  <div slot="title" style="height: 0;color: #dc5c47">价格：￥{{v.price}}</div>
                </a-list-item-meta>
              </a-row>
              <div class="author" style="width: 9cm; height: 18px">
                <a-avatar size="small" src="/img/user.png"/>
                <a>{{userName[n]}}</a>发布于
                <em>2018-08-05 22:23</em>
              </div>
            </div>
            <span slot="actions"><a-icon style="margin-right: 8px" type="eye" />1563</span>
            <span slot="actions"><a-icon style="margin-right: 8px" type="star" />{{likeQuantity[n]}}</span>
            <span slot="actions"><a-icon style="margin-right: 8px" type="message" /></span>
          </a-list-item>
        </div>
        <a-divider></a-divider>
      </a-list>
    </a-card>
  </div>
</template>

<script>
  export default {
    name: 'ArticleList',
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
      if (this.$cookies.isKey('vid') === false)
        this.$router.push('login')
      console.log(this.$cookies.get('vid'))
      this.getList()
    },
    mounted () {
    },
    data () {
      let self = this
      return {
        data: [],
        likeQuantity: [],
        index: 0,
        num: 10,
        pageSizeOptions: ['10', '20', '30', '40', '50'],
        userName: [],
        pagination: {
          pageNo: 1,
          pageSize: 10, // 默认每页显示数量
          showSizeChanger: true, // 显示可改变每页数量
          pageSizeOptions: ['3', '5', '10', '13'], // 每页数量选项
          showTotal: total => `Total ${total} items`, // 显示总数
          onShowSizeChange: (current, pageSize) => self.onShowSizeChange(current, pageSize), // 改变每页数量时更新显示
          onChange: (page, pageSize)=>self.changePage(page, pageSize), //点击页码事件
          total: 0 //总条数
        }
      }
    },

    watch: {
    },
    methods: {
      async getList () {
        let _this = this
        this.loading = true
        console.log("每页数量  "+this.pageSize)
        // await this.axios.get('http://localhost:8181/gmGoods/list/').then(res => {
        await this.axios.get('http://localhost:8181/gmGoods/selectPage/'+ this.pagination.pageNo + '/' + this.pagination.pageSize).then(res => {
          // console.log(this.pageSize)
          console.log(res)
          _this.data = res.data.records
          _this.pagination.pageNo= res.data.current
          _this.pagination.pageSize =res.data.size
          _this.pagination.total = res.data.total
          // _this.num = res.data.total.length
        })
        //alert(this.num)
        for (var i=0; i<this.num; i++){

          let I = i
          //alert(_this.data[i].userId)

          await this.axios.get('http://localhost:8181/umUser/findName/'+_this.data[i].userId).then(res=>{
            console.log(_this.data[i].userId+'\n')
            _this.userName.push(res.data)
            //_this.userName[i] = res.data
          })
        }
      },

      async getUserName (uid)
      {
        let name
        let _this = this
        await this.axios.get('http://localhost:8181/umUser/findName/'+uid).then(function (response) {
          return response.data

        })

      },
      change (uid)
      {
        this.getUserName(uid).then(res => {
          console.log(res.data)
        })
      },
      selectList (val) {
        if (val === 7) { // todo 此处没有做到对getList的复用 之后可以改进
          this.loading = true
          console.log('展示所有商品类型')
          this.pagination.pageNo = 1;
          this.axios.get('http://localhost:8181/gmGoods/selectPage/'+ this.pagination.pageNo + '/' + this.pagination.pageSize).then(res => {
            // console.log(this.pageSize)
            console.log(res)
            _this.data = res.data.records;
            _this.pagination.pageNo= res.data.current
            _this.pagination.pageSize =res.data.size
            _this.pagination.total = res.data.total
            // _this.num = res.data.total.length
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
      priceSort (value) {
        console.log(value)
        console.log(this.data[1].price)
        if (value==='upSort'){
          this.data.sort((a, b)=>{
            return a.price - b.price
          })
        }
        else if (value==='downSort'){
          this.data.sort((a, b)=>{
            return b.price-a.price
          })
        }
        else if (value==='cancelSort'){
          this.data.sort((a, b)=>{
            return a.id-b.id
          })
        }
      },
      onShowSizeChange(current, pageSize){
        console.log("进入这个onshowSizeChange！", current, pageSize)
        this.axios.get('http://localhost:8181/gmGoods/selectPage/'+current+'/'+pageSize+'/').then(res => {
          console.log(this.pageSize)
          console.log(res)
          this.data = res.data.records;
          this.pagination.pageNo= res.data.current
          this.pagination.pageSize =res.data.size
          this.pagination.total = res.data.total
          // this.num = res.data.length
        })
      },
      changePage(page,pageSize){
        // console.log("进入onChange函数")
        // page = 2 //调试
        // console.log("page = "+page)
        // pageSize = 4  //调试
        // console.log("pageSize = "+pageSize)
        this.axios.get('http://localhost:8181/gmGoods/selectPage/'+page+'/'+pageSize).then(res => {
          console.log(pageSize)
          console.log(res.data)
          this.data = res.data.records;
          this.pagination.pageNo= res.data.current
          this.pagination.pageSize =res.data.size
          this.pagination.total = res.data.total
          // this.num = res.data.records.length
        })
      },
      onSearch(value){
        console.log(value);
        this.loading = true
        this.http.get(`http://localhost:8181/gmGoods/findSelect/`+value).then(res => {
          console.log(res)
          this.loading = false
          this.data = res.data
        })
      }
    }
  }
</script>

<style lang="less" scoped>
.content {
  .detail {
    line-height: 16px;
    max-width: 720px;
    flex-wrap:wrap;
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
    margin-top: 2px;
    line-height: 22px;
    & > :global(.ant-avatar) {
      vertical-align: top;
      margin-right: 8px;
      width: 20px;
      height: 20px;
      position: relative;
      top: 0;
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
