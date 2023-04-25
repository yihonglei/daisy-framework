<!-- <template>
  <div class="app-container">
    <el-tabs v-model="activeName">
      <el-tab-pane label="系统设置" name="first">
        <el-table :data="setList" style="width: 100%;margin-top:10px;">
          <el-table-column align="center" label="编号" width="50">
            <template slot-scope="scope">
              {{ scope.row.id }}
            </template>
          </el-table-column>
          <el-table-column align="center" label="参数名" width="220">
            <template slot-scope="scope">
              {{ scope.row.type }}
            </template>
          </el-table-column>
          <el-table-column align="center" label="参数值" width="120">
            <template slot-scope="scope">
              <el-input size="small" v-model="scope.row.value" placeholder></el-input>
            </template>
          </el-table-column>
          <el-table-column align="center" label="说明" width="220">
            <template slot-scope="scope">
              {{scope.row.desc}}
            </template>
          </el-table-column>
          <el-table-column align="center" label="更新时间" width="220">
            <template slot-scope="scope">
              {{ scope.row.updated_at|filterDate }}
            </template>
          </el-table-column>
          <el-table-column v-if="checkPermission(['admin'])" align="center" label="操作" width="120" fixed="right">
            <template slot-scope="scope">
              <el-button type="danger" size="small" @click="handleFirstUpdate(scope)">确定修改</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-tab-pane>

      <el-tab-pane label="提现手续费设置" name="second">

        <div class="filter-container" style="margin-top: 20px;">
          <label class="filter-item" style="font-size: 10pt;margin-left: 10px;">用户名：</label>
          <el-input v-model="nameFilterText" placeholder="可输入用户名查询" style="width: 200px;margin-right: 10px;"
            class="filter-item" />
          <label class="filter-item" style="font-size: 10pt;margin-left: 10px;">用户类型：</label>
          <el-select v-model="typeFilterText" class="filter-item" default-first-option>
            <el-option v-for="item in typeoptions" :key="item.value" :label="item.label" :value="item.value">
            </el-option>
          </el-select>
          <el-button v-waves class="filter-item" style="margin-left: 10px;" type="primary" @click="confirmSecondSearch">
            查询
          </el-button>
          <el-button v-waves class="filter-item" style="margin-left: 10px;" type="primary" @click="addUserFee">
            新增
          </el-button>
        </div>

        <el-table :data="feeList" style="width: 100%;margin-top:10px;">
          <el-table-column align="center" label="编号" width="50">
            <template slot-scope="scope">
              {{ scope.row.id }}
            </template>
          </el-table-column>
          <el-table-column align="center" label="用户ID" width="220">
            <template slot-scope="scope">
              {{ scope.row.uid }}
            </template>
          </el-table-column>
          <el-table-column align="center" label="用户类型" width="220">
            <template slot-scope="scope">
              {{ scope.row.type|filterType }}
            </template>
          </el-table-column>
          <el-table-column align="center" label="用户名" width="220">
            <template slot-scope="scope">
              {{ scope.row.username }}
            </template>
          </el-table-column>
          <el-table-column align="center" label="提现手续费" width="120">
            <template slot-scope="scope">
              <el-input size="small" v-model="scope.row.fee" placeholder></el-input>
            </template>
          </el-table-column>
          <el-table-column align="center" label="更新时间" width="220">
            <template slot-scope="scope">
              {{ scope.row.updated_at|filterDate }}
            </template>
          </el-table-column>
          <el-table-column v-if="checkPermission(['admin'])" align="center" label="操作" width="220" fixed="right">
            <template slot-scope="scope">
              <el-button type="danger" size="small" @click="handleSecondUpdate(scope)">确定修改</el-button>
              <el-button type="danger" size="small" @click="handleSecondDelete(scope)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-tab-pane>
      <el-tab-pane label="分佣比例设置(针对企业)" name="third">
        <el-table :data="commissionList" style="width: 100%;margin-top:10px;">
          <el-table-column align="center" label="编号" width="50">
            <template slot-scope="scope">
              {{ scope.row.id }}
            </template>
          </el-table-column>
          <el-table-column align="center" label="参数名" width="220">
            <template slot-scope="scope">
              {{ scope.row.type }}
            </template>
          </el-table-column>
          <el-table-column align="center" label="参数值" width="120">
            <template slot-scope="scope">
              <el-input size="small" v-model="scope.row.value" placeholder></el-input>
            </template>
          </el-table-column>
          <el-table-column align="center" label="说明" width="220">
            <template slot-scope="scope">
              {{scope.row.desc}}
            </template>
          </el-table-column>
          <el-table-column align="center" label="更新时间" width="220">
            <template slot-scope="scope">
              {{ scope.row.updated_at|filterDate }}
            </template>
          </el-table-column>
          <el-table-column v-if="checkPermission(['admin'])" align="center" label="操作" width="120" fixed="right">
            <template slot-scope="scope">
              <el-button type="danger" size="small" @click="handleUpdate(scope)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-tab-pane>
    </el-tabs>


    <el-dialog :visible.sync="dialogVisible" title="新建用户费率">
      <el-form :model="fee" label-width="100px" label-position="left">
        <el-form-item label="用户名">
          <el-input v-model="fee.username" placeholder="用户名" />
        </el-form-item>
        <el-form-item label="提现手续费">
          <el-input v-model="fee.fee" placeholder="提现手续费" />
        </el-form-item>
      </el-form>
      <div style="text-align:right;">
        <el-button type="danger" @click="dialogVisible=false">取消</el-button>
        <el-button type="primary" @click="confirmAddFee">提交</el-button>
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
  import waves from '@/directive/waves/index.js' // 水波纹指令

  export default {
    directives: {
      waves
    },
    data() {
      return {
        setList: [],
        feeList: [],
        nameFilterText: null,
        typeFilterText: 0,
        commissionList: [],
        activeName: 'first',
        fee:{},
        dialogVisible: false,
        typeoptions: [{
          value: 0,
          label: '全部'
        }, {
          value: 1,
          label: '个人'
        }, {
          value: 2,
          label: '企业'
        }]
      }
    },

    filters: {
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
      filterType(type) {
        if (type == 1) {
          return "个人"
        } else if (type == 2) {
          return "企业"
        }
      }
    },
    created() {
      this.getSetList()
      this.getFeeList()
    },
    methods: {
      checkPermission,

      async getSetList() {
        let obj = {}
        const res = await this.api.set().getSetList(obj)
        if (res.code == 1) {
          this.setList = res.data
        }
      },

      async handleFirstUpdate({
        $index,
        row
      }) {
        const res = await this.api.set().sysUpdateSet(row)
        if (res.code == 1) {
          this.$message.success('操作成功')
        } else {
          this.$message.error('操作失败')
        }
      },

      confirmSecondSearch() {
        this.getFeeList()
      },
      async getFeeList() {
        let obj = {
          'type': this.typeFilterText,
          'username': this.nameFilterText
        }
        const res = await this.api.set().getFeeList(obj)
        if (res.code == 1) {
          this.feeList = res.data
        }
      },

      async handleSecondUpdate({
        $index,
        row
      }) {
        const res = await this.api.set().sysUpdateFee(row)
        if (res.code == 1) {
          this.$message.success('操作成功')
        } else {
          this.$message.error('操作失败')
        }
      },

      handleSecondDelete({
        $index,
        row
      }) {
        this.$confirm('确定删除?', '提示', {
            confirmButtonText: '确认',
            cancelButtonText: '取消',
            type: 'warning'
          })
          .then(async () => {
            await this.api.set().sysDeleteFee(row)
            this.feeList.splice($index, 1)
            this.$message({
              type: 'success',
              message: '删除成功!'
            })
          })
          .catch(err => {
            console.error(err)
          })
      },

      addUserFee() {
          this.$message({
              type: 'info',
              message: '暂未实现!'
            })
          // this.dialogVisible = true
      },

      confirmAddFee() {
        this.dialogVisible = false
      }


    },
  }
</script>

<style lang="scss" scoped>
  .app-container {}
</style>
 -->