/*
Navicat MySQL Data Transfer

Source Server         : soms
Source Server Version : 50610
Source Host           : localhost:3306
Source Database       : myssh

Target Server Type    : MYSQL
Target Server Version : 50610
File Encoding         : 65001

Date: 2013-06-26 13:54:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `account`
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `id` int(64) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  `balance` double(8,0) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for `stock`
-- ----------------------------
DROP TABLE IF EXISTS `stock`;
CREATE TABLE `stock` (
  `id` int(64) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  `qty` double(8,0) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
