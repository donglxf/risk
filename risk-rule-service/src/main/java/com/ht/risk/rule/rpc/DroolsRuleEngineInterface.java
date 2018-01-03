package com.ht.risk.rule.rpc;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("risk-drools")
public interface DroolsRuleInterface {

	@GetMapping("/getDroolsVersion/hourse ")
    String excuteDroolsScene(String paramter);

}