package com.ht.risk.activiti.service.ownerloan.impl;

import com.alibaba.fastjson.JSON;
import com.ht.risk.activiti.rpc.EipServiceInterface;
import com.ht.risk.activiti.service.ownerloan.BairongCheckService;
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
 * 百融多次核查
 */
@Service("bairongCheckService")
public class BairongCheckServiceImpl implements BairongCheckService {

    private static final int MAX_SIZE = 3;
    private static final Logger LOGGER = LoggerFactory.getLogger(BairongCheckServiceImpl.class);
    private static final String BAIRONG_FUNCIONCODE = "10006003";

    @Autowired
    private EipServiceInterface eipServiceInterface;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        LOGGER.info("BairongCheckServiceImpl excute start");
        OwnerLoanModelResult ownerResult = (OwnerLoanModelResult)execution.getVariable(ActivitiConstants.PROC_OWNER_LOAN_RESULT_CODE);
        Map dataMap  = (Map)execution.getVariable(ActivitiConstants.PROC_MODEL_DATA_KEY);
        Map borrowerMap = (Map)dataMap.get("borrowerInfo");
        String identityCard = String.valueOf(borrowerMap.get("identifyCard"));
        String name= String.valueOf(borrowerMap.get("customerName"));
        String mobilePhone = String.valueOf(borrowerMap.get("phoneNo"));
        String flag = RuleHitEnum.UNHIT.getCode();//命中标识： 1 沒有命中，2 命中
        // 汇法网个人分类信息接口
       /* OwnerLoanRuleInfo moreCheckRuleInfo = ownerResult.getInterInfo().get(BAIRONG_FUNCIONCODE);
        moreCheckRuleInfo.setCreateTime(DateUtil.formatDate(DateUtil.SIMPLE_TIME_FORMAT,new Date()));
        long startTime = System.currentTimeMillis();
        Result<BairongMoreCheckDtoOut> moreCheckResult = callBaiRongCheck(identityCard,name,mobilePhone);
        moreCheckRuleInfo.setCall_second((System.currentTimeMillis()-startTime)/1000);
        moreCheckRuleInfo.setCallEndtime(DateUtil.formatDate(DateUtil.SIMPLE_TIME_FORMAT,new Date()));
        if(moreCheckResult == null){
            moreCheckRuleInfo.setInvokeSuccess(false);
            moreCheckRuleInfo.setInterfaceResultCodeRemark("百融多次核查接口调用失败");
            moreCheckRuleInfo.setTsTarget(false);
        }
        if(moreCheckResult != null && ReturnCodeEnum.SUCCESS.getReturnCode().equals(moreCheckResult.getReturnCode())){
            moreCheckRuleInfo.setInvokeSuccess(true);
            moreCheckRuleInfo.setResultJson(JSON.toJSONString(moreCheckResult.getData()));
            if(moreCheckResult.getData() != null){
                moreCheckRuleInfo.setInterfaceResultCodeRemark("命中百融多次核查信息");
                moreCheckRuleInfo.setTsTarget(false);
                //flag = RuleHitEnum.UNHIT.getCode();
            }else{
                moreCheckRuleInfo.setInterfaceResultCodeRemark("执行成功，没有命中");
                moreCheckRuleInfo.setTsTarget(false);
            }
        }*/
        execution.setVariable("flag",flag);
        LOGGER.info("BairongCheckServiceImpl excute end");

    }

    /**
     * 调用百融多次核查，重试次数MAX_SIZE次
     * @param identityCard
     * @param name
     * @return
     */
    private Result<BairongMoreCheckDtoOut> callBaiRongCheck(String identityCard, String name, String mobilePhone){
        BairongMoreCheckDtoIn in = new BairongMoreCheckDtoIn();
        in.setIdentityCard(identityCard);
        in.setRealName(name);
        in.setMobilePhone(mobilePhone);
        Result<BairongMoreCheckDtoOut> result = null;
        int count = 0;
        boolean successFlag = false;
        while(count++<MAX_SIZE && !successFlag){
            try{
                result = eipServiceInterface.moreCheck(in);
                successFlag = true;
            }catch (Exception e){
                LOGGER.error("第"+count+"次调用百融多次核查失败",e);
                successFlag = false;
            }
        }
        return  result;
    }


}
