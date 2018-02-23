package com.ht.risk.api.model.eip.enums;

/**
 * 数据状态
 * @author:喻尊龙
 * @date: 2018/2/2
 */
public enum DataStatusEnum {
    TYPE_1("1","已验证"),
    TYPE_2("2","未验证"),
    TYPE_3("3","异议中"),
    TYPE_99("99","权限不足");

    protected String code;
    protected String codeDesc;

    DataStatusEnum(String code, String codeDesc) {
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
