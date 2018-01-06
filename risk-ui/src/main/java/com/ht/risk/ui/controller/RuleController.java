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
@RequestMapping("/rule/")
public class RuleController {
    @RequestMapping(value = "decision/edit",method = RequestMethod.GET)
    public String ruleEdit(){
        return "rule/decision/rule_edit";
    }

    @RequestMapping(value = "decision/list",method = RequestMethod.GET)
    public String sceneList(){
        return "rule/decision/scene";
    }

    @RequestMapping(value = "decision/gradeList",method = RequestMethod.GET)
    public String sceneGradeList(){
        return "rule/decision/grade_card_list";
    }


    @RequestMapping(value = "decision/scene/edit",method = RequestMethod.GET)
    public String sceneEdit(){
        return "rule/decision/scene_edit";
    }

    @RequestMapping(value = "decision/scene/gradeCardEdit",method = RequestMethod.GET)
    public String gradeCardEdit(){
        return "rule/decision/grade_card_edit";
    }

    /**
     * 描述：策略管理 - 主要和版本关联了
     * @param * @param null
     * @return a
     * @autor 张鹏
     * @date 2018/1/3 14:21
     */
    @RequestMapping(value = "decision/version/list",method = RequestMethod.GET)
    public String decisionVersion(){
        System.out.print("===================");
        return "rule/decision/version/list";
    }

    /**
     * 描述：策略管理 - 主要和版本关联了
     * @param * @param null
     * @return a
     * @autor 张鹏
     * @date 2018/1/3 14:21
     */
        @RequestMapping(value = "decision/version/edit",method = RequestMethod.GET)
    public String versionEdit(){
            System.out.print("===================");
            return "rule/decision/version/edit";
    }



}
