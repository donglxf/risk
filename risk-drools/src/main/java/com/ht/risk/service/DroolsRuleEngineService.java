package com.ht.risk.service;

import com.ht.risk.model.fact.RuleExecutionObject;

/**
 * 描述：
 * VERSION:   1.0
 * Created by lihao
 * DATE:      2017/7/25
 */
public interface DroolsRuleEngineService {

    /**
     * Date 2017/7/25
     * Author lihao [lihao@sinosoft.com]
     *
     * 方法说明: 规则引擎执行方法
     * @param ruleExecutionObject facr对象信息
     * @param scene 场景
     */
    RuleExecutionObject excute(RuleExecutionObject ruleExecutionObject, final String scene) throws Exception;
    
    /**
     * 
    * @Title: getDroolsString
    * @Description: 获取规则字符串
    * @param @param scene 场景名
    * @param @return    设定文件
    * @return String    返回类型
    * @throws
     */
    public String getDroolsString(final String scene) throws Exception ;
    
    /**
     * 
     * @Title: getDroolsString
     * @Description: 根据id查找场景标识
     * @param @param scene 场景名
     * @param @return    设定文件
     * @return String    返回类型
     * @throws
     */
    public String getSceneIdentifyById(String id) throws Exception ;
}
