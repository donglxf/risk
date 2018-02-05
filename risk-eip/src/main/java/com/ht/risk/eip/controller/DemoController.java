package com.ht.risk.eip.controller;

import com.ht.risk.api.feign.eip.DemoRpc;
import com.ht.risk.api.model.eip.QueryUserInformationAuthDtoIn;
import com.ht.risk.api.model.eip.QueryUserInformationAuthDtoOut;
import com.ht.ussp.core.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping("/queryUserInformationAuth")
    @ApiOperation(value = "demo测试",httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<QueryUserInformationAuthDtoOut> queryUserInformationAuth(QueryUserInformationAuthDtoIn input) throws Exception{
        Result<QueryUserInformationAuthDtoOut> result =  demoRpc.queryUserInformationAuth(input);
      return result;
    }


   /* @GetMapping("/eip/tc/blackList/getLlBlackList")
    @ApiOperation(value = "demo测试")
    public Object getLlBlackList(User user) throws Exception{
        LlBlackListRespDto  result = demoRpc.getLlBlackList(user);
        System.out.println(result);
        return null;
    }*/


}

