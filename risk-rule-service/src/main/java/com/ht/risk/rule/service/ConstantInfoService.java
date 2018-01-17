package com.ht.risk.rule.service;

import com.ht.risk.common.service.BaseService;
import com.ht.risk.rule.entity.ConstantInfo;
import com.ht.risk.rule.entity.EntityItemInfo;
import com.ht.risk.rule.vo.EntitySelectVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 张鹏
 * @since 2017-12-29
 */
public interface ConstantInfoService extends BaseService<ConstantInfo> {
    /**
     * 描述：设置变量的常量子集
     * @param * @param null
     * @return a
     * @autor 张鹏
     * @date 2018/1/16 14:57
     */
    EntitySelectVo setItemConstants(EntitySelectVo itemvo,EntityItemInfo item);
}
