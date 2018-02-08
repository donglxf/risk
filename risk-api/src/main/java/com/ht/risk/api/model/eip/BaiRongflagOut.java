package com.ht.risk.api.model.eip;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel
public class BaiRongflagOut implements Serializable {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "")
	private BaiRongIdOut id;

}