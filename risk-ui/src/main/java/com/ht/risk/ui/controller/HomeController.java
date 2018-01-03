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

    @RequestMapping(value = "/modelDetail",method = RequestMethod.GET)
    public String model(Model model,@RequestParam String modelId){
        model.addAttribute("modelId",modelId);
        return "modeler";
    }

}
