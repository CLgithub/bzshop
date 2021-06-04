-- tb_content_category
CREATE TABLE tb_content_category (
	id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '类目ID',
	parent_id bigint(20) DEFAULT NULL COMMENT '父奘目ID=o吋,代表的是一級的类目',
	name varchar (50) DEFAULT NULL COMMENT '分类名称',
	status int(1) DEFAULT 1 COMMENT '状恣。可迭値:1(正常),2 (刪除)',
	sort_order int(4) DEFAULT NULL COMMENT '排列序号,表示同級癸目的展現次序,如数値相等則按',
	is_parent tinyint(1) DEFAULT 1 COMMENT '孩炎目是否カ父炎目,1カtrue, oカfalse',
	created datetime DEFAULT NULL COMMENT '创建时间' ,
	updated datetime DEFAULT NULL COMMENT '创建时间',
	PRIMARY KEY (id),
	KEY parent_id (parent_id, status) USING BTREE,
	KEY sort_order(sort_order)
)
ENGINE=InnoDB AUTO_INCREMENT=98 DEFAULT CHARSET=utf8 COMMENT='内容分类';


-- tb_content
CREATE TABLE tb_content (
	id bigint(20) NOT NULL AUTO_INCREMENT,
	category_id bigint(20) NOT NULL COMMENT '内容类目ID',
	title varchar(200) DEFAULT NULL COMMENT '内容标题',
	sub_title varchar(100) DEFAULT NULL COMMENT '子标题',
	title_desc varchar(500) DEFAULT NULL COMMENT '描述',
	url varchar(500) DEFAULT NULL COMMENT '链接',
	pic varchar (300) DEFAULT NULL COMMENT '图片绝对路径',
	pic2 varchar (300) DEFAULT NULL COMMENT '图片2',
	content text COMMENT '内容',
	created datetime DEFAULT NULL,
	updated datetime DEFAULT NULL,
	PRIMARY KEY (id),
	KEY category_id (category_id) ,
	KEY updated (updated)
) ENGINE= InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

-- tb_item
CREATE TABLE tb_item (
	id bigint(20) NOT NULL COMMENT '商品id,同时也是商品编号',
	title varchar(100) NOT NULL COMMENT '商品标题',
	sell_point varchar(500) DEFAULT NULL COMMENT '商品卖点',
	price bigint(20) NOT NULL COMMENT '商品价格,单位为:分',
	num int(10) NOT NULL COMMENT '库存数量',
	barcode varchar(30) DEFAULT NULL COMMENT '商品条形码',
	image varchar(500) DEFAULT NULL COMMENT '商品图片',
	cid bigint(10) NOT NULL COMMENT '所属类目,叶子类目',
	status tinyint(4) NOT NULL DEFAULT 1 COMMENT '商品状态,1-正常,2-下架,3-删除',
	created datetime NOT NULL COMMENT '创建时间',
	updated datetime NOT NULL COMMENT '更新时间',
	PRIMARY KEY (id),
	KEY cid (cid) ,
	KEY status (status),
	KEY updated (updated)
)
ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品表';

-- tb_item_cat
CREATE TABLE tb_item_cat (
	id bigint(20) NOT NULL AUTO_INCREMENT COMMENT '类目ID',
	parent_id bigint(20) DEFAULT NULL COMMENT '父类目ID=0时,代表的是一级的类目',
	name varchar(50) DEFAULT NULL COMMENT '分类名称',
	status int(1) DEFAULT 1 COMMENT '状态。可选值：1正常,2删除',
	sort_order int(4) DEFAULT NULL COMMENT '',
	is_parent tinyint(1) DEFAULT 1 COMMENT '',
	created datetime DEFAULT NULL COMMENT '',
	updated datetime DEFAULT NULL COMMENT '',
	PRIMARY KEY (id),
	KEY parent_id (parent_id, status) USING BTREE ,
	KEY sort_order (sort_order)
)ENGINE=InnoDB AUTO_INCREMENT=1183 DEFAULT CHARSET=utf8 COMMENT='';

