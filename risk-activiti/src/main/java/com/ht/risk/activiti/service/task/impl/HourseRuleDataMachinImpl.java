package com.ht.risk.activiti.service.task.impl;

import com.ht.risk.activiti.service.task.HourseRuleDataGain;
import com.ht.risk.activiti.service.task.HourseRuleDataMachin;
import org.activiti.engine.delegate.DelegateExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 数据加工接口
 */
@Service("hourseRuleDataMachin")
public class HourseRuleDataMachinImpl implements HourseRuleDataMachin {

    protected static final Logger LOGGER = LoggerFactory.getLogger(HourseRuleDataMachinImpl.class);

    /**
     * 数据加工
     * @param execution
     * @throws Exception
     */
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        LOGGER.error("HourseRuleDataMachinImpl execute method excute start...");
        execution.setVariable("flag","001");
        LOGGER.error("HourseRuleDataMachinImpl execute method excute end...");
    }
}
