/*
Navicat MySQL Data Transfer

Source Server         : 本地连接
Source Server Version : 80012
Source Host           : localhost:3306
Source Database       : db_dtb

Target Server Type    : MYSQL
Target Server Version : 80012
File Encoding         : 65001

Date: 2019-03-11 00:44:17
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for as_address
-- ----------------------------
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
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_20` (`user_id`),
  CONSTRAINT `FK_Reference_20` FOREIGN KEY (`user_id`) REFERENCES `as_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='收货地址表';

-- ----------------------------
-- Records of as_address
-- ----------------------------

-- ----------------------------
-- Table structure for as_admin
-- ----------------------------
DROP TABLE IF EXISTS `as_admin`;
CREATE TABLE `as_admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `admin_nick` varchar(20) NOT NULL COMMENT '管理员昵称',
  `admin_name` varchar(20) NOT NULL COMMENT '管理员姓名',
  `password` varchar(32) NOT NULL COMMENT '登录密码',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱地址',
  `phone` varchar(11) DEFAULT NULL COMMENT '电话号码',
  `login_state` tinyint(1) NOT NULL DEFAULT '1' COMMENT '登录状态',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='管理员表';

-- ----------------------------
-- Records of as_admin
-- ----------------------------

-- ----------------------------
-- Table structure for as_answers
-- ----------------------------
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
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_5` (`user_id`),
  KEY `FK_Reference_6` (`question_id`),
  CONSTRAINT `FK_Reference_5` FOREIGN KEY (`user_id`) REFERENCES `as_user` (`id`),
  CONSTRAINT `FK_Reference_6` FOREIGN KEY (`question_id`) REFERENCES `as_questions` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='解答表';

-- ----------------------------
-- Records of as_answers
-- ----------------------------
INSERT INTO `as_answers` VALUES ('1', '2', '1', '这是答案呀答案呀', '/static/home/images/user-head/2.jpg,/static/home/images/user-head/1.jpg,/static/home/images/user-head/1.jpg,/static/home/images/user-head/2.jpg', '0', '0', '0', '0', null, '2019-03-06 22:55:39', null);
INSERT INTO `as_answers` VALUES ('2', '3', '1', '55555555555555555', '/static/home/images/user-head/2.jpg,/static/home/images/user-head/1.jpg,/static/home/images/user-head/1.jpg,/static/home/images/user-head/2.jpg', '0', '0', '0', '0', null, '2019-03-07 00:22:14', null);

-- ----------------------------
-- Table structure for as_carousel
-- ----------------------------
DROP TABLE IF EXISTS `as_carousel`;
CREATE TABLE `as_carousel` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `photo_url` varchar(300) NOT NULL COMMENT '图片地址',
  `detail_url` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '详细页面',
  `use_state` tinyint(1) NOT NULL DEFAULT '1' COMMENT '启用状态，默认1启用',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='轮播图表';

-- ----------------------------
-- Records of as_carousel
-- ----------------------------
INSERT INTO `as_carousel` VALUES ('1', '/static/home/images/login1.jpg', null, '1', '2019-03-11 00:19:59', null, null);
INSERT INTO `as_carousel` VALUES ('2', '/static/home/images/login2.jpg', null, '1', '2019-03-11 00:20:14', null, null);
INSERT INTO `as_carousel` VALUES ('3', '/static/home/images/3.jpg', null, '1', '2019-03-11 00:20:21', null, null);
INSERT INTO `as_carousel` VALUES ('4', '/static/home/images/5.jpg', null, '1', '2019-03-11 00:20:28', null, null);

-- ----------------------------
-- Table structure for as_documents
-- ----------------------------
DROP TABLE IF EXISTS `as_documents`;
CREATE TABLE `as_documents` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) NOT NULL COMMENT '上传用户',
  `integral` int(11) NOT NULL COMMENT '下载积分',
  `title` varchar(80) NOT NULL COMMENT '资料标题',
  `summary` varchar(500) NOT NULL COMMENT '资料描述',
  `grade_id` int(11) NOT NULL COMMENT '适用年级',
  `subject_id` int(11) NOT NULL COMMENT '适用学科',
  `check_state` tinyint(2) NOT NULL DEFAULT '0' COMMENT '审核状态，默认【0未审核】，【1审核通过】，【2审核未通过】【3等待修改】【4等待二次审核】【5二次审核未通过，不在审核】',
  `download_count` int(11) NOT NULL DEFAULT '0' COMMENT '下载次数',
  `reason` varchar(500) DEFAULT NULL COMMENT '不通过原因',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
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

-- ----------------------------
-- Records of as_documents
-- ----------------------------

-- ----------------------------
-- Table structure for as_document_comments
-- ----------------------------
DROP TABLE IF EXISTS `as_document_comments`;
CREATE TABLE `as_document_comments` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) NOT NULL COMMENT '评论用户',
  `document_id` int(11) NOT NULL COMMENT '资料id',
  `score` int(11) NOT NULL COMMENT '评分',
  `comment_content` text COMMENT '评论内容',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_12` (`document_id`),
  KEY `FK_Reference_13` (`user_id`),
  CONSTRAINT `FK_Reference_12` FOREIGN KEY (`document_id`) REFERENCES `as_documents` (`id`),
  CONSTRAINT `FK_Reference_13` FOREIGN KEY (`user_id`) REFERENCES `as_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资料评论表';

-- ----------------------------
-- Records of as_document_comments
-- ----------------------------

-- ----------------------------
-- Table structure for as_exchange
-- ----------------------------
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
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_3` (`user_id`),
  KEY `FK_Reference_4` (`gift_id`),
  CONSTRAINT `FK_Reference_3` FOREIGN KEY (`user_id`) REFERENCES `as_user` (`id`),
  CONSTRAINT `FK_Reference_4` FOREIGN KEY (`gift_id`) REFERENCES `as_gift` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='积分兑换订单表';

-- ----------------------------
-- Records of as_exchange
-- ----------------------------

-- ----------------------------
-- Table structure for as_feedback
-- ----------------------------
DROP TABLE IF EXISTS `as_feedback`;
CREATE TABLE `as_feedback` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) NOT NULL COMMENT '反馈用户',
  `question_summary` text COMMENT '问题描述',
  `admin_id` int(11) DEFAULT NULL COMMENT '处理人员',
  `question_photos` text COMMENT '反馈图片',
  `question_type` varchar(50) NOT NULL COMMENT '反馈类型',
  `question_state` varchar(50) NOT NULL COMMENT '处理结果',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_15` (`user_id`),
  KEY `FK_Reference_16` (`admin_id`),
  CONSTRAINT `FK_Reference_15` FOREIGN KEY (`user_id`) REFERENCES `as_user` (`id`),
  CONSTRAINT `FK_Reference_16` FOREIGN KEY (`admin_id`) REFERENCES `as_admin` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='反馈表';

-- ----------------------------
-- Records of as_feedback
-- ----------------------------

-- ----------------------------
-- Table structure for as_gift
-- ----------------------------
DROP TABLE IF EXISTS `as_gift`;
CREATE TABLE `as_gift` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `gift_name` varchar(80) NOT NULL COMMENT '物品名称',
  `quantity` int(11) NOT NULL COMMENT '物品数量',
  `integral` int(11) NOT NULL COMMENT '所需积分',
  `photos` text COMMENT '图片描述',
  `summary` text COMMENT '物品简介',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='实物礼品表';

-- ----------------------------
-- Records of as_gift
-- ----------------------------

-- ----------------------------
-- Table structure for as_grade
-- ----------------------------
DROP TABLE IF EXISTS `as_grade`;
CREATE TABLE `as_grade` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `grade_name` varchar(50) NOT NULL COMMENT '年级名称',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='年级表';

-- ----------------------------
-- Records of as_grade
-- ----------------------------
INSERT INTO `as_grade` VALUES ('1', '小学', '2019-03-03 01:11:30', '2019-03-03 01:11:30', null);
INSERT INTO `as_grade` VALUES ('2', '初一', '2019-03-03 01:11:30', '2019-03-03 01:11:30', null);
INSERT INTO `as_grade` VALUES ('3', '初二', '2019-03-03 01:11:30', '2019-03-03 01:11:30', null);
INSERT INTO `as_grade` VALUES ('4', '初三', '2019-03-03 01:11:30', '2019-03-03 01:11:30', null);
INSERT INTO `as_grade` VALUES ('5', '高一', '2019-03-03 01:11:30', '2019-03-03 01:11:30', null);
INSERT INTO `as_grade` VALUES ('6', '高二', '2019-03-03 01:11:30', '2019-03-03 01:11:30', null);
INSERT INTO `as_grade` VALUES ('7', '高三', '2019-03-03 01:11:30', '2019-03-03 01:11:30', null);

-- ----------------------------
-- Table structure for as_questions
-- ----------------------------
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
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_17` (`invita_id`),
  KEY `FK_Reference_7` (`user_id`),
  KEY `FK_Reference_8` (`grade_id`),
  KEY `FK_Reference_9` (`subject_id`),
  FULLTEXT KEY `fulltxt_question_summary` (`question_summary`),
  CONSTRAINT `FK_Reference_17` FOREIGN KEY (`invita_id`) REFERENCES `as_user` (`id`),
  CONSTRAINT `FK_Reference_7` FOREIGN KEY (`user_id`) REFERENCES `as_user` (`id`),
  CONSTRAINT `FK_Reference_8` FOREIGN KEY (`grade_id`) REFERENCES `as_grade` (`id`),
  CONSTRAINT `FK_Reference_9` FOREIGN KEY (`subject_id`) REFERENCES `as_subject` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='提问表';

-- ----------------------------
-- Records of as_questions
-- ----------------------------
INSERT INTO `as_questions` VALUES ('1', '1', '2', '下列实验室制取气体的设计中，可行的是？ A．加热氯酸钾和少量二氧化锰的混合物制取氧 B．盐酸跟氧化钙反应制取二氧化碳 C．稀硫酸跟石灰石反应制取二氧化碳 D．稀硫酸跟铜反应制取氢气', '/static/home/images/user-head/2.jpg,/static/home/images/user-head/1.jpg,/static/home/images/user-head/1.jpg,/static/home/images/user-head/2.jpg', '26', '0', '1', '2', '1', '2019-03-03 17:42:34', null, null);
INSERT INTO `as_questions` VALUES ('2', '2', '1', '甲乙两个小朋友各有一袋糖，没带糖，不到20颗，如果甲给乙一定数量的糖后，甲的糖是乙的糖的2倍，如果乙给甲，同样数量的糖后，甲的糖就是乙的糖颗数的三倍。那么甲乙两个小朋友共有多少颗糖？', null, '0', '0', '1', '3', '2', '2019-03-03 17:42:34', null, null);
INSERT INTO `as_questions` VALUES ('3', '1', '1', '关于生态系统稳定性的错误叙述是？？ A．不同生态系统的抵抗力稳定性和恢复力稳定性是不同的 B．人工生态系统的抵抗力稳定性都比自然生态系统低 C．抵抗力稳定性高的生态系统，其恢复力稳定性往往较低 D．生态系统的成分较少、营养结构简单，恢复力稳定性较高', '/static/home/images/user-head/2.jpg,/static/home/images/user-head/1.jpg,/static/home/images/user-head/1.jpg,/static/home/images/user-head/2.jpg', '53', '0', '1', '1', '5', '2019-03-03 17:42:34', null, null);
INSERT INTO `as_questions` VALUES ('4', '1', '2', '下列关于植物组织培养的叙述中，错误的是？ A．培养基中添加蔗糖的目的是提供营养和调节渗透压 B．培养基中的生长素和细胞分裂素影响愈伤组织的生长和分化 C．离体器官或组织的细胞都必须通过脱分化才能形成愈伤组织 D．同一株绿色开花植物不同部位的细胞经培养获得的愈伤组织基因相同', '/static/home/images/user-head/2.jpg,/static/home/images/user-head/1.jpg,/static/home/images/user-head/1.jpg,/static/home/images/user-head/2.jpg', '0', '0', '1', '1', '2', '2019-03-03 17:42:34', null, null);
INSERT INTO `as_questions` VALUES ('5', '1', '2', '在两个石棉网上分别放两堆白砂糖，一份混少量香烟灰，一份不混香烟灰，分别用火柴点燃。结果，未混香烟灰的蔗糖不能燃烧；混有香烟灰的蔗糖很快燃烧起来，对此解释合理的是： ？ A．香烟灰在加热时，释放出氧气，促进了蔗糖的燃烧 B．香烟灰在蔗糖燃烧中起了催化剂的作用 C．香烟灰的存在改变了蔗糖和氧气反应的机理，降低了蔗糖的燃点 D．香烟灰在用火柴点燃时放出热量，使蔗糖燃烧起来', '/static/home/images/user-head/2.jpg,/static/home/images/user-head/1.jpg,/static/home/images/user-head/1.jpg', '0', '0', '1', '5', '5', '2019-03-03 17:42:34', null, null);
INSERT INTO `as_questions` VALUES ('6', '2', '2', '关于传统发酵技术的应用正确的是？ A．果醋的制作需用醋酸菌，制作时需要隔绝空气 B．果酒制作中的酵母菌来自葡萄，所以葡萄不需清洗 C．制作果酒、果醋及乳腐的最适温度各不相同 D．腐乳的制作要靠毛霉等微生物分解蛋白质和脂肪，装瓶后不需严格密封', '/static/home/images/user-head/2.jpg,/static/home/images/user-head/1.jpg,/static/home/images/user-head/1.jpg,/static/home/images/user-head/2.jpg', '0', '0', '1', '2', '4', '2019-03-03 17:42:34', null, null);
INSERT INTO `as_questions` VALUES ('7', '2', '1', '．豆腐是生活中的少不了的菜肴，制备豆腐的主要流程是：将浸泡过一段时间的大豆研磨过滤并将所得滤液（即豆浆）加热至沸腾，然后再向其中加入适量的石膏（2CaSO4•H2O）溶液，很快豆浆就发生凝聚，再放入相应的工具中成型，就得到各种商品豆腐，下列说法不正确的是? A．豆浆属于高分子化合物溶液 B．加入石膏的目的是使蛋白质发生聚沉 C．加热至沸腾的目的是将豆浆煮熟 D．重金属中毒时及时大量饮用豆浆可起到解毒的作用', '/static/home/images/user-head/2.jpg,/static/home/images/user-head/2.jpg', '0', '0', '1', '2', '3', '2019-03-03 17:42:34', null, null);
INSERT INTO `as_questions` VALUES ('8', '1', '2', '45455354345', '/static/home/images/user-head/2.jpg,/static/home/images/user-head/1.jpg,/static/home/images/user-head/1.jpg,/static/home/images/user-head/2.jpg', '0', '0', '1', '2', '5', '2019-03-03 17:42:34', null, null);
INSERT INTO `as_questions` VALUES ('9', '2', '2', '阿斯匹林的结构简式为 CH3―C―O―C6H4―C―OH某学生推测其具有下列性质, 其中推测错误的是？ A.能发生银镜反应 B.能发生水解反应 C.有酸味 D.能发生酯化反应9.', '/static/home/images/user-head/2.jpg,/static/home/images/user-head/1.jpg,/static/home/images/user-head/1.jpg,/static/home/images/user-head/2.jpg', '0', '0', '1', '1', '1', '2019-03-03 17:42:34', null, null);
INSERT INTO `as_questions` VALUES ('10', '2', '2', '下列说法中正确的是 ？ A．汽油燃烧时将全部的化学能转化为热能 B．向饱和AgCl溶液中加入盐酸，Kap变大 C．若存在简单阴离子R2-，则R一定属于VIA族 D．最外层电子数较少的金属元素，一定比最外层电子数较它多的金属元素活泼性强', '/static/home/images/user-head/1.jpg,/static/home/images/user-head/2.jpg', '0', '0', '1', '1', '6', '2019-03-03 17:42:34', null, null);
INSERT INTO `as_questions` VALUES ('11', '1', '1', '在电场中，沿着电场线的方向？ A．场强一定减小 B．电势一定降低 C．电荷的电势能一定减小 D．移动电荷电场力一定做正功', '/static/home/images/user-head/2.jpg,/static/home/images/user-head/1.jpg,/static/home/images/user-head/1.jpg,/static/home/images/user-head/2.jpg', '0', '0', '1', '4', '5', '2019-03-03 17:42:34', null, null);

-- ----------------------------
-- Table structure for as_report
-- ----------------------------
DROP TABLE IF EXISTS `as_report`;
CREATE TABLE `as_report` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) NOT NULL COMMENT '举报人',
  `report_type` varchar(20) NOT NULL COMMENT '举报类型，提问和解答两种类型',
  `summary` text NOT NULL COMMENT '举报描述',
  `be_id` int(11) NOT NULL COMMENT '被举报id',
  `admin_id` int(11) DEFAULT NULL COMMENT '处理人员',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_18` (`user_id`),
  KEY `FK_Reference_19` (`admin_id`),
  CONSTRAINT `FK_Reference_18` FOREIGN KEY (`user_id`) REFERENCES `as_user` (`id`),
  CONSTRAINT `FK_Reference_19` FOREIGN KEY (`admin_id`) REFERENCES `as_admin` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='举报表';

-- ----------------------------
-- Records of as_report
-- ----------------------------

-- ----------------------------
-- Table structure for as_subject
-- ----------------------------
DROP TABLE IF EXISTS `as_subject`;
CREATE TABLE `as_subject` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `subject_name` varchar(50) NOT NULL COMMENT '学科名称',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='学科表';

-- ----------------------------
-- Records of as_subject
-- ----------------------------
INSERT INTO `as_subject` VALUES ('1', '语文', '2019-03-03 01:11:30', '2019-03-03 01:11:30', null);
INSERT INTO `as_subject` VALUES ('2', '数学', '2019-03-03 01:11:30', '2019-03-03 01:11:30', null);
INSERT INTO `as_subject` VALUES ('3', '英语', '2019-03-03 01:11:30', '2019-03-03 01:11:30', null);
INSERT INTO `as_subject` VALUES ('4', '历史', '2019-03-03 01:11:30', '2019-03-03 01:11:30', null);
INSERT INTO `as_subject` VALUES ('5', '地理', '2019-03-03 01:11:30', '2019-03-03 01:11:30', null);
INSERT INTO `as_subject` VALUES ('6', '政治', '2019-03-03 01:11:30', '2019-03-03 01:11:30', null);
INSERT INTO `as_subject` VALUES ('7', '生物', '2019-03-03 01:11:30', '2019-03-03 01:11:30', null);
INSERT INTO `as_subject` VALUES ('8', '物理', '2019-03-03 01:11:30', '2019-03-03 01:11:30', null);
INSERT INTO `as_subject` VALUES ('9', '化学', '2019-03-03 01:11:30', '2019-03-03 01:11:30', null);

-- ----------------------------
-- Table structure for as_teacher
-- ----------------------------
DROP TABLE IF EXISTS `as_teacher`;
CREATE TABLE `as_teacher` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `auth_state` tinyint(2) NOT NULL DEFAULT '0' COMMENT '教师用户的认证状态',
  `paperwork_photos` text COMMENT '认证时需要的图片',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `admin_id` int(11) DEFAULT NULL COMMENT '审核原因',
  `reason` varchar(500) DEFAULT NULL COMMENT '未通过原因',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `FK_Reference_1` (`user_id`),
  KEY `FK_Reference_2` (`admin_id`),
  CONSTRAINT `FK_Reference_1` FOREIGN KEY (`user_id`) REFERENCES `as_user` (`id`),
  CONSTRAINT `FK_Reference_2` FOREIGN KEY (`admin_id`) REFERENCES `as_admin` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='教师认证表';

-- ----------------------------
-- Records of as_teacher
-- ----------------------------

-- ----------------------------
-- Table structure for as_user
-- ----------------------------
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
  `user_type` tinyint(2) NOT NULL DEFAULT '1' COMMENT '用户类型，1用户、2教师',
  `sex` tinyint(2) NOT NULL COMMENT '用户性别',
  `province` varchar(15) DEFAULT NULL COMMENT '所在省',
  `city` varchar(25) DEFAULT NULL COMMENT '所在市',
  `area` varchar(50) DEFAULT NULL COMMENT '所在区',
  `email` varchar(50) NOT NULL COMMENT '用户邮箱',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '电话号码',
  `user_photo` varchar(200) DEFAULT '/static/home/images/user-head/1.jpg' COMMENT '用户头像地址',
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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='用户信息表';

-- ----------------------------
-- Records of as_user
-- ----------------------------
INSERT INTO `as_user` VALUES ('1', '祝飞鸿', '飞鸿先森', '1', 'e5a5e9c246620ac234a60f6dfebbf437', '1', '1', '1', '1', '1', '440000', '440100', '440105', 'tjpu_feihong@163.com', '15902225075', '/static/home/images/user-head/1.jpg', null, '20', 'fyW9', '0', '1', null, '2019-03-02 22:39:01', '2019-03-03 20:25:56', null);
INSERT INTO `as_user` VALUES ('2', '李明霞', 'lmx', '1', 'e5a5e9c246620ac234a60f6dfebbf437', '1', '1', '1', '1', '2', '120000', '120100', '120111', '1728691258@qq.com', '18224927357', '/static/home/images/user-head/2.jpg', '如果我们需要对读取的数据进行排序，我们就可以使用 MySQL 的 ORDER BY 子句来设定你想按哪个字段哪种方式来进行排序，再返回搜索结果。如果我们需要对读取的数据进行排序，我们就可以使用 MySQL 的 ORDER BY 子句来设定你想按哪个字段哪种方式来进行排序，再返回搜索结果。如果我们需要对读取的数据进行排序，我们就可以使用 MySQL 的 ORDER BY 子句来设定你想按哪个字段哪种方式来进行排序，再返回搜索结果。如果我们需要对读取的数据进行排序，我们就可以使用 MySQL 的 ORDER BY 子句来设定你想按哪个字段哪种方式来进行排序，再返回搜索结果。', '20', 'D4p6', '0', '1', null, '2019-03-03 00:34:13', '2019-03-03 20:25:56', null);
INSERT INTO `as_user` VALUES ('3', '莉莉安', '宋胖子', '1', 'e5a5e9c246620ac234a60f6dfebbf437', '1', '1', '1', '2', '2', '110000', '110100', '110104', 'tjpu_zfh@163.com', '18224927357', '/static/home/images/user-head/3.jpg', '她的名字叫，们就可以使用 MySQL 的 ORDER BY 子句来设定你想按哪个字段哪种方式来进行排序，再返回搜索结果。如果我们需要对读取的数据进行排序，我们就可以使用 MySQL 的 ORDER BY 子句来设定你想按哪个字段哪种方式来进行排序，再返回搜索结们就可以使用 MySQL 的 ORDER BY 子句来设定你想按哪个字段哪种方式来进行排序，再返回搜索结果。如果我们需要对读取的数据进行排序，我们就可以使用 MySQL 的 ORDER BY 子句来设定你想按哪个字段哪种方式来进行排序，再返回搜索结莉莉安', '20', 'K2s3', '0', '1', null, '2019-03-09 01:03:51', '2019-03-09 01:06:39', null);
INSERT INTO `as_user` VALUES ('4', '董小姐1号', '宋胖子', '1', 'e5a5e9c246620ac234a60f6dfebbf437', '1', '1', '1', '1', '2', '110000', '110100', '110104', 'tjpu_zfh@163.com', '18224927357', '/static/home/images/user-head/4.jpg', '她的名字叫，莉莉安', '20', 'K2s3', '0', '1', null, '2019-03-09 01:03:51', '2019-03-09 01:06:39', null);
INSERT INTO `as_user` VALUES ('5', ' 董小姐2号', '宋胖子', '1', 'e5a5e9c246620ac234a60f6dfebbf437', '1', '1', '1', '2', '2', '110000', '110100', '110104', 'tjpu_zfh@163.com', '18224927357', '/static/home/images/user-head/1.jpg', '她的名字叫，们就可以使用 MySQL 的 ORDER BY 子句来设定你想按哪个字段哪种方式来进行排序，再返回搜索结果。如果我们需要对读取的数据进行排序，我们就可以使用 MySQL 的 ORDER BY 子句来设定你想按哪个字段哪种方式来进行排序，再返回搜索结们就可以使用 MySQL 的 ORDER BY 子句来设定你想按哪个字段哪种方式来进行排序，再返回搜索结果。如果我们需要对读取的数据进行排序，我们就可以使用 MySQL 的 ORDER BY 子句来设定你想按哪个字段哪种方式来进行排序，再返回搜索结们就可以使用 MySQL 的 ORDER BY 子句来设定你想按哪个字段哪种方式来进行排序，再返回搜索结果。如果我们需要对读取的数据进行排序，我们就可以使用 MySQL 的 ORDER BY 子句来设定你想按哪个字段哪种方式来进行排序，再返回搜索结们就可以使用 MySQL 的 ORDER BY 子句来设定你想按哪个字段哪种方式来进行排序，再返回搜索结果。如果我们需要对读取的数据进行排序，我们就可以使用 MySQL 的 ORDER BY 子句来设定你想按哪个字段哪种方式来进行排序，再返回搜索结们就可以使用 MySQL 的 ORDER BY 子句来设定你想按哪个字段哪种方式来进行排序，再返回搜索结果。如果我们需要对读取的数据进行排序，我们就可以使用 MySQL 的 ORDER BY 子句来设定你想按哪个字段哪种方式来进行排序，再返回搜索结们就可以使用 MySQL 的 ORDER BY 子句来设定你想按哪个字段哪种方式来进行排序，再返回搜索结果。如果我们需要对读取的数据进行排序，我们就可以使用 MySQL 的 ORDER BY 子句来设定你想按哪个字段哪种方式来进行排序，再返回搜索结莉莉安', '20', 'K2s3', '0', '1', null, '2019-03-09 01:03:51', '2019-03-09 01:06:39', null);
INSERT INTO `as_user` VALUES ('6', '董小姐3号', '宋胖子', '1', 'e5a5e9c246620ac234a60f6dfebbf437', '1', '1', '1', '1', '2', '110000', '110100', '110104', 'tjpu_zfh@163.com', '18224927357', '/static/home/images/user-head/2.jpg', '她的名字叫，莉莉安', '20', 'K2s3', '0', '1', null, '2019-03-09 01:03:51', '2019-03-09 01:06:39', null);
INSERT INTO `as_user` VALUES ('7', '董小姐4号', '宋胖子', '1', 'e5a5e9c246620ac234a60f6dfebbf437', '1', '1', '1', '1', '2', '110000', '110100', '110104', 'tjpu_zfh@163.com', '18224927357', '/static/home/images/user-head/3.jpg', '她的名字叫们就可以使用 MySQL 的 ORDER BY 子句来设定你想按哪个字段哪种方式来进行排序，再返回搜索结果。如果我们需要对读取的数据进行排序，我们就可以使用 MySQL 的 ORDER BY 子句来设定你想按哪个字段哪种方式来进行排序，再返回搜索结们就可以使用 MySQL 的 ORDER BY 子句来设定你想按哪个字段哪种方式来进行排序，再返回搜索结果。如果我们需要对读取的数据进行排序，我们就可以使用 MySQL 的 ORDER BY 子句来设定你想按哪个字段哪种方式来进行排序，再返回搜索结们就可以使用 MySQL 的 ORDER BY 子句来设定你想按哪个字段哪种方式来进行排序，再返回搜索结果。如果我们需要对读取的数据进行排序，我们就可以使用 MySQL 的 ORDER BY 子句来设定你想按哪个字段哪种方式来进行排序，再返回搜索结们就可以使用 MySQL 的 ORDER BY 子句来设定你想按哪个字段哪种方式来进行排序，再返回搜索结果。如果我们需要对读取的数据进行排序，我们就可以使用 MySQL 的 ORDER BY 子句来设定你想按哪个字段哪种方式来进行排序，再返回搜索结们就可以使用 MySQL 的 ORDER BY 子句来设定你想按哪个字段哪种方式来进行排序，再返回搜索结果。如果我们需要对读取的数据进行排序，我们就可以使用 MySQL 的 ORDER BY 子句来设定你想按哪个字段哪种方式来进行排序，再返回搜索结们就可以使用 MySQL 的 ORDER BY 子句来设定你想按哪个字段哪种方式来进行排序，再返回搜索结果。如果我们需要对读取的数据进行排序，我们就可以使用 MySQL 的 ORDER BY 子句来设定你想按哪个字段哪种方式来进行排序，再返回搜索结们就可以使用 MySQL 的 ORDER BY 子句来设定你想按哪个字段哪种方式来进行排序，再返回搜索结果。如果我们需要对读取的数据进行排序，我们就可以使用 MySQL 的 ORDER BY 子句来设定你想按哪个字段哪种方式来进行排序，再返回搜索结，莉莉安', '20', 'K2s3', '0', '1', null, '2019-03-09 01:03:51', '2019-03-09 01:06:39', null);
INSERT INTO `as_user` VALUES ('8', '董小姐5号', '宋胖子', '1', 'e5a5e9c246620ac234a60f6dfebbf437', '1', '1', '1', '2', '2', '110000', '110100', '110104', 'tjpu_zfh@163.com', '18224927357', '/static/home/images/user-head/4.jpg', '她的名字叫，莉莉安', '20', 'K2s3', '0', '1', null, '2019-03-09 01:03:51', '2019-03-09 01:06:39', null);
INSERT INTO `as_user` VALUES ('9', '董小姐6号', '宋胖子', '1', 'e5a5e9c246620ac234a60f6dfebbf437', '1', '1', '1', '1', '2', '110000', '110100', '110104', 'tjpu_zfh@163.com', '18224927357', '/static/home/images/user-head/3.jpg', '她的名字叫，莉莉安', '20', 'K2s3', '0', '1', null, '2019-03-09 01:03:51', '2019-03-09 01:06:39', null);
INSERT INTO `as_user` VALUES ('10', '董小姐7号', '宋胖子', '1', 'e5a5e9c246620ac234a60f6dfebbf437', '1', '1', '1', '2', '2', '110000', '110100', '110104', 'tjpu_zfh@163.com', '18224927357', '/static/home/images/user-head/1.jpg', '她的名字叫，莉莉安', '20', 'K2s3', '0', '1', null, '2019-03-09 01:03:51', '2019-03-09 01:06:39', null);
INSERT INTO `as_user` VALUES ('11', '董小姐8号', '宋胖子', '1', 'e5a5e9c246620ac234a60f6dfebbf437', '1', '1', '1', '2', '2', '110000', '110100', '110104', 'tjpu_zfh@163.com', '18224927357', '/static/home/images/user-head/2.jpg', '她的名字叫，莉莉安', '20', 'K2s3', '0', '1', null, '2019-03-09 01:03:51', '2019-03-09 01:06:39', null);
INSERT INTO `as_user` VALUES ('12', '董小姐9号', '宋胖子', '1', 'e5a5e9c246620ac234a60f6dfebbf437', '1', '1', '1', '1', '2', '110000', '110100', '110104', 'tjpu_zfh@163.com', '18224927357', '/static/home/images/user-head/3.jpg', '她的名字叫，莉莉安', '20', 'K2s3', '0', '1', null, '2019-03-09 01:03:51', '2019-03-09 01:06:39', null);
