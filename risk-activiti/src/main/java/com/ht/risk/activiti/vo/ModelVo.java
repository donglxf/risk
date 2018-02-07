package com.ht.risk.activiti.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.activiti.engine.repository.Model;

import java.io.Serializable;
import java.util.Date;

public class ModelVo implements Serializable{

    public ModelVo(){
        super();
    }

    public ModelVo(Model model){
        this.modelId = model.getId();
        this.createDate = model.getCreateTime();
        this.modelName = model.getName();
        this.modelType = model.getCategory();
    }

    private String modelId;
    private String modelName;
    private String modelType;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;


    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getModelType() {
        return modelType;
    }

    public void setModelType(String modelType) {
        this.modelType = modelType;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }
}
