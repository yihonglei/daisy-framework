import request from '../request/request.js'

const api = function() {
  return {
   async getImageList(data) {
      return await request({
        url: '/sys/api/sysImageList', //'/vue-element-admin/routes',
        method: 'post',
        data
      })
    },
    
    async sysUpdateEnable(data) {
       return await request({
         url: '/sys/api/sysUpdateEnable', //'/vue-element-admin/routes',
         method: 'post',
         data
       })
     },
     
    async sysDeleteImage(data) {
       return await request({
         url: '/sys/api/sysDeleteImage', //'/vue-element-admin/routes',
         method: 'post',
         data
       })
     },
     
     async sysAddImage(data) {
        return await request({
          url: '/sys/api/sysAddImage', //'/vue-element-admin/routes',
          method: 'post',
          data
        })
      },
      
      async sysUpdateImage(data) {
         return await request({
           url: '/sys/api/sysUpdateImage', //'/vue-element-admin/routes',
           method: 'post',
           data
         })
       },
  };
};

export default api;
