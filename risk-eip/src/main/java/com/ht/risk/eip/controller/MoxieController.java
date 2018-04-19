package com.ht.risk.eip.controller;


import com.alibaba.fastjson.JSON;
import com.ht.risk.api.feign.eip.MoxieRpc;
import com.ht.risk.api.feign.eip.TaobaoRpc;
import com.ht.risk.api.model.eip.TaobaoInfoDtoIn;
import com.ht.risk.api.model.eip.TaobaoInfoDtoOut;
import com.ht.risk.api.model.eip.TaobaoJudicialAuctionReqDto;
import com.ht.risk.api.model.eip.TaobaoJudicialAuctionRespDto;
import com.ht.risk.eip.logs.LogEntity;
import com.ht.ussp.core.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @Author dyb
 * @Description
 * @Date 2018/2/9 15:02
 */
@RestController
@RequestMapping("/moxie")
@Api(tags = "MoxieController", description = "魔蝎", hidden = true)
public class MoxieController {

    @Autowired
    MoxieRpc moxieRpc;

    @Autowired
    private MongoTemplate mongoTemplate;

    @PostMapping("/taobaoGetAll")
    @ApiOperation(value = "淘宝司法拍卖", httpMethod = "POST")
    @ResponseBody
    public Result<TaobaoInfoDtoOut> taobaoGetAll(@RequestBody TaobaoInfoDtoIn input) throws Exception {
        System.out.println(">>>>>>>>>");
        long startTime = System.currentTimeMillis();
        Result<TaobaoInfoDtoOut> result = moxieRpc.taobaoGetAll(input);
        System.out.println("<<<<<<<<<<<" + JSON.toJSONString(result));
        LogEntity logEntity = new LogEntity(input.getApp(), "taobaoGetAll", "1", input, result, new Date(), System.currentTimeMillis() - startTime);
        mongoTemplate.insert(logEntity);
        return result;
    }
}
