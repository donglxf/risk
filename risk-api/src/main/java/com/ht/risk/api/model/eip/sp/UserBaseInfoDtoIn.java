package com.ht.risk.api.model.eip.sp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class UserBaseInfoDtoIn {
    @ApiModelProperty(value = "手机号码")
    private String mobilePhone;
    @ApiModelProperty(value = "回调url")
    private String callbackUrl;
    @ApiModelProperty(value = "时间戳")
    private String currentTime;
    @ApiModelProperty(value = "用户id")
    private String userId;
    @ApiModelProperty(value = "真实姓名")
    private String realName;


}
