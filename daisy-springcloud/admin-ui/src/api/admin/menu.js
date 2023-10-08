import request from '@/router/axios'

export function getMenu(id) {
    return request({
        url: '/admin/menu',
        params: {parentId: id},
        method: 'get'
    })
}

export function fetchMenuTree(lazy, parentId) {
    return request({
        url: '/admin/menu/tree',
        method: 'get',
        params: {lazy: lazy, parentId: parentId}
    })
}

export function addObj(obj) {
    return request({
        url: '/admin/menu',
        method: 'post',
        data: obj
    })
}

export function getObj(id) {
    return request({
        url: '/admin/menu/' + id,
        method: 'get'
    })
}

export function delObj(id) {
    return request({
        url: '/admin/menu/' + id,
        method: 'delete'
    })
}

export function putObj(obj) {
    return request({
        url: '/admin/menu',
        method: 'put',
        data: obj
    })
}

export function clearMenuCache() {
    return request({
        url: '/admin/menu/cache',
        method: 'delete'
    })
}

