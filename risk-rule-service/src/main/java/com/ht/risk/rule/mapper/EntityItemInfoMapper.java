package com.ht.risk.rule.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ht.risk.common.mapper.SuperMapper;
import com.ht.risk.rule.entity.EntityItemInfo;
import com.ht.risk.rule.vo.RuleItemTable;

/**
 * <p>
 * 实体属性信息 Mapper 接口
 * </p>
 *
 * @author 张鹏
 * @since 2017-12-15
 */
public interface EntityItemInfoMapper extends SuperMapper<EntityItemInfo> {

    /**
     * Date 2017/7/20
     * Author lihao [lihao@sinosoft.com]
     *
     * 方法说明: 根据实体id获取规则引擎实体属性信息
     * @param baseRuleEntityItemInfo 参数
     */
    List<EntityItemInfo> findBaseRuleEntityItemInfoList(EntityItemInfo baseRuleEntityItemInfo);

    /**
     * Date 2017/7/26
     * Author lihao [lihao@sinosoft.com]
     * <p>
     * 方法说明: 根据id获取对应的属性信息
     *
     * @param id 属性Id
     */
    EntityItemInfo findBaseRuleEntityItemInfoById(@Param("id") Long id);

    /**
     * Date 2017/7/26
     * Author lihao [lihao@sinosoft.com]
     * <p>
     * 方法说明: 根据id获取对应的属性信息
     * @param itemId 属性Id
     */
    RuleItemTable findRuleItemTableById(@Param("itemId") Long itemId);

    /**
     * 根据sceneId查找对于变量
    * @Title: findEntityItemBySceneId
    * @Description: TODO(这里用一句话描述这个方法的作用)
    * @param @param sceneId
    * @param @return    设定文件
    * @return List<Map<String,Object>>    返回类型
    * @throws
     */
    List<Map<String,Object>> findEntityItemBySceneId (String sceneId);
}
