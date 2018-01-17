package com.ht.risk.service;

import java.util.Map;

import com.ht.risk.common.service.BaseService;
import com.ht.risk.model.RuleSceneVersion;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dyb
 * @since 2018-01-12
 */
public interface RuleSceneVersionService extends BaseService<RuleSceneVersion> {
	
	public RuleSceneVersion getTestLastVersion(Map<String,Object> parmaMap) throws Exception;
	
}
