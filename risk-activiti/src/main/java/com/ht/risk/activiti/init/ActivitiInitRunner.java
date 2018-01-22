package com.ht.risk.activiti.init;

import com.ht.risk.activiti.listeners.ProcessEndEventListeners;
import org.activiti.engine.RuntimeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import static org.activiti.engine.delegate.event.ActivitiEventType.PROCESS_COMPLETED;

/**
 *  任务调度初始化类
 */
@Component
@Order(value=2)
public class ActivitiInitRunner implements CommandLineRunner {

    protected static final Logger LOGGER = LoggerFactory.getLogger(ActivitiInitRunner.class);

    @Resource
    private RuntimeService runtimeService;

    @Resource
    private ProcessEndEventListeners processEndEventListeners;

    @Override
    public void run(String... strings) throws Exception {
        LOGGER.info("ActivitiInitRunner runing ...");
        runtimeService.addEventListener(processEndEventListeners,PROCESS_COMPLETED);
        LOGGER.info("ActivitiInitRunner complete ...");
    }
}
