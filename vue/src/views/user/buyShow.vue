<template>
  <div>
    <a-row>
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
    </a-row>
    <a-row :gutter="24">
      <a-col :xs="24" :sm="12" :md="8" :lg="8" v-for="(item, index) in list" :key="index" class="mt-3">
        <a-card :title="item.title" class="w-100">
          <div slot="extra">
            <a-button type="link" @click="onGoPay(item.goodsId)" v-if="item.hasPayed==0">
              去付款
            </a-button>
            <a-button type="danger" v-if="item.hasPayed==1" @click="onConfirmReceipt(item.id)" :disabled="item.hasConfirmed==1">
              {{ item.hasConfirmed == 1 ? '已收货' : '确认收货' }}
            </a-button>
          </div>
          <p class="good-img-box">
            <img :src="item.imageUrl" alt="">
          </p>
          <p>
            <span class="item-label">数量</span>
            <span class="item-content">
               <a-tag color="#108ee9">x{{ item.quantitySiOrder }}</a-tag>
            </span>
          </p>
          <p>
            <span class="item-label">单价</span>
            <span class="item-content">￥{{ item.price }}</span>
          </p>
          <p>
            <span class="item-label">实际支付</span>
            <span class="item-content total-price">￥{{ item.actualPayAmount }}</span>
          </p>
          <p>
            <span class="item-label">货物状态</span>
            <span> <a-tag :color='item.hasShipped==1 ? "cyan" :"blue"'>
              {{item.hasShipped == 1 ? '已发货':'未发货'}}
            </a-tag>
            </span>
          </p>

        </a-card>
      </a-col>
    </a-row>
  </div>
</template>
<script>
  import axios from 'axios'

  export default {
    data () {
      return {
        list: [],
        loading: false
      }
    },
    created () {
      if (this.$cookies.isKey('vid') === false)
        this.$router.push('login')
      let id = this.$cookies.get('vid')
      this.vid = id
      this.getBuyList(id)
    },
    mounted () {

    },
    methods: {
      // 获取订单列表
      getBuyList (vid) {
        // 发送后端请求 成功后将数组赋值给list
        this.loading = true
        let _this = this
        axios.get('http://localhost:8181/buyShow/buylistSimple/' + vid).then(function (response) {
          _this.loading = false
          _this.list = response.data
          console.log(response.data)
        })
      },
      // 确认收货
      onConfirmReceipt (vid) {
        this.$confirm({
          title: '确定要确认收货吗?',
          content: '此操作将无法恢复',
          cancelText: '取消',
          okText: '确定',
          onOk () {
            // 发送后端请求
            console.log('vid')
            console.log(vid)
            axios.get('http://localhost:8181/buyShow/confirm/' + vid).then(res => {
              if (res) {
                alert('收货成功！')
                location.reload()
              }
            })
          }
        })
      },

      onGoPay(goodsId){
        alert("跳转至付款页面")
        this.$router.push('/visitor/goods/purchase/'+goodsId)
      },

      priceSort (value) {
        console.log(value)
        console.log(this.list)
        console.log(this.list[1].price)
        if (value == 'upSort') {
          this.list.sort((a, b) => {
            return a.price - b.price
          })
        } else if (value == 'downSort') {
          this.list.sort((a, b) => {
            return b.price - a.price
          })
        } else if (value == 'cancelSort') {
          this.list.sort((a, b) => {
            return a.id - b.id
          //   location.reload()
          })
        }
      }
    }
  }
</script>

<style lang="less" scoped>
.w-100 {
  width: 100%;
}

.mt-3 {
  margin-top: 24px;
}

.good-img-box {
  width: 120px;
  height: 80px;
  overflow: hidden;
}

.good-img-box img {
  width: 100%;
}

.item-label {
  display: inline-block;
  width: 66px;
  color: #aaa;
}

.item-content {
  color: #333;
}

.total-price {
  color: #ff3300;
  font-weight: bold;
}
</style>
