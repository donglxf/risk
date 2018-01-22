package com.ht.risk.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.ht.risk.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.ht.risk.common.model.DroolsParamter;
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
	
	@Resource
    private DroolsRuleEngineService droolsRuleEngineService;
	
	@Resource
    private RuleSceneService ruleSceneService;
	
	@Autowired
	private DroolsLogInterface droolsLogInterface ;
	
	@Autowired
	private RuleSceneVersionService ruleSceneVersionService ;

    @SuppressWarnings("unchecked")
	@RequestMapping("/excuteDroolsScene")
    public RuleExcuteResult excuteDroolsScene(@RequestBody DroolsParamter paramter){
        RuleExcuteResult data = null;
        // 业务数据转化 
        try {
        	
        	BaseRuleSceneInfo baseRuleInfo=new BaseRuleSceneInfo();
        	Long sceneId=0L;
        	BaseRuleSceneInfo info=new BaseRuleSceneInfo();
    		info.setSceneIdentify(paramter.getSence());
    		info.setVersion(paramter.getVersion());
    		List<BaseRuleSceneInfo> list=ruleSceneService.findBaseRuleSceneInfiList(info);
    		if(ObjectUtils.isNotEmpty(list)){
    			baseRuleInfo=list.get(0);
    			sceneId=baseRuleInfo.getSceneId();
    		}
        	// 1.根据sceneCode 查询最新测试版本
//    		Map<String,Object> parmaMap =new HashMap<String,Object>();
//    		parmaMap.put("type", "1"); // 正式版标志
//    		parmaMap.put("sceneIdentify", paramter.getSence());
//    		RuleSceneVersion ruleVersion=ruleSceneVersionService.getTestLastVersion(parmaMap);
    		
        	 RuleExecutionObject object = new RuleExecutionObject();
        	 Map<String,Object> mapData = paramter.getData();
             object.addFactObject(mapData);
             RuleExecutionResult result = new RuleExecutionResult();
             object.setGlobal("_result",result);
             object = this.droolsRuleEngineService.excute(object,sceneId);
//             object = this.droolsRuleEngineService.excute(object,ruleVersion.getRuleDrl());
             
             // 记录日志
             RuleExecutionResult res=(RuleExecutionResult) object.getGlobalMap().get("_result");
             List<String> li=(List<String>) res.getMap().get("ruleList");
             DroolsLog entity=new DroolsLog();
             entity.setType(paramter.getType());
             entity.setProcinstId(paramter.getProcessInstanceId());
             entity.setInParam(JSON.toJSONString(paramter));
             entity.setSenceVersionid(paramter.getVersion());
             entity.setOutParamter(JSON.toJSONString(object));
             entity.setExecuteTotal(Integer.parseInt(String.valueOf(object.getGlobalMap().get("count"))));
             entity.setModelName(paramter.getModelName());
             String logId=droolsLogInterface.saveLog(entity);
             if(ObjectUtils.isNotEmpty(li)){
            	 for (String string : li) {
            		 DroolsProcessLog process=new DroolsProcessLog();
            		 process.setDroolsLogid(Long.parseLong(logId));
            		 process.setExecuteRulename(string);
            		 droolsLogInterface.saveProcessLog(process);
            	 }
             }
             data = new RuleExcuteResult(0,"",object);
        }catch (Exception e){
        	e.printStackTrace();
            data = new RuleExcuteResult(1,"执行异常",null);
        }
        return data;
    }
    
    @RequestMapping("/excuteDroolsSceneValidation")
    public RuleExcuteResult excuteDroolsSceneValidation(@RequestBody DroolsParamter paramter){
    	RuleExcuteResult data = null;
    	// 业务数据转化 
    	try {
    		// 1.根据sceneCode 查询最新测试版本
    		Map<String,Object> parmaMap =new HashMap<String,Object>();
    		parmaMap.put("type", "0"); // 测试版标志
    		parmaMap.put("sceneIdentify", paramter.getSence());
    		RuleSceneVersion ruleVersion=ruleSceneVersionService.getTestLastVersion(parmaMap);
    		// 2.执行测试版本
    		RuleExecutionObject object = new RuleExecutionObject();
    		Map<String,Object> mapData = paramter.getData();
    		object.addFactObject(mapData);
    		RuleExecutionResult result = new RuleExecutionResult();
    		object.setGlobal("_result",result);
    		System.out.println(ruleVersion.getRuleDrl());
    		object = this.droolsRuleEngineService.excute1(object,ruleVersion);

			// 记录日志
			RuleExecutionResult res=(RuleExecutionResult) object.getGlobalMap().get("_result");
			List<String> li=(List<String>) res.getMap().get("ruleList");
			TestDroolsLog entity=new TestDroolsLog();
			entity.setType(paramter.getType());
			entity.setProcinstId(Long.parseLong(paramter.getProcessInstanceId()));
			entity.setInParamter(JSON.toJSONString(paramter));
			entity.setSenceVersionid(paramter.getVersion());
			entity.setOutParamter(JSON.toJSONString(object));
			entity.setExecuteTotal(Integer.parseInt(String.valueOf(object.getGlobalMap().get("count"))));
			entity.setModelName(paramter.getModelName());
			String logId=droolsLogInterface.saveTestLog(entity);
			if(ObjectUtils.isNotEmpty(li)){
				for (String string : li) {
					TestDroolsDetailLog process=new TestDroolsDetailLog();
					process.setDroolsLogid(Long.parseLong(logId));
					process.setExecuteRulename(string);
					droolsLogInterface.saveTestDroolsDetailLog(process);
				}
			}

			res.getMap().put("logId",logId);

    		data = new RuleExcuteResult(0,"",object);
    	}catch (Exception e){
    		e.printStackTrace();
    		data = new RuleExcuteResult(1,e.getMessage(),null);
    	}
    	return data;
    }

}
