package com.ht.risk.rule.vo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class ModelVerficationDetailVo implements Serializable {

    private List<String> secenCodes;
    private Map<String,VariableVo> variableMap;

    public List<String> getSecenCodes() {
        return secenCodes;
    }

    public void setSecenCodes(List<String> secenCodes) {
        this.secenCodes = secenCodes;
    }

    public Map<String, VariableVo> getVariableMap() {
        return variableMap;
    }

    public void setVariableMap(Map<String, VariableVo> variableMap) {
        this.variableMap = variableMap;
    }
}
