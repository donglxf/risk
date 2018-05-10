package com.ht.risk.api.model.drools;

import java.util.List;
import java.util.Map;

public class DroolsParamter {

	/**
	 * 场景code
	 */
	private String sence;

	/**
	 * 版本号
	 */
	private String version;

	/**
	 * 模型名
	 */
	private String modelName;

	/**
	 * 流程运行实例id
	 */
	private String processInstanceId;
	
	/**
	 * 类型
	 * 1：业务调用；2：规则验证;3:模型调用；4：模型验证
	 */
	private String type;

	/**
	 * 批次号
	 */
	private String batchId;



	/**
	 * 业务数据
	 */
	private Map<String, Object> data;

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSence() {
		return sence;
	}

	public void setSence(String sence) {
		this.sence = sence;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

}
