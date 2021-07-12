<!--商品发布页面-->
<template>
  <a-form-model ref='gmGoods' :model="gmGoods" :rules='rules' class='login-form register'>
    <a-card :body-style="{padding: '24px 32px'}" :bordered="false">
      <a-form>
        <a-form-item
          label="商品名称"
          :labelCol="{span: 7}"
          :wrapperCol="{span: 10}"
        >
          <a-input placeholder="请输入商品名称"
                   block v-model='gmGoods.name'/>
        </a-form-item>
        <a-form-item
          label="商品数量"
          :labelCol="{span: 7}"
          :wrapperCol="{span: 10}">
          <a-input-number style='width:100%' placeholder="请输入商品数量"
                          block v-model='gmGoods.quantity'/>
        </a-form-item>
        <a-form-item
          label="商品单价"
          :labelCol="{span: 7}"
          :wrapperCol="{span: 10}">
          <a-input prefix="￥" placeholder="请输入商品单价" suffix="RMB"
                   block v-model='gmGoods.price'/>
          <!-- <a-input-number :prefix="￥" :placeholder="请输入商品单价" /> -->
        </a-form-item>
        <a-form-item
          label="商品描述"
          :labelCol="{span: 7}"
          :wrapperCol="{span: 10}">
          <a-textarea rows="4" placeholder="请输入商品描述"
                      block v-model='gmGoods.description'/>
        </a-form-item>
        <a-form-item
          label="商品类型"
          :labelCol="{span: 7}"
          :wrapperCol="{span: 10}">
          <a-select v-model='gmGoods.type'>
            <a-select-option v-for="item in lists" :key="item.value">{{ item.label }}</a-select-option>
          </a-select>
        </a-form-item>
        <a-form-item
          label="商品图片"
          :labelCol="{span: 7}"
          :wrapperCol="{span: 10}">
          <a-textarea rows="4" placeholder="请输入商品图片url"
                      block v-model='gmGoods.imageUrl'/>
        </a-form-item>
        <a-form-item style="margin-top: 24px" :wrapperCol="{span: 10, offset: 7}">
          <a-button type="primary" @click='save' style="margin-left: 30%">立即发布</a-button>
        </a-form-item>
      </a-form>
    </a-card>
  </a-form-model>
</template>

<script>
function getBase64(img, callback) {
  const reader = new FileReader();
  reader.addEventListener('load', () => callback(reader.result));
  reader.readAsDataURL(img);
}
export default {
  name: 'BasicForm',
  data () {
    return {
      gmGoods:{
        id:'0',
        userId:this.$cookies.get('vid'),
        name:'',
        type:'',
        quantity:'',
        price:'',
        imageUrl:'',
        description:'',
        //  type:4
      },
      loading: false,
      imageUrl: ''
    }
  },
  created () {
    this.lists = [
      {
        label: '办公文具',
        value: '办公文具'
      },
      {
        label: '蔬菜水果',
        value: '蔬菜水果'
      },
      {
        label: '电子产品',
        value: '电子产品'
      },
      {
        label: '日常杂货',
        value: '日常杂货'
      },
      {
        label: '家具',
        value: '家具'
      },
      {
        label: '健身器材',
        value: '健身器材'
      },
    ]
  },
  mounted()
  {
    if(this.$cookies.isKey('vid') === false)
      this.$router.push('login')
    let id = this.$cookies.get('vid')

  },
  methods: {
    save(){
      axios.post('http://localhost:8181/gmGoods/add',this.gmGoods).then(function (response) {
        if(response.data){
          alert('商品信息提交成功！')
          location.reload()
          // this.$router.push({
          //   path: '/admin/goods/list'
          // })

        }
        else
          alert('商品信息提交失败！')
      })

    },
    handleChange(info) {
      if (info.file.status === 'uploading') {
        this.loading = true;
        return;
      }
      if (info.file.status === 'done') {
        // Get this url from response in real world.
        getBase64(info.file.originFileObj, imageUrl => {
          this.imageUrl = imageUrl;
          this.loading = false;
        });
      }
    },
    beforeUpload(file) {
      const isJpgOrPng = file.type === 'image/jpeg' || file.type === 'image/png';
      if (!isJpgOrPng) {
        this.$message.error('You can only upload JPG file!');
      }
      const isLt2M = file.size / 1024 / 1024 < 2;
      if (!isLt2M) {
        this.$message.error('Image must smaller than 2MB!');
      }
      return isJpgOrPng && isLt2M;
    },
  }
}
</script>
