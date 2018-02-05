package com.ht.risk.api.model.eip;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class OldLaiOut{

	@ApiModelProperty(value = "记录ID,由爬虫程序生成")
	private String globalId;

	@ApiModelProperty(value = "真实姓名")
	private String partyName;

	@ApiModelProperty(value = "身份证号")
	private String partyIdentityCard;

	@ApiModelProperty(value = "创建时间")
	private String createTime;

	@ApiModelProperty(value = "执行法院")
	private String courtName;

	@ApiModelProperty(value = "省份")
	private String areaName;

	@ApiModelProperty(value = "执行依据文号,2014)潭执审字第30号")
	private String gistId;

	@ApiModelProperty(value = "立案时间,2014年03月26日")
	private String regDate;

	@ApiModelProperty(value = "案号,(2014)潭非执字第00051号")
	private String caseCode;

	@ApiModelProperty(value = "做出执行依据单位,建阳市人民法院")
	private String gistUnit;

	@ApiModelProperty(value = "生效法律文书确定的义务")
	private String duty;

	@ApiModelProperty(value = "被执行人的履行情况")
	private String performance;

	@ApiModelProperty(value = "失信被执行人行为具体情形")
	private String disruptTypeName;

	@ApiModelProperty(value = "发布时间")
	private String publishDate;

}