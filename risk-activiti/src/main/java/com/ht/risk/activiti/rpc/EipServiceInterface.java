package com.ht.risk.activiti.rpc;

import com.ht.risk.api.model.drools.DroolsParamter;
import com.ht.risk.api.model.eip.NegativeSearchDtoIn;
import com.ht.risk.api.model.eip.wanda.WDEnterpriseDetailReqDto;
import com.ht.risk.common.result.Result;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("risk-eip")
public interface EipServiceInterface {

	@RequestMapping("/zhengxin/wanda")
	public Result getZhengxinWanda(WDEnterpriseDetailReqDto paramter);

	@RequestMapping("/news/negativeSearch")
	public Result getNegativeSearch(NegativeSearchDtoIn paramter);

}
