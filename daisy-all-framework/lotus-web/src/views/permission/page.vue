<template>
  <div class="app-container">
    <el-row :gutter="12" style="margin-top:30px;">
      <el-col :span="12">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>菜单列表</span>
          </div>
          <div class="filter-container">
            <el-input v-model="filterText" placeholder="输入搜索关键字" style="width: 200px;margin-right: 10px;"
              class="filter-item" />
            <el-button v-waves class="filter-item" style="margin-left: 10px;" type="primary" @click="handleReset">
              重置
            </el-button>
            <el-button v-waves class="filter-item" style="margin-left: 10px;" type="primary" @click="handleAdd">
              新增
            </el-button>
            <el-button v-waves class="filter-item" style="margin-left: 10px;" type="primary" @click="handleEdit">
              编辑
            </el-button>
            <el-button v-waves class="filter-item" style="margin-left: 10px;" type="primary" @click="handleDelete">
              删除
            </el-button>
          </div>
          <div>
            <el-form :model="role">
              <el-tree ref="tree" :check-strictly="checkStrictly" :data="routesData" :props="defaultProps"
                :accordion="false" default-expand-all :highlight-current="true" :expand-on-click-node="false"
                show-checkbox node-key="id" class="permission-tree" @node-click="nodeClick"
                :filter-node-method="filterNode" empty-text="暂无数据" />
            </el-form>
          </div>
        </el-card>
      </el-col>

      <el-col :span="12">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>新增菜单</span>
          </div>
          <div>
            <el-form :model="menu">
              <el-form-item prop="parentid" label="上级ID" label-width="100px" label-position="left">
                <el-input disabled style="width: 200px;" v-model="menu.parentid" placeholder="上级ID"></el-input>
                <el-button style="margin-left: 10px;" type="primary" @click="resetParentId" icon="search">重置
                </el-button>
              </el-form-item>
              <el-form-item prop="path" label="路由URI" label-width="100px" label-position="left">
                <el-input v-model="menu.path" placeholder="路由URI"></el-input>
              </el-form-item>
              <el-form-item prop="component" label="视图URI" label-width="100px" label-position="left">
                <el-input :disabled="menu.parentid==0" v-model="menu.component" placeholder="视图URI"></el-input>
              </el-form-item>
              <el-form-item prop="hidden" label="是否隐藏" label-width="100px" label-position="left">
                <el-checkbox v-model="menu.hidden" label="不显示在菜单栏" :true-label="1" :false-label="0"
                  @change="handleCheckHidden"></el-checkbox>
              </el-form-item>
              <el-form-item prop="redirect" label="重定向URI" label-width="100px" label-position="left">
                <el-input v-model="menu.redirect" placeholder="重定向URI"></el-input>
              </el-form-item>
              <el-form-item prop="alwaysShow" label="一直显示" label-width="100px" label-position="left">
                <el-checkbox v-model="menu.alwaysShow" label="一个子菜单时,也展示父菜单" :true-label="1" :false-label="0"
                  @change="handleCheckAlwaysShow"></el-checkbox>
              </el-form-item>
              <el-form-item prop="name" label="*路由名称" label-width="100px" label-position="left">
                <el-input v-model="menu.name" placeholder="路由名称"></el-input>
              </el-form-item>

              <el-form-item prop="title" label="*菜单名称" label-width="100px" label-position="left">
                <el-input v-model="menu.title" placeholder="菜单名称"></el-input>
              </el-form-item>

              <el-form-item prop="icon" label="ICON" label-width="100px" label-position="left">
                <el-input disabled v-model="menu.icon" placeholder="图标" style="width: 200px;"></el-input>
                <el-button style="margin-left: 10px;" type="primary" @click="selectIcon" icon="search">选择</el-button>
              </el-form-item>
              <el-form-item prop="noCache" label="是否缓存" label-width="100px" label-position="left">
                <el-checkbox v-model="menu.noCache" :true-label="1" :false-label="0" @change="handleCheckNoCache">
                </el-checkbox>
              </el-form-item>
              <el-form-item prop="breadcrumb" label="展示面包屑中" label-width="100px" label-position="left">
                <el-checkbox v-model="menu.breadcrumb" :true-label="1" :false-label="0" @change="handleCheckBreadcrumb">
                </el-checkbox>
              </el-form-item>
              <el-form-item prop="affix" label="固定在顶部" label-width="100px" label-position="left">
                <el-checkbox v-model="menu.affix" :true-label="1" :false-label="0" @change="handleCheckAffix">
                </el-checkbox>
              </el-form-item>
              <el-form-item prop="activeMenu" label="高亮侧边栏" label-width="100px" label-position="left">
                <el-input v-model="menu.activeMenu" placeholder="高亮侧边栏(选填)"></el-input>
              </el-form-item>
              <el-form-item prop="enable" label="状态" label-width="100px" label-position="left">
                <el-radio-group v-model="menu.enable" size="small" @change="changeEnable" fill="#66b1ff">
                  <el-radio :label="1">启用</el-radio>
                  <el-radio :label="0">禁用</el-radio>
                </el-radio-group>
              </el-form-item>
              <el-form-item prop="sort" label="排序" label-width="100px" label-position="left">
                <el-input type="number" min="1" v-model="menu.sort"
                  style="width: 100px;margin-left: 5px;margin-right: 5px;"></el-input>
              </el-form-item>
            </el-form>
          </div>
          <div style="text-align:center;">
            <el-button type="primary" @click="confirmAdd">提交</el-button>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <el-dialog :visible.sync="dialogVisible" title="选择图标">
      <div class="iconList">
        <i v-for="item in iconList" :key="item" :class="item" @click="sureIcon(item)" style="font-size:20px"></i>
      </div>
    </el-dialog>

  </div>
