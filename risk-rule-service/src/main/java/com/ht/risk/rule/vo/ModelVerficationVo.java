package com.ht.risk.rule.vo;

import com.ht.risk.common.vo.Page;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class ModelVerficationVo  extends Page implements Serializable{

    private Long batchId;
    private String modelName;
    private String modelVersion;
    private Long taskId;

    private Map<String,List<VariableVo>> variableMap;

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

    public Map<String, List<VariableVo>> getVariableMap() {
        return variableMap;
    }

    public void setVariableMap(Map<String, List<VariableVo>> variableMap) {
        this.variableMap = variableMap;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }
}
