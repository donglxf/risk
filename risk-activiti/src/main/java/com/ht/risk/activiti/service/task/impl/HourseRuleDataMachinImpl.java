package com.ht.risk.activiti.service.task.impl;

import com.ht.risk.activiti.service.task.HourseRuleDataGain;
import org.activiti.engine.delegate.DelegateExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("hourseRuleDataGain")
public class HourseRuleDataMachinImpl implements HourseRuleDataGain {

    protected static final Logger LOGGER = LoggerFactory.getLogger(HourseRuleDataMachinImpl.class);

    /**
     *
     * @param execution
     * @throws Exception
     */
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        LOGGER.error("HourseRuleDataMachinImpl execute method excute start...");
        LOGGER.error("HourseRuleDataMachinImpl execute method excute end...");
    }
}
