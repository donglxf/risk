package com.ht.risk.rule.service.impl;

import com.ht.risk.rule.entity.ActionInfo;
import com.ht.risk.rule.entity.ActionParamInfo;
import com.ht.risk.rule.entity.ActionParamValueInfo;
import com.ht.risk.rule.entity.ActionRuleRel;
import com.ht.risk.rule.mapper.ActionInfoMapper;
import com.ht.risk.rule.mapper.ActionParamInfoMapper;
import com.ht.risk.rule.mapper.ActionParamValueInfoMapper;
import com.ht.risk.rule.mapper.ActionRuleRelMapper;
import com.ht.risk.rule.service.ActionParamValueInfoService;
import com.ht.risk.common.service.impl.BaseServiceImpl;
import com.ht.ussp.bean.LoginUserInfoHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 动作参数对应的属性值信息表 服务实现类
 * </p>
 *
 * @author 张鹏
 * @since 2017-12-15
 */
@Service
public class ActionParamValueInfoServiceImpl extends BaseServiceImpl<ActionParamValueInfoMapper, ActionParamValueInfo> implements ActionParamValueInfoService {
    @Autowired
    LoginUserInfoHelper userInfoHelper;
    @Resource
    private ActionParamValueInfoMapper actionParamValueInfoMapper;

    @Resource
    private ActionRuleRelMapper actionRuleRelMapper;

    @Resource
    private ActionParamInfoMapper actionParamInfoMapper;

    @Resource
    private ActionInfoMapper actionInfoMapper;



    /**
     * Date 2017/7/27
     * Author lihao [lihao@sinosoft.com]
     * <p>
     * 方法说明: 根据参数id获取参数value
     *
     * @param paramId 参数id
     */
    @Override
    public ActionParamValueInfo findRuleParamValueByActionParamId(Long paramId) throws Exception {
        if(null == paramId){
            throw new NullPointerException("参数缺失");
        }
        return this.actionParamValueInfoMapper.findRuleParamValueByActionParamId(paramId);
    }

    @Override
    public void add(ActionParamValueInfo actionValue, Long ruleId) {
        //获取参数
        ActionParamInfo paramInfo = actionParamInfoMapper.selectById(actionValue.getActionParamId());
        //添加动作规则中间表,确定是否需要新增一个 动作规则中间表
        ActionRuleRel relWhere = new ActionRuleRel();
        relWhere.setRuleId(ruleId);
        relWhere.setActionId(paramInfo.getActionId());
        ActionRuleRel rel = actionRuleRelMapper.selectOne(relWhere);
        if(rel == null ){
            rel = new ActionRuleRel();
            rel.setRuleId(ruleId);
            rel.setActionId(paramInfo.getActionId());
            rel.setCreTime(new Date());
            rel.setIsEffect(1);
            rel.setCreUserId( StringUtils.isEmpty( userInfoHelper.getUserId()) ? "-1" :  userInfoHelper.getUserId());
            actionRuleRelMapper.insert(rel);
        }
        //方法名
        ActionInfo actionInfo = actionInfoMapper.selectById(paramInfo.getActionId());
        //添加值得表
        actionValue.setCreTime(new Date());
        actionValue.setCreUserId(StringUtils.isEmpty( userInfoHelper.getUserId()) ? "-1" :  userInfoHelper.getUserId());
        actionValue.setIsEffect(1);
        actionValue.setRuleActionRelId(rel.getRuleActionRelId());
        actionValue.setRemark("["+actionInfo.getActionName()+"]:"+actionValue.getParamValue()+"("+paramInfo.getActionParamName()+")");
        actionParamValueInfoMapper.insert(actionValue);
    }

    @Override
    public List<ActionParamValueInfo> findActionParamValByRuleId(Long ruleId) {
        return null;
    }

}
