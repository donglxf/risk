package com.ht.risk.api.feign.eip;

/**
 * @Author Huang.zengmeng
 * @Description 7.2.12百融多次申请核查       V2
 * @Date 2018/2/8 13:59
 */

import com.ht.risk.api.model.eip.BairongMoreCheckDtoIn;
import com.ht.risk.api.model.eip.BairongMoreCheckDtoOut;
import com.ht.ussp.core.Result;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@PropertySource("classpath:config.properties")
@FeignClient(value = "eip-out",path = "/eip/tc"+"/bairong")
public interface BairongRpc {

    /**
     * 描述：百融多次申请核查V2
     * @param input
     * @return a
     * @autor 黄增猛
     * @date 2018/2/5 9:38
     */
    @PostMapping(value = "/moreCheck" , produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<BairongMoreCheckDtoOut> moreCheck(@RequestBody BairongMoreCheckDtoIn input);


}
