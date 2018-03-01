/**
 * dyb 20180206
 * 格式化规则执行返回结果
 */
package com.ht.risk.api.model.drools;

import java.util.ArrayList;
import java.util.List;

public class RuleStandardResult {
    private List<String> ruleList = new ArrayList<String>();
    private List<String> result = new ArrayList<>();
    private List<String> logIdList = new ArrayList<>();

    public List<String> getResult() {
        return result;
    }

    public void setResult(List<String> result) {
        this.result = result;
    }

    public List<String> getLogIdList() {

        return logIdList;
    }

    public void setLogIdList(List<String> logIdList) {
        this.logIdList = logIdList;
    }

    public List<String> getRuleList() {
        return ruleList;
    }

    public void setRuleList(List<String> ruleList) {
        this.ruleList = ruleList;
    }
}