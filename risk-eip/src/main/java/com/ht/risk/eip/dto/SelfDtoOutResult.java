package com.ht.risk.eip.dto;

import com.ht.risk.api.model.eip.FrontSeaDtoOut;
import com.ht.risk.api.model.eip.SelfDtoOut;
import com.ht.ussp.core.Result;

import java.io.Serializable;
import java.util.Date;

public class SelfDtoOutResult implements Serializable{


    private static final long serialVersionUID = -6609259042461755045L;

    private String returnCode;
    private String codeDesc;
    private String msg;
    private SelfDtoOut data;
    private Date createTime;
    private String identityCard;

    public SelfDtoOutResult() {
    	super();
    }

	public SelfDtoOutResult(Result<SelfDtoOut> result){
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

    public SelfDtoOut getData() {
        return data;
    }

    public void setData(SelfDtoOut data) {
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
