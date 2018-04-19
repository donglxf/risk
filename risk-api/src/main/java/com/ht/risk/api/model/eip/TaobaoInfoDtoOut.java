package com.ht.risk.api.model.eip;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel
public class TaobaoInfoDtoOut{

	@ApiModelProperty(value = "用户基本信息")
    private TaoBaoUserInfoDto userinfo;
    
	@ApiModelProperty(value = "支付宝资产")
    private TaoBaoPayWealthDto alipaywealth;

	@ApiModelProperty(value = "淘宝收货地址")
    private List<TaoBaoDeliverAddressDto> deliveraddress;
    
	@ApiModelProperty(value = "最近几笔订单收货地址")
    private List<TaoBaoRecentAddressDto> recentaddress;
    
	@ApiModelProperty(value = "淘宝订单信息")
    private TaoBaoTradeDetailsPageDto tradedetails;
	
}