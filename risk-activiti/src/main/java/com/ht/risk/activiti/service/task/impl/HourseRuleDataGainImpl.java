package com.ht.risk.activiti.service.task.impl;

import com.ht.risk.activiti.service.task.HourseRuleDataGain;
import org.activiti.engine.delegate.DelegateExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service("hourseRuleDataGain")
public class HourseRuleDataGainImpl implements HourseRuleDataGain {

    protected static final Logger LOGGER = LoggerFactory.getLogger(HourseRuleDataGainImpl.class);

    /**
     *
     * @param execution
     * @throws Exception
     */
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        LOGGER.error("HourseRuleDataGainImpl execute method excute start...");
        Map map=new HashMap();
        map.put("regicode","广东鸿特信息咨询有限公司");//工商注册号或企 业全称或组织机 构代码
        map.put("keytype","2"); // 查询类型
        map.put("borrowerInfo_borrowerAge","45"); // 借款人年龄
        map.put("borrower_method","20"); //借款人借款期限
        map.put("borrowerInfo_registerCity","海外"); //借款人户籍
        map.put("houseInfo_decorationStatus",""); //
        map.put("houseInfo_reserveType","别墅");//房产类型
        map.put("houseInfo_housePropertyArea","500");  //房产面积
        map.put("","");
        map.put("","");
        map.put("","");
        map.put("","");
        map.put("","");
        map.put("","");
        map.put("","");
        map.put("","");
        map.put("","");
        map.put("","");
        map.put("","");
        map.put("","");
        execution.setVariable("variableMap",map);
        LOGGER.error("HourseRuleDataGainImpl execute method excute end...");
    }
}
