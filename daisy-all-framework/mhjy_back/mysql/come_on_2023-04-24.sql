# ************************************************************
# Sequel Pro SQL dump
# Version 5446
#
# https://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 112.124.36.107 (MySQL 5.7.33-0ubuntu0.16.04.1)
# Database: come_on
# Generation Time: 2023-04-24 07:58:57 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
SET NAMES utf8mb4;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table co_cash_out
# ------------------------------------------------------------

DROP TABLE IF EXISTS `co_cash_out`;

CREATE TABLE `co_cash_out` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL,
  `cid` int(11) DEFAULT NULL,
  `cashmoney` float DEFAULT NULL COMMENT '提现金额',
  `fee` int(11) DEFAULT NULL COMMENT '费率 1-100',
  `outmoney` float DEFAULT NULL COMMENT '到账金额',
  `alipaynum` varchar(50) DEFAULT NULL,
  `realname` varchar(100) DEFAULT NULL,
  `status` int(2) DEFAULT NULL COMMENT '状态：1审核中 2审核通过 3 审核拒绝 4 已拨款',
  `reason` varchar(200) DEFAULT NULL COMMENT '审核原因 ',
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table co_cash_out_fee
# ------------------------------------------------------------

DROP TABLE IF EXISTS `co_cash_out_fee`;

CREATE TABLE `co_cash_out_fee` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL COMMENT '1普通用户  2 系统管理员',
  `fee` varchar(5) DEFAULT NULL COMMENT '提现手续费',
  `username` varchar(11) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table co_fund
# ------------------------------------------------------------

DROP TABLE IF EXISTS `co_fund`;

CREATE TABLE `co_fund` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL,
  `cid` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL COMMENT '1 A纸条被抽返给用户A  2 A企业成员账号被抽返给A企业管理员 3 邀请用户被抽返给被邀请人  4 提现已拨款 5 充VIP返给师傅 6 充VIP返给所属企业',
  `fund` double DEFAULT NULL,
  `alongid` int(11) DEFAULT NULL COMMENT 'type 123 paperid ，4 cashoutid 56 tradeid',
  `serialno` varchar(11) DEFAULT NULL COMMENT '序列号，同一序列号代表同一奖励体系',
  `direction` int(11) DEFAULT NULL COMMENT '1 收入  2 支出【提现】',
  `desc` varchar(100) DEFAULT NULL COMMENT '说明',
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table co_goods
# ------------------------------------------------------------

DROP TABLE IF EXISTS `co_goods`;

CREATE TABLE `co_goods` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `good_name` varchar(32) DEFAULT NULL,
  `buy_time` int(10) unsigned NOT NULL,
  `sale_price` decimal(10,2) DEFAULT NULL,
  `type` tinyint(4) NOT NULL DEFAULT '1' COMMENT '1 VIP商品  2 续费商品',
  `sort` int(2) DEFAULT NULL,
  `enable` int(1) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `co_goods` WRITE;
/*!40000 ALTER TABLE `co_goods` DISABLE KEYS */;

INSERT INTO `co_goods` (`id`, `good_name`, `buy_time`, `sale_price`, `type`, `sort`, `enable`, `created_at`, `updated_at`)
VALUES
	(1,'1月体验版',1,100.00,1,1,1,'2022-07-21 08:00:00','2022-07-21 08:00:00'),
	(2,'1季特惠版',3,200.00,1,2,1,'2022-07-21 08:00:00','2022-07-21 08:00:00'),
	(3,'半年专享版',6,300.00,1,3,1,'2022-07-21 08:00:00','2022-07-21 08:00:00'),
	(4,'1年实惠版',12,400.00,1,4,1,'2022-07-21 08:00:00','2022-07-21 08:00:00'),
	(5,'半年体验版',6,10.00,2,1,1,'2022-07-21 08:00:00','2022-07-21 08:00:00'),
	(6,'一年特惠版',12,18.00,2,2,1,'2022-07-21 08:00:00','2022-07-21 08:00:00'),
	(7,'两年专享版',24,28.00,2,3,1,'2022-07-21 08:00:00','2022-07-21 08:00:00'),
	(8,'三年实惠版',36,38.00,2,4,1,'2022-07-21 08:00:00','2022-07-21 08:00:00');

