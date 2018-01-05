package com.ht.risk.service;

import com.ht.risk.api.model.DroolsParamter;
import com.ht.risk.api.model.RuleExcuteResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("risk-drools")
public interface DroolsRuleEngineInterface {

	@GetMapping("/excuteDroolsScene")
	RuleExcuteResult excuteDroolsScene(DroolsParamter paramter);

}
