package com.ht.risk.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * ${DESCRIPTION}
 *
 * @author wanghaobin
 * @create 2017-06-06 13:34
 */
@Controller
@RequestMapping("")
public class HomeController {
    @RequestMapping(value = "indexDev",method = RequestMethod.GET)
    public String index(){
        return "index-dev";
    }

    @RequestMapping(value = "mainDev",method = RequestMethod.GET)
    public String main(){
        return "main-dev";
    }


}
