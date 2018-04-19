package com.ht.risk.activiti.service.ownerloan.impl;

import com.ht.risk.activiti.rpc.EipServiceInterface;
import com.ht.risk.activiti.service.impl.ActivitiServiceImpl;
import com.ht.risk.activiti.service.ownerloan.BlanckListService;
import com.ht.risk.api.constant.activiti.ActivitiConstants;
import com.ht.risk.api.model.eip.*;
import com.ht.ussp.core.Result;
import com.ht.ussp.core.ReturnCodeEnum;
import org.activiti.engine.delegate.DelegateExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 黑名单，汇法网，天行数科规则
 */
@Service("blanckListService")
public class BlanckListServiceImpl implements BlanckListService {

    @Autowired
    private EipServiceInterface eipServiceInterface;

    private static final Logger LOGGER = LoggerFactory.getLogger(BlanckListServiceImpl.class);


    @Override
    public void execute(DelegateExecution execution) throws Exception {
        LOGGER.info("BlanckListServiceImpl excute start");
        String ruleMsgStr = String.valueOf(execution.getVariable(ActivitiConstants.PROC_EXCUTE_HIT_RULE_MSG));
        String msgStr = String.valueOf(execution.getVariable(ActivitiConstants.PROC_EXCUTE_MSG));
        StringBuffer ruleMsg = new StringBuffer(ruleMsgStr);
        StringBuffer msg = new StringBuffer(msgStr);
        Map dataMap  = (Map)execution.getVariable(ActivitiConstants.PROC_MODEL_DATA_KEY);
        Map borrowerMap = (Map)dataMap.get("borrorwerInfo");
        if(borrowerMap == null){
            execution.setVariable("flag","2");
            return;
        }
        String identityCard = String.valueOf(borrowerMap.get("identifyCard"));
        String name= String.valueOf(borrowerMap.get("customerName"));
        String mobilePhone = String.valueOf(borrowerMap.get("phoneNo"));
        // 网贷
        Result<NetLoanOut> netResult = clallNetLoan(identityCard,name,mobilePhone);
        if(netResult == null){
            msg.append("身份证：").append(identityCard).append(",三次查询网贷黑名单失败；");
            execution.setVariable(ActivitiConstants.PROC_EXCUTE_MSG,msg);
        }
        if(netResult != null && ReturnCodeEnum.SUCCESS.getReturnCode().equals(netResult.getReturnCode()) && netResult.getData() != null){
            ruleMsg.append("身份证：").append(identityCard).append(",命中网贷黑名单；");
            execution.setVariable(ActivitiConstants.PROC_EXCUTE_HIT_RULE_MSG,ruleMsg);
            execution.setVariable("flag","2");
            return;
        }
        // 老赖
        Result<OldLaiOut> oldLaiOutResult = clallOldLai(identityCard,name);
        if(oldLaiOutResult == null){
            msg.append("身份证：").append(identityCard).append(",三次查询老赖黑名单失败；");
            execution.setVariable(ActivitiConstants.PROC_EXCUTE_MSG,msg);
        }
        if(oldLaiOutResult != null && ReturnCodeEnum.SUCCESS.getReturnCode().equals(oldLaiOutResult.getReturnCode()) && oldLaiOutResult.getData() != null){
            ruleMsg.append("身份证：").append(identityCard).append(",命中老赖黑名单；");
            execution.setVariable(ActivitiConstants.PROC_EXCUTE_HIT_RULE_MSG,ruleMsg);
            execution.setVariable("flag","2");
            return;
        }
        // 自有
        Result<SelfDtoOut> selfDtoOutResult = clallSelf(identityCard,name);
        if(selfDtoOutResult == null){
            msg.append("身份证：").append(identityCard).append(",三次查询自有黑名单失败；");
            execution.setVariable(ActivitiConstants.PROC_EXCUTE_MSG,msg);
        }
        if(selfDtoOutResult != null && ReturnCodeEnum.SUCCESS.getReturnCode().equals(selfDtoOutResult.getReturnCode()) && selfDtoOutResult.getData() != null){
            ruleMsg.append("身份证：").append(identityCard).append(",命中自有黑名单；");
            execution.setVariable(ActivitiConstants.PROC_EXCUTE_HIT_RULE_MSG,ruleMsg);
            execution.setVariable("flag","2");
            return;
        }
        // 考拉
        Result<KlRiskBlackListRespDto> klResult = clallKl(identityCard,name,mobilePhone);
        if(klResult == null){
            msg.append("身份证：").append(identityCard).append(",三次查询考拉黑名单失败；");
            execution.setVariable(ActivitiConstants.PROC_EXCUTE_MSG,msg);
        }
        if(klResult != null && ReturnCodeEnum.SUCCESS.getReturnCode().equals(klResult.getReturnCode()) && klResult.getData() != null){
            ruleMsg.append("身份证：").append(identityCard).append(",命中考拉黑名单；");
            execution.setVariable(ActivitiConstants.PROC_EXCUTE_HIT_RULE_MSG,ruleMsg);
            execution.setVariable("flag","2");
            return;
        }
        // 汇法
        Result<LawxpPersonClassifyDtoOut> personClassifyDtoOutResult = clallPersonClassify(identityCard,name,mobilePhone);
        if(personClassifyDtoOutResult == null){
            msg.append("身份证：").append(identityCard).append(",三次查询汇法个人信息统计接口失败；");
            execution.setVariable(ActivitiConstants.PROC_EXCUTE_MSG,msg);
        }
        if(personClassifyDtoOutResult != null && ReturnCodeEnum.SUCCESS.getReturnCode().equals(personClassifyDtoOutResult.getReturnCode()) && personClassifyDtoOutResult.getData() != null){
            ruleMsg.append("身份证：").append(identityCard).append(",命中汇法个人信息失信规则；");
            execution.setVariable(ActivitiConstants.PROC_EXCUTE_HIT_RULE_MSG,ruleMsg);
            execution.setVariable("flag","2");
            return;
        }
        // 天行数科
        Result<NegativeSearchDtoOut> negativeSearchDtoOutResult = clallNegativeSearch(identityCard,name,mobilePhone);
        if(negativeSearchDtoOutResult == null){
            msg.append("身份证：").append(identityCard).append(",三次查询天行数科接口失败；");
            execution.setVariable(ActivitiConstants.PROC_EXCUTE_MSG,msg);
        }
        if(negativeSearchDtoOutResult != null && ReturnCodeEnum.SUCCESS.getReturnCode().equals(negativeSearchDtoOutResult.getReturnCode()) && negativeSearchDtoOutResult.getData() != null){
            ruleMsg.append("身份证：").append(identityCard).append(",命中天行数科负面消息记录规则；");
            execution.setVariable(ActivitiConstants.PROC_EXCUTE_HIT_RULE_MSG,ruleMsg);
            execution.setVariable("flag","2");
            return;
        }
        execution.setVariable("flag","1");
    }

