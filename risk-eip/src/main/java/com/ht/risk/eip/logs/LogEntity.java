package com.ht.risk.eip.logs;

import java.io.Serializable;
import java.util.Date;

public class LogEntity implements Serializable {

    private static final long serialVersionUID = -1323188015903957986L;

    public LogEntity() {
        super();
    }

    public LogEntity(String functionCode, String type, Object inParamter, Object outParamter, Date createTime,long spendTime) {
        this.functionCode = functionCode;
        this.outParamter = outParamter;
        this.inParamter = inParamter;
        this.createTime = createTime;
        this.type = type;
        this.spendTime = spendTime;
    }

    private String functionCode;
    private String type;
    private Object outParamter;
    private Object inParamter;
    private Date createTime;
    private Long spendTime;

    public String getFunctionCode() {
        return functionCode;
    }

    public void setFunctionCode(String functionCode) {
        this.functionCode = functionCode;
    }

    public Object getOutParamter() {
        return outParamter;
    }

    public void setOutParamter(Object outParamter) {
        this.outParamter = outParamter;
    }

    public Object getInParamter() {
        return inParamter;
    }

    public void setInParamter(Object inParamter) {
        this.inParamter = inParamter;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getSpendTime() {
        return spendTime;
    }

    public void setSpendTime(Long spendTime) {
        this.spendTime = spendTime;
    }
}
