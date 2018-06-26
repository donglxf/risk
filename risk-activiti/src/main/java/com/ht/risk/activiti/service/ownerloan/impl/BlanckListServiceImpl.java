package com.ht.risk.activiti.service.ownerloan.impl;

import com.alibaba.fastjson.JSON;
import com.ht.risk.activiti.rpc.EipServiceInterface;
import com.ht.risk.activiti.service.ownerloan.BlanckListService;
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
 * 黑名单，汇法网，天行数科规则
 */
@Service("blanckListService")
public class BlanckListServiceImpl implements BlanckListService {

    @Autowired
    private EipServiceInterface eipServiceInterface;

    private static final Logger LOGGER = LoggerFactory.getLogger(BlanckListServiceImpl.class);
    private static final String BAIQISHI_FUNCIONCODE = "10015001";
    private static final String NETLOAN_FUNCIONCODE = "10013001";
    private static final String SELF_FUNCIONCODE = "10013003";
    private static final String KL_FUNCIONCODE = "10017001";
    private static final String QIANHAI_FUNCIONCODE = "10011002";
    private static final int MAX_SIZE = 3;


    @Override
    public void execute(DelegateExecution execution) throws Exception {
        LOGGER.info("BlanckListServiceImpl excute start");
        OwnerLoanModelResult ownerResult = (OwnerLoanModelResult)execution.getVariable(ActivitiConstants.PROC_OWNER_LOAN_RESULT_CODE);
        Map dataMap  = (Map)execution.getVariable(ActivitiConstants.PROC_MODEL_DATA_KEY);
        Map borrowerMap = (Map)dataMap.get("borrowerInfo");
        String identityCard = String.valueOf(borrowerMap.get("identifyCard"));
        String name= String.valueOf(borrowerMap.get("customerName"));
        String mobilePhone = String.valueOf(borrowerMap.get("phoneNo"));
        String flag = RuleHitEnum.UNHIT.getCode();//命中标识： 1 沒有命中，2 命中
        // 网贷
        OwnerLoanRuleInfo netRuleInfo = ownerResult.getInterInfo().get(NETLOAN_FUNCIONCODE);
        netRuleInfo.setCreateTime(DateUtil.formatDate(DateUtil.SIMPLE_TIME_FORMAT,new Date()));
        long startTime = System.currentTimeMillis();
        Result<NetLoanOut> netResult = callNetLoan(identityCard,name,mobilePhone);
        netRuleInfo.setCall_second((System.currentTimeMillis()-startTime)/1000);
        netRuleInfo.setCallEndtime(DateUtil.formatDate(DateUtil.SIMPLE_TIME_FORMAT,new Date()));
        if(netResult == null){
            netRuleInfo.setInvokeSuccess(false);
            netRuleInfo.setInterfaceResultCodeRemark("网贷黑名单接口调用失败");
            netRuleInfo.setTsTarget(false);
        }
        if(netResult != null && ReturnCodeEnum.SUCCESS.getReturnCode().equals(netResult.getReturnCode())){
            netRuleInfo.setInvokeSuccess(true);
            netRuleInfo.setResultJson(JSON.toJSONString(netResult.getData()));
            if(netResult.getData() != null){
                netRuleInfo.setInterfaceResultCodeRemark("命中网贷黑名单");
                netRuleInfo.setTsTarget(false);
                flag = RuleHitEnum.HIT.getCode();
            }else{
                netRuleInfo.setInterfaceResultCodeRemark("执行成功，没有命中");
                netRuleInfo.setTsTarget(false);
            }
        }
        // 自有
        OwnerLoanRuleInfo slefRuleInfo = ownerResult.getInterInfo().get(SELF_FUNCIONCODE);
        slefRuleInfo.setCreateTime(DateUtil.formatDate(DateUtil.SIMPLE_TIME_FORMAT,new Date()));
        startTime = System.currentTimeMillis();
        Result<SelfDtoOut> selfDtoOutResult = callSelf(identityCard,name);
        slefRuleInfo.setCall_second((System.currentTimeMillis()-startTime)/1000);
        slefRuleInfo.setCallEndtime(DateUtil.formatDate(DateUtil.SIMPLE_TIME_FORMAT,new Date()));
        if(selfDtoOutResult == null){
            slefRuleInfo.setInvokeSuccess(false);
            slefRuleInfo.setInterfaceResultCodeRemark("自有黑名单接口调用失败");
            slefRuleInfo.setTsTarget(false);
        }
        if(selfDtoOutResult != null && ReturnCodeEnum.SUCCESS.getReturnCode().equals(selfDtoOutResult.getReturnCode())){
            slefRuleInfo.setInvokeSuccess(true);
            slefRuleInfo.setResultJson(JSON.toJSONString(selfDtoOutResult.getData()));
            if(selfDtoOutResult.getData() != null && "1".equals(selfDtoOutResult.getData().getIsBlacklistUser())){
                slefRuleInfo.setInterfaceResultCodeRemark("命中自有黑名单");
                slefRuleInfo.setTsTarget(false);
                flag = RuleHitEnum.HIT.getCode();
            }else{
                slefRuleInfo.setInterfaceResultCodeRemark("执行成功，没有命中");
                slefRuleInfo.setTsTarget(false);
            }
        }
        // 考拉
        OwnerLoanRuleInfo klRuleInfo = ownerResult.getInterInfo().get(KL_FUNCIONCODE);
        klRuleInfo.setCreateTime(DateUtil.formatDate(DateUtil.SIMPLE_TIME_FORMAT,new Date()));
        startTime = System.currentTimeMillis();
        Result<KlRiskBlackListRespDto> klResult = callKl(identityCard,name,mobilePhone);
        klRuleInfo.setCall_second((System.currentTimeMillis()-startTime)/1000);
        klRuleInfo.setCallEndtime(DateUtil.formatDate(DateUtil.SIMPLE_TIME_FORMAT,new Date()));
        if(klResult == null){
            klRuleInfo.setInvokeSuccess(false);
            klRuleInfo.setInterfaceResultCodeRemark("考拉黑名单接口调用失败");
            klRuleInfo.setTsTarget(false);
        }
        if(klResult != null && ReturnCodeEnum.SUCCESS.getReturnCode().equals(klResult.getReturnCode())){
            klRuleInfo.setInvokeSuccess(true);
            klRuleInfo.setResultJson(JSON.toJSONString(klResult.getData()));
            if(klResult.getData() != null && klResult.getData().getBlacklistDetailList() != null){
                klRuleInfo.setInterfaceResultCodeRemark("命中考拉黑名单");
                klRuleInfo.setTsTarget(false);
                flag = RuleHitEnum.HIT.getCode();
            }else{
                klRuleInfo.setInterfaceResultCodeRemark("执行成功，没有命中");
                klRuleInfo.setTsTarget(false);

            }
        }
        // 前海
        OwnerLoanRuleInfo qianhaiRuleInfo = ownerResult.getInterInfo().get(QIANHAI_FUNCIONCODE);
        qianhaiRuleInfo.setCreateTime(DateUtil.formatDate(DateUtil.SIMPLE_TIME_FORMAT,new Date()));
        startTime = System.currentTimeMillis();
        Result<FrontSeaDtoOut> qianhaiResult = callQianhai(identityCard,name,mobilePhone);
        qianhaiRuleInfo.setCall_second((System.currentTimeMillis()-startTime)/1000);
        qianhaiRuleInfo.setCallEndtime(DateUtil.formatDate(DateUtil.SIMPLE_TIME_FORMAT,new Date()));
        if(qianhaiResult == null){
            qianhaiRuleInfo.setInvokeSuccess(false);
            qianhaiRuleInfo.setInterfaceResultCodeRemark("前海征信黑名单接口调用失败");
            qianhaiRuleInfo.setTsTarget(false);
        }
        if(qianhaiResult != null && ReturnCodeEnum.SUCCESS.getReturnCode().equals(qianhaiResult.getReturnCode())){
            qianhaiRuleInfo.setInvokeSuccess(true);
            qianhaiRuleInfo.setResultJson(JSON.toJSONString(qianhaiResult.getData()));
            if(qianhaiResult.getData() != null && "1".equals(qianhaiResult.getData().getDataStatus())){
                qianhaiRuleInfo.setInterfaceResultCodeRemark("命中前海征信");
                qianhaiRuleInfo.setTsTarget(false);
                flag = RuleHitEnum.HIT.getCode();
            }else{
                qianhaiRuleInfo.setInterfaceResultCodeRemark("执行成功，没有命中");
                qianhaiRuleInfo.setTsTarget(false);

            }
        }
        // 白骑士
        OwnerLoanRuleInfo baiqishiRuleInfo = ownerResult.getInterInfo().get(BAIQISHI_FUNCIONCODE);
        baiqishiRuleInfo.setCreateTime(DateUtil.formatDate(DateUtil.SIMPLE_TIME_FORMAT,new Date()));
        startTime = System.currentTimeMillis();
        Result<BaiqishiDtoOut> baiqishiResult = callBaiqishi(identityCard,name,mobilePhone);
        baiqishiRuleInfo.setCall_second((System.currentTimeMillis()-startTime)/1000);
        baiqishiRuleInfo.setCallEndtime(DateUtil.formatDate(DateUtil.SIMPLE_TIME_FORMAT,new Date()));
        if(baiqishiResult == null){
            baiqishiRuleInfo.setInvokeSuccess(false);
            baiqishiRuleInfo.setInterfaceResultCodeRemark("白骑士黑名单接口调用失败");
            baiqishiRuleInfo.setTsTarget(false);
        }
        if(baiqishiResult != null && ReturnCodeEnum.SUCCESS.getReturnCode().equals(baiqishiResult.getReturnCode())){
            baiqishiRuleInfo.setInvokeSuccess(true);
            baiqishiRuleInfo.setResultJson(JSON.toJSONString(baiqishiResult.getData()));
            if(baiqishiResult.getData() != null && "Reject".equals(baiqishiResult.getData().getFinalDecision())){
                baiqishiRuleInfo.setInterfaceResultCodeRemark("命中白骑士黑名单");
                baiqishiRuleInfo.setTsTarget(false);
                flag = RuleHitEnum.HIT.getCode();
            }else{
                baiqishiRuleInfo.setInterfaceResultCodeRemark("执行成功，没有命中");
                baiqishiRuleInfo.setTsTarget(false);

            }
        }
        execution.setVariable("flag",flag);
        LOGGER.info("BlanckListServiceImpl excute end");
    }

