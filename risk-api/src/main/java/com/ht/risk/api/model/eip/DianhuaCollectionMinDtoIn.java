package com.ht.risk.api.model.eip;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

@Data
@ApiModel
public class DianhuaCollectionMinDtoIn {

    @ApiModelProperty(value = "查询的手机号码")
    private String mobilePhone;

    @ApiModelProperty(value = "通话详单")
    private List<CallLog> callLog;

    @ApiModelProperty(value = "客户端对用户的唯一标识")
    private String uid;

}

@Data
class CallLog {
    @ApiModelProperty(value = "与查询号码通话的电话号码")
    private String call_tel;

    @ApiModelProperty(value = "时间戳(秒)，通话发生时间")
    private int call_time;

    @ApiModelProperty(value = "通话时长（秒）")
    private int call_duration;

    @ApiModelProperty(value = "1：主叫，2：被叫")
    private int call_method;
}