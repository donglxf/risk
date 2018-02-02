package com.ht.risk.eip.controller;

import com.ht.risk.eip.dto.QueryUserInformationAuthDtoIn;
import com.ht.risk.eip.dto.QueryUserInformationAuthDtoOut;
import com.ht.risk.eip.rpc.DemoRpc;
import com.ht.ussp.core.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 * demo
 * </p>
 *
 * @author 张鹏
 * @since 2017-12-15
 */
@RestController
@RequestMapping("/demo")
@Api(tags = "DemoCon", description = "demo", hidden = true)
public class DemoController {
    @Autowired
    private DemoRpc demoRpc;


    @GetMapping("/queryUserInformationAuth")
    @ApiOperation(value = "demo测试")
    public Result<QueryUserInformationAuthDtoOut> queryUserInformationAuth(QueryUserInformationAuthDtoIn input) throws Exception{
       // Result<QueryUserInformationAuthDtoOut> result =  demoRpc.queryUserInformationAuth(input);
        Object  result = demoRpc.queryUserInformationAuth2(input);
        System.out.println(result);
      return null;
    }




}

