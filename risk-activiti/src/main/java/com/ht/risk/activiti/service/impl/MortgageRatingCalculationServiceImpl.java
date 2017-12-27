package com.ht.risk.activiti.service.impl;

import com.alibaba.fastjson.JSON;
import com.ht.risk.activiti.model.RuleExcuteResult;
import com.ht.risk.activiti.service.MortgageRatingCalculationService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.Expression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("mortgageRatingCalculationService")
public class MortgageRatingCalculationServiceImpl implements MortgageRatingCalculationService {

    protected static final Logger LOGGER = LoggerFactory.getLogger(MortgageRatingCalculationServiceImpl.class);

    private Expression expressionValue;


    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        LOGGER.info("###############房贷评分卡得分计算开始");
        String sence = (String) expressionValue.getValue(delegateExecution);
        RuleExcuteResult result = (RuleExcuteResult)delegateExecution.getVariable(sence);
        String total= null;
        if(result != null){
            Map map = result.getData().getGlobalMap();
            if(map != null){
                total = String.valueOf(map.get("total"));
            }
        }
        delegateExecution.setVariable("result","得分"+total==null?0:total);
        LOGGER.info("###############房贷评分卡得分结果"+ JSON.toJSONString(result));
        LOGGER.info("###############房贷评分卡得分计算结束");
    }


    public Expression getExpressionValue() {
        return expressionValue;
    }

    public void setExpressionValue(Expression expressionValue) {
        this.expressionValue = expressionValue;
    }
}
