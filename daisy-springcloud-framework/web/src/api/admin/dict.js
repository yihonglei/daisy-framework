import request from '@/router/axios'

export function fetchList(query) {
  return request({
    url: '/admin/dict/page',
    method: 'get',
    params: query
  })
}

export function fetchItemList(query) {
  return request({
    url: '/admin/dict/item/page',
    method: 'get',
    params: query
  })
}

export function addItemObj(obj) {
  return request({
    url: '/admin/dict/item',
    method: 'post',
    data: obj
  })
}

export function getItemObj(id) {
  return request({
    url: '/admin/dict/item/' + id,
    method: 'get'
  })
}

export function delItemObj(id) {
  return request({
    url: '/admin/dict/item/' + id,
    method: 'delete'
  })
}

export function putItemObj(obj) {
  return request({
    url: '/admin/dict/item',
    method: 'put',
    data: obj
  })
}

export function addObj(obj) {
  return request({
    url: '/admin/dict/',
    method: 'post',
    data: obj
  })
}

export function getObj(id) {
  return request({
    url: '/admin/dict/' + id,
    method: 'get'
  })
}

export function delObj(row) {
  return request({
    url: '/admin/dict/' + row.id,
    method: 'delete'
  })
}

export function putObj(obj) {
  return request({
    url: '/admin/dict/',
    method: 'put',
    data: obj
  })
}

export function remote(key) {
  return request({
    url: '/admin/dict/key/' + key,
    method: 'get'
  })
}

export function clearDictCache(){
  return request({
    url:'/admin/dict/cache',
    method:'delete'
  })
}
