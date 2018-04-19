package com.ht.risk.eip.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ht.risk.api.feign.eip.SpRpc;
import com.ht.risk.api.model.eip.QueryPhoneRecordDtoIn;
import com.ht.risk.api.model.eip.QueryPhoneRecordDtoOut;
import com.ht.risk.api.model.eip.sp.SendDynamicCodeDtoIn;
import com.ht.risk.api.model.eip.sp.SpDetailOrderCodeDtoOut;
import com.ht.risk.api.model.eip.sp.SpDynamicCodeDtoIn;
import com.ht.risk.api.model.eip.sp.SpLoginDtoIn;
import com.ht.risk.api.model.eip.sp.SpLoginDtoOut;
import com.ht.risk.api.model.eip.sp.SpValidCodeDtoIn;
import com.ht.risk.api.model.eip.sp.SpValidCodeDtoOut;
import com.ht.risk.api.model.eip.sp.UserBaseInfoDtoIn;
import com.ht.risk.api.model.eip.sp.UserBaseInfoDtoOut;
import com.ht.ussp.core.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

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

    @PostMapping("/validCode")
    @ApiOperation(value = "校验验证码", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<SpValidCodeDtoOut> personClassify(@RequestBody SpValidCodeDtoIn input) {
        Result<SpValidCodeDtoOut> result = spRpc.validCode(input);
        return result;
    }

    @PostMapping("/login")
    @ApiOperation(value = "登录", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<SpLoginDtoOut> personClassify(@RequestBody SpLoginDtoIn input) {
        Result<SpLoginDtoOut> result = spRpc.login(input);
        return result;
    }

    @PostMapping("/getDetailOrderCode")
    @ApiOperation(value = "获取详单验证码", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<SpDetailOrderCodeDtoOut> getDetailOrderCode(@RequestBody SpValidCodeDtoIn input) {
        Result<SpDetailOrderCodeDtoOut> result = spRpc.getDetailOrderCode(input);
        return result;
    }

    @PostMapping("/reqDynamicCode")
    @ApiOperation(value = "请求短信动态码", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<SpDetailOrderCodeDtoOut> getDetailOrderCode(@RequestBody SpDynamicCodeDtoIn input) {
        Result<SpDetailOrderCodeDtoOut> result = spRpc.reqDynamicCode(input);
        return result;
    }

    @PostMapping("/sendDynamicCode")
    @ApiOperation(value = "发送短信动态码", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<SpDetailOrderCodeDtoOut> sendDynamicCode(@RequestBody SendDynamicCodeDtoIn input) {
        Result<SpDetailOrderCodeDtoOut> result = spRpc.sendDynamicCode(input);
        return result;
    }

    @PostMapping("/userBaseInfo")
    @ApiOperation(value = "用户基本信息入库", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<UserBaseInfoDtoOut> userBaseInfo(@RequestBody UserBaseInfoDtoIn input) {
        Result<UserBaseInfoDtoOut> result = spRpc.userBaseInfo(input);
        return result;
    }
    
    @PostMapping("/queryPhoneRecord")
    @ApiOperation(value = "通话记录查询", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<QueryPhoneRecordDtoOut> queryPhoneRecord(@RequestBody QueryPhoneRecordDtoIn input) {
        Result<QueryPhoneRecordDtoOut> result = spRpc.queryPhoneRecord(input);
        return result;
    }

}
