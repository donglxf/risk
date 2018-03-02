package com.ht.risk.api.model.activiti;

import com.ht.risk.api.model.rule.RpcRuleDetail;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RuleExcuteDetail implements Serializable{

    private String senceVersionId;
    private String senceName;
    private String logId;
    private List<RpcRuleDetail> ruleDetails;
    private List<String> ruleList;

    public String getSenceVersionId() {
        return senceVersionId;
    }

    public void setSenceVersionId(String senceVersionId) {
        this.senceVersionId = senceVersionId;
    }

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public List<String> getRuleList() {
        return ruleList;
    }

    public void setRuleList(List<String> ruleList) {
        this.ruleList = ruleList;
    }

    public String getSenceName() {
        return senceName;
    }

    public void setSenceName(String senceName) {
        this.senceName = senceName;
    }

    public List<RpcRuleDetail> getRuleDetails() {
        return ruleDetails;
    }

    public void setRuleDetails(List<RpcRuleDetail> ruleDetails) {
        this.ruleDetails = ruleDetails;
    }
}
