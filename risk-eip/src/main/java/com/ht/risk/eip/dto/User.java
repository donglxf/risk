package com.ht.risk.eip.dto;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel
public class User{

	@ApiModelProperty(value = "姓名")
	private String realName;

	@ApiModelProperty(value = "证件号")
	private String identityCard;

	@ApiModelProperty(value = "功能id")
	private String functionCode;

	@ApiModelProperty(value = "查询类型")
	private String queryType;

}