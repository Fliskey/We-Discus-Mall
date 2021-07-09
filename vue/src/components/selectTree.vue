<template>
  <el-select
    v-model="treeLabel"
    v-bind="$attrs"
    :multiple='multiple'
    ref="select"
    @clear="handleSelectClear"
    @remove-tag="handleRemoveTag">
    <el-option :value="treeVal">
      <el-input placeholder="输入关键字进行过滤" v-model="filterText" />
      <el-tree
        ref="tree"
        :data="data"
        highlight-current
        :show-checkbox='multiple'
        :props='props'
        @check='handleNodeCheck' />
    </el-option>
  </el-select>
</template>
<script>
  import { Input, Select, Tree, Option } from 'element-ui'
  export default {
    components: {
      [Input.name]: Input,
      [Select.name]: Select,
      [Tree.name]: Tree,
      [Option.name]: Option
    },
    props: {
      props: { //配置项
        type: Object,
        default: () => {
          return {
            value: 'id', // ID字段名
            label: 'label', // 显示名称
            children: 'children' // 子级字段名
          }
        }
      },
      data: {
        type: Array,
        default: () => []
      },
      value: { //初始值
        type: String,
        default: () => { return null }
      },
      clearable: { //可清空选项
        type: Boolean,
        default: true
      },

      accordion: { //自动收起
        type: Boolean,
        default: true
      },
      multiple: {
        type: Boolean,
        default: false
      },
      leafOnly: { // 是否只选择叶子节点
        type: Boolean,
        default: false
      }
    },
    data () {
      return {
        treeVal: [], // 初始值
        treeLabel: [],
        defaultExpandedKey: [],
        filterText: ''
      }
    },
    methods: {
      handleNodeCheck () {
        console.log(555)
        const labelTemp = []
        const valTemp = []
        // 筛选选中节点
        const checked = this.$refs.tree.getCheckedNodes(this.leafOnly)
        checked.map(item => {
          // input选中label回显
          valTemp.push({id: item.id})
          labelTemp.push({ label: item.name, id: item.id })
        })
        this.treeVal = valTemp
        this.treeLabel = labelTemp
      },

      handleSelectClear () {
        this.$emit('input', '')
      },

      handleRemoveTag (value) {
        let fileterValue = this.value.filter(item => item !== value)
        this.$emit('input', fileterValue)
      }
    }
  }
</script>
