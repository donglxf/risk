package com.ht.risk.eip.dto;

import com.ht.risk.api.model.eip.NetLoanOut;
import com.ht.risk.api.model.eip.OldLaiOut;
import com.ht.ussp.core.Result;

import java.io.Serializable;
import java.util.Date;

public class NetLoanOutResult implements Serializable{

    private static final long serialVersionUID = 5699948896986873641L;

    private String id;
    private String returnCode;
    private String codeDesc;
    private String msg;
    private NetLoanOut data;
    private Date createTime;
    private String funName;
    private String identityCard;
    private String realName;
    
    public NetLoanOutResult() {
		super();
	}
    
    public NetLoanOutResult(Result<NetLoanOut> result){
        this.codeDesc = result.getCodeDesc();
        this.returnCode = result.getReturnCode();
        this.msg = result.getMsg();
        this.data = result.getData();
        this.createTime = new Date();
    }

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public NetLoanOut getData() {
        return data;
    }

    public void setData(NetLoanOut data) {
        this.data = data;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getFunName() {
        return funName;
    }

    public void setFunName(String funName) {
        this.funName = funName;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }
}
