package com.ht.risk.rule.rpc;

import com.ht.risk.api.model.log.RpcHitRuleInfo;
import com.ht.risk.common.result.Result;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient("risk-log")
public interface DroolsLogRpc {

    @RequestMapping("/droolsLog/getHitRuleInfo")
    public Result<List<RpcHitRuleInfo>> getHitRuleInfo(@RequestBody String procInstId);


    @RequestMapping("/droolsLog/countHitRuleInfo")
    public Result<List<RpcHitRuleInfo>> countHitRuleInfo(@RequestBody List<String> procInstId);



}
