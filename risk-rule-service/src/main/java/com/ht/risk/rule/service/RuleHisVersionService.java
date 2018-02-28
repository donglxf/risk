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
    /**
     * 获取规则验证结果匹配信息
     * @param paramMap
     * @return
     */
    List<RuleHisVersionVo> getRuleValidationResult(Map<String,Object> paramMap);

    /**
     * 获取规则自动验证传入值信息
     * @param paramMap
     * @return
     */
    List<Map<String,Object>> getRuleBatchValidationResult(Map<String, Object> paramMap) ;

    /**
     * 根据版本id和规则名获取规则信息
     * @param vo
     * @return
     */
    List<Map<String,Object>> getHisVersionListByVidName(RuleHisVersionVo vo) ;
}
