package com.ht.risk.rule.service;

import com.ht.risk.rule.entity.Info;
import com.ht.risk.common.service.BaseService;
import com.ht.risk.rule.entity.PropertyInfo;
import com.ht.risk.rule.entity.SceneInfo;

import java.util.List;

/**
 * <p>
 * 规则信息 服务类
 * </p>
 *
 * @author 张鹏
 * @since 2017-12-15
 */
public interface InfoService extends BaseService<Info> {


    /**
     * Date 2017/7/25
     * Author lihao [lihao@sinosoft.com]
     *
     * 方法说明: 查询规则属性信息
     * @param baseRulePropertyInfo 参数
     */
    List<PropertyInfo> findBaseRulePropertyInfoList(PropertyInfo baseRulePropertyInfo) throws Exception;

    /**
     * Date 2017/7/25
     * Author lihao [lihao@sinosoft.com]
     *
     * 方法说明: 根据规则获取已经配置的属性信息
     * @param ruleId 参数
     */
    List<PropertyInfo> findRulePropertyListByRuleId(final Long ruleId)throws Exception;

    /**
     * Date 2017/7/26
     * Author lihao [lihao@sinosoft.com]
     *
     * 方法说明: 根据场景获取对应的规则规则信息
     * @param baseRuleSceneInfo 参数
     */
    List<Info> findBaseRuleListByScene(SceneInfo baseRuleSceneInfo)throws Exception;

    void clearBySceneId(Long sceneId);

    /**
     * 添加规则
     * @param sceneId
     */
    Info addRule(Long sceneId);
}
