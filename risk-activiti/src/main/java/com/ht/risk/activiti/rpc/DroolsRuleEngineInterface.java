package com.ht.risk.activiti.rpc;

import com.ht.risk.api.model.drools.DroolsParamter;
import com.ht.risk.api.model.drools.MulitDroolsParamter;
import com.ht.risk.api.model.drools.RuleExcuteResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient("risk-drools")
public interface DroolsRuleEngineInterface {

	@GetMapping("/excuteDroolsScene")
	RuleExcuteResult excuteDroolsScene(DroolsParamter paramter);

	@RequestMapping("/excuteDroolsSceneValidation")
	public RuleExcuteResult excuteDroolsSceneValidation(DroolsParamter paramter);

	/**
	 * 支持多组数据验证
	 * @param paramter
	 * @return
	 */
	@RequestMapping("/mulitExcuteDroolsScene")
	public RuleExcuteResult mulitExcuteDroolsSceneValidation(MulitDroolsParamter paramter);

	@RequestMapping("/batchExcuteRuleValidation")
	public List<RuleExcuteResult> batchExcuteRuleValidation(List<DroolsParamter> paramters);

}
