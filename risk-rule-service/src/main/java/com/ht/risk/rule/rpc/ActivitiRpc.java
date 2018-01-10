package com.ht.risk.rule.rpc;

import com.ht.risk.api.model.activiti.ModelParamter;
import com.ht.risk.common.result.Result;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("risk-activiti-service")
public interface ActivitiRpc {

    @RequestMapping("/modelDeploy")
    Result modelDeploy(ModelParamter paramter);
}
