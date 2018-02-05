package com.ht.risk.activiti.listeners.task;


import com.ht.risk.activiti.rpc.RuleServiceInterface;
import com.ht.risk.api.constant.activiti.ActivitiConstants;
import com.ht.risk.api.model.drools.DroolsParamter;
import com.ht.risk.common.result.Result;
import org.activiti.engine.delegate.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service("ruleTaskListener")
public class RuleTaskListener implements Serializable, ExecutionListener {

    protected static final Logger LOGGER = LoggerFactory.getLogger(RuleTaskListener.class);

    private Expression senceCodeExp;
    private Expression versionExp;

    @Autowired
    private RuleServiceInterface ruleServiceInterface;

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

    @Override
    public void notify(DelegateExecution execution) throws Exception {
        String senceCode = (String) senceCodeExp.getValue(execution);
        String version = (String) versionExp.getValue(execution);
        LOGGER.info("drools excute start,senceCode："+senceCode+";version:"+version);
        if(StringUtils.isNotEmpty(senceCode) && StringUtils.isNotEmpty(version)){
            DroolsParamter droolsParamter = new DroolsParamter();
            droolsParamter.setSence(senceCode);
            droolsParamter.setVersion(version);
            Result result = ruleServiceInterface.getAutoVerficationData(droolsParamter);
            if(result != null && result.getCode() == 0 && result.getData() != null){
                execution.setVariable("senceCode"+ActivitiConstants.DROOLS_VARIABLE_NAME,result.getData());
            }
        }

    }
}
