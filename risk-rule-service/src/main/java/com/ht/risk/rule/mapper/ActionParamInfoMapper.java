package com.ht.risk.rule.mapper;

import com.ht.risk.rule.entity.ActionParamInfo;
import com.ht.risk.common.mapper.SuperMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 动作参数信息表 Mapper 接口
 * </p>
 *
 * @author 张鹏
 * @since 2017-12-15
 */
public interface ActionParamInfoMapper extends SuperMapper<ActionParamInfo> {

    /**
     * Date 2017/7/24
     * Author lihao [lihao@sinosoft.com]
     *
     * 方法说明: 获取动作参数信息
     * @param baseRuleActionParamInfo 参数
     */
    List<ActionParamInfo> findBaseRuleActionParamInfoList(ActionParamInfo baseRuleActionParamInfo);

    /**
     * Date 2017/7/24
     * Author lihao [lihao@sinosoft.com]
     *
     * 方法说明: 根据动作id获取动作参数信息
     * @param actionId 参数
     */
    List<ActionParamInfo> findRuleActionParamByActionId(@Param("actionId") Long actionId);

}
