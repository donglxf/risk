package com.ht.risk.rule.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.ht.risk.rule.entity.ActionInfo;
import com.ht.risk.rule.entity.ActionParamValueInfo;
import com.ht.risk.rule.entity.ActionRuleRel;
import com.ht.risk.rule.mapper.ActionParamValueInfoMapper;
import com.ht.risk.rule.mapper.ActionRuleRelMapper;
import com.ht.risk.rule.service.ActionRuleRelService;
import com.ht.risk.common.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 动作与规则信息关系表 服务实现类
 * </p>
 *
 * @author 张鹏
 * @since 2017-12-15
 */
@Service
public class ActionRuleRelServiceImpl extends BaseServiceImpl<ActionRuleRelMapper, ActionRuleRel> implements ActionRuleRelService {
    @Autowired
    private ActionRuleRelMapper actionRuleRelMapper;

    @Autowired
    private ActionParamValueInfoMapper actionParamValueInfoMapper;

    @Override
    public List<ActionRuleRel> findActionVals(Long ruleId) {
        //包含了 实体类
        List<ActionRuleRel> rels = actionRuleRelMapper.findActions(ruleId);
        //查询
        for (ActionRuleRel rel : rels){
            ActionInfo actionInfo = rel.getActionInfo();
            //获取属性值
            Wrapper<ActionParamValueInfo> wrapper = new EntityWrapper<>();
            wrapper.eq("rule_action_rel_id",rel.getRuleActionRelId());
            List<ActionParamValueInfo> vals = actionParamValueInfoMapper.selectList(wrapper);
            actionInfo.setParamValueInfoList(vals);
        }
        return  rels;
    }
}
