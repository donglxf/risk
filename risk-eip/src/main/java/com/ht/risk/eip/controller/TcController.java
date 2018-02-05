package com.ht.risk.eip.controller;

import com.ht.risk.api.feign.eip.TcRpc;
import com.ht.risk.api.model.eip.*;
import com.ht.ussp.core.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 * demo
 * </p>
 *
 * @author 张鹏
 * @since 2017-12-15
 */
@RestController
@RequestMapping("/tc")
@Api(tags = "TcController", description = "天秤相关接口", hidden = true)
public class TcController {
    @Autowired
    private TcRpc tcRpc;

    @PostMapping("/black/netLoan")
    @ApiOperation(value = "网贷黑名单",httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<NetLoanOut> netLoan(NetLoanIn input) throws Exception{
        Result<NetLoanOut> result =  tcRpc.netLoan(input);
        return result;
    }

    @PostMapping("/black/oldLai")
    @ApiOperation(value = "老赖黑名单",httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<OldLaiOut> oldLai(OldLaiIn input) throws Exception{
        Result<OldLaiOut> result =  tcRpc.oldLai(input);
        return result;
    }

    @PostMapping("/black/mobileValid")
    @ApiOperation(value = "手机号验证",httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<MobileValidDtoOut> oldLai(MobileValidDtoIn input) throws Exception{
        Result<MobileValidDtoOut> result =  tcRpc.mobileValid(input);
        return result;
    }

    @PostMapping("/news/negativeSearch")
    @ApiOperation(value = "负面消息查询",httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<NegativeSearchDtoOut> negativeSearch(NegativeSearchDtoIn input) throws Exception{
        Result<NegativeSearchDtoOut> result =  tcRpc.negativeSearch(input);
        return result;
    }





}

