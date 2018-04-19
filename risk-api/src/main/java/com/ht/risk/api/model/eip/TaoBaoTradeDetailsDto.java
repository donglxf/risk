package com.ht.risk.api.model.eip;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel
public class TaoBaoTradeDetailsDto{

	@ApiModelProperty(value = "淘宝账号在魔蝎数据中的映射ID")
    private String mappingId;
	
	@ApiModelProperty(value = "交易id")
    private String tradeId;
	
	@ApiModelProperty(value = "交易状态")
    private String tradeStatus;
	
	@ApiModelProperty(value = "交易状态中文")
    private String tradeText;
	
	@ApiModelProperty(value = "交易时间")
    private String tradeCreatetime;
	
	@ApiModelProperty(value = "订单金额")
    private Double actualFee;
	
	@ApiModelProperty(value = "卖家id")
    private Long sellerId;
	
	@ApiModelProperty(value = "卖家昵称")
    private String sellerNick;
	
	@ApiModelProperty(value = "店铺名称")
    private String sellerShopname;
	
	@ApiModelProperty(value = "商品信息")
    private List<TaoBaoSubOrdersDto> subOrders;
}