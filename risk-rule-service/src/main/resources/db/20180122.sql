#添加一个字段
alter table rule_action_param_value_info add COLUMN param_text VARCHAR(32) DEFAULT '';
#添加业务线id

alter table rule_action_param_value_info add COLUMN businessId VARCHAR(32) DEFAULT -1;


alter table rule_action_param_value_info add COLUMN param_text VARCHAR(32) DEFAULT -1 ;
