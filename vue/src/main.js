import Vue from 'vue'
import App from './App.vue'
import router from './router/'
import VueCookies from 'vue-cookies'
import './plugins/axios'
import { sha256 } from 'js-sha256'
import './components/lazy_antd'
import Vuex from 'vuex'
Vue.prototype.$sha256 = sha256

Vue.use(VueCookies)
Vue.use(Vuex)

// eslint-disable-next-line
import elem from './elem/'
import 'ant-design-vue/dist/antd.css'
import 'normalize.css/normalize.css'
import './style/common.less'
import http from './axios'
Vue.prototype.http = http

new Vue({
  router,
  render: h => h(App),
  store
}).$mount('#app')

const store = new Vuex.Store({
  state: {
    count: 0
  },
  mutations: {
    increment (state) {
      state.count++
    }
  }
  // store.commit('increment')
  //
  // console.log(store.state.count) // -> 1
})
