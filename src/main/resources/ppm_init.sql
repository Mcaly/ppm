-- 用户表
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(50) NOT NULL COMMENT '登录账号',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `real_name` varchar(20) NOT NULL COMMENT '姓名',
  `phone` varchar(11) DEFAULT NULL COMMENT '手机号',
  `job_type` tinyint NOT NULL DEFAULT 1 COMMENT '1管理员 2收费员 3维修师傅',
  `status` tinyint NOT NULL DEFAULT 1 COMMENT '0停用 1正常',
  `is_delete` tinyint NOT NULL DEFAULT 0,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_username` (`username`)
) COMMENT '物业员工';
INSERT INTO `user` (
    `username`,
    `password`,
    `real_name`,
    `phone`,
    `job_type`,
    `status`,
    `is_delete`
) VALUES
      ('1', '1', '管理员', '18888888888', 1, 1, 0),
      ('2', '2', '收费员', '18888888888', 2, 1, 0),
      ('3', '3', '维修师傅', '18888888888', 3, 1, 0),
      ('4', '4', '保安', '18888888888', 4, 1, 0);


-- 楼栋表
CREATE TABLE `building` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `build_name` varchar(30) NOT NULL COMMENT '楼栋号，如1号楼',
  `floor_count` int NOT NULL COMMENT '总楼层',
  `is_delete` tinyint NOT NULL DEFAULT 0,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) COMMENT '小区楼栋';


-- 房屋表
CREATE TABLE `house` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `build_id` bigint NOT NULL COMMENT '所属楼栋ID',
  `house_no` varchar(20) NOT NULL COMMENT '房号 1-101',
  `area` decimal(10,2) NOT NULL COMMENT '建筑面积',
  `unit_price` decimal(10,2) DEFAULT 0 COMMENT '物业费单价/㎡',
  `status` tinyint NOT NULL DEFAULT 1 COMMENT '1空置 2已入住',
  `is_delete` tinyint NOT NULL DEFAULT 0,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_build` (`build_id`)
) COMMENT '房屋信息';



-- 业主表
CREATE TABLE `owner` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `house_id` bigint NOT NULL COMMENT '绑定房屋ID',
  `owner_name` varchar(20) NOT NULL COMMENT '业主姓名',
  `phone` varchar(11) NOT NULL COMMENT '联系电话',
  `id_card` varchar(30) DEFAULT NULL COMMENT '身份证',
  `live_status` tinyint DEFAULT 1 COMMENT '1自住 2出租',
  `is_delete` tinyint NOT NULL DEFAULT 0,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_house` (`house_id`)
) COMMENT '业主住户';


-- 业务费账单
CREATE TABLE `fee_bill` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `house_id` bigint NOT NULL COMMENT '房屋',
  `bill_month` varchar(10) NOT NULL COMMENT '账单月份 2026-06',
  `total_fee` decimal(12,2) NOT NULL COMMENT '当月物业费',
  `pay_status` tinyint NOT NULL DEFAULT 0 COMMENT '0未缴 1已缴',
  `pay_time` datetime DEFAULT NULL COMMENT '缴费时间',
  `oper_user_id` bigint DEFAULT NULL COMMENT '收费员ID',
  `is_delete` tinyint NOT NULL DEFAULT 0,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_house` (`house_id`)
) COMMENT '物业费账单';

-- 报修维修表
CREATE TABLE `repair` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `house_id` bigint NOT NULL COMMENT '报修房屋',
  `owner_name` varchar(20) NOT NULL COMMENT '报修人',
  `phone` varchar(11) NOT NULL COMMENT '联系电话',
  `repair_type` varchar(30) NOT NULL COMMENT '维修类型 水电/门窗/公共设施',
  `content` varchar(255) DEFAULT NULL COMMENT '故障描述',
  `worker_id` bigint DEFAULT NULL COMMENT '维修师傅ID',
  `repair_status` tinyint NOT NULL DEFAULT 0 COMMENT '0待派单 1维修中 2已完成',
  `finish_time` datetime DEFAULT NULL COMMENT '完工时间',
  `is_delete` tinyint NOT NULL DEFAULT 0,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_house` (`house_id`)
) COMMENT '业主报修工单';

-- 访客车辆登记
CREATE TABLE `car_visitor` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `house_id` bigint DEFAULT NULL COMMENT '拜访房屋',
  `visitor_name` varchar(20) NOT NULL COMMENT '访客姓名',
  `plate_num` varchar(20) DEFAULT NULL COMMENT '车牌号',
  `in_time` datetime NOT NULL COMMENT '进入时间',
  `out_time` datetime DEFAULT NULL COMMENT '离开时间',
  `oper_user_id` bigint DEFAULT NULL COMMENT '登记保安',
  `is_delete` tinyint NOT NULL DEFAULT 0,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) COMMENT '车辆访客登记';


-- 小区公告
CREATE TABLE `notice` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL COMMENT '公告标题',
  `content` text NOT NULL COMMENT '公告内容',
  `publish_user` bigint DEFAULT NULL COMMENT '发布员工ID',
  `is_top` tinyint DEFAULT 0 COMMENT '0不置顶 1置顶',
  `is_delete` tinyint NOT NULL DEFAULT 0,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) COMMENT '小区公告';
