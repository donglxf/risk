package com.ht.risk.activiti.controller;

import com.alibaba.fastjson.JSON;
import com.ht.risk.activiti.entity.ActExcuteTask;
import com.ht.risk.activiti.service.ActExcuteTaskService;
import com.ht.risk.api.model.activiti.RpcActExcuteTask;
import com.ht.risk.common.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping("/task")
public class ActExcuteTaskController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActivitiConfigController.class);

    @Resource
    private ActExcuteTaskService actExcuteTaskService;

    @RequestMapping("/updateTask")
    public Result<Boolean> updateTask(@RequestBody  RpcActExcuteTask rpcActExcuteTask){
        LOGGER.info("updateTask mothod invoke,paramter:"+ JSON.toJSONString(rpcActExcuteTask));
        Result<Boolean> result = null;
        if(rpcActExcuteTask == null || rpcActExcuteTask.getId() == null){
            LOGGER.error("updateTask mothod invoke,paramter null exception");
            result =Result.error(1,"参数异常！");
            return  result;
        }
        try {
            ActExcuteTask task = new ActExcuteTask();
            task.setId(rpcActExcuteTask.getId());
            task = actExcuteTaskService.selectById(task);
            if(task != null){
                long currentTime = System.currentTimeMillis();
                task.setStatus(rpcActExcuteTask.getStatus());
                Long spendTime = Math.abs(currentTime - task.getCreateTime().getTime());
                task.setSpendTime(spendTime);
                task.setOutParamter(rpcActExcuteTask.getOutParamter());
                task.setUpdateTime(new Date(currentTime));
                task.setProcInstId(rpcActExcuteTask.getProcInstId());
                boolean flag = actExcuteTaskService.updateById(task);
                result = Result.success(flag);
            }else{
                result = Result.error(1,"更新失败，没找要更新的数据！");
            }
        }catch (Exception e){
            result = Result.error(2,"updateTask mothod excute exception,"+e);
        }
        LOGGER.info("updateTask mothod invoke end,reustl:"+ JSON.toJSONString(result));
        return result;
    }

}
