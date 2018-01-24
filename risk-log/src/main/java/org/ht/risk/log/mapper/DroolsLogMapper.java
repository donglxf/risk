package org.ht.risk.log.mapper;

import org.ht.risk.log.entity.DroolsLog;

import com.ht.risk.common.mapper.SuperMapper;
import org.ht.risk.log.entity.HitRule;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 张鹏
 * @since 2018-01-10
 */
public interface DroolsLogMapper extends SuperMapper<DroolsLog> {

    public List<HitRule> queryHitRuleByProcInstId(String procInstId);


    public List<HitRule> queryHitRuleByProcInstIds(List<String> procInstIds);


}
