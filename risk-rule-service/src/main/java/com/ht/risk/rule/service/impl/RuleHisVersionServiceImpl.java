package com.ht.risk.rule.service.impl;

import com.ht.risk.rule.entity.RuleHisVersion;
import com.ht.risk.rule.mapper.RuleHisVersionMapper;
import com.ht.risk.rule.service.RuleHisVersionService;
import com.ht.risk.common.service.impl.BaseServiceImpl;
import com.ht.risk.rule.vo.RuleHisVersionVo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangzhen
 * @since 2018-01-10
 */
@Service
public class RuleHisVersionServiceImpl extends BaseServiceImpl<RuleHisVersionMapper, RuleHisVersion> implements RuleHisVersionService {

    public RuleHisVersionMapper ruleHisVersionMapper;

    @Override
    public List<RuleHisVersionVo> getRuleValidationResult(Map<String, Object> paramMap) {
        return ruleHisVersionMapper.getRuleValidationResult(paramMap);
    }
}
