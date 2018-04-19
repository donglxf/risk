package com.ht.risk.api.model.eip.sp;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel
public class QueryPhoneRecordDtoIn{

	@ApiModelProperty(value = "真实姓名")
    private String realName;
	
	@ApiModelProperty(value = "真实姓名")
    private String identityCard;

	@ApiModelProperty(value = "手机号码")
    private String mobilePhone;
	
	@ApiModelProperty(value = "月份",required = false)
    private String month;

	@ApiModelProperty(value = "时间戳")
    private String currentTime;

	@ApiModelProperty(value = "用户id")
    private String userId;

	@ApiModelProperty(value = "忽略授权过期时间标志",required = false)
    private String reqLatestCRFlag;

}