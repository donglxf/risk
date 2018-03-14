package com.ht.risk.activiti.rpc;

import com.ht.risk.api.model.eip.NegativeSearchDtoIn;
import com.ht.risk.api.model.eip.NegativeSearchDtoOut;
import com.ht.risk.api.model.eip.OldLaiIn;
import com.ht.risk.api.model.eip.OldLaiOut;
import com.ht.risk.api.model.eip.wanda.WDEnterpriseDetailReqDto;
import com.ht.risk.common.result.Result;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("risk-eip")
public interface EipServiceInterface {

	@RequestMapping("/zhengxin/wanda")
	public Result getZhengxinWanda(WDEnterpriseDetailReqDto paramter);

	@RequestMapping("/news/negativeSearch")
	public Result<NegativeSearchDtoOut> getNegativeSearch(NegativeSearchDtoIn paramter);

	@PostMapping("/black/oldLai")
	public com.ht.ussp.core.Result<OldLaiOut> oldLai(OldLaiIn input) throws Exception;

}
