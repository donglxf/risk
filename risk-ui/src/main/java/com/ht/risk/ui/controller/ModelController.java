package com.ht.risk.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("")
public class ModelController {

    @RequestMapping(value = "/modelDetail",method = RequestMethod.GET)
    public String model(Model model, @RequestParam String modelId){
        model.addAttribute("modelId",modelId);
        return "modeler";
    }
    @RequestMapping(value = "/model/list",method = RequestMethod.GET)
    public String unDeployList(){
        return "model/config/list";
    }


    @RequestMapping(value = "/model/addView",method = RequestMethod.GET)
    public String addView(){
        return "model/configadd";
    }


    @RequestMapping(value = "/model/verfication",method = RequestMethod.GET)
    public String verification(){
        return "model/verfication/list";
    }

    @RequestMapping(value = "/model/valiable",method = RequestMethod.GET)
    public String valiable(){
        return "model/verfication/model";
    }

    @RequestMapping(value = "/model/result",method = RequestMethod.GET)
    public String result(){
        return "model/result/list";
    }





}
