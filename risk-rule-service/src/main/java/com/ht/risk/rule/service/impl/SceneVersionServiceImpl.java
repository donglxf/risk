package com.ht.risk.rule.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.SqlHelper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ht.risk.common.service.impl.BaseServiceImpl;
import com.ht.risk.rule.entity.*;
import com.ht.risk.rule.mapper.*;
import com.ht.risk.rule.service.SceneVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private SceneInfoVersionMapper sceneInfoVersionMapper;

    @Autowired
    private SceneVersionMapper sceneVersionMapper;
    @Autowired
    private EntityItemInfoMapper itemInfoMapper;

    @Autowired
    private RuleHisVersionMapper ruleHisVersionMapper;
    @Autowired
    private VariableBindMapper variableBindMapper;
    @Autowired
    private InfoMapper infoMapper;

    @Override
    public Page<SceneInfoVersion> selectVersionPage(Page<SceneInfoVersion> pages, Wrapper<SceneInfoVersion> wrapper) {
        SqlHelper.fillWrapper(pages, wrapper);
        pages.setRecords(this.sceneInfoVersionMapper.selectPage(pages, wrapper));
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

    @Override
    public void addRuleDescAndVarids(SceneInfo sceneInfo, SceneVersion version) {
        //查询场景的所有规则集合
        Wrapper<Info> infoWrapper = new EntityWrapper<>();
        infoWrapper.eq("scene_id",sceneInfo.getSceneId());
        List<Info> rules = infoMapper.selectList(infoWrapper);
        rules.forEach(rule ->{
            //添加规则描述信息
            RuleHisVersion ruleHisVersion = new RuleHisVersion();
            ruleHisVersion.setSenceVersionId(version.getVersionId());
            ruleHisVersion.setRuleName(rule.getRuleName());
            ruleHisVersion.setRuleDesc(rule.getRuleDesc());
            ruleHisVersion.setCreateTime(new Date());
            ruleHisVersion.setIsEffect("1");
            ruleHisVersionMapper.insert(ruleHisVersion);
            //添加规则变量，常量信息
            //查询所有使用过的变量，和对象
            List<EntityItemInfo> itemInfos = itemInfoMapper.selectItemBySceneId(sceneInfo.getSceneId());
            //操作新增操作
            itemInfos.forEach(itemInfo -> {
                VariableBind bind = new VariableBind();
                bind.setIsEffect("1");
                bind.setCreateTime(new Date());
                bind.setSenceVersionId(version.getVersionId());
                bind.setVariableCode(itemInfo.getEntityInfo().getEntityIdentify()+"_"+itemInfo.getItemIdentify());
                bind.setVariableName(itemInfo.getEntityInfo().getEntityName()+"_"+itemInfo.getItemName());
                if(itemInfo.getConstantId() != null ){
                    bind.setConstantId(itemInfo.getConstantId());
                }
                variableBindMapper.insert(bind);
            });
        });
    }

}
