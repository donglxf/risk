package com.ht.risk.activiti.service.ownerloan.impl;

import com.alibaba.fastjson.JSON;
import com.ht.risk.activiti.rpc.ActivitiConfigInterface;
import com.ht.risk.activiti.service.impl.TopicSenderServiceImpl;
import com.ht.risk.activiti.service.ownerloan.DistributionRatioService;
import com.ht.risk.activiti.vo.OwnerLoanModelResult;
import com.ht.risk.api.constant.activiti.ActivitiConstants;
import com.ht.risk.api.enums.AuditTypeEnum;
import com.ht.risk.api.model.activiti.ModelExcuteResult;
import com.ht.risk.api.model.activiti.RpcActExcuteTask;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.Expression;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

/**
 *  评分卡，人工和自动审核比例设置
 */
@Service("distributionRatioService")
public class DistributionRatioServiceImpl implements DistributionRatioService {


    private static final Logger LOGGER = LoggerFactory.getLogger(DistributionRatioServiceImpl.class);

    @Resource
    private ActivitiConfigInterface activitiConfigInterface;

    @Resource
    private TopicSenderServiceImpl topicSenderService;

    /**
     * 人工和自动比例设置
     */
    private Expression persionRatioExp;
    private static Random random = new Random();

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        LOGGER.info("DistributionRatioServiceImpl execute start");
        String persionRatioStr = String.valueOf(persionRatioExp.getValue(execution));
        String modelType = String.valueOf(execution.getVariable(ActivitiConstants.PROC_MODEL_EXCUTE_TYPE_KEY));
        String errorMsgStr = String.valueOf(execution.getVariable(ActivitiConstants.PROC_EXCUTE_ERROR_MSG));
        String ruleMsgStr = String.valueOf(execution.getVariable(ActivitiConstants.PROC_EXCUTE_HIT_RULE_MSG));
        String msg = String.valueOf(execution.getVariable(ActivitiConstants.PROC_EXCUTE_MSG));
        String taskIdStr = String.valueOf(execution.getVariable(ActivitiConstants.PROC_TASK_ID_VAR_KEY));
        OwnerLoanModelResult ownerResult = (OwnerLoanModelResult)execution.getVariable(ActivitiConstants.PROC_OWNER_LOAN_RESULT_CODE);
        LOGGER.info("DistributionRatioServiceImpl execute start,persionRatioStr:"+persionRatioStr);
        // 数据组装，人工和自动设置
        String type = randomRatio(persionRatioStr);
        ownerResult.setAuditType(type);
        ownerResult.setCode("0");
        ownerResult.setErrorMsg(errorMsgStr);
        ownerResult.setHitMsg(ruleMsgStr);
        ownerResult.setWarmMsg(StringUtils.isEmpty(msg)?"执行成功":msg);
        ownerResult.setProcInstId(execution.getProcessInstanceId());
        ownerResult.setTaskId(taskIdStr);
        ownerResult.setQuota(0d);
        ownerResult.setInterInfos(new ArrayList<>(ownerResult.getInterInfo().values()));
        ownerResult.setInterInfo(null);
        //更新任务状态
        long startTime = Long.parseLong(String.valueOf(execution.getVariable(ActivitiConstants.PROC_START_CURRENT_TIME)));
        updateTask(ownerResult,taskIdStr,execution.getProcessInstanceId(),startTime);
        // MQ发送消息
        if(ActivitiConstants.EXCUTE_TYPE_SERVICE.equals(modelType)) {// 服务类型
            if("CLS_APP".equals(execution.getVariable(ActivitiConstants.PROC_CHANNEL_TYPE))){ // 鸿特app端调用
                topicSenderService.sendClsAppOwnerLoan(JSON.toJSONString(ownerResult));
            }else {
                topicSenderService.sendOwnerLoan(JSON.toJSONString(ownerResult));
            }
        }
        LOGGER.info("DistributionRatioServiceImpl execute end");
    }

    // 更新任务信息
    private void updateTask(OwnerLoanModelResult modelResult, String taskId, String procInstId, long startTime){
        RpcActExcuteTask task = new RpcActExcuteTask();
        task.setStatus(ActivitiConstants.PROC_STATUS_SUCCESS);
        task.setUpdateTime(new Date(System.currentTimeMillis()));
        task.setId(taskId);
        long spendTime = System.currentTimeMillis()-startTime;
        task.setSpendTime(spendTime);
        task.setOutParamter(JSON.toJSONString(modelResult));
        task.setProcInstId(procInstId);
        activitiConfigInterface.updateTask(task);
    }

    // d  百分比，0是人工，1 是自動
    public static String randomRatio(String str) {
        try{
            double d = Double.parseDouble(str);
            double ratio  = d * 100;
            double autoRation = 100 - ratio;
            // 如果配置錯誤，判定走人工
            if (ratio <= 0 || ratio > 100) {
                return AuditTypeEnum.PERSONAL.getCode();
            }
            Integer n = random.nextInt(100);
            if(n>0 && n< ratio){
                return AuditTypeEnum.PERSONAL.getCode();
            }
            return AuditTypeEnum.AUTO.getCode();
        }catch (Exception e){
            LOGGER.error("DistributionRatioServiceImpl randomRatio exception",e);
        }
        return AuditTypeEnum.PERSONAL.getCode();
    }

    public Expression getPersionRatioExp() {
        return persionRatioExp;
    }

    public void setPersionRatioExp(Expression persionRatioExp) {
        this.persionRatioExp = persionRatioExp;
    }

}
