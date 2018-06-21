package com.ht.risk.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * @author dyb
 * @ClassName: RuleConstantController
 * @Description: 规则库常量
 * @date 2018年1月3日 上午9:42:12
 */
@Controller
@RequestMapping("/faceSearch")
public class RuleIntefaceSearchController {

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String ruleConstant() {
        return "rule/interfaceSearch/index";
    }

}
