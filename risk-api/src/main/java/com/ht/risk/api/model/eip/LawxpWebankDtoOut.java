package com.ht.risk.api.model.eip;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

@Data
@ApiModel
public class LawxpWebankDtoOut{

	@ApiModelProperty(value = "查询结果总数")
	private int totalnumber;

	@ApiModelProperty(value = "当前数据页码")
	private int pg;

	@ApiModelProperty(value = "每页数量")
	private int pz;

	@ApiModelProperty(value = "查询开始时间，格式：yyyy-MM-dd 24HH:mm:ss")
	private String startime;

	@ApiModelProperty(value = "查询结束时间，格式：yyyy-MM-dd 24HH:mm:ss")
	private String endtime;

	@ApiModelProperty(value = "查询的具体结果")
	private List<Allmsglist> allmsglist;

}
