package com.ht.risk.rpc;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.ht.risk.model.DroolsLog;
import com.ht.risk.model.DroolsProcessLog;

@FeignClient("risk-log")
public interface DroolsLogInterface {
	
	@GetMapping("/droolsLog/save")
	public String saveLog(DroolsLog entity);
	
	@GetMapping("/droolsProcessLog/save")
	public String saveProcessLog(DroolsProcessLog entity);
}
