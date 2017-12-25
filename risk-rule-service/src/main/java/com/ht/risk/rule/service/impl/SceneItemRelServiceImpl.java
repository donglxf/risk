package com.ht.risk.rule.service.impl;

import com.ht.risk.rule.entity.SceneItemRel;
import com.ht.risk.rule.mapper.SceneEntityRelMapper;
import com.ht.risk.rule.mapper.SceneItemRelMapper;
import com.ht.risk.rule.service.SceneItemRelService;
import com.ht.risk.common.service.impl.BaseServiceImpl;
import com.ht.risk.rule.vo.RuleItemTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 张鹏
 * @since 2017-12-25
 */
@Service
public class SceneItemRelServiceImpl extends BaseServiceImpl<SceneItemRelMapper, SceneItemRel> implements SceneItemRelService {

    @Autowired
    private SceneItemRelMapper sceneItemRelMapper;


    @Override
    public List<RuleItemTable> findItemTables(Long sceneId) {

        return  this.sceneItemRelMapper.findItemTables(sceneId);
    }
}