/*!40000 ALTER TABLE `co_goods` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table co_image
# ------------------------------------------------------------

DROP TABLE IF EXISTS `co_image`;

CREATE TABLE `co_image` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `place` int(11) DEFAULT NULL COMMENT '1 首页',
  `title` varchar(500) DEFAULT NULL,
  `src` varchar(500) DEFAULT NULL,
  `sort` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL COMMENT '1 无跳转  2 链接',
  `link` varchar(100) DEFAULT NULL,
  `enable` int(1) DEFAULT '1',
  `start_at` datetime DEFAULT NULL COMMENT '生效开始时间',
  `end_at` datetime DEFAULT NULL COMMENT '生效结束时间',
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `co_image` WRITE;
/*!40000 ALTER TABLE `co_image` DISABLE KEYS */;

INSERT INTO `co_image` (`id`, `place`, `title`, `src`, `sort`, `type`, `link`, `enable`, `start_at`, `end_at`, `created_at`, `updated_at`)
VALUES
	(1,1,'001','https://bzrrmedia.oss-cn-beijing.aliyuncs.com/mhjy/banner1665286597964_7586d6db.jpg',1,1,'',1,'2022-07-22 10:22:22','2025-07-22 10:22:22','2022-07-22 10:22:22','2022-10-09 11:36:40'),
	(2,1,'002','https://bzrrmedia.oss-cn-beijing.aliyuncs.com/mhjy/banner1665286612070_f76a74a4.jpg',2,1,'',1,'2022-07-22 10:22:22','2025-07-22 10:22:22','2022-07-22 10:22:22','2022-10-09 11:36:54');

/*!40000 ALTER TABLE `co_image` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table co_menu
# ------------------------------------------------------------

DROP TABLE IF EXISTS `co_menu`;

CREATE TABLE `co_menu` (
  `menuid` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `menuname` varchar(200) DEFAULT NULL,
  `menufatherid` int(11) DEFAULT NULL,
  `menupath` varchar(100) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`menuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table co_paper
# ------------------------------------------------------------

DROP TABLE IF EXISTS `co_paper`;

CREATE TABLE `co_paper` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL,
  `cid` int(11) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL COMMENT '1 男 2 女',
  `portraits` varchar(200) DEFAULT NULL COMMENT '形象照',
  `vxnum` varchar(50) DEFAULT NULL,
  `money` int(11) DEFAULT NULL COMMENT '价值 元',
  `intro` varchar(200) DEFAULT NULL,
  `status` int(2) DEFAULT NULL COMMENT ' 0初始状态 1正常  其他不可用 可能被举报',
  `chosen` int(1) DEFAULT NULL COMMENT '是否精选',
  `del` int(1) DEFAULT '0',
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `co_paper` WRITE;
/*!40000 ALTER TABLE `co_paper` DISABLE KEYS */;

INSERT INTO `co_paper` (`id`, `uid`, `cid`, `sex`, `portraits`, `vxnum`, `money`, `intro`, `status`, `chosen`, `del`, `created_at`, `updated_at`)
VALUES
	(26,1,1,1,'https://bzrrmedia.oss-cn-beijing.aliyuncs.com/jubaopen/read1666075266370_354bc279.jpg','Self_Motivation',1,'朋友相交，贵在交心',1,1,0,'2022-10-17 17:46:34','2022-10-18 14:19:12'),
	(35,1,1,2,'https://bzrrmedia.oss-cn-beijing.aliyuncs.com/jubaopen/read1666075302188_25a15f83.jpg','c222x555y',1,'遇一知己，此生无憾',1,1,0,'2022-10-18 14:18:12','2022-10-18 14:19:12');

