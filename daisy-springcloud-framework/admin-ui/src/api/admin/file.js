import request from '@/router/axios'

export function fetchList(query) {
  return request({
    url: '/admin/sys-file/page',
    method: 'get',
    params: query
  })
}

export function addObj(obj) {
  return request({
    url: '/admin/sys-file',
    method: 'post',
    data: obj
  })
}

export function getObj(id) {
  return request({
    url: '/admin/sys-file/' + id,
    method: 'get'
  })
}

export function delObj(id) {
  return request({
    url: '/admin/sys-file/' + id,
    method: 'delete'
  })
}

export function putObj(obj) {
  return request({
    url: '/admin/sys-file',
    method: 'put',
    data: obj
  })
}

export function onlineFile(bucketName, fileName) {
  return request({
    url: `/admin/sys-file/online/${bucketName}/${fileName}`,
    method: 'get'
  })
}