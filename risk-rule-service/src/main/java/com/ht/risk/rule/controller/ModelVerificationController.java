package com.ht.risk.rule.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ht.risk.api.constant.activiti.ActivitiConstants;
import com.ht.risk.api.model.activiti.RpcActExcuteTaskInfo;
import com.ht.risk.api.model.activiti.RpcModelVerfication;
import com.ht.risk.api.model.activiti.RpcStartParamter;
import com.ht.risk.api.model.drools.DroolsParamter;
import com.ht.risk.api.model.log.RpcHitRuleInfo;
import com.ht.risk.common.result.PageResult;
import com.ht.risk.common.result.Result;
import com.ht.risk.rule.entity.ActReModel;
import com.ht.risk.rule.entity.VariableBind;
import com.ht.risk.rule.rpc.ActivitiConfigRpc;
import com.ht.risk.rule.service.ActReModelService;
import com.ht.risk.rule.service.ModelAnalysisSerivce;
import com.ht.risk.rule.util.StringUtil;
import com.ht.risk.rule.vo.ModelVerficationVo;
import com.ht.risk.rule.vo.SenceParamterVo;
import io.swagger.annotations.ApiOperation;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

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

    protected static final Logger LOGGER = LoggerFactory.getLogger(ModelVerificationController.class);

    @Resource
    private ActivitiConfigRpc activitiConfigRpc;

    @Resource
    private ModelAnalysisSerivce modelAnalysisSerivce;

    /**
     * 查询模型验证任务相关信息
     *
     * @param verficationVo
     * @return
     */
    @RequestMapping("/queryTaskVerficationResult")
    public Result queryTaskVerficationResult(ModelVerficationVo verficationVo) {
        LOGGER.info("querySingleVerficationInfo mothod invoke,paramter:" + JSON.toJSONString(verficationVo));
        Result result = null;
        Map<String, SenceParamterVo> senceMap = modelAnalysisSerivce.queryTaskVerficationResult(verficationVo.getTaskId());
        LOGGER.info("querySingleVerficationInfo mothod invoke end,result:" + JSON.toJSONString(senceMap));
        Set set = senceMap.keySet();
        List<SenceParamterVo> sences = new ArrayList<SenceParamterVo>();
        SenceParamterVo vo = null;
        for (Iterator<String> iterator = set.iterator(); iterator.hasNext(); ) {
            sences.add(senceMap.get(iterator.next()));
        }
        if (sences != null) {
            result = Result.success(sences);
        } else {
            result = Result.error(1, "查询异常...");
        }
        LOGGER.info("querySingleVerficationInfo mothod invoke end,result:" + JSON.toJSONString(result));
        return result;
    }

    @RequestMapping("/createSingleVerficationTask")
    public Result createSingleVerficationTask(HttpServletRequest request) {
        LOGGER.info("createSingleVerficationTask mothod invoke...");
        Result result = null;
        Map<String, String[]> parameterMap = request.getParameterMap();
        Set<String> keys = parameterMap.keySet();
        Iterator<String> key = keys.iterator();
        Map<String, Object> dataMap = new HashMap<String, Object>();
        Map<String, String> senceData = null;
        String[] ary = null;
        String senceCode = null;
        String variableCode = null;
        while (key.hasNext()) {
            String next = key.next();
            String temValue = parameterMap.get(next)[0];
            ary = next.split("#");
            if (ary != null && ary.length == 3) {
                senceCode = ary[1];
                if (dataMap.containsKey(senceCode + "DATA")) {
                    senceData = (Map<String, String>) dataMap.get(senceCode+ "DATA");
                    variableCode = ary[2];
                    senceData.put(variableCode, temValue);
                } else {
                    senceData = new HashMap<String, String>();
                    variableCode = ary[2];
                    senceData.put(variableCode, temValue);
                    dataMap.put(senceCode + "DATA", senceData);
                }
            }
        }
        dataMap.put(ActivitiConstants.EXCUTE_TYPE_VARIABLE_NAME, "1");
        String procDefId = request.getParameter("model_procDefId");
        String modelVersion = request.getParameter("model_version");
        RpcStartParamter rpcStartParamter = new RpcStartParamter();
        rpcStartParamter.setProcDefId(procDefId);
        rpcStartParamter.setVersion(modelVersion);
        rpcStartParamter.setType("1");
        rpcStartParamter.setData(dataMap);
        rpcStartParamter.setBatchSize(1);
        LOGGER.info("createSingleVerficationTask mothod invoke,paramter:" + JSON.toJSONString(rpcStartParamter));
        Result<Long> rpcResult = activitiConfigRpc.startInputValidateProcess(rpcStartParamter);
        LOGGER.info("createSingleVerficationTask mothod invoke,paramter:" + JSON.toJSONString(rpcResult));
        if (rpcResult != null && rpcResult.getCode() == 0 && rpcResult.getData() != null) {
            Long batchId = rpcResult.getData();
            result = Result.success(batchId);
        } else {
            result = Result.error(1, "创建模型验证任务失败");
        }
        LOGGER.info("createSingleVerficationTask mothod invoke end,result:" + JSON.toJSONString(result));
        return result;
    }

    @RequestMapping("/createBatchVerficationTask")
    public Result createBatchVerficationTask(ModelVerficationVo verficationVo) {
        LOGGER.info("createBatchVerficationTask mothod invoke,paramter:" + JSON.toJSONString(null));
        Result result = null;
        // 参数校验
        Long batchId = modelAnalysisSerivce.createBatchVerficationTask();
        // 返回结果校验
        LOGGER.info("createBatchVerficationTask mothod invoke end,result:" + JSON.toJSONString(result));
        return result;
    }
    @RequestMapping("/getAutoVerficationData")
    public Result getAutoVerficationData(@RequestBody  DroolsParamter paramter){
        LOGGER.info("getAutoVerficationData mothod invoke,paramter:" + JSON.toJSONString(null));
        Result result = null;
        if(paramter == null || StringUtils.isEmpty(paramter.getSence()) || StringUtils.isEmpty(paramter.getVersion())){
            result = Result.error(1,"参数异常...");
            LOGGER.info("getAutoVerficationData mothod invoke,paramter exception....");
            return  result;
        }
        try{
            Map data = modelAnalysisSerivce.getAutoVerficationData(paramter.getSence(),paramter.getVersion());
            result = Result.success(data);
        }catch (Exception e){
            result = Result.error(1,"执行异常...");
            e.printStackTrace();
            LOGGER.info("getAutoVerficationData mothod invoke,exception...."+e.getMessage());
        }
        LOGGER.info("getAutoVerficationData mothod invoke end,result:" + JSON.toJSONString(result));
        return result;
    }

}
