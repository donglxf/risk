package com.ht.risk.api.model.eip;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Set;

@Data
@ApiModel
public class BaiqishiDtoOut{

	@ApiModelProperty(value = "本次请求的流水号，用于案件日志跟踪")
	private String flowNo;

	@ApiModelProperty(value = "风险名单比对结果码，请参考决策结果码")
	private String finalDecision;

	@ApiModelProperty(value = "最终风险系数，只有权重策略模式下有效")
	private int finalScore;

	@ApiModelProperty(value = "策略集内容明细，参考 strategySet 字段说明")
	private Set<BaiqishiStrategyDtoOut> strategySet;

}