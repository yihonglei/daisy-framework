import {rule} from "@/util/rule";

import {getObj} from '@/api/admin/sys-public-param'


var validateParam = (rule, value, callback) => {
    getObj(value).then(response => {
        if (window.boxType === 'edit' && window.tableForm.publicKey === value) callback()
        const result = response.data.data
        if (result !== null) {
            callback(new Error('参数键已经存在'))
        } else {
            callback()
        }
    })
}

export const tableOption = {
    border: true,
    index: true,
    indexLabel: '序号',
    stripe: true,
    menuAlign: 'center',
    searchMenuSpan: 6,
    column: [
        {
            label: '名称',
            search: true,
            prop: 'publicName',
            rules: [
                {required: true, message: '请输名称', trigger: 'blur'},
                {max: 30, message: '长度在 30 个字符', trigger: 'blur'},
                {validator: rule.validatorNameCn, trigger: 'blur'}
            ]
        },
        {
            label: '键',
            prop: 'publicKey',
            search: true,
            rules: [
                {required: true, message: '请输入键', trigger: 'blur'},
                {validator: rule.validatorKey, trigger: 'blur'},
                {validator: validateParam, trigger: 'blur'},
            ]

        },
        {
            label: '值',
            overHidden: true,
            prop: 'publicValue',
            rules: [
                {required: true, message: '请输入值', trigger: 'blur'}
            ]
        },
        {
            label: '编码',
            prop: 'validateCode'
        },
        {
            label: '类型',
            prop: 'systemFlag',
            type: 'select',
            dicUrl: '/admin/dict/key/dict_type',
            rules: [{
                required: true,
                message: '请输入类型',
                trigger: 'blur'
            }],
            search: true
        },
        {
            label: '状态',
            prop: 'status',
            width: 80,
            type: 'select',
            dicUrl: '/admin/dict/key/status_type',
            rules: [
                {required: true, message: '请输入值', trigger: 'blur'}
            ]
        },
        {
            label: '类型',
            prop: 'publicType',
            width: 80,
            type: 'select',
            dicUrl: '/admin/dict/key/param_type',
            rules: [{
                required: true,
                message: '请选择类型',
                trigger: 'blur'
            }]
        }
        // 省略 ...
    ]
}
