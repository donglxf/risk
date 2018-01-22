package com.ht.risk.activiti.service.impl;

import com.alibaba.fastjson.JSON;
import com.ht.risk.activiti.entity.ActExcuteTask;
import com.ht.risk.activiti.entity.RiskValidateBatch;
import com.ht.risk.activiti.mapper.ActExcuteTaskMapper;
import com.ht.risk.activiti.mapper.RiskValidateBatchMapper;
import com.ht.risk.activiti.rpc.ActivitiRpc;
import com.ht.risk.api.constant.activiti.ActivitiConstants;
import com.ht.risk.api.model.activiti.ModelParamter;
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
            ModelParamter modelParamter = new ModelParamter();
            modelParamter.setProcessId(proceInstId);
            modelParamter.setVariableName(ActivitiConstants.PROC_TASK_ID_CONSTANTS);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Object result = activitiRpc.getProcInstVarObj(modelParamter);
            LOGGER.info("ReceiverService receiveMessage query variable,result:"+ JSON.toJSONString(result));
            if(result != null){
                ActExcuteTask task = actExcuteTaskMapper.selectById(Long.parseLong(String.valueOf(result)));
                if(task != null){
                    task.setStatus("1");
                    actExcuteTaskMapper.updateById(task);
                }
                LOGGER.info("ReceiverService receiveMessage ,task id:"+String.valueOf(result)+" complete..");
            }
        }
    }
}
