import Vue from 'vue'
import App from './App.vue'
import router from './router/'
import VueCookies from 'vue-cookies'
import './plugins/axios'
import { sha256 } from 'js-sha256'
Vue.prototype.$sha256 = sha256

Vue.use(VueCookies)

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
