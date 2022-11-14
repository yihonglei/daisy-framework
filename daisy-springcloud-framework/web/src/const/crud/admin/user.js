import {isExsit} from '@/api/admin/user'

export const validateUsername = (rule, value, callback) => {
    if (!value) {
        return callback(new Error('请输入用户名'))
    }
    let flag = new RegExp(/^([a-z\d]+?)$/).test(value)
    if (!flag) {
        callback(new Error('用户名支持小写英文、数字'))
    }
    isExsit({username: value}).then(response => {
        if (window.boxType === 'edit') callback()
        let result = response.data.data
        if (result) {
            return callback(new Error('用户名已经存在'))
        } else {
            return callback()
        }
    })
}

// 设置密码校验规则
export const checkPassword = (rule, value, callback) => {
    if (window.boxType === 'edit') {
        return callback()
    }
    if (!value) {
        callback(new Error('请输入密码'))
    } else if (value.length <= 6) {
        callback(new Error('请输入6位以上密码'))
    } else {
        callback()
    }
}

// 设置手机号的验证规则
export const checkPhone = (rule, value, callback) => {
    if (!value) {
        callback(new Error('请输入手机号'))
    } else {
        const reg = /^1[3|4|5|7|8][0-9]\d{8}$/
        if (!reg.test(value)) {
            return callback(new Error('请输入正确的电话'))
        }
    }

    isExsit({phone: value}).then(response => {
        if (window.boxType === 'edit') callback()
        let result = response.data.data
        if (result) {
            return callback(new Error('手机号已经存在'))
        } else {
            return callback()
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
    dialogWidth: '50%',
    editBtn: false,
    delBtn: false,
    align: 'center',
    addBtn: false,
    column: [{
        fixed: true,
        label: 'id',
        prop: 'userId',
        span: 24,
        hide: true,
        editDisplay: false,
        addDisplay: false
    }, {
        fixed: true,
        label: '用户名',
        prop: 'username',
        editDisabled: true,
        slot: true,
        search: true,
        span: 24,
        rules: [{
            required: true,
            message: '请输入用户名'
        },
            {
                min: 3,
                max: 20,
                message: '长度在 3 到 20 个字符',
                trigger: 'blur'
            },
            {validator: validateUsername, trigger: 'blur'}
        ]
    }, {
        label: '密码',
        prop: 'password',
        type: 'password',
        value: '',
        hide: true,
        span: 24,
        rules: [{validator: checkPassword, trigger: 'blur'}]
    }, {
        label: '所属部门',
        prop: 'deptId',
        formslot: true,
        slot: true,
        span: 24,
        hide: true,
        rules: [{
            required: true,
            message: '请选择部门',
            trigger: 'change'
        }]
    }, {
        label: '手机号',
        prop: 'phone',
        value: '',
        span: 24,
        rules: [{
            required: true,
            message: '手机号不能为空',
            trigger: 'blur'
        }, {
            validator: checkPhone,
            trigger: 'blur'
        }]
    }, {
        label: '角色',
        prop: 'role',
        formslot: true,
        slot: true,
        overHidden: true,
        span: 24,
        rules: [{
            required: true,
            message: '请选择角色',
            trigger: 'blur'
        }]
    }, {
        label: '部门',
        prop: 'deptName',
        overHidden: true,
        addDisplay: false,
        editDisplay: false,
        span: 24,
    }, {
        label: '岗位',
        prop: 'post',
        width: 168,
        overHidden: true,
        formslot: true,
        slot: true,
        span: 24,
        rules: [{
            required: true,
            message: '请选择岗位',
            trigger: 'blur'
        }]
    }, {
        label: '状态',
        prop: 'lockFlag',
        type: 'radio',
        slot: true,
        border: true,
        span: 24,
        dicUrl: '/admin/dict/key/user_lock_flag',
        rules: [{
            required: true,
            message: '请选择状态',
            trigger: 'blur'
        }]
    }, {
        width: 120,
        label: '创建时间',
        prop: 'createTime',
        type: 'datetime',
        format: 'YYYY-MM-DD',
        editDisabled: true,
        addDisplay: false,
        span: 24
    }]
}
