package com.ht.risk.activiti.service.task.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ht.risk.activiti.rpc.EipServiceInterface;
import com.ht.risk.activiti.service.task.HourseRuleDataGain;
import com.ht.risk.api.comment.EntstatusEnum;
import com.ht.risk.api.comment.WLInterfaceReturnStatusEnum;
import com.ht.risk.api.constant.activiti.ActivitiConstants;
import com.ht.risk.api.model.eip.NegativeSearchDtoIn;
import com.ht.risk.api.model.eip.NegativeSearchDtoOut;
import com.ht.risk.api.model.eip.OldLaiIn;
import com.ht.risk.api.model.eip.OldLaiOut;
import com.ht.risk.api.model.eip.wanda.WDEnterpriseDetailReqDto;
import com.ht.risk.api.model.eip.wanda.WDEnterpriseDetailRespDtoOut;
import com.ht.risk.common.result.Result;
import com.ht.risk.common.util.DateUtil;
import com.ht.risk.common.util.ObjectUtils;
import com.ht.risk.common.util.ProvinceUtil;
import org.activiti.engine.delegate.DelegateExecution;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service("hourseRuleDataMachin")
public class HourseRuleDataMachinImpl implements HourseRuleDataGain {

    protected static final Logger LOGGER = LoggerFactory.getLogger(HourseRuleDataMachinImpl.class);

    @Autowired
    private EipServiceInterface eipServiceInterface;

