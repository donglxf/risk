package com.ht.risk.activiti.service.impl;

import com.ht.risk.activiti.model.RuleExcuteResult;
import com.ht.risk.activiti.service.MortgageRiskService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.Expression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service("mortgageRiskService")
public class MortgageRiskServiceImpl implements MortgageRiskService {

    protected static final Logger LOGGER = LoggerFactory.getLogger(MortgageRatingCalculationServiceImpl.class);

    private Expression expressionValue;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        LOGGER.info("###########房速贷风险策略结果处理开始");
        String sence = (String) expressionValue.getValue(delegateExecution);
        RuleExcuteResult result = (RuleExcuteResult)delegateExecution.getVariable(sence);
        delegateExecution.setVariable("flag",1);
        LOGGER.info("###########房速贷风险策略结果:符合进件规则");
        LOGGER.info("###########房速贷风险策略结果处理结束");
    }
}
