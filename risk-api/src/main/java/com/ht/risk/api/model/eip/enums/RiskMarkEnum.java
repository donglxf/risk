package com.ht.risk.api.model.eip.enums;

/**
 * 风险标记
 * @author:喻尊龙
 * @date: 2018/2/2
 */
public enum RiskMarkEnum {
    TYPE_B1("B1","失信被执行人"),
    TYPE_B2("B2","被执行人"),
    TYPE_B3("B3","交通严重违章"),
    TYPE_C1("C1","手机号存在欺诈风险"),
    TYPE_C2("C2","卡号存在欺诈风险"),
    TYPE_C3("C3","身份证号存在欺诈风险"),
    TYPE_C4("C4","IP存在欺诈风险"),
    TYPE_99("99","权限不足");

    protected String code;
    protected String codeDesc;

    RiskMarkEnum(String code, String codeDesc) {
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
