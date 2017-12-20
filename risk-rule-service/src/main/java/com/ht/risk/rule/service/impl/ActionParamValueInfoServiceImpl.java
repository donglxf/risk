package com.ht.risk.rule.service.impl;

import com.ht.risk.rule.entity.ActionParamValueInfo;
import com.ht.risk.rule.mapper.ActionParamValueInfoMapper;
import com.ht.risk.rule.service.ActionParamValueInfoService;
import com.ht.risk.common.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

    @Resource
    private ActionParamValueInfoMapper actionParamValueInfoMapper;


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

}
