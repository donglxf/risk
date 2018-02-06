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
public class LowerCollateralServiceImpl extends DroolsActionService{
	
	private Logger log = LoggerFactory.getLogger(LowerCollateralServiceImpl.class);
	
	
    public void lowerCollateral(RuleExecutionObject fact, RuleExecutionResult result,String key) {
    	log.info("########lowerCollateral方法开始");
        int scope=0;
        List<String> total= (List<String>) result.getMap() .get("result");
        if(ObjectUtils.isNotEmpty(total)){
            int count=0;
            for (String s:total){
                count += Integer.parseInt(s);
            }
            scope=count;
        }

        Object val = result.getMap().get(key);
        if(null != val){
            Class a=val.getClass();
            if(a == Double.class){
                scope += Double.parseDouble(String.valueOf(val));
            }else {
                scope += Integer.parseInt(String.valueOf(val));
            }
        }


        List<String> mapList=new ArrayList<String>();
        mapList.add(String.valueOf(scope));
        result.getMap().put("result", mapList);
    	log.info("########lowerCollateral方法结束");
    }

}