--
CREATE TABLE tb_item_desc (
item_id bigint(20) NOT NULL COMMENT '商品ID',
item_desc text COMMENT '商品描述',
created datetime DEFAULT NULL COMMENT '创建时间',
updated datetime DEFAULT NULL COMMENT '更新时间',
PRIMARY KEY(item_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品描述表';

--
CREATE TABLE tb_item_param (
	id bigint(20) NOT NULL AUTO_INCREMENT ,
	item_cat_id bigint(20) DEFAULT NULL ,
	param_data text,
	created datetime DEFAULT NULL,
	updated datetime DEFAULT NULL,
	PRIMARY KEY (id) ,
	KEY item_cat_id (item_cat_id)
)AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

--
CREATE TABLE tb_item_param_item (
id bigint(20) NOT NULL AUTO_INCREMENT ,
item_id bigint(20) DEFAULT NULL COMMENT '',
param_data text COMMENT '',
created datetime DEFAULT NULL,
updated datetime DEFAULT NULL,
PRIMARY KEY (id) ,
KEY item_id (item_id) USING BTREE
)ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='商品规格和商品的关系表';

--
CREATE TABLE tb_order(
	order_id varchar(50) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '',
	payment varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '',
	payment_type int(2) DEFAULT NULL COMMENT '',
	post_fee varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '',
	status int(10) DEFAULT NULL COMMENT '状态：1未付款,2已经付款,3未发货,4已发货',
	create_time datetime DEFAULT NULL COMMENT '订单创建时间',
	update_time datetime DEFAULT NULL COMMENT '订单更新时间',
	payment_time datetime DEFAULT NULL COMMENT '付款时间',
	consign_time datetime DEFAULT NULL COMMENT '发货时间',
	end_time datetime DEFAULT NULL COMMENT '交易完成时间',
	close_time datetime DEFAULT NULL COMMENT '交易完成时间',
	shipping_name varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '物流名称',
	shipping_code varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '物流单号',
	user_id bigint(20) DEFAULT NULL COMMENT '用户',
	buyer_message varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '买家留言',
	buyer_nick varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '买家昵称',
	buyer_rate int(2) DEFAULT NULL COMMENT '买家是否已经评价',
	PRIMARY KEY (order_id),
	KEY create_time (create_time),
	KEY buyer_nick (buyer_nick),
	KEY status (status)
);

-- tb_order_item
CREATE TABLE tb_order_item(
	id varchar(20) COLLATE utf8_bin NOT NULL,
	item_id varchar(50) COLLATE utf8_bin NOT NULL COMMENT '',
	order_id varchar(50) COLLATE utf8_bin NOT NULL COMMENT '',
	num int(10) DEFAULT NULL COMMENT '',
	title varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '',
	price bigint(50) DEFAULT NULL COMMENT '',
	total_fee bigint(50) DEFAULT NULL COMMENT '',
	pic_path varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '',
	PRIMARY KEY (id),
	KEY item_id (item_id),
	KEY order_id (order_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- tb_order_shipping
CREATE TABLE tb_order_shipping(
	order_id varchar(50) NOT NULL COMMENT '',
	receiver_name varchar(20) DEFAULT NULL COMMENT '',
	receiver_phone varchar(20) DEFAULT NULL COMMENT '',
	receiver_mobile varchar(30) DEFAULT NULL COMMENT '',
	receiver_state varchar(10) DEFAULT NULL COMMENT	'',
	receiver_city varchar(10) DEFAULT NULL COMMENT '',
	receiver_district varchar(10) DEFAULT NULL COMMENT '',
	receiver_address varchar(200) DEFAULT NULL COMMENT '',
	receiver_zip varchar(6) DEFAULT NULL COMMENT '',
	created datetime DEFAULT NULL,
	updated datetime DEFAULT NULL,
PRIMARY KEY (order_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- tb_user
CREATE TABLE tb_user(
id bigint(20) NOT NULL AUTO_INCREMENT,
username varchar(50) NOT NULL COMMENT '',
password varchar(32) NOT NULL COMMENT '',
phone varchar(20) DEFAULT NULL COMMENT '',
email varchar(50) DEFAULT NULL COMMENT '',
created datetime NOT NULL,
updated datetime NOT NULL,
PRIMARY KEY (id),
UNIQUE KEY username (username) USING BTREE,
UNIQUE KEY phone (phone) USING BTREE,
UNIQUE KEY email (email) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- insert into tb_item
insert into tb_item(id,title,sell_point,price,num,barcode,image,cid,status,created,updated) values(3,'商品标题3','商品卖点3',100,54,'商品条形码3','商品图片3',0,1,'20200922102030','20200922102030');
insert into tb_item(id,title,sell_point,price,num,barcode,image,cid,status,created,updated) values('1','商品1','清仓处理','2999',894,'','https://avatars.githubusercontent.com/u/74693301?s=64&v=4',560,1,'2021-01-01','2021-02-02');
-- tb_item_cat 插入数据
insert into tb_item_cat values('1','0','图书、影音','1','1','1','2015-10-10','2021-02-14');
insert into tb_item_cat values('2','1','电子书刊','1','1','1','2015-10-10','2021-02-14');
insert into tb_item_cat values('3','0','家用电器','1','1','1','2015-10-10','2021-02-14');
insert into tb_item_cat values('4','3','abc','1','1','1','2015-10-10','2021-02-14');

--
insert into tb_content_category(parent_id,name,status,sort_order,is_parent) values('0','网购商城','1','1','1');
insert into tb_content_category(parent_id,name,status,sort_order,is_parent) values('101','首页','1','1','1');
insert into tb_content_category(parent_id,name,status,sort_order,is_parent) values('101','列表页面','1','1','1');
insert into tb_content_category(parent_id,name,status,sort_order,is_parent) values('101','详细页面','1','1','1');
insert into tb_content_category(parent_id,name,status,sort_order,is_parent) values('102','大广告','1','1','1');
insert into tb_content_category(parent_id,name,status,sort_order,is_parent) values('102','小广告','1','1','1');
