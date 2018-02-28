package com.ht.risk.rule.service.impl;

import com.ht.risk.rule.entity.RuleHisVersion;
import com.ht.risk.rule.mapper.RuleHisVersionMapper;
import com.ht.risk.rule.service.RuleHisVersionService;
import com.ht.risk.common.service.impl.BaseServiceImpl;
import com.ht.risk.rule.vo.RuleHisVersionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zhangzhen
 * @since 2018-01-10
 */
@Service
public class RuleHisVersionServiceImpl extends BaseServiceImpl<RuleHisVersionMapper, RuleHisVersion> implements RuleHisVersionService {

    @Autowired
    private RuleHisVersionMapper ruleHisVersionMapper;

    @Override
    public List<RuleHisVersionVo> getRuleValidationResult(Map<String, Object> paramMap) {
        return ruleHisVersionMapper.getRuleValidationResult(paramMap);
    }

    @Override
    public List<Map<String, Object>> getRuleBatchValidationResult(Map<String, Object> paramMap) {
        return ruleHisVersionMapper.getRuleBatchValidationResult(paramMap);
    }

    @Override
    public List<Map<String, Object>> getHisVersionListByVidName(RuleHisVersionVo vo) {
        List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
        List<String> list = vo.gettRuleName();
        list.forEach(t -> {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("versionId", vo.getVersionId());
            map.put("ruleName", t);
            Map m = ruleHisVersionMapper.getHisVersionListByVidName(map);
            listMap.add(m);
        });
        return listMap;
    }
}
