<template>
  <div class="app-container">
    <el-button v-if="checkPermission(['admin'])" type="primary" @click="handleAddUser">新建用户</el-button>

    <el-table :data="usersList" style="width: 100%;margin-top:30px;" border>
      <el-table-column align="center" label="ID" width="50">
        <template slot-scope="scope">
          {{ scope.row.id }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="用户名" width="220">
        <template slot-scope="scope">
          {{ scope.row.username }}
        </template>
      </el-table-column>
      <el-table-column align="header-center" label="头像" width="120">
        <template slot-scope="scope">
          <img :src="scope.row.avatar" style="width: 80px;height: 80px;" />
        </template>
      </el-table-column>
      <el-table-column align="center" label="真实姓名" width="220">
        <template slot-scope="scope">
          {{ scope.row.name }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="手机号" width="220">
        <template slot-scope="scope">
          {{ scope.row.phone }}
        </template>
      </el-table-column>

      <el-table-column align="center" label="邀请码" width="220">
        <template slot-scope="scope">
          {{ scope.row.attcode }}
        </template>
      </el-table-column>

      <el-table-column align="center" label="角色" width="220">
        <template slot-scope="scope">
          {{ scope.row.roles|filterRoles }}
        </template>
      </el-table-column>

      <el-table-column align="center" label="状态" width="120">
        <template slot-scope="scope">
          {{ scope.row.status|filterStatus }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="是否启用" width="220">
        <template slot-scope="scope">
          <el-switch v-model="scope.row.status" :active-value="1" :inactive-value="0"
            @change="switchChange(scope.row)"></el-switch>
        </template>
      </el-table-column>
      <el-table-column align="header-center" label="创建时间" width="220">
        <template slot-scope="scope">
          {{ scope.row.created_time }}
        </template>
      </el-table-column>
      <el-table-column align="header-center" label="创建人">
        <template slot-scope="scope">
          {{ scope.row.created_by_username }}
        </template>
      </el-table-column>
      <el-table-column align="header-center" label="更新时间" width="220">
        <template slot-scope="scope">
          {{ scope.row.updated_time }}
        </template>
      </el-table-column>
      <el-table-column align="header-center" label="更新人">
        <template slot-scope="scope">
          {{ scope.row.updated_by_username }}
        </template>
      </el-table-column>
      <el-table-column fixed="right" v-if="checkPermission(['admin'])" align="center" label="操作" width="300">
        <template slot-scope="scope">
          <el-button type="primary" size="small" @click="handleEdit(scope)">编辑</el-button>
          <el-button type="danger" size="small" @click="handleDelete(scope)">删除</el-button>
          <el-button type="danger" size="small" @click="handleResetPWD(scope)">重置密码</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog :visible.sync="dialogVisible" :title="dialogType==='edit'?'编辑用户':'新建用户'">
      <el-form :model="user" label-width="100px" label-position="left">
        <el-form-item label="用户名">
          <el-input v-model="user.username" placeholder="用户名" />
        </el-form-item>
        <el-form-item label="头像">
          <el-upload :data="dataObj" action="" :multiple="false" :show-file-list="false" :http-request="httpRequest"
            :before-upload="beforeUpload" :limit="1" class="image-uploader">
            <img :src="user.avatar+'?imageView2/1/w/80/h/80'" class="image-uploader">
            </img>
          </el-upload>
        </el-form-item>
        <el-form-item label="真实姓名">
          <el-input v-model="user.name" placeholder="真实姓名" />
        </el-form-item>
        <el-form-item label="手机号">
          <el-input v-model="user.phone" placeholder="手机号" />
        </el-form-item>
        <el-form-item label="角色">
          <el-tree ref="tree" :check-strictly="checkStrictly" :data="rolesData" :props="defaultProps" show-checkbox
            node-key="id" class="permission-tree" />
        </el-form-item>
      </el-form>
      <div style="text-align:right;">
        <el-button type="danger" @click="dialogVisible=false">取消</el-button>
        <el-button type="primary" @click="confirmuser">提交</el-button>
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
  import {
    client,
    getFileNameUUID
  } from '@/utils/alioss.js'

  const defaultUser = {
    id: 0,
    username: '',
    avatar: '',
    name: '',
    phone: '',
    alipay: '',
    roleIds: '',
    rolesData: []
  }

  export default {
    data() {
      return {
        user: Object.assign({}, defaultUser),
        roles: [],
        usersList: [],
        dataObj: {
          token: '',
          key: ''
        },
        dialogVisible: false,
        dialogType: 'new',
        checkStrictly: false,
        defaultProps: {
          label: 'title',
          title: 'title',
          id: 0
        }
      }
    },
    computed: {
      rolesData() {
        return this.roles
      }
    },
    filters: {
      filterStatus(status) {
        if (status == 1) {
          return "正常"
        } else {
          return "禁用"
        }
      },
      filterRoles(roles) {
        let tmpRoles = []
        for (var i = 0; i < roles.length; i++) {
          let role = roles[i]
          tmpRoles.push(role.name)
        }

        return tmpRoles.join(',')
      }
    },
    created() {
      // Mock: get all routes and roles list from server
      this.getRoles()
      this.getSysUsersList()
    },
    methods: {
      checkPermission,
      async getRoles() {
        const res = await this.api.role().getRoles()
        let roles = res.data
        this.roles = this.generateRoles(roles)
      },
      async getSysUsersList() {
        const res = await this.api.user().getSysUsersList({
          token: getToken()
        })
        this.usersList = res.data
      },

      // Reshape the routes structure so that it looks the same as the sidebar
      generateRoles(roles) {
        const res = []

        for (let role of roles) {
          const data = {
            title: role.name,
            label: role.name,
            id: role.id
          }
          res.push(data)
        }
        return res
      },

      generateArr(roles) {
        let data = []
        roles.forEach(role => {
          data.push(role)
        })
        return data
      },
      handleAddUser() {
        this.user = Object.assign({}, defaultUser)
        if (this.$refs.tree) {
          this.$refs.tree.setCheckedNodes([])
        }
        this.dialogType = 'new'
        this.dialogVisible = true
      },
      handleEdit(scope) {
        this.dialogType = 'edit'
        this.dialogVisible = true
        this.checkStrictly = true
        this.user = deepClone(scope.row)
        this.$nextTick(() => {
          const roles = this.generateRoles(this.rolesData)
          this.$refs.tree.setCheckedNodes(this.generateArr(this.user.roles))
          // set checked state of a node not affects its father and child nodes
          this.checkStrictly = false
        })
      },
      handleDelete({
        $index,
        row
      }) {
        if (row.id == 1) {
          this.$message({
            type: 'warning',
            message: '超级管理员无法删除!'
          })
          return
        }
        this.$confirm('确定删除此用户?', '提示', {
            confirmButtonText: '确认',
            cancelButtonText: '取消',
            type: 'warning'
          })
          .then(async () => {
            await this.api.user().delSysUser({
              'id': row.id
            })
            this.usersList.splice($index, 1)
            this.$message({
              type: 'success',
              message: '删除成功!'
            })
          })
          .catch(err => {
            console.error(err)
          })
      },
     handleResetPWD({
        $index,
        row
      }){
        if (row.id == 1) {
          this.$message({
            type: 'warning',
            message: '超级管理员无法重置!'
          })
          return
        }
        this.$confirm('确定重置此用户密码?', '提示', {
            confirmButtonText: '确认',
            cancelButtonText: '取消',
            type: 'warning'
          })
          .then(async () => {
            const {
              data
            } = await this.api.user().resetSysPWD({
              'id': row.id
            })
            if (data == 1) {
              this.$message({
                type: 'success',
                message: '密码重置成功!'
              })
            } else {
              this.$message({
                type: 'fail',
                message: '密码重置失败!'
              })
            }

          })
          .catch(err => {
            console.error(err)
          })
      },
      async confirmuser() {
        const isEdit = this.dialogType === 'edit'

        const checkedKeys = this.$refs.tree.getCheckedKeys()
        this.user.roleIds = checkedKeys.join(',')

        if (isEdit) {
          const {
            data
          } = await this.api.user().updateSysUser(this.user)
          this.user.roles = data.roles
          for (let index = 0; index < this.usersList.length; index++) {
            if (this.usersList[index].id === this.user.id) {
              this.usersList.splice(index, 1, Object.assign({}, this.user))
              break
            }
          }
        } else {
          const {
            data
          } = await this.api.user().addSysUser(this.user)
          this.user = data
          this.usersList.push(this.user)
        }

        const {
          id,
          username,
          name
        } = this.user
        this.dialogVisible = false
        this.$notify({
          title: 'Success',
          dangerouslyUseHTMLString: true,
          message: `
            <div>用户ID: ${id}</div>
            <div>用户名: ${username}</div>
            <div>真实姓名: ${name}</div>
          `,
          type: 'success'
        })
      },

      async switchChange(obj) {
        const res = await this.api.user().sysUpdateUserStatus(obj)
        if (res.code == 1) {
          this.$message.success('操作成功')
        } else {
          if (obj.status == 1) {
            obj.status = 0
          } else {
            obj.status = 1
          }
          this.$message.error('操作失败')
        }
      },

      beforeUpload(file) {
        const isLt1M = file.size / 1024 / 1024 < 1;
        if (!isLt1M) {
          this.$message.error('文件大小不能超过 1M !');
        }
        return isLt1M;
      },
      httpRequest({
        file
      }) {
        let that = this
        //注意哦，这里指定文件夹'image/'，尝试过写在配置文件，但是各种不行，写在这里就可以
        var fileName = 'mhjy/' + 'header' + getFileNameUUID() + '.jpg';
        client().put(fileName, file).then(({
          res,
          url,
          name
        }) => {
          if (res && res.status == 200) {
            // console.log(`阿里云OSS上传文件成功回调`, res, url, name);
            this.user.avatar = url;
            console.log(url)
          }
        }).catch((err) => {
          console.log(`阿里云OSS上传失败回调`, err);
        });
      },

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

    .image-uploader {
      background-color: #5cd7f7;
      width: 80px;
      height: 80px;
      border-radius: 40px;
      color: #000000;
    }
  }
</style>
