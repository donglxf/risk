package com.ht.risk.api.model.rule;

import java.io.Serializable;

public class RpcRuleDetail implements Serializable{

    private String ruleCode;
    private String ruleDesc;

    public String getRuleCode() {
        return ruleCode;
    }

    public void setRuleCode(String ruleCode) {
        this.ruleCode = ruleCode;
    }

    public String getRuleDesc() {
        return ruleDesc;
    }

    public void setRuleDesc(String ruleDesc) {
        this.ruleDesc = ruleDesc;
    }
}
