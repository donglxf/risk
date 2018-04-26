package com.ht.risk.activiti.service.impl;

import com.ht.risk.activiti.entity.ActExcuteTask;
import com.ht.risk.activiti.mapper.ActExcuteTaskMapper;
import com.ht.risk.activiti.rpc.ActivitiRpc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2016/6/21.
 */
@Service
public class ReceiverService {

    protected static final Logger LOGGER = LoggerFactory.getLogger(ReceiverService.class);

    /*@RabbitListener(queues = "activiti.service")
    public void receiveMessage(String message) {
        LOGGER.info("ReceiverService receiveMessage from quene activiti-result-queue,message"+message);
    }

    @RabbitListener(queues = "risk.model.ownerLoan")
    public void ownerLoanReceiveMessage(String message) {
        LOGGER.info("ReceiverService receiveMessage from quene activiti-result-queue,message"+message);
    }*/


    /*private boolean updateTaskInfo(Long taskId,String status){
        if(taskId == null){
            return false;
        }
        ActExcuteTask task = actExcuteTaskMapper.selectById(taskId);
        if (task != null) {
            task.setStatus(status);
            Long startL = task.getCreateTime().getTime();
            task.setSpendTime(System.currentTimeMillis() - startL);
            actExcuteTaskMapper.updateById(task);
            return true;
        }
        return false;

    }*/

    /*class UpdateTaskStatusTask implements Runnable {
        private String proceInstId;

        public UpdateTaskStatusTask(String proceInstId) {
            this.proceInstId = proceInstId;
        }

        @Override
        public void run() {
            LOGGER.info("UpdateTaskStatusTask running...procInstId: "+proceInstId);
            int count = 1;
            boolean flag = true;
            while(count <= 3 && flag){
                LOGGER.info("UpdateTaskStatusTask running...count: "+count+";flag:"+flag);
                try {
                    ModelParamter modelParamter = new ModelParamter();
                    modelParamter.setProcessId(proceInstId);
                    modelParamter.setVariableName(ActivitiConstants.PROC_TASK_ID_CONSTANTS);
                    String result = activitiRpc.getProcInstVarObj(modelParamter);
                    if(result == null || StringUtils.isEmpty(result)){
                        count++;
                        Thread.currentThread().sleep(4000);
                        continue;
                    }
                    ActExcuteTask task = actExcuteTaskMapper.selectById(Long.parseLong(result));
                    if (task != null) {
                        task.setStatus(ActivitiConstants.PROC_STATUS_SUCCESS);
                        Long  startL = task.getCreateTime().getTime();
                        task.setSpendTime(System.currentTimeMillis() - startL);
                        actExcuteTaskMapper.updateById(task);
                    }
                    flag = false;
                    break;
                } catch (Exception e) {
                    count++;
                }
                try {
                    Thread.currentThread().sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            LOGGER.info("UpdateTaskStatusTask success...procInstId: "+proceInstId);
        }
    }*/
}
