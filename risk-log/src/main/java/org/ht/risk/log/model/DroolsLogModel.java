package org.ht.risk.log.model;

public class DroolsLogModel {
	private String procInstId;
	private String sceneId;
	private String droolsVersion;
	private String inParam;
	private String result;
	private int executeTotal;

	public String getProcInstId() {
		return procInstId;
	}

	public void setProcInstId(String procInstId) {
		this.procInstId = procInstId;
	}

	public String getSceneId() {
		return sceneId;
	}

	public void setSceneId(String sceneId) {
		this.sceneId = sceneId;
	}

	public String getDroolsVersion() {
		return droolsVersion;
	}

	public void setDroolsVersion(String droolsVersion) {
		this.droolsVersion = droolsVersion;
	}

	public String getInParam() {
		return inParam;
	}

	public void setInParam(String inParam) {
		this.inParam = inParam;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public int getExecuteTotal() {
		return executeTotal;
	}

	public void setExecuteTotal(int executeTotal) {
		this.executeTotal = executeTotal;
	}

}
