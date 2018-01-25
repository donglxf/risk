package org.ht.risk.log.mapper;

import com.ht.risk.common.mapper.SuperMapper;
import org.ht.risk.log.entity.HitRule;
import org.ht.risk.log.entity.TestDroolsLog;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author dyb
 * @since 2018-01-18
 */
public interface TestDroolsLogMapper extends SuperMapper<TestDroolsLog> {

    public List<HitRule> queryHitRuleByProcInstId(String procInstId);


    public List<HitRule> queryHitRuleByProcInstIds(Map<String,List<String>> paramter);

}
