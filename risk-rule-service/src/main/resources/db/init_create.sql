create table `risk_business` (
  `business_id` bigint(20) not null auto_increment,
  `business_name` varchar(32) character set utf8 collate utf8_general_ci null default null comment '业务线名',
  `business_desc` varchar(255) character set utf8 collate utf8_general_ci null default null comment '描述',
  `status` tinyint(3) null default 1 comment '状态：1正常0禁用',
  `cre_user_id` varchar(32) character set utf8 collate utf8_general_ci null default null comment '创建人',
  `cre_time` datetime not null comment '创建时间',
  primary key (`business_id`)
) engine = innodb character set = utf8 collate utf8_general_ci auto_increment = 4;


create table `risk_drools_log` (
  `id` bigint(20) not null,
  `batch_id` bigint(20) DEFAULT  null COMMENT '验证批次号',
  `procinst_id` bigint(20) null default null comment '模型实例id',
  `model_name` varchar(100) character set utf8 collate utf8_general_ci null default null comment '模型名',
  `sence_versionid` varchar(64) character set utf8 collate utf8_general_ci not null comment '決策版本流水',
  `in_paramter` longtext character set utf8 collate utf8_general_ci null comment '入参',
  `out_paramter` longtext character set utf8 collate utf8_general_ci null comment '计算结果',
  `execute_total` int(11) null default null comment '命中规则总数',
  `type` varchar(2) character set utf8 collate utf8_general_ci null default null comment '决策执行类型：0-直接调用，1-模型调用',
  `create_time` datetime null default current_timestamp comment '插入时间',
  execute_time BIGINT(20) COMMENT '执行时间',
  primary key (`id`)
) engine = innodb character set = utf8 collate utf8_general_ci;

create table `risk_drools_detail_log` (
  `id` bigint(20) not null,
  `drools_logid` bigint(20) not null,
  `execute_rulename` varchar(100) character set utf8 collate utf8_general_ci null default null comment '命中的规则',
  primary key (`id`)
) engine = innodb character set = utf8 collate utf8_general_ci;


create table `risk_model_release` (
  `id` varchar(64) character set utf8 collate utf8_bin not null comment '主键',
  `model_procdef_id` varchar(64) character set utf8 collate utf8_bin not null comment '模型定义id，与 act_re_procdef.id_ 关联',
  `model_name` varchar(255) character set utf8 collate utf8_bin null default null comment '模型名称',
  `model_version` varchar(64) character set utf8 collate utf8_bin null default null comment '模型版本',
  `model_category` varchar(64) character set utf8 collate utf8_bin null default null comment '模型分类',
  `version_type` varchar(2) character set utf8 collate utf8_bin null default null comment '版本类型，0-测试版，1-正式版',
  `is_validate` char(1) character set utf8 collate utf8_bin not null default '0' comment '是否验证通过： 0-待验证，1-验证通过，2-验证不通过；默认为0;',
  `is_approve` char(1) character set utf8 collate utf8_bin not null default '0' comment '是否审核通过：0-待审核，1-审核通过，2-审核不通过；默认为0;',
  `is_effect` varchar(2) character set utf8 collate utf8_bin not null default '0' comment '是否生效：0-有效，1-无效',
  `update_time` datetime null default null comment '更新时间',
  `update_user` varchar(64) character set utf8 collate utf8_bin null default null comment '更新用户',
  `create_time` datetime not null comment '创建时间',
  `create_user` varchar(64) character set utf8 collate utf8_bin null default null comment '创建用户',
  primary key (`id`)
) engine = innodb character set = utf8 collate utf8_bin;

create table `risk_model_sence` (
  `id` bigint(20) not null comment '主键',
  `model_procdef_id` varchar(64) character set utf8 collate utf8_bin not null comment '模型定义id',
  `sence_version_id` bigint(20) not null comment '決策版本流水号',
  `is_effect` varchar(2) character set utf8 collate utf8_bin not null default '0' comment '是否生效：0-有效，1-无效',
  `create_time` datetime not null comment '创建时间',
  `create_user` varchar(64) character set utf8 collate utf8_bin null default null comment '创建用户',
  primary key (`id`)
) engine = innodb character set = utf8 collate utf8_bin;

