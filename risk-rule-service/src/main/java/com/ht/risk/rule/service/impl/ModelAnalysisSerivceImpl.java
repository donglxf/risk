package com.ht.risk.rule.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.Condition;
import com.ht.risk.api.constant.activiti.ActivitiConstants;
import com.ht.risk.api.model.activiti.RpcActExcuteTaskInfo;
import com.ht.risk.api.model.activiti.RpcModelReleaseInfo;
import com.ht.risk.api.model.activiti.RpcModelVerfication;
import com.ht.risk.api.model.drools.RpcDroolsLog;
import com.ht.risk.api.model.log.RpcHitRuleInfo;
import com.ht.risk.common.result.Result;
import com.ht.risk.rule.controller.ModelVerificationController;
import com.ht.risk.rule.entity.ModelSence;
import com.ht.risk.rule.entity.ValidateBatch;
import com.ht.risk.rule.entity.VariableBind;
import com.ht.risk.rule.mapper.ModelSenceMapper;
import com.ht.risk.rule.mapper.VariableBindMapper;
import com.ht.risk.rule.rpc.ActivitiConfigRpc;
import com.ht.risk.rule.rpc.DroolsLogRpc;
import com.ht.risk.rule.service.ModelAnalysisSerivce;
import com.ht.risk.rule.vo.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class ModelAnalysisSerivceImpl implements ModelAnalysisSerivce {

    private static final Logger LOGGER = LoggerFactory.getLogger(ModelAnalysisSerivceImpl.class);


    @Resource
    private ActivitiConfigRpc activitiConfigRpc;
    @Resource
    private DroolsLogRpc droolsLogRpc;
    @Resource
    private VariableBindMapper variableBindMapper;
    @Resource
    private ModelSenceMapper modelSenceMapper;

    @Override
    public Map<String,List<RpcHitRuleInfo>> queryModelVerfHitRuleInfo(String procInstanceId) {
        Map<String,List<RpcHitRuleInfo>> hitRuleMap = new HashMap<String,List<RpcHitRuleInfo>>();
        List<String> procInstIds = new ArrayList();
        procInstIds.add(procInstanceId);
        return getProcHitRules(procInstIds);
    }
    @Override
    public Map<String,List<RpcHitRuleInfo>> queryBatchModelVerfHitRuleInfo(Long batchId) {
        Map<String,List<RpcHitRuleInfo>> hitRuleMap = new HashMap<String,List<RpcHitRuleInfo>>();
        // 1 查询该批次的所有流程实例
        RpcModelVerfication rpcModelVerfication = new RpcModelVerfication();
        rpcModelVerfication.setBatchId(batchId);
        Result<List<RpcActExcuteTaskInfo>> result = activitiConfigRpc.queryTasksByBatchId(rpcModelVerfication);
        if(result == null || result.getCode() != 0 || result.getData() == null){
            return hitRuleMap;
        }
        // 2 查询流程实例触碰的规则
        List<String> procInstIds = new ArrayList<String>();
        for(Iterator<RpcActExcuteTaskInfo> iterator = result.getData().iterator();iterator.hasNext();){
            procInstIds.add(iterator.next().getProcInstId());
        }
        return getProcHitRules(procInstIds);
    }

    public Map<String,SenceParamterVo>  queryModeVerfDataInfo(String taskId) {
        Map<String,SenceParamterVo> senceMap = new HashMap<String,SenceParamterVo>();
        //获取模型任务信息
        RpcModelVerfication rpcModelVerfication = new RpcModelVerfication();
        rpcModelVerfication.setTaskId(taskId);
        Result<RpcActExcuteTaskInfo> result = activitiConfigRpc.getTaskInfoById(rpcModelVerfication);
        if(result == null || result.getCode() != 0 || result.getData() == null){
            return senceMap;
        }
        RpcActExcuteTaskInfo taskInfo = result.getData();
        // 获取数据
        RpcDroolsLog rpcDroolsLog = new RpcDroolsLog();
        rpcDroolsLog.setProcinstId(taskInfo.getProcInstId());
        Result<List<RpcDroolsLog>> logResult = droolsLogRpc.queryTestModelDroolsLogs(rpcDroolsLog);
        if(logResult == null || logResult.getCode() != 0 || logResult.getData() == null){
            return senceMap;
        }
        List<RpcDroolsLog> logs = logResult.getData();
        // 获取模型定义信息
        rpcModelVerfication.setProcReleaseId(Long.parseLong(taskInfo.getProcReleaseId()));
        Result<RpcModelReleaseInfo> releaseInfoResult = activitiConfigRpc.getProcReleaseById(rpcModelVerfication);
        if(releaseInfoResult == null || releaseInfoResult.getCode() != 0 || releaseInfoResult.getData() == null){
            return senceMap;
        }
        // 获取模型关联决策信息
        List<ModelSence> sences = modelSenceMapper.queryModelSenceInfo(releaseInfoResult.getData().getModelProcdefId());
        List<Long> versionIds = new ArrayList<Long>();
        ModelSence sence = null;
        SenceParamterVo paramterVo = null;
        for(Iterator<ModelSence> iterator = sences.iterator();iterator.hasNext();){
            sence = iterator.next();
            versionIds.add(sence.getSenceVersionId());
            paramterVo = new SenceParamterVo();
            paramterVo.setSenceName(sence.getSceneName());
            senceMap.put(String.valueOf(sence.getSenceVersionId()),paramterVo);
        }
        RpcDroolsLog log = null;
        for(Iterator<RpcDroolsLog> iterator = logs.iterator();iterator.hasNext();){
            log = iterator.next();
            if(log.getInParamter() == null){
                continue;
            }
            if(senceMap.containsKey(log.getSenceVersionid())){
                paramterVo = senceMap.get(log.getSenceVersionid());
                paramterVo.setData(JSON.parseObject(log.getInParamter(),HashMap.class));
            }
        }
        //Map<String, Object> data =  JSON.parseObject(taskInfo.getInParamter(),HashMap.class);
        //获取模型需要变量信息
        List<VariableBind> binds = variableBindMapper.selectList(Condition.create().in("sence_version_id",versionIds));
        String variableCode = null;
        VariableBind bind = null;
        VariableVo vo = null;
        List<VariableVo> vos = null;
        SenceParamterVo senceParamterVo = null;
        for(Iterator<VariableBind> iterator = binds.iterator();iterator.hasNext();){
            bind = iterator.next();
            variableCode = bind.getVariableCode();
            vo = new VariableVo(bind);
            if(senceMap.containsKey(bind.getSenceVersionId())){
                paramterVo =senceMap.get(bind.getSenceVersionId());
                if(paramterVo.getData() != null){
                    vo.setValue(String.valueOf(paramterVo.getData().get(vo.getValibaleEn())));
                }
                paramterVo.addVariableVo(vo);
            }
        }
        return senceMap;
    }

    @Override
    public VerficationResultVo queryTaskVerficationResult(String taskId) {
        LOGGER.info("queryTaskVerficationResult method invoke start,paramter:"+taskId);
        VerficationResultVo resultVo = new VerficationResultVo();
        Map<String,SenceParamterVo> senceMap = new HashMap<String,SenceParamterVo>();
        // 查询任务详情
        RpcModelVerfication rpcModelVerfication = new RpcModelVerfication();
        rpcModelVerfication.setTaskId(taskId);
        resultVo.setTaskId(String.valueOf(taskId));
        Result<RpcActExcuteTaskInfo> taskResult = activitiConfigRpc.getTaskInfoById(rpcModelVerfication);
        LOGGER.info("rpc:activitiConfigRpc.queryTasksByBatchId ,result:"+JSON.toJSONString(taskResult));
        if(taskResult == null || taskResult.getCode() != 0 ||  taskResult.getData() == null){
            resultVo.setMessage("模型执行失败,未查询模型执行信息！");
            resultVo.setValidateFlag("1");
            return resultVo;
        }
        RpcActExcuteTaskInfo task = taskResult.getData();
        if(task == null || StringUtils.isEmpty(task.getStatus()) || ActivitiConstants.PROC_STATUS_EXCEPTION.equals(task.getStatus())){
            resultVo.setMessage("模型执行失败！");
            resultVo.setValidateFlag("1");
            return resultVo;
        }
        resultVo.setProcReleaseId(task.getProcReleaseId());
        String type = task.getType();

        // 获取模型定义信息
        rpcModelVerfication.setProcReleaseId(Long.parseLong(task.getProcReleaseId()));
        Result<RpcModelReleaseInfo> releaseInfoResult = activitiConfigRpc.getProcReleaseById(rpcModelVerfication);
        LOGGER.info("rpc:activitiConfigRpc.getProcReleaseById ,result:"+JSON.toJSONString(releaseInfoResult));
        if(releaseInfoResult == null || releaseInfoResult.getCode() != 0 || releaseInfoResult.getData() == null){
            resultVo.setMessage("模型执行失败,未查询模型定义信息！");
            resultVo.setValidateFlag("1");
            return resultVo;
        }
        // 查询任务规则命中信息
        RpcDroolsLog rpcDroolsLog = new RpcDroolsLog();
        rpcDroolsLog.setProcinstId(task.getProcInstId());
        if(task.getProcInstId() == null){
            resultVo.setMessage("模型执行失败，模型实例未启动！");
            resultVo.setValidateFlag("1");
            return resultVo;
        }
        // 获取模型关联决策信息
        List<ModelSence> sences = modelSenceMapper.queryModelSenceInfo(releaseInfoResult.getData().getModelProcdefId());
        if(sences == null || sences.size() ==0){
            resultVo.setMessage("模型执行成功！");
            return resultVo;
        }
        Result<List<RpcDroolsLog>> logResult = null;
        if(ActivitiConstants.EXCUTE_TYPE_SERVICE.equals(type)){
            logResult= droolsLogRpc.queryModelDroolsLogs(rpcDroolsLog);
        }else{
            logResult= droolsLogRpc.queryTestModelDroolsLogs(rpcDroolsLog);
        }
        LOGGER.info("rpc:activitiConfigRpc.queryTestModelDroolsLogs ,result:"+JSON.toJSONString(logResult));
        if(logResult == null || logResult.getCode() != 0 || logResult.getData() == null){
            resultVo.setMessage("未触发规则引擎！");
            resultVo.setValidateFlag("1");
            return resultVo;
        }
        List<RpcDroolsLog> logs = logResult.getData();
        List<Long> versionIds = new ArrayList<Long>();
        ModelSence sence = null;
        SenceParamterVo paramterVo = null;
        for(Iterator<ModelSence> iterator = sences.iterator();iterator.hasNext();){
            sence = iterator.next();
            versionIds.add(sence.getSenceVersionId());
            paramterVo = new SenceParamterVo();
            paramterVo.setSenceName(sence.getSceneName());
            senceMap.put(String.valueOf(sence.getSenceVersionId()),paramterVo);
        }
        List<VariableBind> binds = variableBindMapper.selectList(Condition.create().in("sence_version_id",versionIds));
        String variableCode = null;
        VariableBind bind = null;
        VariableVo vo = null;
        List<VariableVo> vos = null;
        SenceParamterVo senceParamterVo = null;
        for(Iterator<VariableBind> iterator = binds.iterator();iterator.hasNext();){
            bind = iterator.next();
            variableCode = bind.getVariableCode();
            vo = new VariableVo(bind);
            if(senceMap.containsKey(String.valueOf(bind.getSenceVersionId()))){
                paramterVo =senceMap.get(String.valueOf(bind.getSenceVersionId()));
                if(paramterVo.getData() != null){
                    vo.setValue(String.valueOf(paramterVo.getData().get(vo.getValibaleEn())));
                }
                paramterVo.addVariableVo(vo);
            }
        }
        Result<List<RpcHitRuleInfo>> result = null;
        if(ActivitiConstants.EXCUTE_TYPE_SERVICE.equals(type)){
            result= droolsLogRpc.getHitRuleInfo(task.getProcInstId());
        }else{
            result= droolsLogRpc.getTestHitRuleInfo(task.getProcInstId());
        }

        if(result == null || result.getCode() != 0){
            return null;
        }
        List<RpcHitRuleInfo> hitRuleInfoDatas= result.getData();
        List<RpcHitRuleInfo> tmp = null;
        RpcHitRuleInfo tmpRule = null;
        HitRuleInfoVo ruleInfoVo = null;
        List<RpcHitRuleInfo> hitRules = new ArrayList<RpcHitRuleInfo>();
        for(Iterator<RpcHitRuleInfo> iterator = hitRuleInfoDatas.iterator();iterator.hasNext();){
            tmpRule = iterator.next();
            String senceVersionId = String.valueOf(tmpRule.getSenceVersionId());
            if(senceMap.containsKey(senceVersionId)){
                paramterVo =senceMap.get(senceVersionId);
                ruleInfoVo = new HitRuleInfoVo(tmpRule);
                paramterVo.addHitRuleVo(ruleInfoVo);
            }
            if(tmpRule.getCount() > 0){
                hitRules.add(tmpRule);
            }
        }

        Set set = senceMap.keySet();
        List<SenceParamterVo> list = new ArrayList<SenceParamterVo>();
        for (Iterator<String> iterator = set.iterator(); iterator.hasNext(); ) {
            list.add(senceMap.get(iterator.next()));
        }
        resultVo.setSences(list);
        resultVo.setHitRules(hitRules);
        resultVo.setMessage("模型执行成功！");
        resultVo.setValidateFlag("0");
        return resultVo;
    }

    @Override
    public VerficationResultVo queryTaskServiceResult(String taskId) {
        LOGGER.info("queryTaskVerficationResult method invoke start,paramter:"+taskId);
        VerficationResultVo resultVo = new VerficationResultVo();
        Map<String,SenceParamterVo> senceMap = new HashMap<String,SenceParamterVo>();
        // 查询任务详情
        RpcModelVerfication rpcModelVerfication = new RpcModelVerfication();
        rpcModelVerfication.setTaskId(taskId);
        resultVo.setTaskId(String.valueOf(taskId));
        Result<RpcActExcuteTaskInfo> taskResult = activitiConfigRpc.getTaskInfoById(rpcModelVerfication);
        LOGGER.info("rpc:activitiConfigRpc.queryTasksByBatchId ,result:"+JSON.toJSONString(taskResult));
        if(taskResult == null || taskResult.getCode() != 0 ||  taskResult.getData() == null){
            resultVo.setMessage("模型执行失败,未查询模型执行信息！");
            resultVo.setValidateFlag("1");
            return resultVo;
        }
        RpcActExcuteTaskInfo task = taskResult.getData();
        resultVo.setProcReleaseId(task.getProcReleaseId());
        // 获取模型定义信息
        rpcModelVerfication.setProcReleaseId(Long.parseLong(task.getProcReleaseId()));
        Result<RpcModelReleaseInfo> releaseInfoResult = activitiConfigRpc.getProcReleaseById(rpcModelVerfication);
        LOGGER.info("rpc:activitiConfigRpc.getProcReleaseById ,result:"+JSON.toJSONString(releaseInfoResult));
        if(releaseInfoResult == null || releaseInfoResult.getCode() != 0 || releaseInfoResult.getData() == null){
            resultVo.setMessage("模型执行失败,未查询模型定义信息！");
            resultVo.setValidateFlag("1");
            return resultVo;
        }
        // 查询任务规则命中信息
        RpcDroolsLog rpcDroolsLog = new RpcDroolsLog();
        rpcDroolsLog.setProcinstId(task.getProcInstId());
        if(task.getProcInstId() == null){
            resultVo.setMessage("模型执行失败，模型实例未启动！");
            resultVo.setValidateFlag("1");
            return resultVo;
        }
        // 获取模型关联决策信息
        List<ModelSence> sences = modelSenceMapper.queryModelSenceInfo(releaseInfoResult.getData().getModelProcdefId());
        if(sences == null || sences.size() ==0){
            resultVo.setMessage("模型执行成功！");
            return resultVo;
        }
        Result<List<RpcDroolsLog>> logResult = droolsLogRpc.queryModelDroolsLogs(rpcDroolsLog);
        LOGGER.info("rpc:activitiConfigRpc.queryTestModelDroolsLogs ,result:"+JSON.toJSONString(logResult));
        if(logResult == null || logResult.getCode() != 0 || logResult.getData() == null){
            resultVo.setMessage("模型执行异常，未触发规则引擎！");
            resultVo.setValidateFlag("1");
            return resultVo;
        }
        List<RpcDroolsLog> logs = logResult.getData();
        List<Long> versionIds = new ArrayList<Long>();
        ModelSence sence = null;
        SenceParamterVo paramterVo = null;
        for(Iterator<ModelSence> iterator = sences.iterator();iterator.hasNext();){
            sence = iterator.next();
            versionIds.add(sence.getSenceVersionId());
            paramterVo = new SenceParamterVo();
            paramterVo.setSenceName(sence.getSceneName());
            senceMap.put(String.valueOf(sence.getSenceVersionId()),paramterVo);
        }
        List<VariableBind> binds = variableBindMapper.selectList(Condition.create().in("sence_version_id",versionIds));
        String variableCode = null;
        VariableBind bind = null;
        VariableVo vo = null;
        List<VariableVo> vos = null;
        SenceParamterVo senceParamterVo = null;
        for(Iterator<VariableBind> iterator = binds.iterator();iterator.hasNext();){
            bind = iterator.next();
            variableCode = bind.getVariableCode();
            vo = new VariableVo(bind);
            if(senceMap.containsKey(String.valueOf(bind.getSenceVersionId()))){
                paramterVo =senceMap.get(String.valueOf(bind.getSenceVersionId()));
                if(paramterVo.getData() != null){
                    vo.setValue(String.valueOf(paramterVo.getData().get(vo.getValibaleEn())));
                }
                paramterVo.addVariableVo(vo);
            }
        }
        Result<List<RpcHitRuleInfo>> result = droolsLogRpc.getHitRuleInfo(task.getProcInstId());
        if(result == null || result.getCode() != 0){
            return null;
        }
        List<RpcHitRuleInfo> hitRuleInfoDatas= result.getData();
        List<RpcHitRuleInfo> tmp = null;
        RpcHitRuleInfo tmpRule = null;
        HitRuleInfoVo ruleInfoVo = null;
        List<RpcHitRuleInfo> hitRules = new ArrayList<RpcHitRuleInfo>();
        for(Iterator<RpcHitRuleInfo> iterator = hitRuleInfoDatas.iterator();iterator.hasNext();){
            tmpRule = iterator.next();
            String senceVersionId = String.valueOf(tmpRule.getSenceVersionId());
            if(senceMap.containsKey(senceVersionId)){
                paramterVo =senceMap.get(senceVersionId);
                ruleInfoVo = new HitRuleInfoVo(tmpRule);
                paramterVo.addHitRuleVo(ruleInfoVo);
            }
            if(tmpRule.getCount() > 0){
                hitRules.add(tmpRule);
            }
        }

        Set set = senceMap.keySet();
        List<SenceParamterVo> list = new ArrayList<SenceParamterVo>();
        for (Iterator<String> iterator = set.iterator(); iterator.hasNext(); ) {
            list.add(senceMap.get(iterator.next()));
        }
        resultVo.setSences(list);
        resultVo.setHitRules(hitRules);
        resultVo.setMessage("模型执行成功！");
        resultVo.setValidateFlag("0");
        return resultVo;
    }

    @Override
    public Long createBatchVerficationTask() {
        return null;
    }

    @Override
    public Long createSingleVerficationTask() {
        //

        return null;
    }

    @Override
    public Map getAutoVerficationData(String senceCode, String version) {
        Map<String,String> paramter = new HashMap<String,String>();
        paramter.put("senceCode",senceCode);
        paramter.put("version",version);
        List<VariableBind> binds = variableBindMapper.querySenceBindTableInfo(paramter);
        if(binds.size() > 0){
            String tableName = null;
            String columnName = null;
            VariableBind variableBind = null;
            // SELECT * FROM tablename ORDER BY RAND() LIMIT 1。
            StringBuffer sqlBuf = new StringBuffer("select ");
            String sql = null;
            for (Iterator<VariableBind> iterator = binds.iterator();iterator.hasNext();){
                variableBind = iterator.next();
                tableName = variableBind.getBindTable();
                columnName = variableBind.getBindColumn();
                sqlBuf.append("columnName").append(",");
            }
            sql = sqlBuf.toString();
            sql = sql.substring(0,sql.length()-1);
            sql += " from " +tableName +" order by rand() limit 1";
            paramter = new HashMap<String,String>();
            paramter.put("sql",sql);
            List<Map> data = variableBindMapper.getRandAutoData(paramter);
            if(data != null && data.size() > 0){
                return data.get(0);
            }
        }
        return null;
    }

    //TODO
    @Override
    public ModelLogDetailVo queryModelLogResult(String procInstId, String type,String taskId) {
        LOGGER.info("queryTaskVerficationResult method invoke start,paramter:"+procInstId);
        ModelLogDetailVo resultVo = new ModelLogDetailVo();
        if(StringUtils.isEmpty(type) || StringUtils.isEmpty(taskId)){
            resultVo.setMsg("参数异常。。。");
            return resultVo;
        }
        if(StringUtils.isEmpty(procInstId)){
            resultVo.setMsg("模型执行异常。。。");
            return resultVo;
        }
        // 查询任务详情
        RpcModelVerfication rpcModelVerfication = new RpcModelVerfication();
        rpcModelVerfication.setTaskId(taskId);
        Result<RpcActExcuteTaskInfo> taskResult = activitiConfigRpc.getTaskInfoById(rpcModelVerfication);
        LOGGER.info("rpc:activitiConfigRpc.queryTasksByBatchId ,result:"+JSON.toJSONString(taskResult));
        if(taskResult == null || taskResult.getCode() != 0 ||  taskResult.getData() == null){
            resultVo.setMsg("模型执行失败,未查询模型执行信息。。。");
            return resultVo;
        }
        RpcActExcuteTaskInfo task = taskResult.getData();
        String outParamter = task.getOutParamter();
        if(StringUtils.isNotEmpty(outParamter)){
            Map<String,Object> outParamterMap = JSON.parseObject(outParamter,Map.class);
            resultVo.setModelMsg(String.valueOf(outParamterMap.get("procMsg")));
        }
        Result<List<RpcHitRuleInfo>> result = null;
        if(ActivitiConstants.EXCUTE_TYPE_SERVICE.equals(type)){
            result= droolsLogRpc.getHitRuleInfo(procInstId);
        }else{
            result= droolsLogRpc.getTestHitRuleInfo(procInstId);
        }
        if(result == null || result.getCode() != 0){
            return null;
        }
        List<RpcHitRuleInfo> hitRuleInfoDatas= result.getData();
        RpcHitRuleInfo tmpRule = null;
        Map<String,RpcHitRuleInfo> ruleMap = new HashMap<String,RpcHitRuleInfo>();
        for(Iterator<RpcHitRuleInfo> iterator = hitRuleInfoDatas.iterator();iterator.hasNext();){
            tmpRule = iterator.next();
            if(tmpRule.getCount() == 0){
                continue;
            }
            if(ruleMap.containsKey(tmpRule.getRuleName())){
                tmpRule = ruleMap.get(tmpRule.getRuleName());
                tmpRule.setCount(tmpRule.getCount()+1);
            }else{
                ruleMap.put(tmpRule.getRuleName(),tmpRule);
            }
        }
        List<RpcHitRuleInfo> hitRules = new ArrayList<RpcHitRuleInfo>();
        Set<String> ruleKey = ruleMap.keySet();
        for(Iterator<String> iterator = ruleKey.iterator();iterator.hasNext();){
            String key = iterator.next();
            hitRules.add(ruleMap.get(key));
        }
        resultVo.setHitRules(hitRules);
        resultVo.setMsg("模型执行成功。。。");
        return resultVo;
    }


    private Map<String,List<RpcHitRuleInfo>> getProcHitRules(List<String> procInstIds){
        Map<String,List<RpcHitRuleInfo>> hitRuleMap = new HashMap<String,List<RpcHitRuleInfo>>();
        Result<List<RpcHitRuleInfo>> result = droolsLogRpc.countHitRuleInfo(procInstIds);
        if(result == null || result.getCode() != 0){
            return hitRuleMap;
        }
        List<RpcHitRuleInfo> hitRuleInfoDatas= result.getData();
        List<RpcHitRuleInfo> tmp = null;
        RpcHitRuleInfo tmpRule = null;
        for(Iterator<RpcHitRuleInfo> iterator = hitRuleInfoDatas.iterator();iterator.hasNext();){
            tmpRule = iterator.next();
            String senceVersionId = String.valueOf(tmpRule.getSenceVersionId());
            if(hitRuleMap.containsKey(senceVersionId)){
                tmp = hitRuleMap.get(senceVersionId);
                tmp.add(tmpRule);
            }else{
                tmp = new ArrayList<RpcHitRuleInfo>();
                tmp.add(tmpRule);
                hitRuleMap.put(senceVersionId,tmp);
            }
        }
        return hitRuleMap;
    }
}
