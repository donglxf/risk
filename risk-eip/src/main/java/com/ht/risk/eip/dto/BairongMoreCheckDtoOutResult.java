package com.ht.risk.eip.dto;

import java.io.Serializable;
import java.util.Date;

import com.ht.risk.api.model.eip.BairongMoreCheckDtoOut;
import com.ht.ussp.core.Result;

public class BairongMoreCheckDtoOutResult implements Serializable {

	private static final long serialVersionUID = 80867259502892633L;
	private String returnCode;
	private String codeDesc;
	private String msg;
	private BairongMoreCheckDtoOut data;
	private Date createTime;
	private String identityCard;

	public BairongMoreCheckDtoOutResult() {
		super();
	}

	public BairongMoreCheckDtoOutResult(Result<BairongMoreCheckDtoOut> result) {
		this.codeDesc = result.getCodeDesc();
		this.returnCode = result.getReturnCode();
		this.msg = result.getMsg();
		this.data = result.getData();
		this.createTime = new Date();
	}

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public String getCodeDesc() {
		return codeDesc;
	}

	public void setCodeDesc(String codeDesc) {
		this.codeDesc = codeDesc;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public BairongMoreCheckDtoOut getData() {
		return data;
	}

	public void setData(BairongMoreCheckDtoOut data) {
		this.data = data;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getIdentityCard() {
		return identityCard;
	}

	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}
}