create table `risk_rule_his_version` (
  `id` bigint(20) not null comment '主键',
  `sence_version_id` bigint(20) not null comment '決策版本流水',
  `rule_name` varchar(64) character set utf8 collate utf8_bin not null comment '规则名称',
  `rule_desc` varchar(256) character set utf8 collate utf8_bin not null comment '规则描述',
  `is_effect` varchar(2) character set utf8 collate utf8_bin not null default '0' comment '是否生效：0-有效，1-无效',
  `create_user` varchar(64) character set utf8 collate utf8_bin null default null comment '创建用户',
  `create_time` datetime null default null comment '创建时间',
  primary key (`id`)
) engine = innodb character set = utf8 collate utf8_bin;

create table `risk_sence_verfication_batch` (
  `id` bigint(20) not null comment '主键,批次号',
  `sence_version_id` varchar(64) character set utf8 collate utf8_bin not null comment '決策版本流水',
  `batch_size` int(12) not null comment '批次大小',
  `verfication_type` varchar(2) comment '是否生效：0-手动，1-自动',
  `create_time` TIMESTAMP DEFAULT now() null comment '创建时间',
  `create_user` varchar(64) character set utf8 collate utf8_bin null default null comment '创建用户',
  `end_time` TIMESTAMP  COMMENT '结束时间',
  primary key (`id`)
) engine = innodb character set = utf8 collate utf8_bin;

create table `risk_test_drools_detail_log` (
  `id` bigint(20) not null,
  `drools_logid` bigint(20) not null,
  `execute_rulename` varchar(100) character set utf8 collate utf8_general_ci null default null comment '命中的规则',
  primary key (`id`)
) engine = innodb character set = utf8 collate utf8_general_ci;

create table `risk_test_drools_log` (
  `id` bigint(20) not null,
  `batch_id` bigint(20) DEFAULT  null COMMENT '验证批次号',
  `procinst_id` bigint(20) null default null comment '模型实例id',
  `model_name` varchar(100) character set utf8 collate utf8_general_ci null default null comment '模型名',
  `sence_versionid` varchar(64) character set utf8 collate utf8_general_ci not null comment '決策版本流水',
  `in_paramter` longtext character set utf8 collate utf8_general_ci null comment '入参',
  `out_paramter` longtext character set utf8 collate utf8_general_ci null comment '计算结果',
  `execute_total` int(11) null default null comment '命中规则总数',
  `type` varchar(2) character set utf8 collate utf8_general_ci null default null comment '决策执行类型：0-直接调用，1-模型调用',
  `create_time` datetime null default current_timestamp comment '插入时间',
  primary key (`id`)
) engine = innodb character set = utf8 collate utf8_general_ci;



create table `risk_variable_bind` (
  `id` bigint(20) not null comment '主键,流水号',
  `sence_version_id` bigint(20) not null comment '決策版本流水',
  `variable_code` varchar(64) character set utf8 collate utf8_bin not null comment '变量编码',
  `variable_name` varchar(64) character set utf8 collate utf8_bin null default null comment '变量名称',
  `data_type` varchar(32) character set utf8 collate utf8_bin null default null comment '变量类型，与rule_entity_item_info.data_type 一致',
  `constant_id` bigint(20) null default null comment '常量id，与rule_entity_item_info.constant_id 一致',
  `bind_table` varchar(64) character set utf8 collate utf8_bin null default null comment '绑定数据表',
  `bind_column` varchar(64) character set utf8 collate utf8_bin null default null comment '绑定数据表字段',
  `is_effect` varchar(2) character set utf8 collate utf8_bin null default '0' comment '是否生效：0-有效，1-无效',
  `tmp_value` varchar(64) character set utf8 collate utf8_bin null default null comment '用户输入值，只保存最后一次的',
  `create_user` varchar(64) character set utf8 collate utf8_bin null default null comment '创建用户',
  `create_time` datetime not null comment '创建时间',
  primary key (`id`)
) engine = innodb character set = utf8 collate utf8_bin;

