package com.ht.risk.rule.mapper;

import com.ht.risk.rule.entity.ModelSence;
import com.ht.risk.common.mapper.SuperMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhangzhen
 * @since 2018-01-10
 */
public interface ModelSenceMapper extends SuperMapper<ModelSence> {

    public List<ModelSence> queryModelSenceInfo(String procDefId);

}