    /**
     * 调用网贷黑名单，重试次数MAX_SIZE次
     * @param identityCard
     * @param name
     * @param mobilePhone
     * @return
     */
    private Result<NetLoanOut> callNetLoan(String identityCard,String name,String mobilePhone){
        NetLoanIn in = new NetLoanIn();
        in.setIdentityCard(identityCard);
        in.setMobilePhone(mobilePhone);
        in.setRealName(name);
        Result<NetLoanOut> result = null;
        int count = 0;
        boolean successFlag = false;
        while(count++<MAX_SIZE && !successFlag){
            try{
                result = eipServiceInterface.netLoan(in);
                successFlag = true;
            }catch (Exception e){
                LOGGER.error("第"+count+"次调用网贷黑名单失败",e);
                successFlag = false;
            }
        }
        return  result;
    }

    /**
     * 调用自有黑名单，重试次数MAX_SIZE次
     * @param identityCard
     * @param name
     * @return
     */
    private Result<SelfDtoOut> callSelf(String identityCard, String name){
        OldLaiIn in = new OldLaiIn();
        in.setIdentityCard(identityCard);
        in.setRealName(name);
        in.setQueryType("1");
        Result<SelfDtoOut> result = null;
        int count = 0;
        boolean successFlag = false;
        while(count++<MAX_SIZE && !successFlag){
            try{
                result = eipServiceInterface.self(in);
                successFlag = true;
            }catch (Exception e){
                LOGGER.error("第"+count+"次调用自有黑名单失败",e);
                successFlag = false;
            }
        }
        return  result;
    }

