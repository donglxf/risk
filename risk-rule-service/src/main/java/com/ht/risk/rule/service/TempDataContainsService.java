package com.ht.risk.rule.service;

import com.ht.risk.common.service.BaseService;
import com.ht.risk.rule.entity.TempDataContains;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dyb
 * @since 2018-01-15
 */
public interface TempDataContainsService extends BaseService<TempDataContains> {

    List<Map<String,Object>> getAutoValidaionData();
}
