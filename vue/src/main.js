import Vue from 'vue'
import App from './App.vue'
import router from './router/'
import './plugins/axios'
// eslint-disable-next-line
import elem from './elem/'
import 'ant-design-vue/dist/antd.css'
import 'normalize.css/normalize.css'
import './style/common.less'
import http from './axios'
Vue.prototype.http = http

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
