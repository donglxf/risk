package com.ht.risk.activiti.service.task.impl;

import com.alibaba.fastjson.JSON;
import com.ht.risk.activiti.service.impl.TopicSenderServiceImpl;
import com.ht.risk.activiti.service.task.HourseRuleResult;
import com.ht.risk.api.constant.activiti.ActivitiConstants;
import com.ht.risk.api.model.activiti.ModelExcuteResult;
import com.ht.risk.api.model.activiti.RuleExcuteDetail;
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

    /**
     *
     * @param execution
     * @throws Exception
     */
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        LOGGER.info("HourseRuleResultImpl execute method excute start...");
        ModelExcuteResult modelResult = new ModelExcuteResult();
        // 获取执行结果
        Map<String,Object> result = execution.getVariables();
        Set<String> nameKey = result.keySet();
        String name = null;
        RuleExcuteDetail detail = null;
        List<RuleExcuteDetail> details = new ArrayList<RuleExcuteDetail>();
        for(Iterator<String> iterator = nameKey.iterator(); iterator.hasNext();){
            name = iterator.next();
            if(name.startsWith(ActivitiConstants.SENCE_EXCUTE_RESULT_VAR) && result.get(name) != null){
                detail = (RuleExcuteDetail)result.get(name);
                details.add(detail);
            }
        }
        if(details.size()>0){
            for(Iterator<RuleExcuteDetail> iterator = details.iterator(); iterator.hasNext();){
                detail = iterator.next();
                if("0".equals(detail.getCode())){
                    LOGGER.info("HourseRuleResultImpl execute method excute start..."+ JSON.toJSONString(detail));
                    // TODO 查询规则明星描述
                }
            }
            modelResult.setRuleResultList(details);
            modelResult.setCode("0");
            modelResult.setMsg("模型执行正常");
            modelResult.setProcInstId(execution.getProcessInstanceId());
            String taskId = String.valueOf(execution.getVariable(ActivitiConstants.PROC_TASK_ID_CONSTANTS));
            modelResult.setTaskId(taskId != null ?Long.parseLong(taskId):null);
            topicSenderService.send(JSON.toJSONString(modelResult));
        }
        LOGGER.info("HourseRuleResultImpl execute method excute end...");
    }
}
