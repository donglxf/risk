package com.ht.risk.activiti.service;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public interface MqSendResultService  extends JavaDelegate {

    @Override
    public void execute(DelegateExecution execution)throws Exception;

}
