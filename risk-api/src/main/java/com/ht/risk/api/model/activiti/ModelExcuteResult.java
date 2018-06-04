package com.ht.risk.api.model.activiti;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class ModelExcuteResult implements Serializable{

    private String code;
    private String msg;
    private String procInstId;
    private String taskId;
    private String procMsg;
    private String businessKey;
    private Map<String,List<RuleExcuteDetail>> ruleResultList;
    private String scope;
    private String auditType ;

    public String getAuditType() {
        return auditType;
    }

    public void setAuditType(String auditType) {
        this.auditType = auditType;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
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

    public String getProcInstId() {
        return procInstId;
    }

    public void setProcInstId(String procInstId) {
        this.procInstId = procInstId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public Map<String, List<RuleExcuteDetail>> getRuleResultList() {
        return ruleResultList;
    }

    public void setRuleResultList(Map<String, List<RuleExcuteDetail>> ruleResultList) {
        this.ruleResultList = ruleResultList;
    }
    public String getProcMsg() {
        return procMsg;
    }

    public void setProcMsg(String procMsg) {
        this.procMsg = procMsg;
    }

    public String getBusinessKey() {
        return businessKey;
    }

    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }
}
