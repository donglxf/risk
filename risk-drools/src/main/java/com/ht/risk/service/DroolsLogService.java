package com.ht.risk.service;

import com.ht.risk.api.model.drools.RpcDroolsLog;
import com.ht.risk.api.model.log.RpcHitRuleInfo;
import com.ht.risk.common.service.BaseService;
import com.ht.risk.model.DroolsLog;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 张鹏
 * @since 2018-01-10
 */
public interface DroolsLogService extends BaseService<DroolsLog> {

    List<RpcHitRuleInfo> queryHitRuleInfoByProcInstId(String procInstId);

    List<RpcHitRuleInfo> countHitRuleInfo(List<String> procInstId);

    List<RpcDroolsLog> queryModelDroolsLogs(String procinstId);
}
