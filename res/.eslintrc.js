module.exports = {
  root: true,
  env: {
    node: true
  },
  'extends': [
    'plugin:vue/essential',
    'eslint:recommended'
  ],
  rules: {
    /*
    off/0,不启用；warn/1警告；error/2 错误
    */
    'no-console': process.env.NODE_ENV === 'production' ? 'error' : 'off',
    'no-debugger': process.env.NODE_ENV === 'production' ? 'error' : 'off',
    "no-func-assign": 2,//禁止重复的函数声明
    "no-inline-comments": 0,//禁止行内备注
    "no-irregular-whitespace": 2,//不能有不规则的空格
    "no-mixed-spaces-and-tabs": [2, false],//禁止混用tab和空格
    "no-whitespace-before-property":2,
    "linebreak-style": [0, "windows"],//换行风格
    "no-multiple-empty-lines": [1, {"max": 1}],//空行最多不能超过1行
    "indent": [2, 2],//缩进风格
    "object-curly-spacing": [0, "never"],//大括号内是否允许不必要的空格
    "quotes": [1, "single"],//引号类型 `` "" ''
    "semi": [2, 'never'],// js语句结尾不使用分号
    "vue/script-indent": ['error', 2, {'baseIndent': 1}],
    'comma-dangle': [2, 'never'],// 数组和对象键值对最后一个逗号， never参数：不能带末尾的逗号, always参数：必须带末尾的逗号
    "keyword-spacing": 2,
    "key-spacing":[2,{
      "beforeColon": false,
      "afterColon": true
    }],
    'comma-spacing': [2, {
      'before': false,
      'after': true
    }],// 控制逗号前后的空格
    'eol-last': 2,    // 文件末尾强制换行
    // 禁止使用多个空格
    'no-multi-spaces': [2,{
      "ignoreEOLComments": true,
      "exceptions": { "Property": true }
    }],
    "space-after-keywords": [0, "always"],
    'no-trailing-spaces': 2,    // 禁用行尾空格
    'block-spacing':[2,'always'],
    'no-unreachable': 2,// 禁止在return、throw、continue 和 break语句之后出现不可达代码
    'space-unary-ops': [2, {
      'words': true,
      'nonwords': false
    }],// 强制在一元操作符前后使用一致的空格
    "space-before-function-paren": ["error", {
        "anonymous": "always",
        "named": "always",
        "asyncArrow": "always"
    }],
  },

  parserOptions: {
    parser: 'babel-eslint'
  },
  overrides: [
    {
      "files": ["*.vue"],
      "rules": {
        "indent": "off",
      }
    }
  ]
}