package com.ht.risk.rule.mapper;

import com.ht.risk.rule.entity.SceneInfo;
import com.ht.risk.common.mapper.SuperMapper;

import java.util.List;

/**
 * <p>
 * 规则引擎使用场景 Mapper 接口
 * </p>
 *
 * @author 张鹏
 * @since 2017-12-15
 */
public interface SceneInfoMapper extends SuperMapper<SceneInfo> {
    /**
     * Date 2017/7/20
     * Author lihao [lihao@sinosoft.com]
     *
     * 方法说明: 获取规则引擎场景集合
     * @param sceneInfo 参数
     */
    List<SceneInfo> findBaseRuleSceneInfiList(SceneInfo sceneInfo);

}
