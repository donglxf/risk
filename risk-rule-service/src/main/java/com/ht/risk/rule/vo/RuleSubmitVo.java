package com.ht.risk.rule.vo;

import com.ht.risk.rule.entity.ActionInfo;
import com.ht.risk.rule.entity.ActionParamValueInfo;
import com.ht.risk.rule.entity.ConditionInfo;
import com.ht.risk.rule.entity.EntityInfo;
import io.swagger.annotations.ApiModel;

import java.util.List;
import java.util.Set;

/**
 * 说明：
 *
 * @auther 张鹏
 * @create
 */
@ApiModel
public class RuleSubmitVo {


    /**
     * 条件集合
     */
    private List<ConditionInfo> conditionInfos;
    /**
     * 动作集合
     */
    private List<ActionParamValueInfo> actionInfos;

    public List<ConditionInfo> getConditionInfos() {
        return conditionInfos;
    }

    public void setConditionInfos(List<ConditionInfo> conditionInfos) {
        this.conditionInfos = conditionInfos;
    }

    public List<ActionParamValueInfo> getActionInfos() {
        return actionInfos;
    }

    public void setActionInfos(List<ActionParamValueInfo> actionInfos) {
        this.actionInfos = actionInfos;
    }

}
