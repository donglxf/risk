package com.ht.risk.api.model.eip;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel
public class TaobaoJudicialAuctionRespDto{

	@ApiModelProperty(value = "地址URL")
	private String itemUrl;

	@ApiModelProperty(value = "产品类型")
	private String productType;

	@ApiModelProperty(value = "标题")
	private String title;

	@ApiModelProperty(value = "标的状态")
	private String subjectStatus;

	@ApiModelProperty(value = "抓取时间")
	private String crawlerTime;

	@ApiModelProperty(value = "当前价")
	private String currentPrice;

	@ApiModelProperty(value = "起拍价")
	private String startPrice;

	@ApiModelProperty(value = "保证金")
	private String bond;

	@ApiModelProperty(value = "评估价")
	private String assessmentPrice;

	@ApiModelProperty(value = "加价幅度")
	private String priceMarkup;

	@ApiModelProperty(value = "竞价周期")
	private String biddingCycle;

	@ApiModelProperty(value = "延时周期")
	private String delayCycle;

	@ApiModelProperty(value = "拍卖类型")
	private String auctionType;

	@ApiModelProperty(value = "竞价规则")
	private String biddingRules;

	@ApiModelProperty(value = "处置单位")
	private String disposalUnit;

	@ApiModelProperty(value = "联系咨询方式")
	private String contactType;

	@ApiModelProperty(value = "竞买公告")
	private String bidNotice;

	@ApiModelProperty(value = "竞买须知")
	private String biddingNotes;

	@ApiModelProperty(value = "标的物介绍")
	private String subjectIntroduce;

	@ApiModelProperty(value = "竞买记录")
	private String bidRecord;

	@ApiModelProperty(value = "竞买成功确认书")
	private String confirmOfBid;

	@ApiModelProperty(value = "更新时间")
	private String updateTime;

}