/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 80012
Source Host           : localhost:3306
Source Database       : myhotel

Target Server Type    : MYSQL
Target Server Version : 80012
File Encoding         : 65001

Date: 2018-09-22 11:18:48
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `b_pk` decimal(10,0) NOT NULL,
  `bno` decimal(10,0) NOT NULL,
  `cname` varchar(10) NOT NULL,
  `cphone` varchar(11) CHARACTER SET gbk COLLATE gbk_chinese_ci NOT NULL,
  `cidcard` varchar(18) NOT NULL,
  `rtype` varchar(10) CHARACTER SET gbk COLLATE gbk_chinese_ci NOT NULL,
  `rno` varchar(10) NOT NULL,
  `bdate` timestamp NOT NULL,
  `keeptime` int(5) NOT NULL,
  PRIMARY KEY (`b_pk`,`bno`),
  KEY `rno` (`rno`),
  KEY `cname` (`cname`),
  KEY `bno` (`bno`),
  CONSTRAINT `book_ibfk_1` FOREIGN KEY (`rno`) REFERENCES `roominfo` (`rno`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `book_ibfk_2` FOREIGN KEY (`cname`) REFERENCES `customerinfo` (`cname`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES ('1', '1', '司马迁', '13801286609', '441201197607020852', '单人间', '019', '2018-09-22 00:00:00', '5');
INSERT INTO `book` VALUES ('2', '2', '王二小', '13265485264', '370000199710261325', '单人间', '032', '2018-09-20 00:00:00', '4');

-- ----------------------------
-- Table structure for book_copy
-- ----------------------------
DROP TABLE IF EXISTS `book_copy`;
CREATE TABLE `book_copy` (
  `b_pk` decimal(10,0) NOT NULL,
  `bno` decimal(10,0) NOT NULL,
  `cname` varchar(10) NOT NULL,
  `cphone` varchar(11) CHARACTER SET gbk COLLATE gbk_chinese_ci NOT NULL,
  `cidcard` varchar(18) NOT NULL,
  `rtype` varchar(10) CHARACTER SET gbk COLLATE gbk_chinese_ci NOT NULL,
  `rno` varchar(10) NOT NULL,
  `bdate` timestamp NOT NULL,
  `keeptime` int(5) NOT NULL,
  PRIMARY KEY (`b_pk`,`bno`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of book_copy
-- ----------------------------
INSERT INTO `book_copy` VALUES ('1', '1', '张丹丹', '66666666666', '666666666666666666', '豪华单人间', '001', '2018-09-14 00:00:00', '4');
INSERT INTO `book_copy` VALUES ('2', '2', '李德', '77777777777', '777777777777777777', '豪华单人间', '004', '2018-09-15 00:00:00', '3');
INSERT INTO `book_copy` VALUES ('3', '3', '阿斯达克', '99999999999', '999999999999999999', '豪华单人间', '007', '2018-09-15 00:00:00', '2');
INSERT INTO `book_copy` VALUES ('4', '4', '沈大成', '25615625555', '111111253654585236', '豪华单人间', '016', '2018-09-15 00:00:00', '2');
INSERT INTO `book_copy` VALUES ('5', '5', '孙道存', '13526545566', '152656563265326541', '单人间', '010', '2018-09-15 00:00:00', '2');
INSERT INTO `book_copy` VALUES ('6', '6', '王琳', '13256585955', '370000223652653256', '豪华单人间', '022', '2018-09-16 00:00:00', '7');
INSERT INTO `book_copy` VALUES ('10', '10', '欧克', '48444555555', '31894156165156151', '三人间', '015', '2018-09-16 00:00:00', '7');
INSERT INTO `book_copy` VALUES ('11', '11', '克瑞文', '12312312312', '123123123123123123', '总统套房', '028', '2018-09-16 00:00:00', '3');
INSERT INTO `book_copy` VALUES ('12', '12', '董卓', '13333333333', '370100000000000000', '总统套房', '025', '2018-09-17 00:00:00', '6');
INSERT INTO `book_copy` VALUES ('13', '13', '王强', '12222222222', '222222222222222222', '单人间', '013', '2018-09-20 00:00:00', '3');
INSERT INTO `book_copy` VALUES ('14', '14', '程晨', '22222222222', '222222222222222222', '三人间', '043', '2018-09-22 00:00:00', '4');
INSERT INTO `book_copy` VALUES ('15', '15', '司马迁', '13801286609', '441201197607020852', '单人间', '019', '2018-09-22 00:00:00', '5');
INSERT INTO `book_copy` VALUES ('16', '16', '客户1', '13315248966', '370000199810011110', '单人间', '032', '2018-09-22 00:00:00', '3');
INSERT INTO `book_copy` VALUES ('17', '17', '王二小', '13265485264', '370000199710261325', '单人间', '032', '2018-09-20 00:00:00', '4');

-- ----------------------------
-- Table structure for changeroom
-- ----------------------------
DROP TABLE IF EXISTS `changeroom`;
CREATE TABLE `changeroom` (
  `cr_pk` decimal(10,0) NOT NULL,
  `cr_no` decimal(10,0) NOT NULL,
  `roldno` varchar(10) NOT NULL,
  `rnewno` varchar(10) NOT NULL,
  `cname` varchar(10) NOT NULL,
  `cphone` varchar(11) NOT NULL,
  PRIMARY KEY (`cr_pk`,`cr_no`),
  KEY `roldno` (`roldno`),
  KEY `rnewno` (`rnewno`),
  CONSTRAINT `changeroom_ibfk_1` FOREIGN KEY (`roldno`) REFERENCES `roominfo` (`rno`),
  CONSTRAINT `changeroom_ibfk_2` FOREIGN KEY (`rnewno`) REFERENCES `roominfo` (`rno`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of changeroom
-- ----------------------------
INSERT INTO `changeroom` VALUES ('1', '1', '001', '047', '张丹丹', '66666666666');
INSERT INTO `changeroom` VALUES ('2', '2', '015', '012', ' 欧克', '48444555555');
INSERT INTO `changeroom` VALUES ('3', '3', '025', '026', '董卓', '13333333333');
INSERT INTO `changeroom` VALUES ('4', '4', '027', '029', '董卓', '13333333333');
INSERT INTO `changeroom` VALUES ('5', '5', '013', '019', '王强', '12222222222');
INSERT INTO `changeroom` VALUES ('6', '6', '001', '038', '张丹丹', '66666666666');
INSERT INTO `changeroom` VALUES ('7', '7', '038', '041', '李楠', '11111111111');

-- ----------------------------
-- Table structure for check1
-- ----------------------------
DROP TABLE IF EXISTS `check1`;
CREATE TABLE `check1` (
  `chk_pk` decimal(10,0) NOT NULL,
  `chk_no` decimal(10,0) NOT NULL,
  `ci_no` decimal(10,0) NOT NULL,
  `ci_date` datetime(6) NOT NULL ON UPDATE CURRENT_TIMESTAMP(6),
  `ci_days` int(5) NOT NULL,
  `totalmoney` float(5,0) NOT NULL,
  `cname` varchar(10) DEFAULT NULL,
  `cphone` varchar(11) CHARACTER SET gbk COLLATE gbk_chinese_ci DEFAULT NULL,
  `ctype` varchar(10) CHARACTER SET gbk COLLATE gbk_chinese_ci DEFAULT NULL,
  PRIMARY KEY (`chk_pk`,`chk_no`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of check1
-- ----------------------------
INSERT INTO `check1` VALUES ('1', '1', '7', '2018-09-15 16:57:50.000000', '7', '6125', '欧克', '48444555555', '普通顾客');
INSERT INTO `check1` VALUES ('2', '2', '4', '2018-09-15 16:34:26.000000', '6', '4800', '张丹丹', '66666666666', '普通顾客');
INSERT INTO `check1` VALUES ('3', '3', '4', '2018-09-15 16:34:26.000000', '6', '4800', '张丹丹', '66666666666', '普通顾客');
INSERT INTO `check1` VALUES ('4', '4', '5', '2018-09-15 12:00:00.000000', '3', '1800', '李德', '77777777777', '普通顾客');
INSERT INTO `check1` VALUES ('5', '5', '7', '2018-09-15 16:57:50.000000', '7', '12250', '欧克', '48444555555', '普通顾客');
INSERT INTO `check1` VALUES ('6', '6', '6', '2018-09-15 12:00:00.000000', '2', '800', '阿斯达克', '99999999999', '普通顾客');
INSERT INTO `check1` VALUES ('7', '7', '3', '2018-09-21 00:00:00.000000', '2', '1000', '客户1', '13315248966', '普通顾客');
INSERT INTO `check1` VALUES ('8', '8', '1', '2018-09-16 10:56:39.000000', '2', '800', '阿桑', '13218151651', '普通顾客');
INSERT INTO `check1` VALUES ('9', '9', '4', '2018-09-17 13:58:52.000000', '12', '72000', '董卓', '13333333333', '普通顾客');
INSERT INTO `check1` VALUES ('10', '10', '5', '2018-09-17 16:01:37.000000', '5', '1500', '王强', '12222222222', '普通顾客');
INSERT INTO `check1` VALUES ('11', '11', '3', '2018-09-16 12:00:00.000000', '3', '9000', '克瑞文', '12312312312', '普通顾客');
INSERT INTO `check1` VALUES ('12', '12', '4', '2018-09-17 15:07:42.000000', '5', '17500', '董卓', '13333333333', '会员顾客');
INSERT INTO `check1` VALUES ('13', '13', '3', '2018-09-20 00:00:00.000000', '3', '900', '客户1', '13315248966', '普通顾客');
INSERT INTO `check1` VALUES ('14', '14', '3', '2018-09-20 00:00:00.000000', '2', '400', '客户1', '13315248966', '普通顾客');
INSERT INTO `check1` VALUES ('15', '15', '3', '2018-09-21 00:00:00.000000', '3', '900', '客户1', '13315248966', '普通顾客');
INSERT INTO `check1` VALUES ('16', '16', '3', '2018-09-21 00:00:00.000000', '2', '400', '客户1', '13315248966', '普通顾客');
INSERT INTO `check1` VALUES ('17', '17', '1', '2018-09-21 00:00:00.000000', '4', '1600', '客户1', '13315248966', '普通顾客');
INSERT INTO `check1` VALUES ('18', '18', '1', '2018-09-14 12:00:00.000000', '4', '3200', '张丹丹', '66666666666', '普通顾客');
INSERT INTO `check1` VALUES ('19', '19', '2', '2018-09-15 12:00:00.000000', '3', '1800', '李德', '77777777777', '普通顾客');
INSERT INTO `check1` VALUES ('20', '20', '3', '2018-09-15 12:00:00.000000', '2', '800', '阿斯达克', '99999999999', '普通顾客');
INSERT INTO `check1` VALUES ('21', '21', '6', '2018-09-16 12:00:00.000000', '3', '9000', '克瑞文', '12312312312', '普通顾客');
INSERT INTO `check1` VALUES ('22', '22', '5', '2018-09-17 12:00:00.000000', '6', '25200', '董卓', '13333333333', '会员顾客');
INSERT INTO `check1` VALUES ('23', '23', '4', '2018-09-20 12:00:00.000000', '3', '900', '王强', '12222222222', '普通顾客');
INSERT INTO `check1` VALUES ('24', '24', '5', '2018-09-22 00:00:00.000000', '2', '1000', '李楠', '11111111111', '普通顾客');
INSERT INTO `check1` VALUES ('25', '25', '17', '2018-09-22 12:00:00.000000', '3', '900', '客户1', '13315248966', '普通顾客');
INSERT INTO `check1` VALUES ('26', '26', '6', '2018-09-18 14:27:46.000000', '8', '6400', '张丹丹', '66666666666', '普通顾客');
INSERT INTO `check1` VALUES ('27', '27', '17', '2018-09-18 16:21:14.000000', '4', '1600', '李楠', '11111111111', '普通顾客');

-- ----------------------------
-- Table structure for checkin
-- ----------------------------
DROP TABLE IF EXISTS `checkin`;
CREATE TABLE `checkin` (
  `ci_pk` decimal(10,0) NOT NULL,
  `ci_no` decimal(10,0) NOT NULL,
  `rno` varchar(10) NOT NULL,
  `rtype` varchar(10) CHARACTER SET gbk COLLATE gbk_chinese_ci NOT NULL,
  `cno` decimal(10,0) NOT NULL,
  `cname` varchar(10) NOT NULL,
  `sex` varchar(1) DEFAULT NULL,
  `cphone` varchar(11) CHARACTER SET gbk COLLATE gbk_chinese_ci NOT NULL,
  `cidcard` varchar(18) NOT NULL,
  `ci_date` timestamp(6) NOT NULL ON UPDATE CURRENT_TIMESTAMP(6),
  `ci_days` int(5) NOT NULL,
  `deposit` float(5,0) NOT NULL,
  PRIMARY KEY (`ci_pk`,`ci_no`),
  KEY `cname` (`cname`),
  KEY `ci_no` (`ci_no`),
  KEY `ci_no_2` (`ci_no`,`ci_date`),
  KEY `ci_date` (`ci_date`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of checkin
-- ----------------------------
INSERT INTO `checkin` VALUES ('7', '7', '004', '豪华单人间', '11', '李德', '男', '77777777777', '777777777777777777', '2018-09-15 12:00:00.000000', '3', '300');
INSERT INTO `checkin` VALUES ('8', '8', '007', '豪华单人间', '12', '阿斯达克', '男', '99999999999', '999999999999999999', '2018-09-15 12:00:00.000000', '2', '200');
INSERT INTO `checkin` VALUES ('9', '9', '016', '豪华单人间', '13', '沈大成', '男', '25615625555', '111111253654585236', '2018-09-15 12:00:00.000000', '2', '200');
INSERT INTO `checkin` VALUES ('10', '10', '010', '单人间', '14', '孙道存', '男', '13526545566', '152656563265326541', '2018-09-15 12:00:00.000000', '2', '100');
INSERT INTO `checkin` VALUES ('11', '11', '022', '豪华单人间', '15', '王琳', '女', '13256585955', '370000223652653256', '2018-09-16 12:00:00.000000', '7', '700');
INSERT INTO `checkin` VALUES ('12', '12', '015', '三人间', '16', '欧克', '男', '48444555555', '31894156165156151', '2018-09-16 12:00:00.000000', '7', '875');
INSERT INTO `checkin` VALUES ('13', '13', '028', '总统套房', '18', '克瑞文', '男', '12312312312', '123123123123123123', '2018-09-16 12:00:00.000000', '3', '1500');
INSERT INTO `checkin` VALUES ('14', '14', '025', '总统套房', '19', '董卓', '男', '13333333333', '370100000000000000', '2018-09-17 12:00:00.000000', '6', '3000');
INSERT INTO `checkin` VALUES ('15', '15', '013', '单人间', '20', '王强', '男', '12222222222', '222222222222222222', '2018-09-20 12:00:00.000000', '3', '150');
INSERT INTO `checkin` VALUES ('16', '16', '043', '三人间', '6', '程晨', '男', '22222222222', '222222222222222222', '2018-09-22 12:00:00.000000', '4', '500');
INSERT INTO `checkin` VALUES ('18', '18', '041', '单人间', '22', '2222', '男', 'asdffsfsdfd', 'sdfdsf122222333333', '2018-09-22 00:00:00.000000', '4', '200');

-- ----------------------------
-- Table structure for checkin_copy
-- ----------------------------
DROP TABLE IF EXISTS `checkin_copy`;
CREATE TABLE `checkin_copy` (
  `ci_pk` decimal(10,0) NOT NULL,
  `ci_no` decimal(10,0) NOT NULL,
  `rno` varchar(10) NOT NULL,
  `rtype` varchar(10) CHARACTER SET gbk COLLATE gbk_chinese_ci NOT NULL,
  `cno` decimal(10,0) NOT NULL,
  `cname` varchar(10) NOT NULL,
  `sex` varchar(1) DEFAULT NULL,
  `cphone` varchar(11) CHARACTER SET gbk COLLATE gbk_chinese_ci NOT NULL,
  `cidcard` varchar(18) NOT NULL,
  `ci_date` timestamp(6) NOT NULL ON UPDATE CURRENT_TIMESTAMP(6),
  `ci_days` int(5) NOT NULL,
  `deposit` float(5,0) NOT NULL,
  PRIMARY KEY (`ci_pk`,`ci_no`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of checkin_copy
-- ----------------------------
INSERT INTO `checkin_copy` VALUES ('5', '5', '046', '三人间', '5', '李楠', '女', '11111111111', '111111111111111111', '2018-09-22 00:00:00.000000', '2', '250');
INSERT INTO `checkin_copy` VALUES ('6', '6', '001', '豪华单人间', '10', '张丹丹', '女', '66666666666', '666666666666666666', '2018-09-14 12:00:00.000000', '4', '400');
INSERT INTO `checkin_copy` VALUES ('7', '7', '004', '豪华单人间', '11', '李德', '男', '77777777777', '777777777777777777', '2018-09-15 12:00:00.000000', '3', '300');
INSERT INTO `checkin_copy` VALUES ('8', '8', '007', '豪华单人间', '12', '阿斯达克', '男', '99999999999', '999999999999999999', '2018-09-15 12:00:00.000000', '2', '200');
INSERT INTO `checkin_copy` VALUES ('9', '9', '016', '豪华单人间', '13', '沈大成', '男', '25615625555', '111111253654585236', '2018-09-15 12:00:00.000000', '2', '200');
INSERT INTO `checkin_copy` VALUES ('10', '10', '010', '单人间', '14', '孙道存', '男', '13526545566', '152656563265326541', '2018-09-15 12:00:00.000000', '2', '100');
INSERT INTO `checkin_copy` VALUES ('11', '11', '022', '豪华单人间', '15', '王琳', '女', '13256585955', '370000223652653256', '2018-09-16 12:00:00.000000', '7', '700');
INSERT INTO `checkin_copy` VALUES ('12', '12', '015', '三人间', '16', '欧克', '男', '48444555555', '31894156165156151', '2018-09-16 12:00:00.000000', '7', '875');
INSERT INTO `checkin_copy` VALUES ('13', '13', '028', '总统套房', '18', '克瑞文', '男', '12312312312', '123123123123123123', '2018-09-16 12:00:00.000000', '3', '1500');
INSERT INTO `checkin_copy` VALUES ('14', '14', '025', '总统套房', '19', '董卓', '男', '13333333333', '370100000000000000', '2018-09-17 12:00:00.000000', '6', '3000');
INSERT INTO `checkin_copy` VALUES ('15', '15', '013', '单人间', '20', '王强', '男', '12222222222', '222222222222222222', '2018-09-20 12:00:00.000000', '3', '150');
INSERT INTO `checkin_copy` VALUES ('16', '16', '043', '三人间', '6', '程晨', '男', '22222222222', '222222222222222222', '2018-09-22 12:00:00.000000', '4', '500');
INSERT INTO `checkin_copy` VALUES ('17', '17', '032', '单人间', '1', '客户1', '男', '13315248966', '370000199810011110', '2018-09-22 12:00:00.000000', '3', '150');
INSERT INTO `checkin_copy` VALUES ('18', '18', '038', '单人间', '5', '李楠', '女', '11111111111', '111111111111111111', '2018-09-22 00:00:00.000000', '4', '200');
INSERT INTO `checkin_copy` VALUES ('19', '19', '041', '单人间', '22', '2222', '男', 'asdffsfsdfd', 'sdfdsf122222333333', '2018-09-22 00:00:00.000000', '4', '200');

-- ----------------------------
-- Table structure for customerinfo
-- ----------------------------
DROP TABLE IF EXISTS `customerinfo`;
CREATE TABLE `customerinfo` (
  `c_pk` decimal(10,0) NOT NULL,
  `cno` decimal(10,0) NOT NULL,
  `cname` varchar(10) NOT NULL,
  `sex` varchar(1) DEFAULT NULL,
  `cidcard` varchar(18) NOT NULL,
  `cphone` varchar(11) NOT NULL,
  `ct_no` varchar(10) NOT NULL,
  PRIMARY KEY (`c_pk`,`cno`),
  KEY `cname` (`cname`,`cidcard`,`cphone`),
  KEY `cname_2` (`cname`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of customerinfo
-- ----------------------------
INSERT INTO `customerinfo` VALUES ('1', '1', '客户1', '男', '370000199810011110', '13315248966', '1');
INSERT INTO `customerinfo` VALUES ('2', '2', '王二小', '男', '370000199710261325', '13265485264', '1');
INSERT INTO `customerinfo` VALUES ('3', '3', '慕辰', '男', '370000199710253256', '13652965545', '1');
INSERT INTO `customerinfo` VALUES ('4', '4', '慕辰', '男', '370000000000000000', '13564654555', '1');
INSERT INTO `customerinfo` VALUES ('5', '5', '李楠', '女', '111111111111111111', '11111111111', '1');
INSERT INTO `customerinfo` VALUES ('6', '6', '程晨', '男', '222222222222222222', '22222222222', '1');
INSERT INTO `customerinfo` VALUES ('7', '7', '梁宏达', '男', '33333333333333333', '33333333333', '1');
INSERT INTO `customerinfo` VALUES ('8', '8', '苏达', '男', '444444444444444444', '44444444444', '1');
INSERT INTO `customerinfo` VALUES ('9', '9', '高峰', '男', '555555555555555555', '55555555555', '1');
INSERT INTO `customerinfo` VALUES ('10', '10', '张丹丹', '女', '666666666666666666', '66666666666', '1');
INSERT INTO `customerinfo` VALUES ('11', '11', '李德', '男', '777777777777777777', '77777777777', '1');
INSERT INTO `customerinfo` VALUES ('12', '12', '阿斯达克', '男', '999999999999999999', '99999999999', '1');
INSERT INTO `customerinfo` VALUES ('13', '13', '沈大成', '男', '111111253654585236', '25615625555', '1');
INSERT INTO `customerinfo` VALUES ('14', '14', '孙道存', '男', '152656563265326541', '13526545566', '1');
INSERT INTO `customerinfo` VALUES ('15', '15', '王琳', '女', '370000223652653256', '13256585955', '1');
INSERT INTO `customerinfo` VALUES ('16', '16', '欧克', '男', '31894156165156151', '48444555555', '1');
INSERT INTO `customerinfo` VALUES ('17', '17', '阿桑', '女', '374565896544125689', '13218151651', '1');
INSERT INTO `customerinfo` VALUES ('18', '18', '克瑞文', '男', '123123123123123123', '12312312312', '1');
INSERT INTO `customerinfo` VALUES ('19', '19', '董卓', '男', '370100000000000000', '13333333333', '2');
INSERT INTO `customerinfo` VALUES ('20', '20', '王强', '男', '222222222222222222', '12222222222', '1');
INSERT INTO `customerinfo` VALUES ('21', '21', '司马迁', '男', '441201197607020852', '13801286609', '1');
INSERT INTO `customerinfo` VALUES ('22', '22', '2222', '男', 'sdfdsf122222333333', 'asdffsfsdfd', '1');

-- ----------------------------
-- Table structure for customertype
-- ----------------------------
DROP TABLE IF EXISTS `customertype`;
CREATE TABLE `customertype` (
  `ct_pk` decimal(10,0) NOT NULL,
  `ct_no` decimal(10,0) NOT NULL,
  `ctype` varchar(10) NOT NULL,
  `dis_attr` varchar(10) NOT NULL,
  `discount` float(5,2) NOT NULL,
  PRIMARY KEY (`ct_pk`),
  KEY `ct_no` (`ct_no`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of customertype
-- ----------------------------
INSERT INTO `customertype` VALUES ('1', '1', '普通顾客', '无折扣', '1.00');
INSERT INTO `customertype` VALUES ('2', '2', '会员顾客', '优惠折扣', '0.70');

-- ----------------------------
-- Table structure for roominfo
-- ----------------------------
DROP TABLE IF EXISTS `roominfo`;
CREATE TABLE `roominfo` (
  `r_pk` decimal(10,0) NOT NULL,
  `rno` varchar(10) CHARACTER SET gbk COLLATE gbk_chinese_ci NOT NULL,
  `rt_no` varchar(10) CHARACTER SET gbk COLLATE gbk_chinese_ci NOT NULL,
  `state` varchar(10) CHARACTER SET gbk COLLATE gbk_chinese_ci NOT NULL,
  `statetime` int(5) NOT NULL,
  `rtel` varchar(15) CHARACTER SET gbk COLLATE gbk_chinese_ci NOT NULL,
  `statedate` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`r_pk`,`rno`),
  KEY `rno` (`rno`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of roominfo
-- ----------------------------
INSERT INTO `roominfo` VALUES ('1', '001', '4', '空闲', '30', '88888001', '2018-09-18 14:24:36');
INSERT INTO `roominfo` VALUES ('2', '002', '5', '已入住', '3', '88888002', '2018-09-15 00:00:00');
INSERT INTO `roominfo` VALUES ('3', '003', '6', '空闲', '30', '88888003', '2018-09-13 16:37:59');
INSERT INTO `roominfo` VALUES ('4', '004', '4', '已入住', '3', '88888004', '2018-09-15 12:00:00');
INSERT INTO `roominfo` VALUES ('5', '005', '5', '空闲', '30', '88888005', '2018-09-18 12:04:09');
INSERT INTO `roominfo` VALUES ('6', '006', '6', '空闲', '30', '88888006', '2018-09-18 13:42:01');
INSERT INTO `roominfo` VALUES ('7', '007', '4', '已入住', '2', '88888007', '2018-09-15 12:00:00');
INSERT INTO `roominfo` VALUES ('8', '008', '5', '空闲', '30', '88888008', '2018-09-13 16:43:51');
INSERT INTO `roominfo` VALUES ('9', '009', '6', '空闲', '30', '88888009', '2018-09-18 12:04:21');
INSERT INTO `roominfo` VALUES ('10', '010', '1', '已入住', '2', '88888010', '2018-09-15 12:00:00');
INSERT INTO `roominfo` VALUES ('11', '011', '2', '空闲', '30', '88888011', '2018-09-13 16:44:00');
INSERT INTO `roominfo` VALUES ('12', '012', '3', '空闲', '30', '88888012', '2018-09-16 09:56:49');
INSERT INTO `roominfo` VALUES ('13', '013', '1', '已入住', '3', '88888013', '2018-09-20 12:00:00');
INSERT INTO `roominfo` VALUES ('14', '014', '2', '空闲', '30', '88888014', '2018-09-13 16:44:10');
INSERT INTO `roominfo` VALUES ('15', '015', '3', '已入住', '7', '88888015', '2018-09-16 12:00:00');
INSERT INTO `roominfo` VALUES ('16', '016', '4', '已入住', '2', '88888016', '2018-09-15 12:00:00');
INSERT INTO `roominfo` VALUES ('17', '017', '5', '空闲', '30', '88888017', '2018-09-18 13:42:09');
INSERT INTO `roominfo` VALUES ('18', '018', '6', '空闲', '30', '88888018', '2018-09-13 16:44:26');
INSERT INTO `roominfo` VALUES ('19', '019', '1', '已预定', '5', '88888019', '2018-09-22 00:00:00');
INSERT INTO `roominfo` VALUES ('20', '020', '2', '空闲', '30', '88888020', '2018-09-13 16:44:33');
INSERT INTO `roominfo` VALUES ('21', '021', '3', '空闲', '30', '88888021', '2018-09-18 12:04:43');
INSERT INTO `roominfo` VALUES ('22', '022', '4', '已入住', '7', '88888022', '2018-09-16 12:00:00');
INSERT INTO `roominfo` VALUES ('23', '023', '7', '空闲', '30', '88888023', '2018-09-17 16:06:24');
INSERT INTO `roominfo` VALUES ('24', '024', '6', '空闲', '30', '88888024', '2018-09-13 16:44:50');
INSERT INTO `roominfo` VALUES ('25', '025', '7', '已入住', '6', '88888025', '2018-09-17 12:00:00');
INSERT INTO `roominfo` VALUES ('26', '026', '7', '空闲', '30', '88888026', '2018-09-17 13:59:54');
INSERT INTO `roominfo` VALUES ('27', '027', '7', '空闲', '30', '88888027', '2018-09-17 15:07:43');
INSERT INTO `roominfo` VALUES ('28', '028', '7', '已入住', '3', '88888028', '2018-09-16 12:00:00');
INSERT INTO `roominfo` VALUES ('29', '029', '7', '空闲', '30', '88888029', '2018-09-18 10:57:10');
INSERT INTO `roominfo` VALUES ('30', '030', '7', '空闲', '30', '88888030', '2018-09-13 16:45:08');
INSERT INTO `roominfo` VALUES ('31', '031', '7', '空闲', '30', '88888031', '2018-09-13 16:45:11');
INSERT INTO `roominfo` VALUES ('32', '032', '1', '已预定', '4', '88888032', '2018-09-20 00:00:00');
INSERT INTO `roominfo` VALUES ('33', '033', '2', '空闲', '30', '88888033', '2018-09-13 16:45:16');
INSERT INTO `roominfo` VALUES ('34', '034', '3', '空闲', '30', '88888034', '2018-09-13 16:45:18');
INSERT INTO `roominfo` VALUES ('35', '035', '4', '空闲', '30', '88888035', '2018-09-16 11:05:24');
INSERT INTO `roominfo` VALUES ('36', '036', '5', '空闲', '30', '88888036', '2018-09-13 16:45:25');
INSERT INTO `roominfo` VALUES ('37', '037', '6', '空闲', '30', '88888037', '2018-09-13 16:45:31');
INSERT INTO `roominfo` VALUES ('38', '038', '1', '空闲', '30', '88888038', '2018-09-18 16:21:14');
INSERT INTO `roominfo` VALUES ('39', '039', '2', '空闲', '30', '88888039', '2018-09-13 16:45:36');
INSERT INTO `roominfo` VALUES ('40', '040', '3', '空闲', '30', '88888040', '2018-09-13 16:45:39');
INSERT INTO `roominfo` VALUES ('41', '041', '1', '空闲', '30', '88888041', '2018-09-18 04:25:59');
INSERT INTO `roominfo` VALUES ('42', '042', '2', '空闲', '30', '88888042', '2018-09-13 16:45:45');
INSERT INTO `roominfo` VALUES ('43', '043', '3', '已入住', '4', '88888043', '2018-09-22 12:00:00');
INSERT INTO `roominfo` VALUES ('44', '044', '1', '空闲', '30', '88888044', '2018-09-13 16:45:50');
INSERT INTO `roominfo` VALUES ('45', '045', '2', '空闲', '30', '88888045', '2018-09-13 16:45:53');
INSERT INTO `roominfo` VALUES ('46', '046', '3', '空闲', '30', '88888046', '2018-09-18 01:15:21');
INSERT INTO `roominfo` VALUES ('47', '047', '4', '空闲', '30', '88888047', '2018-09-16 09:52:25');
INSERT INTO `roominfo` VALUES ('48', '048', '5', '空闲', '30', '88888048', '2018-09-13 16:46:00');
INSERT INTO `roominfo` VALUES ('49', '049', '6', '空闲', '30', '88888049', '2018-09-13 16:46:02');
INSERT INTO `roominfo` VALUES ('50', '050', '1', '空闲', '30', '88888050', '2018-09-13 16:46:04');
INSERT INTO `roominfo` VALUES ('51', '051', '4', '空闲', '30', '88888051', '2018-09-17 16:06:06');
INSERT INTO `roominfo` VALUES ('52', '053', '1', '空闲', '30', '88888053', '2018-09-17 16:05:22');
INSERT INTO `roominfo` VALUES ('53', '052', '1', '空闲', '30', '88888052', '2018-09-18 14:31:28');
INSERT INTO `roominfo` VALUES ('54', '054', '1', '空闲', '30', '88888054', '2018-09-18 16:30:54');

-- ----------------------------
-- Table structure for roomtype
-- ----------------------------
DROP TABLE IF EXISTS `roomtype`;
CREATE TABLE `roomtype` (
  `rt_pk` decimal(10,0) NOT NULL,
  `rt_no` varchar(10) NOT NULL,
  `rtype` varchar(10) NOT NULL,
  `price` float(5,0) NOT NULL,
  `deposit` float(5,0) NOT NULL,
  PRIMARY KEY (`rt_pk`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of roomtype
-- ----------------------------
INSERT INTO `roomtype` VALUES ('1', '1', '单人间', '100', '50');
INSERT INTO `roomtype` VALUES ('2', '2', '双人间', '180', '90');
INSERT INTO `roomtype` VALUES ('3', '3', '三人间', '250', '125');
INSERT INTO `roomtype` VALUES ('4', '4', '豪华单人间', '200', '100');
INSERT INTO `roomtype` VALUES ('5', '5', '豪华双人间', '360', '180');
INSERT INTO `roomtype` VALUES ('6', '6', '豪华三人间', '500', '250');
INSERT INTO `roomtype` VALUES ('7', '7', '总统套房', '1000', '500');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_pk` decimal(10,0) NOT NULL,
  `userid` varchar(10) NOT NULL,
  `password` varchar(10) NOT NULL,
  `level` int(5) NOT NULL,
  PRIMARY KEY (`user_pk`),
  KEY `userid` (`userid`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `workerinfo` (`wuserid`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '001', '001', '3');
INSERT INTO `user` VALUES ('2', '002', '002', '2');
INSERT INTO `user` VALUES ('3', '003', '003', '2');
INSERT INTO `user` VALUES ('4', '004', '004', '1');
INSERT INTO `user` VALUES ('5', '005', '005', '1');

-- ----------------------------
-- Table structure for workerinfo
-- ----------------------------
DROP TABLE IF EXISTS `workerinfo`;
CREATE TABLE `workerinfo` (
  `w_pk` decimal(10,0) NOT NULL,
  `wno` decimal(10,0) NOT NULL,
  `wname` varchar(10) NOT NULL,
  `sex` varchar(1) CHARACTER SET gbk COLLATE gbk_chinese_ci NOT NULL,
  `age` int(5) NOT NULL,
  `wphone` varchar(11) CHARACTER SET gbk COLLATE gbk_chinese_ci NOT NULL,
  `wposition` varchar(10) NOT NULL,
  `wuserid` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`w_pk`),
  KEY `wuserid` (`wuserid`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

-- ----------------------------
-- Records of workerinfo
-- ----------------------------
INSERT INTO `workerinfo` VALUES ('1', '1', '李一', '男', '40', '18885188803', '经理', '001');
INSERT INTO `workerinfo` VALUES ('2', '2', '王二', '男', '32', '13605715010', '前台接待员', '002');
INSERT INTO `workerinfo` VALUES ('3', '3', '张三', '女', '33', '18522655552', '前台接待员', '003');
INSERT INTO `workerinfo` VALUES ('4', '4', '赵四', '女', '30', '13711110850', '收银员', '004');
INSERT INTO `workerinfo` VALUES ('5', '5', '杨六', '男', '26', '19802326868', '收银员', '005');
INSERT INTO `workerinfo` VALUES ('7', '7', '张飞', '男', '56', '15666666666', '收银员', 'zhangfei');
