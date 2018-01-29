package com.ht.risk.service.impl.ruleaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ht.risk.model.fact.RuleExecutionObject;
import com.ht.risk.model.fact.RuleExecutionResult;
import com.ht.risk.service.DroolsActionService;

@Service
public class IntopiecesServiceImpl extends DroolsActionService{
	
	private Logger log = LoggerFactory.getLogger(IntopiecesServiceImpl.class);
	
    public void intoPieces(RuleExecutionObject fact, RuleExecutionResult result,String key,String grade) {
    	log.info("########进件方法开始执行");
    	String flag = String.valueOf(result.getMap().get("flag"));
    	if("是".equals(flag)){
    		fact.getGlobalMap().put("into_pieces_flag", "0");
    		log.info("########规则判定结果为：进件");
    	}else{
    		fact.getGlobalMap().put("into_pieces_flag", "1");
    		log.info("########规则判定结果为：不进件");
    	}
    	log.info("########进件方法执行结束");
    }

	public void statisResult(RuleExecutionResult result) {
		
	}


}
