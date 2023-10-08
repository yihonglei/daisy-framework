import request from '../request/request.js'

const api = function() {
  return {
   async getMessageList(data) {
      return await request({
        url: '/sys/api/sysMessageList', //'/vue-element-admin/routes',
        method: 'post',
        data
      })
    },
    
  };
};

export default api;
