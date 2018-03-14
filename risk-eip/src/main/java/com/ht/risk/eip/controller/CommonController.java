package com.ht.risk.eip.controller;

import com.ht.risk.api.feign.eip.CommonRpc;
import com.ht.risk.api.model.eip.MessageDtoIn;
import com.ht.ussp.core.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 * demo
 * </p>
 * @author 张鹏
 * @since 2017-12-15
 */
@RestController
@RequestMapping("/common")
@Api(tags = "CommonController", description = "通用服务接口", hidden = true)
public class CommonController {
    @Autowired
    private CommonRpc commonRpc;

//    @Autowired
//    private DemoRpc demoRpc;

    @PostMapping("/sendSms")
    @ApiOperation(value = "短信发送",httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result negativeSearch(@RequestBody MessageDtoIn input) throws Exception{
        Result result =  commonRpc.sendSms(input);
        return result;
    }
//    @PostMapping("/queryUserInformationAuth")
//    @ApiOperation(value = "demo测试",httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
//    public Result<QueryUserInformationAuthDtoOut> queryUserInformationAuth(QueryUserInformationAuthDtoIn input) throws Exception{
//        Result<QueryUserInformationAuthDtoOut> result =  demoRpc.queryUserInformationAuth(input);
//        return result;
//    }

}

