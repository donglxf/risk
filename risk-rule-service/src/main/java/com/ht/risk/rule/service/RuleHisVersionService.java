package com.ht.risk.rule.service;

import com.ht.risk.common.service.BaseService;
import com.ht.risk.rule.entity.RuleHisVersion;
import com.ht.risk.rule.vo.RuleHisVersionVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhangzhen
 * @since 2018-01-10
 */
public interface RuleHisVersionService extends BaseService<RuleHisVersion> {
    List<RuleHisVersionVo> getRuleValidationResult(Map<String,Object> paramMap);
}
