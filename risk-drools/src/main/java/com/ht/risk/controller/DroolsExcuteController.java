package com.ht.risk.controller;

import java.util.*;

import javax.annotation.Resource;

import com.ht.risk.api.model.drools.DroolsParamter;
import com.ht.risk.api.model.drools.MulitDroolsParamter;
import com.ht.risk.api.model.drools.RuleStandardResult;
import com.ht.risk.common.comenum.RuleCallTypeEnum;
import com.ht.risk.model.*;
import com.ht.risk.service.*;
import com.ht.risk.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSON;
import com.ht.risk.common.model.RuleExcuteResult;
import com.ht.risk.common.util.ObjectUtils;
import com.ht.risk.model.fact.RuleExecutionObject;
import com.ht.risk.model.fact.RuleExecutionResult;
import sun.reflect.generics.scope.Scope;

@RestController
public class DroolsExcuteController {

    protected static final Logger log = LoggerFactory.getLogger(DroolsExcuteController.class);

    @Resource
    private DroolsRuleEngineService droolsRuleEngineService;

    @Resource
    private DroolsLogService droolsLogService;

    @Resource
    private DroolsDetailLogService droolsProcessLogService;

    @Resource
    private TestDroolsLogService testDroolsLogService;

    @Resource
    private TestDroolsDetailLogService testDroolsDetailLogService;

    @Autowired
    private RuleSceneVersionService ruleSceneVersionService;

    @Autowired
    private RedisTemplate<String, String> redis;

