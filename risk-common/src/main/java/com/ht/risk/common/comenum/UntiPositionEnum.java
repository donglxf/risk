package com.ht.risk.common.comenum;

public enum UntiPositionEnum {
    worker("worker", "普通工人"), deploymentzg("deploymentzg", "部门主管"), deploymentManager("deploymentManager", "部门经理"), stockholders("stockholders", "企业股东"), legalperson("legalperson", "企业法人"), positionother("positionother", "其他");
    private String code;
    private String name;

    UntiPositionEnum(String code, String name) {
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
