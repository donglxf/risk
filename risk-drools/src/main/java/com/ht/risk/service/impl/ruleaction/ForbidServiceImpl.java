package com.ht.risk.service.impl.ruleaction;

import com.ht.risk.model.fact.RuleExecutionObject;
import com.ht.risk.model.fact.RuleExecutionResult;
import com.ht.risk.service.DroolsActionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ForbidServiceImpl extends DroolsActionService{
	
	private Logger log = LoggerFactory.getLogger(ForbidServiceImpl.class);
	
	
    public void forbid(RuleExecutionObject fact, RuleExecutionResult result,String key) {
    	log.info("########禁入方法开始");
//    	List<Map<String,Object>> mapList=new ArrayList<Map<String,Object>>();
//        Map<String,Object> mp=new HashMap<String,Object>();
//        mp.put("result", "禁入");
//        mapList.add(mp);
        List<String> mapList=new ArrayList<String>();
        mapList.add("禁入");
        result.getMap().put("result", mapList);
    	log.info("########禁入方法结束");
    }

}
