package com.ht.risk.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/ruleBind")
public class RuleBindController {

	/**
	 * 变量绑定
	* @Title: unDeployList
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @return    设定文件
	* @return String    返回类型
	* @throws
	 */
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String unDeployList(){
        return "rule/bind/bindList";
    }

    /**
     * 规则验证首页
    * @Title: deployList
    * @Description: TODO(这里用一句话描述这个方法的作用)
    * @param @return    设定文件
    * @return String    返回类型
    * @throws
     */
    @RequestMapping(value = "/verification/index",method = RequestMethod.GET)
    public String deployList(){
        return "rule/bind/ruleVerification";
    }

    @RequestMapping(value = "/addView",method = RequestMethod.GET)
    public String addView(){
        return "rule/bind/add";
    }

    @RequestMapping(value = "/startView",method = RequestMethod.GET)
    public String startView(){
        return "rule/bind/start";
    }


}
