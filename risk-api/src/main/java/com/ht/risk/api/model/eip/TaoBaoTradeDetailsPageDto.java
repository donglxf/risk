package com.ht.risk.api.model.eip;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel
public class TaoBaoTradeDetailsPageDto{

	@ApiModelProperty(value = "记录数")
    private Integer totalSize;	
	
	@ApiModelProperty(value = "页数")
    private Integer size;
	
	@ApiModelProperty(value = "交易明细")
    private List<TaoBaoTradeDetailsDto> tradedetails;
}