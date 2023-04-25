# Dump of table sys_user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `username` varchar(20) NOT NULL COMMENT '用户名',
    `password` varchar(200) NOT NULL COMMENT '密码',
    `salt` varchar(10) DEFAULT NULL COMMENT '盐',
    `token` varchar(100) DEFAULT NULL,
    `phone` varchar(20) DEFAULT NULL,
    `name` varchar(64) DEFAULT NULL COMMENT '姓名',
    `avatar` varchar(150) DEFAULT NULL,
    `attcode` varchar(50) DEFAULT NULL,
    `status` int(1) DEFAULT '1' COMMENT '状态 1 正常有效期内 2 过期',
    `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `created_by` int(11) DEFAULT NULL COMMENT '创建者',
    `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `updated_by` int(11) DEFAULT NULL COMMENT '修改人',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户账户';

LOCK TABLES `sys_user` WRITE;

INSERT INTO `sys_user` (`id`, `username`, `password`, `salt`, `token`, `phone`, `name`, `avatar`, `attcode`, `status`, `created_at`, `created_by`, `updated_at`, `updated_by`)
VALUES
    (1,'admin','admin123','123456','admin-token','17600337316','超级管理员','https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif','DHSUFHDS',1,'2022-07-29 09:32:07',1,'2022-09-30 15:03:37',1),
    (2,'test','testtest','123456','test-token','17600337316','test科技','https://bzrrmedia.oss-cn-beijing.aliyuncs.com/mhjy/header1665294018860_583c7dbb.jpg','NbPSQcXC',1,'2022-10-09 13:40:11',1,'2022-10-17 17:37:22',7),
    (3,'test1','test1123','123456','test1-token','17600337316','test','','CCtKj4YE',1,'2023-04-23 17:52:18',1,'2023-04-23 17:52:18',1);

UNLOCK TABLES;

# Dump of table sys_role
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `name` varchar(200) DEFAULT NULL COMMENT '角色名称',
    `intro` varchar(100) DEFAULT NULL COMMENT '角色别名',
    `sort` int(11) DEFAULT '0' COMMENT '排序',
    `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `created_by` int(11) DEFAULT NULL COMMENT '创建者',
    `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `updated_by` int(11) DEFAULT NULL COMMENT '修改人',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

LOCK TABLES `sys_role` WRITE;

INSERT INTO `sys_role` (`id`, `name`, `intro`, `sort`, `created_at`, `created_by`, `updated_at`, `updated_by`)
VALUES
    (1,'admin','admin',1,'2022-07-29 11:01:49',1,'2022-07-29 11:02:23',1),
    (2,'company','company',2,'2022-07-29 11:02:22',1,'2022-08-15 10:36:42',1),
    (27,'test','test',3,'2022-10-09 13:39:22',1,'2022-10-09 13:39:34',1);

UNLOCK TABLES;

