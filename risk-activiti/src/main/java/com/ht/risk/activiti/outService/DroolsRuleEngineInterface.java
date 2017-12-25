package com.ht.risk.activiti.outService;

import com.ht.risk.activiti.model.DroolsParamter;
import com.ht.risk.common.result.Result;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("risk-drools")
public interface DroolsRuleEngineInterface {

	@GetMapping("/excuteDroolsScene")
	Result excuteDroolsScene(DroolsParamter paramter);

}
