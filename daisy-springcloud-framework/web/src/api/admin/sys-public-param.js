import request from '@/router/axios'

export function fetchList(query) {
    return request({
        url: '/admin/param/page',
        method: 'get',
        params: query
    })
}

export function addObj(obj) {
    return request({
        url: '/admin/param',
        method: 'post',
        data: obj
    })
}

export function getObj(key) {
    return request({
        url: '/admin/param/publicValue/' + key,
        method: 'get'
    })
}

export function delObj(id) {
    return request({
        url: '/admin/param/' + id,
        method: 'delete'
    })
}

export function putObj(obj) {
    return request({
        url: '/admin/param',
        method: 'put',
        data: obj
    })
}

export function refreshCache() {
    return request({
        url: '/admin/param/sync',
        method: 'put'
    })
}
