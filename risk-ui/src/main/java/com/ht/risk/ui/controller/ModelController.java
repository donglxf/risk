package com.ht.risk.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/model")
public class ModelController {

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String unDeployList(){
        return "model/list";
    }


    @RequestMapping(value = "/deployList",method = RequestMethod.GET)
    public String deployList(){
        return "model/deploy_list";
    }

    @RequestMapping(value = "/addView",method = RequestMethod.GET)
    public String addView(){
        return "model/add";
    }

    @RequestMapping(value = "/startView",method = RequestMethod.GET)
    public String startView(){
        return "model/start";
    }


}
