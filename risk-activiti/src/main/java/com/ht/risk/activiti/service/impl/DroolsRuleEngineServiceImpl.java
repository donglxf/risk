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
        if(senceCodeExp == null){
            delegateExecution.setVariable(ActivitiConstants.PROC_STATUS,ActivitiConstants.PROC_STATUS_EXCEPTION);
            return ;
        }
        String senceCode = String.valueOf(senceCodeExp.getValue(delegateExecution));
        delegateExecution.setVariable(ActivitiConstants.RULE_SENCE_CODE,senceCode);
        String version = null;
        if(versionExp != null){
            version = String.valueOf(versionExp.getValue(delegateExecution));
        }
        LOGGER.info("drools excute start,senceCode："+senceCode+";version:"+version);
        // 策略的业务数据
        Map senceData = (Map)delegateExecution.getVariable(senceCode+ ActivitiConstants.DROOLS_VARIABLE_NAME);
        String type = String.valueOf(delegateExecution.getVariable(ActivitiConstants.EXCUTE_TYPE_VARIABLE_NAME));
        DroolsParamter paramter = new DroolsParamter();
        paramter.setSence(senceCode);
        paramter.setVersion(version);
        paramter.setData(senceData);
        paramter.setProcessInstanceId(delegateExecution.getProcessInstanceId());
        paramter.setType(type);
        LOGGER.info("DroolsRuleEngineServiceImpl service paramter:"+ JSON.toJSONString(paramter));
        RuleExcuteResult result = null;
        result = droolsRuleEngineInterface.excuteDroolsSceneValidation(paramter);
        delegateExecution.setVariable(senceCode+"-"+version,result);
        LOGGER.info("DroolsRuleEngineServiceImpl service end , result:"+ JSON.toJSONString(result));
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
