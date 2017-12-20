package com.ht.risk.rule.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.ht.risk.rule.entity.ActionParamInfo;
import com.ht.risk.common.service.BaseService;

import java.util.List;

/**
 * <p>
 * 动作参数信息表 服务类
 * </p>
 *
 * @author 张鹏
 * @since 2017-12-15
 */
public interface ActionParamInfoService extends BaseService<ActionParamInfo> {


    /**
     * Date 2017/7/24
     * Author lihao [lihao@sinosoft.com]
     *
     * 方法说明: 根据动作id获取动作参数信息
     * @param actionId 参数
     */
    List<ActionParamInfo> findRuleActionParamByActionId(final Long actionId);

}
