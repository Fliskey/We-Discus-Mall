/**
 * 全站http配置
 *
 * axios参数说明
 * isSerialize是否开启form表单提交
 * isToken是否需要token
 */
import axios from 'axios'
import NProgress from 'nprogress' // progress bar
import 'nprogress/nprogress.css' // progress bar style

//跨域请求，允许保存cookie
axios.defaults.withCredentials = false
axios.defaults.baseURL = require('urlConfig').apiUrl
//HTTPrequest拦截
axios.interceptors.request.use(config => {
  NProgress.start()
  return config
}, error => {
  return Promise.reject(error)
})
//HTTPresponse拦截
axios.interceptors.response.use(res => {
  NProgress.done()
  return res
}, error => {
  console.log(error)
  NProgress.done()
  return Promise.reject(new Error(error))
})

export default axios
