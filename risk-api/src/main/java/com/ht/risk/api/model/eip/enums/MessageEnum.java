package com.ht.risk.api.model.eip.enums;

public enum MessageEnum {

    code("code","验证码"),
    trigger("trigger","触发短信"),
    market("market","营销短信"),
    voice("voice","语音短信"),
    alarm("alarm","预警短信"),
    notice("notice","通知短信"),
    cs("cs","催收短信"),
    ;
    protected String key;
    protected String codeDesc;

    MessageEnum(String key, String codeDesc) {
        this.key = key;
        this.codeDesc = codeDesc;
    }
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getCodeDesc() {
        return codeDesc;
    }

    public void setCodeDesc(String codeDesc) {
        this.codeDesc = codeDesc;
    }
}
