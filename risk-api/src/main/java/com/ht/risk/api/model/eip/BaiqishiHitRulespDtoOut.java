package com.ht.risk.api.model.eip;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel
public class BaiqishiHitRulespDtoOut{

	@ApiModelProperty(value = "规则名称")
	private String ruleName;

	@ApiModelProperty(value = "规则 ID")
	private String ruleId;

	@ApiModelProperty(value = "规则风险系数，只有权重策略模式下有效")
	private int score;

	@ApiModelProperty(value = "失信名单/多头名单分拆后的明细信息")
	private List<BaiqiDetailDtoOut> detail;

	@ApiModelProperty(value = "风险名单比对规则决策结果，请参考决策结果码")
	private String decision;

	@ApiModelProperty(value = "规则模板，模板不同 detail 内字段不同请参考规则模板类型")
	private String template;

	@ApiModelProperty(value = "名单匹配规则击中分类明细")
	private String memo;

}