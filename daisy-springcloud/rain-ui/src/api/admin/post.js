import request from '@/router/axios'

export function listPosts() {
    return request({
        url: '/admin/post/list',
        method: 'get'
    })
}


export function fetchList(query) {
    return request({
        url: '/admin/post/page',
        method: 'get',
        params: query
    })
}

export function addObj(obj) {
    return request({
        url: '/admin/post',
        method: 'post',
        data: obj
    })
}

export function getObj(id) {
    return request({
        url: '/admin/post/' + id,
        method: 'get'
    })
}

export function delObj(id) {
    return request({
        url: '/admin/post/' + id,
        method: 'delete'
    })
}

export function putObj(obj) {
    return request({
        url: '/admin/post',
        method: 'put',
        data: obj
    })
}
