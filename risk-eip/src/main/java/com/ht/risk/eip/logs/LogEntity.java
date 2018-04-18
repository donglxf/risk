package com.ht.risk.eip.logs;

import java.io.Serializable;
import java.util.Date;

public class LogEntity implements Serializable {

    private static final long serialVersionUID = -1323188015903957986L;

    public LogEntity() {
        super();
    }

    public LogEntity(String app,String functionCode, String type, Object inParamter, Object outParamter, Date createTime,long spendTime) {
        this.app = app;
        this.functionCode = functionCode;
        this.outParamter = outParamter;
        this.inParamter = inParamter;
        this.createTime = createTime;
        this.type = type;
        this.spendTime = spendTime;
    }


    /**
     * 接口编码，必填
     */
    private String functionCode;
    /**
     * 接口描述，必填
     */
    private String functionDesc;
    /**
     * 系统编码，必填
     */
    private String app;
    /**
     * 业务编码，必填
     */
    private String businessType;
    /**
     * 调用类型，0 缓存获取，1 直接获取，必填
     */
    private String type;
    /**
     *  出参，必填
     */
    private Object outParamter;
    /**
     *  入参，必填
     */
    private Object inParamter;
    /**
     * 创建时间，必填
     */
    private Date createTime;
    /**
     *  消费时间，必填
     */
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

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getFunctionDesc() {
        return functionDesc;
    }

    public void setFunctionDesc(String functionDesc) {
        this.functionDesc = functionDesc;
    }
}
