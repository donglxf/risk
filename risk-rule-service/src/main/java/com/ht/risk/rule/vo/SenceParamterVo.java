package com.ht.risk.rule.vo;

import java.util.ArrayList;
import java.util.List;

public class SenceParamterVo {

    private String senceName;
    private String senceVersionId;

    private List<VariableVo> variableVos;


    public String getSenceName() {
        return senceName;
    }

    public void setSenceName(String senceName) {
        this.senceName = senceName;
    }

    public String getSenceVersionId() {
        return senceVersionId;
    }

    public void setSenceVersionId(String senceVersionId) {
        this.senceVersionId = senceVersionId;
    }

    public List<VariableVo> getVariableVos() {
        return variableVos;
    }

    public void setVariableVos(List<VariableVo> variableVos) {
        this.variableVos = variableVos;
    }

    public void addVariableVo(VariableVo vo){
        if(variableVos == null){
            variableVos = new ArrayList<VariableVo>();
        }
        variableVos.add(vo);
    }
}
