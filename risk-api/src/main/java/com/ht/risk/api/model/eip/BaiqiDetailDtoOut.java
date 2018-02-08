package com.ht.risk.api.model.eip;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel
public class BaiqiDetailDtoOut{

	@ApiModelProperty(value = "失信名单第一级分类")
	private String firstType;

	@ApiModelProperty(value = "失信名单等级类别:高风险，中风险")
	private String grade;

	@ApiModelProperty(value = "失信名单第二级分类")
	private String secondType;

	@ApiModelProperty(value = "多头统计行业")
	private String name;

	@ApiModelProperty(value = "多头统计类型:all,single")
	private String type;

	@ApiModelProperty(value = "多头统计数字")
	private String value;

}