<template>
  <div>
    <a-table :columns="columns" :data-source="data" :align="center">
      <span slot="imageUrl" slot-scope="text,record">
        <img style="width:140px;height:120px" slot="imageUrl" :slot-scope="text" :src=text />
      </span>
      <span slot="operation" slot-scope="text, record,index">
          <a-button type="primary" @click="toDeliverGoods(record)" style="margin-right: 1px"> 去发货 </a-button>
          <a-divider type="vertical"></a-divider>
          <a-button margin type="danger" @click="showDeleteConfirm(record.id)">取消交易</a-button>
          <div class="editable-row-operations">
            <span v-if="record.editable">
              <a @click="() => save(record.key)">Save</a>
            </span>
          </div>
      </span>

      <template
        v-for="col in ['name', 'type', 'quantity','price']"
        :slot="col"
        slot-scope="text, record, index"
      >
        <div :key="col">
          <a-input
            v-if="record.editable"
            style="margin: -5px 0"
            :value="text"
            @change="e => handleChange(e.target.value, record.key, col)"
          />
          <template v-else>
            {{ text }}
          </template>
        </div>
      </template>
    </a-table>
  </div>
</template>



<script>
import forget from "../../page/login/forget";

const columns = [
  {
    title: '图片',
    dataIndex: 'imageUrl',
    width: '15%',
    key:'imageUrl',
    align: 'center',
    scopedSlots: {customRender: 'imageUrl'},
  },
  {
    title: '名称',
    dataIndex: 'name',
    width: '10%',
    align: 'center',
    scopedSlots: { customRender: 'name' },
  },
  {
    title: '收货人姓名',
    dataIndex: 'buyerName',
    width: '10%',
    align: 'center',
    scopedSlots: { customRender: 'buyerName' },
  },
  {
    title: '收货人电话',
    dataIndex: 'buyerPhone',
    width: '10%',
    align: 'center',
    scopedSlots: { customRender: 'buyerPhone' },
  },
  {
    title: '收货人地址',
    dataIndex: 'buyerAddress',
    width: '10%',
    align: 'center',
    scopedSlots: { customRender: 'buyerAddress' },
  },
  {
    title: '已支付',
    dataIndex: 'actualPayAmount',
    width: '10%',
    align: 'center',
    scopedSlots: { customRender: 'actualPayAmount' },
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
    this.cacheData = data.map(item => ({ ...item }));
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
      uid: '',
    };
  },
  mounted() {
    if(this.$cookies.isKey('vid') === false)
      this.$router.push('login')
    let id = this.$cookies.get('vid')
    this.uid = id
    this.getList(id)
  },
  //获取已发布的商品
  methods: {
    getList (vid) {
      this.loading = true
      let _this = this
      //此处以获取订阅列表来测试显示
      axios.get('http://localhost:8181/omOrder/list/'+vid).then(function (response){
        _this.loading = false
        _this.data = response.data
      })
    },
    toDeliverGoods(record)
    {
      alert("现在就去发货吧！")
      alert("发货人信息："+record.buyerName+" "+record.buyerAddress+" "+record.buyerPhone)
    }



  },
};
</script>
<style scoped>
.editable-row-operations a {
  margin-right: 8px;
}
</style>
