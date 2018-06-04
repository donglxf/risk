package com.ht.risk.activiti.service.htapp.impl;

import com.alibaba.fastjson.JSON;
import com.ht.risk.activiti.rpc.ActivitiConfigInterface;
import com.ht.risk.activiti.rpc.RuleServiceInterface;
import com.ht.risk.activiti.service.htapp.HtAppResult;
import com.ht.risk.activiti.service.impl.TopicSenderServiceImpl;
import com.ht.risk.api.constant.activiti.ActivitiConstants;
import com.ht.risk.api.enums.AuditTypeEnum;
import com.ht.risk.api.model.activiti.ModelExcuteResult;
import com.ht.risk.api.model.activiti.RpcActExcuteTask;
import com.ht.risk.api.model.activiti.RuleExcuteDetail;
import com.ht.risk.api.model.rule.RpcRuleDetail;
import com.ht.risk.api.model.rule.RpcRuleHisVersion;
import com.ht.risk.api.model.rule.RpcRuleHisVersionParamter;
import com.ht.risk.common.result.Result;
import com.ht.risk.common.util.StringUtils;
import org.activiti.engine.delegate.DelegateExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service("htAppResult")
public class HtAppResultImpl implements HtAppResult {

    protected static final Logger LOGGER = LoggerFactory.getLogger(HtAppResultImpl.class);

    @Resource
    private TopicSenderServiceImpl topicSenderService;

    @Resource
    private ActivitiConfigInterface activitiConfigInterface;
    @Resource
    private RuleServiceInterface ruleServiceInterface;

    /**
     * @param execution
     * @throws Exception
     */
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        LOGGER.info("HtAppResultImpl execute method excute start...");
        String msg = String.valueOf(execution.getVariable(ActivitiConstants.PROC_EXCUTE_MSG));
        String modelType = String.valueOf(execution.getVariable(ActivitiConstants.PROC_MODEL_EXCUTE_TYPE_KEY));
        ModelExcuteResult modelResult = new ModelExcuteResult();
        // 获取执行结果
        Map<String, Object> result = execution.getVariables();
        Set<String> nameKey = result.keySet();
        String name = null;
        List<RuleExcuteDetail> returnDetail = null;
        List<RuleExcuteDetail> details = new ArrayList<RuleExcuteDetail>();
        Map<String, List<RuleExcuteDetail>> resultMap = new HashMap<String, List<RuleExcuteDetail>>();
        for (Iterator<String> iterator = nameKey.iterator(); iterator.hasNext(); ) {
            name = iterator.next();
            if (name.startsWith(ActivitiConstants.SENCE_EXCUTE_RESULT_VAR) && result.get(name) != null) {
                returnDetail = (List<RuleExcuteDetail>) result.get(name);
                name = name.replace(ActivitiConstants.SENCE_EXCUTE_RESULT_VAR, "");
                if (returnDetail != null && returnDetail.size() > 0) {
                    resultMap.put(name, returnDetail);
                }
                details.addAll(returnDetail);
            }
        }
        RuleExcuteDetail detail = null;
        String taskIdStr = String.valueOf(execution.getVariable(ActivitiConstants.PROC_TASK_ID_VAR_KEY));
        String taskId = taskIdStr != null ? taskIdStr : null;
        modelResult.setCode("0");
        modelResult.setMsg("模型执行正常");
        modelResult.setProcInstId(execution.getProcessInstanceId());
        modelResult.setTaskId(taskId);
        modelResult.setProcMsg(msg);
        modelResult.setAuditType(AuditTypeEnum.PERSONAL.getCode());
        String businessKey = String.valueOf(execution.getVariable(ActivitiConstants.PROC_BUSINESS_KEY));
        modelResult.setBusinessKey(businessKey);
        if (details.size() > 0) {
            double scope = 0L;
            for (Iterator<RuleExcuteDetail> iterator = details.iterator(); iterator.hasNext(); ) {
                detail = iterator.next();
                String s = StringUtils.isNotEmpty(detail.getScope()) && !"null".equals(detail.getScope()) ? String.valueOf(detail.getScope()) : "0";
                scope += Double.parseDouble(s);
                getRulesDesc(detail);
            }
            modelResult.setScope(String.valueOf(scope));
            modelResult.setRuleResultList(resultMap);
        }

