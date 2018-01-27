package com.ht.risk.service.impl.ruleaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ht.risk.model.fact.RuleExecutionObject;
import com.ht.risk.model.fact.RuleExecutionResult;
import com.ht.risk.service.DroolsActionService;

/**
 * 描述：
 * CLASSPATH: com.sky.TestActionImpl
 * VERSION:   1.0
 * Created by lihao
 * DATE:      2017/7/24
 */
@SuppressWarnings("unchecked")
@Service
public class TestActionImpl extends DroolsActionService {
    private Logger log = LoggerFactory.getLogger(TestActionImpl.class);
    /**
     * Date 2017/7/24
     * Author lihao [lihao@sinosoft.com]
     * <p>
     * 方法说明: 默认执行方法
     *
     * @param fact   参数
     * @param result 结果集
     * @param key // 业务变量
     * @param grade // 权值
     */
    public void grade(RuleExecutionObject fact, RuleExecutionResult result,String key,String grade) {
    	//遍历map信息
        for (Map.Entry<String, Object> entry : result.getMap().entrySet()) {
//            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
        }
        
        int scope=0;
        Object total= result.getMap() .get("scope");
        if(null != total){
        	scope=Integer.parseInt(String.valueOf(total));
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
        result.getMap().put("scope", scope);
        log.info("TestActionImpl 执行");
        System.out.println("总得分>>>>>>>>::"+result.getMap().get("scope"));
        
        
    }

}
