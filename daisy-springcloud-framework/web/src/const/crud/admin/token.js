export const tableOption = {
    border: true,
    index: true,
    indexLabel: '序号',
    stripe: true,
    menuAlign: 'center',
    align: 'center',
    searchMenuSpan: 6,
    viewBtn: false,
    addBtn: false,
    editBtn: false,
    column: [{
        label: '用户名',
        prop: 'username',
        align: 'center',
        slot: true
    }, {
        label: "客户端",
        prop: "clientId",
        align: "center"
    }, {
        label: '令牌',
        prop: 'accessToken',
        align: 'center',
        overHidden: true
    }, {
        label: '创建时间',
        prop: 'issuedAt',
        align: 'center'
    }, {
        label: '过期时间',
        prop: 'expiresAt',
        align: 'center'
    }]
}
