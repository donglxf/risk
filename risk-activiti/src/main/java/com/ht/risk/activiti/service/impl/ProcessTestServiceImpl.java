package com.ht.risk.activiti.service.impl;


import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.Expression;
import org.activiti.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("processTestService")
public class ProcessTestServiceImpl implements JavaDelegate {

    private Expression senceCodeExp;
    private Expression versionExp;


    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessTestServiceImpl.class);

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String senceCode = (String) senceCodeExp.getValue(execution);
        String version = (String) versionExp.getValue(execution);
        LOGGER.info("ProcessTestServiceImpl service invoke...senceCode:"+senceCode+";version:"+version);
    }

    public Expression getSenceCodeExp() {
        return senceCodeExp;
    }

    public void setSenceCodeExp(Expression senceCodeExp) {
        this.senceCodeExp = senceCodeExp;
    }

    public Expression getVersionExp() {
        return versionExp;
    }

    public void setVersionExp(Expression versionExp) {
        this.versionExp = versionExp;
    }
}
