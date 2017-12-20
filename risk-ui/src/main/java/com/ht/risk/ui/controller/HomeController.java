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




    @RequestMapping(value = "rule/entity",method = RequestMethod.GET)
    public String ruleEntity(){
        return "rule/rule-entity";
    }

    @RequestMapping(value = "about",method = RequestMethod.GET)
    public String about(){
        return "about";
    }
    @RequestMapping(value = "user",method = RequestMethod.GET)
    public String user(){
        return "user/list";
    }

    @RequestMapping(value = "user/edit",method = RequestMethod.GET)
    public String userEdit(){
        return "user/edit";
    }

    @RequestMapping(value = "menu",method = RequestMethod.GET)
    public String menu(){
        return "menu/list";
    }
    @RequestMapping(value = "menu/edit",method = RequestMethod.GET)
    public String menuEdit(){
        return "menu/edit";
    }
    @RequestMapping(value = "group",method = RequestMethod.GET)
    public String group(){
        return "group/list";
    }
    @RequestMapping(value = "group/user",method = RequestMethod.GET)
    public String groupUser(){
        return "group/user";
    }
    @RequestMapping(value = "group/edit",method = RequestMethod.GET)
    public String groupEdit(){
        return "group/edit";
    }
    @RequestMapping(value = "groupType",method = RequestMethod.GET)
    public String groupType(){
        return "groupType/list";
    }
    @RequestMapping(value = "ztree",method = RequestMethod.GET)
    public String groupTypeEdit(){
        return "ztree";
    }

    @RequestMapping(value = "/modelDetail",method = RequestMethod.GET)
    public String model(Model model,@RequestParam String modelId){
        model.addAttribute("modelId",modelId);
        return "modeler";
    }

}
