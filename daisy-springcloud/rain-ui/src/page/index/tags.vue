<template>
  <div class="avue-tags"
       v-if="setting.tag"
       @click="contextmenuFlag=false">
    <!-- tag盒子 -->
    <div v-if="contextmenuFlag"
         class="avue-tags__contentmenu"
         :style="{left:contentmenuX+'px',top:contentmenuY+'px'}">
      <div class="item"
           @click="closeOthersTags">{{ $t('tagsView.closeOthers') }}
      </div>
      <div class="item"
           @click="closeAllTags">{{ $t('tagsView.closeAll') }}
      </div>
    </div>
    <div class="avue-tags__box"
         :class="{'avue-tags__box--close':!website.isFirstPage}">
      <el-tabs v-model="active"
               type="card"
               @contextmenu="handleContextmenu"
               :closable="tagLen!==1"
               @tab-click="openTag"
               @edit="menuTag">
        <el-tab-pane :key="item.value"
                     v-for="item in tagList"
                     :label="generateTitle(item)"
                     :name="item.value">
          <template #label>
            <span>
              {{ generateTitle(item) }}
              <i class="el-icon-refresh"
                 :class="{'turn':refresh}"
                 @click="handleRefresh"
                 v-if="active==item.value"></i>
            </span>
          </template>

        </el-tab-pane>

      </el-tabs>
      <el-dropdown class="avue-tags__menu">
        <el-button type="primary">
          {{ $t('tagsView.menu') }}
          <i class="el-icon-arrow-down el-icon--right"></i>
        </el-button>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item @click="openSearch">{{ $t('tagsView.search') }}</el-dropdown-item>
            <el-dropdown-item @click="closeOthersTags">{{ $t('tagsView.closeOthers') }}</el-dropdown-item>
            <el-dropdown-item @click="closeAllTags">{{ $t('tagsView.closeAll') }}</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </div>

  </div>
</template>
<script>
import {mapGetters} from "vuex";

export default {
  name: "tags",
  data() {
    return {
      refresh: false,
      active: "",
      contentmenuX: "",
      contentmenuY: "",
      contextmenuFlag: false
    };
  },
  watch: {
    tag: {
      handler(val) {
        this.active = val.value;
      },
      immediate: true,
    },
    contextmenuFlag() {
      window.addEventListener("mousedown", this.watchContextmenu);
    }
  },
  computed: {
    ...mapGetters(["tagWel", "tagList", "tag", "setting"]),
    tagLen() {
      return this.tagList.length || 0;
    }
  },
  methods: {
    openSearch() {
      this.$store.commit('SET_IS_SEARCH', true)
    },
    handleRefresh() {
      this.refresh = true;
      this.$store.commit('SET_IS_REFRESH', false);
      setTimeout(() => {
        this.$store.commit('SET_IS_REFRESH', true);
      }, 100)
      setTimeout(() => {
        this.refresh = false;
      }, 500)
    },
    generateTitle(item) {
      return this.$router.$avueRouter.generateTitle(item.label, item.meta.i18n);
    },
    watchContextmenu(event) {
      if (!this.$el.contains(event.target) || event.button !== 0) {
        this.contextmenuFlag = false;
      }
      window.removeEventListener("mousedown", this.watchContextmenu);
    },
    handleContextmenu(event) {
      let target = event.target;
      let flag = false;
      if (target.className.indexOf("el-tabs__item") > -1) flag = true;
      else if (target.parentNode.className.indexOf("el-tabs__item") > -1) {
        target = target.parentNode;
        flag = true;
      }
      if (flag) {
        event.preventDefault();
        event.stopPropagation();
        this.contentmenuX = event.clientX;
        this.contentmenuY = event.clientY;
        this.tagName = target.getAttribute("aria-controls").slice(5);
        this.contextmenuFlag = true;
      }
    },
    menuTag(value, action) {
      if (action === "remove") {
        let openTag; // 最终要打开的页面
        let {tag, key} = this.findTag(value);
        if (tag.value === this.tag.value) {
          openTag = this.tagList[key === 0 ? key : key - 1]; //如果关闭本标签让前推一个
        } else {
          openTag = this.tag;
          this.openTag(tag);
        }
        this.$store.commit("DEL_TAG", tag);
        this.openTag(openTag);
      }
    },
    openTag(item) {
      if (item.props) item = item.props
      let tag;
      if (item.name) {
        tag = this.findTag(item.name).tag;
      } else {
        tag = item;
      }
      this.$router.push({
        path: tag.value,
        query: tag.query
      });
    },
    findTag(value) {
      let tag, key;
      this.tagList.map((item, index) => {
        if (item.value === value) {
          tag = item;
          key = index;
        }
      });
      return {tag: tag, key: key};
    },
    // 因需清除每个keep-alive页面的缓存，需一个一个的激活tag到前台做删除
    activeTag(tagList) {
      tagList.forEach(item => {
        this.openTag(item);
        this.$store.commit("DEL_TAG", item);
      });
    },
    closeOthersTags() {
      this.contextmenuFlag = false;
      let openTag = this.tag;
      let tagList = this.tagList.filter(item =>
          item.value !== this.tag.value && !this.website.isFirstPage && item.value !== this.tagWel.value
      );
      this.activeTag(tagList)
      this.openTag(openTag);
    },
    closeAllTags() {
      this.contextmenuFlag = false;
      this.activeTag(this.tagList)
      this.$router.push({
        path: this.tagWel.value,
        query: this.tagWel.query
      });
    }
  }
};
</script>


