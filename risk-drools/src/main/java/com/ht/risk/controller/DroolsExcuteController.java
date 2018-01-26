package com.ht.risk.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.ht.risk.api.model.drools.DroolsParamter;
import com.ht.risk.model.*;
import com.ht.risk.util.StringUtil;
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
import com.ht.risk.rpc.DroolsLogInterface;
import com.ht.risk.service.DroolsRuleEngineService;
import com.ht.risk.service.RuleSceneService;
import com.ht.risk.service.RuleSceneVersionService;

@RestController
public class DroolsExcuteController {

    protected static final Logger log = LoggerFactory.getLogger(DroolsExcuteController.class);

    @Resource
    private DroolsRuleEngineService droolsRuleEngineService;

    @Resource
    private RuleSceneService ruleSceneService;

    @Autowired
    private DroolsLogInterface droolsLogInterface;

    @Autowired
    private RuleSceneVersionService ruleSceneVersionService;

    @SuppressWarnings("unchecked")
    @RequestMapping("/excuteDroolsScene")
    public RuleExcuteResult excuteDroolsScene(@RequestBody DroolsParamter paramter) {
        RuleExcuteResult data = null;
        // 业务数据转化 
        try {
            log.info("规则入参："+JSON.toJSONString(paramter));
            BaseRuleSceneInfo baseRuleInfo = new BaseRuleSceneInfo();
//            Long sceneId = 0L;
//            BaseRuleSceneInfo info = new BaseRuleSceneInfo();
//            info.setSceneIdentify(paramter.getSence());
//            info.setVersion(paramter.getVersion());
//            List<BaseRuleSceneInfo> list = ruleSceneService.findBaseRuleSceneInfiList(info);
//            if (ObjectUtils.isNotEmpty(list)) {
//                baseRuleInfo = list.get(0);
//                sceneId = baseRuleInfo.getSceneId();
//            }
            // 1.根据sceneCode 查询最新测试版本
    		Map<String,Object> parmaMap =new HashMap<String,Object>();
    		parmaMap.put("type", "1"); // 正式版标志
            parmaMap.put("versionId", paramter.getVersion());
            RuleSceneVersion ruleVersion = ruleSceneVersionService.getInfoByVersionId(parmaMap);
            if(ObjectUtils.isEmpty(ruleVersion)){
                data = new RuleExcuteResult(1, paramter.getSence()+"无可用正式版发布信息,请检查", null);
                return data;
            }
            RuleExecutionObject object = new RuleExecutionObject();
            Map<String, Object> mapData = paramter.getData();
            object.addFactObject(mapData);
            RuleExecutionResult result = new RuleExecutionResult();
            object.setGlobal("_result", result);
            object = this.droolsRuleEngineService.excute1(object, ruleVersion);
//            object = this.droolsRuleEngineService.excute(object, sceneId);

            // 记录日志
            RuleExecutionResult res = (RuleExecutionResult) object.getGlobalMap().get("_result");
            List<String> li = (List<String>) res.getMap().get("ruleList");
            DroolsLog entity = new DroolsLog();
            entity.setType(paramter.getType());
            entity.setProcinstId(paramter.getProcessInstanceId());
            entity.setInParam(JSON.toJSONString(paramter));
            entity.setSenceVersionid(paramter.getVersion());
            entity.setOutParamter(JSON.toJSONString(object));
            entity.setExecuteTotal(Integer.parseInt(String.valueOf(object.getGlobalMap().get("count"))));
            entity.setModelName(paramter.getModelName());
            String logId = droolsLogInterface.saveLog(entity);
            if (ObjectUtils.isNotEmpty(li)) {
                for (String string : li) {
                    DroolsProcessLog process = new DroolsProcessLog();
                    process.setDroolsLogid(Long.parseLong(logId));
                    process.setExecuteRulename(string);
                    droolsLogInterface.saveProcessLog(process);
                }
            }
            data = new RuleExcuteResult(0, "", object);
        } catch (Exception e) {
            e.printStackTrace();
            data = new RuleExcuteResult(1, "执行异常", null);
        }
        log.info("规则出参："+JSON.toJSONString(paramter));
        return data;
    }

    /**
     * 批量测试-自动测试
     * @param paramters
     * @return
     */
    @RequestMapping("/batchExcuteRuleValidation")
    public List<RuleExcuteResult> batchExcuteRuleValidation(@RequestBody List<DroolsParamter> paramters) {
        RuleExcuteResult data = null;
        // 业务数据转化
            List<RuleExcuteResult> list=new ArrayList<RuleExcuteResult>();
        try {
            for (DroolsParamter param:paramters){
                RuleExcuteResult result=excuteDroolsSceneValidation(param);
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
     * @param paramter
     * @return
     */
    @RequestMapping("/excuteDroolsSceneValidation")
    public RuleExcuteResult excuteDroolsSceneValidation(@RequestBody DroolsParamter paramter) {
        RuleExcuteResult data = null;
        // 业务数据转化
        try {
            log.info("规则验证入参："+JSON.toJSONString(paramter));
            List<String> logList = new ArrayList<String>();
            Long startTime= System.currentTimeMillis();
            // 1.根据sceneCode 查询最新测试版本
            Map<String, Object> parmaMap = new HashMap<String, Object>();
            parmaMap.put("type", "0"); // 测试版标志
            parmaMap.put("sceneIdentify", paramter.getSence());
            parmaMap.put("versionId", paramter.getVersion());
            RuleSceneVersion ruleVersion = ruleSceneVersionService.getInfoByVersionId(parmaMap);
            RuleExecutionObject object = new RuleExecutionObject();
            RuleExecutionResult result = new RuleExecutionResult();
            object.setGlobal("_result", result);
            Map<String, Object> mapData = paramter.getData();
            object.addFactObject(mapData);
            object = this.droolsRuleEngineService.excute1(object, ruleVersion);
            Long endTime=System.currentTimeMillis();
            log.info("规则验证执行时间》》》》》"+String.valueOf(endTime-startTime));
            // 记录日志
            RuleExecutionResult res = (RuleExecutionResult) object.getGlobalMap().get("_result");
            List<String> li = (List<String>) res.getMap().get("ruleList");
            TestDroolsLog entity = new TestDroolsLog();
            entity.setType(paramter.getType());
            entity.setProcinstId(StringUtil.strIsNotNull(paramter.getProcessInstanceId()) ? Long.parseLong(paramter.getProcessInstanceId()) : 0);
            entity.setInParamter(JSON.toJSONString(paramter));
            entity.setSenceVersionid(paramter.getVersion());
            entity.setOutParamter(JSON.toJSONString(object));
            entity.setExecuteTotal(Integer.parseInt(String.valueOf(res.getMap().get("count"))));
            entity.setModelName(paramter.getModelName());
            entity.setBatchId(Long.parseLong(paramter.getBatchId())); // 批次号
            String logId = droolsLogInterface.saveTestLog(entity);
            if (ObjectUtils.isNotEmpty(li)) {
                for (String string : li) {
                    TestDroolsDetailLog process = new TestDroolsDetailLog();
                    process.setDroolsLogid(Long.parseLong(logId));
                    process.setExecuteRulename(string);
                    droolsLogInterface.saveTestDroolsDetailLog(process);
                }
            }
            logList.add(logId);
            object.getGlobalMap().put("logIdList", logList);
            data = new RuleExcuteResult(0, "success", object);
        } catch (Exception e) {
            e.printStackTrace();
            data = new RuleExcuteResult(1, e.getMessage(), null);
        }
        log.info("规则验证返回结果："+JSON.toJSONString(data));
        return data;
    }

}
