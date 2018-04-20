package com.ht.risk.activiti.service.ownerloan.impl;

import com.ht.risk.activiti.service.ownerloan.OwnerLoanRuleDataMachin;
import com.ht.risk.activiti.service.task.impl.HourseRuleDataMachinImpl;
import com.ht.risk.activiti.vo.OwnerLoanModelResult;
import com.ht.risk.activiti.vo.OwnerLoanRuleInfo;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(OwnerLoanRuleDataMachinImpl.class);

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

        Map borrowerMap = (Map)dataMap.get("borrorwerInfo");
        String identityCard = String.valueOf(borrowerMap.get("borrowerInfo_identifyCard"));
        String name= String.valueOf(borrowerMap.get("borrowerInfo_customerName"));
        String mobilePhone = String.valueOf(borrowerMap.get("borrowerInfo_phoneNo"));
        String businessId = String.valueOf(dataMap.get("businessId"));

        borrowerMap.put("identifyCard",identityCard);
        borrowerMap.put("customerName",name);
        borrowerMap.put("phoneNo",mobilePhone);

        OwnerLoanModelResult result = initResultData(identityCard,name,mobilePhone);
        result.setBusinessKey(businessId);
        execution.setVariable(ActivitiConstants.PROC_OWNER_LOAN_RESULT_CODE,result);
        LOGGER.error("OwnerLoanRuleDataMachinImpl execute method excute end...");
    }

    /**
     * 数据验证数据加工
     * @param dataMap
     * @param execution
     * @return
     */
    private void verficationDataMachin(Map dataMap,DelegateExecution execution){
        Map<String,String> borrowerMap = new HashMap<String,String>();
        borrowerMap.put("identifyCard","110101196811041047");
        borrowerMap.put("customerName","何瑞芬");
        borrowerMap.put("phoneNo","13809885602");
        dataMap.put("borrorwerInfo",borrowerMap);
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

    private OwnerLoanModelResult initResultData(String identityCard,String realName,String mobilePhone){
        String[] functionAry = {"10015001","10013001","10017002","10031004","10013003","10017001","10042002","10041002","10011002","10006003","10041001"};
        String[] functionNameAry = {"白骑士风险黑名单","网贷黑名单","考拉手机号码认证","天行数科新","自有黑名单","考拉黑名单","商汤身份验证","微众法院信息","前海征信","百融多次申请","个人分类统计"};
        OwnerLoanModelResult result = new OwnerLoanModelResult();
        Map<String,OwnerLoanRuleInfo> interInfo = new HashMap<String,OwnerLoanRuleInfo>();
        for(int i=0;i< functionAry.length;i++){
            OwnerLoanRuleInfo ruleInfo = new OwnerLoanRuleInfo(identityCard,realName,mobilePhone, functionAry[i], functionNameAry[i], functionNameAry[i] );
            interInfo.put(functionAry[i],ruleInfo);
        }
        result.setInterInfo(interInfo);
        return result;
    }
}
