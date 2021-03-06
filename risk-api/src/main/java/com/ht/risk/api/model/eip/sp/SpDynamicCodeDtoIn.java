package com.ht.risk.api.model.eip.sp;

import com.ht.risk.api.comment.commEntryIn;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class SpDynamicCodeDtoIn extends commEntryIn {
    @ApiModelProperty(value = "手机号码")
    private String mobilePhone;
    @ApiModelProperty(value = "详单验证码")
    private String authCode;
    @ApiModelProperty(value = "手机动态码")
    private String smsCode;
    @ApiModelProperty(value = "时间戳")
    private String currentTime;
    @ApiModelProperty(value = "用户id")
    private String userId;
    @ApiModelProperty(value = "身份证号")
    private String idCard;


}
