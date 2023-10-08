import {
  asyncRoutes,
  constantRoutes
} from '@/router'

// 用于处理动态菜单数据，将其转为 route 形式
export function fnAddDynamicMenuRoutes(routes = []) {
  let accessRoutes = []
  // 用于保存普通路由数据
  let temp = []
  // 用于保存存在子路由的路由数据
  let route = []
  // 遍历数据
  for (let i = 0; i < routes.length; i++) {
    // 存在子路由，则递归遍历，并返回数据作为 children 保存
    if (routes[i].children && routes[i].children.length > 0) {
      // 获取路由的基本格式
      route = getRoute(routes[i])
      // 递归处理子路由数据，并返回，将其作为路由的 children 保存
      route.children = fnAddDynamicMenuRoutes(routes[i].children)
      // 保存存在子路由的路由
      accessRoutes.push(route)
    } else {
      // 保存普通路由
      accessRoutes.push(getRoute(routes[i]))
    }
  }
  // 返回路由结果
  return accessRoutes
}

// 返回路由的基本格式
function getRoute(item) {
  // 路由基本格式
  let route = {}
  if (item.parentId == 0) {
    route = {
      hidden: item.hidden,
      redirect: item.redirect,
      alwaysShow: item.alwaysShow,
      // 路由名
      name: item.name,
      // 路由的路径
      path: item.path,
      // 路由所在组件 @/layout
      component: (resolve) => require([`@/layout`], resolve),
      // component: (resolve) => require([`@/views${item.component}`], resolve),
      meta: {
        title: item.title,
        icon: item.icon,
        noCache: item.noCache,
        breadcrumb: item.breadcrumb,
        affix: item.affix,
        activeMenu: item.activeMenu
      }
    }
  } else {
    route = {
      // 路由的路径
      path: item.path,
      // 路由名
      name: item.name,
      alwaysShow: item.alwaysShow,
      hidden: item.hidden,
      redirect: item.redirect,
      // 路由所在组件 @/layout
      component: (resolve) => require([`@/views${item.component}`], resolve),
      meta: {
        icon: item.icon,
        title: item.title,
        affix: item.affix,
        breadcrumb: item.hidden_breadcrumb,
      }
    }
  }

  // 返回 route
  return route
}


const state = {
  routes: [],
  addRoutes: []
}

const mutations = {
  SET_ROUTES: (state, routes) => {
    state.addRoutes = routes
    state.routes = constantRoutes.concat(routes)
  }
}

const actions = {
  generateRoutes({
                   commit
                 }, routes) {
    return new Promise(resolve => {

      let accessedRoutes = fnAddDynamicMenuRoutes(routes)
      commit('SET_ROUTES', accessedRoutes)
      resolve(accessedRoutes)
    })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
