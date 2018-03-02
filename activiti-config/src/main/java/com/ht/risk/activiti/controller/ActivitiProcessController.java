package com.ht.risk.activiti.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.plugins.Page;
import com.ht.risk.activiti.entity.ActProcRelease;
import com.ht.risk.activiti.service.ActProcReleaseService;
import com.ht.risk.activiti.vo.ActProcReleaseVo;
import com.ht.risk.activiti.vo.ModelStartVo;
import com.ht.risk.api.model.activiti.ModelParamter;
import com.ht.risk.api.model.activiti.RpcDeployResult;
import com.ht.risk.api.model.activiti.RpcStartParamter;
import com.ht.risk.common.controller.BaseController;
import com.ht.risk.common.result.PageResult;
import com.ht.risk.common.result.Result;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhangzhen
 * @since 2018-01-16
 */
@RestController
@RequestMapping("")
public class ActivitiProcessController extends BaseController {

    protected static final Logger LOGGER = LoggerFactory.getLogger(ActivitiProcessController.class);
    @Resource
    private ActProcReleaseService actProcReleaseService;
    /**
     *  查询待验证的模型信息
     * @param page
     * @param actProcRelease
     * @return
     */
    @GetMapping("/page")
    public PageResult<List<ActProcReleaseVo>> queryProcReleaseForPage(Page<ActProcRelease> page, ActProcRelease actProcRelease){
        LOGGER.info("查询模型版本分页信息开始");
        PageResult<List<ActProcReleaseVo>> result = null;
        if(actProcRelease == null || actProcRelease.getModelId() == null){
            result =PageResult.success(null,0);
            return result;
        }
        Page<ActProcRelease> modelReleasePage = actProcReleaseService.queryProcReleaseForPage(page,actProcRelease);
        List<ActProcReleaseVo> releaseVos = new ArrayList<ActProcReleaseVo>(modelReleasePage.getRecords().size());
        for (Iterator<ActProcRelease> iterator = modelReleasePage.getRecords().iterator(); iterator.hasNext();){
            releaseVos.add(new ActProcReleaseVo(iterator.next()));
        }
        result = PageResult.success(releaseVos,modelReleasePage.getTotal());
        LOGGER.info("查询模型版本证分页信息结束");
        return result;
    }


    /**
     * 模型部署
     * @param paramter
     * @return
     */
    @RequestMapping("/deploy")
    public Result<RpcDeployResult> deploy(@RequestBody ModelParamter paramter){
        LOGGER.info("模型部署,参数paramter:"+ JSON.toJSONString(paramter));
        Result<RpcDeployResult> data = null;
        if(paramter == null || StringUtils.isEmpty(paramter.getModelId())){
            data = Result.error(1,"参数异常！");
            return data;
        }
        try{
            data = actProcReleaseService.proceDeploy(paramter,getUserId());
        }catch(Exception e){
            data = Result.error(1,"部署流程异常,错误信息："+e.getMessage());
            LOGGER.error("部署流程异常!",e);
            return data;
        }
        if(data == null || data.getCode() != 0){
            data = Result.error(2,"部署流程异常,错误信息："+data.getMsg());
            return data;
        }
        LOGGER.info("模型部署结束！");
        return data;
    }

    /**
     * 模型启动
     * @param rpcStartParamter
     * @return
     */
    @RequestMapping("/start")
    public Result startProcess(@RequestBody RpcStartParamter rpcStartParamter){
        LOGGER.info("startProcess invoke start,paramter:"+ JSON.toJSONString(rpcStartParamter));
        Result data = null;
        if(rpcStartParamter == null || StringUtils.isEmpty(rpcStartParamter.getProcDefId()) || StringUtils.isEmpty(rpcStartParamter.getVersion())){
            data = Result.error(1,"参数异常！");
            LOGGER.info("startProcess invoke start error,paramter exception...");
            return data;
        }
        String procInstId = actProcReleaseService.startProcess(rpcStartParamter,getUserId());
        if(StringUtils.isEmpty(procInstId)){
            data = Result.error(1,"启动模型异常！");
            LOGGER.info("startProcess invoke start error");
            return data;
        }
        data = Result.success(procInstId);
        LOGGER.info("startProcess invoke end ...;procInstId:"+procInstId);
        return data;
    }

    /**
     * 模型启动
     * @param modelStartVo
     * @return
     */
    @RequestMapping("/startModel")
    public Result startModel(@RequestBody ModelStartVo modelStartVo){
        LOGGER.info("startProcess invoke start,paramter:"+ JSON.toJSONString(modelStartVo));
        Result data = null;
        if(modelStartVo == null || StringUtils.isEmpty(modelStartVo.getModelCode())){
            data = Result.error(1,"参数异常！");
            LOGGER.info("startProcess invoke start error,paramter exception...");
            return data;
        }
        String taskid = actProcReleaseService.startModel(modelStartVo,getUserId());
        if(StringUtils.isEmpty(taskid)){
            data = Result.error(1,"启动模型异常！");
            LOGGER.info("startProcess invoke start error");
            return data;
        }
        data = Result.success(taskid);
        LOGGER.info("startProcess invoke end ...;procInstId:"+taskid);
        return data;
    }

    /**
     * 启动模型，配置变量绑定，自动取数
     * @param rpcStartParamter
     * @return
     */
    // TODO 自动验证
    @RequestMapping("/verficationBatch")
    public Result<Long> startBatchValidateProcess(@RequestBody RpcStartParamter rpcStartParamter){
        LOGGER.info("startProcess invoke start,paramter:"+ JSON.toJSONString(rpcStartParamter));
        Result<Long> data = null;
        if(rpcStartParamter == null || StringUtils.isEmpty(rpcStartParamter.getProcDefId()) || StringUtils.isEmpty(rpcStartParamter.getVersion())){
            data = Result.error(1,"参数异常！");
            LOGGER.info("startProcess invoke start error,paramter exception...");
            return data;
        }
        Long batchId = null;
        try {
            batchId = actProcReleaseService.startBatchValidateProcess(rpcStartParamter,getUserId());
        } catch (Exception e) {
            data = Result.error(1,"批量启动模型异常！");
            LOGGER.info("startProcess invoke start error");
            return data;
        }
        data = Result.success(batchId);
        LOGGER.info("startProcess invoke end ...;batchId:"+batchId);
        return data;
    }

    /**
     * 启动模型，用户输入数据
     * @param rpcStartParamter
     * @return
     */
    @RequestMapping("/verficationSingle")
    public Result startInputValidateProcess(@RequestBody RpcStartParamter rpcStartParamter){
        LOGGER.info("startInputValidateProcess invoke start,paramter:"+ JSON.toJSONString(rpcStartParamter));
        Result data = null;
        if(rpcStartParamter == null || StringUtils.isEmpty(rpcStartParamter.getProcDefId()) || StringUtils.isEmpty(rpcStartParamter.getVersion())){
            data = Result.error(1,"参数异常！");
            LOGGER.info("startInputValidateProcess invoke start error,paramter exception...");
            return data;
        }
        Long batchId = null;
        try {
            batchId = actProcReleaseService.startInputValidateProcess(rpcStartParamter,getUserId());
        } catch (Exception e) {
            e.printStackTrace();
            data = Result.error(1,"启动模型异常！");
            LOGGER.info("startProcess invoke start error");
            return data;
        }
        data = Result.success(batchId);
        LOGGER.info("startProcess invoke end ...;procInstId:"+batchId);
        return data;
    }

}

