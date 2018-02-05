package com.ht.risk.api.model.eip;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class QueryBlackOldLaiDtoIn{

	@ApiModelProperty(value = "真实姓名")
	private String realName;

	@ApiModelProperty(value = "证件号")
	private String identityCard;


}