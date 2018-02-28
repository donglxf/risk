package com.ht.risk.activiti.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ht.risk.activiti.entity.ActExcuteTask;
import com.ht.risk.activiti.entity.ActProcRelease;
import com.ht.risk.activiti.entity.RiskValidateBatch;
import com.ht.risk.activiti.service.ActExcuteTaskService;
import com.ht.risk.activiti.service.RiskValidateBatchService;
import com.ht.risk.activiti.vo.ActExcuteTaskVo;
import com.ht.risk.activiti.vo.VerficationModelVo;
import com.ht.risk.activiti.vo.VerificationBatchVo;
import com.ht.risk.api.model.activiti.RpcActExcuteTaskInfo;
import com.ht.risk.api.model.activiti.RpcModelVerfication;
import com.ht.risk.common.result.PageResult;
import com.ht.risk.common.result.Result;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("")
public class VerficationResultController {

    protected static final Logger LOGGER = LoggerFactory.getLogger(VerficationResultController.class);

    @Resource
    private RiskValidateBatchService riskValidateBatchService;

    @Resource
    private ActExcuteTaskService actExcuteTaskService;


    @RequestMapping("/verficationBatchPage")
    public PageResult<List<VerificationBatchVo>> verficationBatchPage(VerficationModelVo verficationModelVo){
        LOGGER.info("resultPage mothod invoke,paramter:"+ JSON.toJSONString(verficationModelVo));
        PageResult<List<VerificationBatchVo>> result = null;
        Page<RiskValidateBatch> page = new Page<RiskValidateBatch>();
        page = riskValidateBatchService.queryBatchForPage(verficationModelVo);
        List<RiskValidateBatch> list = page.getRecords();
        List<VerificationBatchVo> vos = new ArrayList<VerificationBatchVo>();
        for(Iterator<RiskValidateBatch> iterator = list.iterator();iterator.hasNext();){
            vos.add(new VerificationBatchVo(iterator.next()));
        }
        result =  PageResult.success(vos,page.getTotal());
        LOGGER.info("resultPage mothod invoke,reustl:"+ JSON.toJSONString(result));
        return result;
    }


    /**
     * 查询模型的验证任务列表
     * @param verficationModelVo
     * @return
     */
    @RequestMapping("/verficationTaskPage")
    public PageResult<List<ActExcuteTaskVo>> verficationTaskPage(VerficationModelVo verficationModelVo){
        LOGGER.info("resultPage mothod invoke,paramter:"+ JSON.toJSONString(verficationModelVo));
        PageResult<List<ActExcuteTaskVo>> result = null;
        if(verficationModelVo == null || StringUtils.isEmpty(verficationModelVo.getModelCode())){
            result = PageResult.error(1,"参数异常！");
            LOGGER.error("verficationTaskPage query exception,parater exception...");
            return result;
        }
        Page<ActExcuteTaskVo> page = new Page<ActExcuteTaskVo>();
        page.setSize(verficationModelVo.getLimit());
        page.setCurrent(verficationModelVo.getPage());
        String modelCode = verficationModelVo.getModelCode();
        page = actExcuteTaskService.verficationTaskPage(page,verficationModelVo);
        result =  PageResult.success(page.getRecords(),page.getTotal());
        LOGGER.info("resultPage mothod invoke,reustl:"+ JSON.toJSONString(result));
        return result;
    }

}
