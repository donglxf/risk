package com.ht.risk.rule.service;

import com.ht.risk.api.model.log.RpcHitRuleInfo;
import com.ht.risk.rule.vo.ModelLogDetailVo;
import com.ht.risk.rule.vo.SenceParamterVo;
import com.ht.risk.rule.vo.VerficationResultVo;

import java.util.List;
import java.util.Map;

public interface ModelAnalysisSerivce {




    /**
     * 根据流程实例查询规则命中信息
     *
     * @param procInstanceId
     * @return
     */
    Map<String, List<RpcHitRuleInfo>> queryModelVerfHitRuleInfo(String procInstanceId);

    /**
     * 查询一个验证批次的规则命中信息
     *
     * @param batchId
     * @return
     */
    Map<String,List<RpcHitRuleInfo>> queryBatchModelVerfHitRuleInfo(Long batchId);

    /**
     * 根据模型任务Id查询模型执行传入参数
     *
     * @param taskId
     * @return
     */
    Map<String,SenceParamterVo> queryModeVerfDataInfo(Long taskId);


    /**
     * 查询模型任务的验证信息
     * @param taskId
     * @return
     */
    public VerficationResultVo queryTaskVerficationResult(Long taskId);

    /**
     * 查询模型任务信息
     * @param taskId
     * @return
     */
    public VerficationResultVo queryTaskServiceResult(Long taskId);

    public Long createBatchVerficationTask();

    public Long createSingleVerficationTask();

    public Map<String,Object> getAutoVerficationData(String senceCode,String version);

    /**
     * 查询模型调用日志接口
     * @param taskId
     * @return
     */
    ModelLogDetailVo queryModelLogResult(String procInstId, String type,String taskId);
}