package com.ht.risk.rule.outinterface;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.ht.risk.common.result.Result;

@FeignClient("risk-drools")
public interface DroolsInterface {

	@GetMapping("/excuteDroolsScene")
	Result droolsExcute();

}
