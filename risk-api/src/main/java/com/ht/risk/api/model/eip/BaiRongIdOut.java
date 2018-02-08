package com.ht.risk.api.model.eip;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel
public class BaiRongIdOut implements Serializable {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "不良")
	private String bankBad;

	@ApiModelProperty(value = "短时逾期")
	private String bankOverdue;

	@ApiModelProperty(value = "欺诈")
	private String bankFraud;

	@ApiModelProperty(value = "失联")
	private String bankLost;

	@ApiModelProperty(value = "拒绝")
	private String bankRefuse;

	@ApiModelProperty(value = "")
	private String creditBad;

	@ApiModelProperty(value = "")
	private String p2pBad;

	@ApiModelProperty(value = "")
	private String p2pOverdue;

	@ApiModelProperty(value = "")
	private String p2pFraud;

	@ApiModelProperty(value = "")
	private String p2pLost;

	@ApiModelProperty(value = "")
	private String p2pRefuse;

	@ApiModelProperty(value = "")
	private String phoneOverdue;

	@ApiModelProperty(value = "")
	private String insuranceFraud;

	@ApiModelProperty(value = "")
	private String courtBad;

}