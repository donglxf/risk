package com.ht.risk.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * ${DESCRIPTION}
 *
 * @author wanghaobin
 * @create 2017-06-06 13:34
 */
@Controller
@RequestMapping("")
public class HomeController {
    @RequestMapping(value = "index",method = RequestMethod.GET)
    public String index(){
        return "index";
    }

    @RequestMapping(value = "login",method = RequestMethod.GET)
    public String login(){
        return "login";
    }
    @RequestMapping(value = "main",method = RequestMethod.GET)
    public String main(){
        return "main";
    }

    @RequestMapping(value = "rule/constant",method = RequestMethod.GET)
    public String ruleConstant(){
    	return "rule/constant/rule_constant";
    }
    
    @RequestMapping(value = "rule/ruleAction",method = RequestMethod.GET)
    public String ruleAction(){
    	return "rule/action/rule_action";
    }
    
    @RequestMapping(value = "rule/ruleAction/edit",method = RequestMethod.GET)
    public String ruleActionEdit(){
    	return "rule/action/rule_action_edit";
    }
    @RequestMapping(value = "rule/ruleAction/actionParamEdit",method = RequestMethod.GET)
    public String ruleActionParamEdit(){
    	return "rule/action/rule_action_param_edit";
    }
    
    @RequestMapping(value = "rule/constant/edit",method = RequestMethod.GET)
    public String ruleConstantEdit(){
    	return "rule/constant/rule_constant_edit";
    }
    
    @RequestMapping(value = "rule/constant/itemEdit",method = RequestMethod.GET)
    public String ruleConstantItemEdit(){
    	return "rule/constant/rule_constant_item_edit";
    }



    @RequestMapping(value = "/modelDetail",method = RequestMethod.GET)
    public String model(Model model,@RequestParam String modelId){
        model.addAttribute("modelId",modelId);
        return "modeler";
    }

}
