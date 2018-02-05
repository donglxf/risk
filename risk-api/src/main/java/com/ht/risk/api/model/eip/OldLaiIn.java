package com.ht.risk.api.model.eip;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class OldLaiIn {

	@ApiModelProperty(value = "证件证号",required = true,example = "")
	private String identityCard;

	@ApiModelProperty(value = "真实姓名")
	private String realName;
}