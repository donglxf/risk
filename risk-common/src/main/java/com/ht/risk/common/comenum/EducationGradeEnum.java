package com.ht.risk.common.comenum;

public enum EducationGradeEnum {
    university("university", "本科"), graduate("graduate", "硕士"), doctor("doctor", "博士及以上"), junior("junior", "大专"), underdz("underdz", "中专及以下"), emphasisUniversity("emphasisUniversity", "重本科211985");
    private String code;
    private String name;

    EducationGradeEnum(String code, String name) {
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
