package com.ht.risk.api.model.activiti;

import java.io.Serializable;
import java.util.List;

public class ModelExcuteResult implements Serializable{

    private String code;
    private String msg;
    private String procInstId;
    private Long taskId;
    private List<RuleExcuteDetail> ruleResultList;

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

    public String getProcInstId() {
        return procInstId;
    }

    public void setProcInstId(String procInstId) {
        this.procInstId = procInstId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public List<RuleExcuteDetail> getRuleResultList() {
        return ruleResultList;
    }

    public void setRuleResultList(List<RuleExcuteDetail> ruleResultList) {
        this.ruleResultList = ruleResultList;
    }
}
