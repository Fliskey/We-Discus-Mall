<template>
  <div>
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

    <a-drawer
      title="修改商品信息"
      :width="720"
      :visible="visible"
      :body-style="{ paddingBottom: '80px' }"
      @close="onClose"
    >
      <a-form-model :form="form" layout="vertical" hide-required-mark>
        <a-row :gutter="16">
          <a-col :span="12">
            <a-form-model-item label="商品编号">
              <a-input
                v-model="form.id" disabled
                v-decorator="[
                  'id',
                  {
                    rules: [{ required: true, message: 'Please enter user name' }],
                  },
                ]"
                placeholder="Please enter user name"
              />
            </a-form-model-item>
          </a-col>
          <a-col :span="12">
            <a-form-model-item label="商品图片Url">
              <a-input
                v-model="form.imageUrl"
                v-decorator="[
                  'url',
                  {
                    rules: [{ required: true, message: 'please enter url' }],
                  },
                ]"
                style="width: 100%"
                placeholder="please enter url"
              />
            </a-form-model-item>
          </a-col>
        </a-row>
        <a-row :gutter="16">
          <a-col :span="12">
            <a-form-model-item label="商品名称">
              <a-input
                v-model="form.name"
                v-decorator="[
                  'owner',
                  {
                    rules: [{ required: true, message: 'Please select an owner' }],
                  },
                ]"
                placeholder="Please a-s an owner"
              />
            </a-form-model-item>
          </a-col>
          <a-col :span="12">
            <a-form-model-item label="商品类型">
              <a-select
                v-model="form.type"
                v-decorator="[
                  'type',
                  {
                    rules: [{ required: true, message: 'Please choose the type' }],
                  },
                ]"
                placeholder="Please choose the type"
              >
                <a-select-option value="办公文具">
                  办公文具
                </a-select-option>
                <a-select-option value="蔬菜水果">
                  蔬菜水果
                </a-select-option>
                <a-select-option value="电子产品">
                  电子产品
                </a-select-option>
                <a-select-option value="日常杂货">
                  日常杂货
                </a-select-option>
                <a-select-option value="家具">
                  家具
                </a-select-option>
                <a-select-option value="健身器材">
                  健身器材
                </a-select-option>
                <a-select-option value="其他">
                  其他
                </a-select-option>
              </a-select>
            </a-form-model-item>
          </a-col>
        </a-row>
        <a-row :gutter="16">
          <a-col :span="12">
            <a-form-model-item label="商品数量">
              <a-input-number id="inputNumber" v-model="form.quantity" :min="1" :max="10" @change="onChange" />
              当前值：{{ form.quantity }}
            </a-form-model-item>
          </a-col>
          <a-col :span="12">
            <a-form-model-item label="商品单价">
              <a-input-number
                v-model="form.price"
                :formatter="value => `￥ ${value}`.replace(/\B(?=(\d{3})+(?!\d))/g, ',')"
                :parser="value => value.replace(/\$\s?|(,*)/g, '')"
                @change="onChange"
              />
              总价：{{ totalPrice }}
            </a-form-model-item>
          </a-col>
        </a-row>
        <a-row :gutter="16">
          <a-col :span="24">
            <a-form-model-item label="商品描述">
              <a-textarea
                v-model="form.description"
                v-decorator="[
                  'description',
                  {
                    rules: [{ required: true, message: 'Please enter url description' }],
                  },
                ]"
                :rows="4"
                placeholder="please enter url description"
              />
            </a-form-model-item>
          </a-col>
          <img :src=form.imageUrl width="220px" height="180px">
        </a-row>
      </a-form-model>
      <div
        :style="{
          position: 'absolute',
          right: 0,
          bottom: 0,
          width: '100%',
          borderTop: '1px solid #e9e9e9',
          padding: '10px 16px',
          background: '#fff',
          textAlign: 'right',
          zIndex: 1,
        }"
      >
        <a-button :style="{ marginRight: '8px' }" @click="onClose">
          取消
        </a-button>
        <a-button type="primary" @click="saveChange">
          提交
        </a-button>
      </div>
    </a-drawer>
  </div>
</template>



<script>
  import forget from "../../page/login/forget";

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
      showDrawer(record) {
        this.visible = true;
        this.form = record;
      },
      onClose() {
        this.visible = false;
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
        axios.get('http://localhost:8181/gmGoods/findByUserId/'+vid).then(function (response){
          _this.loading = false
          _this.data = response.data
        })
      },
      //说明：全量更新
      saveChange(){
        let _this = this
        axios.put('http://localhost:8181/gmGoods/update',_this.form).then(function (response) {
          if(response.data){
            alert('商品信息提交成功！')
            _this.$router.push({
              path: '/visitor/user/sell'
            })
          }
          else
            alert('商品信息提交失败！')
        })
        this.onClose()
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

      },
      save(key) {
        const newData = [...this.data];
        const newCacheData = [...this.cacheData];
        const target = newData.filter(item => key === item.key)[0];
        const targetCache = newCacheData.filter(item => key === item.key)[0];
        if (target && targetCache) {
          delete target.editable;
          this.data = newData;
          let Object
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
      //删除记录,此处拿预订做测试
      showDeleteConfirm(Gid){
        this.$confirm({
          title: '确定要删除'+Gid+'吗？',
          okText: '确定',
          okType: 'danger',
          cancelText: '取消',
          onOk(){
            //alert("删除"+Gid)
            axios.delete('http://localhost:8181/gmGoods/delete/'+Gid).then(res =>{
              if (res){
                alert('删除成功！')
                location.reload()
              }
            })
          }
        })
      }
    },
    computed:{
      totalPrice(){
        let totalPrice=0
        totalPrice = this.form.quantity*this.form.price
        totalPrice = parseFloat(totalPrice).toFixed(2)
        return totalPrice
      }
    }
  };
</script>
<style scoped>
.editable-row-operations a {
  margin-right: 8px;
}
</style>
