package com.ht.risk.rule.vo;

import com.ht.risk.api.model.log.RpcHitRuleInfo;

import java.io.Serializable;
import java.util.List;

public class ModelLogDetailVo implements Serializable{

    private static final long serialVersionUID = -2170040078397113151L;

    private String msg;
    private String modelMsg;
    private List<RpcHitRuleInfo> hitRules;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getModelMsg() {
        return modelMsg;
    }

    public void setModelMsg(String modelMsg) {
        this.modelMsg = modelMsg;
    }

    public List<RpcHitRuleInfo> getHitRules() {
        return hitRules;
    }

    public void setHitRules(List<RpcHitRuleInfo> hitRules) {
        this.hitRules = hitRules;
    }
}
