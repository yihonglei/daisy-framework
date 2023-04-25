<!-- <template>
  <div class="app-container">
    <el-tabs v-model="activeName">
      <el-tab-pane label="用户反馈" name="first">
        <div class="filter-container" style="margin-top: 20px;">
          <label class="filter-item" style="font-size: 10pt;margin-left: 10px;">开始时间：</label>
          <el-date-picker class="filter-item" v-model="startDate" type="date" placeholder="选择开始日期"
            value-format="yyyy-MM-dd"></el-date-picker>
          <label class="filter-item" style="font-size: 10pt;margin-left: 10px;">结束时间：</label>
          <el-date-picker class="filter-item" v-model="endDate" type="date" placeholder="选择结束日期"
            value-format="yyyy-MM-dd">
          </el-date-picker>
          <el-button v-waves class="filter-item" style="margin-left: 10px;" type="primary" @click="confirmSearch">
            查询
          </el-button>
        </div>

        <el-table :data="messageList" style="width: 100%;margin-top:10px;">
          <el-table-column align="center" label="编号" width="50">
            <template slot-scope="scope">
              {{ scope.row.id }}
            </template>
          </el-table-column>
          <el-table-column align="center" label="用户名" width="220">
            <template slot-scope="scope">
              {{ scope.row.suggest_nickname }}
            </template>
          </el-table-column>
          <el-table-column align="center" label="内容" width="220">
            <template slot-scope="scope">
              {{ scope.row.content }}
            </template>
          </el-table-column>
          <el-table-column align="center" label="创建时间" width="220">
            <template slot-scope="scope">
              {{ scope.row.createdAt|filterDate }}
            </template>
          </el-table-column>
        </el-table>
      </el-tab-pane>
    </el-tabs>
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
        messageList: [],
        activeName: 'first',
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
      }
    },
    created() {

    },
    methods: {
      checkPermission,

      confirmSearch() {
        if (!this.startDate) {
          this.$message.error('请先选择开始时间')
          return
        }
        if (!this.endDate) {
          this.$message.error('请先选择结束时间')
          return
        }
        this.getMessageList()
      },
      async getMessageList() {
        let obj = {
          startDate: this.startDate,
          endDate: this.endDate
        }
        const res = await this.api.message().getMessageList(obj)
        if (res.code == 1) {
          this.messageList = res.data
        }
      },

    },
  }
</script>

<style lang="scss" scoped>
  .app-container {}
</style>
 -->
