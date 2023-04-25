import request from '../request/request.js'

const api = function() {
  return {
    async getFinanceList(data) {
      return await request({
        url: '/sys/api/sysFinanceList', //'/vue-element-admin/routes',
        method: 'post',
        data
      })
    },

    async verifyCashOut(data) {
      return await request({
        url: '/sys/api/sysVerifyCashOut', //'/vue-element-admin/routes',
        method: 'post',
        data
      })
    },

    async financeCashoutAny(data) {
      return await request({
        url: '/sys/api/sysFinanceCashoutAny', //'/vue-element-admin/routes',
        method: 'post',
        data
      })
    },

    async verifyOutMoney(data) {
      return await request({
        url: '/sys/api/sysVerifyOutMoney', //'/vue-element-admin/routes',
        method: 'post',
        data
      })
    },

    async sysChargeList(data) {
      return await request({
        url: '/sys/api/sysChargeList', //'/vue-element-admin/routes',
        method: 'post',
        data
      })
    },

    //充值统计
    async financeChargeAny(data) {
      return await request({
        url: '/sys/api/sysFinanceChargeAny', //'/vue-element-admin/routes',
        method: 'post',
        data
      })
    },

    async getFundList(data) {
      return await request({
        url: '/sys/api/sysGetFundList', //'/vue-element-admin/routes',
        method: 'post',
        data
      })
    },



  };
};

export default api;
