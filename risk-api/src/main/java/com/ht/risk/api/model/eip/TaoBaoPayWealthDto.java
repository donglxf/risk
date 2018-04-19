package com.ht.risk.api.model.eip;

import io.swagger.annotations.ApiModelProperty;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel
public class TaoBaoPayWealthDto{

	@ApiModelProperty(value = "淘宝账号在魔蝎数据中的映射ID")
    private String mappingId;
	
	@ApiModelProperty(value = "账户余额")
    private Double balance;
	
	@ApiModelProperty(value = "余额宝历史累计收益")
    private Double totalProfit;		
    
	@ApiModelProperty(value = "余额宝金额")
    private Double totalQuotient;
	
	@ApiModelProperty(value = "花呗当前可用额度")
    private Double huabeiCreditamount;
	
	@ApiModelProperty(value = "花呗授信额度")
    private Double huabeiTotalcreditamount;
	
}