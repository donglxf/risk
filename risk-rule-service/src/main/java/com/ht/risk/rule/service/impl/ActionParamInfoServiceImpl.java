package com.ht.risk.rule.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ht.risk.rule.entity.ActionParamInfo;
import com.ht.risk.rule.mapper.ActionParamInfoMapper;
import com.ht.risk.rule.service.ActionParamInfoService;
import com.ht.risk.common.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * <p>
 * 动作参数信息表 服务实现类
 * </p>
 *
 * @author 张鹏
 * @since 2017-12-15
 */
@Service
public class ActionParamInfoServiceImpl extends BaseServiceImpl<ActionParamInfoMapper, ActionParamInfo> implements ActionParamInfoService {

    @Resource
    private ActionParamInfoMapper actionParamInfoMapper;


    /**
     * Date 2017/7/24
     * Author lihao [lihao@sinosoft.com]
     * <p>
     * 方法说明: 根据动作id获取动作参数信息
     *
     * @param actionId 参数
     */
    @Override
    public List<ActionParamInfo> findRuleActionParamByActionId(Long actionId) {
        if (null == actionId) {
            throw new NullPointerException("参数缺失");
        }
        return this.actionParamInfoMapper.findRuleActionParamByActionId(actionId);
    }

    @Override
    public boolean checkKey(String key,String other) {
        Integer count = this.baseMapper.selectCount(new EntityWrapper<ActionParamInfo>()
                .eq("param_identify", key));
        count = count == null?0:count;
        return count > 0 ? true:false;
    }

}
