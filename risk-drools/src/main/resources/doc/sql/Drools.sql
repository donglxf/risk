-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        10.0.21-mariadb - mariadb.org binary distribution
-- 服务器操作系统:                      win64
-- heidisql 版本:                  9.3.0.4984
-- --------------------------------------------------------

/*!40101 set @old_character_set_client=@@character_set_client */;
/*!40101 set names utf8mb4 */;
/*!40014 set @old_foreign_key_checks=@@foreign_key_checks, foreign_key_checks=0 */;
/*!40101 set @old_sql_mode=@@sql_mode, sql_mode='no_auto_value_on_zero' */;

-- 导出  表 bluesky.rule_action_info 结构
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

-- 正在导出表  bluesky.rule_action_info 的数据：~2 rows (大约)
/*!40000 alter table `rule_action_info` disable keys */;
insert into `rule_action_info` (`action_id`, `action_type`, `action_name`, `action_desc`, `action_class`, `is_effect`, `cre_user_id`, `cre_time`, `remark`) values
	(1, 1, '测试实现类', '测试实现类', 'com.sky.bluesky.service.impl.ruleaction.TestActionImpl', 1, 1, '2017-07-24 17:12:32', null),
	(2, 2, '自身', '测试自身', 'com.sky.bluesky.model.fact.TestRule', 1, 1, '2017-07-27 10:07:03', null);
/*!40000 alter table `rule_action_info` enable keys */;


-- 导出  表 bluesky.rule_action_param_info 结构
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

-- 正在导出表  bluesky.rule_action_param_info 的数据：~2 rows (大约)
/*!40000 alter table `rule_action_param_info` disable keys */;
insert into `rule_action_param_info` (`action_param_id`, `action_id`, `action_param_name`, `action_param_desc`, `param_identify`, `is_effect`, `cre_user_id`, `cre_time`, `remark`) values
	(1, 1, '测试', '测试', 'message', 1, 1, '2017-07-24 18:24:28', null),
	(2, 2, '测试', '测试', 'score', 1, 1, '2017-07-17 10:07:46', null);
/*!40000 alter table `rule_action_param_info` enable keys */;


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

-- 正在导出表  bluesky.rule_action_param_value_info 的数据：~2 rows (大约)
/*!40000 alter table `rule_action_param_value_info` disable keys */;
insert into `rule_action_param_value_info` (`action_param_value_id`, `rule_action_rel_id`, `action_param_id`, `param_value`, `is_effect`, `cre_user_id`, `cre_time`, `remark`) values
	(1, 1, 1, 'hello', 1, 1, '2017-07-24 19:29:17', null),
	(2, 2, 2, '#3#*5', 1, 1, '2017-07-27 10:10:17', null);
/*!40000 alter table `rule_action_param_value_info` enable keys */;


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

-- 正在导出表  bluesky.rule_action_rule_rel 的数据：~2 rows (大约)
/*!40000 alter table `rule_action_rule_rel` disable keys */;
insert into `rule_action_rule_rel` (`rule_action_rel_id`, `action_id`, `rule_id`, `is_effect`, `cre_user_id`, `cre_time`, `remark`) values
	(1, 1, 1, 1, 1, '2017-07-24 18:59:11', null),
	(2, 2, 1, 1, 1, '2017-07-27 10:08:25', null);
/*!40000 alter table `rule_action_rule_rel` enable keys */;


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

-- 正在导出表  bluesky.rule_condition_info 的数据：~1 rows (大约)
/*!40000 alter table `rule_condition_info` disable keys */;
insert into `rule_condition_info` (`condition_id`, `rule_id`, `condition_name`, `condition_expression`, `condition_desc`, `is_effect`, `cre_user_id`, `cre_time`, `remark`) values
	(1, 1, '测试', '$2$==100', '$金额$==100', 1, 1, '2017-07-24 20:04:52', null);
/*!40000 alter table `rule_condition_info` enable keys */;


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

-- 正在导出表  bluesky.rule_entity_info 的数据：~1 rows (大约)
/*!40000 alter table `rule_entity_info` disable keys */;
insert into `rule_entity_info` (`entity_id`, `entity_name`, `entity_desc`, `entity_identify`, `pkg_name`, `cre_user_id`, `cre_time`, `is_effect`, `remark`) values
	(1, '测试规则', '测试规则引擎', 'testRule', 'com.sky.bluesky.model.fact.TestRule', 1, '2017-07-20 11:41:32', 1, null);
