package com.ht.risk.api.model.activiti;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ht.risk.api.model.rule.RpcRuleDetail;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MulitRuleExcuteDetail extends RuleExcuteDetail implements Serializable{

    private List<Map<String,Object>> inParamters;

    public List<Map<String, Object>> getInParamters() {
        return inParamters;
    }

    public void setInParamters(List<Map<String, Object>> inParamters) {
        this.inParamters = inParamters;
    }
}
