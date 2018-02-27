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

	/**
	 * 获取最新版本记录
	 * @param parmaMap sceneIdentify：  type:
	 * @return
	 * @throws Exception
	 */
	public RuleSceneVersion getLastVersionByType(Map<String,Object> parmaMap) throws Exception;

	/**
	 * 根据版本id获取当前版本记录
	 * @param parmaMap
	 * @return
	 * @throws Exception
	 */
	public RuleSceneVersion getInfoByVersionId(Map<String,Object> parmaMap) throws Exception;
	
}
