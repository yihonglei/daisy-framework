<template>
  <div class="app-container">
    <el-tabs v-model="activeName">
      <el-tab-pane label="轮播图" name="first">
        <div v-if="checkPermission(['admin'])" class="filter-container" style="margin-top: 20px;">
          <el-button v-waves class="filter-item" style="margin-left: 10px;" type="primary" @click="handleAddImage">
            新增轮播图
          </el-button>
        </div>
        <el-table :data="imageList" style="width: 100%;margin-top:10px;">
          <el-table-column align="center" label="ID" width="50">
            <template slot-scope="scope">
              {{ scope.row.id }}
            </template>
          </el-table-column>
          <el-table-column align="center" label="位置" width="120">
            <template slot-scope="scope">
              {{ scope.row.place|filterPlace }}
            </template>
          </el-table-column>
          <el-table-column align="header-center" label="图片" width="120">
            <template slot-scope="scope">
              <img :src="scope.row.src" style="width: 80px;height: 80px;" />
            </template>
          </el-table-column>
          <el-table-column align="center" label="轮播图名称" width="200">
            <template slot-scope="scope">
              {{ scope.row.title }}
            </template>
          </el-table-column>
          <el-table-column align="center" label="状态">
            <template slot-scope="scope">
              {{ scope.row.enable|filterEnable }}
            </template>
          </el-table-column>
          <el-table-column align="center" label="是否启用" width="220">
            <template slot-scope="scope">
              <el-switch v-model="scope.row.enable" :active-value="1" :inactive-value="0"
                @change="switchChange(scope.row)"></el-switch>
            </template>
          </el-table-column>
          <el-table-column align="center" label="类型">
            <template slot-scope="scope">
              {{ scope.row.type|filterType }}
            </template>
          </el-table-column>
          <el-table-column align="center" label="跳转链接" width="200">
            <template slot-scope="scope">
              {{ scope.row.link }}
            </template>
          </el-table-column>
          <el-table-column align="center" label="排序">
            <template slot-scope="scope">
              {{ scope.row.sort }}
            </template>
          </el-table-column>
          <el-table-column align="center" label="生效开始时间" width="220">
            <template slot-scope="scope">
              {{ scope.row.start_at|filterDate }}
            </template>
          </el-table-column>
          <el-table-column align="center" label="生效结束时间" width="220">
            <template slot-scope="scope">
              {{ scope.row.end_at|filterDate }}
            </template>
          </el-table-column>
          <el-table-column align="center" label="创建时间" width="220">
            <template slot-scope="scope">
              {{ scope.row.createdAt|filterDate }}
            </template>
          </el-table-column>
          <el-table-column v-if="checkPermission(['admin'])" align="center" label="操作" width="150" fixed="right">
            <template slot-scope="scope">
              <el-button type="primary" size="small" @click="handleEdit(scope)">修改</el-button>
              <el-button type="danger" size="small" @click="handleDelete(scope)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-tab-pane>
    </el-tabs>

    <el-dialog :visible.sync="dialogVisible" :title="dialogType==='edit'?'编辑图片':'新建图片'">
      <el-form :model="image" label-width="100px" label-position="left">
        <el-form-item label="图片">
          <el-upload :data="dataObj" action="" :multiple="false" :show-file-list="false" :http-request="httpRequest"
            :before-upload="beforeUpload" :limit="1" class="image-uploader">
            <img :src="image.src+'?imageView2/1/w/80/h/80'" class="image-uploader">
            </img>
          </el-upload>
        </el-form-item>

        <el-form-item prop="place" label="位置" label-width="100px" label-position="left">
            <el-select v-model="image.place" class="filter-item" default-first-option>
              <el-option v-for="item in places" :key="item.value" :label="item.label" :value="item.value"></el-option>
            </el-select>
        </el-form-item>

        <el-form-item label="标题">
          <el-input v-model="image.title" placeholder="请输入标题" />
        </el-form-item>

        <el-form-item prop="sort" label="排序" label-width="100px" label-position="left">
          <el-input type="number" min="1" v-model="image.sort"
            style="width: 100px;margin-left: 5px;margin-right: 5px;"></el-input>
        </el-form-item>

        <el-form-item label="类型">
          <el-select v-model="image.type" class="filter-item" default-first-option>
            <el-option v-for="item in types" :key="item.value" :label="item.label" :value="item.value"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item v-if="image.type==2" label="链接">
          <el-input v-model="image.link" placeholder="跳转链接" />
        </el-form-item>

        <el-form-item label="是否可用">
          <el-checkbox v-model="image.enable" label="是否可用" :true-label="1" :false-label="0"
            @change="handleCheckHidden"></el-checkbox>
        </el-form-item>

        <el-form-item label="开始时间">
          <el-date-picker class="filter-item" v-model="image.start_at" type="date" placeholder="请选择生效时间"
            value-format="yyyy-MM-dd"></el-date-picker>
        </el-form-item>

        <el-form-item label="结束时间">
          <el-date-picker class="filter-item" v-model="image.end_at" type="date" placeholder="请选择失效时间"
            value-format="yyyy-MM-dd"></el-date-picker>
        </el-form-item>

      </el-form>
      <div style="text-align:right;">
        <el-button type="danger" @click="dialogVisible=false">取消</el-button>
        <el-button type="primary" @click="confirmImage">提交</el-button>
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
  import {
    client,
    getFileNameUUID
  } from '@/utils/alioss.js'

  const defaultImage = {
    id: 0,
    place: 1,
    title: '',
    src: '',
    sort: 1,
    type: 1,
    link: '',
    enable: 1,
    start_at: new Date(),
    end_at: new Date()
  }

  export default {
    directives: {
      waves
    },

    data() {
      return {
        startDate: new Date(),
        endDate: new Date(),
        imageList: [],
        image: {
          id: 0,
          place: 1,
          title: '',
          src: '',
          sort: 1,
          type: 1,
          link: '',
          enable: 1,
          start_at: new Date(),
          end_at: new Date()
        },
        dialogVisible: false,
        dialogType: 'new',
        checkStrictly: false,
        dataObj: {
          token: '',
          key: ''
        },
        places: [{
          value: 1,
          label: '首页'
        }],
        types: [{
          value: 1,
          label: '纯展示'
        },
        {
          value: 2,
          label: '链接'
        }],
        activeName: 'first',
        alipaynum: '',
        alipayname: '',
        optionsvalue: {
          value: 2,
          label: '充值成功'
        },
        options: [{
          value: 0,
          label: '全部'
        }, {
          value: 1,
          label: '待充值'
        }, {
          value: 2,
          label: '充值成功'
        }, {
          value: 3,
          label: '充值失败'
        }]
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
      filterPlace(place) {
        if (place == 1) {
          return '首页'
        }
      },
      filterType(type) {
        if (type == 1) {
          return '纯展示'
        } else if (type == 2) {
          return '链接跳转'
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
      this.getImageList()
    },
    methods: {
      checkPermission,
      async getImageList() {
        let obj = {

        }
        const res = await this.api.image().getImageList(obj)
        if (res.code == 1) {
          this.imageList = res.data
        }
      },

      async switchChange(obj) {
        const res = await this.api.image().sysUpdateEnable(obj)
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

      handleDelete({
        $index,
        row
      }) {
        this.$confirm('确定删除?', '提示', {
            confirmButtonText: '确认',
            cancelButtonText: '取消',
            type: 'warning'
          })
          .then(async () => {
            await this.api.image().sysDeleteImage({
              'id': row.id
            })
            this.imageList.splice($index, 1)
            this.$message({
              type: 'success',
              message: '删除成功!'
            })
          })
          .catch(err => {
            console.error(err)
          })
      },

      handleCheckHidden(val) {
        this.image.enable = val
      },
      handleAddImage() {
        this.image = Object.assign({}, defaultImage)
        this.dialogType = 'new'
        this.dialogVisible = true
      },

      handleEdit(scope) {
        this.dialogType = 'edit'
        this.dialogVisible = true
        this.checkStrictly = true
        this.image = deepClone(scope.row)
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
        var fileName = 'mhjy/' + 'banner' + getFileNameUUID() + '.jpg';
        client().put(fileName, file).then(({
          res,
          url,
          name
        }) => {
          if (res && res.status == 200) {
            // console.log(`阿里云OSS上传文件成功回调`, res, url, name);
            this.image.src = url;
          }
        }).catch((err) => {
          console.log(`阿里云OSS上传失败回调`, err);
        });
      },

      async confirmImage() {
        if (this.dialogType == 'new') {
          const res = await this.api.image().sysAddImage(this.image)
          if (res.code == 1) {
            this.$message.success('新增成功')
            this.dialogVisible = false
            this.getImageList()
          }
        } else {
          if (this.image.type == 1) {
            this.image.link = ''
          }
          const res = await this.api.image().sysUpdateImage(this.image)
          if (res.code == 1) {
            this.$message.success('编辑成功')
            this.dialogVisible = false
            this.getImageList()
          }
        }
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

