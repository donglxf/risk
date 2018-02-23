package com.ht.risk.api.model.eip;

import com.ht.risk.api.model.eip.enums.MessageEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Set;

@Data
@ApiModel
public class MessageDtoIn {

	@ApiModelProperty(value = "手机号码",required = true)
	private Set<String> phones;

	@ApiModelProperty(value = "短信内容",required = true)
	private String content;

	@ApiModelProperty(value = "验证码")
	private String vcode;

	@ApiModelProperty(value = "短信类型")
	private MessageEnum type;

}