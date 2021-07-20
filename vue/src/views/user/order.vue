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
              <p>收货人姓名：{{form.name}}</p>
              <p>收货人电话：{{form.telNumber}}</p>
              <p>收货人地址：{{form.address}}</p>
            </div>
          </a-card>





        </div>
      </div>
    </a-page-header>
    <template>
    <a-form-model :model="form2" :label-col="labelCol" :wrapper-col="wrapperCol">
      <a-form-model-item label="请输入快递单号">
        <a-input  placeholder="快递单号" />
      </a-form-model-item>
    </a-form-model>
    </template>
    <div
      :style="{
                    position: 'absolute',
                    left: '17%',
                    bottom: 0,
                    width: '80.5%',
                    borderTop: '1px solid #e9e9e9',
                    padding: '10px 32px',
                    background: '#fff',
                    textAlign: 'right',
                    zIndex: 1,
                  }"
    >
      <a-button type="primary" @click="addOrder()">
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
      addId: '',
      value: '',
      visible: false,
      createrName: '',
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
    this.axios.get('http://localhost:8181/umUser/findName/'+id).then(function (response){
      _this.createrName = response.data
    })
    this.gid = this.$route.params.id
    this.form.address = this.$route.params.add
    this.form.name=this.$route.params.na
    this.form.telNumber=this.$route.params.pho
    this.ids = this.gid.split(',')
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
    afterVisibleChange(val) {
      console.log('visible', val);
    },
    showDrawer() {
      this.visible = true;
    },
    addAddress(uid) {
      let _this = this
      var add =
        {id:1,
          userId: uid,
          consigneeName: this.form.name,
          consigneeTel: this.form.telNumber,
          consigneeAddress: this.form.address
        }
      this.axios.post('http://localhost:8181/userAddress/add',add).then(function (response) {
        if(response.data)
          alert("插入地址成功！")
      })
      this.visible = false;
    },
    updateAddress(uid)
    {
      let _this = this
      var update =
        {
          id: this.addId,
          userId: uid,
          consigneeName: this.form.name,
          consigneeTel: this.form.telNumber,
          consigneeAddress: this.form.address
        }
      this.axios.put('http://localhost:8181/userAddress/update',update).then(function (response) {
        if(response.data)
          alert("更新地址成功！")
        location.reload()
      })
      this.visible = false;
    },
    chooseAddress(object)
    {
      this.form.name = object.consigneeName
      this.form.telNumber = object.consigneeTel
      this.form.address = object.consigneeAddress
      this.addId = object.id
    },
    removeAddress(id)
    {
      this.axios.delete('http://localhost:8181/userAddress/delete/'+id).then(function (response) {
        if(response.data)
          alert("删除成功！")
        else
          alert("删除失败！")
        location.reload()
      })
    },
    onClose() {
      this.visible = false;
    },
    addOrder()
    {
      var myOrder =
        {
          id: 0,
          goodsId: this.gid,
          buyerId: this.uid,
          actualPayAmount: this.price,
          buyerAddress: this.form.address,
          buyerPhone: this.form.telNumber,
          buyerName: this.form.name
        }
      if(this.form.name.length==0||this.form.telNumber.length==0||this.form.address.length==0)
      {
        alert("姓名，地址和电话不能为空！" +
          "请重新填写！")
        return
      }

      this.axios.post('http://localhost:8181/omOrder/add',myOrder).then(function (response){
        if(response.data)
          alert("创建订单成功！")
        alert("正在转向结算页面...")
      })
    }
  },
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