        //更新任务状态
        long startTime = Long.parseLong(String.valueOf(execution.getVariable(ActivitiConstants.PROC_START_CURRENT_TIME)));
        updateTask(modelResult, taskId, execution.getProcessInstanceId(), startTime);
        // MQ发送消息
        if (ActivitiConstants.EXCUTE_TYPE_SERVICE.equals(modelType)) {// 服务类型
            LOGGER.info("评分卡返回业务系统数据：>>>>>>>>" + JSON.toJSONString(modelResult));
            topicSenderService.sendHtappScore(JSON.toJSONString(modelResult));
        }
        LOGGER.info("HtAppResultImpl execute method excute end...");
    }

    // 更新任务信息
    private void updateTask(ModelExcuteResult modelResult, String taskId, String procInstId, long startTime) {
        RpcActExcuteTask task = new RpcActExcuteTask();
        task.setStatus(ActivitiConstants.PROC_STATUS_SUCCESS);
        task.setUpdateTime(new Date(System.currentTimeMillis()));
        task.setId(taskId);
        long spendTime = System.currentTimeMillis() - startTime;
        task.setSpendTime(spendTime);
        task.setOutParamter(JSON.toJSONString(modelResult));
        task.setProcInstId(procInstId);
        activitiConfigInterface.updateTask(task);
    }

    // 获取策略表所有命中规则的规则描述
    private void getRulesDesc(RuleExcuteDetail detail) {
        if (detail == null || detail.getRuleList() == null) {
            return;
        }
        if (detail.getRuleList().size() == 0) {
            detail.setRuleList(null);
            return;
        }
        if (detail.getRuleList().size() > 0) {
            LOGGER.info("HtAppResultImpl execute method excute start..." + JSON.toJSONString(detail));
            RpcRuleHisVersionParamter vo = new RpcRuleHisVersionParamter();
            vo.setVersionId(Long.parseLong(detail.getSenceVersionId()));
            vo.settRuleName(detail.getRuleList());
            Result<List<RpcRuleHisVersion>> ruleDescResult = ruleServiceInterface.getHisVersionListByVidName(vo);
            if (ruleDescResult != null && ruleDescResult.getCode() == 0 && ruleDescResult.getData() != null) {
                List<RpcRuleHisVersion> hisVersions = ruleDescResult.getData();
                if (hisVersions != null) {
                    List<String> ruleList = new ArrayList<>();
                    List<String> ruleNameList = new ArrayList<>();
                    RpcRuleHisVersion version = null;
                    RpcRuleDetail rpcRuleDetail = null;
                    List<RpcRuleDetail> details = new ArrayList<>();
                    for (Iterator<RpcRuleHisVersion> vIterator = hisVersions.iterator(); vIterator.hasNext(); ) {
                        version = vIterator.next();
                        rpcRuleDetail = new RpcRuleDetail();
                        rpcRuleDetail.setRuleCode(version.getRuleName());
                        rpcRuleDetail.setRuleDesc(formatResultStr(version.getRuleDesc()));
                        details.add(rpcRuleDetail);
                    }
                    detail.setRuleList(null);
                    detail.setRuleDetails(details);
                }
            }
        }
    }

    private static String formatResultStr(String str) {
        if (StringUtils.isEmpty(str)) {
            return "";
        }
        String s1 = str.substring(str.lastIndexOf(":") + 1);
        str = str.substring(0, str.lastIndexOf(":"));
        str = str.replaceAll(":", "#").replaceAll("\\[输出\\]\\#", "").replaceAll("\\(输出值\\)", "").replaceAll("那么", "");
        while (str.contains("#")) {
            str = str.replaceFirst("\\$(.*?)\\#", "");
            str = str.replaceFirst("\\$", "");
        }
        return str + s1;
    }
}
