<template>
  <a-card :body-style="{padding: '24px 32px'}" :bordered="false">
    <a-form>
      <a-form-item
        label="商品名称"
        :labelCol="{span: 7}"
        :wrapperCol="{span: 10}">
        <a-input placeholder="请输入商品名称" />
      </a-form-item>
      <a-form-item
        label="商品数量"
        :labelCol="{span: 7}"
        :wrapperCol="{span: 10}">
        <a-input-number style='width:100%' placeholder="请输入商品数量"/>
      </a-form-item>
      <a-form-item
        label="商品单价"
        :labelCol="{span: 7}"
        :wrapperCol="{span: 10}">
        <a-input prefix="￥" placeholder="请输入商品单价" suffix="RMB" />
        <!-- <a-input-number :prefix="￥" :placeholder="请输入商品单价" /> -->
      </a-form-item>
      <a-form-item
        label="商品描述"
        :labelCol="{span: 7}"
        :wrapperCol="{span: 10}">
        <a-textarea rows="4" placeholder="请输入商品描述"/>
      </a-form-item>
      <a-form-item
        label="商品类型"
        :labelCol="{span: 7}"
        :wrapperCol="{span: 10}">
          <a-select v-model='form.type'>
            <a-select-option :value="4">书籍</a-select-option>
            <a-select-option :value="5">生活用品</a-select-option>
            <a-select-option :value="6">票券</a-select-option>
          </a-select>
      </a-form-item>
      <a-form-item
        label="商品类型"
        :labelCol="{span: 7}"
        :wrapperCol="{span: 10}">
          <a-upload
            name="avatar"
            list-type="picture-card"
            class="avatar-uploader"
            :show-upload-list="false"
            :before-upload="beforeUpload"
            @change="handleChange"
            action="https://www.mocky.io/v2/5cc8019d300000980a055e76">
            <img v-if="imageUrl" width='100px' height='100px' :src="imageUrl" alt="avatar" />
            <div v-else>
              <a-icon :type="loading ? 'loading' : 'plus'" />
              <div class="ant-upload-text">
                Upload
              </div>
            </div>
          </a-upload>
      </a-form-item>
      <a-form-item style="margin-top: 24px" :wrapperCol="{span: 10, offset: 7}">
        <a-button type="primary" @click='save'>保存</a-button>
        <a-button @click='save' style="margin-left: 8px">立即发布</a-button>
      </a-form-item>
    </a-form>
  </a-card>
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
        form: {
          type: 4
        },
        loading: false,
        imageUrl: ''
      }
    },
    methods: {
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
      save () {
        this.http.get('data.json').then(() => {
          this.$message.success('操作成功')
        })
      }
    }
  }
</script>
