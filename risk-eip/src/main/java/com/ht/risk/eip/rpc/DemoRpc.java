package com.ht.risk.eip.rpc;

import com.ht.risk.eip.dto.LlBlackListRespDto;
import com.ht.risk.eip.dto.QueryUserInformationAuthDtoIn;
import com.ht.risk.eip.dto.QueryUserInformationAuthDtoOut;
import com.ht.risk.eip.dto.User;
import com.ht.ussp.core.Result;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("eip-out")
public interface DemoRpc {

    @PostMapping(value = "/eip/td/account/queryUserInformationAuth", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<QueryUserInformationAuthDtoOut> queryUserInformationAuth(QueryUserInformationAuthDtoIn input);

    @RequestMapping("/eip/td/account/queryUserInformationAuth")
    Object queryUserInformationAuth2(QueryUserInformationAuthDtoIn input);

    @RequestMapping("/eip/tc/blackList/getLlBlackList")
    LlBlackListRespDto getLlBlackList(User user);

}
