import request from '../request/request.js'

const api = function() {
  return {
    async login(data) {
      return await request({
        url: '/sys/api/doLogin', //'/vue-element-admin/user/login',
        method: 'post',
        data
      })
    },

    async getInfo(data) {
      return await request({
        url: '/sys/api/userinfo', //'/vue-element-admin/user/info',
        method: 'post',
        data
      })
    },

   async logout(data) {
      return await request({
        url: '/sys/api/logout', //'/vue-element-admin/user/logout',
        method: 'post',
        data
      })
    },

   async getSysUsersList(data) {
      return await request({
        url: '/sys/api/getSysUsersList', //'/vue-element-admin/user/logout',
        method: 'post',
        data
      })
    },

   async addSysUser(data) {
      return await request({
        url: '/sys/api/addSysUser', //'/vue-element-admin/user/logout',
        method: 'post',
        data
      })
    },

   async updateSysUser(data) {
      return await request({
        url: '/sys/api/updateSysUser', //'/vue-element-admin/user/logout',
        method: 'post',
        data
      })
    },

   async delSysUser(data) {
      return await request({
        url: '/sys/api/delSysUser', //'/vue-element-admin/user/logout',
        method: 'post',
        data
      })
    },

   async resetSysPWD(data) {
      return await request({
        url: '/sys/api/resetSysPWD', //'/vue-element-admin/user/logout',
        method: 'post',
        data
      })
    },

   async modifySysPWD(data) {
      return await request({
        url: '/sys/api/modifySysPWD', //'/vue-element-admin/user/logout',
        method: 'post',
        data
      })
    },

    async companyUserList(data) {
      return await request({
        url: '/sys/api/companyUserList', //'/vue-element-admin/user/logout',
        method: 'post',
        data
      })
    },

    async sysUpdateUserStatus(data) {
       return await request({
         url: '/sys/api/sysUpdateUserStatus', //'/vue-element-admin/routes',
         method: 'post',
         data
       })
     },
     
     async sysUpdateUserEnable(data) {
        return await request({
          url: '/sys/api/sysUpdateUserEnable', //'/vue-element-admin/routes',
          method: 'post',
          data
        })
      },
     

  };
};

export default api;
