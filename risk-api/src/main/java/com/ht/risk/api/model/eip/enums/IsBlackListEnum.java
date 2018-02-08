package com.ht.risk.api.model.eip.enums;

/**
 * 是否黑名单
 * @author:喻尊龙
 * @date: 2018/2/2
 */
public enum IsBlackListEnum {
    YES("1","是黑名单"),
    NO("0","不是黑名单");

    protected String code;
    protected String codeDesc;

    IsBlackListEnum(String code, String codeDesc) {
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
