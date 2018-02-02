package com.ht.risk.service;

import com.ht.risk.api.model.drools.RpcDroolsLog;
import com.ht.risk.api.model.log.RpcHitRuleInfo;
import com.ht.risk.common.service.BaseService;
import com.ht.risk.model.TestDroolsLog;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author dyb
 * @since 2018-01-18
 */
public interface TestDroolsLogService extends BaseService<TestDroolsLog> {

    /**
     * 根据流程实例id查询该流程触碰规则
     *
     * @param procInstId
     * @return
     */
    public List<RpcHitRuleInfo> queryHitRuleInfoByProcInstId(String procInstId);

    public List<RpcHitRuleInfo> countHitRuleInfo(List<String> procInstIds);

    public List<RpcDroolsLog> queryTestModelDroolsLogs(String procInstId);

}
