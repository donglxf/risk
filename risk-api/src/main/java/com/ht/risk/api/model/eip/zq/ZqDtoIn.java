package com.ht.risk.api.model.eip.zq;

import com.ht.risk.api.comment.commEntryIn;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class ZqDtoIn extends commEntryIn {

	@ApiModelProperty(value = "证件证号",required = true,example = "")
	private String identityCard;

	@ApiModelProperty(value = "真实姓名",required = true)
	private String realName;

}