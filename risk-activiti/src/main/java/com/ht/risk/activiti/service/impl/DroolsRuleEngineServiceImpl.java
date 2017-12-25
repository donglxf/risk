package com.ht.risk.activiti.service.impl;

import com.alibaba.fastjson.JSON;
import com.ht.risk.activiti.controller.ActivitiController;
import com.ht.risk.activiti.model.DroolsParamter;
import com.ht.risk.activiti.outService.DroolsRuleEngineInterface;
import com.ht.risk.activiti.service.DroolsRuleEngineService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.Expression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("droolsRuleEngineService")
public class DroolsRuleEngineServiceImpl implements DroolsRuleEngineService {

    protected static final Logger LOGGER = LoggerFactory.getLogger(DroolsRuleEngineServiceImpl.class);

    private Expression expressionValue;

    @Autowired
    private DroolsRuleEngineInterface droolsRuleEngineInterface;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        LOGGER.info("DroolsRuleEngineServiceImpl service excute start!");
        String sence = (String) expressionValue.getValue(delegateExecution);
        Map senceData = (Map)delegateExecution.getVariable("senceData");
        DroolsParamter paramter = new DroolsParamter();
        paramter.setSence(sence);
        paramter.setData(senceData);
        LOGGER.info("DroolsRuleEngineServiceImpl service paramter:"+ JSON.toJSONString(paramter));
        droolsRuleEngineInterface.excuteDroolsScene(paramter);
        LOGGER.info("DroolsRuleEngineServiceImpl service excute end!");
    }

    public Expression getExpressionValue() {
        return expressionValue;
    }

    public void setExpressionValue(Expression expressionValue) {
        this.expressionValue = expressionValue;
    }
}