/*!40000 alter table `rule_entity_info` enable keys */;


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

-- 正在导出表  bluesky.rule_entity_item_info 的数据：~3 rows (大约)
/*!40000 alter table `rule_entity_item_info` disable keys */;
insert into `rule_entity_item_info` (`item_id`, `entity_id`, `item_name`, `item_identify`, `item_desc`, `cre_user_id`, `cre_time`, `is_effect`, `remark`) values
	(1, 1, '消息', 'message', '消息信息', 1, '2017-07-20 16:20:56', 1, null),
	(2, 1, '金额', 'amount', '消费金额', 1, '2017-07-20 16:21:47', 1, null),
	(3, 1, '积分', 'score', '积分', 1, '2017-07-20 16:23:55', 1, null);
/*!40000 alter table `rule_entity_item_info` enable keys */;


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

-- 正在导出表  bluesky.rule_info 的数据：~1 rows (大约)
/*!40000 alter table `rule_info` disable keys */;
insert into `rule_info` (`rule_id`, `scene_id`, `rule_name`, `rule_desc`, `rule_enabled`, `is_effect`, `cre_user_id`, `cre_time`, `remark`) values
	(1, 1, '测试', null, 1, 1, 1, '2017-07-25 10:55:36', null);
/*!40000 alter table `rule_info` enable keys */;


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

-- 正在导出表  bluesky.rule_property_info 的数据：~12 rows (大约)
/*!40000 alter table `rule_property_info` disable keys */;
insert into `rule_property_info` (`rule_property_id`, `rule_property_identify`, `rule_property_name`, `rule_property_desc`, `default_value`, `is_effect`, `remark`) values
	(1, 'salience', '优先级', '用来设置规则执行的优先级，salience 属性的值是一个数字，数字越大执行优先级越高，同时它的值可以是一个负数。默认情况下，规则的ssalience 默认值为0，所以如果我们不手动设置规则的salience 属性，那么它的执行顺序是随机（但是一般都是按照加载顺序。）', '0', 1, null),
	(2, 'no-loop', '是否可被重复执行', '是否允许规则多次执行, 值为布尔类型, 默认是false, 即当前的规则只要满足条件, 可以无限次执行; 对当前传入workingmemory中的fact对象进行修改或者个数的增减时, 就会触发规则重新匹配执行; 设置属性no-loop true, 表示当前规则只执行一次, 即使rhs中更新了当前fact对象也不会再次执行该规则了. 不过当其他规则里更新了fact对象时, 即使有no-loop true也会触发, 即no-loop true仅表示本规的rhs中有更新时不重复执行', 'false', 1, null),
	(3, 'date-effective', '生效时间', '设置规则的生效时间, 当前系统时间>=date-effective时才会触发执行, 值是一个日期格式的字符串, 推荐用法是手动在java代码中设置当前系统的时间格式, 然后按照格式指定时间. 比如: date-effective "2016-01-31 23:59:59"', null, 1, null),
	(4, 'date-expires', '失效时间', '设置规则的失效时间, 跟生效时间正好相反', null, 1, null),
	(5, 'enabled', '是否可用', '表示该规则是否可用, 值为布尔类型, 默认是true, 设置成false则该规则就不会被执行了', 'true', 0, '规则信息上已经有此属性，所以默认当前属性无效'),
	(6, 'dialect', '语言类型', '设置语言类型, 值为字符串, 一般有两种语言,mvel和java, 默认为java', 'java', 1, null),
	(7, 'duration', '规则定时', '规则定时, 值为长整型, 单位为毫秒, 如 duration 3000, 表示规则在3秒后执行(另外的线程中执行)', '3000', 1, null),
	(8, 'lock-on-active', '确认规则只执行一次', '是no-loop的增强版, 与其他属性配合使用;规则的重复执行不一定是本身触发的, 也可能是其他规则触发的, 当在规则上使用ruleflow-group属性或agenda-group属性时, 将lock-on-active属性值设置为true，可避免因某些fact对象被修改而使已经执行过的规则再次被激活执行', 'false', 1, null),
	(9, 'activation-group', '规则分组', '作用是将规则分组, 值为字符串表示组名,这样在执行的时候,具有相同activation-group属性的规则中只要有一个会被执行,其它的规则都将不再执行。即在具有相同activation-group属性的规则当中,只有一个规则会被执行,其它规则都将不会被执行.相同activation-group属性的规则中哪一个会先执行,则可以用salience之类的属性来实现', null, 1, null),
	(10, 'agenda-group', '议程分组', 'agenda group 是用来在agenda 的基础之上，对现在的规则进行再次分组，具体的分组方法可以采用为规则添加agenda-group 属性来实现。 \r\nagenda-group 属性的值也是一个字符串，通过这个字符串，可以将规则分为若干个agenda group，默认情况下，引擎在调用这些设置了agenda-group 属性的规则的时候需要显示的指定某个agenda group 得到focus（焦点），这样位于该agenda group 当中的规则才会触发执行，否则将不执行', null, 1, null),
	(11, 'ruleflow-group', '规则流分组', '在使用规则流的时候要用到ruleflow-group 属性，该属性的值为一个字符串，作用是用来将规则划分为一个个的组，然后在规则流当中通过使用ruleflow-group 属性的值，从而使用对应的规则', null, 1, null),
	(12, 'auto-focus', '自动获取焦点', '前面我们也提到auto-focus 属性，它的作用是用来在已设置了agenda-group 的规则上设置该规则是否可以自动独取focus，如果该属性设置为true，那么在引擎执行时，就不需要显示的为某个agenda group 设置focus，否则需要', 'true', 1, null);
