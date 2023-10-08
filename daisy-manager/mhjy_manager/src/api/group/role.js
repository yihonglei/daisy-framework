import request from '../request/request.js'

const api = function() {
  return {
   async getRoutes(data) {
      return await request({
        url: '/sys/api/allRoutes', //'/vue-element-admin/routes',
        method: 'post',
        data
      })
    },

   async getRoles(data) {
      return await request({
        url: '/sys/api/allRoles', //'/vue-element-admin/roles',
        method: 'post',
        data
      })
    },

   async addRole(data) {
      return await request({
        url: '/sys/api/addRole', //  '/vue-element-admin/role',
        method: 'post',
        data
      })
    },

   async updateRole(data) {
      return await request({
        url: '/sys/api/editRole', //`/vue-element-admin/role/${id}`,
        method: 'post',
        data
      })
    },

   async deleteRole(data) {
      return await request({
        url: '/sys/api/delRole', //`/vue-element-admin/role/${id}`,
        method: 'delete',
        data
      })
    }
  };
};

export default api;
