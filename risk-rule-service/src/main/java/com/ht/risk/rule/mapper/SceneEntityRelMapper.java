package com.ht.risk.rule.mapper;

import com.ht.risk.rule.entity.EntityInfo;
import com.ht.risk.rule.entity.SceneEntityRel;
import com.ht.risk.common.mapper.SuperMapper;
import com.ht.risk.rule.entity.SceneInfo;

import java.util.List;

/**
 * <p>
 * 场景实体关联表 Mapper 接口
 * </p>
 *
 * @author 张鹏
 * @since 2017-12-15
 */
public interface SceneEntityRelMapper extends SuperMapper<SceneEntityRel> {
    /**
     * Date 2017/7/24
     * Author lihao [lihao@sinosoft.com]
     *
     * 方法说明: 查询场景与实体关系表信息
     * @param baseRuleSceneEntityRelInfo 参数
     */
    List<SceneEntityRel> findBaseRuleSceneEntityRelInfoList(SceneEntityRel baseRuleSceneEntityRelInfo);


    /**
     * Date 2017/7/26
     * Author lihao [lihao@sinosoft.com]
     *
     * 方法说明: 根据场景信息获取相关的实体信息
     * @param sceneInfo 参数
     */
    List<EntityInfo> findBaseRuleEntityListByScene(SceneInfo sceneInfo);

    /**
     * 通过场景查询该所有绑定的实体对象
     * @param sceneId
     * @return
     */
    String findEntityIdsBySceneId(Long sceneId);
}
