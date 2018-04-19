package com.ht.risk.api.model.eip;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel
public class TaoBaoDeliverAddressDto{

	@ApiModelProperty(value = "淘宝账号在魔蝎数据中的映射ID")
    private String mappingId;
	
	@ApiModelProperty(value = "true:默认收货地址;false：非默认收货地址")
    private Boolean defaultAddress;
	
	@ApiModelProperty(value = "姓名")
    private String name;
	
	@ApiModelProperty(value = "省份")
    private String province;
	
	@ApiModelProperty(value = "城市")
    private String city;
	
	@ApiModelProperty(value = "地址")
    private String address;
	
	@ApiModelProperty(value = "详细地址")
    private String fullAddress;
	
	@ApiModelProperty(value = "邮编")
    private String zipCode;
	
	@ApiModelProperty(value = "电话号码")
    private String phoneNo;
	
}