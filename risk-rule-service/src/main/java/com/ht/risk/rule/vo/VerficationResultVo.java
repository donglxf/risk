package com.ht.risk.rule.vo;

import com.ht.risk.api.model.log.RpcHitRuleInfo;

import java.io.Serializable;
import java.util.List;

public class VerficationResultVo implements Serializable{

    private String procReleaseId;
    private String taskId;
    private List<RpcHitRuleInfo> hitRules;
    private List<SenceParamterVo> sences;
    private String message;
    private String validateFlag;

    public List<RpcHitRuleInfo> getHitRules() {
        return hitRules;
    }

    public void setHitRules(List<RpcHitRuleInfo> hitRules) {
        this.hitRules = hitRules;
    }

    public List<SenceParamterVo> getSences() {
        return sences;
    }

    public void setSences(List<SenceParamterVo> sences) {
        this.sences = sences;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getProcReleaseId() {
        return procReleaseId;
    }

    public void setProcReleaseId(String procReleaseId) {
        this.procReleaseId = procReleaseId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getValidateFlag() {
        return validateFlag;
    }

    public void setValidateFlag(String validateFlag) {
        this.validateFlag = validateFlag;
    }
}
