package com.ht.risk.rule.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.ht.risk.rule.entity.ActionRuleRel;
import com.ht.risk.common.service.BaseService;

import java.util.List;

/**
 * <p>
 * 动作与规则信息关系表 服务类
 * </p>
 *
 * @author 张鹏
 * @since 2017-12-15
 */
public interface ActionRuleRelService extends BaseService<ActionRuleRel> {

    /**
     * 描述：获取动作中间表和动作信息及 动作值信息
     *
     * @param * @param null
     * @return a
     * @autor 张鹏
     * @date 2017/12/25 17:23
     */
    List<ActionRuleRel> findActionVals(Long ruleId);
}
