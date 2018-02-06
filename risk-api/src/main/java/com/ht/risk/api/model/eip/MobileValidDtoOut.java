package com.ht.risk.api.model.eip;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel
public class MobileValidDtoOut{

	@ApiModelProperty(value = "运营商名称，请参考运营商名称")
	private String serviceProvider;

	@ApiModelProperty(value = "外部订单流水号")
	private String outOrderNo;

	@ApiModelProperty(value = "考拉交易流水")
	private String jnlNo;

	@ApiModelProperty(value = "时间戳")
	private String timeStamp;

}