package com.ht.risk.api.feign.eip;

import com.ht.risk.api.model.eip.MessageDtoIn;
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
@FeignClient(value = "eip-out",path = "/eip/common")
public interface CommonRpc {
    /**
     * 描述：短信发送
     * @param input
     * @return a
     * @autor 张鹏
     * @date 2018/2/5 9:38
     */
    @PostMapping(value = "/sendSms",headers = { "app=FK", "content-type=application/json" }, produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    Result sendSms(@RequestBody MessageDtoIn input);

}
