package com.ht.risk.activiti.listeners;

import com.ht.risk.activiti.service.SendService;
import com.ht.risk.activiti.service.impl.ProcessTestServiceImpl;
import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ProcessEndEventListeners implements ActivitiEventListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessEndEventListeners.class);

    @Resource
    private SendService sendService;

    @Override
    public void onEvent(ActivitiEvent event) {
        LOGGER.info("ProcessTestServiceImpl onEvent invoke..."+event.getType());
        switch (event.getType()) {
            case PROCESS_COMPLETED:// 流程结束
                LOGGER.info("ProcessEndEventListeners PROCESS_COMPLETED invoke...");
                sendService.sendToRabbitmq(getEndSendMsgInfo(event));
                break;
            default:
        }
    }

    private String getEndSendMsgInfo(ActivitiEvent event){
        return event.getProcessInstanceId();
    }

    @Override
    public boolean isFailOnException() {
        return false;
    }
}
