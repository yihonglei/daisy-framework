import request from '@/router/axios'

export function fetchDeptTree (query) {
  return request({
    url: '/admin/dept/user-tree',
    method: 'get',
    params: query
  })
}

export function fetchTree (query) {
  return request({
    url: '/admin/dept/tree',
    method: 'get',
    params: query
  })
}

export function addObj (obj) {
  return request({
    url: '/admin/dept',
    method: 'post',
    data: obj
  })
}

export function getObj (id) {
  return request({
    url: '/admin/dept/' + id,
    method: 'get'
  })
}

export function delObj (id) {
  return request({
    url: '/admin/dept/' + id,
    method: 'delete'
  })
}

export function putObj (obj) {
  return request({
    url: '/admin/dept',
    method: 'put',
    data: obj
  })
}

export function getdetails (obj) {
  return request({
    url: '/admin/dept/details/' + obj,
    method: 'get'
  })
}

