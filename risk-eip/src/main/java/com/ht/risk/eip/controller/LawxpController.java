package com.ht.risk.eip.controller;

import com.ht.risk.api.feign.eip.LawxpRpc;
import com.ht.risk.api.model.eip.*;
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
@RequestMapping("/lawxp")
@Api(tags = "Lawxp", description = "汇法网", hidden = true)
public class LawxpController {

    @Autowired
    private LawxpRpc lawxpRpc;

    @Autowired
    private MongoTemplate mongoTemplate;

    @PostMapping("/personClassify")
    @ApiOperation(value = "个人分类统计查询", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<LawxpPersonClassifyDtoOut> personClassify(@RequestBody LawxpPersonClassifyDtoIn input) {
        long startTime = System.currentTimeMillis();
        Result<LawxpPersonClassifyDtoOut> result = lawxpRpc.personClassify(input);
        LogEntity logEntity = new LogEntity(input.getApp(),"personClassify","1",input,result,new Date(),System.currentTimeMillis()-startTime);
        mongoTemplate.insert(logEntity);
        return result;
    }

    @PostMapping("/webank")
    @ApiOperation(value = "微众(法院信息)", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<LawxpWebankDtoOut> webank(@RequestBody LawxpWebankDtoIn input) {
        long startTime = System.currentTimeMillis();
        Result<LawxpWebankDtoOut> result = lawxpRpc.webank(input);
        LogEntity logEntity = new LogEntity(input.getApp(),"webank","1",input,result,new Date(),System.currentTimeMillis()-startTime);
        mongoTemplate.insert(logEntity);
        return result;
    }

    @PostMapping("/fullText")
    @ApiOperation(value = "全文检索", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<LawxpFullTextDtoOut> fullText(@RequestBody LawxpFullTextDtoIn input) {
        long startTime = System.currentTimeMillis();
        Result<LawxpFullTextDtoOut> result = lawxpRpc.fullText(input);
        LogEntity logEntity = new LogEntity(input.getApp(),"fullText","1",input,result,new Date(),System.currentTimeMillis()-startTime);
        mongoTemplate.insert(logEntity);
        return result;
    }

}
