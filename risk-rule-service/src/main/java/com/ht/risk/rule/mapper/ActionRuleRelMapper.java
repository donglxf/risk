package com.ht.risk.rule.mapper;

import com.ht.risk.rule.entity.ActionRuleRel;
import com.ht.risk.common.mapper.SuperMapper;

import java.util.List;

/**
 * <p>
 * 动作与规则信息关系表 Mapper 接口
 * </p>
 *
 * @author 张鹏
 * @since 2017-12-15
 */
public interface ActionRuleRelMapper extends SuperMapper<ActionRuleRel> {

    /**
     * Date 2017/7/24
     * Author lihao [lihao@sinosoft.com]
     *
     * 方法说明: 获取规则与动作关系集合信息
     * @param baseRuleActionRuleRelInfo 参数
     */
    List<ActionRuleRel> findBaseRuleActionRuleRelInfoList(ActionRuleRel baseRuleActionRuleRelInfo);

}
