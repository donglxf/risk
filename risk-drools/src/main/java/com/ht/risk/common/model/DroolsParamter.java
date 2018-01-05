package com.ht.risk.common.model;

import java.util.Map;

public class DroolsParamter {

	
	/**
	 * 场景code
	 */
	private String sence;

	/**
	 * 流程运行实例id
	 */
	private String processInstanceId;

	/**
	 * 业务数据
	 */
	private Map<String, Object> data;

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
}
