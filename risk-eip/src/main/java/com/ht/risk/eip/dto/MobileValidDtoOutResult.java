package com.ht.risk.eip.dto;

import com.ht.risk.api.model.eip.MobileValidDtoOut;

import java.io.Serializable;
import java.util.Date;

public class MobileValidDtoOutResult implements Serializable{

    private static final long serialVersionUID = 2625851352621457891L;
    private String returnCode;
    private String codeDesc;
    private String msg;
    private MobileValidDtoOut data;
    private Date createTime;
    private String identityCard;

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

    public MobileValidDtoOut getData() {
        return data;
    }

    public void setData(MobileValidDtoOut data) {
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
