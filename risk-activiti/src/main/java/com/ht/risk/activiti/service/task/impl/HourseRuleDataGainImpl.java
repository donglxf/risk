package com.ht.risk.activiti.service.task.impl;

import com.ht.risk.activiti.service.task.HourseRuleDataGain;
import com.ht.risk.api.constant.activiti.ActivitiConstants;
import com.ht.risk.common.comenum.RuleCallTypeEnum;
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
        Object dataObj = execution.getVariable(ActivitiConstants.PROC_MODEL_DATA_kEY);
        Map dataMap = null;
        if(dataObj != null){
            dataMap = (Map)dataObj;
        }else{
            dataMap = gernerateModelData();
        }
        execution.setVariable(ActivitiConstants.DROOLS_VARIABLE_NAME,dataMap);
        LOGGER.error("HourseRuleDataGainImpl execute method excute end...");
    }
    // TODO 待完善
    private Map gernerateModelData(){
        Map map=new HashMap();
        map.put("regicode","广东鸿特信息咨询有限公司");//工商注册号或企 业全称或组织机 构代码
        map.put("keytype","2"); // 查询类型
        map.put("borrowerInfo_borrowerAge","45"); // 借款人年龄
        map.put("borrower_method","20"); //借款人借款期限
        map.put("borrowerInfo_registerCity","海外"); //借款人户籍
        map.put("borrowerLawsuit_borrowerSuretyCreditBad","有"); //担保人如有失信
        map.put("houseInfo_reserveType","别墅");//房产类型
        map.put("houseInfo_housePropertyArea","500");  //房产面积
        map.put("houseInfo_houseOwnerRegister","台湾"); //房产权属人籍贯
        map.put("houseInfo_houseAge","20"); //楼龄
        map.put("houseInfo_houseMortgageRate","40"); //房产抵押率抵押率
        map.put("borrowerAmountInfo_expireDateAmount",""); //原业务额度
        map.put("borrowerCreditInfo_overdueInfo","0");// 逾期记录
        map.put("houseInfo_houseCityType","cityTypeA"); //城市类型
        map.put("houseInfo_firstLoans","2000000"); // 一押金额
        map.put("houseInfo_secondLoans","1000000"); // 二押金额
        return map;
    }
}
