package com.ht.risk.api.model.eip;

import com.ht.risk.api.comment.commEntryIn;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel
public class TaobaoInfoDtoIn extends commEntryIn {

	@ApiModelProperty(value = "魔蝎平台上产生的taskId")
	private String taskId;
	
}