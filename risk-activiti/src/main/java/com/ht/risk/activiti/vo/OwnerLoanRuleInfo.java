package com.ht.risk.activiti.vo;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;

public class OwnerLoanRuleInfo implements Serializable {


    private static final long serialVersionUID = 2629717053568444641L;


    public OwnerLoanRuleInfo(){
        super();
    }

    public OwnerLoanRuleInfo(String identityCard,String realName,String mobilePhone,String functionCode,String interfaceName,String interfaceRemark ){
        this.identityCard = identityCard;
        this.realName = realName;
        this.mobilePhone = mobilePhone;
        this.functionCode = functionCode;
        this.interfaceName = interfaceName;
        this.interfaceRemark = interfaceRemark;
        interfaceResultCodeRemark = "未执行";
    }

    // 身份证
    private String identityCard;
    // 真实姓名
    private String realName;
    // 电话号码
    private String mobilePhone;
    // 接口编码

    @JSONField(name="FunctionCode")
    private String functionCode;
    // 接口名称
    @JSONField(name="InterfaceName")
    private String interfaceName;
    // 接口描述
    @JSONField(name="InterfaceRemark")
    private String interfaceRemark;
    // 接口返回
    private String resultJson;
    // 是否调用成功
    @JSONField(name="IsInvokeSuccess")
    private boolean isInvokeSuccess;
    // 执行情况描述
    @JSONField(name="InterfaceResultCodeRemark")
    private String interfaceResultCodeRemark;
    // 是否命中
    @JSONField(name="TsTarget")
    private boolean tsTarget;
    // 创建时间
    @JSONField(name="create_time")
    private String createTime;
    // 返回时间
    @JSONField(name="call_endtime")
    private String callEndtime;
    // 接口花费时间，单位秒
    private long call_second;


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

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getFunctionCode() {
        return functionCode;
    }

    public void setFunctionCode(String functionCode) {
        this.functionCode = functionCode;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getInterfaceRemark() {
        return interfaceRemark;
    }

    public void setInterfaceRemark(String interfaceRemark) {
        this.interfaceRemark = interfaceRemark;
    }

    public String getResultJson() {
        return resultJson;
    }

    public void setResultJson(String resultJson) {
        this.resultJson = resultJson;
    }

    public boolean isInvokeSuccess() {
        return isInvokeSuccess;
    }

    public void setInvokeSuccess(boolean invokeSuccess) {
        isInvokeSuccess = invokeSuccess;
    }

    public String getInterfaceResultCodeRemark() {
        return interfaceResultCodeRemark;
    }

    public void setInterfaceResultCodeRemark(String interfaceResultCodeRemark) {
        this.interfaceResultCodeRemark = interfaceResultCodeRemark;
    }

    public boolean isTsTarget() {
        return tsTarget;
    }

    public void setTsTarget(boolean tsTarget) {
        this.tsTarget = tsTarget;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCallEndtime() {
        return callEndtime;
    }

    public void setCallEndtime(String callEndtime) {
        this.callEndtime = callEndtime;
    }

    public long getCall_second() {
        return call_second;
    }

    public void setCall_second(long call_second) {
        this.call_second = call_second;
    }
}
