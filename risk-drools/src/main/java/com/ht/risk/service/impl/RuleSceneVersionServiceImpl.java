package com.ht.risk.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ht.risk.common.service.impl.BaseServiceImpl;
import com.ht.risk.mapper.RuleSceneVersionMapper;
import com.ht.risk.model.RuleSceneVersion;
import com.ht.risk.service.RuleSceneVersionService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dyb
 * @since 2018-01-12
 */
@Service
public class RuleSceneVersionServiceImpl extends BaseServiceImpl<RuleSceneVersionMapper, RuleSceneVersion> implements RuleSceneVersionService {
	
	@Autowired
	private RuleSceneVersionMapper ruleSceneVersionMapper;
	
	@Override
	public RuleSceneVersion getLastVersionByType(Map<String,Object> parmaMap) throws Exception{
		return ruleSceneVersionMapper.getLastVersionByType(parmaMap);
	}

	@Override
	public RuleSceneVersion getInfoByVersionId(Map<String,Object> parmaMap) throws Exception{
		return ruleSceneVersionMapper.getInfoByVersionId(parmaMap);
	}

	public List<RuleSceneVersion> getSenceVersion(Map<String,Object> parmaMap){
		return ruleSceneVersionMapper.getSenceVersion(parmaMap);
	}

}
