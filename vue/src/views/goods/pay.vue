<template>

  <div>
    <a-page-header
      style="border: 1px solid rgb(235, 237, 240)"
      title="正在支付..."
      sub-title="订单创建完成，正在支付您的订单！"
      @back="() => $router.go(-1)"
    >
      <template slot="extra" >
        <a-button key="2" @click="cancelPay()">
          取消订单
        </a-button>
        <a-modal v-model="cancelVisible" title="正在取消订单" @ok="handleOk2">
          <p>您确定要取消订单吗？ </p>
          <p>请确认！</p>
        </a-modal>
        <a-button key="1" type="primary" @click="goToPay()">
          立即支付
        </a-button>
        <a-modal v-model="visible" title="请选择支付方式" @ok="handleOk1">
          <p>您将立即支付：{{this.price}} </p>
          <p>请确认！</p>
        </a-modal>
      </template>
      <div class="extra">
        <div
          :style="{
              display: 'flex',
              width: 'max-content',
              justifyContent: 'flex-end',
            }"
        >
          <a-statistic
            title="状态"
            value="支付中"
            :style="{
                marginRight: '32px',
              }"
          />
          <a-statistic title="需支付" prefix="￥" :value="this.price" />
        </div>
      </div>
      <div class="content">
        <div class="main">

          <a-card title="收货人信息" style="width: 100%; height: 100%">
            <div>
              <p>收货人姓名：{{this.data[0].buyerName}}</p>
              <p>收货人电话：{{this.data[0].buyerPhone}}</p>
              <p>收货人地址：{{this.data[0].buyerAddress}}</p>
            </div>
          </a-card>
          <a-table :columns="columns" :data-source="data" :align="center">
            <span slot="imageUrl" slot-scope="text,record">
              <img style="width:140px;height:120px" slot="imageUrl" :slot-scope="text" :src=text />
            </span>

          </a-table>
        </div>
      </div>
    </a-page-header>

  </div>
</template>


<script>
const data = [];
const addressData = [];
const ids = [];
const allPrice = [];
const quantity = [];
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
      addId: '',
      value: '',
      visible: false,
      cancelVisible: false,
      createrName: '',
      columns,
      gid: '',
      uid: '',
      aid: '',
      oid: '',
      allPrice,
      quantity,
      data,
      addressData,
      currentTime: new Date().getFullYear()+'/'+new Date().getMonth()+'/'+new Date().getDate()+
        '/'+new Date().getHours()+':'+new Date().getMinutes()+':'+new Date().getSeconds(),
      price: 0,

    };
  },
  async mounted()
  {
    if(this.$cookies.isKey('vid') === false)
      this.$router.push('login')
    let id = this.$cookies.get('vid')
    this.uid = id
    this.oid = this.$route.params.oid
    this.ids = this.oid.split(',')
    //alert(this.ids)
    let _this = this

    await this.axios.get('http://localhost:8181/omOrder/findList/'+this.ids). then(function (response) {
      _this.data = response.data
      var i = 0
      for(i;i<_this.ids.length; i++)
      {
        _this.price+=_this.data[i].actualPayAmount
      }
    })




  },
  methods: {
    goToPay()
    {
      this.visible = true;
    },
    async handleOk1(e) {
      console.log(e);
      let _this = this
      var i = 0
      for(i; i < this.ids.length; i++)
      {
        await this.axios.put('http://localhost:8181/omOrder/updatePay/'+this.ids[i]).then(function (response) {
          //alert(_this.ids[i])
        })
      }
      alert("支付成功！")
      this.$router.push('/visitor/goods/list')
      this.visible = false;
    },
    cancelPay()
    {
      this.cancelVisible = true;
    },
    async handleOk2(e)
    {
      //取消订单分两步，首先将订单删除，其次再将数量加回
      //删除订单
      var j = 0
      for(j;j<this.ids.length;j++)
      {
        var k = this.ids[this.ids.length-1]-j
        await this.axios.delete('http://localhost:8181/omOrder/delete/'+k).then(function (response) {

        })
      }
      alert("删除订单成功！")
      //加回数量
      var i = 0
      for(i;i<this.ids.length;i++)
      {
        let _this = this
        let I = i
        await this.axios.get('http://localhost:8181/gmGoods/find/'+this.data[i].goodsId).then(async function (response){
          //alert(response.data.quantity)
          response.data.quantity+=_this.quantity[I]
          let __this = _this
          await _this.axios.put('http://localhost:8181/gmGoods/update',response.data).then(function(res){
            //alert("加回数量成功！")
            __this.$router.push('/visitor/goods/list')

          })
        })
        this.cancelVisible=false
      }
    }



  },
};

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
    title: '数量',
    dataIndex: 'quantity',
    width: '15%',
    align: 'center',
    key:'quantity',
    scopedSlots: { customRender: 'quantity' },
  },
  {
    title: '总价',
    dataIndex: 'actualPayAmount',
    width: '10%',
    align: 'center',
    scopedSlots: { customRender: 'price' },
  }
];

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
