package com.ht.risk.rule.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ht.risk.rule.entity.SceneInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.SqlHelper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ht.risk.common.service.impl.BaseServiceImpl;
import com.ht.risk.rule.entity.SceneInfoVersion;
import com.ht.risk.rule.entity.SceneVersion;
import com.ht.risk.rule.mapper.SceneInfoVersionMapper;
import com.ht.risk.rule.mapper.SceneVersionMapper;
import com.ht.risk.rule.service.SceneVersionService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 张鹏
 * @since 2018-01-03
 */
@Service
public class SceneVersionServiceImpl extends BaseServiceImpl<SceneVersionMapper, SceneVersion> implements SceneVersionService {

    @Autowired
    private SceneInfoVersionMapper versionMapper;

    @Autowired
    public SceneVersionMapper sceneVersionMapper;

    @Override
    public Page<SceneInfoVersion> selectVersionPage(Page<SceneInfoVersion> pages, Wrapper<SceneInfoVersion> wrapper) {
        SqlHelper.fillWrapper(pages, wrapper);
        pages.setRecords(this.versionMapper.selectPage(pages, wrapper));
        return pages;
    }

	@Override
	public Page<SceneVersion> getNoBindVariableRecord(Page<SceneVersion> pages, Wrapper<SceneVersion> wrapper) {
		SqlHelper.fillWrapper(pages, wrapper);
        pages.setRecords(sceneVersionMapper.getNoBindVariableRecord(pages, wrapper));
		return pages;
	}
	
	@Override
	public Map<String,Object> getMaxTestVersion(Map<String,Object> paramMap) {
		return sceneVersionMapper.getMaxTestVersion(paramMap);
	}

	public SceneVersion querySceneVersionInfoByCodeAndVersion(String code,String version){
        Map<String,Object> paramter = new HashMap<String,Object>();
        paramter.put("scene_identify",code);
        paramter.put("version",version);
        List<SceneVersion> sceneVersionList = sceneVersionMapper.selectByMap(paramter);
        if(sceneVersionList != null && sceneVersionList.size() > 0){
            return sceneVersionList.get(0);
        }
        return null;
    }
    
}
