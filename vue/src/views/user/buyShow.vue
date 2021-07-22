<template>
  <div>
<<<<<<< Updated upstream
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
            <a-button type="danger" @click="onConfirmReceipt" :disabled="item.hasConfirmed==1">
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
            <span> <a-tag :color='item.hasShipped==1 ? "cyan" :"blue"'>{{item.hasShipped == 1 ? '已发货':'未发货'}}</a-tag></span>
          </p>
        </a-card>
      </a-col>
    </a-row>
=======
    <a-page-header title="我的购买" sub-title="" @back="() => $router.go(-1)">
      <a-row type="flex">
        <a-statistic title="已收货订单数" :style="{marginRight:'64px'}" value="1"/>
        <a-statistic title="未收货订单数" :style="{marginRight:'64px'}" value="2"/>
        <a-statistic
          title="已结算总价值"
          prefix="￥"
          :value="totalPriceDone"
          :style="{
            margin: '0 32px',
          }"
        />
      </a-row>
    </a-page-header>
    <a-table :columns="columns" :data-source="data" :align="center">
      <span slot="imageUrl" slot-scope="text">
        <img style="width:140px;height:120px" slot="imageUrl" :slot-scope="text" :src="text"/>
      </span>
      <span slot="operation" slot-scope="text, record">
          <a-button type="primary" @click="confirmShipping(record)" style="margin-right: 1px"> 确认收货 </a-button>
      </span>
    </a-table>
>>>>>>> Stashed changes
  </div>
</template>
<script>
<<<<<<< Updated upstream
  import axios from 'axios'
=======
  const columns = [
    {
      title: '图片',
      dataIndex: 'imageUrl',
      width: '20%',
      key: 'imageUrl',
      align: 'center',
      scopedSlots: {customRender: 'imageUrl'}
    },
    {
      title: '名称',
      dataIndex: 'name',
      width: '15%',
      align: 'center',
      scopedSlots: {customRender: 'name'}
    },
    {
      title: '数量',
      dataIndex: 'quantity',
      width: '15%',
      align: 'center',
      scopedSlots: {customRender: 'quantity'}
    },
    {
      title: '单价',
      dataIndex: 'price',
      width: '10%',
      align: 'center',
      scopedSlots: {customRender: 'price'}
    },
    {
      title: '总价',
      dataIndex: 'price',
      width: '10%',
      align: 'center',
      scopedSlots: {customRender: 'price'}
    },
    {
      title: '操作',
      dataIndex: 'operation',
      width: '25%',
      align: 'center',
      scopedSlots: {customRender: 'operation'}
    }
  ]

  const data = []
>>>>>>> Stashed changes

  export default {
    data () {
      return {
        list: [],
<<<<<<< Updated upstream
        loading: false
=======
        form: this.$form.createForm(this),
        visible: false,
        gmGoods:
          {
            name: '',
            type: '',
            quantity: '',
            price: ''
          },
        data,
        columns,
        editingKey: ''
>>>>>>> Stashed changes
      }
    },
    created () {
      if (this.$cookies.isKey('vid') === false)
        this.$router.push('login')
      let id = this.$cookies.get('vid')
      this.vid = id
<<<<<<< Updated upstream
      this.getBuyList(id)
    },
    mounted () {

    },
=======
      this.getList(id)
    },
    mounted () {
    },
    computed: {
      // 返回未发货数量
      unShip () {
        const unShipList = this.list.map((item) => item.hasShipped !== 1)
        console.log(unShipList.length)
        return unShipList.length
      },
      // 返回已发货数量
      ship () {
        const ShipList = this.list.map((item) => item.hasShipped == 1)
        console.log(ShipList.length)
        return ShipList.length
      },
      // 返回已确认收货数量
      confirmOrder () {
        const ConfirmList = this.list.map((item) => item.hasConfirmed == 1)
        console.log(ConfirmList.length)
        return ConfirmList.length
      },
      //返回未确认收获数量
      unconfirmOrder () {
        const unConfirmList = this.list.map((item) => item.hasConfirmed !== 1)
        console.log(unConfirmList.length)
        return unConfirmList.length
      }
    },
    //获取已发布的商品
>>>>>>> Stashed changes
    methods: {
      // 获取订单列表
      getBuyList (vid) {
        // 发送后端请求 成功后将数组赋值给list
        this.loading = true
        let _this = this
<<<<<<< Updated upstream
        axios.get('http://localhost:8181/buyShow/buylistSimple/' + vid).then(function (response) {
=======
        this.axios.get('http://localhost:8181/omOrder/list/' + vid).then(function (response) {
>>>>>>> Stashed changes
          _this.loading = false
          _this.list = response.data
          console.log(response.data)
        })
      },
<<<<<<< Updated upstream
      // 确认收货
      onConfirmReceipt () {
        this.$confirm({
          title: '确定要确认收货吗?',
          content: '此操作将无法恢复',
          cancelText: '取消',
          okText: '确定',
          onOk (vid) {
            // 发送后端请求
            axios.get('http://localhost:8181/buyShow/confirm/' + vid).then(res => {
              if (res) {
                alert('删除成功！')
                location.reload()
              }
            })
          }
        })
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
          })
=======
      handleChange (value, key, column) {
        const newData = [...this.data]
        const target = newData.filter(item => key === item.key)[0]
        if (target) {
          target[column] = value
          this.data = newData
>>>>>>> Stashed changes
        }
      }
    }
  }
</script>

<<<<<<< Updated upstream
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
=======

<style scoped>
.editable-row-operations a {
  margin-right: 8px;
>>>>>>> Stashed changes
}
</style>
