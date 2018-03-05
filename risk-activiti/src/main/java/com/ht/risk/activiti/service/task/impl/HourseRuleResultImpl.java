package com.ht.risk.activiti.service.task.impl;

import com.alibaba.fastjson.JSON;
import com.ht.risk.activiti.rpc.ActivitiConfigInterface;
import com.ht.risk.activiti.rpc.RuleServiceInterface;
import com.ht.risk.activiti.service.impl.TopicSenderServiceImpl;
import com.ht.risk.activiti.service.task.HourseRuleResult;
import com.ht.risk.api.constant.activiti.ActivitiConstants;
import com.ht.risk.api.model.activiti.ModelExcuteResult;
import com.ht.risk.api.model.activiti.RpcActExcuteTask;
import com.ht.risk.api.model.activiti.RuleExcuteDetail;
import com.ht.risk.api.model.rule.RpcRuleDetail;
import com.ht.risk.api.model.rule.RpcRuleHisVersion;
import com.ht.risk.api.model.rule.RpcRuleHisVersionParamter;
import com.ht.risk.common.result.Result;
import org.activiti.engine.delegate.DelegateExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service("hourseRuleResult")
public class HourseRuleResultImpl implements HourseRuleResult {

    protected static final Logger LOGGER = LoggerFactory.getLogger(HourseRuleResultImpl.class);

    @Resource
    private TopicSenderServiceImpl topicSenderService;

    @Resource
    private ActivitiConfigInterface activitiConfigInterface;
    @Resource
    private RuleServiceInterface ruleServiceInterface;

    /**
     *
     * @param execution
     * @throws Exception
     */
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        LOGGER.info("HourseRuleResultImpl execute method excute start...");
        String msg = String.valueOf(execution.getVariable(ActivitiConstants.PROC_EXCUTE_MSG));
        ModelExcuteResult modelResult = new ModelExcuteResult();
        // 获取执行结果
        Map<String,Object> result = execution.getVariables();
        Set<String> nameKey = result.keySet();
        String name = null;
        List<RuleExcuteDetail> returnDetail = null;
        List<RuleExcuteDetail> details = new ArrayList<RuleExcuteDetail>();
        Map<String,List<RuleExcuteDetail>> resultMap = new HashMap<String,List<RuleExcuteDetail>>();
        for(Iterator<String> iterator = nameKey.iterator(); iterator.hasNext();){
            name = iterator.next();
            if(name.startsWith(ActivitiConstants.SENCE_EXCUTE_RESULT_VAR) && result.get(name) != null){
                returnDetail = (List<RuleExcuteDetail>)result.get(name);
                resultMap.put(name,returnDetail);
                details.addAll(returnDetail);
            }
        }
        RuleExcuteDetail detail = null;
        if(details.size()>0){
            for(Iterator<RuleExcuteDetail> iterator = details.iterator(); iterator.hasNext();){
                detail = iterator.next();
                getRulesDesc(detail);
            }
            modelResult.setRuleResultList(resultMap);
            modelResult.setCode("0");
            modelResult.setMsg("模型执行正常");
            modelResult.setProcInstId(execution.getProcessInstanceId());
            String taskIdStr = String.valueOf(execution.getVariable(ActivitiConstants.PROC_TASK_ID_VAR_KEY));
            Long taskId =taskIdStr != null ?Long.parseLong(taskIdStr):null;
            modelResult.setTaskId(taskId);
            modelResult.setProcMsg(msg);
            // MQ发送消息
            topicSenderService.send(JSON.toJSONString(modelResult));
            //更新任务状态
            updateTask(modelResult,taskId,execution.getProcessInstanceId());
        }
        LOGGER.info("HourseRuleResultImpl execute method excute end...");
    }
    // 更新任务信息
    private void updateTask(ModelExcuteResult modelResult,Long taskId,String procInstId){
        RpcActExcuteTask task = new RpcActExcuteTask();
        task.setStatus(ActivitiConstants.PROC_STATUS_SUCCESS);
        task.setUpdateTime(new Date(System.currentTimeMillis()));
        task.setId(taskId);
        task.setOutParamter(JSON.toJSONString(modelResult));
        task.setProcInstId(procInstId);
        activitiConfigInterface.updateTask(task);
    }

    // 获取策略表所有命中规则的规则描述
    private void getRulesDesc(RuleExcuteDetail detail){
        if(detail != null && detail.getRuleList() != null && detail.getRuleList().size()>0){
            LOGGER.info("HourseRuleResultImpl execute method excute start..."+ JSON.toJSONString(detail));
            RpcRuleHisVersionParamter vo = new RpcRuleHisVersionParamter();
            vo.setVersionId(Long.parseLong(detail.getSenceVersionId()));
            vo.settRuleName(detail.getRuleList());
            Result<List<RpcRuleHisVersion>> ruleDescResult = ruleServiceInterface.getHisVersionListByVidName(vo);
            if(ruleDescResult != null && ruleDescResult.getCode() == 0 && ruleDescResult.getData() != null){
                List<RpcRuleHisVersion>  hisVersions = ruleDescResult.getData();
                if(hisVersions != null){
                    List<String> ruleList = new ArrayList<>();
                    List<String> ruleNameList = new ArrayList<>();
                    RpcRuleHisVersion version = null;
                    RpcRuleDetail rpcRuleDetail = null;
                    List<RpcRuleDetail> details = new ArrayList<>();
                    for(Iterator<RpcRuleHisVersion> vIterator = hisVersions.iterator();vIterator.hasNext();){
                        version = vIterator.next();
                        rpcRuleDetail = new RpcRuleDetail();
                        rpcRuleDetail.setRuleCode(version.getRuleName());
                        rpcRuleDetail.setRuleDesc(version.getRuleDesc());
                        details.add(rpcRuleDetail);
                    }
                    detail.setRuleList(null);
                    detail.setRuleDetails(details);
                }
            }
        }
    }
}
