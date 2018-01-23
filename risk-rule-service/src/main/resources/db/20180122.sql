#添加一个字段
#alter table rule_action_param_value_info add COLUMN param_text VARCHAR(32) DEFAULT '';
#添加业务线id


alter table rule_action_info add COLUMN business_id VARCHAR(32) DEFAULT 0 COMMENT '业务线id' ;

alter table rule_entity_info add COLUMN business_id VARCHAR(32) DEFAULT 0 COMMENT '业务线id' ;

alter table rule_constant_info add COLUMN business_id VARCHAR(32) DEFAULT 0 COMMENT '业务线id' ;

alter table rule_scene_info add COLUMN business_id VARCHAR(32) DEFAULT 0 COMMENT '业务线id' ;
#alter table rule_scene_info add COLUMN business_id VARCHAR(32) DEFAULT 0 COMMENT '业务线id' ;
