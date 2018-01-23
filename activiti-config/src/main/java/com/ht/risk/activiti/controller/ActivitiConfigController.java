package com.ht.risk.activiti.controller;

import com.alibaba.fastjson.JSON;
import com.ht.risk.activiti.entity.ActExcuteTask;
import com.ht.risk.activiti.entity.ActProcRelease;
import com.ht.risk.activiti.service.ActExcuteTaskService;
import com.ht.risk.activiti.service.ActProcReleaseService;
import com.ht.risk.activiti.vo.VerficationModelVo;
import com.ht.risk.api.model.activiti.RpcActExcuteTaskInfo;
import com.ht.risk.api.model.activiti.RpcModelReleaseInfo;
import com.ht.risk.common.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("")
public class ActivitiConfigController {

    protected static final Logger LOGGER = LoggerFactory.getLogger(ActivitiConfigController.class);

    @Resource
    private ActExcuteTaskService actExcuteTaskService;
    @Resource
    private ActProcReleaseService actProcReleaseService;

    @RequestMapping("/queryProcInstId")
    public Result<List<RpcActExcuteTaskInfo>> queryProcInstId(@RequestBody VerficationModelVo verficationModelVo){
        LOGGER.info("queryModelProcInstIdByBatchId mothod invoke,paramter:"+ JSON.toJSONString(verficationModelVo));
        Result<List<RpcActExcuteTaskInfo>> result = null;
        if(verficationModelVo == null || verficationModelVo.getBatchId() == null){
            LOGGER.error("queryModelProcInstIdByBatchId mothod invoke,paramter null exception");
            result =Result.error(1,"参数异常！");
            return  result;
        }
        try {
            List<RpcActExcuteTaskInfo> tasks = actExcuteTaskService.queryProcInstIdBybatchId(verficationModelVo.getBatchId());
            result = Result.success(tasks);
        }catch (Exception e){
            result = Result.error(2,"queryModelProcInstIdByBatchId mothod excute exception,"+e);
            return  result;
        }
        LOGGER.info("resultPage mothod invoke,reustl:"+ JSON.toJSONString(result));
        return result;

    }


    @RequestMapping("/getTaskInfoById")
    public Result<RpcActExcuteTaskInfo> getTaskInfoById(Long TaskId){
        LOGGER.info("queryModelProcInstIdByBatchId mothod invoke,paramter:"+ TaskId);
        Result<RpcActExcuteTaskInfo> result = null;
        if(TaskId == null){
            LOGGER.error("queryModelProcInstIdByBatchId mothod invoke,paramter null exception");
            result =Result.error(1,"参数异常！");
            return  result;
        }
        try {
            ActExcuteTask task = actExcuteTaskService.selectById(TaskId);
            RpcActExcuteTaskInfo rpcTask = actExcuteTaskService.convertRpcActExcuteTask(task);
            result = Result.success(rpcTask);
        }catch (Exception e){
            result = Result.error(2,"getTaskInfoById mothod excute exception,"+e);
            return  result;
        }
        LOGGER.info("resultPage mothod invoke,reustl:"+ JSON.toJSONString(result));
        return result;

    }

    @RequestMapping("/getProcReleaseById")
    public Result<RpcModelReleaseInfo> getProcReleaseById(Long procReleaseId){
        LOGGER.info("queryModelProcInstIdByBatchId mothod invoke,paramter:"+ procReleaseId);
        Result<RpcModelReleaseInfo> result = null;
        if(procReleaseId == null){
            LOGGER.error("queryModelProcInstIdByBatchId mothod invoke,paramter null exception");
            result =Result.error(1,"参数异常！");
            return  result;
        }
        try {
            ActProcRelease release = actProcReleaseService.selectById(procReleaseId);
            RpcModelReleaseInfo rpcRelease = actProcReleaseService.convertRpcActExcuteTask(release);
            result = Result.success(rpcRelease);
        }catch (Exception e){
            result = Result.error(2,"getTaskInfoById mothod excute exception,"+e);
            return  result;
        }
        LOGGER.info("resultPage mothod invoke,reustl:"+ JSON.toJSONString(result));
        return result;
    }


}
