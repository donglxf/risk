package com.ht.risk.rule.vo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.ht.risk.rule.entity.VariableBind;

@SuppressWarnings("serial")
public class VariableBindVo extends VariableBind {
	/**
	 * 执行次数
	 */
	private int excuteTotal;
	
	/**
	 * 场景id
	 */
	private String senceId;
	
	/**
	 * 场景Code
	 */
	private String sceneIdentify;

	public int getExcuteTotal() {
		return excuteTotal;
	}

	public void setExcuteTotal(int excuteTotal) {
		this.excuteTotal = excuteTotal;
	}

	public String getSenceId() {
		return senceId;
	}

	public void setSenceId(String senceId) {
		this.senceId = senceId;
	}

	public String getSceneIdentify() {
		return sceneIdentify;
	}

	public void setSceneIdentify(String sceneIdentify) {
		this.sceneIdentify = sceneIdentify;
	}
	
	
}
