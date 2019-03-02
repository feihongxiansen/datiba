# Host: localhost  (Version 8.0.12)
# Date: 2019-03-03 01:35:51
# Generator: MySQL-Front 6.1  (Build 1.26)


#
# Structure for table "as_admin"
#

DROP TABLE IF EXISTS `as_admin`;
CREATE TABLE `as_admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `admin_nick` varchar(20) NOT NULL COMMENT '管理员昵称',
  `admin_name` varchar(20) NOT NULL COMMENT '管理员姓名',
  `password` varchar(32) NOT NULL COMMENT '登录密码',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱地址',
  `phone` varchar(11) DEFAULT NULL COMMENT '电话号码',
  `login_state` tinyint(1) NOT NULL DEFAULT '1' COMMENT '登录状态',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='管理员表';

#
# Data for table "as_admin"
#


#
# Structure for table "as_carousel"
#

DROP TABLE IF EXISTS `as_carousel`;
CREATE TABLE `as_carousel` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `photo_url` varchar(300) NOT NULL COMMENT '图片地址',
  `detail_url` varchar(300) NOT NULL COMMENT '详细页面',
  `use_state` tinyint(2) NOT NULL COMMENT '启用状态',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='轮播图表';

#
# Data for table "as_carousel"
#


#
# Structure for table "as_gift"
#

DROP TABLE IF EXISTS `as_gift`;
CREATE TABLE `as_gift` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `gift_name` varchar(80) NOT NULL COMMENT '物品名称',
  `quantity` int(11) NOT NULL COMMENT '物品数量',
  `integral` int(11) NOT NULL COMMENT '所需积分',
  `photos` text COMMENT '图片描述',
  `summary` text COMMENT '物品简介',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='实物礼品表';

#
# Data for table "as_gift"
#


#
# Structure for table "as_grade"
#

DROP TABLE IF EXISTS `as_grade`;
CREATE TABLE `as_grade` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `grade_name` varchar(50) NOT NULL COMMENT '年级名称',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='年级表';

#
# Data for table "as_grade"
#


#
# Structure for table "as_subject"
#

DROP TABLE IF EXISTS `as_subject`;
CREATE TABLE `as_subject` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `subject_name` varchar(50) NOT NULL COMMENT '学科名称',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='学科表';

#
# Data for table "as_subject"
#


#
# Structure for table "as_user"
#

DROP TABLE IF EXISTS `as_user`;
CREATE TABLE `as_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(15) NOT NULL COMMENT '用户真实姓名',
  `nick_name` varchar(20) NOT NULL COMMENT '用户昵称',
  `login_state` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否允许登录',
  `password` varchar(32) NOT NULL COMMENT '登录密码',
  `comment_state` tinyint(1) DEFAULT '1' COMMENT '评论状态',
  `question_state` tinyint(1) DEFAULT '1' COMMENT '提问状态',
  `answer_state` tinyint(1) DEFAULT '1' COMMENT '解答状态',
  `user_type` tinyint(2) NOT NULL DEFAULT '1' COMMENT '用户类型，用户、教师',
  `sex` tinyint(2) NOT NULL COMMENT '用户性别',
  `province` varchar(15) DEFAULT NULL COMMENT '所在省',
  `city` varchar(25) DEFAULT NULL COMMENT '所在市',
  `area` varchar(50) DEFAULT NULL COMMENT '所在区',
  `email` varchar(50) NOT NULL COMMENT '用户邮箱',
  `phone` varchar(11) NOT NULL COMMENT '电话号码',
  `user_photo` varchar(200) DEFAULT NULL COMMENT '用户头像地址',
  `user_summary` text COMMENT '用户简介',
  `integral` int(11) NOT NULL DEFAULT '20' COMMENT '我的积分',
  `email_code` char(6) DEFAULT NULL COMMENT '邮箱验证码',
  `phone_verify` tinyint(1) NOT NULL DEFAULT '0' COMMENT '电话号码验证状态',
  `email_verify` tinyint(1) NOT NULL DEFAULT '0' COMMENT '邮箱地址验证状态',
  `login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='用户信息表';

#
# Data for table "as_user"
#

INSERT INTO `as_user` VALUES (1,'祝飞鸿','飞鸿先森',1,'e5a5e9c246620ac234a60f6dfebbf437',1,1,1,1,1,'440000','440100','440105','tjpu_feihong@163.com','15902225075',NULL,'我是开发者',20,'fyW9',0,1,NULL,'2019-03-02 22:39:01','2019-03-03 01:09:37',NULL),(2,'李明霞','lmx',1,'e5a5e9c246620ac234a60f6dfebbf437',1,1,1,1,2,'120000','120100','120111','1728691258@qq.com','18224927357',NULL,'',20,'D4p6',0,1,NULL,'2019-03-03 00:34:13','2019-03-03 01:11:30',NULL);

#
# Structure for table "as_teacher"
#

DROP TABLE IF EXISTS `as_teacher`;
CREATE TABLE `as_teacher` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `auth_state` tinyint(2) NOT NULL DEFAULT '0' COMMENT '教师用户的认证状态',
  `paperwork_photos` text COMMENT '认证时需要的图片',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `admin_id` int(11) DEFAULT NULL COMMENT '审核原因',
  `reason` varchar(500) DEFAULT NULL COMMENT '未通过原因',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_1` (`user_id`),
  KEY `FK_Reference_2` (`admin_id`),
  CONSTRAINT `FK_Reference_1` FOREIGN KEY (`user_id`) REFERENCES `as_user` (`id`),
  CONSTRAINT `FK_Reference_2` FOREIGN KEY (`admin_id`) REFERENCES `as_admin` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='教师认证表';

