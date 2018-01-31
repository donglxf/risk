package com.ht.risk.activiti.controller;

import com.alibaba.fastjson.JSON;
import com.ht.risk.activiti.entity.ActExcuteTask;
import com.ht.risk.activiti.entity.ActProcRelease;
import com.ht.risk.activiti.service.ActExcuteTaskService;
import com.ht.risk.activiti.service.ActProcReleaseService;
import com.ht.risk.activiti.vo.VerficationModelVo;
import com.ht.risk.api.model.activiti.RpcActExcuteTaskInfo;
import com.ht.risk.api.model.activiti.RpcModelReleaseInfo;
import com.ht.risk.api.model.activiti.RpcModelVerfication;
import com.ht.risk.common.result.Result;
import com.ht.risk.common.util.StringUtils;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("")
public class ActivitiConfigController {

    protected static final Logger LOGGER = LoggerFactory.getLogger(ActivitiConfigController.class);

    @Resource
    private ActExcuteTaskService actExcuteTaskService;
    @Resource
    private ActProcReleaseService actProcReleaseService;

    @RequestMapping("/queryProcInstId")
    public Result<List<RpcActExcuteTaskInfo>> queryProcInstId(RpcModelVerfication rpcModelVerfication){
        LOGGER.info("queryProcInstId mothod invoke,paramter:"+ JSON.toJSONString(rpcModelVerfication));
        Result<List<RpcActExcuteTaskInfo>> result = null;
        if(rpcModelVerfication == null || rpcModelVerfication.getBatchId() == null){
            LOGGER.error("queryProcInstId mothod invoke,paramter null exception");
            result =Result.error(1,"参数异常！");
            return  result;
        }
        try {
            List<RpcActExcuteTaskInfo> tasks = actExcuteTaskService.queryProcInstIdBybatchId(rpcModelVerfication.getBatchId());
            result = Result.success(tasks);
        }catch (Exception e){
            result = Result.error(2,"queryProcInstId mothod excute exception,"+e);
            return  result;
        }
        LOGGER.info("queryProcInstId mothod invoke end,reustl:"+ JSON.toJSONString(result));
        return result;

    }

    @RequestMapping("/queryTasksByBatchId")
    public Result<List<RpcActExcuteTaskInfo>> queryTasksByBatchId(@RequestBody RpcModelVerfication rpcModelVerfication){
        LOGGER.info("queryProcInstId mothod invoke,paramter:"+ JSON.toJSONString(rpcModelVerfication));
        Result<List<RpcActExcuteTaskInfo>> result = null;
        if(rpcModelVerfication == null || rpcModelVerfication.getBatchId() == null){
            LOGGER.error("queryProcInstId mothod invoke,paramter null exception");
            result =Result.error(1,"参数异常！");
            return  result;
        }
        try {
            List<RpcActExcuteTaskInfo> tasks = actExcuteTaskService.queryProcInstIdBybatchId(rpcModelVerfication.getBatchId());
            result = Result.success(tasks);
        }catch (Exception e){
            result = Result.error(2,"queryProcInstId mothod excute exception,"+e);
            return  result;
        }
        LOGGER.info("queryProcInstId mothod invoke end,reustl:"+ JSON.toJSONString(result));
        return result;

    }


    @RequestMapping("/getTaskInfoById")
    public Result<RpcActExcuteTaskInfo> getTaskInfoById(@RequestBody  RpcModelVerfication rpcModelVerfication){
        LOGGER.info("getTaskInfoById mothod invoke,paramter:"+  JSON.toJSONString(rpcModelVerfication));
        Result<RpcActExcuteTaskInfo> result = null;
        if(rpcModelVerfication == null || rpcModelVerfication.getTaskId() == null){
            LOGGER.error("getTaskInfoById mothod invoke,paramter null exception");
            result =Result.error(1,"参数异常！");
            return  result;
        }
        try {
            ActExcuteTask task = actExcuteTaskService.selectById(rpcModelVerfication.getTaskId());
            RpcActExcuteTaskInfo rpcTask = actExcuteTaskService.convertRpcActExcuteTask(task);
            result = Result.success(rpcTask);
        }catch (Exception e){
            result = Result.error(2,"getTaskInfoById mothod excute exception,"+e);
            return  result;
        }
        LOGGER.info("getTaskInfoById mothod invoke end...reustl:"+ JSON.toJSONString(result));
        return result;

    }

    @RequestMapping("/getProcReleaseById")
    public Result<RpcModelReleaseInfo> getProcReleaseById(@RequestBody  RpcModelVerfication rpcModelVerfication){
        LOGGER.info("queryModelProcInstIdByBatchId mothod invoke,paramter:"+ rpcModelVerfication);
        Result<RpcModelReleaseInfo> result = null;
        if(rpcModelVerfication == null || rpcModelVerfication.getProcReleaseId() == null){
            LOGGER.error("queryModelProcInstIdByBatchId mothod invoke,paramter null exception");
            result =Result.error(1,"参数异常！");
            return  result;
        }
        try {
            ActProcRelease release = actProcReleaseService.selectById(rpcModelVerfication.getProcReleaseId());
            RpcModelReleaseInfo rpcRelease = actProcReleaseService.convertRpcActExcuteTask(release);
            result = Result.success(rpcRelease);
        }catch (Exception e){
            result = Result.error(2,"getTaskInfoById mothod excute exception,"+e);
            return  result;
        }
        LOGGER.info("resultPage mothod invoke,reustl:"+ JSON.toJSONString(result));
        return result;
    }


    @PostMapping("modelStatistic")
    @ApiOperation(value = "模型统计图")
    public Result<Map<String, Object>> modelStatistic(HttpServletRequest request) {
        Map<String, String[]> paramMap = request.getParameterMap();
        String startTime= StringUtils.isNotBlank(paramMap.get("startDate")[0]) ? paramMap.get("startDate")[0] +" 00:00:00" :null ;
        String endTime=StringUtils.isNotBlank(paramMap.get("endDate")[0]) ? paramMap.get("endDate")[0] +" 23:59:59" :null ;
        String getWay=paramMap.get("getWay")[0];

        Map<String, Object> map = new HashMap<>();
        map.put("startTime", startTime);
        map.put("endTime", endTime);
        map.put("getWay", getWay);
        Map<String, Object> resultMap = actExcuteTaskService.getModelGraph (map); // 平均响应时间

        return Result.success(resultMap);
    }


}
