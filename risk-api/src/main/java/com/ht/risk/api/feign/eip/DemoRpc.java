package com.ht.risk.api.feign.eip;

import com.ht.risk.api.model.eip.QueryUserInformationAuthDtoIn;
import com.ht.risk.api.model.eip.QueryUserInformationAuthDtoOut;
import com.ht.ussp.core.Result;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient("eip-out")
public interface DemoRpc {
    //@Headers({"Content-Type: application/json","app:FK"})
    @ResponseBody
    @PostMapping(value = "/eip/td/account/queryUserInformationAuth", produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<QueryUserInformationAuthDtoOut> queryUserInformationAuth(@RequestBody QueryUserInformationAuthDtoIn input);

    @RequestMapping("/eip/td/account/queryUserInformationAuth")
    Object queryUserInformationAuth2(QueryUserInformationAuthDtoIn input);

}
