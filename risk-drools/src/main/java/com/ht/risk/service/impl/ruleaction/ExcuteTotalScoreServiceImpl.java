package com.ht.risk.service.impl.ruleaction;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ht.risk.model.fact.RuleExecutionObject;
import com.ht.risk.model.fact.RuleExecutionResult;
import com.ht.risk.service.DroolsActionService;

@Service
public class ExcuteTotalScoreServiceImpl extends DroolsActionService{
	
	private Logger log = LoggerFactory.getLogger(ExcuteTotalScoreServiceImpl.class);
	
	
    public void statistics(RuleExecutionObject fact, RuleExecutionResult result,String key,String grade) {
    	log.info("########统计方法开始");
    	 int scope=0;
         Object total= result.getMap() .get("scope");
         if(null != total){
         	scope=Integer.parseInt(String.valueOf(total));
         }
         Object val = result.getMap().get(key);
         if(null != val){
         	scope += Integer.parseInt(String.valueOf(val));
         }
        result.getMap().put("scope", scope);
    	/*Object totalObj = fact.getGlobalMap().get("total");
    	Integer total = 0 ; 
    	if(totalObj != null){
    		total =  Integer.parseInt(String.valueOf(totalObj));
    	}else{
    		total = 0;
    	}
    	Object scoreObj = result.getMap().get("score");
    	if(scoreObj != null){
    		total += Integer.parseInt(String.valueOf(scoreObj));
    	}
    	fact.getGlobalMap().put("total", total);*/
    	
    	log.info("########统计当前结果："+scope);
    	log.info("########统计方法结束");
    }

	public void statisResult(RuleExecutionResult result) {
		
	}

    

}
