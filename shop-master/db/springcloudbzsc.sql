/*
 Navicat MySQL Data Transfer

 Source Server         : docker_mysql
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : localhost:3306
 Source Schema         : springcloudbzsc

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 10/08/2021 20:34:50
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for hibernate_sequence
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hibernate_sequence
-- ----------------------------
BEGIN;
INSERT INTO `hibernate_sequence` VALUES (19);
COMMIT;

-- ----------------------------
-- Table structure for t_logger
-- ----------------------------
DROP TABLE IF EXISTS `t_logger`;
CREATE TABLE `t_logger` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `group_id` varchar(64) NOT NULL,
  `unit_id` varchar(32) NOT NULL,
  `tag` varchar(50) NOT NULL,
  `content` varchar(1024) NOT NULL,
  `create_time` varchar(30) NOT NULL,
  `app_name` varchar(128) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_logger
-- ----------------------------
BEGIN;
INSERT INTO `t_logger` VALUES (1, 'non', 'non', 'JoinGroupExecuteService', 'attempts to join the non-existent transaction group. [0b8ced1e9e77345c8ab3be331b9d3646@cloud-common-item]', '2021-08-09 14:39:51 011', 'cloud-common-tm:7970');
INSERT INTO `t_logger` VALUES (2, 'non', 'non', 'JoinGroupExecuteService', 'attempts to join the non-existent transaction group. [0b8ced1e9e77345c8ab3be331b9d3646@cloud-common-item]', '2021-08-10 14:41:47 067', 'cloud-common-tm:7970');
COMMIT;

-- ----------------------------
-- Table structure for t_tx_exception
-- ----------------------------
DROP TABLE IF EXISTS `t_tx_exception`;
CREATE TABLE `t_tx_exception` (
  `id` bigint NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `ex_state` smallint NOT NULL,
  `group_id` varchar(60) DEFAULT NULL,
  `mod_id` varchar(100) DEFAULT NULL,
  `registrar` smallint NOT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `transaction_state` int DEFAULT NULL,
  `unit_id` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_tx_exception
-- ----------------------------
BEGIN;
INSERT INTO `t_tx_exception` VALUES (1, '2021-03-21 13:03:44', 1, 'fadb75bcb6a537', 'cloud-common-content', 0, NULL, 0, 'ce1c22761593107bebb0eddd6323ca92');
INSERT INTO `t_tx_exception` VALUES (2, '2021-03-21 13:03:56', 1, 'fadb7952d6a537', 'cloud-common-content', 0, NULL, 0, 'ce1c22761593107bebb0eddd6323ca92');
INSERT INTO `t_tx_exception` VALUES (3, '2021-03-21 13:05:16', 1, 'fadb8c36d6b537', 'cloud-common-content', 0, NULL, 0, 'ce1c22761593107bebb0eddd6323ca92');
INSERT INTO `t_tx_exception` VALUES (4, '2021-03-21 13:05:17', 1, 'fadb8ced56b537', 'cloud-common-content', 0, NULL, 0, 'ce1c22761593107bebb0eddd6323ca92');
INSERT INTO `t_tx_exception` VALUES (5, '2021-03-21 13:05:18', 1, 'fadb8c9ca6b537', 'cloud-common-content', 0, NULL, 0, 'ce1c22761593107bebb0eddd6323ca92');
INSERT INTO `t_tx_exception` VALUES (6, '2021-03-21 13:06:47', 1, 'fadba6e856b537', 'cloud-common-content', 0, NULL, 0, 'edc6e75a1a398e9b48f1087a2caaf54e');
INSERT INTO `t_tx_exception` VALUES (7, '2021-06-04 07:42:10', 0, '112ece296f58537', 'cloud-common-content', 0, NULL, 0, 'ce1c22761593107bebb0eddd6323ca92');
INSERT INTO `t_tx_exception` VALUES (8, '2021-06-04 07:42:11', 0, '112ece296f58537', 'cloud-common-content', 0, NULL, 0, 'ce1c22761593107bebb0eddd6323ca92');
INSERT INTO `t_tx_exception` VALUES (9, '2021-06-28 06:52:38', 0, '11aa3974e085537', 'cloud-common-content', 0, NULL, 0, '64b49f717b2a18caaff8dec4be380967');
INSERT INTO `t_tx_exception` VALUES (10, '2021-06-28 06:52:39', 0, '11aa3974e085537', 'cloud-common-content', 0, NULL, 0, '64b49f717b2a18caaff8dec4be380967');
INSERT INTO `t_tx_exception` VALUES (11, '2021-07-04 07:17:09', 0, '11c935cf621b537', 'cloud-common-content', 0, NULL, 0, '64b49f717b2a18caaff8dec4be380967');
INSERT INTO `t_tx_exception` VALUES (12, '2021-07-04 07:36:15', 0, '11c9474cbc29537', 'cloud-common-content', 0, NULL, 0, '64b49f717b2a18caaff8dec4be380967');
INSERT INTO `t_tx_exception` VALUES (13, '2021-07-04 07:37:07', 0, '11c94810ee29537', 'cloud-common-content', 0, NULL, 0, '64b49f717b2a18caaff8dec4be380967');
INSERT INTO `t_tx_exception` VALUES (14, '2021-07-05 03:31:03', 0, '11cd8d63d63a537', 'cloud-common-content', 0, NULL, 0, '12ba50a3f44392636736ee97e8836af4');
INSERT INTO `t_tx_exception` VALUES (15, '2021-08-09 06:23:34', 0, '128269eb568f537', 'cloud-common-item', 0, NULL, 0, '0b8ced1e9e77345c8ab3be331b9d3646');
INSERT INTO `t_tx_exception` VALUES (16, '2021-08-09 06:29:22', 0, '12826f3bab96537', 'cloud-common-item', 0, NULL, 0, '0b8ced1e9e77345c8ab3be331b9d3646');
INSERT INTO `t_tx_exception` VALUES (17, '2021-08-10 07:20:34', 0, '1287c472c210b537', 'cloud-common-item', 0, NULL, 0, '8a3775ff7fb0884a41f81aa05699aafa');
INSERT INTO `t_tx_exception` VALUES (18, '2021-08-10 07:22:12', 0, '1287c5f21810d537', 'cloud-common-item', 0, NULL, 0, '8a3775ff7fb0884a41f81aa05699aafa');
COMMIT;

-- ----------------------------
-- Table structure for tb_content
-- ----------------------------
DROP TABLE IF EXISTS `tb_content`;
CREATE TABLE `tb_content` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `category_id` bigint NOT NULL COMMENT '内容类目ID',
  `title` varchar(200) DEFAULT NULL COMMENT '内容标题',
  `sub_title` varchar(100) DEFAULT NULL COMMENT '子标题',
  `title_desc` varchar(500) DEFAULT NULL COMMENT '描述',
  `url` varchar(500) DEFAULT NULL COMMENT '链接',
  `pic` varchar(300) DEFAULT NULL COMMENT '图片绝对路径',
  `pic2` varchar(300) DEFAULT NULL COMMENT '图片2',
  `content` text COMMENT '内容',
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `category_id` (`category_id`),
  KEY `updated` (`updated`)
) ENGINE=InnoDB AUTO_INCREMENT=129 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_content
-- ----------------------------
BEGIN;
INSERT INTO `tb_content` VALUES (124, 108, '大2', '', '', '', 'http://127.0.0.1/2021/07/05/1625455391647112.png', NULL, '', '2021-07-05 03:23:15', '2021-07-05 03:23:15');
INSERT INTO `tb_content` VALUES (125, 108, '大', '', '', '', 'http://127.0.0.1/2021/07/05/1625455474681957.jpg', NULL, '', '2021-07-05 03:24:38', '2021-07-05 03:24:38');
INSERT INTO `tb_content` VALUES (128, 108, '大3', '', '', '', 'http://127.0.0.1/2021/07/05/1625456058523553.jpeg', NULL, '', '2021-07-05 03:34:21', '2021-07-05 03:34:21');
COMMIT;

-- ----------------------------
-- Table structure for tb_content_category
-- ----------------------------
DROP TABLE IF EXISTS `tb_content_category`;
CREATE TABLE `tb_content_category` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '类目ID',
  `parent_id` bigint DEFAULT NULL COMMENT '父奘目ID=o吋,代表的是一級的类目',
  `name` varchar(50) DEFAULT NULL COMMENT '分类名称',
  `status` int DEFAULT '1' COMMENT '状恣。可迭値:1(正常),2 (刪除)',
  `sort_order` int DEFAULT NULL COMMENT '排列序号,表示同級癸目的展現次序,如数値相等則按',
  `is_parent` tinyint(1) DEFAULT '1' COMMENT '孩炎目是否カ父炎目,1カtrue, oカfalse',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `updated` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `parent_id` (`parent_id`,`status`) USING BTREE,
  KEY `sort_order` (`sort_order`)
) ENGINE=InnoDB AUTO_INCREMENT=138 DEFAULT CHARSET=utf8 COMMENT='内容分类';

-- ----------------------------
-- Records of tb_content_category
-- ----------------------------
BEGIN;
INSERT INTO `tb_content_category` VALUES (101, 0, '网购商城', 1, 1, 1, NULL, '2021-06-04 08:24:09');
INSERT INTO `tb_content_category` VALUES (107, 101, '首页', 1, 1, 1, '2021-03-21 12:24:35', '2021-06-27 09:18:17');
INSERT INTO `tb_content_category` VALUES (108, 107, '大广告位', 1, 1, 0, '2021-03-21 12:25:23', '2021-03-21 12:25:23');
INSERT INTO `tb_content_category` VALUES (109, 107, '小广告位', 1, 1, 0, '2021-03-21 12:25:30', '2021-03-21 12:25:30');
INSERT INTO `tb_content_category` VALUES (110, 101, '列表页面', 1, 1, 0, '2021-03-21 12:31:48', '2021-06-04 08:18:26');
INSERT INTO `tb_content_category` VALUES (111, 101, '详细页面', 1, 1, 0, '2021-03-21 12:32:00', '2021-06-04 08:05:15');
COMMIT;

-- ----------------------------
-- Table structure for tb_item
-- ----------------------------
DROP TABLE IF EXISTS `tb_item`;
CREATE TABLE `tb_item` (
  `id` bigint NOT NULL COMMENT '商品id,同时也是商品编号',
  `title` varchar(100) NOT NULL COMMENT '商品标题',
  `sell_point` varchar(500) DEFAULT NULL COMMENT '商品卖点',
  `price` bigint NOT NULL COMMENT '商品价格,单位为:分',
  `num` int NOT NULL COMMENT '库存数量',
  `barcode` varchar(30) DEFAULT NULL COMMENT '商品条形码',
  `image` varchar(500) DEFAULT NULL COMMENT '商品图片',
  `cid` bigint NOT NULL COMMENT '所属类目,叶子类目',
  `status` tinyint NOT NULL DEFAULT '1' COMMENT '商品状态,1-正常,2-下架,3-删除',
  `created` datetime NOT NULL COMMENT '创建时间',
  `updated` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `cid` (`cid`),
  KEY `status` (`status`),
  KEY `updated` (`updated`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品表';

-- ----------------------------
-- Records of tb_item
-- ----------------------------
BEGIN;
INSERT INTO `tb_item` VALUES (162857900846557, 'mx2', '卖点1', 2499, 123, NULL, 'http://homeUbuntu/2021/08/10/1628579000609250.jpg', 1189, 1, '2021-08-10 07:03:28', '2021-08-10 08:39:02');
INSERT INTO `tb_item` VALUES (162858484588188, 'iphone12', 'iphone12卖点', 5699, 123, NULL, 'http://homeUbuntu/2021/08/10/1628584830444712.jpg', 1189, 1, '2021-08-10 08:40:46', '2021-08-10 08:40:46');
COMMIT;

-- ----------------------------
-- Table structure for tb_item_cat
-- ----------------------------
DROP TABLE IF EXISTS `tb_item_cat`;
CREATE TABLE `tb_item_cat` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '类目ID',
  `parent_id` bigint DEFAULT NULL COMMENT '父类目ID=0时,代表的是一级的类目',
  `name` varchar(50) DEFAULT NULL COMMENT '分类名称',
  `status` int DEFAULT '1' COMMENT '状态。可选值：1正常,2删除',
  `sort_order` int DEFAULT NULL,
  `is_parent` tinyint(1) DEFAULT '1',
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `parent_id` (`parent_id`,`status`) USING BTREE,
  KEY `sort_order` (`sort_order`)
) ENGINE=InnoDB AUTO_INCREMENT=1195 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_item_cat
-- ----------------------------
BEGIN;
INSERT INTO `tb_item_cat` VALUES (1, 0, '手机/数码', 1, 1, 1, '2015-10-10 00:00:00', '2021-02-14 00:00:00');
INSERT INTO `tb_item_cat` VALUES (3, 0, '家用电器', 1, 1, 1, '2015-10-10 00:00:00', '2021-02-14 00:00:00');
INSERT INTO `tb_item_cat` VALUES (4, 3, '电视', 1, 1, 1, '2015-10-10 00:00:00', '2021-02-14 00:00:00');
INSERT INTO `tb_item_cat` VALUES (5, 3, '空调', 1, 1, 1, '2015-10-10 00:00:00', '2021-02-14 00:00:00');
INSERT INTO `tb_item_cat` VALUES (1183, 4, '全面屏电视', 1, 1, 0, NULL, NULL);
INSERT INTO `tb_item_cat` VALUES (1184, 4, 'OLED电视', 1, 1, 0, NULL, NULL);
INSERT INTO `tb_item_cat` VALUES (1185, 5, '空调挂机', 1, 1, 0, NULL, NULL);
INSERT INTO `tb_item_cat` VALUES (1186, 5, '中央空调', 1, 1, 0, NULL, NULL);
INSERT INTO `tb_item_cat` VALUES (1187, 1, '手机通讯', 1, 1, 1, NULL, NULL);
INSERT INTO `tb_item_cat` VALUES (1188, 1, '手机配件', 1, 1, 1, NULL, NULL);
INSERT INTO `tb_item_cat` VALUES (1189, 1187, '手机', 1, 1, 0, NULL, NULL);
INSERT INTO `tb_item_cat` VALUES (1190, 1187, '拍照手机', 1, 1, 0, NULL, NULL);
INSERT INTO `tb_item_cat` VALUES (1191, 1187, '5G手机', 1, 1, 0, NULL, NULL);
INSERT INTO `tb_item_cat` VALUES (1192, 1188, '手机壳', 1, 1, 0, NULL, NULL);
INSERT INTO `tb_item_cat` VALUES (1193, 1188, '手机膜', 1, 1, 0, NULL, NULL);
INSERT INTO `tb_item_cat` VALUES (1194, 1188, '充电器', 1, 1, 0, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for tb_item_desc
-- ----------------------------
DROP TABLE IF EXISTS `tb_item_desc`;
CREATE TABLE `tb_item_desc` (
  `item_id` bigint NOT NULL COMMENT '商品ID',
  `item_desc` text COMMENT '商品描述',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `updated` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品描述表';

-- ----------------------------
-- Records of tb_item_desc
-- ----------------------------
BEGIN;
INSERT INTO `tb_item_desc` VALUES (162857900846557, '<p>描述1</p>', '2021-08-10 07:03:29', '2021-08-10 08:39:02');
INSERT INTO `tb_item_desc` VALUES (162858484588188, '<p>描述</p>', '2021-08-10 08:40:46', '2021-08-10 08:40:46');
COMMIT;

-- ----------------------------
-- Table structure for tb_item_param
-- ----------------------------
DROP TABLE IF EXISTS `tb_item_param`;
CREATE TABLE `tb_item_param` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `item_cat_id` bigint DEFAULT NULL,
  `param_data` text,
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `item_cat_id` (`item_cat_id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_item_param
-- ----------------------------
BEGIN;
INSERT INTO `tb_item_param` VALUES (34, 1189, '[{\"value\":\"品牌\",\"children\":[{\"childValue\":\"型号\"}]},{\"value\":\"尺寸\",\"children\":[]}]', '2021-07-08 14:12:11', '2021-07-08 14:12:11');
COMMIT;

-- ----------------------------
-- Table structure for tb_item_param_item
-- ----------------------------
DROP TABLE IF EXISTS `tb_item_param_item`;
CREATE TABLE `tb_item_param_item` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `item_id` bigint DEFAULT NULL,
  `param_data` text,
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `item_id` (`item_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COMMENT='商品规格和商品的关系表';

-- ----------------------------
-- Records of tb_item_param_item
-- ----------------------------
BEGIN;
INSERT INTO `tb_item_param_item` VALUES (25, 162857900846557, NULL, '2021-08-10 07:03:29', '2021-08-10 08:39:02');
INSERT INTO `tb_item_param_item` VALUES (26, 162858484588188, NULL, '2021-08-10 08:40:46', '2021-08-10 08:40:46');
COMMIT;

-- ----------------------------
-- Table structure for tb_order
-- ----------------------------
DROP TABLE IF EXISTS `tb_order`;
CREATE TABLE `tb_order` (
  `order_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '',
  `payment` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `payment_type` int DEFAULT NULL,
  `post_fee` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `status` int DEFAULT NULL COMMENT '状态：1未付款,2已经付款,3未发货,4已发货',
  `create_time` datetime DEFAULT NULL COMMENT '订单创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '订单更新时间',
  `payment_time` datetime DEFAULT NULL COMMENT '付款时间',
  `consign_time` datetime DEFAULT NULL COMMENT '发货时间',
  `end_time` datetime DEFAULT NULL COMMENT '交易完成时间',
  `close_time` datetime DEFAULT NULL COMMENT '交易完成时间',
  `shipping_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '物流名称',
  `shipping_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '物流单号',
  `user_id` bigint DEFAULT NULL COMMENT '用户',
  `buyer_message` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '买家留言',
  `buyer_nick` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '买家昵称',
  `buyer_rate` int DEFAULT NULL COMMENT '买家是否已经评价',
  PRIMARY KEY (`order_id`),
  KEY `create_time` (`create_time`),
  KEY `buyer_nick` (`buyer_nick`),
  KEY `status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_order
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for tb_order_item
-- ----------------------------
DROP TABLE IF EXISTS `tb_order_item`;
CREATE TABLE `tb_order_item` (
  `id` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `item_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `order_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `num` int DEFAULT NULL,
  `title` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `price` bigint DEFAULT NULL,
  `total_fee` bigint DEFAULT NULL,
  `pic_path` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `item_id` (`item_id`),
  KEY `order_id` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of tb_order_item
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for tb_order_shipping
-- ----------------------------
DROP TABLE IF EXISTS `tb_order_shipping`;
CREATE TABLE `tb_order_shipping` (
  `order_id` varchar(50) NOT NULL,
  `receiver_name` varchar(20) DEFAULT NULL,
  `receiver_phone` varchar(20) DEFAULT NULL,
  `receiver_mobile` varchar(30) DEFAULT NULL,
  `receiver_state` varchar(10) DEFAULT NULL,
  `receiver_city` varchar(10) DEFAULT NULL,
  `receiver_district` varchar(10) DEFAULT NULL,
  `receiver_address` varchar(200) DEFAULT NULL,
  `receiver_zip` varchar(6) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_order_shipping
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(32) NOT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `created` datetime NOT NULL,
  `updated` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`) USING BTREE,
  UNIQUE KEY `phone` (`phone`) USING BTREE,
  UNIQUE KEY `email` (`email`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of tb_user
-- ----------------------------
BEGIN;
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
