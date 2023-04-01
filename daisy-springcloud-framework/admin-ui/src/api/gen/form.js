import request from '@/router/axios'

export function fetchList(query) {
    return request({
        url: '/gen/form/page',
        method: 'get',
        params: query
    })
}

export function addObj(obj) {
    return request({
        url: '/gen/form',
        method: 'post',
        data: obj
    })
}

export function getObj(id) {
    return request({
        url: '/gen/form/' + id,
        method: 'get'
    })
}

export function delObj(id) {
    return request({
        url: '/gen/form/' + id,
        method: 'delete'
    })
}

export function putObj(obj) {
    return request({
        url: '/gen/form',
        method: 'put',
        data: obj
    })
}
