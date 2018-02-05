package com.ht.risk.eip.dto;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel
public class NetLoanIn{

	@ApiModelProperty(value = "证件证号",required = true,example = "")
	private String identityCard;

	@ApiModelProperty(value = "真实姓名")
	private String realName;

	@ApiModelProperty(value = "手机号码")
	private String mobilePhone;
}