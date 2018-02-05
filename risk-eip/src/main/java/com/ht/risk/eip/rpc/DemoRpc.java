package com.ht.risk.eip.rpc;

import com.ht.risk.eip.dto.QueryBlackOldLaiDtoIn;
import com.ht.risk.eip.dto.QueryBlackOldLaiDtoOut;
import com.ht.risk.eip.dto.QueryUserInformationAuthDtoIn;
import com.ht.risk.eip.dto.QueryUserInformationAuthDtoOut;
import com.ht.ussp.core.Result;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("eip-out")
public interface DemoRpc {
    //@Headers({"Content-Type: application/json","app:FK"})
    @PostMapping(value = "/eip/td/account/queryUserInformationAuth", produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<QueryUserInformationAuthDtoOut> queryUserInformationAuth(@RequestBody QueryUserInformationAuthDtoIn input);

    @RequestMapping("/eip/td/account/queryUserInformationAuth")
    Object queryUserInformationAuth2( QueryUserInformationAuthDtoIn input);

    @PostMapping(value = "eip/tc/black/oldLai", produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<QueryBlackOldLaiDtoOut> queryBlackOldLai(@RequestBody QueryBlackOldLaiDtoIn input);
}
