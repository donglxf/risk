package com.ht.risk.api.model.eip;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel
public class BaiqishiStrategyDtoOut{

	@ApiModelProperty(value = "策略名称")
	private String strategyName;

	@ApiModelProperty(value = "策略 ID")
	private String strategyId;

	@ApiModelProperty(value = "策略决策结果，请参考决策结果码")
	private String strategyDecision;

	@ApiModelProperty(value = "策略匹配模式")
	private String strategyMode;

	@ApiModelProperty(value = "策略风险系数，只有权重策略模式下有效")
	private String strategyScore;

	@ApiModelProperty(value = "策略风险类型")
	private String riskType;

	@ApiModelProperty(value = "策略击中话术提示")
	private String tips;

	@ApiModelProperty(value = "规则内容明细，参考 rule 字段说明")
	private List<BaiqishiHitRulespDtoOut> hitRules;

}