/*!40000 ALTER TABLE `co_paper` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table co_paper_history
# ------------------------------------------------------------

DROP TABLE IF EXISTS `co_paper_history`;

CREATE TABLE `co_paper_history` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL,
  `paperid` int(11) DEFAULT NULL,
  `cid` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL COMMENT '1 放入  2 取出',
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `co_paper_history` WRITE;
/*!40000 ALTER TABLE `co_paper_history` DISABLE KEYS */;

INSERT INTO `co_paper_history` (`id`, `uid`, `paperid`, `cid`, `type`, `created_at`, `updated_at`)
VALUES
	(45,1,26,NULL,1,'2022-10-17 17:46:34','2022-10-17 17:46:34'),
	(46,99,35,NULL,2,'2022-10-20 17:20:06','2022-10-20 17:20:06');

/*!40000 ALTER TABLE `co_paper_history` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table co_suggest
# ------------------------------------------------------------

DROP TABLE IF EXISTS `co_suggest`;

CREATE TABLE `co_suggest` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `suggest_uid` int(11) DEFAULT NULL,
  `content` varchar(500) CHARACTER SET utf8mb4 DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table co_trade
# ------------------------------------------------------------

DROP TABLE IF EXISTS `co_trade`;

CREATE TABLE `co_trade` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `uid` int(10) NOT NULL,
  `cid` int(11) DEFAULT NULL COMMENT '公司ID',
  `type` int(11) unsigned DEFAULT NULL COMMENT '1 vip  2 代理续费 3 抽纸条 4 放纸条',
  `along_id` int(11) DEFAULT NULL COMMENT '1、2商品ID  3 抽到的纸条UID 4 当前用户的UID',
  `trade_no` varchar(32) NOT NULL,
  `fen` double NOT NULL COMMENT '费用,分',
  `prepay_id` varchar(100) DEFAULT NULL,
  `status` int(1) DEFAULT '0' COMMENT '0 失败  1 成功',
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `co_trade` WRITE;
/*!40000 ALTER TABLE `co_trade` DISABLE KEYS */;

