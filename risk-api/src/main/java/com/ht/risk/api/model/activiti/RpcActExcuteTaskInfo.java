package com.ht.risk.api.model.activiti;

import java.io.Serializable;
import java.util.Date;

public class RpcActExcuteTaskInfo implements Serializable{

    /**
     * 主键
     */
    private Long id;
    /**
     * 批次号，验证任务调用时存在
     */
    private Long batchId;
    /**
     * 模型版本ID
     */
    private Long procReleaseId;
    /**
     * 流程运行实例ID
     */
    private String procInstId;
    /**
     * 任务状态，0-待执行，1-执行结束，2-执行异常
     */
    private String status;
    /**
     * 入参
     */
    private String inParamter;
    /**
     * 出参
     */
    private String outParamter;
    /**
     * 花费时间
     */
    private Long spendTime;
    /**
     * 备注
     */
    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBatchId() {
        return batchId;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    public Long getProcReleaseId() {
        return procReleaseId;
    }

    public void setProcReleaseId(Long procReleaseId) {
        this.procReleaseId = procReleaseId;
    }

    public String getProcInstId() {
        return procInstId;
    }

    public void setProcInstId(String procInstId) {
        this.procInstId = procInstId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInParamter() {
        return inParamter;
    }

    public void setInParamter(String inParamter) {
        this.inParamter = inParamter;
    }

    public String getOutParamter() {
        return outParamter;
    }

    public void setOutParamter(String outParamter) {
        this.outParamter = outParamter;
    }

    public Long getSpendTime() {
        return spendTime;
    }

    public void setSpendTime(Long spendTime) {
        this.spendTime = spendTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}