/*
Navicat MySQL Data Transfer

Source Server         : soms
Source Server Version : 50610
Source Host           : localhost:3306
Source Database       : shopcart

Target Server Type    : MYSQL
Target Server Version : 50610
File Encoding         : 65001

Date: 2013-06-06 10:45:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `products`
-- ----------------------------
DROP TABLE IF EXISTS `products`;
CREATE TABLE `products` (
  `pid` int(11) NOT NULL AUTO_INCREMENT,
  `pname` varchar(20) NOT NULL,
  `pdescription` varchar(200) NOT NULL,
  `pdate` date NOT NULL,
  `pprice` double NOT NULL,
  `pphoto` blob,
  `pamount` int(11) NOT NULL,
  `pnotes` varchar(20) DEFAULT NULL,
  `pprority` tinyint(4) DEFAULT NULL,
  `pdiscount` int(11) NOT NULL,
  `ptypeid` int(11) NOT NULL,
  PRIMARY KEY (`pid`),
  KEY `ptypeid` (`ptypeid`),
  CONSTRAINT `products_ibfk_1` FOREIGN KEY (`ptypeid`) REFERENCES `producttype` (`ptid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of products
-- ----------------------------
INSERT INTO `products` VALUES ('1', 'jquery高级编程', '真的很好的', '2013-06-06', '22', null, '1', null, null, '100', '1');

-- ----------------------------
-- Table structure for `producttype`
-- ----------------------------
DROP TABLE IF EXISTS `producttype`;
CREATE TABLE `producttype` (
  `ptid` int(11) NOT NULL AUTO_INCREMENT,
  `ptname` varchar(30) NOT NULL,
  `ptnote` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`ptid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of producttype
-- ----------------------------
INSERT INTO `producttype` VALUES ('1', '书籍', '中国IT书籍');

-- ----------------------------
-- Table structure for `transactions`
-- ----------------------------
DROP TABLE IF EXISTS `transactions`;
CREATE TABLE `transactions` (
  `tid` int(11) NOT NULL AUTO_INCREMENT,
  `tuid` int(11) NOT NULL,
  `tpid` int(11) NOT NULL,
  `tdate` date NOT NULL,
  `ttime` time NOT NULL,
  `tshiped` int(11) DEFAULT '0',
  `tshipdate` date DEFAULT NULL,
  `tshiptime` time DEFAULT NULL,
  `tamount` int(11) NOT NULL,
  PRIMARY KEY (`tid`),
  KEY `tuid_fk` (`tuid`),
  KEY `tpid_fk` (`tpid`),
  CONSTRAINT `tpid_fk` FOREIGN KEY (`tpid`) REFERENCES `products` (`pid`),
  CONSTRAINT `tuid_fk` FOREIGN KEY (`tuid`) REFERENCES `userinfo` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of transactions
-- ----------------------------
INSERT INTO `transactions` VALUES ('1', '1', '1', '2013-06-06', '10:44:45', '0', null, null, '1');

-- ----------------------------
-- Table structure for `userinfo`
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo` (
  `umail` varchar(20) NOT NULL,
  `uid` int(11) NOT NULL,
  `uname` varchar(12) NOT NULL,
  `upassword` varchar(12) NOT NULL,
  `uaddress` varchar(50) DEFAULT NULL,
  `utele` varchar(20) DEFAULT NULL,
  `umobile` varchar(13) DEFAULT NULL,
  `uzip` varchar(6) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userinfo
-- ----------------------------
INSERT INTO `userinfo` VALUES ('admin', '1', 'admin', 'admin', null, null, null, null);
INSERT INTO `userinfo` VALUES ('root', '3506402', '', 'root', '', '', '', '');
