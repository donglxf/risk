package com.ht.risk.rule.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.ht.risk.rule.entity.ActionParamValueInfo;
import com.ht.risk.common.service.BaseService;

/**
 * <p>
 * 动作参数对应的属性值信息表 服务类
 * </p>
 *
 * @author 张鹏
 * @since 2017-12-15
 */
public interface ActionParamValueInfoService extends BaseService<ActionParamValueInfo> {


    /**
     * Date 2017/7/27
     * Author lihao [lihao@sinosoft.com]
     * <p>
     * 方法说明: 根据参数id获取参数value
     *
     * @param paramId 参数id
     */
    ActionParamValueInfo findRuleParamValueByActionParamId(final Long paramId)throws Exception;

}
