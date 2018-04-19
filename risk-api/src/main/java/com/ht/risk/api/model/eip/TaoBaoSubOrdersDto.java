package com.ht.risk.api.model.eip;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class TaoBaoSubOrdersDto {

	@ApiModelProperty(value = "淘宝账号在魔蝎数据中的映射ID")
	private String mappingId;

	@ApiModelProperty(value = "交易id")
	private String tradeId;
	
	@ApiModelProperty(value = "商品id")
	private String itemId;
	
	@ApiModelProperty(value = "商品链接")
	private String itemUrl;
	
	@ApiModelProperty(value = "商品图片")
	private String itemPic;
	
	@ApiModelProperty(value = "商品名称")
	private String itemName;
	
	@ApiModelProperty(value = "商品数量")
	private Double quantity;
	
	@ApiModelProperty(value = "商品原价")
	private Double original;
	
	@ApiModelProperty(value = "商品真实交易价格")
	private Double realTotal;
}