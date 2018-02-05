package com.ht.risk.api.model.eip;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel
public class NetLoanOut implements Serializable {

	@ApiModelProperty(value = "黑名单用户名")
	private String user;

	@ApiModelProperty(value = "黑名单身份证号码")
	private String identityCard;

	@ApiModelProperty(value = "黑名单用户身份证地址")
	private String birthAddress;

	@ApiModelProperty(value = "性别")
	private String sex;

	@ApiModelProperty(value = "黑名单用户email")
	private String email;

	@ApiModelProperty(value = "黑名单真实姓名")
	private String name;

	@ApiModelProperty(value = "累计已还金额")
	private String refundmoney;

	@ApiModelProperty(value = "其他联系人")
	private String linkManName;

	@ApiModelProperty(value = "其他联系人关系")
	private String linkManRelation;

	@ApiModelProperty(value = "其他联系人号码")
	private String linkManMobileNo;

	@ApiModelProperty(value = "现住址")
	private String livingAddress;

	@ApiModelProperty(value = "黑名单移动电话")
	private String mobilephone;

	@ApiModelProperty(value = "座机号码")
	private String phone;

	@ApiModelProperty(value = "出借方")
	private String lenders;

	@ApiModelProperty(value = "累计借入总额")
	private String totalborrowamount;

	@ApiModelProperty(value = "平台垫付")
	private String cumulateadvancement;

	@ApiModelProperty(value = "所在公司名")
	private String companyName;

	@ApiModelProperty(value = "所在公司地址")
	private String companyAddress;

	@ApiModelProperty(value = "所在公司电话")
	private String companyPhone;

	@ApiModelProperty(value = "逾期笔数")
	private String overdueno;

	@ApiModelProperty(value = "逾期总金额")
	private String overduepayout;

	@ApiModelProperty(value = "待还滞纳金")
	private String alsoLateFee;

	@ApiModelProperty(value = "待还催收费用")
	private String alsoUrgentFee;

	@ApiModelProperty(value = "最长逾期天数")
	private String maxOverduedays;

	@ApiModelProperty(value = "最近一笔逾期的借款时间")
	private String lastOverdueborrowdate;

	@ApiModelProperty(value = "应还款时间")
	private String shouldpaymentsdate;

	@ApiModelProperty(value = "借款期数")
	private String lastBorrowtime;

	@ApiModelProperty(value = "数据更新时间")
	private String updatedate;

	@ApiModelProperty(value = "所在url")
	private String referenceLink;

	@ApiModelProperty(value = "爬取时间, 爬虫每次爬取都会修改此时间")
	private String crawldataupdatedate;

	@ApiModelProperty(value = "首次爬取时间")
	private String crawlCreate_time;

	@ApiModelProperty(value = "贷款平台")
	private String loanPlatform;

	@ApiModelProperty(value = "爬虫平台")
	private String spiderName;

}