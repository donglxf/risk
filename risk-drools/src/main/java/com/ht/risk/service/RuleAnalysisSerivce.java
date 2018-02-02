package com.ht.risk.service;


import com.ht.risk.vo.RuleVo;

import java.util.List;
import java.util.Map;

public interface RuleAnalysisSerivce {

    /**
     * 查询决策验证命中的规则信息
     * @param senceVerficationBatchId
     * @return
     */
    List<RuleVo> queryBatchSenceHitRuleInof(Long senceVerficationBatchId);

    /**
     * 根据日志id查询策略命中的规则信息
     * @param droolsLogId
     * @return
     */
    List<RuleVo> querySenceHitRuleInfo(Long droolsLogId);

    /**
     * 根据日志id查询策略验证的数据录入信息
     * @param droolsLogId
     * @return
     */
    List<Map<String,Object>> querySenceVerficationData(Long droolsLogId);



}