# Dump of table sys_menu
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_menu`;

CREATE TABLE `sys_menu` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `parent_id` int(11) DEFAULT NULL COMMENT '上级菜单',
    `path` varchar(100) DEFAULT NULL COMMENT '路由地址',
    `component` varchar(100) DEFAULT NULL COMMENT '视图',
    `hidden` int(1) DEFAULT '0' COMMENT '当设置 true 的时候该路由不会在侧边栏出现 如401，login等页面，或者如一些编辑页面/edit/1',
    `redirect` varchar(200) DEFAULT NULL COMMENT '重定向:当设置 noRedirect 的时候该路由在面包屑导航中不可被点击',
    `alwaysShow` int(11) DEFAULT '0' COMMENT '你可以设置 alwaysShow: true，这样它就会忽略之前定义的规则，一直显示根路由',
    `name` varchar(100) DEFAULT NULL COMMENT '必填！设定路由的名字，一定要填写不然使用<keep-alive>时会出现各种问题',
    `title` varchar(200) DEFAULT NULL COMMENT '显示名称',
    `icon` varchar(100) DEFAULT NULL COMMENT '设置该路由的图标，支持 svg-class，也支持 el-icon-x element-ui 的 icon',
    `noCache` int(1) DEFAULT NULL COMMENT '如果设置为true，则不会被 <keep-alive> 缓存(默认 false)',
    `breadcrumb` int(1) DEFAULT '1' COMMENT '如果设置为false，则不会在breadcrumb面包屑中显示(默认 true)',
    `affix` int(11) DEFAULT '0',
    `activeMenu` varchar(50) DEFAULT NULL COMMENT '当路由设置了该属性，则会高亮相对应的侧边栏。',
    `enable` int(1) DEFAULT '1',
    `sort` int(11) DEFAULT '0',
    `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `created_by` int(11) DEFAULT NULL COMMENT '创建者',
    `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `updated_by` int(11) DEFAULT NULL COMMENT '修改人',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单表';

LOCK TABLES `sys_menu` WRITE;

INSERT INTO `sys_menu` (`id`, `parent_id`, `path`, `component`, `hidden`, `redirect`, `alwaysShow`, `name`, `title`, `icon`, `noCache`, `breadcrumb`, `affix`, `activeMenu`, `enable`, `sort`, `created_at`, `created_by`, `updated_at`, `updated_by`)
VALUES
    (8,0,'/permission','layout',0,'',0,'权限中心','权限中心','lock',0,0,0,'',0,7,'2022-07-29 14:29:32',1,'2022-08-15 11:33:00',1),
    (9,8,'page','/permission/page',0,NULL,0,'菜单管理','菜单管理',NULL,1,0,0,'',1,1,'2022-07-29 14:33:02',1,'2022-08-15 00:00:19',1),
    (10,8,'directive','/permission/directive',0,NULL,0,'企业管理','企业管理',NULL,1,0,0,'',0,2,'2022-07-29 14:36:23',1,'2022-09-05 11:34:40',1),
    (11,8,'role','/permission/role',0,NULL,0,'角色管理','角色管理',NULL,1,0,0,'',1,3,'2022-07-29 14:38:55',1,'2022-08-15 00:00:20',1),
    (12,0,'/','layout',0,'/datacenter',0,'数据中心-','数据中心','',0,0,0,'',1,1,'2022-08-02 14:59:54',1,'2022-08-15 11:37:28',1),
    (13,12,'/','/datacenter/index',0,'',0,'数据中心','数据中心','el-icon-s-data',1,0,1,'',0,1,'2022-08-02 14:59:54',1,'2022-08-15 11:33:36',1),
    (14,0,'/usercenter/page','layout',0,'',0,'用户中心-','用户中心','',0,0,0,'',0,4,'2022-08-04 11:20:58',1,'2022-08-15 11:37:32',1),
    (15,14,'/usercenter','/usercenter/page',0,NULL,0,'用户中心','用户中心','el-icon-user-solid',1,0,0,'',0,1,'2022-08-04 11:21:49',1,'2022-08-15 00:00:24',1),
    (28,0,'/messagecenter/index','layout',0,'',0,'消息中心-','消息中心','el-icon-chat-dot-square',0,0,0,'',0,5,'2022-08-11 17:53:52',1,'2022-08-15 17:58:26',1),
    (29,28,'/messagecenter','/messagecenter/index',0,'',0,'消息中心','消息中心','el-icon-chat-dot-square',0,1,0,'',0,1,'2022-08-11 17:57:14',1,'2022-08-11 17:58:04',1),
    (30,0,'/financecenter/page','layout',0,'',0,'财务中心-','财务中心','el-icon-s-cooperation',0,1,0,'',0,6,'2022-08-13 15:26:22',1,'2022-08-15 17:58:30',1),
    (31,30,'/financecenter','/financecenter/page',0,'',0,'财务中心','财务中心','el-icon-s-cooperation',0,1,0,'',0,1,'2022-08-13 15:29:05',1,'2022-08-13 15:31:26',1),
    (32,0,'/homecenter/page','layout',0,'',0,'首页管理-','首页管理','el-icon-s-home',0,1,0,'',0,2,'2022-08-13 17:52:51',1,'2022-08-15 17:58:43',1),
    (33,32,'/homecenter','/homecenter/page',0,'',0,'首页管理','首页管理','el-icon-s-home',0,1,0,'',0,1,'2022-08-13 17:53:28',1,'2022-08-13 17:53:28',1),
    (34,0,'/papercenter/page','layout',0,'',0,'纸条管理-','纸条管理','el-icon-position',0,1,0,'',0,3,'2022-08-14 23:59:30',1,'2022-08-15 17:58:49',1),
    (35,34,'/papercenter','/papercenter/page',0,'',0,'纸条管理','纸条管理','el-icon-position',0,1,0,'',0,1,'2022-08-15 00:00:59',1,'2022-08-15 00:00:59',1),
    (36,0,'/systemset/page','layout',0,'',0,'系统设置-','系统设置','el-icon-setting',0,1,0,'',0,9,'2022-08-23 14:23:49',1,'2022-08-23 14:37:18',1),
    (37,36,'/systemset','/systemset/page',0,'',0,'系统设置','系统设置','el-icon-setting',0,1,0,'',0,1,'2022-08-23 14:36:49',1,'2022-08-23 14:36:49',1),
    (38,0,'/fundcenter/page','layout',0,'',0,'资产中心','资产中心','el-icon-office-building',0,1,0,'',0,6,'2022-09-05 15:04:49',1,'2022-09-05 15:05:18',1),
    (39,38,'/fundcenter','/fundcenter/page',0,'',0,'资产中心','资产中心','el-icon-office-building',0,1,0,'',0,1,'2022-09-05 15:06:18',1,'2022-09-05 15:06:18',1);

UNLOCK TABLES;

# Dump of table sys_user_role
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
    `id` int(11) DEFAULT NULL,
    `user_id` int(11) DEFAULT NULL,
    `role_id` int(11) DEFAULT NULL,
    `created_at` datetime DEFAULT NULL,
    `created_by` int(11) DEFAULT NULL,
    `updated_at` datetime DEFAULT NULL,
    `updated_by` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色表';

LOCK TABLES `sys_user_role` WRITE;

INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`, `created_at`, `created_by`, `updated_at`, `updated_by`)
VALUES
    (1,1,1,'2022-08-08 17:42:59',1,'2022-08-08 17:42:59',1),
    (2,1,2,'2022-10-09 13:40:21',1,'2022-10-09 13:40:21',1),
    (3,3,2,'2023-04-23 17:52:18',1,'2023-04-23 17:52:18',1),
    (4,3,27,'2023-04-23 17:52:18',1,'2023-04-23 17:52:18',1);

