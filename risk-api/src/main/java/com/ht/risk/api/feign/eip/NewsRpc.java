package com.ht.risk.api.feign.eip;

import com.ht.risk.api.model.eip.NegativeSearchDtoIn;
import com.ht.risk.api.model.eip.NegativeSearchDtoOut;
import com.ht.ussp.core.Result;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *负面消息 的接口
 */
@PropertySource("classpath:config.properties")
@FeignClient(value = "eip-out",path = "/eip/tc"+"/news")
public interface NewsRpc {
    /**
     * 描述：负面消息查询
     * @param input
     * @return a
     * @autor 黄增猛
     * @date 2018/2/5 9:38
     */
    @PostMapping(value = "/negativeSearch", produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<NegativeSearchDtoOut> negativeSearch(@RequestBody NegativeSearchDtoIn input);



}
