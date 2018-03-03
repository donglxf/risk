package com.ht.risk.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 实体对象-变量页面跳转相关
 *
 * @author wanghaobin
 * @create 2017-06-06 13:34
 */
@Controller
@RequestMapping("/rule/")
public class EntityController {
    @RequestMapping(value = "entity/edit",method = RequestMethod.GET)
    public String ruleEntityEdit(){
        return "/rule/entity/rule_entity_edit";
    }

    @RequestMapping(value = "entityItem/edit",method = RequestMethod.GET)
    public String ruleEntityItemEdit(){
        return "/rule/entity/rule_entity_item_edit";
    }

    @RequestMapping(value = "entity",method = RequestMethod.GET)
    public String ruleEntity(){
        return "/rule/entity/rule_entity";
    }

}
