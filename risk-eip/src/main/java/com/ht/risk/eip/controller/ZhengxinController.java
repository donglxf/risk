package com.ht.risk.eip.controller;


import com.alibaba.fastjson.JSON;
import com.ht.risk.api.feign.eip.ZhengxinRpc;
import com.ht.risk.api.model.eip.wanda.WDEnterpriseDetailReqDto;
import com.ht.risk.api.model.eip.wanda.WDEnterpriseDetailRespDtoOut;
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
 * @Date 2018/2/27 15:02
 */
@RestController
@RequestMapping("/zhengxin")
@Api(tags = "ZhengxinController", description = "征信相关", hidden = true)
public class ZhengxinController {

    @Autowired
    ZhengxinRpc zhengxinRpc;

    @Autowired
    private MongoTemplate mongoTemplate;

    @PostMapping("/wanda")
    @ApiOperation(value = "万达征信", httpMethod = "POST")
    @ResponseBody
    public Result<WDEnterpriseDetailRespDtoOut> wanda(@RequestBody WDEnterpriseDetailReqDto input) throws Exception {
        System.out.println(">>>>>>>>>");
        long startTime = System.currentTimeMillis();
        Result<WDEnterpriseDetailRespDtoOut> result = zhengxinRpc.collectionMin(input);
        System.out.println("<<<<<<<<<<<"+JSON.toJSONString(result));
        LogEntity logEntity = new LogEntity(input.getApp(),"wanda","1",input,result,new Date(),System.currentTimeMillis()-startTime);
        mongoTemplate.insert(logEntity);
        return result;
    }


}
