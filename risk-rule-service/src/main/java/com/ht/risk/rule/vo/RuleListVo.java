package com.ht.risk.rule.vo;

import com.ht.risk.rule.entity.ActionParamValueInfo;
import com.ht.risk.rule.entity.ConditionInfo;
import com.ht.risk.rule.entity.EntityInfo;
import io.swagger.annotations.ApiModel;

import java.util.List;

/**
 * 说明：
 *
 * @auther 张鹏
 * @create
 */
@ApiModel
public class RuleListVo {
    /**场景id*/
    private String sceneId;



    /**
     * 条件集合
     */
    private List<ConditionInfo> conditionInfos;
    /**
     * 动作集合
     */
    private List<ActionParamValueInfo> actionInfos;
    /**
     *导入的实体类
     */
    private List<EntityInfo> entityInfos ;

   // private List<>




}
