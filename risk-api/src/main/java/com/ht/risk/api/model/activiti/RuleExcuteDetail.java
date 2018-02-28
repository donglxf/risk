package com.ht.risk.api.model.activiti;

import java.io.Serializable;
import java.util.List;

public class RuleExcuteDetail implements Serializable{

    private String code;
    private String msg;
    private String senceVersionId;
    private String senceName;
    private String logId;
    private List<String> ruleList;
    private List<String> ruleNameList;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getSenceName() {
        return senceName;
    }

    public void setSenceName(String senceName) {
        this.senceName = senceName;
    }

    public List<String> getRuleNameList() {
        return ruleNameList;
    }

    public void setRuleNameList(List<String> ruleNameList) {
        this.ruleNameList = ruleNameList;
    }

}
