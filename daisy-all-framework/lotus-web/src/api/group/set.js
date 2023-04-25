import request from '../request/request.js'

const api = function() {
  return {
   async getSetList(data) {
      return await request({
        url: '/sys/api/sysSetList', //'/vue-element-admin/routes',
        method: 'post',
        data
      })
    },

    async sysUpdateSet(data) {
       return await request({
         url: '/sys/api/sysUpdateSet', //'/vue-element-admin/routes',
         method: 'post',
         data
       })
     },

     async getFeeList(data) {
        return await request({
          url: '/sys/api/sysFeeList', //'/vue-element-admin/routes',
          method: 'post',
          data
        })
      },

    async sysUpdateFee(data) {
       return await request({
         url: '/sys/api/sysUpdateFee', //'/vue-element-admin/routes',
         method: 'post',
         data
       })
     },

  async sysDeleteFee(data) {
       return await request({
         url: '/sys/api/sysDeleteFee', //'/vue-element-admin/routes',
         method: 'post',
         data
       })
     },


  };
};

export default api;
