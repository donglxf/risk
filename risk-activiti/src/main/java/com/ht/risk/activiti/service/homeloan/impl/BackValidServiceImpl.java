package com.ht.risk.activiti.service.homeloan.impl;

import com.ht.risk.activiti.rpc.EipServiceInterface;
import com.ht.risk.activiti.service.homeloan.BackValidService;
import com.ht.risk.api.constant.activiti.ActivitiConstants;
import com.ht.risk.api.model.eip.*;
import com.ht.risk.common.util.ObjectUtils;
import com.ht.ussp.core.Result;
import lombok.extern.log4j.Log4j2;
import org.activiti.engine.delegate.DelegateExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("HomeLoanBackValid")
@Log4j2
public class BackValidServiceImpl implements BackValidService {

    @Autowired
    private EipServiceInterface eipServiceInterface;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        log.info("HomeLoanRuleDataMachinImpl execute method excute start...");
        StringBuffer msg = new StringBuffer("");
        Object dataObj = execution.getVariable(ActivitiConstants.PROC_MODEL_DATA_KEY);
        execution.setVariable(ActivitiConstants.PROC_START_CURRENT_TIME, System.currentTimeMillis());
        Map dataMap = null;
        if (dataObj == null) {
            dataMap = new HashMap();
            msg.append("模型所需数据为空;");
        } else {
            dataMap = (Map) dataObj;
        }
        callInterface(dataMap, execution);
    }

    public void callInterface(Map dataMap, DelegateExecution execution) {
//        List<Map<String, Object>> resultListMap = new ArrayList<Map<String, Object>>();
        Map<String, Object> resultMap = new HashMap<>();
        try {
            boolean bool = false;  // true-黑名单命中 ，false-没命中 黑名单
            String identityCard = String.valueOf(dataMap.get("identityCard"));
            String mobilePhone = String.valueOf(dataMap.get("mobilePhone"));
            String realName = String.valueOf(dataMap.get("realName"));

            OldLaiIn in = new OldLaiIn();
            in.setIdentityCard(identityCard);
            in.setQueryType("1");
            Result<SelfDtoOut> result = eipServiceInterface.self(in);
            if (ObjectUtils.isNotEmpty(result) && "0000".equals(result.getReturnCode())) {
                if ("1".equals(result.getData().getIsBlacklistUser())) { // 命中黑名单
                    bool = true;
                    resultMap.put("HitSelf", "Y");
                }
            }
            if (!bool) {
                NetLoanIn in1 = new NetLoanIn();
                in1.setMobilePhone(mobilePhone);
                in1.setIdentityCard(identityCard);
                in1.setRealName(realName);
                Result<NetLoanOut> result1 = eipServiceInterface.netLoan(in1);
                if (ObjectUtils.isNotEmpty(result1) && "0000".equals(result1.getReturnCode())) {
                    NetLoanOut out = result1.getData();
                    boolean b1 = Integer.parseInt(out.getMaxOverduedays()) > 90 && false;
                    boolean b2 = Integer.parseInt(out.getMaxOverduedays()) > 60 && Integer.parseInt(out.getOverduepayout()) > 500;
                    boolean b3 = (Integer.parseInt(out.getOverduepayout()) > 500 && Integer.parseInt(out.getMaxOverduedays()) >= 5) && (false);
                    if (b1 || b2 || b3) {
                        bool = true;
                        resultMap.put("HitNetLoan", "Y");
                    }
                }
            }
            if (!bool) {
                OldLaiIn in2 = new OldLaiIn();
                in2.setIdentityCard(identityCard);
                in2.setRealName(realName);
                Result<OldLaiOut> result1 = eipServiceInterface.oldLai(in2);
                if (ObjectUtils.isNotEmpty(result1) && "0000".equals(result1.getReturnCode())) {
                    OldLaiOut out = result1.getData();
                    if (ObjectUtils.isNotEmpty(out)) {
                        bool = true;
                        resultMap.put("HitOldLai", "Y");
                    }
                }
            }

            //TODO  电话邦通话记录  ，此处需要传20条以上记录。。。。。。
            List<CallLog> callLog = (List<CallLog>) dataMap.get("CallLog");
            CallLog calog = new CallLog();
            calog.setCall_method(1);
            calog.setCall_tel(mobilePhone);
            callLog.add(calog);

            DianhuaCollectionMinDtoIn dianhuaIn = new DianhuaCollectionMinDtoIn();
            dianhuaIn.setMobilePhone(mobilePhone);
            dianhuaIn.setCallLog(callLog);
            Result<DianhuaCollectionMinDtoOut> dhResult = eipServiceInterface.collectionMin(dianhuaIn);
            if (ObjectUtils.isNotEmpty(dhResult) && "0000".equals(dhResult.getReturnCode())) {
                DianhuaCollectionMinDtoOut out = dhResult.getData();
                String callTotalTime = out.getCsData().getOverview().getDunning().getCallTotalTimes();
                String callAvgDuration = out.getCsData().getOverview().getDunning().getCallAvgDuration();
                if (Integer.parseInt(callTotalTime) > 5 && Integer.parseInt(callAvgDuration) > 200) {
                    // 命中规则
                    resultMap.put("HitCollectionMin", "Y");
                }
            }

            // 汇法网 失信记录
            LawxpWebankDtoIn webIn = new LawxpWebankDtoIn();
            webIn.setIdentityCard(identityCard);
            webIn.setRealName(realName);
            webIn.setStype("1");
            Result<LawxpWebankDtoOut> webResult = eipServiceInterface.webank(webIn);
            if (ObjectUtils.isNotEmpty(dhResult) && "0000".equals(dhResult.getReturnCode())) {
                LawxpWebankDtoOut out = webResult.getData();
                List<Allmsglist> msgList = out.getAllmsglist();
                msgList.forEach(m -> {
                    int bigType = m.getBigtype();
                    String type = m.getType();
                    if (bigType == 1 && ("暂缓执行".equals(type) || "执行中".equals(type) || "资产清理".equals(type))) {
                        resultMap.put("HitWebank", "Y");
                    }
                    if (bigType == 12 || bigType == 13 || bigType == 14 || bigType == 17 || bigType == 19 || bigType == 21 || bigType == 20) {
                        resultMap.put("HitWebank", "Y");
                    }
                });
            }

            // 前海征信
            try {
                FrontSeaDtoIn seaDtoIn = new FrontSeaDtoIn();
                seaDtoIn.setIdentityCard(identityCard);
                seaDtoIn.setIdType("1");
                seaDtoIn.setRealName(realName);
                seaDtoIn.setReasonNo("01");
                Result<FrontSeaDtoOut> result1 = eipServiceInterface.frontSea(seaDtoIn);
                List<Map<String, Object>> listMap = new ArrayList<>();
                if (ObjectUtils.isNotEmpty(result1) && "0000".equals(result1.getReturnCode())) {
                    FrontSeaDtoOut out = result1.getData();
                    boolean b1 = "B2".equals(out.getRskMark()) || "B1".equals(out.getRskMark());
                    Map<String, Object> mapDate = new HashMap<>();
                    mapDate.put("homeloadfrontsea_hitBlack", "是");
                    mapDate.put("homeloadfrontsea_belongexecutor", b1 ? "是" : "否");
                    mapDate.put("homeloadfrontsea_rskScore", out.getRskMark());
                    listMap.add(mapDate);
                    execution.setVariable(ActivitiConstants.DROOLS_VARIABLE_NAME + "frontsea", listMap); // 赋值规则所需变量
                }
            } catch (Exception e) {
                e.printStackTrace();
                log.error("前海征信执行异常+++++" + e.getMessage());
            }
//            resultListMap.add(resultMap);
            execution.setVariable(ActivitiConstants.PROC_HOME_LOAN_RESULT_CODE + "homeLoan", resultMap);

        } catch (Exception e) {
            log.error("执行异常+++++" + e.getMessage());
        }
    }
}
