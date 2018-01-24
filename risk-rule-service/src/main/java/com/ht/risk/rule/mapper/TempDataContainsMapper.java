package com.ht.risk.rule.mapper;

import com.ht.risk.common.mapper.SuperMapper;
import com.ht.risk.rule.entity.TempDataContains;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author dyb
 * @since 2018-01-15
 */
public interface TempDataContainsMapper extends SuperMapper<TempDataContains> {


    public List<Map<String,Object>> getAutoValidaionData(Map<String,String> paramter);

}