INSERT INTO `co_trade` (`id`, `uid`, `cid`, `type`, `along_id`, `trade_no`, `fen`, `prepay_id`, `status`, `created_at`, `updated_at`)
VALUES
	(106,1,0,2,26,'12ba293297054344a19c32ca0f8bd31a',100,'wx1717463478483626d98fe2dafd2a910000',0,'2022-10-17 17:46:35','2022-10-17 17:46:35'),
	(107,1,0,2,26,'6011447ca54745de87c52040e732a047',100,'wx171748463675034addded26d6982230000',0,'2022-10-17 17:48:46','2022-10-17 17:48:46'),
	(108,1,0,2,26,'625a079aaf9a4eb780e7b8ced03761ad',100,'wx17174847818949422bf5db491aa5180000',0,'2022-10-17 17:48:48','2022-10-17 17:48:48'),
	(109,1,1,2,29,'53ce77699189475f907e2235f7002fe5',100,'wx1813421451587730b7efcb2cd9139f0000',0,'2022-10-18 13:42:15','2022-10-18 13:42:15'),
	(110,1,1,2,30,'b6556d8b57734abf8f792580fd2d53aa',100,'wx181343452290894c61c5189e2d997c0000',0,'2022-10-18 13:43:45','2022-10-18 13:43:45'),
	(111,1,1,2,31,'5936b11a606043d3b116ae92363556f7',2000,'wx18135120696609f9590376726a28180000',0,'2022-10-18 13:51:21','2022-10-18 13:51:21'),
	(112,1,1,2,32,'916ebd8e82bd4981a233fc6e19a93cd2',1200,'wx1813582375559302ae4751642173a90000',0,'2022-10-18 13:58:24','2022-10-18 13:58:24'),
	(113,1,1,2,33,'ab7e61dce4e54d0ba45fc8bc7936e237',1500,'wx181400253632244f66087d87dfcd1a0000',0,'2022-10-18 14:00:25','2022-10-18 14:00:25'),
	(114,1,1,2,34,'559a145097dd4077a425b34da6ebdef3',100,'wx181405530732813617637d92d2bce00000',0,'2022-10-18 14:05:53','2022-10-18 14:05:53'),
	(115,1,1,2,35,'7d6ab8a52de34bc48dcef7b723102272',2000,'wx181418120468507f07aa2e264656600000',0,'2022-10-18 14:18:12','2022-10-18 14:18:12'),
	(116,98,0,2,35,'3d90e95f72fc4a12b4d975e66b69ce4d',100,'wx201506371946169258e60ff7b49d530000',0,'2022-10-20 15:06:37','2022-10-20 15:06:37'),
	(117,98,0,2,35,'5e03094240f948bf89abfb1b3d2a8f98',100,'wx201506376828381fcea55419a700000000',0,'2022-10-20 15:06:38','2022-10-20 15:06:38'),
	(118,1,1,2,35,'f82b701988524d148f0e713daaf67286',100,'wx201506463286738cc07d396e7d89070000',0,'2022-10-20 15:06:46','2022-10-20 15:06:46'),
	(123,104,1,3,35,'36e9bc3adad048e3b5b6b89cb95c245e',100,'wx20170031733863d4faf695850953070000',0,'2022-10-20 17:00:32','2022-10-20 17:00:32'),
	(124,100,1,3,35,'ae856006f8a343b4b8866d7db87cf215',100,'wx2017020916461532b872074b7253500000',0,'2022-10-20 17:02:09','2022-10-20 17:02:09'),
	(125,99,1,3,35,'00f240f025504a07a04daff518005600',100,'wx201703512088035c5590ed9a24cca10000',0,'2022-10-20 17:03:51','2022-10-20 17:03:51'),
	(126,99,1,3,35,'db4418e2853f41eca5781b7a133aef49',100,'wx201712003096645c84d00ace2570070000',0,'2022-10-20 17:12:00','2022-10-20 17:12:00'),
	(127,99,1,3,35,'d519c8aad84d4b4cba9c67ddb11f61d4',100,'wx201716542945251132fd4b6b81bc010000',0,'2022-10-20 17:16:54','2022-10-20 17:16:54'),
	(128,99,1,3,35,'880e365c787e4a8e97f4724826eef6b1',100,'wx20171958524921c0cdf6e6e9a620fb0000',1,'2022-10-20 17:19:59','2022-10-20 17:19:59'),
	(129,99,1,3,35,'86e8acab980e41239148b68d3046edcb',100,'wx20172107489104b2eee3fb640c0c470000',0,'2022-10-20 17:21:08','2022-10-20 17:21:08'),
	(130,101,1,3,35,'5a058494270b432685f820decf217975',100,'wx091504379954376f17d0107f29ed650000',0,'2022-11-09 15:04:38','2022-11-09 15:04:38'),
	(131,1,1,2,5,'3207b448d8fa413187788b9d3debdcd3',1000,'wx29165540634769a1f339ea8a5a28330000',0,'2022-12-29 16:55:41','2022-12-29 16:55:41'),
	(132,1,1,3,35,'51b0fe8ca78e4312baa867d7052c0a22',100,'wx2916561025161347c3c13264b6e5cd0000',0,'2022-12-29 16:56:10','2022-12-29 16:56:10');

/*!40000 ALTER TABLE `co_trade` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table co_user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `co_user`;

CREATE TABLE `co_user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `openid` varchar(50) DEFAULT NULL,
  `nickname` varchar(50) CHARACTER SET utf8mb4 DEFAULT NULL,
  `sex` varchar(2) DEFAULT NULL,
  `city` varchar(40) DEFAULT '',
  `province` varchar(40) DEFAULT NULL,
  `country` varchar(40) DEFAULT NULL,
  `headimgurl` varchar(500) DEFAULT 'https://thirdwx.qlogo.cn/mmopen/vi_32/POgEwh4mIHO4nibH0KlMECNjjGxQUq24ZEaGT4poC6icRiccVGKSyXwibcPq4BWmiaIGuG1icwxaQX6grC9VemZoJ8rg/132',
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `co_user` WRITE;
/*!40000 ALTER TABLE `co_user` DISABLE KEYS */;

