/**
 * dyb 20180206
 * 格式化规则执行返回结果
 */
package com.ht.risk.model.fact;

import java.util.ArrayList;
import java.util.List;

public class RuleStandardResult {
    private List<String> ruleList = new ArrayList<String>(); // 命中规则列表集合
    private List<String> result = new ArrayList<>(); // 规则执行结果
    private List<String> logIdList = new ArrayList<>(); // 规则执行记录日志id
    private String scope; // 如果是评分卡，则保存最后总分

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

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
