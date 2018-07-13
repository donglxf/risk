package com.ht.risk.common.comenum;

public enum CityTypeEnum {
    cityTypeA("1", "cityTypeA"), cityTypeB("2", "cityTypeB"), cityTypeC("3", "cityTypeC");
    private String code;
    private String name;

    CityTypeEnum(String code, String name) {
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