CREATE TABLE `rule_action_info` (
  `action_id` bigint(20) NOT NULL COMMENT '主键',
  `action_type` int(11) NOT NULL COMMENT '动作类型(1实现2自身)',
  `action_name` varchar(200) NOT NULL COMMENT '动作名称',
  `action_desc` varchar(3000) DEFAULT NULL COMMENT '动作描述',
  `action_class` varchar(200) NOT NULL COMMENT '动作实现类(包路径)',
  `is_effect` int(11) NOT NULL DEFAULT '1' COMMENT '是否有效',
  `cre_user_id` bigint(20) NOT NULL COMMENT '创建人',
  `cre_time` datetime NOT NULL COMMENT '创建时间',
  `remark` varchar(3000) DEFAULT NULL COMMENT '备注',
  `business_id` varchar(32) DEFAULT '0' COMMENT '业务线id',
  `action_method` varchar(255) DEFAULT NULL COMMENT '动作执行方法'
  PRIMARY KEY (`action_id`),
  KEY `action_type` (`action_type`),
  KEY `action_name` (`action_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='规则动作定义信息';


create table `rule_action_param_info` (
  `action_param_id` bigint(20) not null comment '主键',
  `action_id` bigint(20) not null comment '动作id',
  `action_param_name` varchar(200) character set utf8 collate utf8_general_ci not null comment '参数名称',
  `action_param_desc` varchar(3000) character set utf8 collate utf8_general_ci null default null comment '参数描述',
  `param_identify` varchar(200) character set utf8 collate utf8_general_ci not null comment '标识',
  `is_effect` int(11) not null default 1 comment '是否有效',
  `cre_user_id` bigint(20) not null comment '创建人',
  `cre_time` datetime not null comment '创建时间',
  `remark` varchar(3000) character set utf8 collate utf8_general_ci null default null comment '备注',
  primary key (`action_param_id`),
  index `action_id`(`action_id`),
  index `action_param_name`(`action_param_name`),
  index `param_identify`(`param_identify`)
) engine = innodb character set = utf8 collate utf8_general_ci comment '动作参数信息表';

create table `rule_action_param_value_info` (
  `action_param_value_id` bigint(20) not null comment '主键',
  `rule_action_rel_id` bigint(20) not null comment '动作规则关系主键',
  `action_param_id` bigint(20) not null comment '动作参数',
  `param_value` varchar(200) character set utf8 collate utf8_general_ci not null comment '参数值',
  `param_text` varchar(255) character set utf8 collate utf8_general_ci null default null comment '参数文字描述',
  `is_effect` int(11) not null default 1 comment '是否有效',
  `cre_user_id` bigint(20) not null comment '创建人',
  `cre_time` datetime not null comment '创建时间',
  `remark` varchar(3000) character set utf8 collate utf8_general_ci null default null comment '备注',
  primary key (`action_param_value_id`),
  index `rule_action_rel_id`(`rule_action_rel_id`),
  index `action_param_id`(`action_param_id`)
) engine = innodb character set = utf8 collate utf8_general_ci comment '动作参数对应的属性值信息表';

create table `rule_action_rule_rel` (
  `rule_action_rel_id` bigint(20) not null comment '主键',
  `action_id` bigint(20) not null comment '动作',
  `rule_id` bigint(20) not null comment '规则',
  `is_effect` int(11) not null default 1 comment '是否有效',
  `cre_user_id` bigint(20) not null comment '创建人',
  `cre_time` datetime not null comment '创建时间',
  `remark` varchar(3000) character set utf8 collate utf8_general_ci null default null comment '备注',
  primary key (`rule_action_rel_id`),
  index `action_id`(`action_id`),
  index `rule_id`(`rule_id`)
) engine = innodb character set = utf8 collate utf8_general_ci comment '动作与规则信息关系表';

create table `rule_condition_info` (
  `condition_id` bigint(20) not null comment '主键',
  `rule_id` bigint(20) not null comment '规则',
  `condition_name` varchar(3000) character set utf8 collate utf8_general_ci not null comment '条件名称',
  `condition_expression` varchar(3000) character set utf8 collate utf8_general_ci not null comment '条件表达式',
  `condition_desc` varchar(3000) character set utf8 collate utf8_general_ci not null comment '条件描述',
  `is_effect` int(11) not null default 1 comment '是否有效',
  `cre_user_id` bigint(20) not null comment '创建人',
  `cre_time` datetime not null comment '创建时间',
  `remark` varchar(3000) character set utf8 collate utf8_general_ci null default null comment '备注',
  `val` varchar(255) character set utf8 collate utf8_general_ci null default null comment '值',
  `hasvariable` tinyint(3) null default 0 comment '1常量0输入值',
  primary key (`condition_id`),
  index `rule_id`(`rule_id`)
) engine = innodb character set = utf8 collate utf8_general_ci comment '规则条件信息表';

create table `rule_constant_info` (
  `con_id` bigint(20) not null comment '主键',
  `con_key` varchar(200) character set utf8 collate utf8_general_ci not null comment '常量类别',
  `con_name` varchar(200) character set utf8 collate utf8_general_ci not null comment '常量名',
  `con_type` varchar(200) character set utf8 collate utf8_general_ci not null default '0' comment '常量类型',
  `con_code` varchar(200) character set utf8 collate utf8_general_ci not null comment '变量code',
  `is_effect` int(11) not null default 1 comment '是否有效',
  `cre_user_id` bigint(20) not null default 1 comment '创建人',
  `cre_time` timestamp null default current_timestamp comment '创建时间',
  `remark` varchar(3000) character set utf8 collate utf8_general_ci not null default '' comment '备注',
  `business_id` varchar(32) character set utf8 collate utf8_general_ci null default '0' comment '业务线id',
  primary key (`con_id`)
) engine = innodb character set = utf8 collate utf8_general_ci;

create table `rule_entity_info` (
  `entity_id` bigint(20) not null comment '主键',
  `entity_name` varchar(50) character set utf8 collate utf8_general_ci not null comment '名称',
  `entity_desc` varchar(3000) character set utf8 collate utf8_general_ci not null comment '描述',
  `entity_identify` varchar(50) character set utf8 collate utf8_general_ci not null comment '标识',
  `pkg_name` varchar(200) character set utf8 collate utf8_general_ci null default null comment '包路径',
  `cre_user_id` bigint(20) null default null comment '创建人',
  `cre_time` datetime not null comment '创建时间',
  `is_effect` int(11) not null default 1 comment '是否有效(1是0否)',
  `remark` varchar(3000) character set utf8 collate utf8_general_ci null default null comment '备注',
  `business_id` varchar(32) character set utf8 collate utf8_general_ci null default '0' comment '业务线id',
  primary key (`entity_id`),
  index `entity_identify`(`entity_identify`),
  index `entity_name`(`entity_name`)
) engine = innodb character set = utf8 collate utf8_general_ci comment '规则引擎实体信息表';

create table `rule_entity_item_info` (
  `item_id` bigint(20) not null comment '主键',
  `entity_id` bigint(20) not null comment '实体id',
  `item_name` varchar(50) character set utf8 collate utf8_general_ci not null comment '字段名称',
  `item_identify` varchar(50) character set utf8 collate utf8_general_ci not null comment '字段标识',
  `item_desc` varchar(50) character set utf8 collate utf8_general_ci null default null comment '属性描述',
  `cre_user_id` bigint(20) null default null comment '创建人',
  `cre_time` datetime null default null comment '创建时间',
  `is_effect` int(11) not null default 1 comment '是否有效',
  `remark` varchar(50) character set utf8 collate utf8_general_ci null default null comment '备注',
  `data_type` varchar(16) character set utf8 collate utf8_general_ci null default null comment '数据类型 ',
  `constant_id` bigint(20) null default null comment '常量id',
  primary key (`item_id`),
  index `entity_id`(`entity_id`),
  index `item_identify`(`item_identify`)
) engine = innodb character set = utf8 collate utf8_general_ci comment '实体属性信息';

create table `rule_group` (
  `rule_group_id` bigint(20) not null auto_increment comment '规则分组id',
  `rule_id` bigint(20) not null comment '规则id',
  `index` int(6) not null comment '序号，排序',
  `updatetime` timestamp null default null comment '更新时间',
  `weight` double(11, 2) null default 0.00 comment '权值',
  `scene_id` bigint(20) null default null comment '场景id',
  `name` varchar(64) character set utf8 collate utf8_general_ci null default null comment '名称',
  primary key (`rule_group_id`)
) engine = innodb character set = utf8 collate utf8_general_ci auto_increment = 1;

create table `rule_info` (
  `rule_id` bigint(20) not null comment '主键',
  `scene_id` bigint(20) not null comment '场景',
  `rule_name` varchar(50) character set utf8 collate utf8_general_ci not null comment '名称',
  `rule_desc` varchar(3000) character set utf8 collate utf8_general_ci null default null comment '描述',
  `rule_enabled` int(11) not null default 1 comment '是否启用',
  `is_effect` int(11) not null default 1 comment '是否有效',
  `cre_user_id` bigint(20) not null comment '创建人',
  `cre_time` datetime not null comment '创建时间',
  `remark` varchar(3000) character set utf8 collate utf8_general_ci null default null comment '备注',
  primary key (`rule_id`),
  index `scene_id`(`scene_id`),
  index `rule_name`(`rule_name`)
) engine = innodb character set = utf8 collate utf8_general_ci comment '规则信息';

create table `rule_property_info` (
  `rule_property_id` bigint(20) not null comment '主键',
  `rule_property_identify` varchar(200) character set utf8 collate utf8_general_ci not null comment '标识',
  `rule_property_name` varchar(200) character set utf8 collate utf8_general_ci not null comment '名称',
  `rule_property_desc` varchar(3000) character set utf8 collate utf8_general_ci null default null comment '描述',
  `default_value` varchar(200) character set utf8 collate utf8_general_ci null default null comment '默认值',
  `is_effect` int(11) not null default 1 comment '是否有效',
  `remark` varchar(3000) character set utf8 collate utf8_general_ci null default null comment '备注',
  primary key (`rule_property_id`),
  index `rule_property_identify`(`rule_property_identify`),
  index `rule_property_name`(`rule_property_name`)
) engine = innodb character set = utf8 collate utf8_general_ci comment '规则基础属性信息表';

create table `rule_property_rel` (
  `rule_pro_rel_id` bigint(20) not null comment '主键',
  `rule_id` bigint(20) not null comment '规则',
  `rule_property_id` bigint(20) not null comment '规则属性',
  `rule_property_value` varchar(200) character set utf8 collate utf8_general_ci not null comment '规则属性值',
  primary key (`rule_pro_rel_id`),
  index `rule_id`(`rule_id`),
  index `rule_property_id`(`rule_property_id`)
) engine = innodb character set = utf8 collate utf8_general_ci comment '规则属性配置表';

create table `rule_scene_entity_rel` (
  `scene_entity_rel_id` bigint(20) not null comment '主键',
  `scene_id` bigint(20) null default null comment '场景',
  `entity_id` bigint(20) null default null comment '实体',
  primary key (`scene_entity_rel_id`),
  index `scene_id`(`scene_id`),
  index `entity_id`(`entity_id`)
) engine = innodb character set = utf8 collate utf8_general_ci comment '场景实体关联表';

create table `rule_scene_info` (
  `scene_id` bigint(20) not null comment '主键',
  `scene_identify` varchar(50) character set utf8 collate utf8_general_ci not null comment '标识',
  `scene_type` int(11) null default null comment '类型(暂不使用)',
  `scene_name` varchar(50) character set utf8 collate utf8_general_ci not null comment '名称',
  `scene_desc` varchar(3000) character set utf8 collate utf8_general_ci null default null comment '描述',
  `is_effect` int(11) not null default 1 comment '是否有效',
  `cre_user_id` bigint(20) null default null comment '创建人',
  `cre_time` datetime not null comment '创建时间',
  `remark` varchar(3000) character set utf8 collate utf8_general_ci null default null comment '备注',
  `version` varchar(32) character set utf8 collate utf8_general_ci null default null comment '版本号',
  `business_id` varchar(32) character set utf8 collate utf8_general_ci null default '0' comment '业务线id',
  primary key (`scene_id`),
  index `scene_identify`(`scene_identify`),
  index `scene_type`(`scene_type`),
  index `scene_name`(`scene_name`)
) engine = innodb character set = utf8 collate utf8_general_ci comment '规则引擎使用场景';

create table `rule_scene_item_rel` (
  `id` bigint(20) not null auto_increment,
  `scene_id` bigint(20) null default null comment '场景id',
  `entity_item_id` bigint(20) null default null comment '变量id',
  `sort` int(11) null default null comment '排序',
  `cre_time` timestamp null default null,
  `entity_id` bigint(20) null default null comment '实体类id',
  primary key (`id`)
) engine = innodb character set = utf8 collate utf8_general_ci auto_increment = 187;

create table `rule_scene_version` (
  `version_id` bigint(20) not null auto_increment comment '版本记录id',
  `version` varchar(32) character set utf8 collate utf8_general_ci not null comment '版本号 ',
  `official_version` varchar(6) character set utf8 collate utf8_general_ci null default null comment '正式版本号',
  `type` int(6) null default null comment '0表示测试版 1表示正式版',
  `title` varchar(128) character set utf8 collate utf8_general_ci not null comment '标题',
  `comment` varchar(512) character set utf8 collate utf8_general_ci null default null comment '详细描述',
  `scene_identify` varchar(64) character set utf8 collate utf8_general_ci null default null comment '场景code',
  `scene_id` varchar(32) character set utf8 collate utf8_general_ci null default null comment '业务id',
  `cre_time` timestamp not null default current_timestamp comment '创建时间',
  `cre_user_id` bigint(20) null default null comment '创建用户',
  `rule_div` text character set utf8 collate utf8_general_ci null comment '规则html',
  `rule_drl` text character set utf8 collate utf8_general_ci null comment 'rule文件内容',
  `status` tinyint(3) null default 1 comment '是否启用',
  `test_status` tinyint(3) null default null comment '测试是否通过，1-通过，0-未通过',
  `business_type` varchar(100) character set utf8 collate utf8_general_ci null default null comment '业务类型，1-评分卡，2-决策表',
  `business_line` varchar(100) character set utf8 collate utf8_general_ci null default null comment '业务线，1-房速贷，2-现金贷',
  `is_bind_var` varchar(2) character set utf8 collate utf8_general_ci null default null comment '是否绑定变量，1-绑定，0-未绑定',
  primary key (`version_id`)
) engine = innodb character set = utf8 collate utf8_general_ci auto_increment = 4;

create table `rule_variable` (
  `variable_id` bigint(20) not null comment '主键',
  `variable_name` varchar(200) character set utf8 collate utf8_general_ci not null comment '变量名称',
  `variable_type` int(11) not null comment '变量类型（1条件2动作）',
  `default_value` varchar(200) character set utf8 collate utf8_general_ci not null comment '默认值',
  `value_type` int(11) not null comment '数值类型（ 1字符型 2数字型 3 日期型）',
  `variable_value` varchar(200) character set utf8 collate utf8_general_ci not null comment '变量值',
  `is_effect` int(11) not null default 1 comment '是否有效',
  `cre_user_id` bigint(20) not null comment '创建人',
  `cre_time` datetime not null comment '创建时间',
  `remark` varchar(3000) character set utf8 collate utf8_general_ci null default null comment '备注',
  primary key (`variable_id`),
  index `variable_type`(`variable_type`),
  index `value_type`(`value_type`),
  index `variable_name`(`variable_name`)
) engine = innodb character set = utf8 collate utf8_general_ci comment '规则引擎常用变量';

CREATE TABLE `risk_model_task` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `model_procdef_id` varchar(64) NOT NULL COMMENT '模型id',
  `corn_text` varchar(200) DEFAULT NULL COMMENT 'corn表达式',
  `task_status` varchar(2) DEFAULT NULL COMMENT '任务状态1停止2启动',
  `update_time` datetime DEFAULT NULL COMMENT '最后更新时间',
  `update_user` varchar(64) DEFAULT NULL COMMENT '最后更新人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(64) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='风控模型离线任务表';


CREATE TABLE `risk_rule_action_version`  (
  `risk_rule_action_version_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '动作版本关联id',
  `version_id` bigint(20) NOT NULL COMMENT '版本id',
  `action_class` varchar(168) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '实体类的名',
  `action_id` bigint(20) DEFAULT NULL COMMENT '动作id',
  PRIMARY KEY (`risk_rule_action_version_id`) USING BTREE
) ENGINE = InnoDB  DEFAULT CHARSET=utf8 COMMENT='规则版本记录表';




INSERT INTO rule_action_info(action_id, action_type, action_name, action_desc, action_class, is_effect, cre_user_id, cre_time, remark, business_id, action_method) VALUES (3, 1, '评分卡', '评分卡类型', 'com.ht.risk.service.impl.ruleaction.TestActionImpl', 1, 1, '2018-01-24 18:34:35', '不可以删除', '5', 'grade');
INSERT INTO rule_action_info(action_id, action_type, action_name, action_desc, action_class, is_effect, cre_user_id, cre_time, remark, business_id, action_method) VALUES (4, 1, '统计', '统计分数', 'com.ht.risk.service.impl.ruleaction.ExcuteTotalScoreServiceImpl', 1, 1, '2018-02-05 09:33:55', 'asa', '5', 'statistics');
INSERT INTO rule_action_info(action_id, action_type, action_name, action_desc, action_class, is_effect, cre_user_id, cre_time, remark, business_id, action_method) VALUES (5, 1, '进件', '进件', 'com.ht.risk.service.impl.ruleaction.IntopiecesServiceImpl', 1, 1, '2018-01-24 18:34:50', NULL, '5', 'intoPieces');
INSERT INTO rule_action_info(action_id, action_type, action_name, action_desc, action_class, is_effect, cre_user_id, cre_time, remark, business_id, action_method) VALUES (960680035971096578, 1, '禁入', '禁入类', 'com.ht.risk.service.impl.ruleaction.ForbidServiceImpl', 1, 1, '2018-02-06 09:34:30', NULL, '5', 'forbid');
INSERT INTO rule_action_info(action_id, action_type, action_name, action_desc, action_class, is_effect, cre_user_id, cre_time, remark, business_id, action_method) VALUES (960709401115484162, 1, '降低抵押率类', '降低抵押率类', 'com.ht.risk.service.impl.ruleaction.LowerCollateralServiceImpl', 1, 1, '2018-02-06 11:31:15', NULL, '5', 'lowerCollateral');
INSERT INTO rule_action_info(action_id, action_type, action_name, action_desc, action_class, is_effect, cre_user_id, cre_time, remark, business_id, action_method) VALUES (967243425069694977, 1, '动作提示信息', '提示类动作', 'com.ht.risk.service.impl.ruleaction.AlertInfoActionImpl', 1, 1, '2018-02-24 11:42:58', NULL, '4', 'alertInfo');



INSERT INTO `` (`action_param_id`, `action_id`, `action_param_name`, `action_param_desc`, `param_identify`, `is_effect`, `cre_user_id`, `cre_time`, `remark`) VALUES (3, 3, '分值', '分值', 'grade', 1, 1, '2017-12-22 10:34:22', NULL);
INSERT INTO `` (`action_param_id`, `action_id`, `action_param_name`, `action_param_desc`, `param_identify`, `is_effect`, `cre_user_id`, `cre_time`, `remark`) VALUES (6, 3, '权值', '权值-不可以删除', 'grade1', 1, 1, '2018-1-25 11:08:04', '');


INSERT INTO rule_property_info(rule_property_id, rule_property_identify, rule_property_name, rule_property_desc, default_value, is_effect, remark) VALUES (1, 'salience', '优先级', '用来设置规则执行的优先级，salience 属性的值是一个数字，数字越大执行优先级越高，同时它的值可以是一个负数。默认情况下，规则的ssalience 默认值为0，所以如果我们不手动设置规则的salience 属性，那么它的执行顺序是随机（但是一般都是按照加载顺序。）', '0', 1, NULL);
INSERT INTO rule_property_info(rule_property_id, rule_property_identify, rule_property_name, rule_property_desc, default_value, is_effect, remark) VALUES (2, 'no-loop', '是否可被重复执行', '是否允许规则多次执行, 值为布尔类型, 默认是false, 即当前的规则只要满足条件, 可以无限次执行; 对当前传入workingmemory中的fact对象进行修改或者个数的增减时, 就会触发规则重新匹配执行; 设置属性no-loop true, 表示当前规则只执行一次, 即使rhs中更新了当前fact对象也不会再次执行该规则了. 不过当其他规则里更新了fact对象时, 即使有no-loop true也会触发, 即no-loop true仅表示本规的rhs中有更新时不重复执行', 'false', 1, NULL);
INSERT INTO rule_property_info(rule_property_id, rule_property_identify, rule_property_name, rule_property_desc, default_value, is_effect, remark) VALUES (3, 'date-effective', '生效时间', '设置规则的生效时间, 当前系统时间>=date-effective时才会触发执行, 值是一个日期格式的字符串, 推荐用法是手动在java代码中设置当前系统的时间格式, 然后按照格式指定时间. 比如: date-effective \"2016-01-31 23:59:59\"', NULL, 1, NULL);
INSERT INTO rule_property_info(rule_property_id, rule_property_identify, rule_property_name, rule_property_desc, default_value, is_effect, remark) VALUES (4, 'date-expires', '失效时间', '设置规则的失效时间, 跟生效时间正好相反', NULL, 1, NULL);
INSERT INTO rule_property_info(rule_property_id, rule_property_identify, rule_property_name, rule_property_desc, default_value, is_effect, remark) VALUES (5, 'enabled', '是否可用', '表示该规则是否可用, 值为布尔类型, 默认是true, 设置成false则该规则就不会被执行了', 'true', 0, '规则信息上已经有此属性，所以默认当前属性无效');
INSERT INTO rule_property_info(rule_property_id, rule_property_identify, rule_property_name, rule_property_desc, default_value, is_effect, remark) VALUES (6, 'dialect', '语言类型', '设置语言类型, 值为字符串, 一般有两种语言,mvel和java, 默认为java', 'java', 1, NULL);
INSERT INTO rule_property_info(rule_property_id, rule_property_identify, rule_property_name, rule_property_desc, default_value, is_effect, remark) VALUES (7, 'duration', '规则定时', '规则定时, 值为长整型, 单位为毫秒, 如 duration 3000, 表示规则在3秒后执行(另外的线程中执行)', '3000', 1, NULL);
INSERT INTO rule_property_info(rule_property_id, rule_property_identify, rule_property_name, rule_property_desc, default_value, is_effect, remark) VALUES (8, 'lock-on-active', '确认规则只执行一次', '是no-loop的增强版, 与其他属性配合使用;规则的重复执行不一定是本身触发的, 也可能是其他规则触发的, 当在规则上使用ruleflow-group属性或agenda-group属性时, 将lock-on-active属性值设置为true，可避免因某些fact对象被修改而使已经执行过的规则再次被激活执行', 'false', 1, NULL);
INSERT INTO rule_property_info(rule_property_id, rule_property_identify, rule_property_name, rule_property_desc, default_value, is_effect, remark) VALUES (9, 'activation-group', '规则分组', '作用是将规则分组, 值为字符串表示组名,这样在执行的时候,具有相同activation-group属性的规则中只要有一个会被执行,其它的规则都将不再执行。即在具有相同activation-group属性的规则当中,只有一个规则会被执行,其它规则都将不会被执行.相同activation-group属性的规则中哪一个会先执行,则可以用salience之类的属性来实现', NULL, 1, NULL);
INSERT INTO rule_property_info(rule_property_id, rule_property_identify, rule_property_name, rule_property_desc, default_value, is_effect, remark) VALUES (10, 'agenda-group', '议程分组', 'agenda group 是用来在agenda 的基础之上，对现在的规则进行再次分组，具体的分组方法可以采用为规则添加agenda-group 属性来实现。 \r\nagenda-group 属性的值也是一个字符串，通过这个字符串，可以将规则分为若干个agenda group，默认情况下，引擎在调用这些设置了agenda-group 属性的规则的时候需要显示的指定某个agenda group 得到focus（焦点），这样位于该agenda group 当中的规则才会触发执行，否则将不执行', NULL, 1, NULL);
INSERT INTO rule_property_info(rule_property_id, rule_property_identify, rule_property_name, rule_property_desc, default_value, is_effect, remark) VALUES (11, 'ruleflow-group', '规则流分组', '在使用规则流的时候要用到ruleflow-group 属性，该属性的值为一个字符串，作用是用来将规则划分为一个个的组，然后在规则流当中通过使用ruleflow-group 属性的值，从而使用对应的规则', NULL, 1, NULL);
INSERT INTO rule_property_info(rule_property_id, rule_property_identify, rule_property_name, rule_property_desc, default_value, is_effect, remark) VALUES (12, 'auto-focus', '自动获取焦点', '前面我们也提到auto-focus 属性，它的作用是用来在已设置了agenda-group 的规则上设置该规则是否可以自动独取focus，如果该属性设置为true，那么在引擎执行时，就不需要显示的为某个agenda group 设置focus，否则需要', 'true', 1, NULL);


