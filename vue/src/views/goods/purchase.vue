<template>

  <div>
    <a-page-header
      style="border: 1px solid rgb(235, 237, 240)"
      title="订单提交界面"
      sub-title="开始创建你的订单"
      @back="() => $router.go(-1)"
    >
      <div class="content">
        <div class="main">

          <a-steps>
            <a-step status="finish" title="Login">
              <a-icon slot="icon" type="user" />
            </a-step>
            <a-step status="finish" title="Verification">
              <a-icon slot="icon" type="solution" />
            </a-step>
            <a-step status="process" title="Pay">
              <a-icon slot="icon" type="loading" />
            </a-step>
            <a-step status="wait" title="Done">
              <a-icon slot="icon" type="smile-o" />
            </a-step>
          </a-steps>

          <div class="gutter-example">
            <a-row :gutter="16">
              <a-col class="gutter-row" :span="6" v-for="(v,n) in addressData" :key="v.id">
                <div class="gutter-box">
                  <a-card title="地址" style="width: 250px">
                    <a slot="extra" @click="chooseAddress(v)">选择  </a>
                    <a slot="extra" @click="removeAddress(v.id)">删除</a>
                    <p>收货人姓名：{{v.consigneeName}}</p>
                    <p>收货人电话：{{v.consigneeTel}}</p>
                    <p>收货人地址：{{v.consigneeAddress}}</p>
                  </a-card>
                </div>
              </a-col>
            </a-row>
          </div>

          <a-card title="收货人信息" style="width: 100%; height: 100%">
            <a slot="extra" @click="showDrawer">编辑收货信息</a>
            <div>
              <p>收货人姓名：{{form.name}}</p>
              <p>收货人电话：{{form.telNumber}}</p>
              <p>收货人地址：{{form.address}}</p>
              <a-drawer
                title="修改您的收货信息"
                placement="right"
                :closable="false"
                :visible="visible"
                :get-container="false"
                :wrap-style="{ position: 'absolute' }"


                @close="onClose"
              >
                <a-form-model :form="form" layout="vertical" hide-required-mark>
                  <a-row :gutter="16">
                    <a-col :span="56">
                      <a-form-model-item >
                        <a-input
                          v-model="form.name"
                          v-decorator="['name',
                          {
                            rules: [{ required: true, message: '请输入收货人姓名！' ,trigger: 'blur'}],
                          },
                           ]"
                          placeholder="请输入收货人姓名"
                        />
                      </a-form-model-item>
                      <a-form-model-item >
                        <a-input
                          v-model="form.telNumber"
                          v-decorator="['telNumber',
                          {
                            rules: [{ required: true, message: '请输入收货人电话！' }],
                          },
                           ]"
                          placeholder="请输入收货人电话"
                        />
                      </a-form-model-item>
                      <a-form-model-item >
                        <a-input
                          v-model="form.address"
                          v-decorator="['address',
                          {
                            rules: [{ required: true, message: '请输入收货人地址！' }],
                          },
                           ]"
                          placeholder="请输入收货人地址"
                        />
                      </a-form-model-item>
                    </a-col>

                  </a-row>
                </a-form-model>
                <div
                  :style="{
                    position: 'absolute',
                    right: 0,
                    bottom: 0,
                    width: '110%',
                    borderTop: '1px solid #e9e9e9',
                    padding: '10px 16px',
                    background: '#fff',
                    textAlign: 'right',
                    zIndex: 1,
                  }"
                >
                  <a-button  @click="updateAddress(uid)">
                    修改
                  </a-button>
                  <a-divider type="vertical" />
                  <a-button type="primary" @click="addAddress(uid)">
                    添加
                  </a-button>
                  <a-divider type="vertical" />
                  <a-button  @click="onClose">
                    取消
                  </a-button>
                </div>
              </a-drawer>
            </div>
          </a-card>
          <a-table :columns="columns" :data-source="data" :align="center">
            <span slot="imageUrl" slot-scope="text,record">
              <img style="width:140px;height:120px" slot="imageUrl" :slot-scope="text" :src=text />
            </span>

          </a-table>

          <a-descriptions size="small" :column="2">
            <a-descriptions-item label="订单创建者">
              {{this.createrName}}
            </a-descriptions-item>
            <a-descriptions-item label="创建时间">
              {{ this.currentTime }}
            </a-descriptions-item>
            <a-descriptions-item label="备注">
              这里是商品备注
            </a-descriptions-item>
          </a-descriptions>
        </div>
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
              value="正在创建订单"
              :style="{
                marginRight: '32px',
              }"
            />
            <a-statistic title="需支付" prefix="￥" :value="this.price" />
          </div>
        </div>
      </div>
    </a-page-header>
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
        <a-icon type="shopping" />
        创建订单
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
      columns,
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
      let gid = this.gid
      let aid = this.addId
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
        let _this = this
        this.axios.post('http://localhost:8181/omOrder/add',myOrder).then(function (response){
          if(response.data)
            alert("创建订单成功！")
            alert("正在转向结算页面...")
            _this.$router.push('/visitor/goods/pay/'+gid+'/'+aid)
        })
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
