package com.ht.risk.api.model.eip.enums;

/**
 * 功能编号
 * @author:喻尊龙
 * @date: 2018/2/2
 */
public enum FunctionCodeEnum {
    NETLOAN("10013001","网贷黑名单"),
    OLDLAI("10013002","老赖黑名单"),
    SELF("10013003","自有黑名单"),
    FRONTSEA("10011002","前海黑名单"),
    MOBILEVALID("10017002","手机号验证"),
    NEGATIVESEARCH("10020001","负面消息查询");

    protected String code;
    protected String codeDesc;

    FunctionCodeEnum(String code, String codeDesc) {
        this.code = code;
        this.codeDesc = codeDesc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCodeDesc() {
        return codeDesc;
    }

    public void setCodeDesc(String codeDesc) {
        this.codeDesc = codeDesc;
    }
}