    /**
     * 调用网贷黑名单，重试次数3次
     * @param identityCard
     * @param name
     * @param mobilePhone
     * @return
     */
    private Result<NetLoanOut> clallNetLoan(String identityCard,String name,String mobilePhone){
        NetLoanIn in = new NetLoanIn();
        in.setIdentityCard(identityCard);
        in.setMobilePhone(mobilePhone);
        in.setRealName(name);
        Result<NetLoanOut> result = null;
        int count = 0;
        boolean successFlag = false;
        while(count<3 && !successFlag){
            try{
                result = eipServiceInterface.netLoan(in);
                count++;
                if(!"0000".equals(result.getReturnCode())){
                    successFlag = false;
                    result = null;
                }else{
                    successFlag = true;
                }
            }catch (Exception e){
                count++;
                LOGGER.error("第"+count+"次调用网贷黑名单失败",e);
                successFlag = false;
            }
        }
        return  result;
    }

    /**
     * 调用老赖黑名单，重试次数3次
     * @param identityCard
     * @param name
     * @return
     */
    private Result<OldLaiOut> clallOldLai(String identityCard, String name){
        OldLaiIn in = new OldLaiIn();
        in.setIdentityCard(identityCard);
        in.setRealName(name);
        in.setQueryType("1");
        Result<OldLaiOut> result = null;
        int count = 0;
        boolean successFlag = false;
        while(count<3 && !successFlag){
            try{
                result = eipServiceInterface.oldLai(in);
                count++;
                if(!"0000".equals(result.getReturnCode())){
                    successFlag = false;
                    result = null;
                }else{
                    successFlag = true;
                }
            }catch (Exception e){
                count++;
                LOGGER.error("第"+count+"次调用老赖黑名单失败",e);
                successFlag = false;
            }
        }
        return  result;
    }

