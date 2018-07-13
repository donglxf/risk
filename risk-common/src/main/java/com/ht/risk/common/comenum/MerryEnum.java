package com.ht.risk.common.comenum;

public enum MerryEnum {
    married("married", "已婚"), spinsterhood("spinsterhood", "未婚"), divorc("divorc", "离异"),marriedOther("marriedOther", "其他"),remarry("remarry", "再婚");
    private String code;
    private String name;

    MerryEnum(String code, String name) {
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
