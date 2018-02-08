package com.ht.risk.api.model.eip;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel
public class BairongDtoOut implements Serializable{
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "流水号")
	private String swiftNumber;

	@ApiModelProperty(value = "产品输出标识")
	private BaiRongflagOut flag;

	@ApiModelProperty(value = "特殊名单核查")
	private BaiRongSpecialListCDto specialListC;

}