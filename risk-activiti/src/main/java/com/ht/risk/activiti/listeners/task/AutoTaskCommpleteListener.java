package com.ht.risk.activiti.listeners.task;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("autoTaskCommpleteService")
public class AutoTaskCommpleteListener implements TaskListener {

    @Resource
    private TaskService taskService;

    @Resource
    ProcessEngine processEngine;

    public void notify(DelegateTask delegateTask) {

        List<Task> tasks = taskService.createTaskQuery().taskAssignee("zhangzhen").list();
        Task task = null;
        for (int i= 0;i<tasks.size();i++){
            task = tasks.get(i);
            processEngine.getTaskService()//与正在执行的任务管理相关的Service
                    .complete(task.getId(),null);
            System.out.println("#########################任务已处理！");
        }

    }
}
