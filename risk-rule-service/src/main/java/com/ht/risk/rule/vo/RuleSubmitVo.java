package com.ht.risk.rule.vo;

import com.ht.risk.rule.entity.ActionParamValueInfo;
import com.ht.risk.rule.entity.ConditionInfo;
import com.ht.risk.rule.entity.RuleGroup;
import io.swagger.annotations.ApiModel;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 说明：
 *
 * @auther 张鹏
 * @create
 */
@ApiModel
public class RuleSubmitVo {
    /**
     * 分组和权值设置
     */
    private RuleGroup group;
    /**
     * 条件集合
     */
    @NotNull
    @Valid
    private List<ConditionInfo> conditionInfos;
    /**
     * 动作集合
     */
    @NotNull
    @Valid
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

    public RuleGroup getGroup() {
        return group;
    }

    public void setGroup(RuleGroup group) {
        this.group = group;
    }
}
