package com.ht.risk.activiti.service.impl;


import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("processTestService")
public class ProcessTestServiceImpl implements JavaDelegate {


    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessTestServiceImpl.class);

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        LOGGER.info("ProcessTestServiceImpl service invoke...");
    }
}
