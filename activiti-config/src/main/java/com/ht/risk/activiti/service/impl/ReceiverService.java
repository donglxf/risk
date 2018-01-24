package com.ht.risk.activiti.service.impl;

import com.alibaba.fastjson.JSON;
import com.ht.risk.activiti.entity.ActExcuteTask;
import com.ht.risk.activiti.entity.RiskValidateBatch;
import com.ht.risk.activiti.mapper.ActExcuteTaskMapper;
import com.ht.risk.activiti.mapper.RiskValidateBatchMapper;
import com.ht.risk.activiti.rpc.ActivitiRpc;
import com.ht.risk.api.constant.activiti.ActivitiConstants;
import com.ht.risk.api.model.activiti.ModelParamter;
import com.ht.risk.common.result.Result;
import com.ht.risk.common.util.StringUtils;
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

    @Resource
    private ActExcuteTaskMapper actExcuteTaskMapper;

    @Resource
    private ActivitiRpc activitiRpc;

    @RabbitListener(queues = "activiti-result-queue")
    public void receiveMessage(String message) {
        LOGGER.info("ReceiverService receiveMessage from quene activiti-result-queue,message"+message);
        String proceInstId = message;
        if(StringUtils.isNotEmpty(proceInstId)){
            new Thread(new UpdateTaskStatusTask(proceInstId)).start();
        }
    }

    class UpdateTaskStatusTask implements Runnable {
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
                        task.setStatus("1");
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
    }
}
