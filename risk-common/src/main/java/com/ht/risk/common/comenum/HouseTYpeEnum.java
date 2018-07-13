package com.ht.risk.common.comenum;

public enum HouseTYpeEnum {
    commodityhouse("commodityhouse", "商品房"), villa("Villa", "别墅"), officeBuilding("officeBuilding", "写字楼"), apartment("apartment", "公寓"), shop("shop", "商铺"), other("other", "其他");
    private String code;
    private String name;

    HouseTYpeEnum(String code, String name) {
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
