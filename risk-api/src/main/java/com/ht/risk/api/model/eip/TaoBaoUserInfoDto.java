package com.ht.risk.api.model.eip;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel
public class TaoBaoUserInfoDto{

	@ApiModelProperty(value = "关联id")
	private String mappingId;
	
	@ApiModelProperty(value = "淘宝昵称")
	private String nick;
	
	@ApiModelProperty(value = "姓名")
	private String realName;
	
	@ApiModelProperty(value = "电话号码")
	private String phoneNumber;
	
	@ApiModelProperty(value = "绑定邮箱")
	private String email;
	
	@ApiModelProperty(value = "VIP等级")
	private String vipLevel;
	
	@ApiModelProperty(value = "成长值")
	private String vipCount;
	
	@ApiModelProperty(value = "绑定微博账号")
	private String weiboAccount;
	
	@ApiModelProperty(value = "绑定微博昵称")
	private String weiboNick;
	
	@ApiModelProperty(value = "淘宝头像图片")
	private String pic;
	
	@ApiModelProperty(value = "绑定的支付宝账号")
	private String alipayAccount;
	
	@ApiModelProperty(value = "天猫等级")
	private String tmallLevel;
	
	@ApiModelProperty(value = "天猫VIP值")
	private String tmallVipcount;
	
	@ApiModelProperty(value = "天猫信誉")
	private String tmallApass;
	
	@ApiModelProperty(value = "首次交易时间")
	private String firstOrdertime;
	
}