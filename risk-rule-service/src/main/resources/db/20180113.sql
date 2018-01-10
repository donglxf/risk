/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50515
Source Host           : 127.0.0.1:3306
Source Database       : drools_rule

Target Server Type    : MYSQL
Target Server Version : 50515
File Encoding         : 65001

Date: 2018-01-06 15:10:19
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for rule_scene_version
-- ----------------------------
DROP TABLE IF EXISTS `rule_scene_version`;
CREATE TABLE `rule_scene_version` (
  `version_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '版本记录id',
  `version` varchar(32) NOT NULL COMMENT '版本号 ',
  `type` int(6) DEFAULT NULL COMMENT '类型：1决策或评分卡2模型',
  `title` varchar(128) NOT NULL COMMENT '标题',
  `comment` varchar(512) DEFAULT NULL COMMENT '详细描述',
  `scene_id` varchar(32) DEFAULT NULL COMMENT '业务id',
  `cre_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `cre_user_id` bigint(20) DEFAULT NULL COMMENT '创建用户',
  `rule_div` text COMMENT '规则html',
  `rule_drl` text COMMENT 'rule文件内容',
  `status` tinyint(3) DEFAULT '1',
  PRIMARY KEY (`version_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
