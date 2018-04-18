package com.ht.risk.activiti.rpc;

import com.ht.risk.api.model.eip.*;
import com.ht.risk.api.model.eip.wanda.WDEnterpriseDetailReqDto;
import com.ht.risk.common.result.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("risk-eip")
public interface EipServiceInterface {

	@RequestMapping("/zhengxin/wanda")
	public com.ht.ussp.core.Result getZhengxinWanda(WDEnterpriseDetailReqDto paramter);

	/**
	 * 天行数科
	 * @param paramter
	 * @return
	 */
	@RequestMapping("/news/negativeSearch")
	public com.ht.ussp.core.Result<NegativeSearchDtoOut> getNegativeSearch(NegativeSearchDtoIn paramter);

	@PostMapping("/black/oldLai")
	public com.ht.ussp.core.Result<OldLaiOut> oldLai(OldLaiIn input) throws Exception;

	@PostMapping("/black/netLoan")
	public com.ht.ussp.core.Result<NetLoanOut> netLoan(NetLoanIn input);

	@PostMapping("/black/self")
	public com.ht.ussp.core.Result<SelfDtoOut> self(OldLaiIn input);


	@PostMapping("/klRiskBlackList")
	public com.ht.ussp.core.Result<KlRiskBlackListRespDto> kl(KlRiskBlackListReqDto input);


	/**
	 * 汇法个人信息统计
	 * @param input
	 * @return
	 */
	@PostMapping("/lawxp/personClassify")
	public com.ht.ussp.core.Result<LawxpPersonClassifyDtoOut> personClassify(LawxpPersonClassifyDtoIn input);

}