INSERT INTO `co_user` (`id`, `openid`, `nickname`, `sex`, `city`, `province`, `country`, `headimgurl`, `created_at`, `updated_at`)
VALUES
	(1,'obF436_Wqrrunu1ItmMeCi3ycxp4','刘洋','0','','','','https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKsPjddRibSRA5JcT1Bp7G70Do7icyvnicAibiabL797ok3Iibm2dZjIPhrw2xUV9EgyWUVSfJS7C2jmtPg/132','2022-09-30 17:16:10','2022-09-30 17:16:10'),
	(96,'obF4361ScAdMAy9_4K2j6OHot7Oo','ΜΟτ。。。！','0','','','','https://thirdwx.qlogo.cn/mmopen/vi_32/AoXC8Dwxrs9D2ichVIicINH29c95gE4Y9KsqsYX0xY8pszS6J2ibG3j0F0NzbHysdrqhPHTUmOafOpiapvjPfrAfVQ/132','2022-10-01 21:54:05','2022-10-01 21:54:05'),
	(97,'obF4363obUXjSy8neE1WuSJfIt9M','🚶可以帅🏃','0','','','','https://thirdwx.qlogo.cn/mmopen/vi_32/sLFZCXvyTJCOqUrleyh5Q2DfjfGmMg81rhn3xU5977OapYuujYZ85nB1Lw3UEBxB5SInIH9MK0NAn7uQhPzUHA/132','2022-10-20 15:04:24','2022-10-20 15:04:24'),
	(98,'obF4366avFVk5Pp8H3tiClRivEN4','老段阿','0','','','','https://thirdwx.qlogo.cn/mmopen/vi_32/juZRgQukxoAukOOFibCE9KJa3IPicaKbypbGjJpib0UBBr9BxYS6YhO0ZceAv0P3YWg2gRaOt3oImYotdBqCUQO7w/132','2022-10-20 15:05:02','2022-10-20 15:05:02'),
	(99,'obF43610SBSpOPGxrnF2YWaQPh8E','聚阅','0','','','','https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLrE3B9KlmqIJJbMj50u6JRy7mP1kwicHqC3Jol4Q80RkNUBgLyX46cnTC74eQFBPV73t4HMYjiahZA/132','2022-10-20 17:10:22','2022-10-20 17:10:22'),
	(100,'obF4366QTQEP5eCukY37fVSqIWyE','风尘','0','','','','https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJqPibCRBtRmad6Ehlc7fnveClDYbeROwDLiceVB5VW0gnBMq3mlyOBx48khXVPzwW2wMNzgIcDibx7Q/132','2022-11-09 14:42:27','2022-11-09 14:42:27'),
	(101,'obF436-UaZQiwMWEw_SmAI814djs','岁驿','0','','','','https://thirdwx.qlogo.cn/mmopen/vi_32/ibZibFiaZbJUgCvHvEqNNY4NHaJOB0Q60dCL6rMbVXS9p69vhJ2dRfibXJQPL9QbTP8UVofrQ06Y70kYgYLLevLYHg/132','2022-11-09 15:04:33','2022-11-09 15:04:33'),
	(102,'obF43684R7SHAG_8w1jcgW7DUEDo','CTY','0','','','','https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLKucaJQYlficlHqCs3ia1yssiajpMeWR5VAIPHxZtc97enMNYMgKowYrJSUj0reUwTibFAD9heia8qhKw/132','2023-04-11 10:28:38','2023-04-11 10:28:38');

