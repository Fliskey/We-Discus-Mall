import Vue from 'vue'

import '@/style/theme.less'
import {
  Button,
  FormModel,
  Form,
  Input,
  Icon,
  Checkbox,
  Row,
  Col,
  Layout,
  Menu,
  Radio,
  Modal,
  Message,
  Table,
  Card,
  List,
  Tag,
  Avatar,
  Select,
  InputNumber,
  Upload
} from 'ant-design-vue'
Vue.prototype.$confirm = Modal.confirm
Vue.prototype.$message = Message
Vue.use(FormModel)
Vue.use(Form)
Vue.use(Button)
Vue.use(Input)
Vue.use(Icon)
Vue.use(Checkbox)
Vue.use(Row)
Vue.use(Col)
Vue.use(Layout)
Vue.use(Menu)
Vue.use(Radio)
Vue.use(Table)
Vue.use(Modal)
Vue.use(Card)
Vue.use(List)
Vue.use(Tag)
Vue.use(Avatar)
Vue.use(InputNumber)
Vue.use(Select)
Vue.use(Upload)
Vue.component(Input.TextArea.name, Input.TextArea)
