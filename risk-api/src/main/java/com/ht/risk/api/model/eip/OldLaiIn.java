package com.ht.risk.api.model.eip;

import com.ht.risk.api.comment.commEntryIn;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class OldLaiIn extends commEntryIn {

	@ApiModelProperty(value = "证件证号",required = true,example = "")
	private String identityCard;

	@ApiModelProperty(value = "真实姓名")
	private String realName;

	@ApiModelProperty(value = "查询类型")
	private String queryType;
}