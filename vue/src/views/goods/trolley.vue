<template>
  <div>
  <a-button margin type="primary" @click="showConfirm">结算购物车</a-button>
    <a-table :columns="columns" :data-source="data" :align="center">
      <span slot="imageUrl" slot-scope="text,record">
        <img style="width:140px;height:120px" slot="imageUrl" :slot-scope="text" :src=text />
      </span>
      <span slot="operation" slot-scope="text, record,index">
          <a-button type="primary" @click="showDrawer(record)" style="margin-right: 1px"> 修改 </a-button>
          <a-divider type="vertical"></a-divider>
          <a-button margin type="danger" @click="showDeleteConfirm(record.id)">删除</a-button>
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
    };
  },
  mounted() {
    if(this.$cookies.isKey('vid') === false)
      this.$router.push('login')
    let id = this.$cookies.get('vid')
    this.getList(id)
  },
  //获取已发布的商品
  methods: {
    onChange(value) {
      console.log('changed', value);
    },
    getList (vid) {
      this.loading = true
      let _this = this
      //获取已发布商品的列表
      // axios.get('http://localhost:8181/'+vid).then(res=>{
      //   _this.loading = false
      //   _this.data = res.data
      // })
      //此处以获取订阅列表来测试显示
      axios.get('http://localhost:8181/purchaseGoods/findByUserId/'+vid).then(function (response){
        _this.loading = false
        _this.data = response.data
      })
    },
    handleChange(value, key, column) {
      const newData = [...this.data];
      const target = newData.filter(item => key === item.key)[0];
      if (target) {
        target[column] = value;
        this.data = newData;
      }
    },
    edit(key) {
      const newData = [...this.data];
      const target = newData.filter(item => key === item.key)[0];
      this.editingKey = key;
      if (target) {
        target.editable = true;
        this.data = newData;
      }
      this.data = JSON.parse(JSON.stringify(this.data));
    },
    save(key) {
      const newData = [...this.data];
      const newCacheData = [...this.cacheData];
      const target = newData.filter(item => key === item.key)[0];
      const targetCache = newCacheData.filter(item => key === item.key)[0];
      if (target && targetCache) {
        delete target.editable;
        this.data = newData;
        Object.assign(targetCache, target);
        this.cacheData = newCacheData;
      }
      this.editingKey = '';
    },
    cancel(key) {
      const newData = [...this.data];
      const target = newData.filter(item => key === item.key)[0];
      this.editingKey = '';
      if (target) {
        Object.assign(target, this.cacheData.filter(item => key === item.key)[0]);
        delete target.editable;
        this.data = newData;
      }
    },


    showDeleteConfirm(c){
      this.$confirm(/*axios.post('http://localhost:8181/purchaseGoods/GtoP/'+c).then(res => {

          })*/{
        title: '确定要删除'+c+'吗？',
        okText: '确定',
        okType: 'danger',
        cancelText: '取消',
        onOk(){
          //alert("删除"+Gid)
          axios.get('http://localhost:8181/purchaseGoods/GtoP/'+c).then(res =>{
            c = res.data
            axios.post('http://localhost:8181/purchaseGoods/delete/'+c).then(res =>{
              console.log(res)
              if (res){
                alert('删除成功！')
                location.reload()
              }
            })
          })

        }
      })
    },
    //计算总价格，还没写好
    calcTotalPrice() {
      var _this=this
      this.totalPrice=0
      this.getProductList.forEach((element,index) => {
        if(element.makeChoose){
          _this.totalPrice+=element.price*element.quantity //这里是一个累加的过程,所以要用+=
        }
      });
    },
    showConfirm() {
      //this.calcTotalPrice();
      this.$confirm({
        title: '是否确认购买?',
        content: h => <div style="color:red;">谢谢惠顾</div>,
        onOk() {
          return new Promise((resolve, reject) => {
            setTimeout(Math.random() > 0.5 ? resolve : reject, 1000);
          }).catch(() => console.log('Oops errors!'));
        },
        onCancel() {},
      });
    },
  },
};
</script>
<style scoped>
.editable-row-operations a {
  margin-right: 8px;
}
</style>
