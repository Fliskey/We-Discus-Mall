/**
 * 全局配置文件
 */
export default {
  title: "MinMax Service Center",
  logo: "F",
  key: 'saber',//配置主键,目前用于存储
  indexTitle: '富亿享',
  clientId: 'saber', // 客户端id
  clientSecret: 'saber_secret', // 客户端密钥
  tenantMode: true, // 是否开启租户模式
  tenantId: "000000", // 管理组租户编号
  captchaMode: false, // 是否开启验证码模式
  lockPage: '/lock',
  tokenTime: 3000,
  codeTime:119, // 倒计时总体设置
  //http的status默认放行不才用统一处理的,
  statusWhiteList: [41,480,490,491,492,493,405],
  //配置首页不可关闭
  isFirstPage: false,
  fistPage: {
    label: "首页",
    value: "/wel/index",
    path:"/wel/index",
    params: {},
    query: {},
    meta: {
      i18n: 'dashboard'
    },
    group: [],
    close: false
  },
  //配置菜单的属性
  menu: {
    iconDefault: 'iconfont icon-caidan',
    props: {
      label: 'name',
      path: 'path',
      icon: 'source',
      children: 'children'
    }
  },
  // 流程设计器地址
  flowDesignUrl: 'http://localhost:9999',
}
