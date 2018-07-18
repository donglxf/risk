package com.ht.risk.eip.controller;

import com.alibaba.fastjson.JSONObject;
import com.ht.risk.api.feign.eip.ZqRpc;
import com.ht.risk.api.model.eip.zq.*;
import com.ht.risk.api.model.eip.NetLoanIn;
import com.ht.risk.common.util.StringUtils;
import com.ht.risk.eip.logs.LogEntity;
import com.ht.ussp.core.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

/**
 * @Author dyb
 * @Description
 * @Date 2018/7/17 17:02
 */
@RestController
@RequestMapping("/zq")
@Api(tags = "zq", description = "中青", hidden = true)
@Log4j2
public class ZqController {

    @Autowired
    private ZqRpc zqRpc;

    @PostMapping("/financialCreditDefault")
    @ApiOperation(value = "金融信贷逾期", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<FinancialCreditDefaultOut> financialCreditDefault(@RequestBody NetLoanIn input) {
        long startTime = System.currentTimeMillis();
        if (StringUtils.isEmpty(input.getIdentityCard()) || StringUtils.isEmpty(input.getRealName()) || StringUtils.isEmpty(input.getMobilePhone())) {
            return Result.buildFail("身份证，电话，真实姓名不能为空", "身份证，电话，真实姓名不能为空");
        }
        Result<FinancialCreditDefaultOut> result = zqRpc.financialCreditDefault(input);
        log.info("financialCreditDefault=========>>" + JSONObject.toJSONString(result));
        LogEntity logEntity = new LogEntity(input.getApp(), "financialCreditDefault", "1", input, result, new Date(), System.currentTimeMillis() - startTime);
        return result;
    }

    @PostMapping("/longBorrowing")
    @ApiOperation(value = "多头借贷", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Map<String, Object>> longBorrowing(@RequestBody NetLoanIn input) {
        long startTime = System.currentTimeMillis();
        if (StringUtils.isEmpty(input.getIdentityCard()) || StringUtils.isEmpty(input.getRealName()) || StringUtils.isEmpty(input.getMobilePhone())) {
            return Result.buildFail("身份证，电话，真实姓名不能为空", "身份证，电话，真实姓名不能为空");
        }
        Result<Map<String, Object>> result = zqRpc.longBorrowing(input);
        log.info("longBorrowing=========>>" + JSONObject.toJSONString(result));
        LogEntity logEntity = new LogEntity(input.getApp(), "longBorrowing", "1", input, result, new Date(), System.currentTimeMillis() - startTime);
        return result;
    }

    @PostMapping("/criminalInformation")
    @ApiOperation(value = "犯罪信息", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<CriminalInformationOut> criminalInformation(@RequestBody ZqDtoIn input) {
        long startTime = System.currentTimeMillis();
        if (StringUtils.isEmpty(input.getIdentityCard()) || StringUtils.isEmpty(input.getRealName())) {
            return Result.buildFail("身份证，真实姓名不能为空", "身份证，真实姓名不能为空");
        }
        Result<CriminalInformationOut> result = zqRpc.criminalInformation(input);
        log.info("criminalInformation=========>>" + JSONObject.toJSONString(result));
        LogEntity logEntity = new LogEntity(input.getApp(), "criminalInformation", "1", input, result, new Date(), System.currentTimeMillis() - startTime);
        return result;
    }

    @PostMapping("/courtExecutionInformation")
    @ApiOperation(value = "法院执行信息", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public CourtExecutionResult<CourtExecutionDtoOut> courtExecutionInformation(@RequestBody ZqDtoIn input) {
        long startTime = System.currentTimeMillis();
        if (StringUtils.isEmpty(input.getIdentityCard()) || StringUtils.isEmpty(input.getRealName())) {
            return CourtExecutionResult.buildFail("身份证，真实姓名不能为空", "身份证，真实姓名不能为空");
        }
        CourtExecutionResult<CourtExecutionDtoOut> result = zqRpc.courtExecutionInformation(input);
        log.info("courtExecutionInformation=========>>" + JSONObject.toJSONString(result));
        LogEntity logEntity = new LogEntity(input.getApp(), "courtExecutionInformation", "1", input, result, new Date(), System.currentTimeMillis() - startTime);
//        mongoTemplate.insert(logEntity);
        return result;
    }

    @PostMapping("/enterpriseInformation")
    @ApiOperation(value = "企业信息查询", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Map<String, Object>> enterpriseInformation(@RequestBody EnterpriseInformationIn input) {
        long startTime = System.currentTimeMillis();
        if (StringUtils.isEmpty(input.getCompanyName()) && StringUtils.isEmpty(input.getCreditNo())) {
            return Result.buildFail("企业信用代码和企业名称必填一个", "企业信用代码和企业名称必填一个");
        }
        Result<Map<String, Object>> result = zqRpc.enterpriseInformation(input);
        log.info("enterpriseInformation=========>>" + JSONObject.toJSONString(result));
        LogEntity logEntity = new LogEntity(input.getApp(), "enterpriseInformation", "1", input, result, new Date(), System.currentTimeMillis() - startTime);
        return result;
    }

}


