package com.ht.risk.common.comenum;

public enum UnitTypeEnum {
    institutions("institutions", "机关事业单位"), governmentEnterprise("governmentEnterprise", "国有企业"), szenterprise("szenterprise", "三资企业"), corporateEnterprise("corporateEnterprise", "股份制企业"), individual("individual", "私营个体"), freework("freework", "自由职业者"), organization("organization", "社会团体"), unitother("unitother", "其他");
    private String code;
    private String name;

    UnitTypeEnum(String code, String name) {
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
