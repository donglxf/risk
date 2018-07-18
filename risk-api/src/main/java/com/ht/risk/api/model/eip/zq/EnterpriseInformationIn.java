package com.ht.risk.api.model.eip.zq;

import com.ht.risk.api.comment.commEntryIn;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class EnterpriseInformationIn extends commEntryIn {

	@ApiModelProperty(value = "企业信用代码")
	private String creditNo;

	@ApiModelProperty(value = "企业名称")
	private String companyName;

}