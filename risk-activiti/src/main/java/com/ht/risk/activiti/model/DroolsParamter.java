package com.ht.risk.activiti.model;

import java.io.Serializable;
import java.util.Map;

public class DroolsParamter  implements Serializable {

	
	/**
	 * 场景code
	 */
	private String sence;
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

}
