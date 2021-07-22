<template>

  <div>
    <a-page-header
      style="border: 1px solid rgb(235, 237, 240)"
      title="发货界面"
      sub-title="确认发货"
      @back="() => $router.go(-1)"
    >
      <div class="content">
        <div class="main">

          <a-card title="收货人信息" style="width: 100%; height: 100%">
            <div>
              <p>收货人姓名：{{order.buyerName}}</p>
              <p>收货人电话：{{order.buyerPhone}}</p>
              <p>收货人地址：{{order.buyerAddress}}</p>
            </div>
          </a-card>





        </div>
      </div>
      <template>
        <a-form-model :model="courierNumber" :label-col="labelCol" :wrapper-col="wrapperCol">
          <a-form-model-item label="请输入快递单号">
            <a-input  placeholder="快递单号"
            v-model="courierNumber"/>
          </a-form-model-item>
        </a-form-model>
      </template>
    </a-page-header>

    <div
      :style="{
                    position: 'absolute',
                    left: '17%',
                    bottom: 5,
                    width: '80.5%',
                    borderTop: '1px solid #e9e9e9',
                    padding: '10px 32px',
                    background: '#fff',
                    textAlign: 'right',
                    zIndex: 1,
                  }"
    >
      <a-button type="primary" @click="hasShipped()">
        <a-icon type="like" />
        完成发货
      </a-button>
    </div>

  </div>
</template>


<script>
const data = [];
const addressData = [];
const ids = [];
export default {
  data() {
    return {
      form:
        {
          name: '',
          telNumber: '',
          address: ''
        },
      ids,
      courierNumber: '',
      addId: '',
      value: '',
      visible: false,
      oid: '',
      order:
        {
          id: '',
          goodsId: '',
          buyerId: '',
          actualPayAmount: '',
          buyerAddress: '',
          buyerPhone: '',
          buyerName: '',
          hasPaid: '',
          hasShipped: '',
          hasConfirmed: ''
        },
      gid: '',
      uid: '',
      data,
      addressData,
      currentTime: new Date().getFullYear()+'/'+new Date().getMonth()+'/'+new Date().getDate()+
        '/'+new Date().getHours()+':'+new Date().getMinutes()+':'+new Date().getSeconds(),
      price: 0,

    };
  },
  mounted()
  {

    if(this.$cookies.isKey('vid') === false)
      this.$router.push('login')
    let id = this.$cookies.get('vid')
    this.uid = id
    let _this = this
    // this.axios.get('http://localhost:8181/umUser/findName/'+id).then(function (response){
    //   _this.createrName = response.data
    // })
    this.oid = this.$route.params.id
    this.axios.get('http://localhost:8181/omOrder/order/'+this.oid).then(function (response){
        _this.order = response.data
         })

    this.loading = true
    var i = 0
    this.axios.get('http://localhost:8181/gmGoods/findList/'+this.ids). then(function (response){
      _this.data = response.data
      for(i;i<response.data.length;i++){
        _this.price += response.data[i].price
      }


    })
    this.axios.get('http://localhost:8181/userAddress/list'). then(function (response){
      _this.addressData = response.data

    })


  },
  methods: {
    hasShipped()
    {
      var newOrder =
        {
          id: this.order.id,
          goodsId: this.order.goodsId,
          buyerId: this.order.buyerId,
          actualPayAmount: this.order.actualPayAmount,
          buyerAddress: this.order.buyerAddress,
          buyerPhone: this.order.buyerPhone,
          buyerName: this.order.buyerName,
          hasPaid: this.order.hasPaid,
          hasShipped: 1,
          hasConfirmed: this.order.hasConfirmed
        }
      //  alert(this.courierNumber)
        if(this.courierNumber.length==0){
          alert('未输入订单号')
          return
        }
        let _this = this
      this.axios.put('http://localhost:8181/omOrder/update/',newOrder).then(function (response){
        var newShipped =
          {
            orderId: _this.order.id,
            shippedId:_this.courierNumber

          }
        _this.axios.post('http://localhost:8181/shippedGoods/add/',newShipped).then(function (response){
          if(response.data)
            alert("发货成功！")
        })

      })

    }
  }
};



</script>

<style scoped>
.gutter-example >>> .ant-row > div {
  background: transparent;
  border: 0;
}
.gutter-box {
  background: #ffffff;
  padding: 5px 0;
}
tr:last-child td {
  padding-bottom: 0;
}
#components-page-header-demo-responsive .content {
  display: flex;
}
#components-page-header-demo-responsive .ant-statistic-content {
  font-size: 20px;
  line-height: 28px;
}
@media (max-width: 576px) {
  #components-page-header-demo-responsive .content {
    display: block;
  }

  #components-page-header-demo-responsive .main {
    width: 100%;
    margin-bottom: 12px;
  }

  #components-page-header-demo-responsive .extra {
    width: 100%;
    margin-left: 0;
    text-align: left;
  }
}
</style>
