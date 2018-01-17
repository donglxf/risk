package com.ht.risk.common.model;

import com.ht.risk.model.fact.RuleExecutionObject;
import io.swagger.annotations.ApiModelProperty;

public class RuleExcuteResult {


    public RuleExcuteResult(){
        super();
    }

    public RuleExcuteResult(int code, String msg, RuleExecutionObject data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 错误代码
     */
    @ApiModelProperty(required = true, value = "错误代码")
    private int code;  // 0-成功,其他失败
    /**
     * 错误描述
     */
    @ApiModelProperty(required = true, value = "错误描述")
    private String msg;

    /**
     * 传递给请求者的数据
     */
    @ApiModelProperty(value = "传递给请求者的数据")
    private RuleExecutionObject data;


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public RuleExecutionObject getData() {
        return data;
    }

    public void setData(RuleExecutionObject data) {
        this.data = data;
    }
}
