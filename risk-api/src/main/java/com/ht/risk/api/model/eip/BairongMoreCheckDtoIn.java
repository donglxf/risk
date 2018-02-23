package com.ht.risk.api.model.eip;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel
public class BairongMoreCheckDtoIn{

	@ApiModelProperty(value = "真实姓名")
	private String realName;

	@ApiModelProperty(value = "证件证号")
	private String identityCard;

	@ApiModelProperty(value = "手机号码")
	private String mobilePhone;

}