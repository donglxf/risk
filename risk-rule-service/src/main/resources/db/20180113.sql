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


create table `risk_variable_bind` (
  `id` varchar(64) collate utf8_bin not null comment '主键,流水号',
  `sence_versionid` varchar(64) collate utf8_bin not null comment '決策版本流水',
  `variable_code` varchar(64) collate utf8_bin not null comment '变量编码',
  `variable_name` varchar(64) collate utf8_bin default null comment '变量名称',
  `data_type` varchar(32) collate utf8_bin default null comment '变量类型，与rule_entity_item_info.data_type 一致',
  `constant_id` varchar(32) collate utf8_bin default null comment '常量id，与rule_entity_item_info.constant_id 一致',
  `bind_table` varchar(64) collate utf8_bin not null comment '绑定数据表',
  `bind_column` varchar(64) collate utf8_bin not null comment '绑定数据表字段',
  `is_effect` varchar(2) collate utf8_bin not null default '0' comment '是否生效：0-有效，1-无效',
  `tmp_value` varchar(64) collate utf8_bin default null comment '用户输入值，只保存最后一次的',
  `create_user` varchar(64) collate utf8_bin default null comment '创建用户',
  `create_time` datetime not null default current_timestamp comment '创建时间',
  primary key (`id`)
) engine=innodb default charset=utf8 collate=utf8_bin;



create table `risk_rule_his_version` (
  `id` varchar(64) collate utf8_bin not null comment '主键',
  `sence_versionid` varchar(64) collate utf8_bin not null comment '決策版本流水',
  `rule_name` varchar(64) collate utf8_bin not null comment '规则名称',
  `rule_desc` varchar(64) collate utf8_bin not null comment '规则描述',
  `is_effect` varchar(2) collate utf8_bin not null default '0' comment '是否生效：0-有效，1-无效',
  `create_user` varchar(64) collate utf8_bin default null comment '创建用户',
  `create_time` datetime default null comment '创建时间',
  primary key (`id`)
) engine=innodb default charset=utf8 collate=utf8_bin;
