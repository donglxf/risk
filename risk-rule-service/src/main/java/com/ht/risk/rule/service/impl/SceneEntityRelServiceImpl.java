package com.ht.risk.rule.service.impl;

import com.ht.risk.rule.entity.EntityInfo;
import com.ht.risk.rule.entity.SceneEntityRel;
import com.ht.risk.rule.entity.SceneInfo;
import com.ht.risk.rule.mapper.SceneEntityRelMapper;
import com.ht.risk.rule.service.SceneEntityRelService;
import com.ht.risk.common.service.impl.BaseServiceImpl;
import com.ht.risk.rule.util.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 场景实体关联表 服务实现类
 * </p>
 *
 * @author 张鹏
 * @since 2017-12-15
 */
@Service
public class SceneEntityRelServiceImpl extends BaseServiceImpl<SceneEntityRelMapper, SceneEntityRel> implements SceneEntityRelService {

    @Resource
    private SceneEntityRelMapper sceneEntityRelMapper;



    /**
     * Date 2017/7/26
     * Author lihao [lihao@sinosoft.com]
     * <p>
     * 方法说明: 根据场景信息获取相关的实体信息
     *
     * @param sceneInfo 参数
     */
    @Override
    public List<EntityInfo> findBaseRuleEntityListByScene(SceneInfo sceneInfo) throws Exception {
        //判断参数
        if (null == sceneInfo || (StringUtil.strIsNull(sceneInfo.getSceneIdentify()) &&
                null == sceneInfo.getSceneId())) {
            throw new NullPointerException("参数缺失");
        }
        //查询数据
        return this.sceneEntityRelMapper.findBaseRuleEntityListByScene(sceneInfo);
    }

}
