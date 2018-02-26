package com.ht.risk.activiti.service.task.impl;

import com.ht.risk.activiti.service.task.HourseRuleDataGain;
import com.ht.risk.activiti.service.task.HourseRuleResult;
import org.activiti.engine.delegate.DelegateExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("hourseRuleResult")
public class HourseRuleResultImpl implements HourseRuleResult {

    protected static final Logger LOGGER = LoggerFactory.getLogger(HourseRuleResultImpl.class);

    /**
     *
     * @param execution
     * @throws Exception
     */
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        LOGGER.error("HourseRuleResultImpl execute method excute start...");
        LOGGER.error("HourseRuleResultImpl execute method excute end...");
    }
}
