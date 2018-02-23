package com.ht.risk.api.feign.eip;

import com.ht.risk.api.model.eip.ContactFastReqDto;
import com.ht.risk.api.model.eip.ContactFastRespDtoOut;
import com.ht.risk.api.model.eip.DianhuaCollectionMinDtoIn;
import com.ht.risk.api.model.eip.DianhuaCollectionMinDtoOut;
import com.ht.ussp.core.Result;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "eip-out",path = "/eip/tc"+"/dianhua",url = "http://192.168.14.230:30406")
public interface DianhuaRpc {

    /**
     * 描述：电话邦催收分
     */
    @PostMapping(value = "/collectionMin",headers = { "app=FK", "content-type=application/json" }, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<DianhuaCollectionMinDtoOut> collectionMin(@RequestBody DianhuaCollectionMinDtoIn input);

    /**
     * 描述：邦秒配接口查询
     */
    @PostMapping(value = "/contactFast",headers = { "app=FK", "content-type=application/json" },  produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<List<ContactFastRespDtoOut>> contactFast(@RequestBody ContactFastReqDto input);

}
