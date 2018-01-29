package com.ht.risk.rule.controller;

import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Author Huang.zengmeng
 * @Description 模型发布功能
 * @Date 2018/1/29 9:38
 */

@RestController
@RequestMapping(value = "model/publish")
public class ModelPublishController {

    private static Logger logger = LoggerFactory.getLogger(ModelPublishController.class);


    @ApiOperation(value = "模型发布列表展示")
    @GetMapping(value = "list")
    public Object getModelList() {
        logger.info("---模型发布列表展示---");
        return null;
    }
}
