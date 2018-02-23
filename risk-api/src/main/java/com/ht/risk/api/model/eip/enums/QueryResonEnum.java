package com.ht.risk.api.model.eip.enums;

/**
 * 查询原因
 * @author:喻尊龙
 * @date: 2018/2/2
 */
public enum QueryResonEnum {
    TYPE_01("01","贷款审批"),
    TYPE_02("02","贷中管理"),
    TYPE_03("03","贷后管理"),
    TYPE_04("04","本人查询"),
    TYPE_05("05","异议查询"),
    TYPE_99("99","其他");

    protected String code;
    protected String codeDesc;

    QueryResonEnum(String code, String codeDesc) {
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
