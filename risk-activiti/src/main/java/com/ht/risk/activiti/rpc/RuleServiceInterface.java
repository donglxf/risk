package com.ht.risk.activiti.rpc;

import com.ht.risk.api.model.drools.DroolsParamter;
import com.ht.risk.api.model.rule.RpcRuleHisVersion;
import com.ht.risk.api.model.rule.RpcRuleHisVersionParamter;
import com.ht.risk.common.result.Result;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient("rule-service")
public interface RuleServiceInterface {

	@RequestMapping("/verification/getAutoVerficationData")
	public Result getAutoVerficationData(DroolsParamter paramter);

	@PostMapping("/sceneVersion/getHisVersionListByVidName")
	public Result<List<RpcRuleHisVersion>> getHisVersionListByVidName(@RequestBody RpcRuleHisVersionParamter paramter);

	@GetMapping("/cityType/getCityTypeByCityName")
	public Result<String> getCityTypeByCityName(@RequestParam(value = "cityName") String cityName);

}
