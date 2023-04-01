export const tableOption = {
    border: true,
    index: true,
    indexLabel: '序号',
    stripe: true,
    menuAlign: 'center',
    align: 'center',
    refreshBtn: false,
    showClomnuBtn: false,
    searchMenuSpan: 6,
    column: [{
        label: '标识',
        prop: 'dictKey',
        search: true,
        editDisabled: true,
        rules: [{
            required: true,
            message: '请输入字典标识',
            trigger: 'blur'
        }]
    }, {
        label: '描述',
        prop: 'description',
        rules: [{
            required: true,
            message: '请输入字典描述',
            trigger: 'blur'
        }]
    }, {
        label: '字典类型',
        prop: 'systemFlag',
        type: 'radio',
        border: true,
        dicUrl: '/admin/dict/key/dict_type',
        rules: [{
            required: true,
            message: '请选择字典类型',
            trigger: 'blur'
        }],
        search: true
    }, {
        label: '备注信息',
        prop: 'remark'
    }, {
        width: 150,
        label: '创建时间',
        prop: 'createTime',
        type: 'datetime',
        addDisplay: false,
        editDisabled: true,
        format: 'YYYY-MM-DD HH:mm',
        valueFormat: 'YYYY-MM-DD HH:mm:ss'
    }]
}

export const tableDictItemOption = {
    border: true,
    index: true,
    indexLabel: '序号',
    stripe: true,
    menuAlign: 'center',
    align: 'center',
    refreshBtn: false,
    showClomnuBtn: false,
    searchSize: 'mini',
    column: [{
        label: '标识',
        prop: 'dictKey',
        addDisabled: true,
        editDisabled: true
    }, {
        width: 150,
        label: '数据值',
        prop: 'value',
        rules: [{
            required: true,
            message: '请输入数据值',
            trigger: 'blur'
        }]
    }, {
        label: '标签名',
        prop: 'label',
        rules: [{
            required: true,
            message: '请输入标签名',
            trigger: 'blur'
        }]
    }, {
        label: '展示类型',
        prop: 'type',
        type: 'select',
        border: true,
        dicUrl: '/admin/dict/key/dict_css_type',
        rules: [{
            // required: true,
            message: '请选择展示类型',
            trigger: 'blur'
        }],
        search: true
    }, {
        label: '描述',
        prop: 'description',
        rules: [{
            required: true,
            message: '请输入字典描述',
            trigger: 'blur'
        }]
    }, {
        label: '排序',
        prop: 'sortOrder',
        type: 'number',
        rules: [{
            required: true,
            message: '请输入排序',
            trigger: 'blur'
        }]
    }, {
        label: '备注信息',
        prop: 'remark'
    }]
}
