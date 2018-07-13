package com.ht.risk.activiti.service.impl;

import com.alibaba.fastjson.JSON;
import com.ht.risk.activiti.config.SpringContextUtils;
import com.ht.risk.activiti.rpc.ActivitiConfigInterface;
import com.ht.risk.activiti.rpc.RuleServiceInterface;
import com.ht.risk.activiti.service.ModelProcessRefuseService;
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
import lombok.extern.log4j.Log4j2;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.Expression;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.*;

@Log4j2
@Service("modelProcessRefuse")
public class ModelProcessRefuseServiceImpl implements ModelProcessRefuseService {

    @Resource
    private TopicSenderServiceImpl topicSenderService;

    @Resource
    private ActivitiConfigInterface activitiConfigInterface;
    @Resource
    private RuleServiceInterface ruleServiceInterface;


    private Expression methodName;

    /**
     * @param execution
     * @throws Exception
     */
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        log.info("ModelProcessRefuseServiceImpl execute method excute start...");
        String method = String.valueOf(methodName.getValue(execution));
        String modelType = String.valueOf(execution.getVariable(ActivitiConstants.PROC_MODEL_EXCUTE_TYPE_KEY));
        String errorMsgStr = String.valueOf(execution.getVariable(ActivitiConstants.PROC_EXCUTE_ERROR_MSG));
        String msg = String.valueOf(execution.getVariable(ActivitiConstants.PROC_EXCUTE_MSG));
//        String ruleMsgStr = String.valueOf(execution.getVariable(ActivitiConstants.PROC_EXCUTE_HIT_RULE_MSG));
//        String msg = String.valueOf(execution.getVariable(ActivitiConstants.PROC_EXCUTE_MSG));
//        String taskIdStr = String.valueOf(execution.getVariable(ActivitiConstants.PROC_TASK_ID_VAR_KEY));

        ModelExcuteResult modelResult = new ModelExcuteResult();
        // 获取执行结果
        Map<String, Object> result = execution.getVariables();
        Set<String> nameKey = result.keySet();
        String name = null;
        List<RuleExcuteDetail> returnDetail = null;
        List<RuleExcuteDetail> details = new ArrayList<RuleExcuteDetail>();
        Map<String, List<RuleExcuteDetail>> resultMap = new HashMap<String, List<RuleExcuteDetail>>();
        StringBuffer hitMsg = new StringBuffer("");
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
        String taskIdStr = String.valueOf(execution.getVariable(ActivitiConstants.PROC_TASK_ID_VAR_KEY));
        String taskId = taskIdStr != null ? taskIdStr : null;
        modelResult.setCode("0");
        modelResult.setMsg("模型执行正常,满足禁入规则");
        modelResult.setProcInstId(execution.getProcessInstanceId());
        modelResult.setTaskId(taskId);
        modelResult.setProcMsg(msg);
        modelResult.setAuditType(AuditTypeEnum.REFUSE.getCode());
        String businessKey = String.valueOf(execution.getVariable(ActivitiConstants.PROC_BUSINESS_KEY));
        modelResult.setBusinessKey(businessKey);

        RuleExcuteDetail detail = null;
        if (details.size() > 0) {
            double scope = 0L;
            for (Iterator<RuleExcuteDetail> iterator = details.iterator(); iterator.hasNext(); ) {
                detail = iterator.next();
                String s = StringUtils.isNotEmpty(detail.getScope()) && !"null".equals(detail.getScope()) ? String.valueOf(detail.getScope()) : "0";
                scope += Double.parseDouble(s);
                getRulesDesc(detail);
            }
            modelResult.setRuleResultList(resultMap);
        }

        //更新任务状态
        long startTime = Long.parseLong(String.valueOf(execution.getVariable(ActivitiConstants.PROC_START_CURRENT_TIME)));
        updateTask(modelResult, taskId, execution.getProcessInstanceId(), startTime);
//        // MQ发送消息
        if (ActivitiConstants.EXCUTE_TYPE_SERVICE.equals(modelType)) { // 服务类型
            log.info("通用拒绝返回数据：>>>>>>>>" + JSON.toJSONString(modelResult));
            Class cs = topicSenderService.getClass();
            try {
                Method tmethod = cs.getMethod(method, String.class);
                try {
                    tmethod.invoke(topicSenderService, JSON.toJSONString(modelResult));//invoke是反射调用方法，方法里面有两个参数，第一个参数是调用这个方法的实例，第二个就是那个方法的参数类表
                } catch (Exception e) {
                    e.printStackTrace();
                }

//                Class cls = SpringContextUtils.getApplicationContext().getBean("topicSenderService").getClass();
//                Method method1 = cs.getMethod("sendTest", String.class);
//                method1.invoke(cls.newInstance(), JSON.toJSONString(modelResult));//invoke是反射调用方法，方法里面有两个参数，第一个参数是调用这个方法的实例，第二个就是那个方法的参数类表
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        log.info("ModelProcessRefuseServiceImpl execute end");
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
            log.info("ModelProcessRefuseServiceImpl execute method excute start..." + JSON.toJSONString(detail));
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


    private static String formatResultStr(String str) {
        if (com.ht.risk.common.util.StringUtils.isEmpty(str)) {
            return "";
        }
        str = str.replaceAll("\\:", "#").replaceAll("\\[禁入\\]\\#", "").replaceAll("\\(forbidden\\)", "").replaceAll("那么", "");
        while (str.contains("#")) {
            str = str.replaceFirst("\\$(.*?)\\#", "");
            str = str.replaceFirst("\\$", "");
        }
        return str;
    }
}
