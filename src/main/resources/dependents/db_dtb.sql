/*
Navicat MySQL Data Transfer

Source Server         : 本地连接
Source Server Version : 80012
Source Host           : localhost:3306
Source Database       : db_dtb

Target Server Type    : MYSQL
Target Server Version : 80012
File Encoding         : 65001

Date: 2019-03-19 00:29:11
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='管理员表';

-- ----------------------------
-- Records of as_admin
-- ----------------------------
INSERT INTO `as_admin` VALUES ('1', 'admin', 'admin', '123456', 'dtb_admin@163.com', '15902225075', '1', '2019-03-16 17:32:16', null, null);

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='解答表';

-- ----------------------------
-- Records of as_answers
-- ----------------------------
INSERT INTO `as_answers` VALUES ('1', '2', '1', '这是答案呀答案呀', '/static/home/images/user-head/2.jpg,/static/home/images/user-head/1.jpg,/static/home/images/user-head/1.jpg,/static/home/images/user-head/2.jpg', '0', '0', '0', '0', null, '2019-03-06 22:55:39', null);
INSERT INTO `as_answers` VALUES ('2', '3', '1', '55555555555555555', '/static/home/images/user-head/2.jpg,/static/home/images/user-head/1.jpg,/static/home/images/user-head/1.jpg,/static/home/images/user-head/2.jpg', '0', '0', '0', '0', null, '2019-03-07 00:22:14', null);
INSERT INTO `as_answers` VALUES ('3', '1', '17', '就是这样做的啦', '/static/upload/images/answer/2019/3/answer_20190316121027158663000.png', '0', '0', '0', '0', null, '2019-03-16 12:10:27', null);
INSERT INTO `as_answers` VALUES ('4', '1', '19', '66666666666666666666666', null, '0', '0', '0', '0', null, '2019-03-16 13:50:42', null);
INSERT INTO `as_answers` VALUES ('5', '1', '19', '感动啊感动', null, '0', '0', '0', '0', null, '2019-03-16 13:55:18', null);

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
  `admin_id` int(11) DEFAULT NULL COMMENT '审核管理员id',
  `integral` int(11) NOT NULL COMMENT '下载积分',
  `title` varchar(80) NOT NULL COMMENT '资料标题',
  `summary` varchar(500) NOT NULL COMMENT '资料描述',
  `document_type` tinyint(2) NOT NULL COMMENT '文档类型，1题目文档，2复习文档，3杂项文档',
  `grade_id` int(11) NOT NULL COMMENT '适用年级',
  `subject_id` int(11) NOT NULL COMMENT '适用学科',
  `check_state` tinyint(2) NOT NULL DEFAULT '0' COMMENT '审核状态，默认【0未审核】，【1审核通过】，【2审核未通过】【3等待修改】【4等待二次审核】【5二次审核未通过，不在审核】',
  `download_count` int(11) NOT NULL DEFAULT '0' COMMENT '下载次数',
  `file_path` varchar(600) NOT NULL COMMENT '文件路径',
  `reason` varchar(500) DEFAULT NULL COMMENT '不通过原因',
  `score` float(4,2) DEFAULT NULL COMMENT '用户评分',
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='资料文件表';

-- ----------------------------
-- Records of as_documents
-- ----------------------------
INSERT INTO `as_documents` VALUES ('1', '1', null, '5', '新人教版小学数学三年级(上册)期中试卷1', '新人教版小学数学三年级(上册)期中试卷，试卷题目，不含答案', '1', '1', '2', '0', '0', '/static/upload/document/user-upload/2019/3/document_1_201903182356322377085.doc', null, null, '2019-03-18 23:56:32', null, null);

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
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COMMENT='提问表';

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
INSERT INTO `as_questions` VALUES ('12', '1', '3', '题目如下图', '/static/upload/images/question/2019/3/question_2019031123583888436390.png', '66', '0', '1', '3', '2', '2019-03-11 23:58:38', null, null);
INSERT INTO `as_questions` VALUES ('13', '1', '5', '学校合唱队的人数在40～50之间，男生与女生的人数比是5:7，这个合唱队有（    ）人。', '/static/upload/images/question/2019/3/question_2019031501002348294919.png', '1', '0', '1', '2', '1', '2019-03-15 01:00:23', null, null);
INSERT INTO `as_questions` VALUES ('14', '1', '3', '脱式计算，如下图', '/static/upload/images/question/2019/3/question_20190315011840848415221.png', '10', '0', '1', '2', '1', '2019-03-15 01:18:41', null, null);
INSERT INTO `as_questions` VALUES ('15', '1', '3', '脱式计算，如下图', '/static/upload/images/question/2019/3/question_20190315011840848415221.png', '10', '0', '1', '2', '1', '2019-03-15 01:19:52', null, null);
INSERT INTO `as_questions` VALUES ('16', '1', '7', '如图，在直四棱柱ABCD-A1B1C1D1中，底面ABCD为等腰梯形，AB//CD，AB=4, BC=CD=2,  AA1=2,  E、E1分别是棱AD、AA1的中点. \n（1） 设F是棱AB的中点,证明：直线EE1//平面FCC1； \n（2） 证明:平面D1AC⊥平面BB1C1C.', '/static/upload/images/question/2019/3/question_20190315201029232161392.png', '5', '0', '1', '2', '5', '2019-03-15 20:10:29', null, null);
INSERT INTO `as_questions` VALUES ('17', '1', '9', '如图1，在Rt△ABC中，∠C=90°，D，E分别为AC，AB的中点，点F为线段CD上的一点，将△ADE沿DE折起到△A\n1DE的位置，使A1F⊥CD，如图2． （Ⅰ）求证：A1F⊥BE；  （Ⅱ）线段A1B上是否存在点Q，使A1C⊥平面  DEQ？说明理由．', '/static/upload/images/question/2019/3/question_20190316120426759826136.png', '0', '0', '1', '2', '5', '2019-03-16 12:06:02', null, null);
INSERT INTO `as_questions` VALUES ('18', '1', null, '如图1，在Rt△ABC中，∠C=90°，D，E分别为AC，AB的中点，点F为线段CD上的一点，将△ADE沿DE折起到△A\n1DE的位置，使A1F⊥CD，如图2． （Ⅰ）求证：A1F⊥BE；  （Ⅱ）线段A1B上是否存在点Q，使A1C⊥平面  DEQ？说明理由．', '/static/upload/images/question/2019/3/question_20190316120700468331731.png', '0', '0', '1', '2', '5', '2019-03-16 12:07:00', null, null);
INSERT INTO `as_questions` VALUES ('19', '1', null, '每天天刚亮时，我母亲便把我喊醒，叫我披衣坐起。我从不知道她醒来坐了多久了，她看我清醒了，便对我说昨天我做错了什么事，说错了什么话，要我认错，要我用功读书，有时侯她对我说父亲的种种好处，她说：“你总要踏上你老子的脚步。我一生只晓得这一个完全的人，你要学他，不要跌他的股，”（跌股便是丢脸聘书丑）她说到伤心处，往往掉下泪来，到天大明时，她才把我的衣服穿好，催我去上早学。学堂门上的锁匙放在先生家里；我先到学堂门口一望，便跑到先生家里去敲门。先生家里有人把锁匙从门缝里递出来，我拿了跑回去，开了门，坐下念生书，十天之中，总有八、九天我是第一个去开学堂门的。等到先生来了，我背了生书，才回家吃早饭。\n\n我母亲管束我最严，她是慈爱母兼任严父。但她从来不在别人面前骂我一句，打我一下。我做错了事，她只对我一望，我看见了她的严厉眼光，便吓住了，犯的事小，她等到第二天早晨我睡醒时才教训我。犯的事大，她等人静时，关了房门，先责备我，然后行罚，或罚跪，或拧我的肉，无论息样重罚，总不许我哭出声音来，她教训儿子不是借此出气叫别人听的。\n\n有一个初秋的傍晚，我吃了晚饭，在门口玩，身上只穿着一件单背心，这时侯我母亲的妹子玉英姨母在我家住，她怕我冷了，拿了一件小衫出来叫我穿上。我不肯穿，她说：“穿上吧，凉了。”我随口回答：“娘（凉）什么！老子都不老子呀。”我刚说了这句话，一抬头，看见母亲从家里走出，我赶快把小衫穿上。但她已听见这句轻薄的话了。晚上人静后，她罚我跪下，重重的责罚了一顿。她说：“你没了老子，是多么得意的事！好用来说嘴！”她气得坐着发抖，也不许我上床去睡。这是我的严师，我的慈母。我母亲待人最仁慈，最温和，从来没有一句伤人感情的话；但她有时侯也很有刚气，不受一点人格上的侮辱。我家五叔是个无正业的浪人，有一天在烟馆里发牢骚，说我母亲家中有事请某人帮忙，大概总有什么好处给他。这句话传到了我母亲耳杂里，她气得大哭，请了几位本家来，把五叔喊来，她当面质问他给了某人什么好处。直到五叔当众认错赔罪，她才罢休。我在我母亲的教训之下住了九年，受了她的极大极深的影响。我十四岁（其实只有十二零二、三个月）便离开她了，在这广漠的人海里独扑克混了二十多年，没有一个人管束过我。如果我学得了一丝一毫的好脾气，如果我学得了一点点待人接物的和气，如果我能宽恕人，体谅人——我都得感谢我的慈母。', '/static/upload/images/question/2019/3/question_20190316133018737505830.jpg', '0', '0', '1', '1', '3', '2019-03-16 13:30:18', null, null);
INSERT INTO `as_questions` VALUES ('20', '1', null, '你是不是撒', null, '0', '0', '1', '4', '2', '2019-03-16 14:18:01', null, null);

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
  `auth_state` tinyint(2) NOT NULL DEFAULT '0' COMMENT '教师用户的认证状态，0待审核，1审核通过，2待修改，3审核不通过',
  `paperwork_photos` text COMMENT '认证时需要的图片',
  `subject_id` int(11) NOT NULL COMMENT '所授学科',
  `user_id` int(11) DEFAULT NULL COMMENT '用户id',
  `admin_id` int(11) DEFAULT NULL COMMENT '审核管理员',
  `reason` varchar(500) DEFAULT NULL COMMENT '未通过原因',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `fk_user_id` (`user_id`),
  KEY `fk_admin_id` (`admin_id`),
  KEY `fk_subject_id` (`subject_id`),
  CONSTRAINT `fk_admin_id` FOREIGN KEY (`admin_id`) REFERENCES `as_admin` (`id`),
  CONSTRAINT `fk_subject_id` FOREIGN KEY (`subject_id`) REFERENCES `as_subject` (`id`),
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `as_user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='教师认证表';

-- ----------------------------
-- Records of as_teacher
-- ----------------------------
INSERT INTO `as_teacher` VALUES ('3', '1', '/static/upload/images/teacher/2019/3/teacher_idcard1_20190317164357435850613.jpg,/static/upload/images/teacher/2019/3/teacher_idcard2_20190317164357524704319.jpg,/static/upload/images/teacher/2019/3/teacher_certificate_20190317164357544139465.jpg', '2', '1', '1', null, '2019-03-17 16:43:57', null, null);
INSERT INTO `as_teacher` VALUES ('4', '0', '/static/upload/images/teacher/2019/3/teacher_idcard1_20190317202823163131727.jpg,/static/upload/images/teacher/2019/3/teacher_idcard2_20190317202823172613951.jpg,/static/upload/images/teacher/2019/3/teacher_certificate_20190317202823181726372.jpg', '2', '2', null, null, '2019-03-17 20:28:23', null, null);

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
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COMMENT='用户信息表';

-- ----------------------------
-- Records of as_user
-- ----------------------------
INSERT INTO `as_user` VALUES ('1', '祝飞鸿', '飞鸿先森', '1', 'e5a5e9c246620ac234a60f6dfebbf437', '1', '1', '1', '2', '1', '440000', '440100', '440105', 'tjpu_feihong@163.com', '15902225075', '/static/home/images/user-head/1.jpg', null, '25', 'fyW9', '0', '1', '2019-03-09 01:03:51', '2019-03-02 22:39:01', '2019-03-03 20:25:56', null);
INSERT INTO `as_user` VALUES ('2', '李明霞', 'lmx', '1', 'e5a5e9c246620ac234a60f6dfebbf437', '1', '1', '1', '1', '2', '120000', '120100', '120111', '1728691258@qq.com', '18224927357', '/static/home/images/user-head/2.jpg', '如果我们需要对读取的数据进行排序，我们就可以使用 MySQL 的 ORDER BY 子句来设定你想按哪个字段哪种方式来进行排序，再返回搜索结果。如果我们需要对读取的数据进行排序，我们就可以使用 MySQL 的 ORDER BY 子句来设定你想按哪个字段哪种方式来进行排序，再返回搜索结果。如果我们需要对读取的数据进行排序，我们就可以使用 MySQL 的 ORDER BY 子句来设定你想按哪个字段哪种方式来进行排序，再返回搜索结果。如果我们需要对读取的数据进行排序，我们就可以使用 MySQL 的 ORDER BY 子句来设定你想按哪个字段哪种方式来进行排序，再返回搜索结果。', '20', 'D4p6', '0', '1', '2019-03-09 01:03:51', '2019-03-03 00:34:13', '2019-03-03 20:25:56', null);
INSERT INTO `as_user` VALUES ('3', '莉莉安', '宋胖子', '1', 'e5a5e9c246620ac234a60f6dfebbf437', '1', '1', '1', '2', '2', '110000', '110100', '110104', 'tjpu_zfh@163.com', '18224927357', '/static/home/images/user-head/3.jpg', '她的名字叫，们就可以使用 MySQL 的 ORDER BY 子句来设定你想按哪个字段哪种方式来进行排序，再返回搜索结果。如果我们需要对读取的数据进行排序，我们就可以使用 MySQL 的 ORDER BY 子句来设定你想按哪个字段哪种方式来进行排序，再返回搜索结们就可以使用 MySQL 的 ORDER BY 子句来设定你想按哪个字段哪种方式来进行排序，再返回搜索结果。如果我们需要对读取的数据进行排序，我们就可以使用 MySQL 的 ORDER BY 子句来设定你想按哪个字段哪种方式来进行排序，再返回搜索结莉莉安', '20', 'K2s3', '0', '1', '2019-03-09 01:03:51', '2019-03-09 01:03:51', '2019-03-09 01:06:39', null);
INSERT INTO `as_user` VALUES ('4', '董小姐1号', '宋胖子', '1', 'e5a5e9c246620ac234a60f6dfebbf437', '1', '1', '1', '1', '2', '110000', '110100', '110104', 'tjpu_zfh@163.com', '18224927357', '/static/home/images/user-head/4.jpg', '她的名字叫，莉莉安', '20', 'K2s3', '0', '1', '2019-03-09 01:03:51', '2019-03-09 01:03:51', '2019-03-09 01:06:39', null);
INSERT INTO `as_user` VALUES ('5', ' 董小姐2号', '宋胖子', '1', 'e5a5e9c246620ac234a60f6dfebbf437', '1', '1', '1', '2', '2', '110000', '110100', '110104', 'tjpu_zfh@163.com', '18224927357', '/static/home/images/user-head/1.jpg', '她的名字叫，们就可以使用 MySQL 的 ORDER BY 子句来设定你想按哪个字段哪种方式来进行排序，再返回搜索结果。如果我们需要对读取的数据进行排序，我们就可以使用 MySQL 的 ORDER BY 子句来设定你想按哪个字段哪种方式来进行排序，再返回搜索结们就可以使用 MySQL 的 ORDER BY 子句来设定你想按哪个字段哪种方式来进行排序，再返回搜索结果。如果我们需要对读取的数据进行排序，我们就可以使用 MySQL 的 ORDER BY 子句来设定你想按哪个字段哪种方式来进行排序，再返回搜索结们就可以使用 MySQL 的 ORDER BY 子句来设定你想按哪个字段哪种方式来进行排序，再返回搜索结果。如果我们需要对读取的数据进行排序，我们就可以使用 MySQL 的 ORDER BY 子句来设定你想按哪个字段哪种方式来进行排序，再返回搜索结们就可以使用 MySQL 的 ORDER BY 子句来设定你想按哪个字段哪种方式来进行排序，再返回搜索结果。如果我们需要对读取的数据进行排序，我们就可以使用 MySQL 的 ORDER BY 子句来设定你想按哪个字段哪种方式来进行排序，再返回搜索结们就可以使用 MySQL 的 ORDER BY 子句来设定你想按哪个字段哪种方式来进行排序，再返回搜索结果。如果我们需要对读取的数据进行排序，我们就可以使用 MySQL 的 ORDER BY 子句来设定你想按哪个字段哪种方式来进行排序，再返回搜索结们就可以使用 MySQL 的 ORDER BY 子句来设定你想按哪个字段哪种方式来进行排序，再返回搜索结果。如果我们需要对读取的数据进行排序，我们就可以使用 MySQL 的 ORDER BY 子句来设定你想按哪个字段哪种方式来进行排序，再返回搜索结莉莉安', '20', 'K2s3', '0', '1', '2019-03-09 01:03:51', '2019-03-09 01:03:51', '2019-03-09 01:06:39', null);
INSERT INTO `as_user` VALUES ('6', '董小姐3号', '宋胖子', '1', 'e5a5e9c246620ac234a60f6dfebbf437', '1', '1', '1', '1', '2', '110000', '110100', '110104', 'tjpu_zfh@163.com', '18224927357', '/static/home/images/user-head/2.jpg', '她的名字叫，莉莉安', '20', 'K2s3', '0', '1', '2019-03-09 01:03:51', '2019-03-09 01:03:51', '2019-03-09 01:06:39', null);
INSERT INTO `as_user` VALUES ('7', '董小姐4号', '宋胖子', '1', 'e5a5e9c246620ac234a60f6dfebbf437', '1', '1', '1', '1', '2', '110000', '110100', '110104', 'tjpu_zfh@163.com', '18224927357', '/static/home/images/user-head/3.jpg', '她的名字叫们就可以使用 MySQL 的 ORDER BY 子句来设定你想按哪个字段哪种方式来进行排序，再返回搜索结果。如果我们需要对读取的数据进行排序，我们就可以使用 MySQL 的 ORDER BY 子句来设定你想按哪个字段哪种方式来进行排序，再返回搜索结们就可以使用 MySQL 的 ORDER BY 子句来设定你想按哪个字段哪种方式来进行排序，再返回搜索结果。如果我们需要对读取的数据进行排序，我们就可以使用 MySQL 的 ORDER BY 子句来设定你想按哪个字段哪种方式来进行排序，再返回搜索结们就可以使用 MySQL 的 ORDER BY 子句来设定你想按哪个字段哪种方式来进行排序，再返回搜索结果。如果我们需要对读取的数据进行排序，我们就可以使用 MySQL 的 ORDER BY 子句来设定你想按哪个字段哪种方式来进行排序，再返回搜索结们就可以使用 MySQL 的 ORDER BY 子句来设定你想按哪个字段哪种方式来进行排序，再返回搜索结果。如果我们需要对读取的数据进行排序，我们就可以使用 MySQL 的 ORDER BY 子句来设定你想按哪个字段哪种方式来进行排序，再返回搜索结们就可以使用 MySQL 的 ORDER BY 子句来设定你想按哪个字段哪种方式来进行排序，再返回搜索结果。如果我们需要对读取的数据进行排序，我们就可以使用 MySQL 的 ORDER BY 子句来设定你想按哪个字段哪种方式来进行排序，再返回搜索结们就可以使用 MySQL 的 ORDER BY 子句来设定你想按哪个字段哪种方式来进行排序，再返回搜索结果。如果我们需要对读取的数据进行排序，我们就可以使用 MySQL 的 ORDER BY 子句来设定你想按哪个字段哪种方式来进行排序，再返回搜索结们就可以使用 MySQL 的 ORDER BY 子句来设定你想按哪个字段哪种方式来进行排序，再返回搜索结果。如果我们需要对读取的数据进行排序，我们就可以使用 MySQL 的 ORDER BY 子句来设定你想按哪个字段哪种方式来进行排序，再返回搜索结，莉莉安', '20', 'K2s3', '0', '1', '2019-03-09 01:03:51', '2019-03-09 01:03:51', '2019-03-09 01:06:39', null);
INSERT INTO `as_user` VALUES ('8', '董小姐5号', '宋胖子', '1', 'e5a5e9c246620ac234a60f6dfebbf437', '1', '1', '1', '2', '2', '110000', '110100', '110104', 'tjpu_zfh@163.com', '18224927357', '/static/home/images/user-head/4.jpg', '她的名字叫，莉莉安', '20', 'K2s3', '0', '1', '2019-03-09 01:03:51', '2019-03-09 01:03:51', '2019-03-09 01:06:39', null);
INSERT INTO `as_user` VALUES ('9', '董小姐6号', '宋胖子', '1', 'e5a5e9c246620ac234a60f6dfebbf437', '1', '1', '1', '1', '2', '110000', '110100', '110104', 'tjpu_zfh@163.com', '18224927357', '/static/home/images/user-head/3.jpg', '她的名字叫，莉莉安', '20', 'K2s3', '0', '1', '2019-03-09 01:03:51', '2019-03-09 01:03:51', '2019-03-09 01:06:39', null);
INSERT INTO `as_user` VALUES ('10', '董小姐7号', '宋胖子', '1', 'e5a5e9c246620ac234a60f6dfebbf437', '1', '1', '1', '2', '2', '110000', '110100', '110104', 'tjpu_zfh@163.com', '18224927357', '/static/home/images/user-head/1.jpg', '她的名字叫，莉莉安', '20', 'K2s3', '0', '1', '2019-03-09 01:03:51', '2019-03-09 01:03:51', '2019-03-09 01:06:39', null);
INSERT INTO `as_user` VALUES ('11', '董小姐8号', '宋胖子', '1', 'e5a5e9c246620ac234a60f6dfebbf437', '1', '1', '1', '2', '2', '110000', '110100', '110104', 'tjpu_zfh@163.com', '18224927357', '/static/home/images/user-head/2.jpg', '她的名字叫，莉莉安', '20', 'K2s3', '0', '1', '2019-03-09 01:03:51', '2019-03-09 01:03:51', '2019-03-09 01:06:39', null);
INSERT INTO `as_user` VALUES ('12', '董小姐9号', '宋胖子', '1', 'e5a5e9c246620ac234a60f6dfebbf437', '1', '1', '1', '1', '2', '110000', '110100', '110104', 'tjpu_zfh@163.com', '18224927357', '/static/home/images/user-head/3.jpg', '她的名字叫，莉莉安', '20', 'K2s3', '0', '1', '2019-03-09 01:03:51', '2019-03-09 01:03:51', '2019-03-09 01:06:39', null);
INSERT INTO `as_user` VALUES ('13', '董小姐10号', '宋胖子', '1', 'e5a5e9c246620ac234a60f6dfebbf437', '1', '1', '1', '1', '2', '110000', '110100', '110104', 'tjpu_zfh@163.com', '18224927357', '/static/home/images/user-head/3.jpg', '她的名字叫，莉莉安', '20', 'K2s3', '0', '1', '2019-03-09 01:03:51', '2019-03-09 01:03:51', '2019-03-09 01:06:39', null);
INSERT INTO `as_user` VALUES ('14', '董小姐11号', '宋胖子', '1', 'e5a5e9c246620ac234a60f6dfebbf437', '1', '1', '1', '1', '2', '110000', '110100', '110104', 'tjpu_zfh@163.com', '18224927357', '/static/home/images/user-head/3.jpg', '她的名字叫，莉莉安', '20', 'K2s3', '0', '1', '2019-03-09 01:03:51', '2019-03-09 01:03:51', '2019-03-09 01:06:39', null);
INSERT INTO `as_user` VALUES ('15', '董小姐12号', '宋胖子', '1', 'e5a5e9c246620ac234a60f6dfebbf437', '1', '1', '1', '1', '2', '110000', '110100', '110104', 'tjpu_zfh@163.com', '18224927357', '/static/home/images/user-head/3.jpg', '她的名字叫，莉莉安', '20', 'K2s3', '0', '1', '2019-03-09 01:03:51', '2019-03-09 01:03:51', '2019-03-09 01:06:39', null);
INSERT INTO `as_user` VALUES ('16', 'aaa', 'aaaaa', '1', 'e5a5e9c246620ac234a60f6dfebbf437', '1', '1', '1', '2', '1', '110000', '110100', '110101', 'aaa@163.com', '15902225057', '/static/upload/images/avatar/2019/3/avatar_2019031702494585410154.jpg', 'sdfsssssssssss', '20', 'kgxm', '0', '1', '2019-03-17 02:49:45', '2019-03-17 02:49:45', null, null);
INSERT INTO `as_user` VALUES ('17', 'JackChen', 'JackChen', '1', 'e5a5e9c246620ac234a60f6dfebbf437', '1', '1', '1', '1', '1', '110000', '110100', '110101', 'cheneeeeeeeee@163.com', '15902225075', '/static/upload/images/avatar/2019/3/avatar_20190317025608974919945.jpg', 'adfffffffffffffffffffffffffffffffffffffffff', '20', 'DGWp', '0', '1', '2019-03-17 02:56:09', '2019-03-17 02:56:09', null, null);
INSERT INTO `as_user` VALUES ('18', 'Mayun', 'JackMa', '1', 'e5a5e9c246620ac234a60f6dfebbf437', '1', '1', '1', '2', '1', '110000', '110100', '110101', 'aaa@77.com', '15902225075', '/static/upload/images/avatar/2019/3/avatar_20190317025934117867878.jpg', '这是简介这是简介这是简介这是简介这是简介这是简介', '20', 'jSpx', '0', '1', '2019-03-17 02:59:34', '2019-03-17 02:59:34', null, null);
INSERT INTO `as_user` VALUES ('19', '啊啊啊', '啊啊啊', '1', 'e5a5e9c246620ac234a60f6dfebbf437', '1', '1', '1', '2', '1', '110000', '110100', '110101', 'aaa@bbb.com', '15902225075', '/static/upload/images/avatar/2019/3/avatar_20190317121200533587132.jpg', '111111111111111111111', '20', 'G63b', '0', '1', '2019-03-17 12:12:00', '2019-03-17 12:12:00', null, null);
INSERT INTO `as_user` VALUES ('20', '增阿六', '666', '1', 'e5a5e9c246620ac234a60f6dfebbf437', '1', '1', '1', '2', '2', '110000', '110100', '110101', '999@qqq.com', '15902225075', '/static/upload/images/avatar/2019/3/avatar_20190317121500793120079.jpg', '你好呀你好呀你好呀', '20', '7f1z', '0', '1', '2019-03-17 12:15:00', '2019-03-17 12:15:00', null, null);
INSERT INTO `as_user` VALUES ('21', 'aaa', 'vbb', '1', 'e5a5e9c246620ac234a60f6dfebbf437', '1', '1', '1', '2', '2', '110000', '110100', '110101', '777@77.cpm', '15902225075', '/static/upload/images/avatar/2019/3/avatar_20190317121751970610506.jpg', '阿萨德发发二维', '20', 'Pj9V', '0', '1', '2019-03-17 12:17:52', '2019-03-17 12:17:52', null, null);
INSERT INTO `as_user` VALUES ('22', '666', '666', '1', 'e5a5e9c246620ac234a60f6dfebbf437', '1', '1', '1', '2', '2', '110000', '110100', '110101', '666@777.com', '15902225075', '/static/upload/images/avatar/2019/3/avatar_20190317122040273969130.jpg', '12345678945165', '20', 'Sg8s', '0', '1', '2019-03-17 12:20:40', '2019-03-17 12:20:40', null, null);
INSERT INTO `as_user` VALUES ('23', '啊啊啊', '二恶烷若', '1', 'e5a5e9c246620ac234a60f6dfebbf437', '1', '1', '1', '2', '2', '110000', '110100', '110101', '8489@ee.com', '15905866589', '/static/upload/images/avatar/2019/3/avatar_20190317122621879803209.jpg', 'asdfear aew', '20', 'qyHR', '0', '1', '2019-03-17 12:26:22', '2019-03-17 12:26:22', null, null);
INSERT INTO `as_user` VALUES ('24', 'aa', 'aaa', '1', 'e5a5e9c246620ac234a60f6dfebbf437', '1', '1', '1', '2', '2', '110000', '110100', '110101', 'aaa@99.com', '15902225075', '/static/upload/images/avatar/2019/3/avatar_20190317123713223490900.jpg', 'adfadsfasdf', '20', '4wCf', '0', '1', '2019-03-17 12:37:13', '2019-03-17 12:37:13', null, null);
INSERT INTO `as_user` VALUES ('25', 'tjpu_feihong@16', 'asdfdf', '1', 'e5a5e9c246620ac234a60f6dfebbf437', '1', '1', '1', '2', '2', '110000', '110100', '110101', 'WWWWWWWWW@WWW.com', '15902225075', '/static/upload/images/avatar/2019/3/avatar_20190317130001173960092.jpg', '5615', '20', 'k7N7', '0', '1', '2019-03-17 13:00:01', '2019-03-17 13:00:01', null, null);
INSERT INTO `as_user` VALUES ('26', 'tjpu_feihong@16', 'aaaa', '1', 'e5a5e9c246620ac234a60f6dfebbf437', '1', '1', '1', '2', '2', '110000', '110100', '110101', 'aaaa@999.com', '15902225075', '/static/upload/images/avatar/2019/3/avatar_20190317130723890639742.jpg', '', '20', 'uJXW', '0', '1', '2019-03-17 13:07:24', '2019-03-17 13:07:24', null, null);
