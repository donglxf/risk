DROP  table rule_action_info;
create table if not exists `rule_action_info` (
  `action_id` bigint(20) not null comment '主键',
  `action_type` int(11) not null comment '动作类型(1实现2自身)',
  `action_name` varchar(200) not null comment '动作名称',
  `action_desc` varchar(3000) default null comment '动作描述',
  `action_class` varchar(200) not null comment '动作实现类(包路径)',
  `is_effect` int(11) not null default '1' comment '是否有效',
  `cre_user_id` bigint(20) not null comment '创建人',
  `cre_time` datetime not null comment '创建时间',
  `remark` varchar(3000) default null comment '备注',
  primary key (`action_id`),
  key `action_type` (`action_type`),
  key `action_name` (`action_name`)
) engine=innodb default charset=utf8 comment='规则动作定义信息';

DROP  table rule_action_param_info;
create table if not exists `rule_action_param_info` (
  `action_param_id` bigint(20) not null comment '主键',
  `action_id` bigint(20) not null comment '动作id',
  `action_param_name` varchar(200) not null comment '参数名称',
  `action_param_desc` varchar(3000) default null comment '参数描述',
  `param_identify` varchar(200) not null comment '标识',
  `is_effect` int(11) not null default '1' comment '是否有效',
  `cre_user_id` bigint(20) not null comment '创建人',
  `cre_time` datetime not null comment '创建时间',
  `remark` varchar(3000) default null comment '备注',
  primary key (`action_param_id`),
  key `action_id` (`action_id`),
  key `action_param_name` (`action_param_name`),
  key `param_identify` (`param_identify`)
) engine=innodb default charset=utf8 comment='动作参数信息表';



-- 导出  表 bluesky.rule_action_param_value_info 结构
create table if not exists `rule_action_param_value_info` (
  `action_param_value_id` bigint(20) not null comment '主键',
  `rule_action_rel_id` bigint(20) not null comment '动作规则关系主键',
  `action_param_id` bigint(20) not null comment '动作参数',
  `param_value` varchar(200) not null comment '参数值',
  `is_effect` int(11) not null default '1' comment '是否有效',
  `cre_user_id` bigint(20) not null comment '创建人',
  `cre_time` datetime not null comment '创建时间',
  `remark` varchar(3000) default null comment '备注',
  primary key (`action_param_value_id`),
  key `rule_action_rel_id` (`rule_action_rel_id`),
  key `action_param_id` (`action_param_id`)
) engine=innodb default charset=utf8 comment='动作参数对应的属性值信息表';



-- 导出  表 bluesky.rule_action_rule_rel 结构
create table if not exists `rule_action_rule_rel` (
  `rule_action_rel_id` bigint(20) not null comment '主键',
  `action_id` bigint(20) not null comment '动作',
  `rule_id` bigint(20) not null comment '规则',
  `is_effect` int(11) not null default '1' comment '是否有效',
  `cre_user_id` bigint(20) not null comment '创建人',
  `cre_time` datetime not null comment '创建时间',
  `remark` varchar(3000) default null comment '备注',
  primary key (`rule_action_rel_id`),
  key `action_id` (`action_id`),
  key `rule_id` (`rule_id`)
) engine=innodb default charset=utf8 comment='动作与规则信息关系表';



-- 导出  表 bluesky.rule_condition_info 结构
create table if not exists `rule_condition_info` (
  `condition_id` bigint(20) not null comment '主键',
  `rule_id` bigint(20) not null comment '规则',
  `condition_name` varchar(3000) not null comment '条件名称',
  `condition_expression` varchar(3000) not null comment '条件表达式',
  `condition_desc` varchar(3000) not null comment '条件描述',
  `is_effect` int(11) not null default '1' comment '是否有效',
  `cre_user_id` bigint(20) not null comment '创建人',
  `cre_time` datetime not null comment '创建时间',
  `remark` varchar(3000) default null comment '备注',
  primary key (`condition_id`),
  key `rule_id` (`rule_id`),
  key `condition_name` (`condition_name`(255))
) engine=innodb default charset=utf8 comment='规则条件信息表';


-- 导出  表 bluesky.rule_entity_info 结构
create table if not exists `rule_entity_info` (
  `entity_id` bigint(20) not null comment '主键',
  `entity_name` varchar(50) not null comment '名称',
  `entity_desc` varchar(3000) not null comment '描述',
  `entity_identify` varchar(50) not null comment '标识',
  `pkg_name` varchar(200) not null comment '包路径',
  `cre_user_id` bigint(20) not null comment '创建人',
  `cre_time` datetime not null comment '创建时间',
  `is_effect` int(11) not null default '1' comment '是否有效(1是0否)',
  `remark` varchar(3000) default null comment '备注',
  primary key (`entity_id`),
  key `entity_identify` (`entity_identify`),
  key `entity_name` (`entity_name`)
) engine=innodb default charset=utf8 comment='规则引擎实体信息表';


