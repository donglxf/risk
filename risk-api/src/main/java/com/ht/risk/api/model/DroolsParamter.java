package com.ht.risk.api.model;

import java.io.Serializable;
import java.util.Map;

public class DroolsParamter implements Serializable {

	
	/**
	 * 场景code
	 */
	private String sence;

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
