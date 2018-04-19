package com.ht.risk.eip.controller;

import com.ht.risk.api.feign.eip.CreditLimitRpc;
import com.ht.risk.api.model.eip.QuotaApplyDtoIn;
import com.ht.risk.api.model.eip.QuotaApplyDtoOut;
import com.ht.risk.eip.logs.LogEntity;
import com.ht.ussp.core.Result;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;

//不需要
//@RestController
//@RequestMapping("/quota")
//@Api(tags = "Quota", description = "额度操作", hidden = true)
public class QuotaController {

    @Autowired
    private CreditLimitRpc creditLimitRpc;

    @Autowired
    private MongoTemplate mongoTemplate;

    @PostMapping("/generate")
    @ApiOperation(value = "授信额度生成",httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<QuotaApplyDtoOut> generateQuota(@RequestBody QuotaApplyDtoIn quotaApplyDtoIn){
    	System.out.println("generate:"+quotaApplyDtoIn.getProductCode());
        long startTime = System.currentTimeMillis();
        Result<QuotaApplyDtoOut> result =  creditLimitRpc.callQuotaApply(quotaApplyDtoIn);
        LogEntity logEntity = new LogEntity(quotaApplyDtoIn.getApp(),"personClassify","1",quotaApplyDtoIn,result,new Date(),System.currentTimeMillis()-startTime);
        mongoTemplate.insert(logEntity);
      return result;
    }
    
    @PostMapping("/apply")
    @ApiOperation(value = "模拟额度系统申请额度",httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Integer> apply(@RequestBody QuotaApplyDtoIn quotaApplyDtoIn) throws Exception{
        System.out.println("apply:"+quotaApplyDtoIn.getProductCode());
    	return Result.buildSuccess(1000);
    }
	
}
