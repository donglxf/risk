package com.ht.risk.eip.dto;

import com.ht.risk.api.model.eip.DianhuaCollectionMinDtoOut;
import com.ht.risk.api.model.eip.NetLoanOut;
import com.ht.ussp.core.Result;

import java.io.Serializable;
import java.util.Date;

public class DianhuaCollectionMinDtoOutResult<T> implements Serializable{

    private static final long serialVersionUID = 5699948896986873641L;

    private String id;
    private String returnCode;
    private String codeDesc;
    private String msg;
    private DianhuaCollectionMinDtoOut data;
    private Date createTime;
    private String funName;
    private String uid;
    private String realName;

    public DianhuaCollectionMinDtoOutResult() {
		super();
	}

    public DianhuaCollectionMinDtoOutResult(Result<DianhuaCollectionMinDtoOut> result){
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

    public DianhuaCollectionMinDtoOut getData() {
        return data;
    }

    public void setData(DianhuaCollectionMinDtoOut data) {
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

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }
}
