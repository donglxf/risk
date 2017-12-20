package com.ht.risk.rule.service.impl;

import com.ht.risk.rule.entity.SceneInfo;
import com.ht.risk.rule.mapper.SceneInfoMapper;
import com.ht.risk.rule.service.SceneInfoService;
import com.ht.risk.common.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 规则引擎使用场景 服务实现类
 * </p>
 *
 * @author 张鹏
 * @since 2017-12-15
 */
@Service
public class SceneInfoServiceImpl extends BaseServiceImpl<SceneInfoMapper, SceneInfo> implements SceneInfoService {

    @Resource
    private SceneInfoMapper sceneInfoMapper;
    /**
     * Date 2017/7/20
     * Author lihao [lihao@sinosoft.com]
     * <p>
     * 方法说明: 获取规则引擎场景集合
     *
     * @param sceneInfo 参数
     */
    @Override
    public List<SceneInfo> findBaseRuleSceneInfiList(SceneInfo sceneInfo) throws Exception {
        return this.sceneInfoMapper.findBaseRuleSceneInfiList(sceneInfo);
    }

}
