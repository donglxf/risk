package com.ht.risk.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
* @ClassName: OffLineTaskController
* @Description: 离线任务配置管理
* @author liuzq
* @date 
* 
*/
@Controller
@RequestMapping("/rule")
public class OfflineTaskController {
    @RequestMapping(value = "/offlineTask/model",method = RequestMethod.GET)
    public String ruleConstant(){
    	return "rule/offlineTask/rule_offline_task";
    }
    
    @RequestMapping(value = "/offlinetask/edit",method = RequestMethod.GET)
    public String ruleConstantEdit(){
    	return "rule/offlineTask/rule_offline_task_edit";
    }
    
    @RequestMapping(value = "/offlinetask/itemEdit",method = RequestMethod.GET)
    public String ruleConstantItemEdit(){
    	return "rule/constant/rule_constant_item_edit";
    }

}
