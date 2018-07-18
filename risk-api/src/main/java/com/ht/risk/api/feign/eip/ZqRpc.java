package com.ht.risk.api.feign.eip;

import com.ht.risk.api.model.eip.*;
import com.ht.risk.api.model.eip.zq.*;
import com.ht.ussp.core.Result;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

/**
 * 中青 的接口
 * 2018/7/17 16:46
 *
 * @autor dyb
 */
@FeignClient(value = "eip-out", path = "/eip/tc" + "/zq")
//@RequestMapping(headers = { "app=FK", "content-type=application/json" }, produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
public interface ZqRpc {
    /**
     * 描述：金融信贷逾期
     *
     * @param input
     */
    @PostMapping(value = "/financialCreditDefault"/*,headers = { "app=FK", "content-type=application/json" }*/, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<FinancialCreditDefaultOut> financialCreditDefault(@RequestBody NetLoanIn input);

    /**
     * 描述：多头借贷
     *
     * @param input
     * @return a
     */
    @PostMapping(value = "/longBorrowing"/*,headers = { "app=FK", "content-type=application/json" }*/, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<Map<String, Object>> longBorrowing(@RequestBody NetLoanIn input);

    /**
     * 描述：犯罪信息
     *
     * @param input
     * @return a
     */
    @PostMapping(value = "/criminalInformation"/*,headers = { "app=FK", "content-type=application/json" }*/, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<CriminalInformationOut> criminalInformation(@RequestBody ZqDtoIn input);

    /**
     * 描述：法院执行信息
     *
     * @param input
     * @return a
     */
    @PostMapping(value = "/courtExecutionInformation"/*,headers = { "app=FK", "content-type=application/json" }*/, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    CourtExecutionResult<CourtExecutionDtoOut> courtExecutionInformation(@RequestBody ZqDtoIn input);


    /**
     * 描述：企业信息查询
     *
     * @param input
     * @return a
     */
    @PostMapping(value = "/enterpriseInformation"/*,headers = { "app=FK", "content-type=application/json" }*/, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<Map<String, Object>> enterpriseInformation(@RequestBody EnterpriseInformationIn input);


}
