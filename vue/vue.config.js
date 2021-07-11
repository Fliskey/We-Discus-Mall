const path = require('path');
const assetsCDN = {
  // webpack build externals
  externals: {
    vue: 'Vue',
    'vue-router': 'VueRouter',
    vuex: 'Vuex',
    axios: 'axios'
  },
  css: [],

  jsDev:[
    '/js/env.js'
  ],
  js: [
    '/js/env.js',
    '//cdn.jsdelivr.net/npm/vue@2.6.10/dist/vue.min.js',
    '//cdn.jsdelivr.net/npm/vue-router@3.1.3/dist/vue-router.min.js',
    '//cdn.jsdelivr.net/npm/vuex@3.1.1/dist/vuex.min.js',
    '//cdn.jsdelivr.net/npm/axios@0.19.0/dist/axios.min.js',
  ]
}

module.exports = {
  devServer: {
    disableHostCheck: true,
  },

  css: {
    loaderOptions: {
      less: {
        javascriptEnabled: true,
      }
    }
  },
  pluginOptions: {
    'style-resources-loader': {
      preProcessor: 'less',
      patterns: [path.resolve(__dirname, "./src/style/variables.less")],
    }
  },
  //路径前缀
  publicPath: "/",
  lintOnSave: true,
  // 输出文件目录
  outputDir: "ROOT",
  productionSourceMap: false,
  chainWebpack: (config) => {
    /*
    config.optimization.splitChunks({
      chunks: 'all',
      cacheGroups: {
        echarts: { // 图表抽离出来单独打包
          name: 'chunk-echarts',
          priority: 20,
          test: /[/]node_modules[/]_?echarts(.*)/
        }
      }
    })*/
    const oneOfsMap = config.module.rule("less").oneOfs.store;
    oneOfsMap.forEach(item => {
      item
        .use("style-resources-loader")
        .loader("style-resources-loader")
        .options({
          // or an array : ["./path/to/vars.less", "./path/to/mixins.less"] 这里的路径不能使用@，否则会报错
          patterns: [path.resolve(__dirname, "./src/style/variables.less")],
        })
        .end()
    })
    config.optimization.splitChunks({
      cacheGroups: {
        element: {  //自定义打包模块
          chunks: 'all',
          test: /[\\/]node_modules[\\/](ant-design-vue)/,
          priority: 2 //优先级，先打包到哪个组里面，值越大，优先级越高
        },
        element: {  //自定义打包模块
          chunks: 'all',
          test: /[\\/]node_modules[\\/]/,
          priority: 1 //优先级，先打包到哪个组里面，值越大，优先级越高
        },

        /*polyfill: {  //自定义打包模块
          chunks: 'all',
          test: /[\\/]node_modules[\\/](babel-polyfill|core-js|babel-runtime|async-validator|resize-observer-polyfill|deepmerge|vue-loader|throttle-debounce)/,
          priority: 20 //优先级，先打包到哪个组里面，值越大，优先级越高
        },*/
      }
    })

    config
    .plugin('html')
    .tap(args => {
      args[0].title= '二手交易系统'
      return args
    });

    config.plugin('html').tap(args => {
      console.log(process.env.NODE_ENV)
      args[0].cdn = process.env.NODE_ENV === 'production' ? assetsCDN.js : assetsCDN.jsDev
      return args
    });

    // 移除 prefetch 插件
      config.plugins.delete('prefetch');
      // 移除 preload 插件
      config.plugins.delete('preload');

    let externals = {urlConfig: 'urlConfig'};

    if (process.env.NODE_ENV === 'production') {     // 分析
      Object.assign(externals,assetsCDN.externals);
      config
        .plugin('webpack-bundle-analyzer')
        .use(require('webpack-bundle-analyzer').BundleAnalyzerPlugin)
    }
    //忽略的打包文件
    config.externals(externals);
    const entry = config.entry('app')
    entry
      .add('babel-polyfill')
      .end()
  }
}
