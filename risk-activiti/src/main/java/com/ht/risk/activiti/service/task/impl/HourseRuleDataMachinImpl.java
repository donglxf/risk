package com.ht.risk.activiti.service.task.impl;

import com.ht.risk.activiti.service.task.HourseRuleDataGain;
import com.ht.risk.common.util.ObjectUtils;
import com.ht.risk.common.util.ProvinceUtil;
import org.activiti.engine.delegate.DelegateExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

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
        Map map = (Map) execution.getVariable("variableMap");
        if (ObjectUtils.isEmpty(map)) {
            map = new HashMap();
        }
        String age = (String) map.get("borrowerInfo_borrowerAge"); // 借款人年龄
        String method = (String) map.get(""); // 借款人借款期限
        map.put("borrowerInfo_borrowerAgePlusLoanterm", ProvinceUtil.getborrowAge(Integer.parseInt(age), Integer.parseInt(method)));


        LOGGER.error("HourseRuleDataMachinImpl execute method excute end...");
    }
}
