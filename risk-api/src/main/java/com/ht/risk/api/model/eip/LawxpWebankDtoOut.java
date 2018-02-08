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

@Data
@ApiModel
class Allmsglist{
	@ApiModelProperty(value = "信息类型，请参考案件类型")
	private int bigtype;

	@ApiModelProperty(value = "数据来源")
	private String source;

	@ApiModelProperty(value = "采集时间,格式：yyyy-MM-dd")
	private String posttime;

	@ApiModelProperty(value = "案件编号")
	private String casenum;

	@ApiModelProperty(value = "案件类型")
	private String type;

	@ApiModelProperty(value = "案件时间，格式：yyyy-MM-dd")
	private String datetime;

	@ApiModelProperty(value = "案件地点")
	private String address;

	@ApiModelProperty(value = "涉案金额")
	private String money;

}