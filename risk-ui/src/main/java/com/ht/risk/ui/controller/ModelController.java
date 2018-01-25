package com.ht.risk.ui.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/model")
public class ModelController {

    private static Logger logger = LoggerFactory.getLogger(ModelController.class);

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String unDeployList(){
        return "model/list";
    }

    @RequestMapping(value = "/modelDetail",method = RequestMethod.GET)
    public String modelDetail(){
        return "modeler";
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

    @RequestMapping(value = "/verfication",method = RequestMethod.GET)
    public String verification(){
        return "model/verfication/list";
    }

    @RequestMapping(value = "/valiable",method = RequestMethod.GET)
    public String valiable(){
        logger.info("手动测试");
        return "model/verfication/model2";
    }

    @RequestMapping(value = "/valiable/auto",method = RequestMethod.GET)
    public String valiableAuto(){
        logger.info("请求自动测试页面");
        return "model/verfication/model_auto";
    }


}
