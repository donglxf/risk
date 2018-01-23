package org.ht.risk.log.service.impl;

import org.ht.risk.log.service.ModelAnalysisSerivce;
import org.ht.risk.log.vo.RuleVo;

import java.util.List;
import java.util.Map;

public class ModelAnalysisSerivceImpl implements ModelAnalysisSerivce {


    @Override
    public List<RuleVo> queryModelVerfHitRuleInfo(String procInstanceId) {
        // 1 查询模型版本信息

        // 2 查询模型版本对应的策略版本信息

        // 3 查询策略命中的规则信息


        return null;
    }

    @Override
    public List<RuleVo> queryBatchModelVerfHitRuleInfo(Long batchId) {
        return null;
    }

    @Override
    public List<RuleVo> queryReleaseModelVerfHitRuleInfo(Long modelReleaseId) {
        return null;
    }

    @Override
    public List<Map<String, Object>> queryModeVerfDataInfo(Long taskId) {
        return null;
    }
}