    /**
     * 调用自有黑名单，重试次数3次
     * @param identityCard
     * @param name
     * @return
     */
    private Result<SelfDtoOut> clallSelf(String identityCard, String name){
        OldLaiIn in = new OldLaiIn();
        in.setIdentityCard(identityCard);
        in.setRealName(name);
        in.setQueryType("1");
        Result<SelfDtoOut> result = null;
        int count = 0;
        boolean successFlag = false;
        while(count<3 && !successFlag){
            try{
                result = eipServiceInterface.self(in);
                count++;
                if(!"0000".equals(result.getReturnCode())){
                    successFlag = false;
                    result = null;
                }else{
                    successFlag = true;
                }
            }catch (Exception e){
                count++;
                LOGGER.error("第"+count+"次调用自有黑名单失败",e);
                successFlag = false;
            }
        }
        return  result;
    }

    /**
     * 调用考拉黑名单，重试次数3次
     * @param identityCard
     * @param name
     * @return
     */
    private Result<KlRiskBlackListRespDto> clallKl(String identityCard, String name,String mobilePhone){
        KlRiskBlackListReqDto in = new KlRiskBlackListReqDto();
        in.setIdentityCard(identityCard);
        in.setRealName(name);
        in.setMobilePhone(mobilePhone);
        Result<KlRiskBlackListRespDto> result = null;
        int count = 0;
        boolean successFlag = false;
        while(count<3 && !successFlag){
            try{
                result = eipServiceInterface.kl(in);
                count++;
                if(!"0000".equals(result.getReturnCode())){
                    successFlag = false;
                    result = null;
                }else{
                    successFlag = true;
                }
            }catch (Exception e){
                count++;
                LOGGER.error("第"+count+"次调用考拉黑名单失败",e);
                successFlag = false;
            }
        }
        return  result;
    }

    /**
     * 调用汇法网接口，重试次数3次
     * @param identityCard
     * @param name
     * @return
     */
    private Result<LawxpPersonClassifyDtoOut> clallPersonClassify(String identityCard, String name,String mobilePhone){
        LawxpPersonClassifyDtoIn in = new LawxpPersonClassifyDtoIn();
        in.setIdentityCard(identityCard);
        in.setRealName(name);
        Result<LawxpPersonClassifyDtoOut> result = null;
        int count = 0;
        boolean successFlag = false;
        while(count<3 && !successFlag){
            try{
                result = eipServiceInterface.personClassify(in);
                count++;
                if(!"0000".equals(result.getReturnCode())){
                    successFlag = false;
                    result = null;
                }else{
                    successFlag = true;
                }
            }catch (Exception e){
                count++;
                LOGGER.error("第"+count+"次调用汇法网接口失败",e);
                successFlag = false;
            }
        }
        return  result;
    }


    /**
     * 调用天行数科，重试次数3次
     * @param identityCard
     * @param name
     * @return
     */
    private Result<NegativeSearchDtoOut> clallNegativeSearch(String identityCard, String name,String mobilePhone){
        NegativeSearchDtoIn in = new NegativeSearchDtoIn();
        in.setIdentityCard(identityCard);
        in.setRealName(name);
        Result<NegativeSearchDtoOut> result = null;
        int count = 0;
        boolean successFlag = false;
        while(count<3 && !successFlag){
            try{
                result = eipServiceInterface.getNegativeSearch(in);
                count++;
                if(!"0000".equals(result.getReturnCode())){
                    successFlag = false;
                    result = null;
                }else{
                    successFlag = true;
                }
            }catch (Exception e){
                count++;
                LOGGER.error("第"+count+"次调用天行数科失败",e);
                successFlag = false;
            }
        }
        return  result;
    }
}
