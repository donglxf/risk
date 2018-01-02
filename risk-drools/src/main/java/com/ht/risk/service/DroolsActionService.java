package com.ht.risk.service;

import com.ht.risk.model.fact.RuleExecutionObject;
import com.ht.risk.model.fact.RuleExecutionResult;

/**
 * 描述： drools 实现类动作接口
 * CLASSPATH: com.sky.DroolsActionService
 * VERSION:   1.0
 * Created by lihao
 * DATE:      2017/7/24
 */
public interface DroolsActionService {

    /**
     * Date 2017/7/24
     * Author lihao [lihao@sinosoft.com]
     *
     * 方法说明: 默认执行方法
     * @param fact 参数
     * @param result 结果集
     */
    void execute(RuleExecutionObject fact, RuleExecutionResult result,String key);
    
    void statisResult(RuleExecutionResult result);
}
