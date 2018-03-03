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
        Object modelObj = execution.getVariable(ActivitiConstants.PROC_MODEL_DATA_KEY);
        List<Map<String,Object>> droolsData = null;
        Map map = null;
        if(modelObj != null){
            map = (Map)modelObj;
        }else{
            map = new HashMap();
        }
        String msg = String.valueOf(execution.getVariable(ActivitiConstants.PROC_EXCUTE_MSG));
        // 年龄和借款期限处理
        try {
            String businessId = String.valueOf(map.get("businessId"));
            String businessType = String.valueOf(map.get("businessType"));
            String months = String.valueOf(map.get("months"));

            Object borrowerObj = map.get("borrowerInfo");
            Object houseInfoObj = map.get("houseInfo");
            Object guaranteeInfoObj = map.get("guaranteeInfo");

            List<Map<String,Object>> houseInfos = ( List<Map<String,Object>>)houseInfoObj;
            List<Map<String,Object>> guaranteeInfos = ( List<Map<String,Object>>)guaranteeInfoObj;
            List<Map<String,Object>> borrowers = ( List<Map<String,Object>>)borrowerObj;

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
                    msg += "老赖接口异常;";
                }
                else{
                    guaranteeMap.put("guaranteeInfo_negative","否");
                }
                String negativeStr = getNegativeSearch(identityCard,name);
                if("0".equals(negativeStr)){
                    guaranteeMap.put("guaranteeInfo_oldLai","有");
                }else if("3".equals(oldLaiStr)){
                    msg += "天行数科接口异常;";
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
            for(int i= 0;i<houseInfos.size();i++){
                houseInfoMap =houseInfos.get(i);
                String openTimeStr = String.valueOf(houseInfoMap.get("houseInfo_openTime"));
                houseInfoMap.put("houseInfo_hourseAge",caculateHourseAge(openTimeStr));
                droolsData.add(houseInfos.get(i));
            }
            // 房产决策变量
            execution.setVariable(ActivitiConstants.DROOLS_VARIABLE_NAME+"hourse_hourseInfo",droolsData);
        }catch(Exception e){
            msg += "数据加工异常；";
            LOGGER.error("年龄和借款期限处理异常，"+e.getMessage());
        }




        /*// 装修情况处理
        String decoration= (String) map.get("houseInfo_decorationStatus");
        if("已装修".equals(decoration)){

        }else if("装修中".equals(decoration)){

        }else if("毛坯".equals(decoration)){
            decoration="roughcast";
        }
        map.put("houseInfo_decorationStatus",decoration);*/

        /*try {
            // 万达接口调用
            WDEnterpriseDetailReqDto wdReq = new WDEnterpriseDetailReqDto();
            wdReq.setRegicode((String) map.get("regicode"));
            wdReq.setKeyType((String) map.get("keytype"));
            Result<WDEnterpriseDetailRespDtoOut> result = eipServiceInterface.getZhengxinWanda(wdReq);
            JSONObject str = JSONObject.parseObject(JSON.toJSONString(result));
            if (getResultSuccess(str)) { // 执行成功
                String entstatus = str.getJSONObject("data").getJSONObject("basic").getString("entstatus");
                if (EntstatusEnum.closeUp.getName().equals(entstatus) || EntstatusEnum.liquidate.getName().equals(entstatus)
                        || EntstatusEnum.outOfBusiness.getName().equals(entstatus)) {
                    map.put("borrowerInfo_borrowerCompanyStatus", entstatus);
                }
            }

        } catch (Exception e) {
            LOGGER.error("年龄和借款期限处理异常，" + e.getMessage());
            msg += "万达接口调用异常；";
        }*/
        execution.setVariable("flag",0);
        execution.setVariable(ActivitiConstants.PROC_EXCUTE_MSG,msg.toString());
        LOGGER.error("HourseRuleDataMachinImpl execute method excute end...");
    }

    public boolean getResultSuccess(JSONObject str){
        return WLInterfaceReturnStatusEnum.success.getValue().equals(str.getString("returnCode"));
    }

    private int caculateHourseAge(String openTimeStr){
        try{
            Date openTime = DateUtil.getDate(openTimeStr,DateUtil.DATE_FORMAT);
            int mouth = DateUtil.getDiffMonths(new Date(System.currentTimeMillis()),openTime);
            return mouth/12;
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
            return   "老赖接口异常";
        }
    }
}
