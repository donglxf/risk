package com.ht.risk.service.impl.ruleaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ht.risk.model.fact.RuleExecutionObject;
import com.ht.risk.model.fact.RuleExecutionResult;
import com.ht.risk.service.DroolsActionService;

@Service
public class ExcuteTotalScoreServiceImpl implements DroolsActionService{
	
	private Logger log = LoggerFactory.getLogger(ExcuteTotalScoreServiceImpl.class);
	
	
    @Override
    public void execute(RuleExecutionObject fact, RuleExecutionResult result,String key) {
    	log.info("########统计方法开始");
    	Object totalObj = fact.getGlobalMap().get("total");
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
    	fact.getGlobalMap().put("total", total);
    	log.info("########统计当前结果："+total);
    	log.info("########统计方法结束");
    }

	@Override
	public void statisResult(RuleExecutionResult result) {
		
	}

    

}
