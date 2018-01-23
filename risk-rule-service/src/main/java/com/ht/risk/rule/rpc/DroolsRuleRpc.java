package com.ht.risk.rule.rpc;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ht.risk.rule.model.DroolsParamter;

import java.util.List;

@FeignClient("risk-drools")
public interface DroolsRuleRpc {

    @RequestMapping("/getDroolsVersion/{dev}")
    String getDroolsVersion(@PathVariable(name="dev") String dev);
    
    @RequestMapping("/excuteDroolsSceneValidation")
    String excuteDroolsSceneValidation(DroolsParamter paramter);

    @RequestMapping("/batchExcuteRuleValidation")
    String batchExcuteRuleValidation(List<DroolsParamter> paramter);

    
    
}