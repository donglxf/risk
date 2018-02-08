package com.ht.risk.api.model.eip;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel
public class LawxpPersonClassifyDtoOut{

	@ApiModelProperty(value = "案例条数")
	private int anli;

	@ApiModelProperty(value = "失信条数")
	private int shixinnum;

	@ApiModelProperty(value = "执行条数")
	private int zhixingnum;

	@ApiModelProperty(value = "税务条数")
	private int shuiwunum;

	@ApiModelProperty(value = "催欠条数")
	private int cuiqiannum;

	@ApiModelProperty(value = "网贷条数")
	private int wangdainum;

}