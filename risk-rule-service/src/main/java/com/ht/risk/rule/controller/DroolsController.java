package com.ht.risk.rule.controller;


import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ht.risk.common.result.Result;
import com.ht.risk.rule.outinterface.DroolsInterface;
import com.ht.risk.rule.service.DroolsRuleEngineService;


@RestController
@RequestMapping("/actionInfo")
public class DroolsController {


    @Autowired
    private DroolsInterface droolsInterface;

 
    @GetMapping("clientExcuteDroolsScene")
    public Result<String> excuteDroolsScene(){
        Result<String> data = null;
        // 业务数据转化
        try {
        	data = droolsInterface.droolsExcute();
        }catch (Exception e){
            data = Result.error(1,"");
            e.printStackTrace();
        }
        return data;
    }
}
