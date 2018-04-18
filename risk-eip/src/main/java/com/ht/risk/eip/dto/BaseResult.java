package com.ht.risk.eip.dto;

import com.ht.risk.api.model.eip.BaiqishiDtoOut;
import com.ht.ussp.core.Result;

import java.util.Date;

public class BaseResult {

    public BaseResult(){
        super();
    }

    public BaseResult(Result result){
        super();
        this.returnCode = result.getReturnCode();
        this.codeDesc = result.getCodeDesc();
        this.msg = result.getMsg();
        this.createTime = new Date(System.currentTimeMillis());
    }

    private String returnCode;
    private String codeDesc;
    private String msg;
    private Date createTime;


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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
