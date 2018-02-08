package com.ht.risk.api.model.eip.enums;

/**
 *  运营商名称
 * @author:喻尊龙
 * @date: 2018/2/2
 */
public enum OperatorEnum {
    TYPE_1("1","电信"),
    TYPE_2("2","联通"),
    TYPE_3("3","移动");

    protected String code;
    protected String codeDesc;

    OperatorEnum(String code, String codeDesc) {
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