#
# Data for table "as_teacher"
#


#
# Structure for table "as_documents"
#

DROP TABLE IF EXISTS `as_documents`;
CREATE TABLE `as_documents` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) NOT NULL COMMENT '上传用户',
  `integral` int(11) NOT NULL COMMENT '下载积分',
  `title` varchar(80) NOT NULL COMMENT '资料标题',
  `summary` varchar(500) NOT NULL COMMENT '资料描述',
  `grade_id` int(11) NOT NULL COMMENT '适用年级',
  `subject_id` int(11) NOT NULL COMMENT '适用学科',
  `check_state` tinyint(2) NOT NULL DEFAULT '0' COMMENT '审核状态',
  `download_count` int(11) NOT NULL DEFAULT '0' COMMENT '下载次数',
  `reason` varchar(500) DEFAULT NULL COMMENT '不通过原因',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_10` (`grade_id`),
  KEY `FK_Reference_11` (`subject_id`),
  KEY `FK_Reference_14` (`user_id`),
  CONSTRAINT `FK_Reference_10` FOREIGN KEY (`grade_id`) REFERENCES `as_grade` (`id`),
  CONSTRAINT `FK_Reference_11` FOREIGN KEY (`subject_id`) REFERENCES `as_subject` (`id`),
  CONSTRAINT `FK_Reference_14` FOREIGN KEY (`user_id`) REFERENCES `as_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资料文件表';

#
# Data for table "as_documents"
#


#
# Structure for table "as_document_comments"
#

DROP TABLE IF EXISTS `as_document_comments`;
CREATE TABLE `as_document_comments` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) NOT NULL COMMENT '评论用户',
  `document_id` int(11) NOT NULL COMMENT '资料id',
  `score` int(11) NOT NULL COMMENT '评分',
  `comment_content` text COMMENT '评论内容',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_12` (`document_id`),
  KEY `FK_Reference_13` (`user_id`),
  CONSTRAINT `FK_Reference_12` FOREIGN KEY (`document_id`) REFERENCES `as_documents` (`id`),
  CONSTRAINT `FK_Reference_13` FOREIGN KEY (`user_id`) REFERENCES `as_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资料评论表';

#
# Data for table "as_document_comments"
#


#
# Structure for table "as_report"
#

DROP TABLE IF EXISTS `as_report`;
CREATE TABLE `as_report` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) NOT NULL COMMENT '举报人',
  `report_type` varchar(20) NOT NULL COMMENT '举报类型，提问和解答两种类型',
  `summary` text NOT NULL COMMENT '举报描述',
  `be_id` int(11) NOT NULL COMMENT '被举报id',
  `admin_id` int(11) DEFAULT NULL COMMENT '处理人员',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_18` (`user_id`),
  KEY `FK_Reference_19` (`admin_id`),
  CONSTRAINT `FK_Reference_18` FOREIGN KEY (`user_id`) REFERENCES `as_user` (`id`),
  CONSTRAINT `FK_Reference_19` FOREIGN KEY (`admin_id`) REFERENCES `as_admin` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='举报表';

#
# Data for table "as_report"
#


#
# Structure for table "as_questions"
#

DROP TABLE IF EXISTS `as_questions`;
CREATE TABLE `as_questions` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) NOT NULL COMMENT '提问用户',
  `invita_id` int(11) DEFAULT NULL COMMENT '邀约答题人',
  `question_summary` text COMMENT '题目描述',
  `question_photos` text COMMENT '题目图片',
  `integral` int(11) NOT NULL DEFAULT '0' COMMENT '悬赏积分',
  `solve_state` tinyint(2) NOT NULL DEFAULT '0' COMMENT '解决状态',
  `question_state` tinyint(2) NOT NULL DEFAULT '1' COMMENT '问题状态，关闭后不通知提问者，默认1打开',
  `subject_id` int(11) NOT NULL COMMENT '学科id',
  `grade_id` int(11) NOT NULL COMMENT '年级id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_17` (`invita_id`),
  KEY `FK_Reference_7` (`user_id`),
  KEY `FK_Reference_8` (`grade_id`),
  KEY `FK_Reference_9` (`subject_id`),
  CONSTRAINT `FK_Reference_17` FOREIGN KEY (`invita_id`) REFERENCES `as_user` (`id`),
  CONSTRAINT `FK_Reference_7` FOREIGN KEY (`user_id`) REFERENCES `as_user` (`id`),
  CONSTRAINT `FK_Reference_8` FOREIGN KEY (`grade_id`) REFERENCES `as_grade` (`id`),
  CONSTRAINT `FK_Reference_9` FOREIGN KEY (`subject_id`) REFERENCES `as_subject` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='提问表';

