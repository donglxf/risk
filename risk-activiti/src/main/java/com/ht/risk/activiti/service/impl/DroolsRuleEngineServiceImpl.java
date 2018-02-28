package com.ht.risk.activiti.service.impl;

import com.alibaba.fastjson.JSON;
import com.ht.risk.activiti.rpc.DroolsRuleEngineInterface;
import com.ht.risk.activiti.service.DroolsRuleEngineService;
import com.ht.risk.api.constant.activiti.ActivitiConstants;
import com.ht.risk.api.model.activiti.RuleExcuteDetail;
import com.ht.risk.api.model.drools.DroolsParamter;
import com.ht.risk.api.model.drools.RuleExcuteResult;
import com.ht.risk.api.model.drools.RuleStandardResult;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.Expression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
        Object senceObj = delegateExecution.getVariable(ActivitiConstants.DROOLS_VARIABLE_NAME);
        String type = String.valueOf(delegateExecution.getVariable(ActivitiConstants.EXCUTE_TYPE_VARIABLE_NAME));
        Map senceMap = null;
        if(senceObj != null){
            senceMap = (Map)senceObj;
        }
        List<RuleExcuteDetail> details = new ArrayList<RuleExcuteDetail>();
        RuleExcuteDetail detail = null;
        if(senceCode.contains(",")){
            String[] senceCodeAry =senceCode.split(",");
            RuleExcuteResult result = null;
            for(int i=0;i<senceCodeAry.length;i++){
                result = this.excuteRules(senceCodeAry[i],version,senceMap,delegateExecution.getProcessInstanceId(),type);
                if(result == null){
                    continue;
                }
                detail = matainExcuteDetail(result);
                details.add(detail);
                delegateExecution.setVariable(ActivitiConstants.SENCE_EXCUTE_RESULT_VAR+senceCodeAry[i],detail);
            }
        }else{
            RuleExcuteResult result = this.excuteRules(senceCode,version,senceMap,delegateExecution.getProcessInstanceId(),type);
            if(result != null){
                detail = matainExcuteDetail(result);
                details.add(detail);
                delegateExecution.setVariable(ActivitiConstants.SENCE_EXCUTE_RESULT_VAR+senceCode,matainExcuteDetail(result));
            }
        }
        LOGGER.info("DroolsRuleEngineServiceImpl service end , result:"+ JSON.toJSONString(details));
    }
    private RuleExcuteDetail matainExcuteDetail(RuleExcuteResult result){
        RuleExcuteDetail detail = new RuleExcuteDetail();
        RuleStandardResult ruleInfo = result.getData();
        String logId = ruleInfo.getLogIdList() != null && ruleInfo.getLogIdList().size()>0 ? ruleInfo.getLogIdList().get(0):"";
        detail.setSenceVersionId(result.getSenceVersoionId());
        detail.setCode(String.valueOf(result.getCode()));
        detail.setMsg(result.getMsg());
        detail.setLogId(logId);
        detail.setRuleList(ruleInfo.getRuleList());
        return  detail;
    }

    private RuleExcuteResult excuteRules(String senceCode,String version,Map<String,Object> data,String proceInstId,String type){
        DroolsParamter paramter = new DroolsParamter();
        paramter.setSence(senceCode);
        paramter.setVersion(version);
        paramter.setData(data);
        paramter.setProcessInstanceId(proceInstId);
        paramter.setType(type);
        LOGGER.info("DroolsRuleEngineServiceImpl service paramter:"+ JSON.toJSONString(paramter));
        RuleExcuteResult result = null;
        result = droolsRuleEngineInterface.excuteDroolsSceneValidation(paramter);
        return result;
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
