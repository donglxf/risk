package com.ht.risk.rule.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.ht.risk.rule.entity.EntityInfo;
import com.ht.risk.rule.entity.SceneEntityRel;
import com.ht.risk.common.service.BaseService;
import com.ht.risk.rule.entity.SceneInfo;

import java.util.List;

/**
 * <p>
 * 场景实体关联表 服务类
 * </p>
 *
 * @author 张鹏
 * @since 2017-12-15
 */
public interface SceneEntityRelService extends BaseService<SceneEntityRel> {


    /**
     * Date 2017/7/26
     * Author lihao [lihao@sinosoft.com]
     *
     * 方法说明: 根据场景信息获取相关的实体信息
     * @param SceneInfo 参数
     */
    List<EntityInfo> findBaseRuleEntityListByScene(SceneInfo sceneInfo)throws Exception;

}
