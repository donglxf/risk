package com.ht.risk.api.model.eip;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel
public class FrontSeaDtoIn implements Serializable {

	@ApiModelProperty(value = "主题名称")
	private String realName;

	@ApiModelProperty(value = "证件证号")
	private String identityCard;

	@ApiModelProperty(value = "证件类型，请参考证件类型")
	private String idType;

	@ApiModelProperty(value = "查询原因，请参考查询原因")
	private String reasonNo;



}