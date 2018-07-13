package com.ht.risk.common.comenum;

public enum OrganizationalFormEnum {
    soleproprietorship("soleproprietorship", "独资"), partnership("partnership", "合伙");
    private String code;
    private String name;

    OrganizationalFormEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
