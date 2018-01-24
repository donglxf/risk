package com.ht.risk.rule.vo;

import com.ht.risk.rule.entity.RuleHisVersion;

import java.util.Map;

public class RuleHisVersionVo extends RuleHisVersion {

    public String validationResult; // 匹配结果  1-未匹配，0-匹配

    public String executeRule;

    public String variableName;
    public String variableValue;

    public String getVariableValue() {
        return variableValue;
    }

    public void setVariableValue(String variableValue) {
        this.variableValue = variableValue;
    }

    public String getVariableName() {

        return variableName;
    }

    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

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
