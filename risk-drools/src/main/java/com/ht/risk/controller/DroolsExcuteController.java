package com.ht.risk.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ht.risk.common.model.DroolsParamter;
import com.ht.risk.common.model.Result;
import com.ht.risk.model.fact.RuleExecutionObject;
import com.ht.risk.model.fact.RuleExecutionResult;
import com.ht.risk.model.fact.TestRule;
import com.ht.risk.service.DroolsRuleEngineService;

import io.swagger.annotations.ApiOperation;

@RestController
public class DroolsExcuteController {
	
	@Resource
    private DroolsRuleEngineService droolsRuleEngineService;


    @GetMapping("/excuteDroolsScene")
    @ApiOperation(value = "" )
    public Result<RuleExecutionObject> excuteDroolsScene(DroolsParamter paramter){
        Result<RuleExecutionObject> data = null;
        // 业务数据转化
        try {
        	 RuleExecutionObject object = new RuleExecutionObject();
        	 Map<String,Object> mapData = paramter.getData();
             TestRule test = new TestRule();
             test.setAmount(100);
             test.setScore(20);
             test.setMessage("lihao");
             object.addFactObject(test);
             object.addFactObject(mapData);
             RuleExecutionResult result = new RuleExecutionResult();
             object.setGlobal("_result",result);
             object = this.droolsRuleEngineService.excute(object,paramter.getSence());
             data = Result.success(object);
        }catch (Exception e){
        	e.printStackTrace();
            data = Result.error(1,"");
        }
        return data;
    }

}
