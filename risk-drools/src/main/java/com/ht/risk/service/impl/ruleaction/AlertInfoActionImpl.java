package com.ht.risk.service.impl.ruleaction;

import com.ht.risk.common.util.ObjectUtils;
import com.ht.risk.model.fact.RuleExecutionObject;
import com.ht.risk.model.fact.RuleExecutionResult;
import com.ht.risk.service.DroolsActionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlertInfoActionImpl extends DroolsActionService{
	
	private Logger log = LoggerFactory.getLogger(AlertInfoActionImpl.class);
	


    public void alertInfo(RuleExecutionObject fact, RuleExecutionResult result,String key) {
    	log.info("########输出提示信息方法开始");
        List<String> total= (List<String>) result.getMap() .get("result");
        if(ObjectUtils.isEmpty(total)){
            total=new ArrayList<String>();
        }
         Object val = result.getMap().get(key);
         if(null != val){
         	total.add(String.valueOf(val));
         }
        result.getMap().put("result", total);

    	log.info("########输出提示信息方法结束");
    }

    

}
