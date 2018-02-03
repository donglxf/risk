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

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String unDeployList() {
        return "model/list";
    }

    @RequestMapping(value = "/modelDetail", method = RequestMethod.GET)
    public String modelDetail() {
        return "modeler";
    }


    @RequestMapping(value = "/deployList", method = RequestMethod.GET)
    public String deployList() {
        return "model/deploy_list";
    }

    @RequestMapping(value = "/addView", method = RequestMethod.GET)
    public String addView() {
        return "model/add";
    }

    @RequestMapping(value = "/startView", method = RequestMethod.GET)
    public String startView() {
        return "model/start";
    }

    @RequestMapping(value = "/verfication", method = RequestMethod.GET)
    public String verification() {
        return "model/verfication/list";
    }

    @RequestMapping(value = "/valiable", method = RequestMethod.GET)
    public String valiable() {
        return "model/verfication/model2";
    }

    @RequestMapping(value = "/valiable/auto", method = RequestMethod.GET)
    public String valiableAuto() {
        return "model/verfication/model_auto";
    }

    @RequestMapping(value = "/verfication/result", method = RequestMethod.GET)
    public String verficationResult() {
        return "model/verfication/result/list";
    }

    @RequestMapping(value = "/verfication/result/detail", method = RequestMethod.GET)
    public String resultDetail() {
        return "model/verfication/result/detail";
    }

    /**
     * 模型发布
     *
     * @return
     */
    @RequestMapping(value = "/publish/list", method = RequestMethod.GET)
    public String modelPublish() {
        return "model/publish/list";
    }

}
