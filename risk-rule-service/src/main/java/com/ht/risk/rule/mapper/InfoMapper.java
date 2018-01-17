package com.ht.risk.rule.mapper;

import com.ht.risk.rule.entity.Info;
import com.ht.risk.common.mapper.SuperMapper;
import com.ht.risk.rule.entity.PropertyInfo;
import com.ht.risk.rule.entity.SceneInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 规则信息 Mapper 接口
 * </p>
 *
 * @author 张鹏
 * @since 2017-12-15
 */
public interface InfoMapper extends SuperMapper<Info> {

    /**
     * Date 2017/7/25
     * Author lihao [lihao@sinosoft.com]
     *
     * 方法说明: 查询规则信息集合
     * @param baseRuleInfo 参数
     */
    List<Info> findBaseRuleInfoList(Info baseRuleInfo);

    /**
     * Date 2017/7/25
     * Author lihao [lihao@sinosoft.com]
     *
     * 方法说明: 查询规则属性信息
     * @param baseRulePropertyInfo 参数
     */
    List<PropertyInfo> findBaseRulePropertyInfoList(PropertyInfo baseRulePropertyInfo);

    /**
     * Date 2017/7/25
     * Author lihao [lihao@sinosoft.com]
     *
     * 方法说明: 根据规则获取已经配置的属性信息
     * @param ruleId 参数
     */
    List<PropertyInfo> findRulePropertyListByRuleId(@Param("ruleId") Long ruleId);

    /**
     * Date 2017/7/26
     * Author lihao [lihao@sinosoft.com]
     *
     * 方法说明: 根据场景获取对应的规则规则信息
     * @param baseRuleSceneInfo 参数
     */
    List<Info> findBaseRuleListByScene(SceneInfo baseRuleSceneInfo);

    /**
     * 联合查询规则信息，包括了分组。多对一查询
     * @param sceneId
     * @return
     */
    List<Info> findListBySceneId(@Param("sceneId") Long sceneId);
}
