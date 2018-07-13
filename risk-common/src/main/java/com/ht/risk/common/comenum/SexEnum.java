package com.ht.risk.common.comenum;

public enum SexEnum {
    human("human", "男"), women("women", "女");
    private String code;
    private String name;

    SexEnum(String code, String name) {
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
