package com.ht.risk.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ht.risk.model.fact.RuleExecutionObject;
import com.ht.risk.model.fact.RuleExecutionResult;

/**
 * 描述： drools 实现类动作接口 CLASSPATH: com.sky.DroolsActionService VERSION: 1.0
 * Created by lihao DATE: 2017/7/24
 */
@SuppressWarnings("unchecked")
public abstract class DroolsActionService {

	private Logger log = LoggerFactory.getLogger(DroolsActionService.class);
	
	/**
	 * Date 2017/7/24 Author lihao [lihao@sinosoft.com]
	 *
	 * 方法说明: 默认执行方法
	 * 
	 * @param fact
	 *            参数
	 * @param result
	 *            结果集
	 */
	public abstract void execute(RuleExecutionObject fact, RuleExecutionResult result, String key);

	/**
	 * 规则日志
	* @Title: saveLog
	* @Description: 记录执行的规则
	* @param @param fact
	* @param @param result    设定文件
	* @return void    返回类型
	* @throws
	 */
	public void saveLog(RuleExecutionObject fact, RuleExecutionResult result) {
		List<String> ruleList = (List<String>) result.getMap().get("ruleList");
		if (null == ruleList) {
			ruleList = new ArrayList<String>();
		}
		String rule = (String) result.getMap().get("rule");
		ruleList.add(rule);
		result.getMap().put("ruleList", ruleList);
		
		log.info("########统计当前结果："+rule);
	}

}
