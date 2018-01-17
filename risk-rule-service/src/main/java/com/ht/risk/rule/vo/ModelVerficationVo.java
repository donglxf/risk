package com.ht.risk.rule.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class ModelVerficationVo implements Serializable {

    private String modelName;
    private String modelVersion;
    private Map<String,List<VariableVo>> variableMap;

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
}
