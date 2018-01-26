package com.ht.risk.activiti.vo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.ht.risk.common.vo.Page;
import io.swagger.annotations.ApiModelProperty;

import java.util.Map;

public class VerficationModelVo extends Page{

    private Long procReleaseId;
    private Long batchId;
    private String procDefId;
    private String version;
    private Map<String,Object> data;
    private String batchSize;
    private String modelName;
    private String modelVersion;


    public Long getProcReleaseId() {
        return procReleaseId;
    }

    public void setProcReleaseId(Long procReleaseId) {
        this.procReleaseId = procReleaseId;
    }

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

    public Long getBatchId() {
        return batchId;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getModelVersion() {
        return modelVersion;
    }

    public void setModelVersion(String modelVersion) {
        this.modelVersion = modelVersion;
    }
}
