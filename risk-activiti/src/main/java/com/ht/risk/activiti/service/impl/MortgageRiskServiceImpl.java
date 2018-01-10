package com.ht.risk.activiti.service.impl;

import com.ht.risk.activiti.service.MortgageRiskService;
import com.ht.risk.api.model.drools.RuleExcuteResult;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.Expression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("mortgageRiskService")
public class MortgageRiskServiceImpl implements MortgageRiskService {

    protected static final Logger LOGGER = LoggerFactory.getLogger(MortgageRatingCalculationServiceImpl.class);

    private Expression expressionValue;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        LOGGER.info("###########房速贷风险策略结果处理开始");
        String sence = (String) expressionValue.getValue(delegateExecution);
        RuleExcuteResult result = (RuleExcuteResult)delegateExecution.getVariable(sence);
        if(result != null){
            Map map = result.getData().getGlobalMap();
            if(map != null){
                String flag = String.valueOf(map.get("into_pieces_flag"));
                if("0".equals(flag)){
                    delegateExecution.setVariable("flag",1);
                    LOGGER.info("###########房速贷风险策略结果:符合进件规则");
                }else{
                    delegateExecution.setVariable("flag",2);
                    LOGGER.info("###########房速贷风险策略结果:不符合进件规则");
                    delegateExecution.setVariable("result", "不符合进件规则!");
                }
            }
        }
        LOGGER.info("###########房速贷风险策略结果处理结束");
    }

    public Expression getExpressionValue() {
        return expressionValue;
    }

    public void setExpressionValue(Expression expressionValue) {
        this.expressionValue = expressionValue;
    }
}
