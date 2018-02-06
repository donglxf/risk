package com.ht.risk.eip.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ht.risk.api.feign.eip.CreditLimitRpc;
import com.ht.risk.api.model.eip.QuotaApplyDtoIn;
import com.ht.ussp.core.Result;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/quota")
@Api(tags = "Quota", description = "额度操作", hidden = true)
public class QuotaController {

    @Autowired
    private CreditLimitRpc creditLimitRpc;

    @PostMapping("/generate")
    @ApiOperation(value = "授信额度生成",httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Integer> generateQuota(@RequestBody QuotaApplyDtoIn quotaApplyDtoIn){
    	System.out.println("generate:"+quotaApplyDtoIn.getProductCode());
        Result<Integer> result =  creditLimitRpc.callQuotaApply(quotaApplyDtoIn);
      return result;
    }
    
    @PostMapping("/apply")
    @ApiOperation(value = "模拟额度系统申请额度",httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<Integer> apply(@RequestBody QuotaApplyDtoIn quotaApplyDtoIn) throws Exception{
        System.out.println("apply:"+quotaApplyDtoIn.getProductCode());
    	return Result.buildSuccess(1000);
    }
	
}
