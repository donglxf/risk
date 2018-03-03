package com.ht.risk.activiti.service.impl;

import com.alibaba.fastjson.JSON;
import com.ht.risk.activiti.rpc.DroolsRuleEngineInterface;
import com.ht.risk.activiti.service.DroolsRuleEngineService;
import com.ht.risk.api.constant.activiti.ActivitiConstants;
import com.ht.risk.api.model.activiti.RuleExcuteDetail;
import com.ht.risk.api.model.drools.DroolsParamter;
import com.ht.risk.api.model.drools.RuleExcuteResult;
import com.ht.risk.api.model.drools.RuleStandardResult;
import com.ht.risk.common.comenum.RuleCallTypeEnum;
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
        // 获取决策编码
        String msg = String.valueOf(delegateExecution.getVariable(ActivitiConstants.PROC_EXCUTE_MSG));
        String senceCode = String.valueOf(senceCodeExp.getValue(delegateExecution));
        String version = null;
        if(versionExp != null){
            version = String.valueOf(versionExp.getValue(delegateExecution));
        }
        LOGGER.info("drools excute start,senceCode："+senceCode+";version:"+version);
        // drools引擎所需数据
        Object senceObj = delegateExecution.getVariable(ActivitiConstants.DROOLS_VARIABLE_NAME+senceCode);
        // 模型执行模式
        String modelType = String.valueOf(delegateExecution.getVariable(ActivitiConstants.PROC_MODEL_EXCUTE_TYPE_KEY));
        // 获取规则执行模式
        String ruleType = getDroolsExcuteType(modelType);
        Map senceMap = null;
        // drools引擎执行
        RuleExcuteDetail detail = null;
        List<RuleExcuteDetail> details = new ArrayList<RuleExcuteDetail>();
        try{
            List<Map<String,Object>> datas = ( List<Map<String,Object>>)senceObj;
            for(int i =0;i<datas.size();i++){
                RuleExcuteResult result = this.excuteRules(senceCode,version,datas.get(i),delegateExecution.getProcessInstanceId(),ruleType);
                if(result != null){
                    detail = matainExcuteDetail(result);
                    detail.setInParamter(datas.get(i));
                    details.add(detail);
                }
            }
            delegateExecution.setVariable(ActivitiConstants.SENCE_EXCUTE_RESULT_VAR+senceCode,details);
        }catch (Exception e){
            msg += "决策编码为："+senceCode+"；执行异常";
        }
        delegateExecution.setVariable(ActivitiConstants.PROC_EXCUTE_MSG,msg.toString());
        LOGGER.info("DroolsRuleEngineServiceImpl service end , result:"+ JSON.toJSONString(details));
    }
    // 获取规则执行类型
    private String getDroolsExcuteType(String modelExcuteType){
        // 服务模式
        if(ActivitiConstants.EXCUTE_TYPE_SERVICE.equals(modelExcuteType)){
            return RuleCallTypeEnum.model.getType();
        }
        // 验证模式
        else{
            return RuleCallTypeEnum.modelValidation.getType();
        }
    }
    // 包装返回结果
    private RuleExcuteDetail matainExcuteDetail(RuleExcuteResult result){
        RuleExcuteDetail detail = new RuleExcuteDetail();
        RuleStandardResult ruleInfo = result.getData();
        String logId = ruleInfo.getLogIdList() != null && ruleInfo.getLogIdList().size()>0 ? ruleInfo.getLogIdList().get(0):"";
        detail.setSenceVersionId(result.getSenceVersoionId());
        detail.setLogId(logId);
        detail.setRuleList(ruleInfo.getRuleList());
        return  detail;
    }
    // 调用规则引擎执行规则
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

    /*if(senceCode.contains(",")){
            String[] senceCodeAry =senceCode.split(",");
            RuleExcuteResult result = null;
            for(int i=0;i<senceCodeAry.length;i++){
                result = this.excuteRules(senceCodeAry[i],version,senceMap,delegateExecution.getProcessInstanceId(),ruleType);
                if(result == null){
                    continue;
                }
                detail = matainExcuteDetail(result);
                details.add(detail);
                delegateExecution.setVariable(ActivitiConstants.SENCE_EXCUTE_RESULT_VAR+senceCodeAry[i],detail);
            }
        }else{

        }*/
}
