package com.ht.risk.rule.rpc;

import com.ht.risk.api.model.activiti.ModelParamter;
import com.ht.risk.api.model.activiti.RpcDeployResult;
import com.ht.risk.api.model.activiti.RpcModelReleaseInfo;
import com.ht.risk.api.model.log.RpcHitRuleInfo;
import com.ht.risk.common.result.Result;
import com.ht.risk.rule.vo.HitRuleVo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient("drools-log")
public interface DroolsLogRpc {

    @RequestMapping("/getHitRuleInfo")
    public Result<List<RpcHitRuleInfo>> getHitRuleInfo(@RequestBody List<String> procInstId);



}
