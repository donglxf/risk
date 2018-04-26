package com.ht.risk.activiti.service.task.impl;

import com.ht.risk.activiti.service.task.HourseRuleResult;
import com.ht.risk.activiti.service.task.HourseUnVersion;
import com.ht.risk.api.model.drools.RuleExcuteResult;
import org.activiti.engine.delegate.DelegateExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("hourseUnVersion")
public class HourseUnVersionImpl implements HourseUnVersion {

    protected static final Logger LOGGER = LoggerFactory.getLogger(HourseUnVersionImpl.class);

    /**
     *
     * @param execution
     * @throws Exception
     */
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        LOGGER.info("HourseUnVersionImpl execute method excute start...");
        RuleExcuteResult result = new RuleExcuteResult();
        // 执行成功
        //execution.setVariable(senceCode+"-"+version,result);
        LOGGER.info("HourseUnVersionImpl execute method excute end...");
    }
}
