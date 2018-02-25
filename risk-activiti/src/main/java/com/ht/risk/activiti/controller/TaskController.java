package com.ht.risk.activiti.controller;

import com.alibaba.fastjson.JSON;
import com.ht.risk.activiti.vo.TaskVo;
import com.ht.risk.api.model.activiti.ModelParamter;
import com.ht.risk.common.result.Result;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.repository.Model;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskController.class);

    private ProcessEngine processEngine;

    /**
     * 查用户的任务列表
     *
     * @param taskVo
     * @return
     */
    @RequestMapping(value = "/queryUserTaskList")
    public Result<List<TaskVo>> queryUserTaskList(@RequestBody TaskVo taskVo) {
        LOGGER.info("queryUserTaskList method invoke start,paramter:" + JSON.toJSONString(taskVo));
        Result<List<TaskVo>> data = null;
        List<TaskVo> tasks = null;
        try {
            String userId = taskVo.getUserId();
            String procDefId = null;
            List<Task> list = processEngine.getTaskService()//
                    .createTaskQuery()//
                    .processDefinitionId(procDefId)
                    .taskAssignee(userId)//指定个人任务查询
                    .list();
            tasks = new ArrayList<TaskVo>();
            for (Task task : list) {
                taskVo = new TaskVo();
                taskVo.setTaskId(task.getId());
                taskVo.setTaskName(task.getName());
                taskVo.setExecutionId(task.getExecutionId());
                taskVo.setDescription(task.getDescription());
                taskVo.setProcessInstanceId(task.getProcessInstanceId());
                tasks.add(taskVo);
            }
        } catch (Exception e) {
            LOGGER.info("queryUserTaskList method invoke error,exception:" + e.getMessage());
            e.printStackTrace();
            data = Result.error(1, "查询待审批任务异常！");
            return data;
        }
        data = Result.success(tasks);
        LOGGER.info("queryUserTaskList method invoke end,result:" + JSON.toJSONString(data));
        return data;
    }

    /**
     * 完成审批任务
     *
     * @param taskVo
     */
    @RequestMapping(value = "/completeTask")
    public Result completeTask(@RequestBody TaskVo taskVo) {
        LOGGER.info("completeTask method invoke start,paramter:" + JSON.toJSONString(taskVo));
        Result data = null;
        try {
            if (taskVo == null || StringUtils.isEmpty(taskVo.getProcessInstanceId())) {
                data = Result.error(1, "参数异常");
                return data;
            }
            String taskId = taskVo.getTaskId();
            if (taskVo.getVariableData() != null) {
                processEngine.getTaskService().complete(taskId, taskVo.getVariableData());
            } else {
                processEngine.getTaskService().complete(taskId);
            }
        } catch (Exception e) {
            LOGGER.info("completeTask method invoke error,exception:" + e.getMessage());
            e.printStackTrace();
            data = Result.error(1, "完成待审批任务异常！");
            return data;
        }
        data = Result.success();
        LOGGER.info("completeTask method invoke end...");
        return data;
    }

   /* *//**
     * 自动审批任务
     *
     * @param taskVo
     *//*
    @RequestMapping(value = "/autoCompleteTask")
    public Result autoCompleteTask(@RequestBody TaskVo taskVo) {
        LOGGER.info("autoCompleteTask method invoke start,paramter:" + JSON.toJSONString(taskVo));
        Result data = null;
        if(taskVo == null || StringUtils.isEmpty(taskVo.getProcessInstanceId())){
            data =  Result.error(1,"参数异常");
            return data;
        }
        List<Task> list = processEngine.getTaskService().createTaskQuery().processInstanceId(taskVo.getProcessInstanceId())
                .orderByTaskCreateTime().asc().list();
        for (Iterator<Task> iterator = list.iterator();iterator.hasNext();){
            processEngine.getTaskService().complete(taskId);
        }

        String taskId = taskVo.getTaskId();
        processEngine.getTaskService().complete(taskId);
        LOGGER.info("autoCompleteTask method invoke end...");
    }*/
}
