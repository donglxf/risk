package com.ht.risk.rule.service.impl;

import com.ht.risk.rule.entity.EntityInfo;
import com.ht.risk.rule.mapper.EntityInfoMapper;
import com.ht.risk.rule.service.EntityInfoService;
import com.ht.risk.common.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

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

}
