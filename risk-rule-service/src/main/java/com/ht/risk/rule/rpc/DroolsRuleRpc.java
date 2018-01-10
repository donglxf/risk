package com.ht.risk.rule.rpc;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("risk-drools")
public interface DroolsRuleRpc {

    @RequestMapping("/getDroolsVersion/{dev}")
    String getDroolsVersion(@PathVariable(name="dev") String dev);

}