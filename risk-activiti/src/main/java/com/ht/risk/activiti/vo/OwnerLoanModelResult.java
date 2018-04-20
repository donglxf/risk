package com.ht.risk.activiti.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class OwnerLoanModelResult implements Serializable{

    private static final long serialVersionUID = 6471099926064284714L;

    private String code;
    private String warmMsg;
    private String procInstId;
    private String errorMsg;
    private String taskId;
    private String hitMsg;
    private String auditType;
    private Double quota;
    private String businessKey;
    private Map<String,OwnerLoanRuleInfo> interInfo;
        private List<OwnerLoanRuleInfo> interInfos;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getWarmMsg() {
        return warmMsg;
    }

    public void setWarmMsg(String warmMsg) {
        this.warmMsg = warmMsg;
    }

    public String getProcInstId() {
        return procInstId;
    }

    public void setProcInstId(String procInstId) {
        this.procInstId = procInstId;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getHitMsg() {
        return hitMsg;
    }

    public void setHitMsg(String hitMsg) {
        this.hitMsg = hitMsg;
    }

    public String getAuditType() {
        return auditType;
    }

    public void setAuditType(String auditType) {
        this.auditType = auditType;
    }

    public Double getQuota() {
        return quota;
    }
    public void setQuota(Double quota) {
        this.quota = quota;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Map<String, OwnerLoanRuleInfo> getInterInfo() {
        return interInfo;
    }

    public void setInterInfo(Map<String, OwnerLoanRuleInfo> interInfo) {
        this.interInfo = interInfo;
    }

    public List<OwnerLoanRuleInfo> getInterInfos() {
        return interInfos;
    }

    public void setInterInfos(List<OwnerLoanRuleInfo> interInfos) {
        this.interInfos = interInfos;
    }

    public String getBusinessKey() {
        return businessKey;
    }

    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }
}
