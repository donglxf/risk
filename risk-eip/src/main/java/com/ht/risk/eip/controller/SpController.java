package com.ht.risk.eip.controller;

import com.ht.risk.api.model.eip.sp.*;
import com.ht.risk.eip.logs.LogEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ht.risk.api.feign.eip.SpRpc;
import com.ht.ussp.core.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.Date;

/**
 * @Author dyb
 * @Description
 * @Date 2018/3/29 15:02
 */
@RestController
@RequestMapping("/sp")
@Api(tags = "SpController", description = "运营商授权", hidden = true)
public class SpController {

    @Autowired
    private SpRpc spRpc;

    @Autowired
    private MongoTemplate mongoTemplate;

    @PostMapping("/validCode")
    @ApiOperation(value = "校验验证码", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<SpValidCodeDtoOut> validCode(@RequestBody SpValidCodeDtoIn input) {
        long startTime = System.currentTimeMillis();
        Result<SpValidCodeDtoOut> result = spRpc.validCode(input);
        LogEntity logEntity = new LogEntity(input.getApp(),"validCode","1",input,result,new Date(),System.currentTimeMillis()-startTime);
        mongoTemplate.insert(logEntity);
        return result;
    }

    @PostMapping("/login")
    @ApiOperation(value = "登录", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<SpLoginDtoOut> login(@RequestBody SpLoginDtoIn input) {
        long startTime = System.currentTimeMillis();
        Result<SpLoginDtoOut> result = spRpc.login(input);
        LogEntity logEntity = new LogEntity(input.getApp(),"login","1",input,result,new Date(),System.currentTimeMillis()-startTime);
        mongoTemplate.insert(logEntity);
        return result;
    }

    @PostMapping("/getDetailOrderCode")
    @ApiOperation(value = "获取详单验证码", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<SpDetailOrderCodeDtoOut> getDetailOrderCode(@RequestBody SpValidCodeDtoIn input) {
        long startTime = System.currentTimeMillis();
        Result<SpDetailOrderCodeDtoOut> result = spRpc.getDetailOrderCode(input);
        LogEntity logEntity = new LogEntity(input.getApp(),"getDetailOrderCode","1",input,result,new Date(),System.currentTimeMillis()-startTime);
        mongoTemplate.insert(logEntity);
        return result;
    }

    @PostMapping("/reqDynamicCode")
    @ApiOperation(value = "请求短信动态码", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<SpDetailOrderCodeDtoOut> reqDynamicCode(@RequestBody SpDynamicCodeDtoIn input) {
        long startTime = System.currentTimeMillis();
        Result<SpDetailOrderCodeDtoOut> result = spRpc.reqDynamicCode(input);
        LogEntity logEntity = new LogEntity(input.getApp(),"reqDynamicCode","1",input,result,new Date(),System.currentTimeMillis()-startTime);
        mongoTemplate.insert(logEntity);
        return result;
    }

    @PostMapping("/sendDynamicCode")
    @ApiOperation(value = "发送短信动态码", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<SpDetailOrderCodeDtoOut> sendDynamicCode(@RequestBody SendDynamicCodeDtoIn input) {
        long startTime = System.currentTimeMillis();
        Result<SpDetailOrderCodeDtoOut> result = spRpc.sendDynamicCode(input);
        LogEntity logEntity = new LogEntity(input.getApp(),"sendDynamicCode","1",input,result,new Date(),System.currentTimeMillis()-startTime);
        mongoTemplate.insert(logEntity);
        return result;
    }

    @PostMapping("/userBaseInfo")
    @ApiOperation(value = "用户基本信息入库", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<UserBaseInfoDtoOut> userBaseInfo(@RequestBody UserBaseInfoDtoIn input) {
        long startTime = System.currentTimeMillis();
        Result<UserBaseInfoDtoOut> result = spRpc.userBaseInfo(input);
        LogEntity logEntity = new LogEntity(input.getApp(),"userBaseInfo","1",input,result,new Date(),System.currentTimeMillis()-startTime);
        mongoTemplate.insert(logEntity);
        return result;
    }
    
    @PostMapping("/queryPhoneRecord")
    @ApiOperation(value = "通话记录查询", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<QueryPhoneRecordDtoOut> queryPhoneRecord(@RequestBody QueryPhoneRecordDtoIn input) {
        long startTime = System.currentTimeMillis();
        Result<QueryPhoneRecordDtoOut> result = spRpc.queryPhoneRecord(input);
        LogEntity logEntity = new LogEntity(input.getApp(),"queryPhoneRecord","1",input,result,new Date(),System.currentTimeMillis()-startTime);
        mongoTemplate.insert(logEntity);
        return result;
    }

}
