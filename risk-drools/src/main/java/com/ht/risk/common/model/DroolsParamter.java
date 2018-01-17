package com.ht.risk.common.model;

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
	 */
	private String type;

	/**
	 * 业务数据
	 */
	private Map<String, Object> data;

	
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
