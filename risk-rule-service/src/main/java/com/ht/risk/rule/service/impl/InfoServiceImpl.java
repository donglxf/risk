package com.ht.risk.rule.service.impl;

import com.ht.risk.rule.entity.Info;
import com.ht.risk.rule.entity.PropertyInfo;
import com.ht.risk.rule.entity.SceneInfo;
import com.ht.risk.rule.mapper.InfoMapper;
import com.ht.risk.rule.service.InfoService;
import com.ht.risk.common.service.impl.BaseServiceImpl;
import com.ht.risk.rule.util.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 规则信息 服务实现类
 * </p>
 *
 * @author 张鹏
 * @since 2017-12-15
 */
@Service
public class InfoServiceImpl extends BaseServiceImpl<InfoMapper, Info> implements InfoService {

    @Resource
    private InfoMapper infoMapper;



    /**
     * Date 2017/7/25
     * Author lihao [lihao@sinosoft.com]
     * <p>
     * 方法说明: 查询规则属性信息
     *
     * @param propertyInfo 参数
     */
    @Override
    public List<PropertyInfo> findBaseRulePropertyInfoList(PropertyInfo propertyInfo) throws Exception {
        return this.infoMapper.findBaseRulePropertyInfoList(propertyInfo);
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
    public List<PropertyInfo> findRulePropertyListByRuleId(final Long ruleId) throws Exception {
        return this.infoMapper.findRulePropertyListByRuleId(ruleId);
    }

    /**
     * Date 2017/7/26
     * Author lihao [lihao@sinosoft.com]
     * <p>
     * 方法说明: 根据场景获取对应的规则规则信息
     *
     * @param sceneInfo 参数
     */
    @Override
    public List<Info> findBaseRuleListByScene(SceneInfo sceneInfo) throws Exception {
        if (null == sceneInfo || (null == sceneInfo.getSceneId() &&
                StringUtil.strIsNull(sceneInfo.getSceneIdentify()))) {
            throw new NullPointerException("参数缺失！");
        }

        return this.infoMapper.findBaseRuleListByScene(sceneInfo);
    }

}