    /**
     * @param execution
     * @throws Exception
     */
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        LOGGER.info("HourseRuleDataMachinImpl execute method excute start...");
        StringBuffer msg = new StringBuffer("");
        Object dataObj = execution.getVariable(ActivitiConstants.PROC_MODEL_DATA_KEY);
        Map dataMap = null;
        if(dataObj == null){
            dataMap = new HashMap();
            msg.append("模型所需数据为空;");
        }else{
            dataMap = (Map)dataObj;
        }
        // 数据校验
        msg.append(dataValidate(dataMap));
        String modelType = String.valueOf(execution.getVariable(ActivitiConstants.PROC_MODEL_EXCUTE_TYPE_KEY));
        if(ActivitiConstants.EXCUTE_TYPE_SERVICE.equals(modelType)){// 服务类型
            // 年龄和借款期限处理
            msg.append(dataMachin(dataMap,execution));
        }else{// 验证类型
            msg.append(verficationDataMachin(dataMap,execution));
        }
        execution.setVariable(ActivitiConstants.PROC_EXCUTE_MSG,msg.toString());
        LOGGER.error("HourseRuleDataMachinImpl execute method excute end...");
    }
    // 根据房产位置获取决策版本
    private int  getRuleVersionFlag(String address){
        if(StringUtils.isEmpty(address)){
            return 0;
        }
        if(address.contains("北京")){
            return 1;
        }
        if(address.contains("上海")){
            return 2;
        }
        if(address.contains("广州")){
            return 3;
        }
        if(address.contains("成都")){
            return 4;
        }
        if(address.contains("重庆")){
            return 5;
        }
        if(address.contains("杭州")){
            return 6;
        }
        if(address.contains("义乌")){
            return 7;
        }
        if(address.contains("苏州")){
            return 8;
        }
        if(address.contains("厦门")){
            return 9;
        }
        if(address.contains("武汉")){
            return 10;
        }
        return 0;
    }

    // 数据校验
    private StringBuffer  dataValidate(Map dataMap){
        // 获取流程变量
        StringBuffer msg = new StringBuffer("");
        // 数据校验
        String businessId = String.valueOf(dataMap.get("businessId"));
        String businessType = String.valueOf(dataMap.get("businessId"));
        String months = String.valueOf(dataMap.get("months"));
        if(StringUtils.isEmpty(businessId) || StringUtils.isEmpty(businessType)  || StringUtils.isEmpty(months) ){
            msg.append("合同单号、是否标准件、申请期限必填信息为空;");
        }
        Object borrowerObj = dataMap.get("borrowerInfo");
        if(borrowerObj == null){
            msg.append("借款人信息为空;");
        }
        Object houseInfoObj = dataMap.get("houseInfo");
        if(houseInfoObj == null){
            msg.append("房产信息为空;");
        }
        Object guaranteeInfoObj = dataMap.get("guaranteeInfo");
        if(guaranteeInfoObj == null){
            msg.append("担保信息为空;");
        }
        return msg;
    }
    // 数据处理
    private StringBuffer dataMachin(Map dataMap,DelegateExecution execution){
        StringBuffer msg = new StringBuffer("");
        List<Map<String,Object>> droolsData = null;
        try {
            String businessId = String.valueOf(dataMap.get("businessId"));
            String businessType = String.valueOf(dataMap.get("businessType"));
            String months = String.valueOf(dataMap.get("months"));
            execution.setVariable(ActivitiConstants.PROC_BUSINESS_KEY,businessId);
            // 借款人信息
            Object borrowerObj = dataMap.get("borrowerInfo");
            // 房产信息
            Object houseInfoObj = dataMap.get("houseInfo");
            // 担保人信息
            Object guaranteeInfoObj = dataMap.get("guaranteeInfo");
            List<Map<String,Object>> houseInfos = houseInfoObj !=null ? ( List<Map<String,Object>>)houseInfoObj:new ArrayList<Map<String,Object>>();
            List<Map<String,Object>> guaranteeInfos = guaranteeInfoObj !=null ? ( List<Map<String,Object>>)guaranteeInfoObj:new ArrayList<Map<String,Object>>();
            List<Map<String,Object>> borrowers = borrowerObj !=null ? ( List<Map<String,Object>>)borrowerObj:new ArrayList<Map<String,Object>>();
            Object ageObj = null;
            Object methodObj = null;
            droolsData = new ArrayList<Map<String,Object>>();
            // 借款人信息处理
            Map<String,Object> borrowerMap = null;
            for(int i= 0;i<borrowers.size();i++){
                borrowerMap =borrowers.get(i);
                ageObj = borrowerMap.get("borrowerInfo_borrowerAge");
                methodObj = borrowerMap.get("borrower_method");
                int age = ageObj != null ? Integer.parseInt(String.valueOf(ageObj)) : 0;
                int method = methodObj != null ? Integer.parseInt(String.valueOf(methodObj)) : 0;
                borrowerMap.put("borrowerInfo_borrowerAgePlusLoanterm", ProvinceUtil.getborrowAge(age, method));
                borrowerMap.put("business_id",businessId);
                borrowerMap.put("business_Type",businessType);
                borrowerMap.put("business_months",months);
                droolsData.add(borrowerMap);
            }
            // 借款人决策变量
            execution.setVariable(ActivitiConstants.DROOLS_VARIABLE_NAME+"hourse_borrower",droolsData);
            // 担保人信息处理
            droolsData = new ArrayList<Map<String,Object>>();
            Map<String,Object> guaranteeMap = null;
            for(int i= 0;i<guaranteeInfos.size();i++){
                guaranteeMap = guaranteeInfos.get(i);
                String identityCard = String.valueOf(guaranteeMap.get("guaranteeInfo_guaranteeIdentifyCard"));
                String name = String.valueOf(guaranteeMap.get("guaranteeInfo_guaranteeName"));
                String oldLaiStr = getOldLai(identityCard,name);
                if("0".equals(oldLaiStr)){
                    guaranteeMap.put("guaranteeInfo_negative","是");
                }else if("3".equals(oldLaiStr)){
                    msg.append("身份证：").append(identityCard).append(",老赖接口异常;");
                }
                else{
                    guaranteeMap.put("guaranteeInfo_negative","否");
                }
                String negativeStr = getNegativeSearch(identityCard,name);
                if("0".equals(negativeStr)){
                    guaranteeMap.put("guaranteeInfo_oldLai","有");
                }else if("3".equals(negativeStr)){
                    msg.append("身份证：").append(identityCard).append(",天行数科接口异常;");
                }
                else{
                    guaranteeMap.put("guaranteeInfo_oldLai","无");
                }
                droolsData.add(guaranteeMap);
            }
            // 担保人决策变量
            execution.setVariable(ActivitiConstants.DROOLS_VARIABLE_NAME+"hourse_guaranteeInfo",droolsData);
            // 房产信息处理
            droolsData = new ArrayList<Map<String,Object>>();
            Map<String,Object> houseInfoMap = null;
            String address = null;
            for(int i= 0;i<houseInfos.size();i++){
                houseInfoMap = houseInfos.get(i);
                address =  String.valueOf(houseInfoMap.get("houseInfo_HouseShi"));
                houseInfoMap =houseInfos.get(i);
                String openTimeStr = String.valueOf(houseInfoMap.get("houseInfo_openTime"));
                houseInfoMap.put("houseInfo_hourseAge",caculateHourseAge(openTimeStr));
                droolsData.add(houseInfos.get(i));
            }
            // 设置规则版本
            execution.setVariable("flag",getRuleVersionFlag(address));
            // 房产决策变量
            execution.setVariable(ActivitiConstants.DROOLS_VARIABLE_NAME+"hourse_hourseInfo",droolsData);
        }catch(Exception e){
            msg.append("数据加工异常；");
        }
        return msg;
    }

    private StringBuffer verficationDataMachin(Map dataMap,DelegateExecution execution){
        StringBuffer msg = new StringBuffer("");
        String address = null;
        Map<String,Object> houseInfos = (Map<String,Object>)dataMap.get(ActivitiConstants.DROOLS_VARIABLE_NAME+"hourse_hourseInfo");
        Map<String,Object> guaranteeInfos = ( Map<String,Object>)dataMap.get(ActivitiConstants.DROOLS_VARIABLE_NAME+"hourse_guaranteeInfo");
        Map<String,Object> borrowers = ( Map<String,Object>)dataMap.get(ActivitiConstants.DROOLS_VARIABLE_NAME+"hourse_borrower");
        if(houseInfos != null){
            address =  String.valueOf(houseInfos.get("houseInfo_HouseShi"));
        }
        List<Map<String,Object>> houseInfoList = new ArrayList<Map<String,Object>>();
        houseInfoList.add(houseInfos);
        List<Map<String,Object>> guaranteeInfoList = new ArrayList<Map<String,Object>>();
        guaranteeInfoList.add(guaranteeInfos);
        List<Map<String,Object>> borrowerList = new ArrayList<Map<String,Object>>();
        borrowerList.add(borrowers);
        execution.setVariable("flag",getRuleVersionFlag(address));
        execution.setVariable(ActivitiConstants.DROOLS_VARIABLE_NAME+"hourse_hourseInfo",houseInfoList);
        execution.setVariable(ActivitiConstants.DROOLS_VARIABLE_NAME+"hourse_guaranteeInfo",guaranteeInfoList);
        execution.setVariable(ActivitiConstants.DROOLS_VARIABLE_NAME+"hourse_borrower",borrowerList);
        return  msg;
    }

    public boolean getResultSuccess(JSONObject str){
        return WLInterfaceReturnStatusEnum.success.getValue().equals(str.getString("returnCode"));
    }

    private int caculateHourseAge(String openTimeStr){
        try{
            int year = Integer.parseInt(openTimeStr);
            int currentYear = DateUtil.getYear(new Date(System.currentTimeMillis()));
            return currentYear - year;
        }catch (Exception e){
            LOGGER.error("转换楼龄异常，openTimeStr："+openTimeStr);
        }
        return 0;
    }

    // 天行数科
    private String getNegativeSearch(String identityCard,String name){
        try {
            NegativeSearchDtoIn negative = new NegativeSearchDtoIn();
            negative.setIdentityCard(identityCard);
            negative.setRealName(name);
            Result<NegativeSearchDtoOut> neResult = eipServiceInterface.getNegativeSearch(negative);
            JSONObject str1 = JSONObject.parseObject(JSON.toJSONString(neResult));
            if(neResult != null && neResult.getData() != null && "1".equals(neResult.getData().getIsCrime())){
                return "0";
            }
            if(neResult == null || neResult.getCode() != 0){
                return   "3";
            }
            return  "1";
        } catch (Exception e) {
            LOGGER.error("天行数科接口异常，identityCard:"+identityCard+";name:"+name +e.getMessage());
            return   "3";
        }
    }

    // 老赖
    private String getOldLai(String identityCard,String name){
        try {
            OldLaiIn input = new OldLaiIn();
            input.setIdentityCard(identityCard);
            input.setRealName(name);
            com.ht.ussp.core.Result<OldLaiOut> neResult = eipServiceInterface.oldLai(input);
            if(neResult != null && neResult.getData() != null && StringUtils.isNotEmpty(neResult.getData().getGistId())){
                return "0";
            }
            if(neResult == null || !"0000".equals(neResult.getReturnCode())){
                return "3";
            }
            return  "1";
        } catch (Exception e) {
            LOGGER.error("老赖接口异常，identityCard:"+identityCard+";name:"+name +e.getMessage());
            return "3";
        }
    }
}
