<template>

  <div>
    <a-page-header
      style="border: 1px solid rgb(235, 237, 240)"
      title="正在支付..."
      sub-title="订单创建完成，正在支付您的订单！"
      @back="() => $router.go(-1)"
    >
      <template slot="extra">
        <a-button key="2">
          取消订单
        </a-button>
        <a-button key="1" type="primary" @click="goToPay()">
          立即支付
        </a-button>
      </template>
      <a-descriptions size="small" :column="2">
        <a-descriptions-item label="订单创建者">
          {{this.createrName}}
        </a-descriptions-item>
        <a-descriptions-item label="创建时间">
          {{ this.currentTime }}
        </a-descriptions-item>
      </a-descriptions>
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
              <p>收货人姓名：{{this.addressData.consigneeName}}</p>
              <p>收货人电话：{{this.addressData.consigneeTel}}</p>
              <p>收货人地址：{{this.addressData.consigneeAddress}}</p>
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
      createrName: '',
      columns,
      gid: '',
      uid: '',
      aid: '',
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
    this.gid = this.$route.params.gid
    this.aid = this.$route.params.aid
    this.ids = this.gid.split(',')
    let _this = this
    var i = 0
    this.axios.get('http://localhost:8181/gmGoods/findList/'+this.ids). then(function (response) {
      _this.data = response.data
      for (i; i < response.data.length; i++) {
        _this.price += response.data[i].price
      }
    })
    this.axios.get('http://localhost:8181/userAddress/getAddress/'+this.aid). then(function (response){
      _this.addressData = response.data

    })

  },
  methods: {



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
