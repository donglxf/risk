package com.ht.risk.api.model.eip;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel
public class SelfDtoOut implements Serializable {

	@ApiModelProperty(value = "是否黑名单")
	private String isBlacklistUser;

}