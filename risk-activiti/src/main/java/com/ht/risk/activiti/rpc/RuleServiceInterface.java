package com.ht.risk.activiti.rpc;

import com.ht.risk.api.model.drools.DroolsParamter;
import com.ht.risk.api.model.drools.RuleExcuteResult;
import com.ht.risk.common.result.Result;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("rule-service")
public interface RuleServiceInterface {

	@RequestMapping("/verification/getAutoVerficationData")
	public Result getAutoVerficationData(DroolsParamter paramter);

}