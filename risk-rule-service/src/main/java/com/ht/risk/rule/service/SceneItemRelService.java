package com.ht.risk.rule.service;

import com.ht.risk.rule.entity.SceneItemRel;
import com.ht.risk.common.service.BaseService;
import com.ht.risk.rule.vo.RuleItemTable;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 张鹏
 * @since 2017-12-25
 */
public interface SceneItemRelService extends BaseService<SceneItemRel> {
    /**
     * 描述：通过场景id查询 列表的表头部相关信息
     *
     * @param * @param null
     * @return a
     * @autor 张鹏
     * @date 2017/12/25 11:23
     */
    List<RuleItemTable> findItemTables(Long scene_id);
}
