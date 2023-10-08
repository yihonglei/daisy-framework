import request from '@/router/axios'

export function fetchList(query) {
    return request({
        url: '/admin/user/page',
        method: 'get',
        params: query
    })
}

export function addObj(obj) {
    return request({
        url: '/admin/user',
        method: 'post',
        data: obj
    })
}

export function getObj(id) {
    return request({
        url: '/admin/user/' + id,
        method: 'get'
    })
}

export function delObj(id) {
    return request({
        url: '/admin/user/' + id,
        method: 'delete'
    })
}

export function putObj(obj) {
    return request({
        url: '/admin/user',
        method: 'put',
        data: obj
    })
}

export function isExsit(params) {
    return request({
        url: '/admin/user/check/exsit',
        method: 'get',
        params: params
    })
}

// 更改个人信息
export function editInfo(obj) {
    return request({
        url: '/admin/user/edit',
        method: 'put',
        data: obj
    })
}
