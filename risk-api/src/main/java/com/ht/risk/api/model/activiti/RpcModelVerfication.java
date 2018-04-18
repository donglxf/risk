package com.ht.risk.api.model.activiti;

import java.io.Serializable;

public class RpcModelVerfication implements Serializable{

    private Long batchId;
    private String taskId;
    private Long procReleaseId;


    public Long getBatchId() {
        return batchId;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public Long getProcReleaseId() {
        return procReleaseId;
    }

    public void setProcReleaseId(Long procReleaseId) {
        this.procReleaseId = procReleaseId;
    }
}
