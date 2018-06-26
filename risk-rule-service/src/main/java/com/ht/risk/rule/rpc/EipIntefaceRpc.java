package com.ht.risk.rule.rpc;

import com.ht.risk.api.model.eip.*;
import com.ht.risk.api.model.eip.bairong.BairongMoreCheckDtoOut;
import com.ht.risk.rule.vo.OldLaiInVo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient(value = "risk-eip")
public interface EipIntefaceRpc {

    /**
     * 考拉黑名单
     *
     * @param input
     * @return
     * @throws Exception
     */
    @RequestMapping("/black/noCacheKlRiskBlack")
    public com.ht.ussp.core.Result<KlRiskBlackListRespDto> noCacheKlRiskBlack(@RequestBody KlRiskBlackListReqDto input) throws Exception;

    /**
     * 网贷黑名单
     *
     * @param input
     * @return
     * @throws Exception
     */
    @RequestMapping("/black/noCacheNetLoan")
    public com.ht.ussp.core.Result<NetLoanOut> noCacheNetLoan(@RequestBody NetLoanIn input) throws Exception;

    /**
     * 自有黑名单
     *
     * @param input
     * @return
     * @throws Exception
     */
    @RequestMapping("/black/noCacheSelf")
    public com.ht.ussp.core.Result<SelfDtoOut> noCacheSelf(@RequestBody OldLaiInVo input) throws Exception;

    /**
     * 老赖黑名单
     *
     * @param input
     * @return
     * @throws Exception
     */
    @RequestMapping("/black/noCacheOldLai")
    public com.ht.ussp.core.Result<OldLaiOut> noCacheOldLai(@RequestBody OldLaiIn input) throws Exception;

    /**
     * 汇法网
     *
     * @param input
     * @return
     * @throws Exception
     */
    @RequestMapping("/lawxp/webank")
    public com.ht.ussp.core.Result<LawxpWebankDtoOut> noCacheWebank(@RequestBody LawxpWebankDtoIn input) throws Exception;

    /**
     * 前海征信黑名单
     *
     * @param input
     * @return
     * @throws Exception
     */
    @RequestMapping("/black/noCacheFrontSea")
    public com.ht.ussp.core.Result<FrontSeaDtoOut> noCacheFrontSea(@RequestBody FrontSeaDtoIn input) throws Exception;

    /**
     * 百融多次核查
     *
     * @param input
     * @return
     * @throws Exception
     */
    @RequestMapping("/bairong/moreCheck")
    public com.ht.ussp.core.Result<BairongMoreCheckDtoOut> moreCheck(@RequestBody BairongMoreCheckDtoIn input) throws Exception;


}