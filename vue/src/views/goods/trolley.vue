<template>
  <div>
    <a-page-header title="购物车" sub-title="" @back="() => $router.go(-1)">
      <template slot="tags">
        <a-tag color="blue">
          Running
        </a-tag>
      </template>
      <template slot="extra">
        <!--        <a-button key="3">-->
        <!--          Operation-->
        <!--        </a-button>-->
        <!--        <a-button key="2">-->
        <!--          Operation-->
        <!--        </a-button>-->
        <a-button key="1" type="primary" @click="createOrder">
          结算购物车
        </a-button>
      </template>
      <a-row type="flex">
        <a-statistic title="Status" value="Pending" />
        <a-statistic
          title="总价"
          prefix="￥"
          :value="totalPrice"
          :style="{
            margin: '0 32px',
          }"
        />
        <a-statistic title="已选中" prefix="￥" :value="paymentAmount" />
      </a-row>
    </a-page-header>
    <!--    <a-button margin type="primary" @click="showConfirm">结算购物车</a-button>-->
    <a-table :columns="columns" :data-source="data" :align="center" rowKey="id"
             :row-selection="{selectedRowKeys: selectedRowKeys,
             onChange: onSelectChange}">
      <span slot="imageUrl" slot-scope="text,record">
        <img style="width:140px;height:120px" slot="imageUrl" :slot-scope="text" :src=text alt="加载失败"/>
      </span>
      <span slot="quantity" slot-scope="text,record,index" >
         <a-input-number id="quantity" v-model="record.quantity" :min="1" :max="10" @change="changeQuantity(record.quantity,record.id,vid)" />
      </span>
      <span slot="operation" slot-scope="text, record,index">
          <a-button margin type="danger" @click="deleteShopping(record.id,vid)">删除</a-button>
      </span>


    </a-table>
  </div>
</template>


<script>
const columns = [
  {
    title: '图片',
    dataIndex: 'imageUrl',
    width: '20%',
    key:'imageUrl',
    align: 'center',
    scopedSlots: {customRender: 'imageUrl'},
  },
  {
    title: '名称',
    dataIndex: 'name',
    width: '15%',
    align: 'center',
    scopedSlots: { customRender: 'name' },
  },
  {
    title: '类型',
    dataIndex: 'type',
    width: '15%',
    align: 'center',
    scopedSlots: { customRender: 'type' },
  },
  {
    title: '数量',
    dataIndex: 'quantity',
    width: '15%',
    align: 'center',
    key:'quantity',
    scopedSlots: { customRender: 'quantity' },
  },
  {
    title: '单价',
    dataIndex: 'price',
    width: '10%',
    align: 'center',
    key: 'price',
    scopedSlots: { customRender: 'price' },
  },
  {
    title: '操作',
    dataIndex: 'operation',
    width: '25%',
    align: 'center',
    scopedSlots: { customRender: 'operation' },
  },
];

const data = [];

export default {
  data() {
    this.cacheData = data.map(item => ({...item}));
    return {
      selectedRowKeys: [],
      gmGoods:
        {
          name: '',
          type: '',
          quantity: '',
          price: ''
        },
      data,
      columns,
      editingKey: '',
      vid: '',
      quantityValue: ''
    };
  },
  created() {
    if (this.$cookies.isKey('vid') === false)
      this.$router.push('login')
    let id = this.$cookies.get('vid')
    this.vid = id
    this.getList(id)
  },
  mounted() {
  },
  //获取已发布的商品
  methods: {
    onSelectChange(selectedRowKeys) {
      console.log("selectedRowKeys changed: ", selectedRowKeys);
      this.selectedRowKeys = selectedRowKeys;
    },
    deleteShopping(gid, vid) {
      // this.data.map((shopping,index) => {
      //   if (shopping.id === id) {
      //     this.data.splice(index, 1);
      //   }
      // });
      // this.onSelectChange(this.selectedRowKeys);
      this.$confirm({
        title: '确定要删除' + gid + '吗？',
        okText: '确定',
        okType: 'danger',
        cancelText: '取消',
        onOk() {
          axios.get('http://localhost:8181/purchaseGoods/delete/' + vid + '/' + gid + '/').then(res => {
            //console.log(res)
            if (res) {
              alert('删除成功！')
              location.reload()
            }
          })
        }
      })
    },
    onChange(value, gid, vid) {
      console.log('changed', value);
      this.quantityValue = value
      // alert("onChange:" + this.quantityValue)
      // alert(gid + ":" + vid)
      axios.get('http://localhost:8181/purchaseGoods/update/' + vid + '/' + gid + '/' + this.quantityValue).then(function (response) {
        if (response.data) {
          // alert('调动了修改！')
          // alert('修改成功！')
        }
      })
    },
    getList(vid) {
      this.loading = true
      let _this = this
      axios.get('http://localhost:8181/purchaseGoods/findByUserId/' + vid).then(function (response) {
        _this.loading = false
        _this.data = response.data
        console.log(response.data)
      })
    },
    //按钮修改数量
    changeQuantity(value, gid, vid) {
      axios.get('http://localhost:8181/purchaseGoods/update/' + vid + '/' + gid + '/' + value).then(function (response) {
      })
    },
    showDeleteConfirm(gid, vid) {
      this.$confirm({
        title: '确定要删除' + gid + '吗？',
        okText: '确定',
        okType: 'danger',
        cancelText: '取消',
        onOk() {
          axios.get('http://localhost:8181/purchaseGoods/delete/' + vid + '/' + gid + '/').then(res => {
            //console.log(res)
            if (res) {
              alert('删除成功！' + vid + "：" + gid)
              location.reload()
            }
          })
        }
      })
    },
    createOrder() {
      //勾选的编号存放砸selectedRowKeys中
      alert("您要购买：" + this.selectedRowKeys)
      this.$router.push('/visitor/goods/purchase/'+this.selectedRowKeys)


    },
  },
  computed: {
    //总价
    totalPrice() {
      let totalPrice = 0
      console.log(this.data)
      this.data.forEach((item) => {
        console.log(item)
        totalPrice += item.quantity * item.price
      })
      totalPrice = parseFloat(totalPrice).toFixed(2)
      return totalPrice
    },
    //选中价格
    paymentAmount() {
      let paymentAmount = 0
      this.data.forEach(item=>{
        this.selectedRowKeys.forEach(selected =>{
          if (item.id === selected){
            paymentAmount += item.quantity*item.price
          }
        })
      })
      paymentAmount = parseFloat(paymentAmount).toFixed(2)
      return paymentAmount
    }
  }
}
</script>
<style scoped>
.editable-row-operations a {
  margin-right: 8px;
}
</style>
