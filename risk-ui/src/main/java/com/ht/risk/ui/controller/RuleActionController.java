package com.ht.risk.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
* @ClassName: RuleActionController
* @Description: 规则库动作
* @author dyb
* @date 2018年1月3日 上午9:42:59
* 
*/
@Controller
@RequestMapping("/rule")
public class RuleActionController {

    
    @RequestMapping(value = "/ruleAction",method = RequestMethod.GET)
    public String ruleAction(){
    	return "rule/action/rule_action";
    }
    
    @RequestMapping(value = "/ruleAction/edit",method = RequestMethod.GET)
    public String ruleActionEdit(){
    	return "rule/action/rule_action_edit";
    }
    @RequestMapping(value = "/ruleAction/actionParamEdit",method = RequestMethod.GET)
    public String ruleActionParamEdit(){
    	return "rule/action/rule_action_param_edit";
    }
    
}
