import request from '../request/request.js'

const api = function() {
  return {

    async getAllPermissionRoutes(data) {
       return await request({
         url: '/sys/api/allPermissionRoutes', //'/vue-element-admin/routes',
         method: 'post',
         data
       })
     },

    async addSysRoute(data) {
      return await request({
        url: '/sys/api/addSysRoute', //'/vue-element-admin/user/login',
        method: 'post',
        data
      })
    },
    
    async editSysRoute(data) {
      return await request({
        url: '/sys/api/editSysRoute', //'/vue-element-admin/user/login',
        method: 'post',
        data
      })
    },

    async delSysRoute(data) {
      return await request({
        url: '/sys/api/delSysRoute', //'/vue-element-admin/user/login',
        method: 'post',
        data
      })
    }

  };
};

export default api;
