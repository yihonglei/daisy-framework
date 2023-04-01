export const tableOption = {
    border: true,
    index: true,
    indexLabel: '序号',
    stripe: true,
    menuAlign: 'center',
    menuWidth: 150,
    align: 'center',
    addBtn: false,
    editBtn: false,
    viewBtn: true,
    props: {
        label: 'label',
        value: 'value'
    },
    column: [{
        label: '类型',
        prop: 'type',
        type: 'select',
        dicUrl: '/admin/dict/key/log_type',
        search: true
    }, {
        label: '标题',
        prop: 'title'
    }, {
        label: 'IP地址',
        prop: 'remoteAddr',
        search: true
    }, {
        label: '请求方式',
        prop: 'method'
    }, {
        label: '客户端',
        prop: 'serviceId'
    }, {
        width: 80,
        label: '请求时间',
        prop: 'time'
    }, {
        width: 150,
        label: '创建时间',
        prop: 'createTime',
        type: 'datetime',
        format: 'YYYY-MM-DD HH:mm',
        valueFormat: 'YYYY-MM-DD HH:mm:ss',
        rangeSeparator: '-',
        search: true,
        searchRange: true
    }, {
        width: 180,
        label: '异常日志',
        prop: 'exception',
        hide: true
    }]
}
