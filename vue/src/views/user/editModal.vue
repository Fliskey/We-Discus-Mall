<template>
  <a-modal v-model="visible" title="编辑用户信息" ok-text="确认" cancel-text="取消" @ok="show">
    <a-form-model ref='form' :model="form" :rules='rules' :label-col="{ span: 3 }" :wrapper-col="{ span: 21 }">
      <a-form-model-item prop='no' label="学号">
        <a-input placeholder='学号' v-model='form.no' />
      </a-form-model-item>
      <a-form-model-item prop='name' label='姓名'>
        <a-input v-model='form.name' placeholder='姓名' />
      </a-form-model-item>
      <a-form-model-item prop='type' label='校区' >
        <a-radio-group v-model="form.type">
          <a-radio :value="1">思明</a-radio>
          <a-radio :value="2">翔安</a-radio>
        </a-radio-group>
    </a-form-model-item>
    </a-form-model>
  </a-modal>
</template>
<script>
  export default {
    data () {
      return {
        visible: false,
        form: {
          type: 1
        },
        rules: {}
      }
    },
    methods: {
      show () {
        this.visible = !this.visible
      }
    }
  }
</script>
