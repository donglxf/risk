package com.ht.risk.api.feign.eip;

import com.ht.risk.api.model.eip.*;
import com.ht.ussp.core.Result;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *黑名单 的接口
 */
@PropertySource("classpath:config.properties")
@FeignClient(value = "eip-out",path = "/eip/tc"+"/black")
//@RequestMapping(headers = { "app=FK", "content-type=application/json" }, produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
public interface BlackRpc {
    /**
     * 描述：网贷黑名单
     * @param input
     * @return a
     * @autor 张鹏
     * @date 2018/2/5 9:38
     */
    @PostMapping(value = "/netLoan",headers = { "app=FK", "content-type=application/json" }, produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<NetLoanOut> netLoan(@RequestBody NetLoanIn input);

    /**
     * 描述：老赖黑名单
     * @param input
     * @return a
     * @autor 张鹏
     * @date 2018/2/5 9:38
     */
    @PostMapping(value = "/oldLai",headers = { "app=FK", "content-type=application/json" }, produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<OldLaiOut> oldLai(@RequestBody OldLaiIn input);

    /**
     * 描述：自有黑名单
     * @param input
     * @return a
     * @autor 张鹏
     * @date 2018/2/5 9:38
     */
    @PostMapping(value = "/self",headers = { "app=FK", "content-type=application/json" }, produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<SelfDtoOut> self(@RequestBody OldLaiIn input);

    /**
     * 描述：自有黑名单
     * @param input
     * @return a
     * @autor 张鹏
     * @date 2018/2/5 9:38
     */
    @PostMapping(value = "/frontSea",headers = { "app=FK", "content-type=application/json" }, produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<FrontSeaDtoOut> frontSea(@RequestBody FrontSeaDtoIn input);


    /**
     * 描述：手机号验证
     * @param input
     * @return a
     * @autor 黄增猛
     * @date 2018/2/5 9:38
     */
    @PostMapping(value = "/mobileValid",headers = { "app=FK", "content-type=application/json" }, produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<MobileValidDtoOut> mobileValid(@RequestBody MobileValidDtoIn input);


    /**
     * 描述：白骑士黑名单
     * @param input
     * @return a
     * @autor 张鹏
     * @date 2018/2/5 9:38
     */
    @PostMapping(value = "/baiqishi", headers = { "app=FK", "content-type=application/json" },produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<BaiqishiDtoOut> baiqishi(@RequestBody NetLoanIn input);


    /**
     * 描述：7.2.6	百融反欺诈特殊黑名单
     * @param input
     * @return a
     * @autor 张鹏
     * @date 2018/2/5 9:38
     */
    @PostMapping(value = "/bairong", headers = { "app=FK", "content-type=application/json" }, produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<BairongDtoOut> bairong(@RequestBody BairongDtoIn input);

    /**
     * 描述：7.2.6	考拉特殊黑名单
     * @param input
     * @return a
     * @autor 张鹏
     * @date 2018/2/5 9:38
     */
    @PostMapping(value = "/klRiskBlackList", headers = { "app=FK", "content-type=application/json" }, produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<KlRiskBlackListRespDto> klRiskBlack(@RequestBody KlRiskBlackListReqDto input);




}
