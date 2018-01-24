package com.ht.risk.activiti.service.impl;

import com.alibaba.fastjson.JSON;
import com.ht.risk.activiti.rpc.DroolsRuleEngineInterface;
import com.ht.risk.activiti.service.DroolsRuleEngineService;
import com.ht.risk.api.constant.activiti.ActivitiConstants;
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

    private Expression senceCodeExp;
    private Expression versionExp;

    @Autowired
    private DroolsRuleEngineInterface droolsRuleEngineInterface;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String senceCode = (String) senceCodeExp.getValue(delegateExecution);
        String version = (String) versionExp.getValue(delegateExecution);
        LOGGER.info("###############drools excute start,senceCode："+senceCode+";version:"+version);
        Map senceData = (Map)delegateExecution.getVariable(senceCode+ ActivitiConstants.DROOLS_VARIABLE_NAME);
        String type = String.valueOf(delegateExecution.getVariable("droolsExcuteType"));
        DroolsParamter paramter = new DroolsParamter();
        paramter.setSence(senceCode);
        paramter.setVersion(version);
        paramter.setData(senceData);
        paramter.setProcessInstanceId(delegateExecution.getProcessInstanceId());
        paramter.setType(type);
        LOGGER.info("DroolsRuleEngineServiceImpl service paramter:"+ JSON.toJSONString(paramter));
        RuleExcuteResult result = null;
        if("1".equals(type)){
            result = droolsRuleEngineInterface.excuteDroolsScene(paramter);
        }
        if("2".equals(type)){
            result = droolsRuleEngineInterface.excuteDroolsSceneValidation(paramter);
        }
        delegateExecution.setVariable(senceCode+"-"+version,result);
        LOGGER.info("###############drools excute end,senceCode："+senceCode+";version:"+version);
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
