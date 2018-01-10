package com.ht.risk.api.model.drools;


import java.io.Serializable;

public class RuleExcuteResult implements Serializable {


    public RuleExcuteResult(){
        super();
    }

    public RuleExcuteResult(int code, String msg, RuleExecutionObject data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    private int code;
    private String msg;

    private RuleExecutionObject data;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public RuleExecutionObject getData() {
        return data;
    }

    public void setData(RuleExecutionObject data) {
        this.data = data;
    }
}
