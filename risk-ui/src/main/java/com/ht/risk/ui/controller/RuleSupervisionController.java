package com.ht.risk.ui.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/supervision")
public class RuleSupervisionController {

	/**
	 * 日志查询首页
	* @Title: unDeployList
	* @Description: 决策变量绑定首页
	* @param @return    设定文件
	* @return String    返回类型
	* @throws
	 */
    @RequestMapping(value = "/log/index",method = RequestMethod.GET)
    public String logIndex(){
        return "supervision/logindex";
    }

    /**
	 * 日志查询首页
	* @Title: unDeployList
	* @Description: 决策变量绑定首页
	* @param @return    设定文件
	* @return String    返回类型
	* @throws
	 */
    @RequestMapping(value = "/log/view",method = RequestMethod.GET)
    public String logView(){
        return "supervision/logView";
    }

    /**
     * 模型报告首页
     * @Title: unDeployList
     * @Description: 决策变量绑定首页
     * @param @return    设定文件
     * @return String    返回类型
     * @throws
     */
    @RequestMapping(value = "/model/report",method = RequestMethod.GET)
    public String modelReport(){
        return "supervision/modelReport";
    }

    /**
     * 策略报告首页
    * @Title: deployList
    * @Description: 决策验证首页
    * @param @return    设定文件
    * @return String    返回类型
    * @throws
     */
    @RequestMapping(value = "/rule/report",method = RequestMethod.GET)
    public String deployList(){
        return "supervision/ruleReport";
    }


}
