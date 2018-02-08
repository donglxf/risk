package com.ht.risk.api.model.eip.enums;

/**
 * 证件类型
 * @author:喻尊龙
 * @date: 2018/2/2
 */
public enum IdTypeEnum {
    TYPE_0("0","身份证"),
    TYPE_1("1","户口簿"),
    TYPE_2("2","护照"),
    TYPE_3("3","军官证"),
    TYPE_4("4","士兵证"),
    TYPE_5("5","港澳居民来往内地通行证"),
    TYPE_6("6","台湾同胞来往内地通行证"),
    TYPE_7("7","临时身份证"),
    TYPE_8("8","外国人居留证"),
    TYPE_9("9","警官证"),
    TYPE_X("X","其他证件");

    protected String code;
    protected String codeDesc;

    IdTypeEnum(String code, String codeDesc) {
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
