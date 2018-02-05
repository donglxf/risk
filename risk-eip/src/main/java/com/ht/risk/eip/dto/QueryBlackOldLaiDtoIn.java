package com.ht.risk.eip.dto;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel
public class QueryBlackOldLaiDtoIn{

	@ApiModelProperty(value = "真实姓名")
	private String realName;

	@ApiModelProperty(value = "证件号")
	private String identityCard;
}