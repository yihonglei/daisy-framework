// var OSS = require('ali-oss');
import OSS from "ali-oss";
export function client(){
  var client = new OSS({
      endpoint: 'oss-cn-beijing.aliyuncs.com',//填写Bucket所在地域
      accessKeyId: 'LTAIim0z9v46YVcA',
      accessKeySecret: 'RYzU1u6mq5yqupEbFGnof6J87LvXOt',
      bucket: 'bzrrmedia',// 填写Bucket名称。
  })  //后端提供数据 
  return client
}

/**
 * 生成随机uuid
 */
export const getFileNameUUID = () => {
  function rx() {
    return (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1)
  }
  return `${+new Date()}_${rx()}${rx()}`
}