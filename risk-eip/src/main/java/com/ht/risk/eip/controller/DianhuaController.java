package com.ht.risk.eip.controller;


import com.alibaba.fastjson.JSON;
import com.ht.risk.api.feign.eip.DianhuaRpc;
import com.ht.risk.api.model.eip.*;
import com.ht.risk.eip.dto.DianhuaCollectionMinDtoOutResult;
import com.ht.risk.eip.dto.NetLoanOutResult;
import com.ht.risk.eip.logs.LogEntity;
import com.ht.risk.eip.util.BlackAtiveUtil;
import com.ht.ussp.core.Result;
import com.ht.ussp.core.ReturnCodeEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @Author dyb
 * @Description
 * @Date 2018/2/9 15:02
 */
@RestController
@RequestMapping("/dianhua")
@Api(tags = "DianhuaController", description = "电话相关（邦）", hidden = true)
public class DianhuaController {

    @Autowired
    DianhuaRpc dianhuaRpc;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private BlackAtiveUtil util;


    @PostMapping("/collectionMin")
    @ApiOperation(value = "电话邦催收分", httpMethod = "POST")
    @ResponseBody
    public Result<DianhuaCollectionMinDtoOut> collectionMin(@RequestBody  DianhuaCollectionMinDtoIn input) throws Exception {
        long startTime = System.currentTimeMillis();
        Result<DianhuaCollectionMinDtoOut> result = dianhuaRpc.collectionMin(input);
        LogEntity logEntity = new LogEntity(input.getApp(),"collectionMin","1",input,result,new Date(),System.currentTimeMillis()-startTime);
        mongoTemplate.insert(logEntity);
        return result;
    }

    @PostMapping("/contactFast")
    @ApiOperation(value = "邦秒配接口查询", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Result<List<ContactFastRespDtoOut>> contactFast(@RequestBody ContactFastReqDto input) throws Exception {
        long startTime = System.currentTimeMillis();
        Result<List<ContactFastRespDtoOut>> result = dianhuaRpc.contactFast(input);
        System.out.println("<<<<<<<<<<<"+JSON.toJSONString(result));
        LogEntity logEntity = new LogEntity(input.getApp(),"contactFast","1",input,result,new Date(),System.currentTimeMillis()-startTime);
        mongoTemplate.insert(logEntity);
        return result;
    }


}
