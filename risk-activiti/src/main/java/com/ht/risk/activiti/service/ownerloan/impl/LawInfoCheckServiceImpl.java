package com.ht.risk.activiti.service.ownerloan.impl;

import com.alibaba.fastjson.JSON;
import com.ht.risk.activiti.rpc.EipServiceInterface;
import com.ht.risk.activiti.service.ownerloan.LawInfoCheckService;
import com.ht.risk.activiti.vo.OwnerLoanModelResult;
import com.ht.risk.activiti.vo.OwnerLoanRuleInfo;
import com.ht.risk.api.constant.activiti.ActivitiConstants;
import com.ht.risk.api.enums.RuleHitEnum;
import com.ht.risk.api.model.eip.*;
import com.ht.risk.common.util.DateUtil;
import com.ht.ussp.core.Result;
import com.ht.ussp.core.ReturnCodeEnum;
import org.activiti.engine.delegate.DelegateExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * 法院信息，犯罪信息
 */
@Service("lawInfoCheckService")
public class LawInfoCheckServiceImpl implements LawInfoCheckService {

    private static final int MAX_SIZE = 3;
    private static final Logger LOGGER = LoggerFactory.getLogger(LawInfoCheckServiceImpl.class);
    private static final String WEIZHONG_FUNCIONCODE = "10041002";
    private static final String PERSION_FUNCIONCODE = "10041001";
    private static final String CRIME_FUNCIONCODE = "10031004";