/*!40000 ALTER TABLE `co_user` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table co_user_extr
# ------------------------------------------------------------

DROP TABLE IF EXISTS `co_user_extr`;

CREATE TABLE `co_user_extr` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL,
  `cid` int(11) DEFAULT NULL COMMENT '所属企业id',
  `ismatch` int(11) DEFAULT NULL COMMENT '是否是月老& 与过期时间有关联',
  `match_expire` datetime DEFAULT NULL COMMENT '月老过期时间',
  `isVIP` int(11) DEFAULT NULL COMMENT '是否是VIP  1 是 0 不是',
  `vip_expire` datetime DEFAULT NULL COMMENT 'VIP过期时间',
  `income` float DEFAULT NULL COMMENT '累计收入',
  `remaining` float DEFAULT NULL COMMENT '可提现金额',
  `enable` int(1) DEFAULT '1' COMMENT '是否可用',
  `attCode` varchar(11) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `co_user_extr` WRITE;
/*!40000 ALTER TABLE `co_user_extr` DISABLE KEYS */;

INSERT INTO `co_user_extr` (`id`, `uid`, `cid`, `ismatch`, `match_expire`, `isVIP`, `vip_expire`, `income`, `remaining`, `enable`, `attCode`, `created_at`, `updated_at`)
VALUES
	(1,1,1,1,'2022-12-30 17:16:10',0,NULL,30,0,0,'QXFKWNOO','2022-09-30 17:16:10','2022-10-20 17:20:06'),
	(9,96,1,1,'2022-12-30 17:16:10',0,NULL,0,0,0,'BLGMMCND','2022-10-01 21:54:05','2022-10-01 21:54:05'),
	(10,97,1,0,NULL,0,NULL,0,0,0,'EASWHVAQ','2022-10-20 15:04:24','2022-10-20 15:04:24'),
	(11,98,1,0,NULL,0,NULL,0,0,0,'BPSYLDBS','2022-10-20 15:05:02','2022-10-20 15:05:02'),
	(22,99,1,0,NULL,0,NULL,0,0,1,'NMYLFBIZ','2022-10-20 17:10:22','2022-10-20 17:10:22'),
	(23,100,1,0,NULL,0,NULL,0,0,1,'XVFKMMIL','2022-11-09 14:42:27','2022-11-09 14:42:27'),
	(24,101,1,0,NULL,0,NULL,0,0,1,'TKQTPQMB','2022-11-09 15:04:33','2022-11-09 15:04:33'),
	(25,102,1,0,NULL,0,NULL,0,0,1,'AFXYLYDH','2023-04-11 10:28:38','2023-04-11 10:28:38');

/*!40000 ALTER TABLE `co_user_extr` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sys_company_user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_company_user`;

CREATE TABLE `sys_company_user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `cid` int(11) DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  `invited_at` datetime DEFAULT NULL,
  `invited_by` int(11) DEFAULT NULL,
  `invited_by_type` int(1) DEFAULT '0' COMMENT '0 系统管理员用户邀请 1 普通用户邀请',
  `created_at` datetime DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `sys_company_user` WRITE;
/*!40000 ALTER TABLE `sys_company_user` DISABLE KEYS */;

INSERT INTO `sys_company_user` (`id`, `cid`, `uid`, `invited_at`, `invited_by`, `invited_by_type`, `created_at`, `created_by`, `updated_at`, `updated_by`)
VALUES
	(3,1,1,'2022-10-17 10:00:00',1,0,'2022-10-17 10:00:00',1,'2022-10-17 10:00:00',1),
	(7,1,99,'2022-10-20 17:10:22',1,1,'2022-10-20 17:10:22',1,'2022-10-20 17:10:22',1),
	(8,1,97,'2022-10-20 17:10:22',1,1,'2022-10-20 17:10:22',1,'2022-10-20 17:10:22',1),
	(9,1,98,'2022-10-20 17:10:22',1,1,'2022-10-20 17:10:22',1,'2022-10-20 17:10:22',1),
	(10,1,100,'2022-11-09 14:42:27',1,1,'2022-11-09 14:42:27',1,'2022-11-09 14:42:27',1),
	(11,1,101,'2022-11-09 15:04:33',1,1,'2022-11-09 15:04:33',1,'2022-11-09 15:04:33',1),
	(12,1,102,'2023-04-11 10:28:38',1,0,'2023-04-11 10:28:38',1,'2023-04-11 10:28:38',1);

