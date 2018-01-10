package com.ht.risk.activiti.model;

import org.activiti.engine.repository.ProcessDefinition;

import java.io.Serializable;
import java.util.Iterator;

public class ProcessDefinitionModel implements Serializable {

    public ProcessDefinitionModel() {
        super();
    }


    public ProcessDefinitionModel(ProcessDefinition definition) {
        this.deploymentId = definition.getDeploymentId();
        this.definitionId = definition.getId();
        this.version = String.valueOf(definition.getVersion());
        this.key = definition.getKey();
    }

    private String modelName;
    private String modelDesc;
    private String definitionId;
    private String version;
    private String key;
    private String deploymentId;

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getModelDesc() {
        return modelDesc;
    }

    public void setModelDesc(String modelDesc) {
        this.modelDesc = modelDesc;
    }

    public String getDefinitionId() {
        return definitionId;
    }

    public void setDefinitionId(String definitionId) {
        this.definitionId = definitionId;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDeploymentId() {
        return deploymentId;
    }

    public void setDeploymentId(String deploymentId) {
        this.deploymentId = deploymentId;
    }
}
