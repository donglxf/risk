package com.ht.risk.mapper;

import java.util.Map;

import com.ht.risk.common.mapper.SuperMapper;
import com.ht.risk.model.RuleSceneVersion;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author dyb
 * @since 2018-01-12
 */
public interface RuleSceneVersionMapper extends SuperMapper<RuleSceneVersion> {
	
	/**
	 * 获取最后版本，可以是测试或正式
	* @Title: getTestLastVersion
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @param @param parmaMap
	* @param @return    设定文件
	* @return RuleSceneVersion    返回类型
	* @throws
	 */
	public RuleSceneVersion getTestLastVersion(Map<String,Object> parmaMap);
}