</template>

<script>
  import path from 'path'
  import {
    deepClone
  } from '@/utils'
  import {
    getToken,
    setToken,
    removeToken
  } from '@/utils/auth'

  import checkPermission from '@/utils/permission' // 权限判断函数
  import PanThumb from '@/components/PanThumb'
  import MdInput from '@/components/MDinput'
  import Mallki from '@/components/TextHoverEffect/Mallki'
  import DropdownMenu from '@/components/Share/DropdownMenu'
  import waves from '@/directive/waves/index.js' // 水波纹指令
  import {
    elementIcons
  } from '@/utils/icon'

  const defaultRole = {
    id: 0,
    name: '',
    intro: '',
    routes: []
  }

  export default {
    name: 'ComponentMixinDemo',
    components: {
      PanThumb,
      MdInput,
      Mallki,
      DropdownMenu,
    },
    directives: {
      waves
    },
    watch: {
      filterText(val) {
        this.$refs.tree.filter(val);
      }
    },
    data() {
      return {
        role: Object.assign({}, defaultRole),
        routes: [],
        dialogVisible: false,
        checkStrictly: false,
        defaultProps: {
          children: 'children',
          label: 'title',
          title: 'title',
          id: 0
        },
        filterText: '',
        clickMenu: {
          id:0,
          title:'根菜单'
        },
        defaultMenu: {
          id: 0,
          parentid: 0,
          path: '',
          component: 'layout',
          hidden: 0,
          redirect: '',
          alwaysShow: 0,
          name: '',
          title: '',
          icon: '',
          activeMenu: '',
          noCache: 0,
          breadcrumb: 1,
          affix: 0,
          enable: 1,
          sort: 1
        },
        menu: {
          id: 0,
          parentid: 0,
          path: '',
          component: 'layout',
          hidden: 0,
          redirect: '',
          alwaysShow: 0,
          name: '',
          title: '',
          icon: '',
          activeMenu: '',
          noCache: 0,
          breadcrumb: 1,
          affix: 0,
          enable: 1,
          sort: 1
        }
      }
    },
    computed: {
      routesData() {
        return this.routes
      }
    },
    created() {
      // Mock: get all routes and roles list from server
      this.getRoutes()
      this.iconList = elementIcons
    },
    methods: {
      checkPermission,
      async getRoutes() {
        const res = await this.api.role().getRoutes()
        this.serviceRoutes = res.data

        this.routes = this.generateRoutes(res.data)
      },

      nodeClick(val) {
        this.clickMenu = val
        this.menu = this.defaultMenu
      },

      filterNode(value, data) {
        if (!value) return true
        return data.label.includes(value)
      },
      handleReset() {
        this.filterText = ''
      },

      handleAdd() {
        let title = '确定新增【' + this.clickMenu.title + '】的子菜单？'
        this.$confirm(title, '提示', {
            confirmButtonText: '确认',
            cancelButtonText: '取消',
            type: 'warning'
          })
          .then(() => {
            this.menu = this.defaultMenu
            this.menu.parentid = this.clickMenu.id
            if (this.menu.parentid == 0) {
              this.menu.component = 'layout'
            } else {
              this.menu.component = ''
            }
          })
          .catch(err => {
            console.error(err)
          })
      },

      handleEdit() {
        let title = '确定编辑【' + this.clickMenu.title + '】菜单？'
        this.$confirm(title, '提示', {
            confirmButtonText: '确认',
            cancelButtonText: '取消',
            type: 'warning'
          })
          .then(() => {
            this.menu = this.clickMenu
            this.menu.parentid = this.clickMenu.id
          })
          .catch(err => {
            console.error(err)
          })
      },

      resetParentId() {
        this.menu = this.defaultMenu
        this.menu.parentid = 0
        this.menu.component = 'layout'
      },
      handleCheckHidden(val) {
        this.menu.hidden = val
      },
      handleCheckAlwaysShow(val) {
        this.menu.alwaysShow = val
      },
      handleCheckNoCache(val) {
        this.menu.noCache = val
      },
      handleCheckBreadcrumb(val) {
        this.menu.breadcrumb = val
      },
      handleCheckAffix(val) {
        this.menu.affix = val
      },
      changeEnable(enable) {
        this.menu.enable = enable
      },
      selectIcon() {
        this.dialogVisible = true
      },
      sureIcon(item) {
        this.menu.icon = item
        this.dialogVisible = false
      },

      async confirmAdd() {
        var isEdit = false
        let menuId = this.menu.id
        if (menuId != 0) {
          isEdit = true
        }
        if (isEdit) {
          let res = await this.api.menu().editSysRoute(this.menu)
          if (res.code == 1) {
            this.$message.success('菜单编辑成功')
            this.menu = this.defaultMenu;
            this.getRoutes()
          } else {
            this.$message.error(res.msg + '')
          }
        } else {
          let res = await this.api.menu().addSysRoute(this.menu)
          if (res.code == 1) {
            this.$message.success('菜单新增成功')
            this.menu = this.defaultMenu;
            this.getRoutes()
          } else {
            this.$message.error(res.msg + '')
          }
        }
      },

      handleDelete() {
        const checkedKeys = this.$refs.tree.getCheckedKeys()
        let ids = checkedKeys.join(',');
        let obj = {
          'ids': ids
        }
        if (checkedKeys.length == 0) {
          this.$message.info('请先选择待删除的菜单')
        } else {
          this.$confirm('确定删除选中的菜单?', '提示', {
              confirmButtonText: '确认',
              cancelButtonText: '取消',
              type: 'warning'
            })
            .then( async () => {
              let res = await this.api.menu().delSysRoute(obj)
              if (res.code == 1) {
                this.$message.success('菜单删除成功')
                this.menu = this.defaultMenu;
                this.getRoutes()
              } else {
                this.$message.error(res.msg + '')
              }
            })
            .catch(err => {
              console.error(err)
            })
        }
      },

      // Reshape the routes structure so that it looks the same as the sidebar
      generateRoutes(routes, basePath = '/') {
        const res = []

        for (let route of routes) {
          // skip some route
          if (route.hidden) {
            continue
          }

          // const onlyOneShowingChild = this.onlyOneShowingChild(route.children, route)

          // if (route.children && onlyOneShowingChild && !route.alwaysShow) {
          //   route = onlyOneShowingChild
          // }

          const data = route
          data.path = route.path//path.resolve(basePath, route.path)
          data.title = route.title
          data.label = route.title,
          data.id = route.id

          // const data = {
          //   path: path.resolve(basePath, route.path),
          //   title: route.title,
          //   label: route.title,
          //   id: route.id
          // }

          // recursive child routes
          if (route.children) {
            data.children = this.generateRoutes(route.children, data.path)
          }
          res.push(data)
        }
        return res
      },
      // reference: src/view/layout/components/Sidebar/SidebarItem.vue
      onlyOneShowingChild(children = [], parent) {
        let onlyOneChild = null
        const showingChildren = children.filter(item => !item.hidden)

        // When there is only one child route, the child route is displayed by default
        if (showingChildren.length === 1) {
          onlyOneChild = showingChildren[0]
          onlyOneChild.path = path.resolve(parent.path, onlyOneChild.path)
          return onlyOneChild
        }

        // Show parent if there are no child route to display
        if (showingChildren.length === 0) {
          onlyOneChild = {
            ...parent,
            path: '',
            noShowingChildren: true
          }
          return onlyOneChild
        }

        return false
      }
    }
  }
</script>

<style lang="scss" scoped>
  .app-container {
    .roles-table {
      margin-top: 30px;
    }

    .permission-tree {
      margin-bottom: 30px;
    }

    .component-item {
      min-height: 100px;
    }

    .iconList {
      overflow-y: scroll; //y轴显示滚动条
      overflow-x: hidden; //x轴溢出隐藏
      display: flex;
      justify-content: space-around;
      flex-wrap: wrap; //一定要换行

      i {
        display: inline-block;
        width: 60px;
        height: 45px;
        color: #000000;
        font-size: 20px;
        border: 1px solid #E6E6E6;
        border-radius: 4px;
        cursor: pointer;
        text-align: center;
        line-height: 45px;
        margin: 5px;

        &:hover {
          color: #f5de00;
          border-color: #f5de00;
        }
      }
    }
  }
</style>
