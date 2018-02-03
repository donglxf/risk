create table `act_excute_task` (
  `id` bigint(20) not null comment '主键',
  `batch_id` bigint(20) null default null comment '批次号，验证任务调用时存在',
  `proc_release_id` bigint(20) not null comment '模型版本id',
  `proc_inst_id` varchar(64) character set utf8 collate utf8_bin null default null comment '流程运行实例id',
  `status` varchar(32) character set utf8 collate utf8_bin null default null comment '任务状态，0-待执行，1-执行结束，2-执行异常',
  `type` varchar(32) character set utf8 collate utf8_bin null default null comment '任务类型，0-验证任务，1-业务系统调用',
  `in_paramter` longtext character set utf8 collate utf8_bin null comment '入参',
  `out_paramter` longtext character set utf8 collate utf8_bin null comment '出参',
  `spend_time` bigint(20) null default null comment '花费时间',
  `remark` varchar(5000) character set utf8 collate utf8_bin null default null comment '备注',
  `create_time` datetime not null comment '创建时间',
  `create_user` varchar(64) character set utf8 collate utf8_bin null default null comment '创建用户',
  `update_time` datetime null default null comment '结束时间',
  primary key (`id`)
) engine = innodb character set = utf8 collate utf8_bin;

create table `act_proc_release` (
  `id` bigint(20) not null comment '主键',
  `model_id` varchar(64) character set utf8 collate utf8_bin not null comment '模型id，与 act_re_model.id_ 关联',
  `model_procdef_id` varchar(64) character set utf8 collate utf8_bin not null comment '模型定义id，与 act_re_procdef.id_ 关联,act_re_procdef 表中有模型部署id',
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

create table `risk_business` (
  `business_id` bigint(20) not null auto_increment,
  `business_name` varchar(32) character set utf8 collate utf8_general_ci null default null comment '业务线名',
  `business_desc` varchar(255) character set utf8 collate utf8_general_ci null default null comment '描述',
  `status` tinyint(3) null default 1 comment '状态：1正常0禁用',
  `cre_user_id` varchar(32) character set utf8 collate utf8_general_ci null default null comment '创建人',
  `cre_time` datetime not null comment '创建时间',
  primary key (`business_id`)
) engine = innodb character set = utf8 collate utf8_general_ci auto_increment = 4;

create table `risk_drools_detail_log` (
  `id` bigint(20) not null,
  `drools_logid` bigint(20) not null,
  `execute_rulename` varchar(100) character set utf8 collate utf8_general_ci null default null comment '命中的规则',
  primary key (`id`)
) engine = innodb character set = utf8 collate utf8_general_ci;

create table `risk_drools_log` (
  `id` bigint(20) not null,
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

create table `risk_validate_batch` (
  `id` bigint(20) not null comment '主键,批次号',
  `proc_release_id` varchar(64) character set utf8 collate utf8_bin not null comment '流程部署id，与 act_re_procdef.deployment_id 关联',
  `batch_size` int(12) not null comment '批次大小',
  `status` varchar(2) character set utf8 collate utf8_bin not null default '0' comment '批次状态，0-待执行，1-正在执行，2-执行完成，3-执行异常',
  `complete_count` int(11) null default null comment '已执行次数',
  `is_effect` varchar(2) character set utf8 collate utf8_bin not null default '0' comment '是否生效：0-有效，1-无效',
  `create_time` datetime not null comment '创建时间',
  `create_user` varchar(64) character set utf8 collate utf8_bin null default null comment '创建用户',
  primary key (`id`)
) engine = innodb character set = utf8 collate utf8_bin;

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

create table `rule_action_info` (
  `action_id` bigint(20) not null comment '主键',
  `action_type` int(11) not null comment '动作类型(1实现2自身)',
  `action_name` varchar(200) character set utf8 collate utf8_general_ci not null comment '动作名称',
  `action_desc` varchar(3000) character set utf8 collate utf8_general_ci null default null comment '动作描述',
  `action_class` varchar(200) character set utf8 collate utf8_general_ci not null comment '动作实现类(包路径)',
  `is_effect` int(11) not null default 1 comment '是否有效',
  `cre_user_id` bigint(20) not null comment '创建人',
  `cre_time` datetime not null comment '创建时间',
  `remark` varchar(3000) character set utf8 collate utf8_general_ci null default null comment '备注',
  `business_id` varchar(32) character set utf8 collate utf8_general_ci null default '0' comment '业务线id',
  primary key (`action_id`),
  index `action_type`(`action_type`),
  index `action_name`(`action_name`)
) engine = innodb character set = utf8 collate utf8_general_ci comment '规则动作定义信息';

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


insert into `risk_rule_his_version` (`id`, `sence_version_id`, `rule_name`, `rule_desc`, `is_effect`, `create_user`, `create_time`) values (1, 138, '年龄规则', '年龄等于20', '0', null, null);
insert into `risk_rule_his_version` (`id`, `sence_version_id`, `rule_name`, `rule_desc`, `is_effect`, `create_user`, `create_time`) values (2, 138, '学历规则', '学历为本科', '0', null, null);
insert into `risk_rule_his_version` (`id`, `sence_version_id`, `rule_name`, `rule_desc`, `is_effect`, `create_user`, `create_time`) values (3, 138, '工资规则', '工资大于20000', '0', null, null);
insert into `risk_rule_his_version` (`id`, `sence_version_id`, `rule_name`, `rule_desc`, `is_effect`, `create_user`, `create_time`) values (4, 138, '芝麻分规则', '芝麻分大于650', '0', null, null);
insert into `risk_variable_bind` (`id`, `sence_version_id`, `variable_code`, `variable_name`, `data_type`, `constant_id`, `bind_table`, `bind_column`, `is_effect`, `tmp_value`, `create_user`, `create_time`) values (10, 138, 'age', '年龄', 'double', null, null, null, '0', '20', null, '2018-1-23 17:03:48');
insert into `risk_variable_bind` (`id`, `sence_version_id`, `variable_code`, `variable_name`, `data_type`, `constant_id`, `bind_table`, `bind_column`, `is_effect`, `tmp_value`, `create_user`, `create_time`) values (11, 138, 'education', '学历', 'double', null, null, null, '0', '本科', null, '2018-1-23 17:04:36');
insert into `risk_variable_bind` (`id`, `sence_version_id`, `variable_code`, `variable_name`, `data_type`, `constant_id`, `bind_table`, `bind_column`, `is_effect`, `tmp_value`, `create_user`, `create_time`) values (12, 138, 'salary', '工资', 'double', null, null, null, '0', '100', null, '2018-1-23 17:05:07');
insert into `risk_variable_bind` (`id`, `sence_version_id`, `variable_code`, `variable_name`, `data_type`, `constant_id`, `bind_table`, `bind_column`, `is_effect`, `tmp_value`, `create_user`, `create_time`) values (13, 138, 'zhimascore', '芝麻分', 'double', null, null, null, '0', '750', null, '2018-1-23 17:05:38');
