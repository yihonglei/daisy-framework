<template>
  <div class="user">
    <basic-container>
      <avue-crud
          :option="option"
          ref="crud"
          v-model="form"
          v-model:page="page"
          @on-load="getList"
          @size-change="sizeChange"
          @current-change="currentChange"
          :table-loading="listLoading"
          @search-change="handleFilter"
          @refresh-change="handleRefreshChange"
          @row-update="update"
          @row-save="create"
          :before-open="handleOpenBefore"
          :data="list"
      >
        <template #menu-left="{}">
          <el-button
              class="filter-item"
              @click="$refs.crud.rowAdd()"
              type="primary"
              icon="el-icon-edit"
          >添加
          </el-button>
          <el-button
              class="filter-item"
              plain
              type="primary"
              icon="el-icon-upload"
              @click="$refs.excelUpload.show()"
          >导入
          </el-button>
          <el-button
              class="filter-item"
              plain
              type="primary"
              icon="el-icon-download"
              @click="exportExcel"
          >导出
          </el-button>
        </template>
        <template #username="scope">
          <span>{{ scope.row.username }}</span>
        </template>
        <template #role="scope">
          <span v-for="(role, index) in scope.row.roleList" :key="index">
            <el-tag>{{ role.roleName }} </el-tag>&nbsp;&nbsp;
          </span>
        </template>
        <template #post="scope">
          <span v-for="(role, index) in scope.row.postList" :key="index">
            <el-tag>{{ role.postName }} </el-tag>&nbsp;&nbsp;
          </span>
        </template>
        <template #deptName="scope">
          {{ scope.row.deptName }}
        </template>
        <template #lockFlag="scope">
          <dict-tag :options="scope.dic" :value="scope.row.lockFlag"/>
        </template>
        <template #menu="scope">
          <el-button
              text
              v-if="permissions.sys_user_edit"
              type="primary"
              icon="el-icon-edit"
              @click="handleUpdate(scope.row, scope.index)"
          >编辑
          </el-button>
          <el-button
              text
              v-if="permissions.sys_user_del"
              type="primary"
              icon="el-icon-delete"
              @click="deletes(scope.row, scope.index)"
          >删除
          </el-button>
        </template>
        <template #deptId-form="scope">
          <avue-input-tree
              v-model="form.deptId"
              placeholder="请选择所属部门"
              :node-click="getNodeData"
              :dic="treeDeptData"
              :props="defaultProps"
          ></avue-input-tree>
        </template>
        <template #role-form="scope">
          <avue-select
              v-model="role"
              multiple
              placeholder="请选择角色"
              :dic="rolesOptions"
              :props="roleProps"
          ></avue-select>
        </template>
        <template #post-form="scope">
          <avue-select
              v-model="post"
              multiple
              placeholder="请选择岗位"
              :dic="postOptions"
              :props="postProps"
          ></avue-select>
        </template>
      </avue-crud>

      <!--excel 模板导入 -->
      <excel-upload
          ref="excelUpload"
          title="用户信息导入"
          url="/admin/user/import"
          temp-name="用户信息.xlsx"
          temp-url="/admin/sys-file/local/user.xlsx"
          @refreshDataList="handleRefreshChange"
      ></excel-upload>
    </basic-container>
  </div>
</template>

<script>
import {addObj, delObj, fetchList, putObj} from "@/api/admin/user";
import {deptRoleList} from "@/api/admin/role";
import {listPosts} from "@/api/admin/post";
import {fetchTree} from "@/api/admin/dept";
import {tableOption} from "@/const/crud/admin/user";
import {mapGetters} from "vuex";
import ExcelUpload from "@/components/ExcelUpload/index.vue";

export default {
  name: "table_user",
  components: {ExcelUpload},
  data() {
    return {
      option: tableOption,
      treeDeptData: [],
      checkedKeys: [],
      postProps: {
        label: "postName",
        value: "postId"
      },
      roleProps: {
        label: "roleName",
        value: "roleId"
      },
      defaultProps: {
        label: "name",
        value: "id"
      },
      page: {
        total: 0, // 总页数
        currentPage: 1, // 当前页数
        pageSize: 20, // 每页显示多少条,
        isAsc: false //是否倒序
      },
      query: {},
      list: [],
      listLoading: true,
      post: [],
      role: [],
      form: {},
      postOptions: [],
      rolesOptions: []
    };
  },
  computed: {
    ...mapGetters(["permissions"])
  },
  watch: {
    role() {
      this.form.role = this.role;
    },
    post() {
      this.form.post = this.post;
    }
  },
  methods: {
    getList(page, params) {
      this.listLoading = true;
      fetchList(
          Object.assign(
              {
                current: page.currentPage,
                size: page.pageSize
              },
              params
          )
      ).then(response => {
        this.list = response.data.data.records;
        console.table(this.list)
        this.page.total = response.data.data.total;
        this.listLoading = false;
      });
    },
    getNodeData(data) {
      deptRoleList().then(response => {
        this.rolesOptions = response.data.data;
      });
      listPosts().then(response => {
        this.postOptions = response.data.data
      })
    },
    sizeChange(pageSize) {
      this.page.pageSize = pageSize;
    },
    currentChange(current) {
      this.page.currentPage = current;
    },
    handleFilter(param, done) {
      this.query = param;
      this.page.currentPage = 1;
      this.getList(this.page, param);
      done();
    },
    handleRefreshChange() {
      this.getList(this.page);
    },
    handleOpenBefore(show, type) {
      window.boxType = type;
      // 查询部门树
      fetchTree().then(response => {
        this.treeDeptData = response.data.data;
      });
      // 查询角色列表
      deptRoleList().then(response => {
        this.rolesOptions = response.data.data;
      });
      //查询岗位列表
      listPosts().then(response => {
        this.postOptions = response.data.data
      })
      // 若是编辑、查看回显角色名称
      if (["edit", "views"].includes(type)) {
        this.role = [];
        for (let i = 0; i < this.form.roleList.length; i++) {
          this.role[i] = this.form.roleList[i].roleId;
        }
        this.post = []
        for (let i = 0; i < this.form.postList.length; i++) {
          this.post[i] = this.form.postList[i].postId;
        }
      } else if (type === "add") {
        // 若是添加角色列表设置为空
        this.role = [];
        this.post = [];
      }
      show();
    },
    handleUpdate(row, index) {
      this.$refs.crud.rowEdit(row, index);
      this.form.password = undefined;
    },
    create(row, done, loading) {
      addObj(this.form)
          .then(() => {
            this.getList(this.page);
            done();
            this.$notify.success("创建成功");
          })
          .catch(() => {
            loading();
          });
    },
    update(row, index, done, loading) {
      console.log('this.form', this.form)
      putObj(this.form)
          .then(() => {
            this.getList(this.page);
            done();
            this.$notify.success("修改成功");
          })
          .catch(() => {
            loading();
          });
    },
    deletes(row) {
      this.$confirm(
          "此操作将永久删除该用户(用户名:" + row.username + "), 是否继续?",
          "提示",
          {
            confirmButtonText: "确定",
            cancelButtonText: "取消",
            type: "warning"
          }
      ).then(() => {
        delObj(row.userId)
            .then(() => {
              this.getList(this.page);
              this.$notify.success("删除成功");
            })
            .catch(() => {
              this.$notify.error("删除失败");
            });
      });
    },
    exportExcel() {
      this.downBlobFile("/admin/user/export", this.query, "user.xlsx");
    }
  }
};
</script>
