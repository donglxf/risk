package com.ht.risk.rule.service.impl;

import com.baomidou.mybatisplus.mapper.SqlHelper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ht.risk.common.service.impl.BaseServiceImpl;
import com.ht.risk.rule.entity.SceneInfoVersion;
import com.ht.risk.rule.entity.SceneVersion;
import com.ht.risk.rule.mapper.SceneInfoVersionMapper;
import com.ht.risk.rule.mapper.SceneVersionMapper;
import com.ht.risk.rule.service.SceneVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public Page<SceneInfoVersion> selectVersionPage(Page<SceneInfoVersion> pages, Wrapper<SceneInfoVersion> wrapper) {
        SqlHelper.fillWrapper(pages, wrapper);
        pages.setRecords(this.versionMapper.selectPage(pages, wrapper));
        return pages;
    }
}
