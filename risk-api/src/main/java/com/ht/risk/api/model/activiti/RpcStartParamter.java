package com.ht.risk.api.model.activiti;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class RpcStartParamter implements Serializable {

    /**
     * 模型定义ID
     */
    private String procDefId;

    private String userId;
    /**
     * 模型版本
     */
    private String version;
    /**
     * 批次号
     */
    private Long batchId;
    /**
     * 类型，1 验证，2 正式调用
     */
    private String type;

    private String taskId;

    private Map<String,Object> data;
    /**
     * 批次大小,批量调用时需要传递
     */
    private int batchSize;

    /**
     * 渠道类型
     */
    private String channelType; // 区分调用来源, htapp


    /**
     * 批量调用流程参数
     */
    private List<Map<String,Object>> datas;

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProcDefId() {
        return procDefId;
    }

    public void setProcDefId(String procDefId) {
        this.procDefId = procDefId;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public Long getBatchId() {
        return batchId;
    }

    public void setBatchId(Long batchId) {
        this.batchId = batchId;
    }

    public int getBatchSize() {
        return batchSize;
    }

    public void setBatchSize(int batchSize) {
        this.batchSize = batchSize;
    }

    public List<Map<String, Object>> getDatas() {
        return datas;
    }

    public void setDatas(List<Map<String, Object>> datas) {
        this.datas = datas;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