#
# Data for table "as_questions"
#


#
# Structure for table "as_feedback"
#

DROP TABLE IF EXISTS `as_feedback`;
CREATE TABLE `as_feedback` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) NOT NULL COMMENT '反馈用户',
  `question_summary` text COMMENT '问题描述',
  `admin_id` int(11) DEFAULT NULL COMMENT '处理人员',
  `question_photos` text COMMENT '反馈图片',
  `question_type` varchar(50) NOT NULL COMMENT '反馈类型',
  `question_state` varchar(50) NOT NULL COMMENT '处理结果',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_15` (`user_id`),
  KEY `FK_Reference_16` (`admin_id`),
  CONSTRAINT `FK_Reference_15` FOREIGN KEY (`user_id`) REFERENCES `as_user` (`id`),
  CONSTRAINT `FK_Reference_16` FOREIGN KEY (`admin_id`) REFERENCES `as_admin` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='反馈表';

#
# Data for table "as_feedback"
#


#
# Structure for table "as_exchange"
#

DROP TABLE IF EXISTS `as_exchange`;
CREATE TABLE `as_exchange` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) NOT NULL COMMENT '兑换用户',
  `gift_id` int(11) NOT NULL COMMENT '兑换物品',
  `user_name` varchar(20) NOT NULL COMMENT '收货人姓名',
  `user_phone` varchar(11) NOT NULL COMMENT '收货人电话',
  `address_province` varchar(20) NOT NULL COMMENT '收货省份',
  `address_city` varchar(20) NOT NULL COMMENT '收货市名',
  `address_district` varchar(20) NOT NULL COMMENT '收货区名',
  `address_detial` varchar(100) NOT NULL COMMENT '收货详细地址',
  `track_number` varchar(50) DEFAULT NULL COMMENT '快递单号',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_3` (`user_id`),
  KEY `FK_Reference_4` (`gift_id`),
  CONSTRAINT `FK_Reference_3` FOREIGN KEY (`user_id`) REFERENCES `as_user` (`id`),
  CONSTRAINT `FK_Reference_4` FOREIGN KEY (`gift_id`) REFERENCES `as_gift` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='积分兑换订单表';

#
# Data for table "as_exchange"
#


#
# Structure for table "as_answers"
#

DROP TABLE IF EXISTS `as_answers`;
CREATE TABLE `as_answers` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) NOT NULL COMMENT '解答用户',
  `question_id` int(11) NOT NULL COMMENT '题目id',
  `answer_summary` text COMMENT '解答描述',
  `answer_photos` text COMMENT '解答图片',
  `approval_num` int(11) NOT NULL DEFAULT '0' COMMENT '赞同人数',
  `oppose_num` int(11) NOT NULL DEFAULT '0' COMMENT '反对人数',
  `adoption_state` tinyint(1) NOT NULL DEFAULT '0' COMMENT '采纳状态，默认0未采纳',
  `score` tinyint(2) DEFAULT '0' COMMENT '提问者打分',
  `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_5` (`user_id`),
  KEY `FK_Reference_6` (`question_id`),
  CONSTRAINT `FK_Reference_5` FOREIGN KEY (`user_id`) REFERENCES `as_user` (`id`),
  CONSTRAINT `FK_Reference_6` FOREIGN KEY (`question_id`) REFERENCES `as_questions` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='解答表';

#
# Data for table "as_answers"
#


#
# Structure for table "as_address"
#

DROP TABLE IF EXISTS `as_address`;
CREATE TABLE `as_address` (
  `id` int(11) NOT NULL COMMENT 'id',
  `user_id` int(11) NOT NULL COMMENT '所属用户',
  `user_name` varchar(20) NOT NULL COMMENT '收货人姓名',
  `phone` varchar(11) NOT NULL COMMENT '收货人电话',
  `province` varchar(20) NOT NULL COMMENT '收货省份',
  `city` varchar(20) NOT NULL COMMENT '收货市名',
  `area` varchar(20) NOT NULL COMMENT '收货区名',
  `detial` varchar(100) NOT NULL COMMENT '收货详细地址',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_20` (`user_id`),
  CONSTRAINT `FK_Reference_20` FOREIGN KEY (`user_id`) REFERENCES `as_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='收货地址表';

#
# Data for table "as_address"
#

