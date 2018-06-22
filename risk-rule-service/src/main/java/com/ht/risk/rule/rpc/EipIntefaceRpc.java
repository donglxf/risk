package com.ht.risk.rule.rpc;

import com.ht.risk.api.model.eip.KlRiskBlackListReqDto;
import com.ht.risk.api.model.eip.KlRiskBlackListRespDto;
import com.ht.risk.api.model.eip.NetLoanIn;
import com.ht.risk.api.model.eip.NetLoanOut;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(value = "risk-eip", url = "http://localhost:30526")
public interface EipIntefaceRpc {

    /**
     * 考拉黑名单
     *
     * @param input
     * @return
     * @throws Exception
     */
    @RequestMapping("/black/noCacheKlRiskBlack")
    public com.ht.risk.common.result.Result<KlRiskBlackListRespDto> noCacheKlRiskBlack(@RequestBody KlRiskBlackListReqDto input) throws Exception;

    /**
     * 网贷黑名单
     *
     * @param input
     * @return
     * @throws Exception
     */
    @RequestMapping("/black/noCacheNetLoan")
    public com.ht.risk.common.result.Result<NetLoanOut> noCacheNetLoan(@RequestBody NetLoanIn input) throws Exception;


}