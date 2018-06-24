package com.ht.risk.api.comment;

/**
 * 外联接口返回状态枚举
 *
 * @author dyb
 * @since 2018-02-27
 */
public enum FrontSeaSourceIdEnum {
    A("A", "信贷逾期风险"), B("B", "行政负面风险"),C("C","欺诈风险"),D("D","权限不足");

    private String value;
    private String name;

    FrontSeaSourceIdEnum(String value, String name) {
        this.value = value;
        this.name = name;
    }

    public static FrontSeaSourceIdEnum findById(String value) {
        FrontSeaSourceIdEnum[] val = FrontSeaSourceIdEnum.values();
        for (FrontSeaSourceIdEnum v : val) {
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
