package com.ht.risk.service;

import java.util.List;

import com.ht.risk.model.BaseRuleEntityInfo;

/**
 * 描述：
 * CLASSPATH: RuleEntityService
 * VERSION:   1.0
 * Created by lihao
 * DATE:      2017/7/20
 */
public interface RuleEntityService {

    /**
     * Date 2017/7/20
     * Author lihao [lihao@sinosoft.com]
     * <p>
     * 方法说明: 获取规则引擎实体信息
     *
     * @param baseRuleEntityInfo 参数
     */
    List<BaseRuleEntityInfo> findBaseRuleEntityInfoList(BaseRuleEntityInfo baseRuleEntityInfo) throws Exception;

    /**
     * Date 2017/7/26
     * Author lihao [lihao@sinosoft.com]
     * <p>
     * 方法说明: 根据实体id获取实体信息
     *
     * @param id 实体id
     */
    BaseRuleEntityInfo findBaseRuleEntityInfoById(final Long id) throws Exception;
}
