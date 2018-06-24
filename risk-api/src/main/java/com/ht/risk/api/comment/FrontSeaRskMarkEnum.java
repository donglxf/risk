package com.ht.risk.api.comment;

/**
 * 外联接口返回状态枚举
 *
 * @author dyb
 * @since 2018-02-27
 */
public enum FrontSeaRskMarkEnum {
    B1("B1", "失信被执行人"), B2("B2", "被执行人"), C1("C1", "手机号存在欺诈风险"), C2("C2", "卡号存在欺诈风险"), B3("B3", "交通严重违章"), C3("C3", "身份证号存在欺诈风险"), C4("C4", "IP存在欺诈风险"), N99("99", "权限不足");

    private String value;
    private String name;

    FrontSeaRskMarkEnum(String value, String name) {
        this.value = value;
        this.name = name;
    }

    public static FrontSeaRskMarkEnum findById(String value) {
        FrontSeaRskMarkEnum[] val = FrontSeaRskMarkEnum.values();
        for (FrontSeaRskMarkEnum v : val) {
            if (value.equals(v.getValue())) {
                return v;
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
