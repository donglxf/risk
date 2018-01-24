package com.ht.risk.activiti.rpc;

import com.ht.risk.api.model.drools.DroolsParamter;
import com.ht.risk.api.model.drools.RuleExcuteResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("risk-drools")
public interface DroolsRuleEngineInterface {

	@GetMapping("/excuteDroolsScene")
	RuleExcuteResult excuteDroolsScene(DroolsParamter paramter);

	@RequestMapping("/excuteDroolsSceneValidation")
	public RuleExcuteResult excuteDroolsSceneValidation(DroolsParamter paramter);

}
