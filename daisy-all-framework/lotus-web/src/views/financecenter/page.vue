<!-- <template>
  <div class="app-container">
    <el-tabs v-model="activeName">
      <el-tab-pane label="提现管理" name="first">
        <div class="filter-container" style="margin-top: 20px;">
          <label class="filter-item" style="font-size: 10pt;margin-left: 10px;">审核状态：</label>
          <el-select v-model="firstFilter.status" class="filter-item" default-first-option
            style="width: 120px;margin-right: 10px;">
            <el-option v-for="item in firstFilterStatus" :key="item.value" :label="item.label" :value="item.value">
            </el-option>
          </el-select>
          <el-input class="filter-item" v-model="firstFilter.alipaynum" placeholder="请输入支付宝号查询"
            style="width: 180px;margin-right: 10px;"></el-input>
          <el-input class="filter-item" v-model="firstFilter.realname" placeholder="请输入真实姓名查询" style="width: 180px;">
          </el-input>
          <el-button v-waves class="filter-item" style="margin-left: 10px;" type="primary" @click="confirmFirstSearch">
            查询
          </el-button>
          <el-button v-waves class="filter-item" style="margin-left: 10px;" type="primary" @click="resetFirstSearch">
            重置
          </el-button>
        </div>
        <el-table :data="financeList" style="width: 100%;margin-top:10px;">
          <el-table-column align="center" label="ID" width="50">
            <template slot-scope="scope">
              {{ scope.row.id }}
            </template>
          </el-table-column>
          <el-table-column align="center" label="用户ID" width="80">
            <template slot-scope="scope">
              {{ scope.row.uid }}
            </template>
          </el-table-column>
          <el-table-column align="center" label="用户名" width="120">
            <template slot-scope="scope">
              {{ scope.row.username }}
            </template>
          </el-table-column>
          <el-table-column v-if="checkPermission(['admin'])" align="center" label="所属企业" width="220">
            <template slot-scope="scope">
              {{ scope.row.cusername }}
            </template>
          </el-table-column>
          <el-table-column align="center" label="提现金额" width="120">
            <template slot-scope="scope">
              {{ scope.row.cashmoney }}
            </template>
          </el-table-column>
          <el-table-column align="center" label="费率">
            <template slot-scope="scope">
              {{ scope.row.fee }}
            </template>
          </el-table-column>
          <el-table-column align="center" label="到账金额">
            <template slot-scope="scope">
              {{ scope.row.outmoney }}
            </template>
          </el-table-column>

          <el-table-column align="center" label="支付宝号">
            <template slot-scope="scope">
              {{ scope.row.alipaynum }}
            </template>
          </el-table-column>
          <el-table-column align="center" label="真实姓名">
            <template slot-scope="scope">
              {{ scope.row.realname }}
            </template>
          </el-table-column>
          <el-table-column align="center" width="220" label="状态">
            <template slot-scope="scope">
              {{ scope.row.status|filterStatus }}
            </template>
          </el-table-column>
          <el-table-column align="center" width="220" label="审核原因">
            <template slot-scope="scope">
              {{ scope.row.reason }}
            </template>
          </el-table-column>
          <el-table-column align="center" label="创建时间" width="220">
            <template slot-scope="scope">
              {{ scope.row.createdAt|filterDate }}
            </template>
          </el-table-column>
          <el-table-column align="center" label="更新时间" width="220">
            <template slot-scope="scope">
              {{ scope.row.updated_at|filterDate }}
            </template>
          </el-table-column>
          <el-table-column v-if="checkPermission(['admin'])" align="center" fixed="right" label="操作" width="120">
            <template slot-scope="scope">
              <el-button v-if="scope.row.status==1" type="primary" size="small" @click="verifyCheck(scope)">审核
              </el-button>
              <el-button v-else-if="scope.row.status==2" type="primary" size="small" @click="verifyOutMoney(scope)">去拨款
              </el-button>
              <el-button v-else-if="scope.row.status==3" type="danger" disabled size="small">审核拒绝</el-button>
              <el-button v-else-if="scope.row.status==4" type="primary" disabled size="small">已拨款</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-tab-pane>

      <el-tab-pane label="提现统计" name="second" style="min-height: 500px;">
        <div class="filter-container" style="margin-top: 20px;">
          <el-date-picker class="filter-item" v-model="secondFilterDate" type="date" placeholder="选择开始日期"
            value-format="yyyy-MM-dd"></el-date-picker>
          <el-button v-waves class="filter-item" style="margin-left: 10px;" type="primary"
            @click="confirmSecondSearch(1)">
            按天统计
          </el-button>
          <el-button v-waves class="filter-item" style="margin-left: 10px;" type="primary"
            @click="confirmSecondSearch(2)">
            按月统计
          </el-button>
          <el-button v-waves class="filter-item" style="margin-left: 10px;" type="primary"
            @click="confirmSecondSearch(3)">
            按年统计
          </el-button>
        </div>
        <el-row :gutter="12" style="margin-top:30px;">
          <el-col :span="6">
            <el-card class="box-card">
              <div slot="header" class="clearfix">
                <span>累计提现金额</span>
              </div>
              <div>
                {{secondResult.outAmount|filterAmountF}}元
              </div>
            </el-card>
          </el-col>

          <el-col :span="6">
            <el-card class="box-card">
              <div slot="header" class="clearfix">
                <span>提现次数</span>
              </div>
              <div>
                {{secondResult.outCount}}笔
              </div>
            </el-card>
          </el-col>

          <el-col :span="6">
            <el-card class="box-card">
              <div slot="header" class="clearfix">
                <span>待转账次数</span>
              </div>
              <div>
                {{secondResult.woutCount}}笔
              </div>
            </el-card>
          </el-col>
        </el-row>

      </el-tab-pane>
      <el-tab-pane label="充值统计" name="third" style="min-height: 500px;">
        <div class="filter-container" style="margin-top: 20px;">
          <el-date-picker class="filter-item" v-model="thirdFilterDate" type="date" placeholder="选择开始日期"
            value-format="yyyy-MM-dd"></el-date-picker>
          <el-button v-waves class="filter-item" style="margin-left: 10px;" type="primary" @click="confirmThirdSearch(1)">
            按天统计
          </el-button>
          <el-button v-waves class="filter-item" style="margin-left: 10px;" type="primary" @click="confirmThirdSearch(2)">
            按月统计
          </el-button>
          <el-button v-waves class="filter-item" style="margin-left: 10px;" type="primary" @click="confirmThirdSearch(3)">
            按年统计
          </el-button>
        </div>

        <el-row :gutter="12" style="margin-top:30px;">
          <el-col :span="6">
            <el-card class="box-card">
              <div slot="header" class="clearfix">
                <span>充值总金额</span>
              </div>
              <div>
                {{thirdResult.totalAmount|filterAmount}}元
              </div>
            </el-card>
          </el-col>
        </el-row>

        <el-row :gutter="12" style="margin-top:30px;">
          <el-col :span="6">
            <el-card class="box-card">
              <div slot="header" class="clearfix">
                <span>VIP充值金额</span>
              </div>
              <div>
                {{thirdResult.vipAmount|filterAmount}}元
              </div>
            </el-card>
          </el-col>

          <el-col :span="6">
            <el-card class="box-card">
              <div slot="header" class="clearfix">
                <span>续费充值金额</span>
              </div>
              <div>
                {{thirdResult.conAmount|filterAmount}}元
              </div>
            </el-card>
          </el-col>

          <el-col :span="6">
            <el-card class="box-card">
              <div slot="header" class="clearfix">
                <span>抽纸条充值金额</span>
              </div>
              <div>
                {{thirdResult.pullAmount|filterAmount}}元
              </div>
            </el-card>
          </el-col>

          <el-col :span="6">
            <el-card class="box-card">
              <div slot="header" class="clearfix">
                <span>放纸条充值金额</span>
              </div>
              <div>
                {{thirdResult.pushAmount|filterAmount}}元
              </div>
            </el-card>
          </el-col>

        </el-row>
      </el-tab-pane>

      <el-tab-pane label="充值记录" name="fourth" style="min-height: 500px;">
        <div class="filter-container" style="margin-top: 20px;">
          <label class="filter-item" style="font-size: 10pt;">状态：</label>
          <el-select v-model="fourthFilter.status" class="filter-item" default-first-option>
            <el-option v-for="item in fourthFilterStatus" :key="item.value" :label="item.label" :value="item.value">
            </el-option>
          </el-select>
          <label class="filter-item" style="font-size: 10pt;margin-left: 10px;">类型：</label>
          <el-select v-model="fourthFilter.type" class="filter-item" default-first-option>
            <el-option v-for="item in fourthFilterType" :key="item.value" :label="item.label" :value="item.value">
            </el-option>
          </el-select>
          <label class="filter-item" style="font-size: 10pt;margin-left: 10px;">开始时间：</label>
          <el-date-picker class="filter-item" v-model="fourthFilter.startDate" type="date" placeholder="选择开始日期"
            value-format="yyyy-MM-dd"></el-date-picker>
          <label class="filter-item" style="font-size: 10pt;margin-left: 10px;">结束时间：</label>
          <el-date-picker class="filter-item" v-model="fourthFilter.endDate" type="date" placeholder="选择结束日期"
            value-format="yyyy-MM-dd">
          </el-date-picker>

          <el-button v-waves class="filter-item" style="margin-left: 10px;" type="primary" @click="confirmFourthSearch">
            查询
          </el-button>
          <el-button v-waves class="filter-item" style="margin-left: 10px;" type="primary" @click="resetFourthSearch">
            重置
          </el-button>
        </div>

        <el-table :data="chargesList" style="width: 100%;margin-top:10px;">
          <el-table-column align="center" label="编号" width="50">
            <template slot-scope="scope">
              {{ scope.row.id }}
            </template>
          </el-table-column>
          <el-table-column align="center" label="用户名称" width="120">
            <template slot-scope="scope">
              {{ scope.row.username }}
            </template>
          </el-table-column>
          <el-table-column align="center" label="充值订单号" width="220">
            <template slot-scope="scope">
              {{ scope.row.trade_no }}
            </template>
          </el-table-column>
          <el-table-column align="header-center" label="充值金额" width="120">
            <template slot-scope="scope">
              {{ scope.row.fen }} 分
            </template>
          </el-table-column>
          <el-table-column align="center" label="分类" width="120">
            <template slot-scope="scope">
              {{ scope.row.type|filterType }}
            </template>
          </el-table-column>
          <el-table-column align="center" label="状态" width="120">
            <template slot-scope="scope">
              {{ scope.row.status|filterChargeStatus }}
            </template>
          </el-table-column>
          <el-table-column align="center" label="创建时间" width="220">
            <template slot-scope="scope">
              {{ scope.row.createdAt|filterDate }}
            </template>
          </el-table-column>
          <el-table-column align="center" label="支付时间" width="220">
            <template slot-scope="scope">
              {{ scope.row.upated_at|filterDate }}
            </template>
          </el-table-column>
        </el-table>
      </el-tab-pane>
    </el-tabs>

    <el-dialog :visible.sync="dialogVisible" title="审核">
      <el-form :model="verify" label-width="100px" label-position="left">
        <el-form-item label="类型">
          <el-select v-model="verify.status" class="filter-item" default-first-option>
            <el-option v-for="item in optionsVerifyStatus" :key="item.value" :label="item.label" :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="原因">
          <el-input v-model="verify.reason" placeholder="审核原因(限100字)" maxlength="100" />
        </el-form-item>
      </el-form>
      <div style="text-align:right;">
        <el-button type="danger" @click="dialogVisible=false">取消</el-button>
        <el-button type="primary" @click="confirmVerify">提交</el-button>
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

  const defaultVerify = {
    id: 0,
    status: 1,
    reason: ""
  }

  const firstFilter = {
    status: 0,
    alipaynum: null,
    realname: null,
  }

  const thirdFilter = {
    status: 0,
    type: 0,
    startDate: new Date(),
    endDate: new Date()
  }

  const fourthFilter = {
    status: 1,
    type: 0,
    startDate: new Date(),
    endDate: new Date()
  }

  const firstFilterStatus = [{
    value: 0,
    label: '全部'
  }, {
    value: 1,
    label: '待审核'
  }, {
    value: 2,
    label: '审核成功'
  }, {
    value: 3,
    label: '审核失败'
  }, {
    value: 4,
    label: '已拨款'
  }]

  const optionsVerifyStatus = [{
    value: 1,
    label: '待审核'
  }, {
    value: 2,
    label: '审核成功'
  }, {
    value: 3,
    label: '审核失败'
  }]

  const fourthFilterStatus = [{
    value: 0,
    label: '全部'
  }, {
    value: 1,
    label: '充值成功'
  }, {
    value: 2,
    label: '充值失败'
  }]

  const fourthFilterType = [{
    value: 0,
    label: '全部'
  }, {
    value: 1,
    label: 'VIP充值'
  }, {
    value: 2,
    label: '代理充值'
  }, {
    value: 3,
    label: '抽取纸条'
  }, {
    value: 4,
    label: '放入纸条'
  }]

  export default {
    directives: {
      waves
    },

    data() {
      return {
        secondFilterDate: new Date(),
        thirdFilterDate: new Date(),
        usersList: [],
        financeList: [],
        chargesList:[],
        dialogVisible: false,
        dialogType: 'new',
        checkStrictly: false,
        activeName: 'first',
        verify: Object.assign({}, defaultVerify),
        firstFilter: Object.assign({}, firstFilter),
        fourthFilter: Object.assign({}, fourthFilter),
        firstFilterStatus: Object.assign({}, firstFilterStatus),
        optionsVerifyStatus: Object.assign({}, optionsVerifyStatus),
        fourthFilterStatus: Object.assign({}, fourthFilterStatus),
        fourthFilterType: Object.assign({}, fourthFilterType),
        secondResult: {
          outAmount: 0,
          outCount: 0,
          woutCount: 0
        },
        thirdResult: {
          totalAmount: 0,
          vipAmount: 0,
          conAmount: 0,
          pullAmount: 0,
          pushAmount: 0
        }
      }
    },

    filters: {
      filterStatus(status) {
        if (status == 1) {
          return "审核中"
        } else if (status == 2) {
          return "审核通过，待拨款"
        } else if (status == 3) {
          return "审核拒绝"
        } else if (status == 4) {
          return "已拨款"
        }
      },
      filterChargeStatus(status) {
        if (status == 0) {
          return "充值中"
        } else if (status == 1) {
          return "充值成功"
        } else if (status == 2) {
          return "充值失败"
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
      },
      filterType(type) {
        if (type == 1) {
          return "VIP充值"
        } else if (type == 2) {
          return "代理续费"
        } else if (type == 3) {
          return "抽纸条"
        } else if (type == 4) {
          return "放纸条"
        }
      },
      filterAmount(amount) {
        return parseInt(amount/10)
      },
      filterAmountF(amount) {
        return parseInt(amount*100)/100
      }
    },
    created() {

    },
    methods: {
      checkPermission,

      verifyCheck({
        $index,
        row
      }) {
        this.verify.id = this.financeList[$index].id
        this.dialogVisible = true
      },

      async verifyOutMoney({
        $index,
        row
      }) {
        let obj = {
          id: this.financeList[$index].id,
        }
        const res = await this.api.finance().verifyOutMoney(obj)
        if (res.code == 1) {
          this.$message.success('操作成功')
          this.confirmFirstSearch()
        }
      },

      async confirmVerify() {
        let obj = this.verify
        const res = await this.api.finance().verifyCashOut(obj)
        if (res.code == 1) {
          this.$message.success('操作成功')
          this.dialogVisible = false
          this.verify = deepClone(defaultVerify)
          this.confirmFirstSearch()
        }
      },

      resetSearch() {

      },

      async confirmFirstSearch() {
        const res = await this.api.finance().getFinanceList(this.firstFilter)
        if (res.code == 1) {
          this.financeList = res.data
        }
      },

      resetFirstSearch() {
        this.firstFilter = deepClone(firstFilter)
        this.confirmFirstSearch()
      },

      /* 提现统计 */
      async confirmSecondSearch(index) {
        let obj = {
          date: this.secondFilterDate,
          type: index
        }

        const res = await this.api.finance().financeCashoutAny(obj)
        if (res.code == 1) {
          this.secondResult = res.data
        }
      },

      async confirmThirdSearch(index) {
        let obj = {
          date: this.thirdFilterDate,
          index: index
        }

        const res = await this.api.finance().financeChargeAny(obj)
        if (res.code == 1) {
          this.thirdResult = res.data
        }
      },

      /*充值统计*/
      async confirmFourthSearch() {
        console.log(this.fourthFilter)
        const res = await this.api.finance().sysChargeList(this.fourthFilter)
        if (res.code == 1) {
          this.chargesList = res.data
        }
      },
      resetFourthSearch() {
        this.fourthFilter = Object.assign({}, fourthFilter)
        this.confirmFourthSearch()
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

      inviteUser() {
        // 生成邀请用户二维码

      },
    },
  }
</script>

<style lang="scss" scoped>
  .app-container {}
</style>
 -->
