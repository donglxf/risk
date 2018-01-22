package com.ht.risk.rule.controller;


import com.baomidou.mybatisplus.plugins.Page;
import com.ht.risk.common.result.Result;
import com.ht.risk.rule.entity.ActReModel;
import com.ht.risk.rule.service.ActReModelService;
import com.ht.risk.rule.vo.ModelVerficationVo;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 规则动作定义信息 前端控制器
 * </p>
 *
 * @author zhangzhen
 * @since 2017-12-15
 */
@RestController
@RequestMapping("/verification")
public class ModelVerificationController {

    /*private static final Logger LOGGER = LoggerFactory.getLogger(ModelDeployController.class);
    @Autowired
    private ActReModelService actReModelService;

    *//**
     *  查询待验证的模型信息
     * @param page
     * @param modelRelease
     * @return
     *//*
    @GetMapping("page")
    public Result<Page<ModelRelease>> waitVerficationModelForPage(Page<ModelRelease> page,ModelRelease modelRelease){
        LOGGER.info("查询模型待验证分页信息开始");
        Result<Page<ModelRelease>> result = null;
        Page<ModelRelease> modelReleasePage = modelReleaseService.queryWaitVerficationModelForPage(page,modelRelease);
        result = Result.success(modelReleasePage);
        LOGGER.info("查询模型待验证分页信息结束");
        return result;
    }

    @GetMapping("demoPage")
    @ApiOperation(value = "查询所有的对象")
    public Result<List<ActReModel>> list(Page<ActReModel> page) {
        Page<ActReModel> pages =  actReModelService.selectPage(page);
        Result<List<ActReModel>> result = Result.build(0,"",pages.getRecords(),pages.getTotal());
        return result;
    }


    *//**
     * 创建模型验证批次
     * @return
     *//*
    @RequestMapping("createBatch")
    public Result createVerficationModelBatch(ModelRelease modelRelease){
        LOGGER.info("创建模型验证批次开始");
        Result<Page<ModelRelease>> result = null;
        if(modelRelease == null || StringUtils.isEmpty(modelRelease.getId())){
            result = Result.error(1,"参数异常，ID为空");
            return result;
        }
        boolean flag = modelReleaseService.createVerficationModelBatch(modelRelease.getId());
        if(flag == true){
            result = Result.success();
        }else {
            result = Result.error(1,"无法获取到对应的版本信息");
        }
        LOGGER.info("创建模型验证批次结束");
        return result;
    }

    *//**
     * 模型验证通过
     * @return
     *//*
    @RequestMapping("pass")
    public Result verficationModelPass(ModelRelease modelRelease){
        LOGGER.info("创建模型验证批次开始");
        Result<Page<ModelRelease>> result = null;
        if(modelRelease == null || StringUtils.isEmpty(modelRelease.getId())){
            result = Result.error(1,"参数异常，ID为空");
            return result;
        }
        modelRelease = modelReleaseService.selectById(modelRelease.getId());
        if(modelRelease == null){
            result = Result.error(1,"参数异常，没有找到对应的版本！");
            return result;
        }
        modelRelease.setIsValidate("1");
        modelReleaseService.updateById(modelRelease);
        result = Result.success();
        LOGGER.info("创建模型验证批次结束");
        return result;
    }

    *//**
     *  发起模型版本发布流程审批
     * @param modelRelease
     * @return
     *//*
    @RequestMapping("startProcess")
    public Result startProcess(ModelRelease modelRelease){
        LOGGER.info("启动模型发布审批开始");
        Result<Page<ModelRelease>> result = null;
        result = Result.success();
        LOGGER.info("启动模型发布审批结束");
        return result;
    }

    *//**
     *  模型正式发布版本
     * @param modelRelease
     * @return
     *//*
    @RequestMapping("release")
    public Result modelRelease(ModelRelease modelRelease){
        LOGGER.info("模型正式发布版本开始");
        Result<Page<ModelRelease>> result = null;
        result = Result.success();
        LOGGER.info("模型正式发布版本结束");
        return result;
    }

    *//**
     * 查询模型相关的变量
     * @param modelRelease
     * @return
     *//*
    @RequestMapping("queryModelVariable")
    public Result queryModelVariable(ModelRelease modelRelease){
        LOGGER.info("查询模型相关的变量开始");
        Result<ModelVerficationVo> result = null;
        ModelVerficationVo verficationVo = modelReleaseService.queryModelVariable(modelRelease.getId());
        result = Result.success(verficationVo);
        LOGGER.info("查询模型相关的变量结束");
        return result;
    }*/

}
