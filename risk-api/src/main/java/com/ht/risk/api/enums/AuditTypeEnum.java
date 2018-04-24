package com.ht.risk.api.enums;

public enum AuditTypeEnum {

    PERSONAL("0","人工审核"),
    AUTO("1","自动审核"),
    REFUSE("2","拒绝");


    private String code;
    private String codeDesc;

    private AuditTypeEnum(String code,String codeDesc){
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