    @Autowired
    private EipServiceInterface eipServiceInterface;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        LOGGER.info("LawInfoCheckServiceImpl excute start");
        OwnerLoanModelResult ownerResult = (OwnerLoanModelResult)execution.getVariable(ActivitiConstants.PROC_OWNER_LOAN_RESULT_CODE);
        Map dataMap  = (Map)execution.getVariable(ActivitiConstants.PROC_MODEL_DATA_KEY);
        Map borrowerMap = (Map)dataMap.get("borrowerInfo");
        String identityCard = String.valueOf(borrowerMap.get("identifyCard"));
        String name= String.valueOf(borrowerMap.get("customerName"));
        String mobilePhone = String.valueOf(borrowerMap.get("phoneNo"));
        String flag = RuleHitEnum.UNHIT.getCode();;//命中标识： 1 沒有命中，2 命中
        // 汇法网个人分类信息接口
        OwnerLoanRuleInfo persionRuleInfo = ownerResult.getInterInfo().get(PERSION_FUNCIONCODE);
        persionRuleInfo.setCreateTime(DateUtil.formatDate(DateUtil.SIMPLE_TIME_FORMAT,new Date()));
        long startTime = System.currentTimeMillis();
        Result<LawxpPersonClassifyDtoOut> persionResult = callPersonClassify(identityCard,name,mobilePhone);
        persionRuleInfo.setCall_second((System.currentTimeMillis()-startTime)/1000);
        persionRuleInfo.setCallEndtime(DateUtil.formatDate(DateUtil.SIMPLE_TIME_FORMAT,new Date()));
        if(persionResult == null){
            persionRuleInfo.setInvokeSuccess(false);
            persionRuleInfo.setInterfaceResultCodeRemark("汇法网个人分类信息接口调用失败");
            persionRuleInfo.setTsTarget(false);
        }
        if(persionResult != null && ReturnCodeEnum.SUCCESS.getReturnCode().equals(persionResult.getReturnCode())){
            persionRuleInfo.setInvokeSuccess(true);
            persionRuleInfo.setResultJson(JSON.toJSONString(persionResult.getData()));
            //案例条数，失信条数，执行条数，税务条数，催欠条数，网贷条数
            if(persionResult.getData() != null && (persionResult.getData().getAnli()>0 || persionResult.getData().getCuiqiannum()>0 || persionResult.getData().getShixinnum()>0||
                    persionResult.getData().getShuiwunum() >0 || persionResult.getData().getWangdainum() >0 || persionResult.getData().getZhixingnum()>0) ){
                persionRuleInfo.setInterfaceResultCodeRemark("命中汇法网个人分类信息");
                persionRuleInfo.setTsTarget(true);
                flag = RuleHitEnum.HIT.getCode();
            }else{
                persionRuleInfo.setInterfaceResultCodeRemark("执行成功，没有命中");
                persionRuleInfo.setTsTarget(false);
            }
        }
        // 微众（法院）接口接口
        OwnerLoanRuleInfo webBackRuleInfo = ownerResult.getInterInfo().get(WEIZHONG_FUNCIONCODE);
        webBackRuleInfo.setCreateTime(DateUtil.formatDate(DateUtil.SIMPLE_TIME_FORMAT,new Date()));
        startTime = System.currentTimeMillis();
        Result<LawxpWebankDtoOut> webBankResult = callWebank(identityCard,name,mobilePhone);
        webBackRuleInfo.setCall_second((System.currentTimeMillis()-startTime)/1000);
        webBackRuleInfo.setCallEndtime(DateUtil.formatDate(DateUtil.SIMPLE_TIME_FORMAT,new Date()));
        if(webBankResult == null){
            webBackRuleInfo.setInvokeSuccess(false);
            webBackRuleInfo.setInterfaceResultCodeRemark("微众（法院）接口接口调用失败");
            webBackRuleInfo.setTsTarget(false);
        }
        if(webBankResult != null && ReturnCodeEnum.SUCCESS.getReturnCode().equals(webBankResult.getReturnCode())){
            webBackRuleInfo.setInvokeSuccess(true);
            webBackRuleInfo.setResultJson(JSON.toJSONString(webBankResult.getData()));
            if(webBankResult.getData() != null && webBankResult.getData().getTotalnumber() >0){
                webBackRuleInfo.setInterfaceResultCodeRemark("命中微众（法院）");
                webBackRuleInfo.setTsTarget(true);
                flag = RuleHitEnum.HIT.getCode();
            }else{
                webBackRuleInfo.setInterfaceResultCodeRemark("执行成功，没有命中");
                webBackRuleInfo.setTsTarget(false);
            }
        }
        // 天行数科接口
        OwnerLoanRuleInfo crimeRuleInfo = ownerResult.getInterInfo().get(CRIME_FUNCIONCODE);
        crimeRuleInfo.setCreateTime(DateUtil.formatDate(DateUtil.SIMPLE_TIME_FORMAT,new Date()));
        startTime = System.currentTimeMillis();
        Result<NegativeSearchDtoOut> crimeResult = callNegativeSearch(identityCard,name,mobilePhone);
        crimeRuleInfo.setCall_second((System.currentTimeMillis()-startTime)/1000);
        crimeRuleInfo.setCallEndtime(DateUtil.formatDate(DateUtil.SIMPLE_TIME_FORMAT,new Date()));
        if(crimeResult == null){
            crimeRuleInfo.setInvokeSuccess(false);
            crimeRuleInfo.setInterfaceResultCodeRemark("天行数科接口接口调用失败");
            crimeRuleInfo.setTsTarget(false);
        }
        if(crimeResult != null && ReturnCodeEnum.SUCCESS.getReturnCode().equals(crimeResult.getReturnCode())){
            crimeRuleInfo.setInvokeSuccess(true);
            crimeRuleInfo.setResultJson(JSON.toJSONString(crimeResult.getData()));
            //是否在逃,是否有前科,是否吸毒,是否涉毒
            if(crimeResult.getData() != null && ("1".equals(crimeResult.getData().getIsCrime()) || "1".equals(crimeResult.getData().getIsDrug()) || "1".equals(crimeResult.getData().getIsDrugRelated())
                || "1".equals(crimeResult.getData().getIsEscape()))){
                crimeRuleInfo.setInterfaceResultCodeRemark("命中天行数科接口");
                crimeRuleInfo.setTsTarget(true);
                flag = RuleHitEnum.HIT.getCode();
            }else{
                crimeRuleInfo.setInterfaceResultCodeRemark("执行成功，没有命中");
                crimeRuleInfo.setTsTarget(false);
            }
        }
        execution.setVariable("flag",flag);
        LOGGER.info("LawInfoCheckServiceImpl excute end");
    }

    /**
     * 调用微众（法院）接口，重试次数MAX_SIZE次
     * @param identityCard
     * @param name
     * @return
     */
    private Result<LawxpWebankDtoOut> callWebank(String identityCard, String name, String mobilePhone){
        LawxpWebankDtoIn in = new LawxpWebankDtoIn();
        in.setIdentityCard(identityCard);
        in.setRealName(name);
        in.setStype("1");
        Result<LawxpWebankDtoOut> result = null;
        int count = 0;
        boolean successFlag = false;
        while(count++<MAX_SIZE && !successFlag){
            try{
                result = eipServiceInterface.webank(in);
                successFlag = true;
            }catch (Exception e){
                LOGGER.error("第"+count+"次调用微众（法院）接口失败",e);
                successFlag = false;
            }
        }
        return  result;
    }

    /**
     * 调用汇法网接口，重试次数MAX_SIZE次
     * @param identityCard
     * @param name
     * @return
     */
    private Result<LawxpPersonClassifyDtoOut> callPersonClassify(String identityCard, String name, String mobilePhone){
        LawxpPersonClassifyDtoIn in = new LawxpPersonClassifyDtoIn();
        in.setIdentityCard(identityCard);
        in.setRealName(name);
        Result<LawxpPersonClassifyDtoOut> result = null;
        int count = 0;
        boolean successFlag = false;
        while(count++<MAX_SIZE && !successFlag){
            try{
                result = eipServiceInterface.personClassify(in);
                successFlag = true;
            }catch (Exception e){
                LOGGER.error("第"+count+"次调用汇法网接口失败",e);
                successFlag = false;
            }
        }
        return  result;
    }


    /**
     * 调用天行数科，重试次数MAX_SIZE次
     * @param identityCard
     * @param name
     * @return
     */
    private Result<NegativeSearchDtoOut> callNegativeSearch(String identityCard, String name,String mobilePhone){
        NegativeSearchDtoIn in = new NegativeSearchDtoIn();
        in.setIdentityCard(identityCard);
        in.setRealName(name);
        Result<NegativeSearchDtoOut> result = null;
        int count = 0;
        boolean successFlag = false;
        while(count++<MAX_SIZE && !successFlag){
            try{
                result = eipServiceInterface.getNegativeSearch(in);
                successFlag = true;
            }catch (Exception e){
                LOGGER.error("第"+count+"次调用天行数科失败",e);
                successFlag = false;
            }
        }
        return  result;
    }
}
