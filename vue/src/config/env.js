// 配置编译环境和线上环境之间的切换

let baseUrl = '';
let iconfontVersion = ['567566_pwc3oottzol', '1066523_6bvkeuqao36','2227985_cwdm71juf6g'];
let iconfontUrl = baseUrl+'/cdn/iconfont/font_$key.css';
let codeUrl = baseUrl + '/code'

// 测试服务器地址，发布时需改为正式环境
let apiUrl = 'https://apitest.minmaxtec.com/'

export {
    baseUrl,
    iconfontUrl,
    iconfontVersion,
    codeUrl,
    apiUrl
}
