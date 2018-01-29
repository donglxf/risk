package com.ht.risk.activiti.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ht.risk.activiti.entity.ActExcuteTask;
import com.ht.risk.activiti.entity.ActProcRelease;
import com.ht.risk.activiti.entity.RiskValidateBatch;
import com.ht.risk.activiti.service.ActExcuteTaskService;
import com.ht.risk.activiti.service.RiskValidateBatchService;
import com.ht.risk.activiti.vo.VerficationModelVo;
import com.ht.risk.activiti.vo.VerificationBatchVo;
import com.ht.risk.common.result.PageResult;
import com.ht.risk.common.result.Result;
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


}
