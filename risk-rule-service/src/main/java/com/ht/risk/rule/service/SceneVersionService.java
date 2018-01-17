package com.ht.risk.rule.service;


import java.util.Map;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ht.risk.common.service.BaseService;
import com.ht.risk.rule.entity.SceneInfoVersion;
import com.ht.risk.rule.entity.SceneVersion;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 张鹏
 * @since 2018-01-03
 */
public interface SceneVersionService extends BaseService<SceneVersion> {

    Page<SceneInfoVersion> selectVersionPage(Page<SceneInfoVersion> pages, Wrapper<SceneInfoVersion> wrapper);
    
    Page<SceneVersion> getNoBindVariableRecord(Page<SceneVersion> pages , Wrapper<SceneVersion> wrapper);
    
    public Map<String,Object> getMaxTestVersion(Map<String,Object> paramMap);

    /**
     * 通过策略唯一标示和版本查询策略版本信息
     * @param code 策略唯一标示
     * @param version 策略版本
     * @return
     */
    public SceneVersion querySceneVersionInfoByCodeAndVersion(String code,String version);
}
