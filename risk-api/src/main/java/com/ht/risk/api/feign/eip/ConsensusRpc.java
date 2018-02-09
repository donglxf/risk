package com.ht.risk.api.feign.eip;

import com.ht.risk.api.model.eip.*;
import com.ht.ussp.core.Result;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "${eip.feign.service}", path = "${eip.feign.path}", url = "${eip.feign.url}")
public interface ConsensusRpc {

    /**
     * 描述：电话邦催收分
     */
    @PostMapping(value = "/consensus",headers = { "app=FK", "content-type=application/json" }, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<ConsensusRespDto> consensus(@RequestBody ConsensusReqDto input);

}
