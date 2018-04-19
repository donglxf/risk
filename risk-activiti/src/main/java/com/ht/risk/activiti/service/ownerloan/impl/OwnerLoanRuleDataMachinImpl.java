package com.ht.risk.activiti.service.ownerloan.impl;

import com.ht.risk.activiti.service.ownerloan.OwnerLoanRuleDataMachin;
import com.ht.risk.activiti.service.task.impl.HourseRuleDataMachinImpl;
import com.ht.risk.api.constant.activiti.ActivitiConstants;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  数据加工接口
 */
@Service("ownerLoanRuleDataMachin")
public class OwnerLoanRuleDataMachinImpl implements OwnerLoanRuleDataMachin {

    private static final Logger LOGGER = LoggerFactory.getLogger(HourseRuleDataMachinImpl.class);

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        LOGGER.info("OwnerLoanRuleDataMachinImpl execute method excute start...");
        execution.setVariable(ActivitiConstants.PROC_EXCUTE_ERROR_MSG,"");
        execution.setVariable(ActivitiConstants.PROC_EXCUTE_HIT_RULE_MSG,"");
        execution.setVariable(ActivitiConstants.PROC_EXCUTE_MSG,"");
        StringBuffer msg = new StringBuffer("");
        Object dataObj = execution.getVariable(ActivitiConstants.PROC_MODEL_DATA_KEY);
        // 设置开始时间
        execution.setVariable(ActivitiConstants.PROC_START_CURRENT_TIME,System.currentTimeMillis());
        Map dataMap = null;
        if(dataObj == null){
            dataMap = new HashMap();
            msg.append("模型所需数据为空;");
        }else{
            dataMap = (Map)dataObj;
        }
        // 数据校验
        String modelType = String.valueOf(execution.getVariable(ActivitiConstants.PROC_MODEL_EXCUTE_TYPE_KEY));
        if(ActivitiConstants.EXCUTE_TYPE_SERVICE.equals(modelType)){// 服务类型
            // 年龄和借款期限处理
            msg.append(dataValidate(dataMap));
        }else{// 验证类型
            verficationDataMachin(dataMap,execution);
        }
        execution.setVariable(ActivitiConstants.PROC_EXCUTE_MSG,msg.toString());
        LOGGER.error("OwnerLoanRuleDataMachinImpl execute method excute end...");
    }

    /**
     * 数据验证数据加工
     * @param dataMap
     * @param execution
     * @return
     */
    private void verficationDataMachin(Map dataMap,DelegateExecution execution){
        Map<String,Object> houseInfos = (Map<String,Object>)dataMap.get(ActivitiConstants.DROOLS_VARIABLE_NAME+"hourse_hourseInfo");
        Map<String,Object> borrowers = ( Map<String,Object>)dataMap.get(ActivitiConstants.DROOLS_VARIABLE_NAME+"hourse_borrower");
        List<Map<String,Object>> houseInfoList = new ArrayList<Map<String,Object>>();
        houseInfoList.add(houseInfos);
        List<Map<String,Object>> borrowerList = new ArrayList<Map<String,Object>>();
        borrowerList.add(borrowers);
        execution.setVariable(ActivitiConstants.DROOLS_VARIABLE_NAME+"hourse_hourseInfo",houseInfoList);
        execution.setVariable(ActivitiConstants.DROOLS_VARIABLE_NAME+"hourse_borrower",borrowerList);
    }


    // 数据校验
    private StringBuffer  dataValidate(Map dataMap){
        // 获取流程变量
        StringBuffer msg = new StringBuffer("");
        // 数据校验
        String businessId = String.valueOf(dataMap.get("businessId"));
        if(StringUtils.isEmpty(businessId)){
            msg.append("合同单号、是否标准件、申请期限必填信息为空;");
        }
        // 主借款人
        Object borrowerObj = dataMap.get("borrorwerInfo");
        if(borrowerObj == null){
            msg.append("借款人信息为空;");
        }
        // 房产信息
        Object houseInfoObj = dataMap.get("houseInfo");
        if(houseInfoObj == null){
            msg.append("房产信息为空;");
        }
        return msg;
    }
}
