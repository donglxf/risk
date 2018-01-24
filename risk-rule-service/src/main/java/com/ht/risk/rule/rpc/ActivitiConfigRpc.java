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

    /**
     * 流程部署
     * @param paramter
     * @return
     */
    @RequestMapping("/deploy")
    public Result<RpcDeployResult> deploy(@RequestBody ModelParamter paramter);

    /**
     * 查询该批次的所有流程实例
     * @param batchId
     * @return
     */
    @RequestMapping("/queryProcInstId")
    public Result<List<RpcActExcuteTaskInfo>> queryTasksByBatchId(Long batchId);

    /**
     * 根据任务id，查询任务详情
     * @param TaskId
     * @return
     */
    @RequestMapping("/getTaskInfoById")
    public Result<RpcActExcuteTaskInfo> getTaskInfoById(Long TaskId);


    /**
     * 根据模型版本ID,查询模型版本信息
     * @param procReleaseId
     * @return
     */
    @RequestMapping("/getProcReleaseById")
    public Result<RpcModelReleaseInfo> getProcReleaseById(Long procReleaseId);


}
