package com.ht.risk.rule.controller;

import com.alibaba.fastjson.JSON;
import com.ht.risk.api.model.activiti.ModelParamter;
import com.ht.risk.api.model.activiti.RpcDeployResult;
import com.ht.risk.common.controller.BaseController;
import com.ht.risk.common.result.Result;
import com.ht.risk.rule.service.ModelDeployService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 风控模型部署
 * </p>
 *
 * @author zhangzhen
 * @since 2018-01-10
 */
@RestController
@RequestMapping("/modelDeploy")
public class ModelDeployController extends BaseController{

    protected static final Logger LOGGER = LoggerFactory.getLogger(ModelDeployController.class);

    @Resource
    private ModelDeployService modelDeployService;

    @RequestMapping("deploy")
    public Result modelDeploy(ModelParamter paramter){
        LOGGER.info("模型部署,参数paramter:"+ JSON.toJSONString(paramter));
        Result<String> data = null;
        if(paramter == null || StringUtils.isEmpty(paramter.getModelId())){
            data = Result.error(1,"参数异常！");
            return data;
        }
        Result<RpcDeployResult> result = null;
        try{
            paramter.setUserId(this.getUserId());
            result = modelDeployService.modelDeploy(paramter);
        }catch(Exception e){
            data = Result.error(1,"部署流程异常,错误信息："+e.getMessage());
            LOGGER.error("部署流程异常!",e);
            return data;
        }
        if(result != null && result.getCode() != 0){
            data = Result.error(2,"部署流程异常,错误信息："+result.getMsg());
            return data;
        }
        data = Result.success(result.getData().getDeploymentId());
        LOGGER.info("模型部署结束！");
        return data;
    }




}
