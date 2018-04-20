package com.ht.risk.activiti.service.ownerloan.impl;

import com.alibaba.fastjson.JSON;
import com.ht.risk.activiti.rpc.EipServiceInterface;
import com.ht.risk.activiti.service.ownerloan.IdentityCheckService;
import com.ht.risk.activiti.vo.OwnerLoanModelResult;
import com.ht.risk.activiti.vo.OwnerLoanRuleInfo;
import com.ht.risk.api.constant.activiti.ActivitiConstants;
import com.ht.risk.api.model.eip.*;
import com.ht.risk.common.util.DateUtil;
import com.ht.ussp.core.Result;
import com.ht.ussp.core.ReturnCodeEnum;
import org.activiti.engine.delegate.DelegateExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

/**
 * 身份认证，身份证实名认证，手机号实名认证
 */
@Service("identityCheckService")
public class IdentityCheckServiceImpl implements IdentityCheckService {

    private static final int MAX_SIZE = 3;
    private static final Logger LOGGER = LoggerFactory.getLogger(IdentityCheckServiceImpl.class);
    private static final String IDVERIFY_FUNCIONCODE = "10042002";
    private static final String PHONE_FUNCIONCODE = "10017002";

    @Resource
    private EipServiceInterface eipServiceInterface;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        LOGGER.info("IdentityCheckServiceImpl execute start");
        OwnerLoanModelResult ownerResult = (OwnerLoanModelResult)execution.getVariable(ActivitiConstants.PROC_OWNER_LOAN_RESULT_CODE);
        long startTime = System.currentTimeMillis();

        Map dataMap  = (Map)execution.getVariable(ActivitiConstants.PROC_MODEL_DATA_KEY);
        Map borrowerMap = (Map)dataMap.get("borrowerInfo");
        if(borrowerMap == null){
            execution.setVariable("flag","2");
            return;
        }
        String identityCard = String.valueOf(borrowerMap.get("identifyCard"));
        String name= String.valueOf(borrowerMap.get("customerName"));
        String mobilePhone = String.valueOf(borrowerMap.get("mobilePhone"));

        String flag = "1";

