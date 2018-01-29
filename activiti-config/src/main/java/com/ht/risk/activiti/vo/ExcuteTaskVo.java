package com.ht.risk.activiti.vo;

import com.ht.risk.activiti.entity.ActExcuteTask;
import com.ht.risk.activiti.entity.RiskValidateBatch;

import java.io.Serializable;

public class ExcuteTaskVo implements Serializable{

    public ExcuteTaskVo(){
        super();
    }

    public ExcuteTaskVo(ActExcuteTask task){
        this.setId(String.valueOf(task.getId()));
        this.setProcInstId(task.getProcInstId());
        this.setStatus(task.getStatus());
    }

    private String id;
    private String procInstId;
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
