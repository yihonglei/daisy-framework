import request from '../request/request.js'

const api = function() {
  return {
    async getPushPaperList(data) {
      return await request({
        url: '/sys/api/pushPaperList', //'/vue-element-admin/routes',
        method: 'post',
        data
      })
    },

    async getPullPaperList(data) {
      return await request({
        url: '/sys/api/pullPaperList', //'/vue-element-admin/routes',
        method: 'post',
        data
      })
    },

    async sysUpdatePaperEnable(data) {
      return await request({
        url: '/sys/api/sysUpdatePaperEnable', //'/vue-element-admin/routes',
        method: 'post',
        data
      })
    },

    async sysUpdatePaperChosen(data) {
      return await request({
        url: '/sys/api/sysUpdatePaperChosen', //'/vue-element-admin/routes',
        method: 'post',
        data
      })
    },
    
    async sysDeletePushPaper(data) {
      return await request({
        url: '/sys/api/sysDeletePushPaper', //'/vue-element-admin/routes',
        method: 'post',
        data
      })
    },
    async sysDeletePullPaper(data) {
      return await request({
        url: '/sys/api/sysDeletePullPaper', //'/vue-element-admin/routes',
        method: 'post',
        data
      })
    },
    
    
  };
};

export default api;