    /**
     * 调用考拉黑名单，重试次数MAX_SIZE次
     * @param identityCard
     * @param name
     * @return
     */
    private Result<KlRiskBlackListRespDto> callKl(String identityCard, String name,String mobilePhone){
        KlRiskBlackListReqDto in = new KlRiskBlackListReqDto();
        in.setIdentityCard(identityCard);
        in.setRealName(name);
        in.setMobilePhone(mobilePhone);
        Result<KlRiskBlackListRespDto> result = null;
        int count = 0;
        boolean successFlag = false;
        while(count++<MAX_SIZE && !successFlag){
            try{
                result = eipServiceInterface.kl(in);
                successFlag = true;
            }catch (Exception e){
                LOGGER.error("第"+count+"次调用考拉黑名单失败",e);
                successFlag = false;
            }
        }
        return  result;
    }


    /**
     * 调用白骑士黑名单，重试次数MAX_SIZE次
     * @param identityCard
     * @param name
     * @return
     */
    private Result<BaiqishiDtoOut> callBaiqishi(String identityCard, String name,String mobilePhone){
        NetLoanIn in = new NetLoanIn();
        in.setMobilePhone(mobilePhone);
        in.setIdentityCard(identityCard);
        in.setRealName(name);
        Result<BaiqishiDtoOut> result = null;
        int count = 0;
        boolean successFlag = false;
        while(count++<MAX_SIZE && !successFlag){
            try{
                result = eipServiceInterface.baiqishi(in);
                successFlag = true;
            }catch (Exception e){
                LOGGER.error("第"+count+"次调用白骑士黑名单失败",e);
                successFlag = false;
            }
        }
        return  result;
    }

    /**
     * 调用前海黑名单，重试次数MAX_SIZE次
     * @param identityCard
     * @param name
     * @return
     */
    private Result<FrontSeaDtoOut> callQianhai(String identityCard, String name,String mobilePhone){
        FrontSeaDtoIn in = new FrontSeaDtoIn();
        in.setIdentityCard(identityCard);
        in.setIdType("0");//身份证
        in.setReasonNo("01");
        in.setRealName(name);
        Result<FrontSeaDtoOut> result = null;
        int count = 0;
        boolean successFlag = false;
        while(count++<MAX_SIZE && !successFlag){
            try{
                result = eipServiceInterface.frontSea(in);
                successFlag = true;
            }catch (Exception e){
                LOGGER.error("第"+count+"次调用白前海黑名单失败",e);
                successFlag = false;
            }
        }
        return  result;
    }
}
