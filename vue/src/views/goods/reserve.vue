<template>
  <div>
    <h1>我的预定</h1>
    <a-row :gutter="[16,16]">
      <a-col :span="8" v-for="(v,n) in data" :key="v.id" >
        <a-card hoverable style="width: 300px">
          <img
            slot="cover"
            alt="example"
            :src=v.imageUrl
          />
          <template slot="actions" class="ant-card-actions">
            <!-- 进入详情页 -->
            <router-link :to="'/visitor/goods/detail/'+v.id">
              <a-icon key="setting" type="exclamation-circle"/>
            </router-link>
            <a-icon key="edit" type="shopping" />
            <a-icon key="ellipsis" type="close-circle" @click="delLike(uid,v.id)"/>
          </template>
          <a-card-meta :title=v.name :description=v.description>
          </a-card-meta>
        </a-card>
      </a-col>
    </a-row>
  </div>
</template>

<script>

export default {
  name: 'ArticleList',
  mounted () {
    if(this.$cookies.isKey('vid') === false)
      this.$router.push('login')
    let id = this.$cookies.get('vid')
    this.uid = id;
    this.getList(id)

    //this.num = this.data.length
  },
  data() {
    return {
      data: [],
      index: 0,
      num: 10,
      wantId: '',
      pageSize: 5,
      current: 4,
      Uid: '',
      Gid: ''
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
    //获取预订列表
    getList (id) {
      this.loading = true
      let _this = this
      axios.get('http://localhost:8181/wantGoods/queryWant/'+id).then(function (response){
        _this.loading = false
        _this.data = response.data
      })
    },
    onShowSizeChange(current, pageSize) {
      console.log(current, pageSize);
    },
    delLike(uid,gid){
      //删除请求,对象为用户-货物数值对
      let _this = this
      this.$confirm({
        title: '确定删除货物编号:'+gid+' 你的编号:'+uid+"吗？",
        okText:'确定',
        okType:'danger',
        cancelText:'取消',
        onOk(){
          //axios删除
          axios.get('http://localhost:8181/wantGoods/delete/'+uid+'/'+gid).then(function (response){
            if(response)
              alert('删除成功！')
            location.reload()
          })
        }
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
