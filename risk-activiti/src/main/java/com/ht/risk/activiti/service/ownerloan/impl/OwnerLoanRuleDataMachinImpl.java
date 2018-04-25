package com.ht.risk.activiti.service.ownerloan.impl;

import com.ht.risk.activiti.service.ownerloan.OwnerLoanRuleDataMachin;
import com.ht.risk.activiti.service.task.impl.HourseRuleDataMachinImpl;
import com.ht.risk.activiti.vo.OwnerLoanModelResult;
import com.ht.risk.activiti.vo.OwnerLoanRuleInfo;
import com.ht.risk.api.constant.activiti.ActivitiConstants;
import com.ht.risk.api.enums.RuleHitEnum;
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
            // 数据校验失败
            if(!dataValidate(dataMap)){
                execution.setVariable("flag",RuleHitEnum.HIT.getCode());
                OwnerLoanModelResult result = new OwnerLoanModelResult();
                result.setErrorMsg("数据校验不通过;");
                LOGGER.info("OwnerLoanRuleDataMachinImpl execute method excute end...");
                execution.setVariable(ActivitiConstants.PROC_OWNER_LOAN_RESULT_CODE,result);
                return;
            }
        }else{// 验证类型
            verficationDataMachin(dataMap,execution);
        }
        dataMachin(dataMap,execution);

        Map borrowerMap = (Map)dataMap.get("borrowerInfo");
        String identityCard = String.valueOf(borrowerMap.get("borrowerInfo_identifyCard"));
        String name= String.valueOf(borrowerMap.get("borrowerInfo_customerName"));
        String mobilePhone = String.valueOf(borrowerMap.get("borrowerInfo_phoneNo"));
        String businessId = String.valueOf(dataMap.get("businessId"));
        OwnerLoanModelResult result = initResultData(identityCard,name,mobilePhone);
        result.setBusinessKey(businessId);
        execution.setVariable(ActivitiConstants.PROC_OWNER_LOAN_RESULT_CODE,result);
        execution.setVariable("flag",RuleHitEnum.UNHIT.getCode());
        LOGGER.info("OwnerLoanRuleDataMachinImpl execute method excute end...");
    }

    /**
     * 数据验证数据加工
     * @param dataMap
     * @param execution
     * @return
     */
    private void verficationDataMachin(Map dataMap,DelegateExecution execution){
        Map<String,String> borrowerMap = new HashMap<String,String>();
        borrowerMap.put("borrowerInfo_identifyCard","110101196811041047");
        borrowerMap.put("borrowerInfo_customerName","何瑞芬");
        borrowerMap.put("borrowerInfo_phoneNo","13809885602");
        borrowerMap.put("businessId","TXDDEMO001");
        dataMap.put("borrowerInfo",borrowerMap);
    }


    // 数据校验
    private boolean  dataValidate(Map dataMap){
        boolean flag = true;
        try {
            // 获取流程变量
            StringBuffer msg = new StringBuffer("");
            // 数据校验
            String businessId = String.valueOf(dataMap.get("businessId"));
            if (StringUtils.isEmpty(businessId)) {
                LOGGER.error("businessId 为空");
                return false;
            }
            // 主借款人
            Object borrowerObj = dataMap.get("borrowerInfo");
            if (borrowerObj == null) {
                LOGGER.error("borrowerInfo节点 为空");
                return false;
            }
            Map borrowerMap = (Map) borrowerObj;
            Object identityCard = borrowerMap.get("borrowerInfo_identifyCard");
            Object name = borrowerMap.get("borrowerInfo_customerName");
            Object mobilePhone = borrowerMap.get("borrowerInfo_phoneNo");
            if (identityCard == null || name == null || mobilePhone == null){
                LOGGER.error("身份证，姓名，电话号码节点 为空");
                return false;
            }
        }catch (Exception e){
            LOGGER.error("数据校验异常",e);
            return false;
        }
        return true;
    }


    // 数据处理
    private void dataMachin(Map dataMap,DelegateExecution execution){
        Object borrowerObj = dataMap.get("borrowerInfo");
        Map borrowerMap = (Map)borrowerObj;
        String identityCard = String.valueOf(borrowerMap.get("borrowerInfo_identifyCard"));
        String name = String.valueOf(borrowerMap.get("borrowerInfo_customerName"));
        String mobilePhone = String.valueOf(borrowerMap.get("borrowerInfo_phoneNo"));
        String accountProvince = String.valueOf(borrowerMap.get("borrowerInfo_accountProvince"));
        String borrowerAge = String.valueOf(borrowerMap.get("borrowerInfo_borrowerAge"));
        List<Map<String,Object>> droolsData = new ArrayList<Map<String,Object>>();
        Map<String,Object> guaranteeMap = new HashMap<String,Object>();
        guaranteeMap.put("ownerLoanBorrower_ownerLoanAccountProvince",accountProvince);
        guaranteeMap.put("ownerLoanBorrower_ownerLoanBorrowerAge",borrowerAge);
        droolsData.add(guaranteeMap);
        borrowerMap.put("identifyCard",identityCard);
        borrowerMap.put("customerName",name);
        borrowerMap.put("phoneNo",mobilePhone);
        execution.setVariable(ActivitiConstants.DROOLS_VARIABLE_NAME+"ownerLoanAdmittance",droolsData);
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
