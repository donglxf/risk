package com.ht.risk.rule.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.ht.risk.rule.entity.ConditionInfo;
import com.ht.risk.common.service.BaseService;

import java.util.List;

/**
 * <p>
 * 规则条件信息表 服务类
 * </p>
 *
 * @author 张鹏
 * @since 2017-12-15
 */
public interface ConditionInfoService extends BaseService<ConditionInfo> {


    /**
     * Date 2017/7/26
     * Author lihao [lihao@sinosoft.com]
     * <p>
     * 方法说明: 根据规则id获取规则条件信息
     *
     * @param ruleId 规则id
     */
    List<ConditionInfo> findRuleConditionInfoByRuleId(final Long ruleId) throws Exception;
}

