package com.ht.risk.activiti.service.ownerloan.impl;

import com.alibaba.fastjson.JSON;
import com.ht.risk.activiti.rpc.ActivitiConfigInterface;
import com.ht.risk.activiti.service.impl.TopicSenderServiceImpl;
import com.ht.risk.activiti.service.ownerloan.RefuseResultService;
import com.ht.risk.activiti.vo.OwnerLoanModelResult;
import com.ht.risk.api.constant.activiti.ActivitiConstants;
import com.ht.risk.api.model.activiti.RpcActExcuteTask;
import org.activiti.engine.delegate.DelegateExecution;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;

/**
 *  拒绝结果处理
 */
@Service("refuseResultService")
public class RefuseResultServiceImpl implements RefuseResultService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RefuseResultServiceImpl.class);

    @Resource
    private ActivitiConfigInterface activitiConfigInterface;

    @Resource
    private TopicSenderServiceImpl topicSenderService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        LOGGER.info("RefuseResultServiceImpl execute start");
        String modelType = String.valueOf(execution.getVariable(ActivitiConstants.PROC_MODEL_EXCUTE_TYPE_KEY));
        String errorMsgStr = String.valueOf(execution.getVariable(ActivitiConstants.PROC_EXCUTE_ERROR_MSG));
        String ruleMsgStr = String.valueOf(execution.getVariable(ActivitiConstants.PROC_EXCUTE_HIT_RULE_MSG));
        String msg = String.valueOf(execution.getVariable(ActivitiConstants.PROC_EXCUTE_MSG));
        String taskIdStr = String.valueOf(execution.getVariable(ActivitiConstants.PROC_TASK_ID_VAR_KEY));
        OwnerLoanModelResult ownerResult = (OwnerLoanModelResult)execution.getVariable(ActivitiConstants.PROC_OWNER_LOAN_RESULT_CODE);
        // 数据组装，人工和自动设置
        //ownerResult.setAuditType("2");
        ownerResult.setAuditType("0");
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
            topicSenderService.sendOwnerLoan(JSON.toJSONString(ownerResult));
        }
        LOGGER.info("RefuseResultServiceImpl execute end");
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
}
