package com.ht.risk.api.model.eip;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel
public class BaiRongSpecialListCDto implements Serializable{
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "")
	private String specialListC;

}