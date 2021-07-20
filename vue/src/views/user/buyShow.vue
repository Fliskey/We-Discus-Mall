<template>
  <div>
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
      <span slot="imageUrl" slot-scope="text,record">
        <img style="width:140px;height:120px" slot="imageUrl" :slot-scope="text" :src=text/>
      </span>
      <span slot="operation" slot-scope="text, record,index">
          <a-button type="primary" @click="confirmShipping(record)" style="margin-right: 1px"> 确认收货 </a-button>
      </span>
    </a-table>
  </div>
</template>

<script>
  import forget from '../../page/login/forget'

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

  export default {
    data () {
      this.cacheData = data.map(item => ({...item}))
      return {
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
      }
    },
    mounted () {
      if (this.$cookies.isKey('vid') === false)
        this.$router.push('login')
      let id = this.$cookies.get('vid')
      console.log("cookies"+this.$cookies)
      this.getList(id)
    },
    //获取已发布的商品
    methods: {
      confirmShipping (record) {
        console.log(record)
        this.visible = true
        this.form = record
      },
      onClose () {
        this.visible = false
      },
      buyList (vid) {
        this.loading = true
        let _this = this
        axios.get('http://localhost:8181/omOrder/list/' + vid).then(function (response) {
          _this.loading = false
          _this.data = response.data
        })
      },
      handleChange (value, key, column) {
        const newData = [...this.data]
        const target = newData.filter(item => key === item.key)[0]
        if (target) {
          target[column] = value
          this.data = newData
        }
      },
      }
    },
    computed: {
      totalPriceDone () {
      }
    }
  }
</script>
<style scoped>
.editable-row-operations a {
  margin-right: 8px;
}
</style>
