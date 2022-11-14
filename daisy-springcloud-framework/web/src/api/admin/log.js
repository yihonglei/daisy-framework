import request from '@/router/axios'

export function fetchList (query) {
  return request({
    url: '/admin/log/page',
    method: 'get',
    params: query
  })
}

export function delObj (id) {
  return request({
    url: '/admin/log/' + id,
    method: 'delete'
  })
}

export function addObj (obj) {
  return request({
    url: '/admin/log',
    method: 'post',
    data: obj
  })
}

export function getObj (id) {
  return request({
    url: '/admin/log/' + id,
    method: 'get'
  })
}

export function putObj (obj) {
  return request({
    url: '/admin/log',
    method: 'put',
    data: obj
  })
}

