package com.ht.risk.api.feign.eip;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ht.risk.api.model.eip.QuotaApplyDtoIn;
import com.ht.risk.api.model.eip.QuotaApplyDtoOut;
import com.ht.ussp.core.Result;

@FeignClient("CLMS-LIMIT-SERVICE")
public interface CreditLimitRpc {
    @GetMapping(value = "/quota/apply", produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    Result<QuotaApplyDtoOut> callQuotaApply(@RequestBody QuotaApplyDtoIn quotaApplyDtoIn);
}
