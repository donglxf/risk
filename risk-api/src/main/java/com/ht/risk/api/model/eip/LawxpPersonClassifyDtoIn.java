package com.ht.risk.api.model.eip;

import com.ht.risk.api.comment.commEntryIn;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel
public class LawxpPersonClassifyDtoIn extends commEntryIn {

	@ApiModelProperty(value = "证件证号")
	private String identityCard;

	@ApiModelProperty(value = "真实姓名")
	private String realName;

}