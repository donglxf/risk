package com.ht.risk.activiti.vo;

import com.ht.risk.common.vo.Page;

import java.util.Map;

public class VerficationModelVo extends Page{

    private String procDefId;
    private String version;
    private Map<String,Object> data;
    private String batchSize;


    public String getProcDefId() {
        return procDefId;
    }

    public void setProcDefId(String procDefId) {
        this.procDefId = procDefId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public String getBatchSize() {
        return batchSize;
    }

    public void setBatchSize(String batchSize) {
        this.batchSize = batchSize;
    }
}
