package com.ht.risk.rule.entity.enums;

import com.baomidou.mybatisplus.enums.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;

/**
 * 测试枚举
 */
public enum DataTypeEnum implements IEnum {
    INTEGER("Integer", "整形"),
    BOOLEAN("Boolean", "布尔类型"),
    DOUBLE("Double", "数字类型"),
    STRING("String", "字符串");

    private String value;
    private String desc;

    DataTypeEnum(final String value, final String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public Serializable getValue() {
        return this.value;
    }

    @JsonValue
    public String getDesc(){
        return this.desc;
    }
}
