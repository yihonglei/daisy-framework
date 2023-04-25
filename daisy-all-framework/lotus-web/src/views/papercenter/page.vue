<!-- <template>
  <div class="app-container">
    <el-tabs v-model="activeName">
      <el-tab-pane label="放入纸条" name="first">
        <div class="filter-container" style="margin-top: 20px;">
          <label class="filter-item" style="font-size: 10pt;margin-left: 10px;">性别：</label>
          <el-select v-model="pushFilterSex" class="filter-item" default-first-option>
            <el-option v-for="item in sexs" :key="item.value" :label="item.label" :value="item.value"></el-option>
          </el-select>
          <label class="filter-item" style="font-size: 10pt;margin-left: 10px;">内容：</label>
          <el-input v-model="pushFilterText" placeholder="可输入纸条内容查询" style="width: 200px;margin-right: 10px;"
            class="filter-item" />
          <el-button v-waves class="filter-item" style="margin-left: 10px;" type="primary" @click="confirmPushSearch">
            查询
          </el-button>
        </div>
        <el-table :data="pushPaperList" style="width: 100%;margin-top:10px;">
          <el-table-column align="center" label="ID" width="50">
            <template slot-scope="scope">
              {{ scope.row.id }}
            </template>
          </el-table-column>
          <el-table-column align="center" label="昵称" width="120">
            <template slot-scope="scope">
              {{ scope.row.nickname }}
            </template>
          </el-table-column>
          <el-table-column align="header-center" label="图片" width="120">
            <template slot-scope="scope">
              <img :src="scope.row.portraits" style="width: 80px;height: 80px;" />
            </template>
          </el-table-column>
          <el-table-column v-if="checkPermission(['admin'])" align="center" label="所属企业" width="120">
            <template slot-scope="scope">
              {{ scope.row.companyname }}
            </template>
          </el-table-column>
          <el-table-column align="center" label="微信号" width="120">
            <template slot-scope="scope">
              {{ scope.row.vxnum }}
            </template>
          </el-table-column>
          <el-table-column align="center" label="性别" width="200">
            <template slot-scope="scope">
              {{ scope.row.sex|filterSex }}
            </template>
          </el-table-column>
          <el-table-column align="center" label="留言" width="200">
            <template slot-scope="scope">
              {{ scope.row.intro }}
            </template>
          </el-table-column>
          <el-table-column align="center" label="状态">
            <template slot-scope="scope">
              {{ scope.row.status|filterStatus }}
            </template>
          </el-table-column>
          <el-table-column align="center" label="是否启用" width="220">
            <template slot-scope="scope">
              <el-switch v-model="scope.row.status" :active-value="1" :inactive-value="0"
                @change="switchChangeEnable(scope.row)"></el-switch>
            </template>
          </el-table-column>
          <el-table-column align="center" label="精选纸条">
            <template slot-scope="scope">
              {{ scope.row.chosen|filterChosen }}
            </template>
          </el-table-column>
          <el-table-column align="center" label="是否精选" width="220">
            <template slot-scope="scope">
              <el-switch v-model="scope.row.chosen" :active-value="1" :inactive-value="0"
                @change="switchChangeChosen(scope.row)"></el-switch>
            </template>
          </el-table-column>
          <el-table-column align="center" label="创建时间" width="220">
            <template slot-scope="scope">
              {{ scope.row.createdAt|filterDate }}
            </template>
          </el-table-column>
          <el-table-column v-if="checkPermission(['admin'])" align="center" label="操作" width="80" fixed="right">
            <template slot-scope="scope">
              <el-button type="danger" size="small" @click="handlePushDelete(scope)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-tab-pane>

      <el-tab-pane label="抽取纸条" name="second">
        <div class="filter-container" style="margin-top: 20px;">
          <label class="filter-item" style="font-size: 10pt;margin-left: 10px;">性别：</label>
          <el-select v-model="pullFilterSex" class="filter-item" default-first-option>
            <el-option v-for="item in sexs" :key="item.value" :label="item.label" :value="item.value"></el-option>
          </el-select>
          <label class="filter-item" style="font-size: 10pt;margin-left: 10px;">内容：</label>
          <el-input v-model="pullFilterText" placeholder="可输入纸条内容查询" style="width: 200px;margin-right: 10px;"
            class="filter-item" />
          <el-button v-waves class="filter-item" style="margin-left: 10px;" type="primary" @click="confirmPullSearch">
            查询
          </el-button>
        </div>
        <el-table :data="pullPaperList" style="width: 100%;margin-top:10px;">
          <el-table-column align="center" label="ID" width="50">
            <template slot-scope="scope">
              {{ scope.row.id }}
            </template>
          </el-table-column>
          <el-table-column align="center" label="抽取人昵称" width="120">
            <template slot-scope="scope">
              {{ scope.row.cnickname }}
            </template>
          </el-table-column>
          <el-table-column v-if="checkPermission(['admin'])" align="center" label="抽取人所属企业" width="120">
            <template slot-scope="scope">
              {{ scope.row.companyname }}
            </template>
          </el-table-column>

          <el-table-column align="center" label="被抽取人昵称" width="120">
            <template slot-scope="scope">
              {{ scope.row.pnickname }}
            </template>
          </el-table-column>
          <el-table-column align="header-center" label="图片" width="120">
            <template slot-scope="scope">
              <img :src="scope.row.portraits" style="width: 80px;height: 80px;" />
            </template>
          </el-table-column>
          <el-table-column align="center" label="微信号" width="120">
            <template slot-scope="scope">
              {{ scope.row.vxnum }}
            </template>
          </el-table-column>
          <el-table-column align="center" label="性别" width="200">
            <template slot-scope="scope">
              {{ scope.row.sex|filterSex }}
            </template>
          </el-table-column>
          <el-table-column align="center" label="留言" width="200">
            <template slot-scope="scope">
              {{ scope.row.intro }}
            </template>
          </el-table-column>
          <el-table-column align="center" label="创建时间" width="220">
            <template slot-scope="scope">
              {{ scope.row.createdAt|filterDate }}
            </template>
          </el-table-column>
          <el-table-column v-if="checkPermission(['admin'])" align="center" label="操作" width="80" fixed="right">
            <template slot-scope="scope">
              <el-button type="danger" size="small" @click="handlePullDelete(scope)">删除</el-button>
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
        pullPaperList: [],
        pushPaperList: [],
        sexs: [{
            value: 0,
            label: '全部'
          },
          {
            value: 1,
            label: '男'
          },
          {
            value: 2,
            label: '女'
          }
        ],
        activeName: 'first',
        pushFilterText:'',
        pushFilterSex:0,
        pullFilterText:'',
        pullFilterSex:0
      }
    },

    filters: {
      filterStatus(status) {
        if (status) {
          return "正常"
        } else {
          return "禁用"
        }
      },
      filterSex(sex) {
        if (sex == 1) {
          return '男'
        } else {
          return '女'
        }
      },
      filterChosen(chosen) {
        if (chosen == 1) {
          return '是'
        } else {
          return '否'
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
      this.getPushPaperList()
      this.confirmPullSearch()
    },
    methods: {
      checkPermission,
      async getPushPaperList() {
        let obj = {
          sex: this.pushFilterSex,
          intro: this.pushFilterText
        }
        const res = await this.api.paper().getPushPaperList(obj)
        if (res.code == 1) {
          this.pushPaperList = res.data
        }
      },

      async getPullPaperList() {
        let obj = {
          sex: this.pullFilterSex,
          intro: this.pullFilterText
        }
        const res = await this.api.paper().getPullPaperList(obj)
        if (res.code == 1) {
          this.pullPaperList = res.data
        }
      },

      confirmPushSearch() {
        this.getPushPaperList()
      },
      confirmPullSearch() {
        this.getPullPaperList()
      },

      async switchChangeEnable(obj) {
        const res = await this.api.paper().sysUpdatePaperEnable(obj)
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

      async switchChangeChosen(obj) {
        const res = await this.api.paper().sysUpdatePaperChosen(obj)
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

      handlePushDelete({
        $index,
        row
      }) {
        this.$confirm('确定删除?', '提示', {
            confirmButtonText: '确认',
            cancelButtonText: '取消',
            type: 'warning'
          })
          .then(async () => {
            await this.api.paper().sysDeletePaper({
              'id': row.id
            })
            this.pushPaperList.splice($index, 1)
            this.$message({
              type: 'success',
              message: '删除成功!'
            })
          })
          .catch(err => {
            console.error(err)
          })
      },

      handlePullDelete({
        $index,
        row
      }) {
        this.$confirm('确定删除?', '提示', {
            confirmButtonText: '确认',
            cancelButtonText: '取消',
            type: 'warning'
          })
          .then(async () => {
            await this.api.paper().sysDeletePullPaper({
              'id': row.id
            })
            this.pullPaperList.splice($index, 1)
            this.$message({
              type: 'success',
              message: '删除成功!'
            })
          })
          .catch(err => {
            console.error(err)
          })
      },


    },
  }
</script>

<style lang="scss" scoped>
  .app-container {

    .image-uploader {
      background-color: #5cd7f7;
      width: 80px;
      height: 80px;
      border-radius: 40px;
      color: #000000;
    }
  }
</style>
 -->