/*!40000 alter table `rule_property_info` enable keys */;


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

-- 正在导出表  bluesky.rule_property_rel 的数据：~3 rows (大约)
/*!40000 alter table `rule_property_rel` disable keys */;
insert into `rule_property_rel` (`rule_pro_rel_id`, `rule_id`, `rule_property_id`, `rule_property_value`) values
	(1, 1, 1, '1'),
	(2, 1, 2, 'true'),
	(3, 1, 8, 'true');
/*!40000 alter table `rule_property_rel` enable keys */;


-- 导出  表 bluesky.rule_scene_entity_rel 结构
create table if not exists `rule_scene_entity_rel` (
  `scene_entity_rel_id` bigint(20) not null comment '主键',
  `scene_id` bigint(20) default null comment '场景',
  `entity_id` bigint(20) default null comment '实体',
  primary key (`scene_entity_rel_id`),
  key `scene_id` (`scene_id`),
  key `entity_id` (`entity_id`)
) engine=innodb default charset=utf8 comment='场景实体关联表';

-- 正在导出表  bluesky.rule_scene_entity_rel 的数据：~1 rows (大约)
/*!40000 alter table `rule_scene_entity_rel` disable keys */;
insert into `rule_scene_entity_rel` (`scene_entity_rel_id`, `scene_id`, `entity_id`) values
	(1, 1, 1);
/*!40000 alter table `rule_scene_entity_rel` enable keys */;


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

-- 正在导出表  bluesky.rule_scene_info 的数据：~1 rows (大约)
/*!40000 alter table `rule_scene_info` disable keys */;
insert into `rule_scene_info` (`scene_id`, `scene_identify`, `scene_type`, `scene_name`, `scene_desc`, `is_effect`, `cre_user_id`, `cre_time`, `remark`) values
	(1, 'test', null, '测试', '测试规则引擎', 1, 1, '2017-07-20 16:56:10', '测试');
/*!40000 alter table `rule_scene_info` enable keys */;


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

-- 正在导出表  bluesky.rule_variable 的数据：~2 rows (大约)
/*!40000 alter table `rule_variable` disable keys */;
insert into `rule_variable` (`variable_id`, `variable_name`, `variable_type`, `default_value`, `value_type`, `variable_value`, `is_effect`, `cre_user_id`, `cre_time`, `remark`) values
	(1, '增加积分100', 2, '100', 2, '100', 1, 1, '2017-07-20 18:38:01', null),
	(2, '金额大于100', 1, '100', 2, '100', 1, 1, '2017-07-20 18:39:18', null);
/*!40000 alter table `rule_variable` enable keys */;
/*!40101 set sql_mode=ifnull(@old_sql_mode, '') */;
/*!40014 set foreign_key_checks=if(@old_foreign_key_checks is null, 1, @old_foreign_key_checks) */;
/*!40101 set character_set_client=@old_character_set_client */;
