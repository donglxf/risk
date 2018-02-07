package com.ht.risk.api.feign.eip;

import com.ht.risk.api.model.eip.*;
import com.ht.ussp.core.Result;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *负面消息 的接口
 */
@FeignClient(value = "${eip.feign.service}",path = "${eip.feign.path}"+"/news",url = "${eip.feign.url}")
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
