package com.ht.risk.eip.dto;

import com.ht.risk.api.model.eip.NetLoanOut;
import com.ht.risk.api.model.eip.OldLaiOut;
import com.ht.ussp.core.Result;

import java.io.Serializable;
import java.util.Date;

public class OldLaiOutResult implements Serializable {

    private static final long serialVersionUID = -8269929054305393468L;

    private String id;
    private String returnCode;
    private String codeDesc;
    private String msg;
    private OldLaiOut data;
    private Date createTime;
    private String identityCard;

    public OldLaiOutResult(){
        super();
    }

    public OldLaiOutResult(Result<OldLaiOut> result){
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

    public OldLaiOut getData() {
        return data;
    }

    public void setData(OldLaiOut data) {
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
