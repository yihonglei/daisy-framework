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
          @on-load="getList"
          @row-update="handleUpdate"
          @row-save="handleSave"
          @search-change="searchChange"
          @size-change="sizeChange"
          @current-change="currentChange"
          @row-del="rowDel">
        <template slot="menuLeft">
          <el-button
              v-if="permissions.sys_dict_del"
              class="filter-item"
              type="primary"
              icon="el-icon-refresh-left"
              @click="handleClearDictCache"
          >缓存
          </el-button>
        </template>
        <template #menu="scope">
          <el-button
              text
              v-if="permissions.sys_dict_add"
              type="primary"
              icon="el-icon-menu"
              @click="handleItem(scope.row,scope.index)">字典项
          </el-button>
        </template>
      </avue-crud>
    </basic-container>
    <el-dialog
        v-model="dialogFormVisible"
        title="字典项管理"
        width="90%"
        @close="dictItemVisible">
      <avue-crud
          ref="crudItem"
          v-model:page="itemPage"
          :data="tableDictItemData"
          :permission="permissionList"
          v-model="form"
          :before-open="handleBeforeOpen"
          :option="tableDictItemOption"
          @size-change="itemSizeChange"
          @current-change="itemCurrentChange"
          @row-update="handleItemUpdate"
          @row-save="handleItemSave"
          @row-del="rowItemDel">
        <template #type="scope">
          <dict-tag :options="scope.dic" :value="scope.row.type"/>
        </template>
      </avue-crud>
    </el-dialog>
  </div>
</template>

<script>
import {
  addItemObj,
  addObj,
  clearDictCache,
  delItemObj,
  delObj,
  fetchItemList,
  fetchList,
  putItemObj,
  putObj
} from '@/api/admin/dict'
import {tableDictItemOption, tableOption} from '@/const/crud/admin/dict'
import {mapGetters} from 'vuex'

export default {
  name: 'Dict',
  data() {
    return {
      searchForm: {},
      form: {
        dictKey: undefined,
        dictId: undefined
      },
      dictKey: undefined,
      dictId: undefined,
      dialogFormVisible: false,
      tableData: [],
      tableDictItemData: [],
      page: {
        total: 0, // 总页数
        currentPage: 1, // 当前页数
        pageSize: 20 // 每页显示多少条
      },
      itemPage: {
        total: 0, // 总页数
        currentPage: 1, // 当前页数
        pageSize: 20 // 每页显示多少条
      },
      tableLoading: false,
      tableOption: tableOption,
      tableDictItemOption: tableDictItemOption
    }
  },
  computed: {
    ...mapGetters(['permissions']),
    permissionList() {
      return {
        addBtn: this.validData(this.permissions.sys_dict_add, false),
        delBtn: this.validData(this.permissions.sys_dict_del, false),
        editBtn: this.validData(this.permissions.sys_dict_edit, false)
      }
    }
  },
  methods: {
    //======字典表格相关=====
    getList(page, params) {
      this.tableLoading = true
      fetchList(Object.assign({
        current: page.currentPage,
        size: page.pageSize
      }, params, this.searchForm)).then(response => {
        this.tableData = response.data.data.records
        this.page.total = response.data.data.total
        this.tableLoading = false
      })
    },
    rowDel: function (row) {
      this.$confirm('是否确认删除数据类型为"' + row.type + '"的数据项?', '警告', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(function () {
        return delObj(row)
      }).then(() => {
        this.getList(this.page)
        this.$message.success('删除成功')
      }).catch(function () {
      })
    },
    handleUpdate: function (row, index, done) {
      putObj(row).then(() => {
        this.$message.success('修改成功')
        this.getList(this.page)
        done()
      })
    },
    handleSave: function (row, done) {
      addObj(row).then(() => {
        this.$message.success('添加成功')
        this.getList(this.page)
        done()
      })
    },
    searchChange(form, done) {
      this.searchForm = form
      this.page.currentPage = 1
      this.getList(this.page, form)
      done()
    },
    sizeChange(pageSize) {
      this.page.pageSize = pageSize
    },
    currentChange(current) {
      this.page.currentPage = current
    },
    //======字典项表格相关=====
    dictItemVisible: function () {
      this.dialogFormVisible = false
      this.itemPage.currentPage = 1
    },
    handleItem: function (row) {
      this.dictId = row.id
      this.dictKey = row.dictKey
      this.getDictItemList()
    },
    getDictItemList() {
      this.dialogFormVisible = true
      fetchItemList(Object.assign({
        current: this.itemPage.currentPage,
        size: this.itemPage.pageSize
      }, {dictId: this.dictId})).then(response => {
        this.tableDictItemData = response.data.data.records
        this.itemPage.total = response.data.data.total
      })
    },
    handleBeforeOpen(done) {
      this.form.dictKey = this.dictKey
      this.form.dictId = this.dictId
      done()
    },
    handleItemSave: function (row, done) {
      addItemObj(row).then(() => {
        this.$message.success('添加成功')
        this.getDictItemList()
        done()
      })
    },
    handleItemUpdate: function (row, index, done) {
      putItemObj(row).then(() => {
        this.$message.success('修改成功')
        this.getDictItemList()
        done()
      })
    },
    itemSizeChange(pageSize) {
      this.itemPage.pageSize = pageSize
      this.getDictItemList()
    },
    itemCurrentChange(current) {
      this.itemPage.currentPage = current
      this.getDictItemList()
    },
    rowItemDel: function (row) {
      this.$confirm('是否确认删除数据为"' + row.label + '"的数据项?', '警告', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(function () {
        return delItemObj(row.id)
      }).then(() => {
        this.getDictItemList()
        this.$message.success('删除成功')
      }).catch(function () {
      })
    },
    handleClearDictCache: function () {
      clearDictCache().then(() => {
        this.$message.success('清除缓存成功')
      }).catch(function () {
      })
    }
  }
}
</script>

<style lang="scss" scoped>
</style>

