package com.ht.risk.service.impl.ruleaction;

import org.springframework.stereotype.Service;

import com.ht.risk.model.fact.RuleExecutionObject;
import com.ht.risk.model.fact.RuleExecutionResult;
import com.ht.risk.model.fact.TestRule;
import com.ht.risk.service.DroolsActionService;

import java.util.Map;

/**
 * 描述：
 * CLASSPATH: com.sky.TestActionImpl
 * VERSION:   1.0
 * Created by lihao
 * DATE:      2017/7/24
 */
@Service
public class TestActionImpl implements DroolsActionService {
    /**
     * Date 2017/7/24
     * Author lihao [lihao@sinosoft.com]
     * <p>
     * 方法说明: 默认执行方法
     *
     * @param fact   参数
     * @param result 结果集
     */
    @Override
    public void execute(RuleExecutionObject fact, RuleExecutionResult result) {
    	//遍历map信息
        for (Map.Entry<String, Object> entry : result.getMap().entrySet()) {
            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
        }
        
        
        Map<String,Object> t = (Map<String,Object>) fact.getFactObjectList().get(0);
        for(Map.Entry<String, Object> item:t.entrySet()){
        	System.out.println(item.getKey()+">>>>"+item.getValue());
        }
//        TestRule t = (TestRule) fact.getFactObjectList().get(0);
//        System.out.println(t.getScore());
//        System.out.println(t.getAmount());
//        System.out.println(t.getMessage());
    }

	@Override
	public void statisResult(RuleExecutionResult result) {
		//遍历map信息
        System.out.println("》》》》》》》》》》》》》:::"+result.getTotal());
	}
    
    
}
