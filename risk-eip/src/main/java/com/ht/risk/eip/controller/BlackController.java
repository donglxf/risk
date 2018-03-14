package com.ht.risk.eip.controller;

import com.ht.risk.api.feign.eip.BlackRpc;
import com.ht.risk.api.model.eip.*;
import com.ht.ussp.core.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 * demo
 * </p>
 * @author 张鹏
 * @since 2017-12-15
 */
@RestController
@RequestMapping("/black")
@Api(tags = "BlackController", description = "黑名单接口", hidden = true)
public class BlackController {
    @Autowired
    private BlackRpc tcRpc;

    @PostMapping("/netLoan")
    @ApiOperation(value = "网贷黑名单",httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<NetLoanOut> netLoan(@RequestBody NetLoanIn input) throws Exception{
        Result<NetLoanOut> result =  tcRpc.netLoan(input);
        return result;
    }

    @PostMapping("/oldLai")
    @ApiOperation(value = "老赖黑名单",httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<OldLaiOut> oldLai(@RequestBody OldLaiIn input) throws Exception{
        Result<OldLaiOut> result =  tcRpc.oldLai(input);
        return result;
    }
    @PostMapping("/self")
    @ApiOperation(value = "自有黑名单",httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<SelfDtoOut> self(@RequestBody OldLaiIn input) throws Exception{
        Result<SelfDtoOut> result =  tcRpc.self(input);
        return result;
    }
    @PostMapping("/frontSea")
    @ApiOperation(value = "前海黑名单",httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<FrontSeaDtoOut> frontSea(FrontSeaDtoIn input) throws Exception{
        Result<FrontSeaDtoOut> result =  tcRpc.frontSea(input);
        return result;
    }
    @PostMapping("/baiqishi")
    @ApiOperation(value = "白骑士黑名单",httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<BaiqishiDtoOut> baiqishi(@RequestBody NetLoanIn input) throws Exception{
        Result<BaiqishiDtoOut> result =  tcRpc.baiqishi(input);
        return result;
    }


    @PostMapping("/mobileValid")
    @ApiOperation(value = "手机号验证",httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<MobileValidDtoOut> oldLai(@RequestBody MobileValidDtoIn input) throws Exception{
        Result<MobileValidDtoOut> result =  tcRpc.mobileValid(input);
        return result;
    }

   @PostMapping("/klRiskBlackList")
    @ApiOperation(value = "考拉黑名单",httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<KlRiskBlackListRespDto> bairong(@RequestBody KlRiskBlackListReqDto input) throws Exception{
        Result<KlRiskBlackListRespDto> result =  tcRpc.klRiskBlack(input);
        return result;
    }





}

