package com.ht.risk.rule.mapper;

import com.ht.risk.common.mapper.SuperMapper;
import com.ht.risk.rule.entity.RuleHisVersion;
import com.ht.risk.rule.vo.RuleHisVersionVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhangzhen
 * @since 2018-01-10
 */
public interface RuleHisVersionMapper extends SuperMapper<RuleHisVersion> {

    List<RuleHisVersionVo> getRuleValidationResult(Map<String,Object> map);

    List<Map<String,Object>> getRuleBatchValidationResult(Map<String,Object> map);

}
