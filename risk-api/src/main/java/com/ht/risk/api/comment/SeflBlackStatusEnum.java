package com.ht.risk.api.comment;

/**
 * 外联接口返回状态枚举
 *
 * @author dyb
 * @since 2018-02-27
 */
public enum SeflBlackStatusEnum {
    noBlack("0", "不是黑名单"), yesBlack("1", "是黑名单");

    private String value;
    private String name;

    SeflBlackStatusEnum(String value, String name) {
        this.value = value;
        this.name = name;
    }

    public static SeflBlackStatusEnum findById(String value) {
        SeflBlackStatusEnum[] val = SeflBlackStatusEnum.values();
        for (SeflBlackStatusEnum v : val) {
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
