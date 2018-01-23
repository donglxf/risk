package org.ht.risk.log.service;

import com.ht.risk.api.model.log.RpcHitRuleInfo;
import org.ht.risk.log.entity.DroolsLog;

import com.ht.risk.common.service.BaseService;

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

    /**
     *  根据流程实例id查询该流程触碰规则
     * @param procInstId
     * @return
     */
    public List<RpcHitRuleInfo> queryHitRuleInfoByProcInstId(String procInstId);

    public List<RpcHitRuleInfo> countHitRuleInfo(List<String> procInstIds);

}
