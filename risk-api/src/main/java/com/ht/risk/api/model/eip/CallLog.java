package com.ht.risk.api.model.eip;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CallLog {
    @ApiModelProperty(value = "与查询号码通话的电话号码")
    private String call_tel;

    @ApiModelProperty(value = "时间戳(秒)，通话发生时间")
    private int call_time;

    @ApiModelProperty(value = "通话时长（秒）")
    private int call_duration;

    @ApiModelProperty(value = "1：主叫，2：被叫")
    private int call_method;
}