        // 身份认证
        OwnerLoanRuleInfo idetifyRuleInfo = ownerResult.getInterInfo().get(IDVERIFY_FUNCIONCODE);
        idetifyRuleInfo.setCreateTime(DateUtil.formatDate(DateUtil.SIMPLE_TIME_FORMAT,new Date()));
        Result<IdVerifyRespDto> result = clallIdVerify(identityCard,name);
        idetifyRuleInfo.setCall_second((System.currentTimeMillis()-startTime)/1000);
        idetifyRuleInfo.setCallEndtime(DateUtil.formatDate(DateUtil.SIMPLE_TIME_FORMAT,new Date()));
        if(result != null && ReturnCodeEnum.SUCCESS.getReturnCode().equals(result.getReturnCode())){
            idetifyRuleInfo.setInvokeSuccess(true);
            idetifyRuleInfo.setResultJson(JSON.toJSONString(result.getData()));
            if( "1".equals(result.getData().getResult())){
                idetifyRuleInfo.setInterfaceResultCodeRemark("身份证号码与姓名匹配成功");
                idetifyRuleInfo.setTsTarget(false);
            }
            if( "2".equals(result.getData().getResult())){
                idetifyRuleInfo.setInterfaceResultCodeRemark("身份证号码与姓名不一致");
                idetifyRuleInfo.setTsTarget(true);
                flag = "2";
            }
            if( "3".equals(result.getData().getResult())){
                idetifyRuleInfo.setInterfaceResultCodeRemark("无此身份证号");
                idetifyRuleInfo.setTsTarget(true);
                flag = "2";
            }
        }
        if(result == null || !ReturnCodeEnum.SUCCESS.getReturnCode().equals(result.getReturnCode())){
            idetifyRuleInfo.setInvokeSuccess(false);
            idetifyRuleInfo.setInterfaceResultCodeRemark("身份验证接口调用失败");
            idetifyRuleInfo.setTsTarget(false);
        }
        // 手机号认证
        OwnerLoanRuleInfo mobileRuleInfo = ownerResult.getInterInfo().get(PHONE_FUNCIONCODE);
        idetifyRuleInfo.setCreateTime(DateUtil.formatDate(DateUtil.SIMPLE_TIME_FORMAT,new Date()));
        startTime = System.currentTimeMillis();
        Result<MobileValidDtoOut> mobileResult = clallKl(identityCard,name,mobilePhone);
        mobileRuleInfo.setCall_second((System.currentTimeMillis()-startTime)/1000);
        mobileRuleInfo.setCallEndtime(DateUtil.formatDate(DateUtil.SIMPLE_TIME_FORMAT,new Date()));
        if(result != null && ReturnCodeEnum.SUCCESS.getReturnCode().equals(result.getReturnCode())){
            mobileRuleInfo.setInvokeSuccess(true);
            mobileRuleInfo.setResultJson(JSON.toJSONString(result.getData()));
            if( result.getData().getResult() != null){
                mobileRuleInfo.setInterfaceResultCodeRemark("手机号校验成功");
                mobileRuleInfo.setTsTarget(false);
            }
            else{
                mobileRuleInfo.setInterfaceResultCodeRemark("手机号不存在");
                mobileRuleInfo.setTsTarget(true);
                flag = "2";
            }
        }
        if(result == null || !ReturnCodeEnum.SUCCESS.getReturnCode().equals(result.getReturnCode())){
            mobileRuleInfo.setInvokeSuccess(false);
            mobileRuleInfo.setInterfaceResultCodeRemark("手机号校验接口调用失败");
            mobileRuleInfo.setTsTarget(false);
        }
        execution.setVariable("flag",flag);
        LOGGER.info("IdentityCheckServiceImpl execute end");

    }

    /**
     * 调用商汤实名认证，重试次数3次
     * @param identityCard
     * @param name
     * @return
     */
    private Result<IdVerifyRespDto> clallIdVerify(String identityCard, String name){
        IdVerifyReqDto idVerifyReqDto  = new IdVerifyReqDto();
        idVerifyReqDto.setIdentityCard(identityCard);
        idVerifyReqDto.setRealName(name);
        idVerifyReqDto.setApp(ActivitiConstants.OWNER_LOAN_APP_CODE);
        Result<IdVerifyRespDto> result = null;
        int count = 0;
        boolean successFlag = false;
        while(count++<MAX_SIZE && !successFlag){
            try{
                result = eipServiceInterface.idVerify(idVerifyReqDto);
                successFlag = true;
            }catch (Exception e){
                LOGGER.error("第"+count+"次调用网贷黑名单失败",e);
                successFlag = false;
            }
        }
        return  result;
    }

    /**
     * 调用考拉实名认证，重试次数3次
     * @param identityCard
     * @param name
     * @param mobilePhone
     * @return
     */
    private Result<MobileValidDtoOut> clallKl(String identityCard, String name, String mobilePhone){
        MobileValidDtoIn mobileValidDtoIn  = new MobileValidDtoIn();
        mobileValidDtoIn.setIdentityCard(identityCard);
        mobileValidDtoIn.setRealName(name);
        mobileValidDtoIn.setMobilePhone(mobilePhone);
        mobileValidDtoIn.setApp(ActivitiConstants.OWNER_LOAN_APP_CODE);
        Result<MobileValidDtoOut> result = null;
        int count = 0;
        boolean successFlag = false;
        while(count++<MAX_SIZE && !successFlag){
            try{
                result = eipServiceInterface.mobileValid(mobileValidDtoIn);
                successFlag = true;
            }catch (Exception e){
                LOGGER.error("第"+count+"次考拉实名认证失败",e);
                successFlag = false;
            }
        }
        return  result;
    }
}
