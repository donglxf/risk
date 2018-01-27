package com.ht.risk.activiti.vo;

import com.ht.risk.activiti.entity.RiskValidateBatch;

import java.io.Serializable;

public class VerificationBatchVo implements Serializable{

    public VerificationBatchVo(){
        super();
    }

    public VerificationBatchVo(RiskValidateBatch batch){
        this.setBatchId(String.valueOf(batch.getId()));
        this.setModelName(batch.getModelName());
        this.setModelVersion(batch.getModelVersion());
        this.setStatus(batch.getStatus());
    }


    private String batchId;
    private String modelName;
    private String ModelVersion;
    private String status;

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getModelVersion() {
        return ModelVersion;
    }

    public void setModelVersion(String modelVersion) {
        ModelVersion = modelVersion;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
