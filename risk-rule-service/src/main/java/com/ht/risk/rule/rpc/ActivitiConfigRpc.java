package com.ht.risk.rule.rpc;

import com.ht.risk.api.model.activiti.*;
import com.ht.risk.common.result.Result;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient("risk-activiti-extend")
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
     * @param rpcModelVerfication
     * @return
     */
    @RequestMapping("/queryTasksByBatchId")
    public Result<List<RpcActExcuteTaskInfo>> queryTasksByBatchId(RpcModelVerfication rpcModelVerfication);

    /**
     * 根据任务id，查询任务详情
     * @param rpcModelVerfication
     * @return
     */
    @RequestMapping("/getTaskInfoById")
    public Result<RpcActExcuteTaskInfo> getTaskInfoById(RpcModelVerfication rpcModelVerfication);


    /**
     * 根据模型版本ID,查询模型版本信息
     * @param rpcModelVerfication
     * @return
     */
    @RequestMapping("/getProcReleaseById")
    public Result<RpcModelReleaseInfo> getProcReleaseById(RpcModelVerfication rpcModelVerfication);

    /**
     * 启动模型，用户输入数据
     * @param rpcStartParamter
     * @return
     */
    @RequestMapping("/verficationSingle")
    public Result<String> startInputValidateProcess(@RequestBody RpcStartParamter rpcStartParamter);


}
