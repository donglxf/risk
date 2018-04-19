package com.ht.risk.api.model.eip;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel
public class TaoBaoRecentAddressDto{

	@ApiModelProperty(value = "订单ID")
    private String tradeId;
	
	@ApiModelProperty(value = "订单时间")
    private String tradeCreatetime;
	
	@ApiModelProperty(value = "订单费用")
    private String actualFee;
	
	@ApiModelProperty(value = "收货地址中的姓名")
    private String deliverName;
	
	@ApiModelProperty(value = "收货地址中的手机")
    private String deliverMobilephone;
	
	@ApiModelProperty(value = "收货地址中的固定电话")
    private String deliverFixedphone;
	
	@ApiModelProperty(value = "省份")
    private String province;
	
	@ApiModelProperty(value = "城市")
    private String city;
	
	@ApiModelProperty(value = "详细地址")
    private String deliverAddress;
	
	@ApiModelProperty(value = "邮编")
    private String deliverPostcode;
	
	@ApiModelProperty(value = "发票抬头")
    private String invoiceName;
	
}