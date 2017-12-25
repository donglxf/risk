package com.ht.risk.rule.service.impl;

import com.ht.risk.rule.entity.EntityInfo;
import com.ht.risk.rule.mapper.EntityInfoMapper;
import com.ht.risk.rule.service.EntityInfoService;
import com.ht.risk.common.service.impl.BaseServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 规则引擎实体信息表 服务实现类
 * </p>
 *
 * @author 张鹏
 * @since 2017-12-15
 */
@Service
public class EntityInfoServiceImpl extends BaseServiceImpl<EntityInfoMapper, EntityInfo> implements EntityInfoService {

    Logger logger = LoggerFactory.getLogger(EntityInfoServiceImpl.class);

    @Autowired
    private EntityInfoMapper entityInfoMapper;


    /**
     * Date 2017/7/20
     * Author lihao [lihao@sinosoft.com]
     * <p>
     * 方法说明: 获取规则引擎实体信息
     *
     * @param baseRuleEntityInfo 参数
     */
    @Override
    public List<EntityInfo> findBaseRuleEntityInfoList(EntityInfo baseRuleEntityInfo) throws Exception {
        return this.entityInfoMapper.findBaseRuleEntityInfoList(baseRuleEntityInfo);
    }

    /**
     * Date 2017/7/26
     * Author lihao [lihao@sinosoft.com]
     * <p>
     * 方法说明: 根据实体id获取实体信息
     *
     * @param id 实体id
     */
    @Override
    public EntityInfo findBaseRuleEntityInfoById(Long id) throws Exception {
        if (null == id) {
            throw new NullPointerException("参数缺失");
        }
        return this.entityInfoMapper.findBaseRuleEntityInfoById(id);
    }

}
