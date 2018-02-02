package com.ht.risk.service;


import com.ht.risk.vo.RuleVo;

import java.util.List;
import java.util.Map;

public interface ModelAnalysisSerivce {


    /**
     * 根据流程实例查询规则命中信息
     * @param procInstanceId
     * @return
     */
    List<RuleVo> queryModelVerfHitRuleInfo(String procInstanceId);

    /**
     * 查询一个验证批次的规则命中信息
     * @param batchId
     * @return
     */
    List<RuleVo> queryBatchModelVerfHitRuleInfo(Long batchId);

    /**
     * 查询一个模型版本所有验证命中的规则信息
     * @param modelReleaseId
     * @return
     */
    List<RuleVo> queryReleaseModelVerfHitRuleInfo(Long modelReleaseId);

    /**
     * 根据模型任务Id查询模型执行传入参数
     * @param taskId
     * @return
     */
    List<Map<String,Object>> queryModeVerfDataInfo(Long taskId);


}