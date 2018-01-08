package com.ht.risk.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.ht.risk.common.model.DroolsParamter;
import com.ht.risk.common.model.RuleExcuteResult;
import com.ht.risk.common.util.ObjectUtils;
import com.ht.risk.model.DroolsLog;
import com.ht.risk.model.DroolsProcessLog;
import com.ht.risk.model.fact.RuleExecutionObject;
import com.ht.risk.model.fact.RuleExecutionResult;
import com.ht.risk.rpc.DroolsLogInterface;
import com.ht.risk.service.DroolsRuleEngineService;

@RestController
public class DroolsExcuteController {
	
	@Resource
    private DroolsRuleEngineService droolsRuleEngineService;
	
	@Autowired
	private DroolsLogInterface droolsLogInterface ;

    @SuppressWarnings("unchecked")
	@RequestMapping("/excuteDroolsScene")
    public RuleExcuteResult excuteDroolsScene(@RequestBody DroolsParamter paramter){
        RuleExcuteResult data = null;
        // 业务数据转化
        try {
        	 String identity=droolsRuleEngineService.getSceneIdentifyById(paramter.getSence());
        	 String version="";
//           System.out.println(JSON.toJSONString(paramter));
        	 RuleExecutionObject object = new RuleExecutionObject();
        	 Map<String,Object> mapData = paramter.getData();
             object.addFactObject(mapData);
             RuleExecutionResult result = new RuleExecutionResult();
             object.setGlobal("_result",result);
             object = this.droolsRuleEngineService.excute(object,identity,version);
             
             
             // 记录日志
             RuleExecutionResult res=(RuleExecutionResult) object.getGlobalMap().get("_result");
             List<String> li=(List<String>) res.getMap().get("ruleList");
             DroolsLog entity=new DroolsLog();
             entity.setSceneId(paramter.getSence());
             entity.setProcInstId(paramter.getProcessInstanceId());
             entity.setInParam(JSON.toJSONString(paramter));
             entity.setDroolsVersion("");
             entity.setResult(JSON.toJSONString(object));
             entity.setExecuteTotal(Integer.parseInt(String.valueOf(object.getGlobalMap().get("count"))));
             String logId=droolsLogInterface.saveLog(entity);
             if(ObjectUtils.isNotEmpty(li)){
            	 for (String string : li) {
            		 DroolsProcessLog process=new DroolsProcessLog();
            		 process.setDroolsid(Long.parseLong(logId));
            		 process.setExecuteRule(string);
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

}
