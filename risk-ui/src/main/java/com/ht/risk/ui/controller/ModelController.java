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

    /**
     * 模型配置列表
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String unDeployList() {
        return "model/config/list";
    }

    /**
     * 模型编辑页面
     * @return
     */
    @RequestMapping(value = "/modelDetail", method = RequestMethod.GET)
    public String modelDetail() {
        return "modeler";
    }

    /**
     * 模型新增
     * @return
     */
    @RequestMapping(value = "/addView", method = RequestMethod.GET)
    public String addView() {
        return "model/config/add";
    }

    /**
     * 模型待验证列表
     * @return
     */
    @RequestMapping(value = "/verfication", method = RequestMethod.GET)
    public String verification() {
        return "model/verfication/list";
    }

    @RequestMapping(value = "/valiable", method = RequestMethod.GET)
    public String valiable() {
        return "model/verfication/verfication_task_create";
    }

/*    @RequestMapping(value = "/valiable/auto", method = RequestMethod.GET)
    public String valiableAuto() {
        return "model/verfication/model_auto";
    }*/

    @RequestMapping(value = "/verfication/result", method = RequestMethod.GET)
    public String verficationResult() {
        return "model/verfication/verfication_task_list";
    }

    @RequestMapping(value = "/verfication/result/detail", method = RequestMethod.GET)
    public String resultDetail() {
        return "model/verfication/detail";
    }

    @RequestMapping(value = "/verfication/log/modelLogDetail", method = RequestMethod.GET)
    public String modelLogDetail() {
        return "model/verfication/model_log_detail";
    }

    /**
     * 模型发布
     *
     * @return
     */
    @RequestMapping(value = "/publish/list", method = RequestMethod.GET)
    public String modelPublish() {
        return "model/version/list";
    }

    /**
     * 模型审批
     *
     * @return
     */
    @RequestMapping(value = "/approval/list", method = RequestMethod.GET)
    public String modelApproval() {
        return "model/approval/list";
    }


    @RequestMapping(value = "/deployList", method = RequestMethod.GET)
    public String deployList() {
        return "model/deploy_list";
    }


    @RequestMapping(value = "/startView", method = RequestMethod.GET)
    public String startView() {
        return "model/start";
    }

}
