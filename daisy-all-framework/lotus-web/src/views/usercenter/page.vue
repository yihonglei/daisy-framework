<!-- <template>
  <div class="app-container">
    <el-row :gutter="24" style="margin-top:10px;">
      <el-col :span="24">
        <el-card class="box-card">
          <div slot="header" class="clearfix">
            <span>查询条件</span>
          </div>
          <div class="filter-container">
            <el-date-picker v-model="startDate" style="margin-right: 20px;" type="date" placeholder="选择开始日期"
              value-format="yyyy-MM-dd"></el-date-picker>
            <el-date-picker v-model="endDate" type="date" placeholder="选择结束日期" value-format="yyyy-MM-dd">
            </el-date-picker>
            <el-button v-waves class="filter-item" style="margin-left: 10px;" type="primary" @click="confirmSearch">
              查询
            </el-button>
            <el-button v-waves class="filter-item" style="margin-left: 10px;" type="primary" @click="resetSearch">
              重置
            </el-button>
            <el-button v-waves class="filter-item" style="margin-left: 10px;" type="primary" @click="inviteUser">
              邀请用户
            </el-button>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <el-table :data="usersList" style="width: 100%;margin-top:30px;" border>
      <el-table-column align="center" label="ID" width="50">
        <template slot-scope="scope">
          {{ scope.row.id }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="微信ID" width="220">
        <template slot-scope="scope">
          {{ scope.row.openid }}
        </template>
      </el-table-column>
      <el-table-column align="header-center" label="头像" width="120">
        <template slot-scope="scope">
          <img :src="scope.row.headimgurl" style="width: 80px;height: 80px;" />
        </template>
      </el-table-column>
      <el-table-column align="header-center" label="用户昵称">
        <template slot-scope="scope">
          {{ scope.row.nickname }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="性别">
        <template slot-scope="scope">
          {{ scope.row.sex|filterSex }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="位置" width="220">
        <template slot-scope="scope">
          {{ scope.row.country }}-{{ scope.row.province }}-{{ scope.row.city }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="是否VIP">
        <template slot-scope="scope">
          {{ scope.row.isVIP|filterIsVIP }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="过期时间" width="220">
        <template slot-scope="scope">
          {{ scope.row.expire|filterDate }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="用户昵称">
        <template slot-scope="scope">
          {{ scope.row.nickname }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="累计收入(元)">
        <template slot-scope="scope">
          {{ scope.row.income }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="可提现金额(元)">
        <template slot-scope="scope">
          {{ scope.row.remaining }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="个人邀请码" width="100">
        <template slot-scope="scope">
          {{ scope.row.attCode }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="邀请人" width="140">
        <template slot-scope="scope">
          {{ scope.row.inviteusername }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="是否可用">
        <template slot-scope="scope">
          {{ scope.row.enable|filterEnable }}
        </template>
      </el-table-column>
      <el-table-column v-if="checkPermission(['admin'])" align="center" label="是否启用" width="220">
        <template slot-scope="scope">
          <el-switch v-model="scope.row.enable" :active-value="1" :inactive-value="0"
            @change="switchChangeEnable(scope.row)"></el-switch>
        </template>
      </el-table-column>

      <el-table-column align="center" label="加入时间" width="220">
        <template slot-scope="scope">
          {{ scope.row.createdAt|filterDate }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="更新时间" width="220">
        <template slot-scope="scope">
          {{ scope.row.updated_at|filterDate }}
        </template>
      </el-table-column>

      <el-table-column fixed="right" v-if="checkPermission(['admin'])" align="center" label="操作" width="220">
        <template slot-scope="scope">
          <el-button type="primary" size="small" :disabled="false" @click="handleEdit(scope)">编辑</el-button>
          <el-button type="danger" size="small" :disabled="false" @click="handleDelete(scope)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!--
    <el-dialog :visible.sync="dialogVisible" :title="dialogType==='edit'?'编辑角色':'新建角色'">
      <el-form :model="role" label-width="80px" label-position="left">
        <el-form-item label="角色名称">
          <el-input v-model="role.name" placeholder="角色名称" />
        </el-form-item>
        <el-form-item label="角色描述">
          <el-input v-model="role.intro" :autosize="{ minRows: 2, maxRows: 4}" type="textarea" placeholder="角色描述" />
        </el-form-item>
        <el-form-item label="权限菜单">
          <el-tree ref="tree" :check-strictly="checkStrictly" :data="routesData" :props="defaultProps" show-checkbox
            node-key="id" class="permission-tree" />
        </el-form-item>
      </el-form>
      <div style="text-align:right;">
        <el-button type="danger" @click="dialogVisible=false">取消</el-button>
        <el-button type="primary" @click="confirmRole">提交</el-button>
      </div>
    </el-dialog> -->
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
  import waves from '@/directive/waves/index.js' // 水波纹指令

  export default {
    directives: {
      waves
    },
    data() {
      return {
        startDate: new Date(),
        endDate: new Date(),
        usersList: [],
        dialogVisible: false,
        dialogType: 'new',
        checkStrictly: false,
      }
    },

    filters: {
      filterEnable(enable) {
        if (enable) {
          return "正常"
        } else {
          return "禁用"
        }
      },
      filterIsVIP(isVIP) {
        if (isVIP) {
          return "是"
        } else {
          return "否"
        }
      },
      filterDate(date) {
        var time = new Date(date)
        var year = time.getFullYear();
        var month = time.getMonth() + 1;
        var dates = time.getDate();
        var hour = time.getHours();
        hour = hour < 10 ? '0' + hour : hour;
        var minute = time.getMinutes();
        minute = minute < 10 ? '0' + minute : minute;
        var second = time.getSeconds();
        second = second < 10 ? '0' + second : second;
        return year + '-' + month + '-' + dates + '\t' + hour + ':' + minute + ':' + second;
      },
      filterSex(sex) {
        if (sex == 1) {
          return '男'
        } else {
          return '女'
        }
      }
    },
    created() {

    },
    methods: {
      checkPermission,

      resetSearch() {
        this.startDate = new Date()
        this.endDate = new Date()
        this.confirmSearch()
      },

      confirmSearch() {
        if (!this.startDate) {
          this.$message.error('请先选择开始时间')
          return
        }
        if (!this.endDate) {
          this.$message.error('请先选择结束时间')
          return
        }
        this.getCompanyUserList()
      },
      async getCompanyUserList() {
        let obj = {
          startDate: this.startDate,
          endDate: this.endDate
        }
        const res = await this.api.user().companyUserList(obj)
        if (res.code == 1) {
          this.usersList = res.data
        }
      },

      async switchChangeEnable(obj) {
        const res = await this.api.user().sysUpdateUserEnable(obj)
        if (res.code == 1) {
          this.$message.success('操作成功')
        } else {
          if (obj.enable == 1) {
            obj.enable = 0
          } else {
            obj.enable = 1
          }
          this.$message.error('操作失败')
        }
      },

      inviteUser() {
        // 生成邀请用户二维码

      },

      handleEdit() {
        this.$message.info('暂未实现')
      },
      handleDelete() {
        this.$message.info('暂未实现')
      }
    },
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
  }
</style>
 -->
