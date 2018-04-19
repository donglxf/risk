package com.ht.risk.api.model.eip.sp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class SendDynamicCodeDtoIn {
    @ApiModelProperty(value = "手机号码")
    private String mobilePhone;
    @ApiModelProperty(value = "详单验证码")
    private String authCode;
    @ApiModelProperty(value = "身份证号")
    private String smsCode;
    @ApiModelProperty(value = "时间戳")
    private String currentTime;
    @ApiModelProperty(value = "用户id")
    private String userId;


}
