<!-- <template>
  <div>
    <div class="filter-container" style="margin-top: 20px;">
      <label class="filter-item" style="font-size: 10pt;margin-left: 10px;">用户ID：</label>
      <el-input v-model="filter.userId" placeholder="可输入用户ID查询" style="width: 200px;margin-right: 10px;"
        class="filter-item" />
      <label class="filter-item" style="font-size: 10pt;margin-left: 10px;">状态：</label>
      <el-select v-model="filter.direction" class="filter-item" default-first-option>
        <el-option v-for="item in directions" :key="item.value" :label="item.label" :value="item.value"></el-option>
      </el-select>
      <el-button v-waves class="filter-item" style="margin-left: 10px;" type="primary" @click="confirmSearch">
        查询
      </el-button>
    </div>
    <el-table :data="fundList" style="width: 100%;margin-top:10px;">
      <el-table-column align="center" label="ID" width="50">
        <template slot-scope="scope">
          {{ scope.row.id }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="用户ID" width="120">
        <template slot-scope="scope">
          {{ scope.row.uid }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="用户名" width="120">
        <template slot-scope="scope">
          {{ scope.row.nickname }}
        </template>
      </el-table-column>
      <el-table-column v-if="checkPermission(['admin'])" align="center" label="所属企业" width="120">
        <template slot-scope="scope">
          {{ scope.row.cid }}
        </template>
      </el-table-column>
      <el-table-column v-if="checkPermission(['admin'])" align="center" label="所属企业" width="120">
        <template slot-scope="scope">
          {{ scope.row.companyname }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="类型" width="120">
        <template slot-scope="scope">
          {{ scope.row.type|filterType }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="alongid" width="200">
        <template slot-scope="scope">
          {{ scope.row.alongid }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="序列号" width="200">
        <template slot-scope="scope">
          {{ scope.row.serialno }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="状态" width="200">
        <template slot-scope="scope">
          {{ scope.row.direction|filterDirection }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="说明" width="200">
        <template slot-scope="scope">
          {{ scope.row.desc }}
        </template>
      </el-table-column>
      <el-table-column align="center" label="创建时间" width="220">
        <template slot-scope="scope">
          {{ scope.row.createdAt|filterDate }}
        </template>
      </el-table-column>
      <!-- <el-table-column v-if="checkPermission(['admin'])" align="center" label="操作" width="80" fixed="right">
        <template slot-scope="scope">
          <el-button type="danger" size="small" @click="handlePushDelete(scope)">删除</el-button>
        </template>
      </el-table-column> -->
    </el-table>
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


  const filter = {
    userId: '',
    direction: 0
  }

  export default {
    directives: {
      waves
    },

    data() {
      return {
        fundList: [],
        filter: Object.assign({}, filter),
        directions: [{
            value: 0,
            label: '全部'
          },
          {
            value: 1,
            label: '收入'
          },
          {
            value: 2,
            label: '支出'
          }
        ]
      }
    },

    filters: {
      filterType(type) {
        if (type == 1) {
          return "用户纸条被抽返佣"
        } else if (type == 2) {
          return "企业返佣"
        } else if (type == 3) {
          return "邀请用户纸条被抽返佣"
        } else if (type == 4) {
          return "提现"
        }
      },
      filterDirection(direction) {
        if (direction == 1) {
          return '收入'
        } else {
          return '支出'
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
    },
    created() {

    },
    methods: {
      checkPermission,
      async getFundList() {
        let obj = this.filter
        const res = await this.api.finance().getFundList(obj)
        if (res.code == 1) {
          this.fundList = res.data
        }
      },

      confirmSearch() {
        this.getFundList()
      }

    },
  }
</script>

<style lang="scss" scoped>
  .app-container {}
</style>
 -->
