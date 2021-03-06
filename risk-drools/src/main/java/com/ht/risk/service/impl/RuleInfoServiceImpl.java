package com.ht.risk.service.impl;

import com.github.pagehelper.PageInfo;
import com.ht.risk.mapper.BaseRuleInfoMapper;
import com.ht.risk.model.BaseRuleInfo;
import com.ht.risk.model.BaseRulePropertyInfo;
import com.ht.risk.model.BaseRulePropertyRelInfo;
import com.ht.risk.model.BaseRuleSceneInfo;
import com.ht.risk.service.RuleInfoService;
import com.ht.risk.util.StringUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 描述：
 * CLASSPATH: com.sky.RuleInfoServiceImpl
 * VERSION:   1.0
 * Created by lihao
 * DATE:      2017/7/25
 */
@Service
public class RuleInfoServiceImpl implements RuleInfoService {

    @Autowired
    private BaseRuleInfoMapper baseRuleInfoMapper;

    /**
     * Date 2017/7/25
     * Author lihao [lihao@sinosoft.com]
     * <p>
     * 方法说明: 获取规则集合信息
     *
     * @param baseRuleInfo 参数
     * @param page         分页参数
     */
    @Override
    public PageInfo<BaseRuleInfo> findBaseRuleInfoPage(BaseRuleInfo baseRuleInfo, PageInfo page) throws Exception {

        List<BaseRuleInfo> list = this.baseRuleInfoMapper.findBaseRuleInfoList(baseRuleInfo);
        return new PageInfo<>(list);
    }

    /**
     * Date 2017/7/25
     * Author lihao [lihao@sinosoft.com]
     * <p>
     * 方法说明: 查询规则属性信息
     *
     * @param baseRulePropertyInfo 参数
     */
    @Override
    public List<BaseRulePropertyInfo> findBaseRulePropertyInfoList(BaseRulePropertyInfo baseRulePropertyInfo) throws Exception {
        return this.baseRuleInfoMapper.findBaseRulePropertyInfoList(baseRulePropertyInfo);
    }

    /**
     * Date 2017/7/25
     * Author lihao [lihao@sinosoft.com]
     * <p>
     * 方法说明: 根据规则获取已经配置的属性信息
     *
     * @param ruleId 参数
     */
    @Override
    public List<BaseRulePropertyRelInfo> findRulePropertyListByRuleId(final Long ruleId) throws Exception {
        return this.baseRuleInfoMapper.findRulePropertyListByRuleId(ruleId);
    }

    /**
     * Date 2017/7/26
     * Author lihao [lihao@sinosoft.com]
     * <p>
     * 方法说明: 根据场景获取对应的规则规则信息
     *
     * @param baseRuleSceneInfo 参数
     */
    @Override
    public List<BaseRuleInfo> findBaseRuleListByScene(BaseRuleSceneInfo baseRuleSceneInfo) throws Exception {
        if (null == baseRuleSceneInfo || (null == baseRuleSceneInfo.getSceneId() &&
                StringUtil.strIsNull(baseRuleSceneInfo.getSceneIdentify()))) {
            throw new NullPointerException("参数缺失！");
        }

        return this.baseRuleInfoMapper.findBaseRuleListByScene(baseRuleSceneInfo);
    }
}
