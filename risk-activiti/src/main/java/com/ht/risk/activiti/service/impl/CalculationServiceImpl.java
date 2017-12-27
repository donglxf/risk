package com.ht.risk.activiti.service.impl;

import com.alibaba.fastjson.JSON;
import com.ht.risk.activiti.model.RuleExcuteResult;
import com.ht.risk.activiti.service.CalculationService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.Expression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CalculationServiceImpl  implements CalculationService {

    protected static final Logger LOGGER = LoggerFactory.getLogger(CalculationServiceImpl.class);

    private Expression expressionValue;


    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        LOGGER.info("###############房贷评分卡得分计算开始");
        String sence = (String) expressionValue.getValue(delegateExecution);
        RuleExcuteResult result = (RuleExcuteResult)delegateExecution.getVariable(sence);
        LOGGER.info("###############房贷评分卡得分结果"+ JSON.toJSONString(result));
        LOGGER.info("###############房贷评分卡得分计算结束");
    }
}
