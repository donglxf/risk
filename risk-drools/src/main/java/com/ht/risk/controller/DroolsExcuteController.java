package com.ht.risk.controller;

import com.alibaba.fastjson.JSON;
import com.ht.risk.common.model.DroolsParamter;
import com.ht.risk.common.model.Result;
import com.ht.risk.model.fact.RuleExecutionObject;
import com.ht.risk.model.fact.RuleExecutionResult;
import com.ht.risk.model.fact.TestRule;
import com.ht.risk.service.DroolsRuleEngineService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
public class DroolsExcuteController {
	
	@Resource
    private DroolsRuleEngineService droolsRuleEngineService;


    @RequestMapping("/excuteDroolsScene")
    public Result<RuleExecutionObject> excuteDroolsScene(@RequestBody DroolsParamter paramter){
        Result<RuleExecutionObject> data = null;
        // 业务数据转化
        try {
            System.out.println(JSON.toJSONString(paramter));
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
