package com.ht.risk.api.model.eip;

import com.ht.risk.api.comment.commEntryIn;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel
public class LawxpFullTextDtoIn extends commEntryIn {

	@ApiModelProperty(value = "支持多个关键字同时查询，使用空格分开")
	private String keyWord;

}