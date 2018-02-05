package com.ht.risk.api.model.eip;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 测试的
 */
@Data
@ApiModel
public class QueryUserInformationAuthDtoOut implements Serializable{

	@ApiModelProperty(value = "团贷网账号ID")
	private String platformUserNo;

	@ApiModelProperty(value = "用户类型（参考： 用户类型）")
	private String userType;

	@ApiModelProperty(value = "账号角色（参考： 账号角色）")
	private String userRole;

	@ApiModelProperty(value = "审核状态（参考： 审核状态）")
	private String auditStatus;

	@ApiModelProperty(value = "账号状态（参考： 账号状态）")
	private String activeStatus;

	@ApiModelProperty(value = "账号余额")
	private Double balance;

	@ApiModelProperty(value = "可用余额")
	private Double availableAmount;

	@ApiModelProperty(value = "冻结金额")
	private Double freezeAmount;

	@ApiModelProperty(value = "绑定的卡号 ,没有则表示没有绑卡")
	private String bankcardNo;

	@ApiModelProperty(value = "银行编码（参考： 银行编码）")
	private String bankCode;

	@ApiModelProperty(value = "预留手机号")
	private String mobile;

	@ApiModelProperty(value = "账号授权列表")
	private String authlist;

	@ApiModelProperty(value = "迁移导入会员状态， true表示已激活， false表示未激活，正常注册成功会员则默认状态为 true")
	private Boolean isImportUserActivate;

	@ApiModelProperty(value = "鉴权通过类型（参考： 鉴权类型）")
	private String accessType;

	@ApiModelProperty(value = "证件类型（参考： 证件）")
	private String idCardType;

	@ApiModelProperty(value = "开户名称，个人返回姓名，企业返回企业名称")
	private String idCardNo;

	@ApiModelProperty(value = "开户名称，个人返回姓名，企业返回企业名称")
	private String name;

}