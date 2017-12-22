package com.ht.risk.rule.service;

import com.ht.risk.rule.entity.EntityItemInfo;
import com.ht.risk.common.service.BaseService;

import java.util.List;

/**
 * <p>
 * 实体属性信息 服务类
 * </p>
 *
 * @author 张鹏
 * @since 2017-12-15
 */
public interface EntityItemInfoService extends BaseService<EntityItemInfo> {

    /**
     * Date 2017/7/20
     * Author lihao [lihao@sinosoft.com]
     * <p>
     * 方法说明: 根据实体id获取规则引擎实体属性信息
     *
     * @param baseRuleEntityItemInfo 参数
     */
    List<EntityItemInfo> findBaseRuleEntityItemInfoList(EntityItemInfo baseRuleEntityItemInfo) throws Exception;

    /**
     * Date 2017/7/26
     * Author lihao [lihao@sinosoft.com]
     * <p>
     * 方法说明: 根据id获取对应的属性信息
     *
     * @param id 属性Id
     */
    EntityItemInfo findBaseRuleEntityItemInfoById(final Long id) throws Exception;

}
