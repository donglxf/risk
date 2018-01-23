package com.ht.risk.rule.rpc;

import com.ht.risk.api.model.activiti.ModelParamter;
import com.ht.risk.api.model.activiti.RpcActExcuteTaskInfo;
import com.ht.risk.api.model.activiti.RpcDeployResult;
import com.ht.risk.api.model.activiti.RpcModelReleaseInfo;
import com.ht.risk.common.result.Result;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient("activiti-config")
public interface ActivitiConfigRpc {

    @RequestMapping("/deploy")
    public Result<RpcDeployResult> deploy(@RequestBody ModelParamter paramter);

    @RequestMapping("/queryModelRelease")
    public Result<RpcModelReleaseInfo> queryModelProcInstIdByBatchId(String procInstId);


    @RequestMapping("/queryProcInstId")
    public Result<List<String>> queryProcInstIdByBatchId(Long procInstId);

    @RequestMapping("/getTaskInfoById")
    public Result<RpcActExcuteTaskInfo> getTaskInfoById(Long TaskId);

    @RequestMapping("/getProcReleaseById")
    public Result<RpcModelReleaseInfo> getProcReleaseById(Long TaskId);


}
