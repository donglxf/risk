package com.ht.risk.eip.controller;

import com.ht.risk.api.feign.eip.BairongRpc;
import com.ht.risk.api.model.eip.BairongMoreCheckDtoIn;
import com.ht.risk.api.model.eip.bairong.BairongMoreCheckDtoOut;
import com.ht.risk.eip.logs.LogEntity;
import com.ht.ussp.core.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @Author Huang.zengmeng
 * @Description
 * @Date 2018/2/7 15:02
 */
@RestController
@RequestMapping("/bairong")
@Api(tags = "Bairong", description = "百融", hidden = true)
public class BaiRongController {

    @Autowired
    private BairongRpc bairongRpc;

    @Autowired
    private MongoTemplate mongoTemplate;

    @PostMapping("/moreCheck")
    @ApiOperation(value = "百融多次申请核查V2", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<BairongMoreCheckDtoOut> moreCheck(@RequestBody BairongMoreCheckDtoIn input) {
        long startTime = System.currentTimeMillis();
        Result<BairongMoreCheckDtoOut> result = bairongRpc.moreCheck(input);
        LogEntity logEntity = new LogEntity(input.getApp(),"moreCheck","1",input,result,new Date(),System.currentTimeMillis()-startTime);
        mongoTemplate.insert(logEntity);
        return result;
    }

}