UNLOCK TABLES;

# Dump of table sys_role_menu
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_role_menu`;

CREATE TABLE `sys_role_menu` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `role_id` int(11) DEFAULT NULL COMMENT '角色ID',
    `menu_id` int(11) DEFAULT NULL COMMENT '菜单ID',
    `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `created_by` int(11) DEFAULT NULL COMMENT '创建者',
    `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `updated_by` int(11) DEFAULT NULL COMMENT '修改人',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色菜单表';

LOCK TABLES `sys_role_menu` WRITE;

INSERT INTO `sys_role_menu` (`id`, `role_id`, `menu_id`, `created_at`, `created_by`, `updated_at`, `updated_by`)
VALUES
    (153,2,12,'2022-08-15 10:36:42',1,'2022-08-15 10:36:42',1),
    (154,2,13,'2022-08-15 10:36:42',1,'2022-08-15 10:36:42',1),
    (155,2,34,'2022-08-15 10:36:42',1,'2022-08-15 10:36:42',1),
    (156,2,35,'2022-08-15 10:36:42',1,'2022-08-15 10:36:42',1),
    (157,2,14,'2022-08-15 10:36:42',1,'2022-08-15 10:36:42',1),
    (158,2,15,'2022-08-15 10:36:42',1,'2022-08-15 10:36:42',1),
    (175,27,12,'2022-10-09 13:39:34',1,'2022-10-09 13:39:34',1),
    (176,27,13,'2022-10-09 13:39:34',1,'2022-10-09 13:39:34',1),
    (177,27,32,'2022-10-09 13:39:34',1,'2022-10-09 13:39:34',1),
    (178,27,33,'2022-10-09 13:39:34',1,'2022-10-09 13:39:34',1),
    (179,27,34,'2022-10-09 13:39:34',1,'2022-10-09 13:39:34',1),
    (180,27,35,'2022-10-09 13:39:34',1,'2022-10-09 13:39:34',1),
    (181,27,14,'2022-10-09 13:39:34',1,'2022-10-09 13:39:34',1),
    (182,27,15,'2022-10-09 13:39:34',1,'2022-10-09 13:39:34',1),
    (183,27,28,'2022-10-09 13:39:34',1,'2022-10-09 13:39:34',1),
    (184,27,29,'2022-10-09 13:39:34',1,'2022-10-09 13:39:34',1),
    (185,27,30,'2022-10-09 13:39:34',1,'2022-10-09 13:39:34',1),
    (186,27,31,'2022-10-09 13:39:34',1,'2022-10-09 13:39:34',1),
    (187,27,38,'2022-10-09 13:39:34',1,'2022-10-09 13:39:34',1),
    (188,27,39,'2022-10-09 13:39:34',1,'2022-10-09 13:39:34',1),
    (189,27,36,'2022-10-09 13:39:34',1,'2022-10-09 13:39:34',1),
    (190,27,37,'2022-10-09 13:39:34',1,'2022-10-09 13:39:34',1);

UNLOCK TABLES;