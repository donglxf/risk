package com.ht.risk.api.model.eip.enums;

/**
 * 来源代码
 * @author:喻尊龙
 * @date: 2018/2/2
 */
public enum SourceIdEnum {
    TYPE_A("A","信贷逾期风险"),
    TYPE_B("B","行政负面风险"),
    TYPE_C("C","欺诈风险"),
    TYPE_D("D","权限不足");

    protected String code;
    protected String codeDesc;

    SourceIdEnum(String code, String codeDesc) {
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
