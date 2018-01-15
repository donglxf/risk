package com.ht.risk.rule.vo;

import java.io.Serializable;
import java.util.Map;

public class VariableVo implements Serializable {

    // 变量中文名称
    private String en;
    // 英文名称
    private String cn;
    // 提交代码
    private String submitName;
    // 描述
    private String desc;
    // 类型
    private String type;
    // 下拉、多选等数据
    private Map<String,String> optionData;

    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }

    public String getCn() {
        return cn;
    }

    public void setCn(String cn) {
        this.cn = cn;
    }

    public String getSubmitName() {
        return submitName;
    }

    public void setSubmitName(String submitName) {
        this.submitName = submitName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, String> getOptionData() {
        return optionData;
    }

    public void setOptionData(Map<String, String> optionData) {
        this.optionData = optionData;
    }
}
