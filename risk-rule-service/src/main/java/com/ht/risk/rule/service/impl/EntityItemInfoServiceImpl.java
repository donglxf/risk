package com.ht.risk.rule.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.ht.risk.common.service.impl.BaseServiceImpl;
import com.ht.risk.common.util.ObjectUtils;
import com.ht.risk.rule.entity.EntityItemInfo;
import com.ht.risk.rule.mapper.EntityItemInfoMapper;
import com.ht.risk.rule.service.EntityItemInfoService;
import com.ht.risk.rule.vo.RuleItemTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 实体属性信息 服务实现类
 * </p>
 *
 * @author 张鹏
 * @since 2017-12-15
 */
@Service
public class EntityItemInfoServiceImpl extends BaseServiceImpl<EntityItemInfoMapper, EntityItemInfo> implements EntityItemInfoService {

    Logger logger = LoggerFactory.getLogger(EntityItemInfoServiceImpl.class);

    @Resource
    private EntityItemInfoMapper entityItemInfoMapper;

    /**
     * Date 2017/7/20
     * Author lihao [lihao@sinosoft.com]
     * <p>
     * 方法说明: 根据实体id获取规则引擎实体属性信息
     *
     * @param baseRuleEntityItemInfo 参数
     */
    @Override
    public List<EntityItemInfo> findBaseRuleEntityItemInfoList(EntityItemInfo baseRuleEntityItemInfo) throws Exception {
        return this.entityItemInfoMapper.findBaseRuleEntityItemInfoList(baseRuleEntityItemInfo);
    }

    /**
     * Date 2017/7/26
     * Author lihao [lihao@sinosoft.com]
     * <p>
     * 方法说明: 根据id获取对应的属性信息
     *
     * @param id 属性Id
     */
    @Override
    public EntityItemInfo findBaseRuleEntityItemInfoById(Long id) throws Exception {
        if (null == id) {
            throw new NullPointerException("参数缺失");
        }
        return this.entityItemInfoMapper.findBaseRuleEntityItemInfoById(id);
    }

    @Override
    public RuleItemTable findRuleItemTableById(long itemId) {
        return this.entityItemInfoMapper.findRuleItemTableById(itemId);
    }

	@Override
	public List<EntityItemInfo> findEntityItemBySceneId(String sceneId) {
		List<Map<String,Object>> map=entityItemInfoMapper.findEntityItemBySceneId(sceneId);
		if(ObjectUtils.isNotEmpty(map)){
			List<Long> str=assemblyCondition(map,"conditionExpression");
			Wrapper<EntityItemInfo> wrapper = new EntityWrapper<>();
	        wrapper.in("item_id", str);
	        return entityItemInfoMapper.selectList(wrapper);
		}
		return null;
	}
	
	public List<Long> assemblyCondition(List<Map<String,Object>> map,String key){
		List<Long> list=new ArrayList<Long>();
		for (Map<String, Object> map2 : map) {
			String conditionExpression=(String) map2.get(key);
			list.add(Long.parseLong(conditionExpression.substring(1,conditionExpression.lastIndexOf("$"))));
		}
		return list;
	}
    @Override
    public boolean checkKey(String key,String other) {
        Integer count = this.baseMapper.selectCount(new EntityWrapper<EntityItemInfo>()
                .eq("item_identify", key)
        .eq("entity_id",other));
        count = count == null?0:count;
        return count > 0 ? true:false;
    }

}
