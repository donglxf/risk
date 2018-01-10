package com.ht.risk.activiti.service.impl;

import com.alibaba.fastjson.JSON;
import com.ht.risk.activiti.rpc.DroolsRuleEngineInterface;
import com.ht.risk.activiti.service.DroolsRuleEngineService;
import com.ht.risk.api.model.drools.DroolsParamter;
import com.ht.risk.api.model.drools.RuleExcuteResult;
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

        LOGGER.info("###############策略规则运行开始！");
        String sence = (String) expressionValue.getValue(delegateExecution);
        LOGGER.info("###############策略规则运行开始,策略唯一标识："+sence);
        Map senceData = (Map)delegateExecution.getVariable("senceData");
        DroolsParamter paramter = new DroolsParamter();
        paramter.setSence(sence);
        paramter.setData(senceData);
        paramter.setProcessInstanceId(delegateExecution.getProcessInstanceId());

        LOGGER.info("DroolsRuleEngineServiceImpl service paramter:"+ JSON.toJSONString(paramter));
        RuleExcuteResult result = droolsRuleEngineInterface.excuteDroolsScene(paramter);
        delegateExecution.setVariable(sence,result);
        LOGGER.info("###############策略"+sence+"规则运行结束！");
    }

    public Expression getExpressionValue() {
        return expressionValue;
    }

    public void setExpressionValue(Expression expressionValue) {
        this.expressionValue = expressionValue;
    }
}
