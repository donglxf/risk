package com.ht.risk.activiti.vo;

import org.h2.result.SearchRow;

import java.io.Serializable;
import java.util.Map;

public class TaskVo implements Serializable{

    private String taskId;
    private String userId;
    private String taskName;
    private String executionId;
    private String description;
    private String processInstanceId;
    private Map<String,Object> variableData;

    public TaskVo() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getExecutionId() {
        return executionId;
    }

    public void setExecutionId(String executionId) {
        this.executionId = executionId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public Map<String, Object> getVariableData() {
        return variableData;
    }

    public void setVariableData(Map<String, Object> variableData) {
        this.variableData = variableData;
    }
}
