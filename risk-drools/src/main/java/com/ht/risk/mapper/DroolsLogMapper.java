package com.ht.risk.mapper;

import com.ht.risk.common.mapper.SuperMapper;
import com.ht.risk.model.DroolsLog;
import com.ht.risk.model.HitRule;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 张鹏
 * @since 2018-01-10
 */
public interface DroolsLogMapper extends SuperMapper<DroolsLog> {

    List<HitRule> queryHitRuleByProcInstId(String procInstId);

    List<HitRule> queryHitRuleByProcInstIds(Map<String, List<String>> paramter);
}
