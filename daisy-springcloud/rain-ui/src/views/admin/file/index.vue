<template>
  <div class="execution">
    <basic-container>
      <avue-crud
          ref="crud"
          v-model:page="page"
          :data="tableData"
          :permission="permissionList"
          :table-loading="tableLoading"
          :option="tableOption"
          :upload-after="uploadAfter"
          @on-load="getList"
          @search-change="searchChange"
          @refresh-change="refreshChange"
          @size-change="sizeChange"
          @current-change="currentChange"
          @row-del="rowDel"
      >
        <template #menu="scope">
          <el-button
              text
              type="primary"
              icon="el-icon-download"
              @click="download(scope.row, scope.index)"
          >下载
          </el-button>
          <el-button
              text
              type="primary"
              icon="el-icon-view"
              @click="onlineFile(scope.row, scope.index)"
          >在线浏览
          </el-button>
        </template>
      </avue-crud>
    </basic-container>
  </div>
</template>

<script>
import {delObj, fetchList, onlineFile} from "@/api/admin/file";
import {tableOption} from "@/const/crud/admin/file";
import {mapGetters} from "vuex";

export default {
  name: "sys-file",
  data() {
    return {
      searchForm: {},
      tableData: [],
      page: {
        total: 0, // 总页数
        currentPage: 1, // 当前页数
        pageSize: 20 // 每页显示多少条
      },
      tableLoading: false,
      tableOption: tableOption
    };
  },
  computed: {
    ...mapGetters(["permissions"]),
    permissionList() {
      return {
        addBtn: this.validData(this.permissions.sys_file_add, true),
        delBtn: this.validData(this.permissions.sys_file_del, true),
        editBtn: this.validData(this.permissions.sys_file_edit, false)
      };
    }
  },
  methods: {
    getList(page, params) {
      this.tableLoading = true;
      fetchList(
          Object.assign(
              {
                descs: "create_time",
                current: page.currentPage,
                size: page.pageSize
              },
              params,
              this.searchForm
          )
      )
          .then(response => {
            this.tableData = response.data.data.records;
            this.page.total = response.data.data.total;
            this.tableLoading = false;
          })
          .catch(() => {
            this.tableLoading = false;
          });
    },
    rowDel: function (row, index) {
      let _this = this;
      this.$confirm("是否确认删除ID为" + row.id, "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
          .then(function () {
            return delObj(row.id);
          })
          .then(data => {
            _this.$message.success("删除成功");
            this.getList(this.page);
          });
    },
    searchChange(form, done) {
      this.searchForm = form;
      this.page.currentPage = 1;
      this.getList(this.page, form);
      done();
    },
    refreshChange() {
      this.getList(this.page);
    },
    sizeChange(pageSize) {
      this.page.pageSize = pageSize;
    },
    currentChange(current) {
      this.page.currentPage = current;
    },
    download: function (row, index) {
      this.downBlobFile(
          "/admin/sys-file/" + row.bucketName + "/" + row.fileName,
          this.searchForm,
          row.fileName
      )
    },
    uploadAfter(res, done, loading) {
      if (!this.validatenull(res.fileName)) {
        this.$message.success("上传成功");
        this.getList(this.page);
      }
      done();
    },
    onlineFile(row, index) {
      onlineFile(row.bucketName, row.fileName).then(res => {
        window.open(res.data.data, '_blank')
      })
    }
  }
};
</script>
