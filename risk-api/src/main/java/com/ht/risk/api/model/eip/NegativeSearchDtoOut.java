package com.ht.risk.api.model.eip;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

@Data
@ApiModel
public class NegativeSearchDtoOut {

	@ApiModelProperty(value = "姓名")
	private String name;

	@ApiModelProperty(value = "身份证号")
	private String idCard;

	@ApiModelProperty(value = "是否在逃,0：否1：是")
	private String isEscape;

	@ApiModelProperty(value = "是否有前科,0：否1：是")
	private String isCrime;

	@ApiModelProperty(value = "是否吸毒,0：否1：是")
	private String isDrug;

	@ApiModelProperty(value = "是否涉毒,0：否1：是")
	private String isDrugRelated;

	@ApiModelProperty(value = "前科事件数量")
	private String checkCount;

	@ApiModelProperty(value = "案发时间[a,b)的时间区间，代表案发距今a-b年(不含b)例:['[5,10]']")
	private List caseTime;

	@ApiModelProperty(value = "事件类别")
	private String caseType;

}