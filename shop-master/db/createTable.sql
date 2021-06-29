-- springcloudbzsc.hibernate_sequence definition

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- springcloudbzsc.t_logger definition

CREATE TABLE `t_logger` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `group_id` varchar(64) NOT NULL,
  `unit_id` varchar(32) NOT NULL,
  `tag` varchar(50) NOT NULL,
  `content` varchar(1024) NOT NULL,
  `create_time` varchar(30) NOT NULL,
  `app_name` varchar(128) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- springcloudbzsc.t_tx_exception definition

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


-- springcloudbzsc.tb_content definition

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
) ENGINE=InnoDB AUTO_INCREMENT=112 DEFAULT CHARSET=utf8;


-- springcloudbzsc.tb_content_category definition

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


-- springcloudbzsc.tb_item definition

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


-- springcloudbzsc.tb_item_cat definition

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


-- springcloudbzsc.tb_item_desc definition

CREATE TABLE `tb_item_desc` (
  `item_id` bigint NOT NULL COMMENT '商品ID',
  `item_desc` text COMMENT '商品描述',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `updated` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品描述表';


-- springcloudbzsc.tb_item_param definition

CREATE TABLE `tb_item_param` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `item_cat_id` bigint DEFAULT NULL,
  `param_data` text,
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `item_cat_id` (`item_cat_id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;


-- springcloudbzsc.tb_item_param_item definition

CREATE TABLE `tb_item_param_item` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `item_id` bigint DEFAULT NULL,
  `param_data` text,
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `item_id` (`item_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT='商品规格和商品的关系表';


-- springcloudbzsc.tb_order definition

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


-- springcloudbzsc.tb_order_item definition

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


-- springcloudbzsc.tb_order_shipping definition

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


-- springcloudbzsc.tb_user definition

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