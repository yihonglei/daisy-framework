import request from '@/router/axios'

export function fetchList(query) {
    return request({
        url: '/admin/token/page',
        method: 'get',
        params: query
    })
}

export function delObj(token) {
    return request({
        url: '/admin/token/' + token,
        method: 'delete'
    })
}
