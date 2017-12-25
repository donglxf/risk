package com.ht.risk.service.impl;

import org.springframework.stereotype.Service;

import com.ht.risk.mapper.BaseRuleSceneInfoMapper;
import com.ht.risk.model.BaseRuleSceneInfo;
import com.ht.risk.service.RuleSceneService;

import javax.annotation.Resource;
import java.util.List;

/**
 * 描述：
 * CLASSPATH: com.sky.RuleSceneServiceImpl
 * VERSION:   1.0
 * Created by lihao
 * DATE:      2017/7/20
 */
@Service
public class RuleSceneServiceImpl implements RuleSceneService {

    @Resource
    private BaseRuleSceneInfoMapper baseRuleSceneInfoMapper;
    /**
     * Date 2017/7/20
     * Author lihao [lihao@sinosoft.com]
     * <p>
     * 方法说明: 获取规则引擎场景集合
     *
     * @param sceneInfo 参数
     */
    @Override
    public List<BaseRuleSceneInfo> findBaseRuleSceneInfiList(BaseRuleSceneInfo sceneInfo) throws Exception {
        return this.baseRuleSceneInfoMapper.findBaseRuleSceneInfiList(sceneInfo);
    }
}