/*!40000 ALTER TABLE `sys_company_user` ENABLE KEYS */;
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
/*!40000 ALTER TABLE `sys_menu` DISABLE KEYS */;

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

/*!40000 ALTER TABLE `sys_menu` ENABLE KEYS */;
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
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;

INSERT INTO `sys_role` (`id`, `name`, `intro`, `sort`, `created_at`, `created_by`, `updated_at`, `updated_by`)
VALUES
	(1,'admin','admin',1,'2022-07-29 11:01:49',1,'2022-07-29 11:02:23',1),
	(2,'company','company',2,'2022-07-29 11:02:22',1,'2022-08-15 10:36:42',1),
	(27,'test','test',3,'2022-10-09 13:39:22',1,'2022-10-09 13:39:34',1);

/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;
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
/*!40000 ALTER TABLE `sys_role_menu` DISABLE KEYS */;

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

/*!40000 ALTER TABLE `sys_role_menu` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sys_set
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_set`;

CREATE TABLE `sys_set` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '纵向表ID',
  `type` int(3) DEFAULT NULL COMMENT '参数类型 1-系统设置费率',
  `value` varchar(11) DEFAULT NULL COMMENT '参数值',
  `desc` varchar(50) DEFAULT NULL COMMENT '说明',
  `created_at` datetime DEFAULT NULL,
  `created_by` int(11) DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `updated_by` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `sys_set` WRITE;
/*!40000 ALTER TABLE `sys_set` DISABLE KEYS */;

INSERT INTO `sys_set` (`id`, `type`, `value`, `desc`, `created_at`, `created_by`, `updated_at`, `updated_by`)
VALUES
	(1,1,'1','提现默认手续费','2022-07-26 10:00:00',1,'2022-07-26 10:00:00',1),
	(2,2,'50','企业抽佣比例','2022-07-26 10:00:00',1,'2022-07-26 10:00:00',1);

/*!40000 ALTER TABLE `sys_set` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table sys_user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL,
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
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;

INSERT INTO `sys_user` (`id`, `uid`, `username`, `password`, `salt`, `token`, `phone`, `name`, `avatar`, `attcode`, `status`, `created_at`, `created_by`, `updated_at`, `updated_by`)
VALUES
	(1,1,'admin','admin123','123456','admin-token','17600337316','超级管理员','https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif','DHSUFHDS',1,'2022-07-29 09:32:07',1,'2022-09-30 15:03:37',1),
	(2,0,'test','testtest','123456','test-token','17600337316','test科技','https://bzrrmedia.oss-cn-beijing.aliyuncs.com/mhjy/header1665294018860_583c7dbb.jpg','NbPSQcXC',1,'2022-10-09 13:40:11',1,'2022-10-17 17:37:22',7),
	(3,0,'test1','test1123','123456','test1-token','17600337316','test','','CCtKj4YE',1,'2023-04-23 17:52:18',1,'2023-04-23 17:52:18',1);

/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;
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
/*!40000 ALTER TABLE `sys_user_role` DISABLE KEYS */;

INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`, `created_at`, `created_by`, `updated_at`, `updated_by`)
VALUES
	(1,1,1,'2022-08-08 17:42:59',1,'2022-08-08 17:42:59',1),
	(2,0,2,'2022-10-09 13:40:21',1,'2022-10-09 13:40:21',1),
	(NULL,3,2,'2023-04-23 17:52:18',1,'2023-04-23 17:52:18',1),
	(NULL,3,27,'2023-04-23 17:52:18',1,'2023-04-23 17:52:18',1);

/*!40000 ALTER TABLE `sys_user_role` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