-- 导出  表 bluesky.rule_entity_item_info 结构
create table if not exists `rule_entity_item_info` (
  `item_id` bigint(20) not null comment '主键',
  `entity_id` bigint(20) not null comment '实体id',
  `item_name` varchar(50) not null comment '字段名称',
  `item_identify` varchar(50) not null comment '字段标识',
  `item_desc` varchar(50) default null comment '属性描述',
  `cre_user_id` bigint(20) not null comment '创建人',
  `cre_time` datetime not null comment '创建时间',
  `is_effect` int(11) not null default '1' comment '是否有效',
  `remark` varchar(50) default null comment '备注',
  primary key (`item_id`),
  key `entity_id` (`entity_id`),
  key `item_identify` (`item_identify`)
) engine=innodb default charset=utf8 comment='实体属性信息';



-- 导出  表 bluesky.rule_info 结构
create table if not exists `rule_info` (
  `rule_id` bigint(20) not null comment '主键',
  `scene_id` bigint(20) not null comment '场景',
  `rule_name` varchar(50) not null comment '名称',
  `rule_desc` varchar(3000) default null comment '描述',
  `rule_enabled` int(11) not null default '1' comment '是否启用',
  `is_effect` int(11) not null default '1' comment '是否有效',
  `cre_user_id` bigint(20) not null comment '创建人',
  `cre_time` datetime not null comment '创建时间',
  `remark` varchar(3000) default null comment '备注',
  primary key (`rule_id`),
  key `scene_id` (`scene_id`),
  key `rule_name` (`rule_name`)
) engine=innodb default charset=utf8 comment='规则信息';


-- 导出  表 bluesky.rule_property_info 结构
create table if not exists `rule_property_info` (
  `rule_property_id` bigint(20) not null comment '主键',
  `rule_property_identify` varchar(200) not null comment '标识',
  `rule_property_name` varchar(200) not null comment '名称',
  `rule_property_desc` varchar(3000) default null comment '描述',
  `default_value` varchar(200) default null comment '默认值',
  `is_effect` int(11) not null default '1' comment '是否有效',
  `remark` varchar(3000) default null comment '备注',
  primary key (`rule_property_id`),
  key `rule_property_identify` (`rule_property_identify`),
  key `rule_property_name` (`rule_property_name`)
) engine=innodb default charset=utf8 comment='规则基础属性信息表';


-- 导出  表 bluesky.rule_property_rel 结构
create table if not exists `rule_property_rel` (
  `rule_pro_rel_id` bigint(20) not null comment '主键',
  `rule_id` bigint(20) not null comment '规则',
  `rule_property_id` bigint(20) not null comment '规则属性',
  `rule_property_value` varchar(200) not null comment '规则属性值',
  primary key (`rule_pro_rel_id`),
  key `rule_id` (`rule_id`),
  key `rule_property_id` (`rule_property_id`)
) engine=innodb default charset=utf8 comment='规则属性配置表';



-- 导出  表 bluesky.rule_scene_entity_rel 结构
create table if not exists `rule_scene_entity_rel` (
  `scene_entity_rel_id` bigint(20) not null comment '主键',
  `scene_id` bigint(20) default null comment '场景',
  `entity_id` bigint(20) default null comment '实体',
  primary key (`scene_entity_rel_id`),
  key `scene_id` (`scene_id`),
  key `entity_id` (`entity_id`)
) engine=innodb default charset=utf8 comment='场景实体关联表';



-- 导出  表 bluesky.rule_scene_info 结构
create table if not exists `rule_scene_info` (
  `scene_id` bigint(20) not null comment '主键',
  `scene_identify` varchar(50) not null comment '标识',
  `scene_type` int(11) default null comment '类型(暂不使用)',
  `scene_name` varchar(50) not null comment '名称',
  `scene_desc` varchar(3000) default null comment '描述',
  `is_effect` int(11) not null default '1' comment '是否有效',
  `cre_user_id` bigint(20) not null comment '创建人',
  `cre_time` datetime not null comment '创建时间',
  `remark` varchar(3000) default null comment '备注',
  primary key (`scene_id`),
  key `scene_identify` (`scene_identify`),
  key `scene_type` (`scene_type`),
  key `scene_name` (`scene_name`)
) engine=innodb default charset=utf8 comment='规则引擎使用场景';


-- 导出  表 bluesky.rule_variable 结构
create table if not exists `rule_variable` (
  `variable_id` bigint(20) not null comment '主键',
  `variable_name` varchar(200) not null comment '变量名称',
  `variable_type` int(11) not null comment '变量类型（1条件2动作）',
  `default_value` varchar(200) not null comment '默认值',
  `value_type` int(11) not null comment '数值类型（ 1字符型 2数字型 3 日期型）',
  `variable_value` varchar(200) not null comment '变量值',
  `is_effect` int(11) not null default '1' comment '是否有效',
  `cre_user_id` bigint(20) not null comment '创建人',
  `cre_time` datetime not null comment '创建时间',
  `remark` varchar(3000) default null comment '备注',
  primary key (`variable_id`),
  key `variable_type` (`variable_type`),
  key `value_type` (`value_type`),
  key `variable_name` (`variable_name`)
) engine=innodb default charset=utf8 comment='规则引擎常用变量';

