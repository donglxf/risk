package com.ht.risk.api.feign.eip;

import com.ht.risk.api.model.eip.wanda.WDEnterpriseDetailReqDto;
import com.ht.risk.api.model.eip.wanda.WDEnterpriseDetailRespDtoOut;
import com.ht.ussp.core.Result;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "eip-out",path = "/eip/tc"+"/zhengxin",url = "http://192.168.14.230:30406")
public interface ZhengxinRpc {

    /**
     * 描述：万达征信
     */
    @PostMapping(value = "/wanda",headers = { "app=FK", "content-type=application/json" }, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<WDEnterpriseDetailRespDtoOut> collectionMin(@RequestBody WDEnterpriseDetailReqDto input);
}
