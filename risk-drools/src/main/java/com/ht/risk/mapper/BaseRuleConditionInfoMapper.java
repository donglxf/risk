package com.ht.risk.mapper;

import org.apache.ibatis.annotations.Param;

import com.ht.risk.model.BaseRuleConditionInfo;

import java.util.List;

/**
 * 描述：
 * CLASSPATH: com.sky.BaseRuleConditionInfoMapper
 * VERSION:   1.0
 * Created by lihao
 * DATE:      2017/7/24
 */
public interface BaseRuleConditionInfoMapper {

    /**
     * Date 2017/7/24
     * Author lihao [lihao@sinosoft.com]
     *
     * 方法说明: 根据规则获取规则条件信息
     * @param baseRuleConditionInfo 参数
     */
    List<BaseRuleConditionInfo> findBaseRuleConditionInfoList(BaseRuleConditionInfo baseRuleConditionInfo);

    /**
     * Date 2017/7/26
     * Author lihao [lihao@sinosoft.com]
     *
     * 方法说明: 根据规则获取对应的条件信息
     * @param ruleId 规则id
     */
    List<BaseRuleConditionInfo> findRuleConditionInfoByRuleId(@Param("ruleId") Long ruleId, @Param("relFlag") Integer relFlag);
}