    // 作废
    @SuppressWarnings("unchecked")
    @RequestMapping("/excuteDroolsScene")
    public RuleExcuteResult excuteDroolsScene(@RequestBody DroolsParamter paramter) {
        RuleExcuteResult data = null;
        // 业务数据转化 
        try {
            log.info("规则入参：" + JSON.toJSONString(paramter));
            Long startTime = System.currentTimeMillis();
            BaseRuleSceneInfo baseRuleInfo = new BaseRuleSceneInfo();
            // 1.根据sceneCode 查询最新测试版本
            Map<String, Object> parmaMap = new HashMap<String, Object>();
            parmaMap.put("type", "1"); // 正式版标志
            parmaMap.put("sceneIdentify", paramter.getSence()); // 决策code
            parmaMap.put("version", paramter.getVersion());
//            parmaMap.put("versionId", paramter.getVersion());
            RuleSceneVersion ruleVersion = ruleSceneVersionService.getInfoByVersionId(parmaMap);
            if (ObjectUtils.isEmpty(ruleVersion)) {
                data = new RuleExcuteResult(1, paramter.getSence() + "无可用正式版发布信息,请检查", null, null);
                return data;
            }
            RuleExecutionObject object = new RuleExecutionObject();
            Map<String, Object> mapData = paramter.getData();
            object.addFactObject(mapData);
            RuleExecutionResult result = new RuleExecutionResult();
            object.setGlobal("_result", result);
            object = this.droolsRuleEngineService.excute1(object, ruleVersion);
            Long endTime = System.currentTimeMillis();
            Long executeTime = endTime - startTime;
            log.info("规则验证执行时间》》》》》" + String.valueOf(executeTime));
//            object = this.droolsRuleEngineService.excute(object, sceneId);

            // 记录日志
            RuleExecutionResult res = (RuleExecutionResult) object.getGlobalMap().get("_result");
            List<String> li = (List<String>) res.getMap().get("ruleList");
            List<String> newList = new ArrayList();
            if (ObjectUtils.isNotEmpty(li)) {
                Set set = new HashSet();
                set.addAll(li);
                newList.addAll(set);
            }
            DroolsLog entity = new DroolsLog();
            entity.setType(paramter.getType());
            entity.setProcinstId(paramter.getProcessInstanceId());
            entity.setInParamter(JSON.toJSONString(paramter));
            entity.setSenceVersionid(String.valueOf(ruleVersion.getVersionId()));
            entity.setOutParamter(JSON.toJSONString(object));
            entity.setExecuteTotal(newList.size());
            entity.setModelName(paramter.getModelName());
            entity.setExecuteTime(executeTime);
            droolsLogService.insertOrUpdate(entity);
            String logId = entity.getId();
            if (ObjectUtils.isNotEmpty(li)) {
                for (String string : newList) {
                    DroolsDetailLog process = new DroolsDetailLog();
                    process.setDroolsLogid(logId);
                    process.setExecuteRulename(string);
                    droolsProcessLogService.insertOrUpdate(process);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            data = new RuleExcuteResult(1, "执行异常", null, null);
        }
        log.info("规则出参：" + JSON.toJSONString(paramter));
        return data;
    }

    /**
     * 批量测试-自动测试
     *
     * @param paramters
     * @return
     */
    @RequestMapping("/batchExcuteRuleValidation")
    public List<RuleExcuteResult> batchExcuteRuleValidation(@RequestBody List<DroolsParamter> paramters) {
        RuleExcuteResult data = null;
        // 业务数据转化
        List<RuleExcuteResult> list = new ArrayList<RuleExcuteResult>();
        try {
            for (DroolsParamter param : paramters) {
                RuleExcuteResult result = excuteDroolsSceneValidation(param);
                list.add(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
            data = new RuleExcuteResult(1, e.getMessage(), null, null);
        }
        return list;
    }

    /**
     * 手动验证
     *
     * @return
     */
    @RequestMapping(value = "/excuteDroolsSceneValidation")
    public RuleExcuteResult excuteDroolsSceneValidation(@RequestBody DroolsParamter paramter) {
        RuleExcuteResult data = null;
        // 业务数据转化
        try {
            log.info("excuteDroolsSceneValidation==>规则验证入参：" + JSON.toJSONString(paramter));
            RuleSceneVersion ruleVersion = null;
            boolean bool = true; // true-正式,false-测试
            if (RuleCallTypeEnum.rule.getType().equals(paramter.getType())) { // 规则调用
                bool = false;
            }
            String version = "测试版";
            Long startTime = System.currentTimeMillis();
            Map<String, Object> parmaMap = new HashMap<String, Object>();
            // 1.根据sceneCode 查询最新测试版本
            if (bool) {
                parmaMap.put("type", 1); // 正式版
                version = "正式版";
            } else {
                parmaMap.put("type", 0);
            }
            parmaMap.put("sceneIdentify", paramter.getSence()); // 决策code
            parmaMap.put("version", paramter.getVersion());
            List<RuleSceneVersion> versions = ruleSceneVersionService.getSenceVersion(parmaMap);
            if (versions == null || versions.size() == 0) {
                data = new RuleExcuteResult(1, paramter.getSence() + "参数出错，无可用" + version + "版本信息,请检查", null, paramter.getVersion());
                return data;
            }
            ruleVersion = versions.get(0);
            RuleExecutionObject object = new RuleExecutionObject();
            RuleExecutionResult result = new RuleExecutionResult();
            object.setGlobal("_result", result);
            Map<String, Object> mapData = paramter.getData();
            object.addFactObject(mapData);
            object = this.droolsRuleEngineService.excute1(object, ruleVersion);
            Long endTime = System.currentTimeMillis();
            Long executeTime = endTime - startTime;
            log.info("rule exec time》》》》》" + String.valueOf(executeTime));
            data = new RuleExcuteResult(0, "success", saveLog(object, paramter, ruleVersion, executeTime), paramter.getVersion());
            data.setSenceVersoionId(String.valueOf(ruleVersion.getVersionId()));
        } catch (Exception e) {
            log.error("规则执行异常：======》", e);
            data = new RuleExcuteResult(1, e.getMessage(), null, paramter.getVersion());
        }
        log.info("rule exe result>>>：" + JSON.toJSONString(data));
        return data;
    }

    /**
     * 支持多组数据验证
     *
     * @return
     */
    @RequestMapping(value = "/mulitExcuteDroolsScene")
    public RuleExcuteResult mulitExcuteDroolsScene(@RequestBody MulitDroolsParamter paramter) {
        RuleExcuteResult data = null;
        // 业务数据转化
        try {
            log.info("mulitExcuteDroolsScene==>规则验证入参：" + JSON.toJSONString(paramter));
            RuleSceneVersion ruleVersion = null;
            boolean bool = true; // true-正式,false-测试
            if (RuleCallTypeEnum.rule.getType().equals(paramter.getType())) { // 规则调用
                bool = false;
            }
            String version = "测试版";
            Long startTime = System.currentTimeMillis();
            Map<String, Object> parmaMap = new HashMap<String, Object>();
            // 1.根据sceneCode 查询最新测试版本
            if (bool) {
                parmaMap.put("type", 1); // 正式版
                version = "正式版";
            } else {
                parmaMap.put("type", 0);
            }
            parmaMap.put("sceneIdentify", paramter.getSence()); // 决策code
            parmaMap.put("version", paramter.getVersion());
            String ruleDrl = redis.opsForValue().get(paramter.getSence() + paramter.getVersion()); // 首先到redis查找看有无缓存
            if (StringUtils.isNotEmpty(ruleDrl)) {
                ruleVersion = JSON.parseObject(ruleDrl, RuleSceneVersion.class);
            } else {
                List<RuleSceneVersion> versions = ruleSceneVersionService.getSenceVersion(parmaMap);
                if (versions == null || versions.size() == 0) {
                    data = new RuleExcuteResult(1, paramter.getSence() + "参数出错，无可用" + version + "版本信息,请检查", null, paramter.getVersion());
                    return data;
                }
                ruleVersion = versions.get(0);
                redis.opsForValue().set(paramter.getSence() + ruleVersion.getOfficialVersion(), JSON.toJSONString(ruleVersion)); // 规则信息存入redis缓存
            }
            RuleExecutionObject object = new RuleExecutionObject();
            RuleExecutionResult result = new RuleExecutionResult();
            object.setGlobal("_result", result);
            List<String> logList = new ArrayList<>();
            List<Map<String, Object>> mapData = paramter.getMulitDate();
            double totalScope = 0l;
            for (int i = 0; i < mapData.size(); i++) {
                object.addFactObject(mapData.get(i));
                object = this.droolsRuleEngineService.excute1(object, ruleVersion);
                Long endTime = System.currentTimeMillis();
                Long executeTime = endTime - startTime;
                logList.addAll(batchSaveLog(object, paramter, ruleVersion, executeTime));
                RuleExecutionResult res = (RuleExecutionResult) object.getGlobalMap().get("_result");
                String scope = String.valueOf(res.getMap().get("scope"));
                if (StringUtil.strIsNotNull(scope)) {
                    Double scrope = Double.parseDouble(scope);
                    totalScope += scrope;
                }
                log.info("mulitRule exec time》》》》》" + String.valueOf(executeTime));
            }

            data = new RuleExcuteResult(0, "success", batchResultPro(object, logList), paramter.getVersion(), String.valueOf(totalScope));
            data.setSenceVersoionId(String.valueOf(ruleVersion.getVersionId()));
        } catch (Exception e) {
            log.error("规则执行异常：======》", e);
            data = new RuleExcuteResult(1, e.getMessage(), null, paramter.getVersion(), null);
        }
        log.info("mulitRule exe result>>>：" + paramter.getSence() + ">>>>>>" + JSON.toJSONString(data));
        return data;
    }

    public RuleStandardResult batchResultPro(RuleExecutionObject object, List<String> logIdList) {
        RuleExecutionResult res = (RuleExecutionResult) object.getGlobalMap().get("_result");
        List<String> reulst = (List<String>) res.getMap().get("result"); // 规则计算返回结果(输出提示类动作)
        List<String> li = (List<String>) res.getMap().get("ruleList"); // 命中规则名列表
        String scrope = String.valueOf(res.getMap().get("scope")); // 如果是评分卡，存放最后评分
        List<String> newList = new ArrayList();
        if (ObjectUtils.isNotEmpty(li)) { // 去掉重复命中规则名
            Set set = new HashSet();
            set.addAll(li);
            newList.addAll(set);
        }
        RuleStandardResult ruleResult = new RuleStandardResult();
        ruleResult.setLogIdList(logIdList);
        ruleResult.setRuleList(newList);
        ruleResult.setResult(reulst);
//        ruleResult.setScope(null == scrope ? "" : String.valueOf(scrope));
        return ruleResult;
    }

    public List<String> batchSaveLog(RuleExecutionObject object, MulitDroolsParamter paramter, RuleSceneVersion ruleVersion, Long executeTime) {
        List<String> logList = new ArrayList<String>();
        RuleExecutionResult res = (RuleExecutionResult) object.getGlobalMap().get("_result");

        List<String> li = (List<String>) res.getMap().get("ruleList"); // 命中规则名列表
        List<String> newList = new ArrayList();
        if (ObjectUtils.isNotEmpty(li)) { // 去掉重复命中规则名
            Set set = new HashSet();
            set.addAll(li);
            newList.addAll(set);
        }
        if (RuleCallTypeEnum.business.getType().equals(paramter.getType()) ||
                RuleCallTypeEnum.model.getType().equals(paramter.getType())) {
            DroolsLog entity = new DroolsLog();
            entity.setType(paramter.getType());
            entity.setProcinstId(StringUtil.strIsNotNull(paramter.getProcessInstanceId()) ? paramter.getProcessInstanceId() : null);
            entity.setInParamter(JSON.toJSONString(paramter));
            entity.setSenceVersionid(String.valueOf(ruleVersion.getVersionId()));
            entity.setOutParamter(JSON.toJSONString(object));
            entity.setExecuteTotal(newList.size());
            entity.setModelName(paramter.getModelName());
            entity.setExecuteTime(executeTime);
            droolsLogService.insertOrUpdate(entity);
            String logId = entity.getId();
            if (ObjectUtils.isNotEmpty(li)) {
                for (String string : newList) {
                    DroolsDetailLog process = new DroolsDetailLog();
                    process.setDroolsLogid(logId);
                    process.setExecuteRulename(string);
                    droolsProcessLogService.insertOrUpdate(process);
                }
            }
        } else {
            TestDroolsLog entity = new TestDroolsLog();
            entity.setType(paramter.getType());
            entity.setProcinstId(StringUtil.strIsNotNull(paramter.getProcessInstanceId()) ? paramter.getProcessInstanceId() : null);
            entity.setInParamter(JSON.toJSONString(paramter));
            entity.setSenceVersionid(String.valueOf(ruleVersion.getVersionId()));
            entity.setOutParamter(JSON.toJSONString(object));
            entity.setExecuteTotal(newList.size());
            entity.setModelName(paramter.getModelName());
            if (StringUtils.isNotEmpty(paramter.getBatchId())) {
                entity.setBatchId(Long.parseLong(paramter.getBatchId())); // 批次号
            }
            testDroolsLogService.insertOrUpdate(entity);
            String logId = entity.getId();
            if (ObjectUtils.isNotEmpty(li)) {
                for (String string : newList) {
                    TestDroolsDetailLog process = new TestDroolsDetailLog();
                    process.setDroolsLogid(logId);
                    process.setExecuteRulename(string);
                    testDroolsDetailLogService.insertOrUpdate(process);
                }
            }
            logList.add(String.valueOf(logId));
        }
//        object.getGlobalMap().put("logIdList", logList); // 日志id集合
//        RuleStandardResult ruleResult = new RuleStandardResult();
//        ruleResult.setLogIdList(logList);
//        ruleResult.setRuleList(li);
//        ruleResult.setResult(reulst);
        return logList;
    }


    public RuleStandardResult saveLog(RuleExecutionObject object, DroolsParamter paramter, RuleSceneVersion ruleVersion, Long executeTime) {
        List<String> logList = new ArrayList<String>();
        RuleExecutionResult res = (RuleExecutionResult) object.getGlobalMap().get("_result");
        List<String> reulst = (List<String>) res.getMap().get("result"); // 规则计算返回结果(输出提示类动作)
        List<String> li = (List<String>) res.getMap().get("ruleList"); // 命中规则名列表
        String scrope = String.valueOf(res.getMap().get("scope")); // 如果是评分卡，存放最后评分
        List<String> newList = new ArrayList();
        if (ObjectUtils.isNotEmpty(li)) { // 去掉重复命中规则名
            Set set = new HashSet();
            set.addAll(li);
            newList.addAll(set);
        }
        if (RuleCallTypeEnum.business.getType().equals(paramter.getType()) ||
                RuleCallTypeEnum.model.getType().equals(paramter.getType())) {
            DroolsLog entity = new DroolsLog();
            entity.setType(paramter.getType());
            entity.setProcinstId(StringUtil.strIsNotNull(paramter.getProcessInstanceId()) ? paramter.getProcessInstanceId() : null);
            entity.setInParamter(JSON.toJSONString(paramter));
            entity.setSenceVersionid(String.valueOf(ruleVersion.getVersionId()));
            entity.setOutParamter(JSON.toJSONString(object));
            entity.setExecuteTotal(newList.size());
            entity.setModelName(paramter.getModelName());
            entity.setExecuteTime(executeTime);
            droolsLogService.insertOrUpdate(entity);
            String logId = entity.getId();
            if (ObjectUtils.isNotEmpty(li)) {
                for (String string : newList) {
                    DroolsDetailLog process = new DroolsDetailLog();
                    process.setDroolsLogid(logId);
                    process.setExecuteRulename(string);
                    droolsProcessLogService.insertOrUpdate(process);
                }
            }
        } else {
            TestDroolsLog entity = new TestDroolsLog();
            entity.setType(paramter.getType());
            entity.setProcinstId(StringUtil.strIsNotNull(paramter.getProcessInstanceId()) ? paramter.getProcessInstanceId() : null);
            entity.setInParamter(JSON.toJSONString(paramter));
            entity.setSenceVersionid(String.valueOf(ruleVersion.getVersionId()));
            entity.setOutParamter(JSON.toJSONString(object));
            entity.setExecuteTotal(newList.size());
            entity.setModelName(paramter.getModelName());
            if (StringUtils.isNotEmpty(paramter.getBatchId())) {
                entity.setBatchId(Long.parseLong(paramter.getBatchId())); // 批次号
            }
            testDroolsLogService.insertOrUpdate(entity);
            String logId = entity.getId();
            if (ObjectUtils.isNotEmpty(li)) {
                for (String string : newList) {
                    TestDroolsDetailLog process = new TestDroolsDetailLog();
                    process.setDroolsLogid(logId);
                    process.setExecuteRulename(string);
                    testDroolsDetailLogService.insertOrUpdate(process);
                }
            }
            logList.add(String.valueOf(logId));
        }
        object.getGlobalMap().put("logIdList", logList); // 日志id集合
        RuleStandardResult ruleResult = new RuleStandardResult();
        ruleResult.setLogIdList(logList);
        ruleResult.setRuleList(li);
        ruleResult.setResult(reulst);
        ruleResult.setScope(null == scrope ? "" : String.valueOf(scrope));
        return ruleResult;
    }
}

