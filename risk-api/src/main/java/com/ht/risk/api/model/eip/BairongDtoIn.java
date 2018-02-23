package com.ht.risk.api.model.eip;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel
public class BairongDtoIn{


	@ApiModelProperty(value = "真实姓名",required = true)
	private String realName;

	@ApiModelProperty(value = "证件证号",required = true)
	private String identityCard;

	@ApiModelProperty(value = "手机号码",required = true)
	private String mobilePhone;

	@ApiModelProperty(value = "家庭地址")
	private String address;

	@ApiModelProperty(value = "申请时间")
	private String applyTime;

	@ApiModelProperty(value = "")
	private String linkmanCell;

	@ApiModelProperty(value = "")
	private String linkmanName;

	@ApiModelProperty(value = "")
	private String linkmanRela;

	@ApiModelProperty(value = "银行卡号")
	private String bankId;

	@ApiModelProperty(value = "")
	private String email;

	@ApiModelProperty(value = "公司地址")
	private String bizAddr;

}