package com.ht.risk.rule.vo;

import com.ht.risk.rule.entity.RuleHisVersion;

public class RuleHisVersionVo extends RuleHisVersion {

    public String validationResult;

    public String executeRule;

    public String getExecuteRule() {
        return executeRule;
    }

    public void setExecuteRule(String executeRule) {
        this.executeRule = executeRule;
    }

    public String getValidationResult() {
        return validationResult;
    }

    public void setValidationResult(String validationResult) {
        this.validationResult = validationResult;
    }
}
