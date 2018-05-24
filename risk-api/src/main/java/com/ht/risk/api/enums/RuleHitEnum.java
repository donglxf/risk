package com.ht.risk.api.enums;

public enum RuleHitEnum {

    HIT("1","有命中"),
    UNHIT("0","没命中");

    protected String code;
    protected String codeDesc;

    private RuleHitEnum(String code,String codeDesc){
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
