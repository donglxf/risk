package com.ht.risk.api.feign.eip;

import com.ht.risk.api.comment.FeignConfig;
import com.ht.risk.api.model.eip.TaobaoInfoDtoIn;
import com.ht.risk.api.model.eip.TaobaoInfoDtoOut;
import com.ht.risk.api.model.eip.TaobaoJudicialAuctionReqDto;
import com.ht.risk.api.model.eip.TaobaoJudicialAuctionRespDto;
import com.ht.ussp.core.Result;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "eip-out",path = "/eip",url = "http://172.16.200.110:30406/eip/taobao/getAll")
public interface MoxieRpc {

    /**
     * 描述：魔蝎淘宝信息
     */
    @PostMapping(value = "/taobao/getAll",headers = { "app=FK", "content-type=application/json" }, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<TaobaoInfoDtoOut> taobaoGetAll(@RequestBody TaobaoInfoDtoIn input);

}
