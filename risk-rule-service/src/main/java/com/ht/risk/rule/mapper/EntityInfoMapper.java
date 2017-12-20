package com.ht.risk.rule.mapper;

import com.ht.risk.rule.entity.EntityInfo;
import com.ht.risk.common.mapper.SuperMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 规则引擎实体信息表 Mapper 接口
 * </p>
 *
 * @author 张鹏
 * @since 2017-12-15
 */
public interface EntityInfoMapper extends SuperMapper<EntityInfo> {


    /**
     * Date 2017/7/20
     * Author lihao [lihao@sinosoft.com]
     * <p>
     * 方法说明: 获取规则引擎实体信息
     *
     * @param baseRuleEntityInfo 参数
     */
    List<EntityInfo> findBaseRuleEntityInfoList(EntityInfo baseRuleEntityInfo);

    /**
     * Date 2017/7/26
     * Author lihao [lihao@sinosoft.com]
     * <p>
     * 方法说明: 根据实体id获取实体信息
     *
     * @param id 实体id
     */
    EntityInfo findBaseRuleEntityInfoById(@Param("id") Long id);

}
