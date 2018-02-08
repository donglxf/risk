package com.ht.risk.controller;

import java.util.*;

import javax.annotation.Resource;

import com.ht.risk.api.model.drools.DroolsParamter;
import com.ht.risk.common.comenum.RuleCallTypeEnum;
import com.ht.risk.model.*;
import com.ht.risk.model.fact.RuleStandardResult;
import com.ht.risk.service.*;
import com.ht.risk.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.ht.risk.common.model.RuleExcuteResult;
import com.ht.risk.common.util.ObjectUtils;
import com.ht.risk.model.fact.RuleExecutionObject;
import com.ht.risk.model.fact.RuleExecutionResult;

@RestController
public class DroolsExcuteController {

    protected static final Logger log = LoggerFactory.getLogger(DroolsExcuteController.class);

    @Resource
    private DroolsRuleEngineService droolsRuleEngineService;

//    @Resource
//    private RuleSceneService ruleSceneService;

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
                data = new RuleExcuteResult(1, paramter.getSence() + "无可用正式版发布信息,请检查", null);
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
            entity.setProcinstId(Long.parseLong(paramter.getProcessInstanceId()));
            entity.setInParamter(JSON.toJSONString(paramter));
            entity.setSenceVersionid(String.valueOf(ruleVersion.getVersionId()));
            entity.setOutParamter(JSON.toJSONString(object));
            entity.setExecuteTotal(newList.size());
            entity.setModelName(paramter.getModelName());
            entity.setExecuteTime(executeTime);
            droolsLogService.insertOrUpdate(entity);
            Long logId = entity.getId();
            if (ObjectUtils.isNotEmpty(li)) {
                for (String string : newList) {
                    DroolsDetailLog process = new DroolsDetailLog();
                    process.setDroolsLogid(logId);
                    process.setExecuteRulename(string);
                    droolsProcessLogService.insertOrUpdate(process);
                }
            }
//            data = new RuleExcuteResult(0, "", object);
        } catch (Exception e) {
            e.printStackTrace();
            data = new RuleExcuteResult(1, "执行异常", null);
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
            data = new RuleExcuteResult(1, e.getMessage(), null);
        }
        return list;
    }

    /**
     * 手动测试
     *
     * @param paramter
     * @return
     */
    @RequestMapping("/excuteDroolsSceneValidation")
    public RuleExcuteResult excuteDroolsSceneValidation(@RequestBody DroolsParamter paramter) {
        RuleExcuteResult data = null;
        // 业务数据转化
        try {
            log.info("规则验证入参：" + JSON.toJSONString(paramter));
            Map<String, Object> parmaMap = new HashMap<String, Object>();
            boolean bool = true; // true-正式,false-测试
            if (RuleCallTypeEnum.rule.getType().equals(paramter.getType())) { // 规则调用
                bool = false;
            }

            Long startTime = System.currentTimeMillis();
            // 1.根据sceneCode 查询最新测试版本
            if (bool) {
                parmaMap.put("type", "1"); // 正式版
            } else {
                parmaMap.put("type", "0");
            }
            parmaMap.put("sceneIdentify", paramter.getSence()); // 决策code
            parmaMap.put("version", paramter.getVersion());
            RuleSceneVersion ruleVersion = ruleSceneVersionService.getInfoByVersionId(parmaMap);
            if (ObjectUtils.isEmpty(ruleVersion)) {
                data = new RuleExcuteResult(1, paramter.getSence() + "参数出错，无可用测试版本信息,请检查", null);
                return data;
            }
            RuleExecutionObject object = new RuleExecutionObject();
            RuleExecutionResult result = new RuleExecutionResult();
            object.setGlobal("_result", result);
            Map<String, Object> mapData = paramter.getData();
            object.addFactObject(mapData);
            object = this.droolsRuleEngineService.excute1(object, ruleVersion);
            Long endTime = System.currentTimeMillis();
            Long executeTime = endTime - startTime;
            log.info("规则验证执行时间》》》》》" + String.valueOf(executeTime));

            // 记录日志
//            RuleExecutionResult res = (RuleExecutionResult) object.getGlobalMap().get("_result");
//            List<String> reulst = (List<String>) res.getMap().get("result");
//            List<String> li = (List<String>) res.getMap().get("ruleList");
//            List<String> newList = new ArrayList();
//            if (ObjectUtils.isNotEmpty(li)) {
//                Set set = new HashSet();
//                set.addAll(li);
//                newList.addAll(set);
//            }
//            TestDroolsLog entity = new TestDroolsLog();
//            entity.setType(paramter.getType());
//            entity.setProcinstId(StringUtil.strIsNotNull(paramter.getProcessInstanceId()) ? Long.parseLong(paramter.getProcessInstanceId()) : 0);
//            entity.setInParamter(JSON.toJSONString(paramter));
//            entity.setSenceVersionid(String.valueOf(ruleVersion.getVersionId()));
//            entity.setOutParamter(JSON.toJSONString(object));
//            entity.setExecuteTotal(newList.size());
//            entity.setModelName(paramter.getModelName());
//            if (StringUtils.isNotEmpty(paramter.getBatchId())) {
//                entity.setBatchId(Long.parseLong(paramter.getBatchId())); // 批次号
//            }
//            testDroolsLogService.insertOrUpdate(entity);
//            Long logId = entity.getId();
//            if (ObjectUtils.isNotEmpty(li)) {
//                for (String string : newList) {
//                    TestDroolsDetailLog process = new TestDroolsDetailLog();
//                    process.setDroolsLogid(logId);
//                    process.setExecuteRulename(string);
//                    testDroolsDetailLogService.insertOrUpdate(process);
//                }
//            }
//            logList.add(String.valueOf(logId));
//            object.getGlobalMap().put("logIdList", logList);
//
//            RuleStandardResult ruleResult = new RuleStandardResult();
//            ruleResult.setLogIdList(logList);
//            ruleResult.setRuleList(li);
//            ruleResult.setResult(reulst);

            data = new RuleExcuteResult(0, "success", saveLog(object, paramter, ruleVersion, executeTime));
        } catch (Exception e) {
            e.printStackTrace();
            data = new RuleExcuteResult(1, e.getMessage(), null);
        }
        log.info("规则验证返回结果：" + JSON.toJSONString(data));
        return data;
    }

    public RuleStandardResult saveLog(RuleExecutionObject object, DroolsParamter paramter, RuleSceneVersion ruleVersion, Long executeTime) {
        List<String> logList = new ArrayList<String>();
        RuleExecutionResult res = (RuleExecutionResult) object.getGlobalMap().get("_result");
        List<String> reulst = (List<String>) res.getMap().get("result");
        List<String> li = (List<String>) res.getMap().get("ruleList");
        List<String> newList = new ArrayList();
        if (ObjectUtils.isNotEmpty(li)) {
            Set set = new HashSet();
            set.addAll(li);
            newList.addAll(set);
        }
        if (RuleCallTypeEnum.business.getType().equals(paramter.getType()) ||
                RuleCallTypeEnum.model.getType().equals(paramter.getType())) {
            DroolsLog entity = new DroolsLog();
            entity.setType(paramter.getType());
            entity.setProcinstId(Long.parseLong(paramter.getProcessInstanceId()));
            entity.setInParamter(JSON.toJSONString(paramter));
            entity.setSenceVersionid(String.valueOf(ruleVersion.getVersionId()));
            entity.setOutParamter(JSON.toJSONString(object));
            entity.setExecuteTotal(newList.size());
            entity.setModelName(paramter.getModelName());
            entity.setExecuteTime(executeTime);
            droolsLogService.insertOrUpdate(entity);
            Long logId = entity.getId();
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
            entity.setProcinstId(StringUtil.strIsNotNull(paramter.getProcessInstanceId()) ? Long.parseLong(paramter.getProcessInstanceId()) : 0);
            entity.setInParamter(JSON.toJSONString(paramter));
            entity.setSenceVersionid(String.valueOf(ruleVersion.getVersionId()));
            entity.setOutParamter(JSON.toJSONString(object));
            entity.setExecuteTotal(newList.size());
            entity.setModelName(paramter.getModelName());
            if (StringUtils.isNotEmpty(paramter.getBatchId())) {
                entity.setBatchId(Long.parseLong(paramter.getBatchId())); // 批次号
            }
            testDroolsLogService.insertOrUpdate(entity);
            Long logId = entity.getId();
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
        object.getGlobalMap().put("logIdList", logList);
        RuleStandardResult ruleResult = new RuleStandardResult();
        ruleResult.setLogIdList(logList);
        ruleResult.setRuleList(li);
        ruleResult.setResult(reulst);
        return ruleResult;
//        data = new RuleExcuteResult(0, "success", ruleResult);
    }
}

