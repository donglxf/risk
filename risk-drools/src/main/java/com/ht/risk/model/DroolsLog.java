package com.ht.risk.model;

public class DroolsLog {
	private String procInstId;
	private String sceneId;
	private String droolsVersion;
	private String inParam;
	private String result;
	private int executeTotal;
	private String sceneCode;
	private String sceneName;
	private String modelName;

	
	public String getSceneCode() {
		return sceneCode;
	}

	public void setSceneCode(String sceneCode) {
		this.sceneCode = sceneCode;
	}

	public String getSceneName() {
		return sceneName;
	}

	public void setSceneName(String sceneName) {
		this.sceneName = sceneName;
	}

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

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	
}
