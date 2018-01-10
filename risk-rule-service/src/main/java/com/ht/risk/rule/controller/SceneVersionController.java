package com.ht.risk.rule.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ht.risk.common.result.PageResult;
import com.ht.risk.common.result.Result;
import com.ht.risk.rule.entity.SceneInfo;
import com.ht.risk.rule.entity.SceneInfoVersion;
import com.ht.risk.rule.entity.SceneVersion;
import com.ht.risk.rule.rpc.DroolsRuleRpc;
import com.ht.risk.rule.service.SceneInfoService;
import com.ht.risk.rule.service.SceneVersionService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 张鹏
 * @since 2018-01-03
 */
@RestController
@RequestMapping("/sceneVersion")
public class SceneVersionController {
    @Autowired
    private SceneVersionService sceneVersionService;

    @Autowired
    private SceneInfoService sceneInfoService;

    @Autowired
    private DroolsRuleRpc droolsRuleRpc;

    @GetMapping("page")
    @ApiOperation(value = "分页查询")
    public PageResult<List<SceneInfoVersion>> page(String key , Integer sceneType, Long sceneId,Integer page , Integer limit){
        Wrapper<SceneInfoVersion> wrapper = new EntityWrapper<>();
        if(StringUtils.isNotBlank(key)){
            wrapper.like("scene_name",key);
            wrapper.or().like("scene_desc",key);
            wrapper.or().like("scene_identify",key);
        }
        if(sceneType != null ){
           // wrapper.eq("scene_type",sceneType);
        }
        if(sceneId != null ){
            wrapper.eq("v.scene_id",sceneId);
        }
        wrapper.orderBy("v.scene_id");
        wrapper.orderBy("v.version");
        Page<SceneInfoVersion> pages = new Page<>();
        pages.setCurrent(page);
        pages.setSize(limit);
        pages = sceneVersionService.selectVersionPage(pages,wrapper);
        return PageResult.success(pages.getRecords(),pages.getTotal());
    }
    @PostMapping ("forbidden")
    @ApiOperation(value = "禁用")
    @Transactional(rollbackFor = RuntimeException.class)
    public Result<Integer> forbidden(Integer status ,Long versionId){

        if(versionId != null ){
           SceneVersion version =  sceneVersionService.selectById(versionId);
            version.setStatus(status);
            sceneVersionService.updateById(version);
        }else{
            Result.success(-1);
        }
        return Result.success(0);
    }

    @PostMapping ("push")
    @ApiOperation(value = "版本发布")
    @Transactional(rollbackFor = RuntimeException.class)
    public Result<Integer> push(SceneVersion version){

        SceneInfo sceneInfo = sceneInfoService.selectById(version.getSceneId());

        if(sceneInfo == null ){
            return Result.error(-1,"不存在");
        }
        String identify = sceneInfo.getSceneIdentify();
        //获取当前总共发布的数量
        Wrapper wrapperCount = new EntityWrapper();
        wrapperCount.eq("scene_id",sceneInfo.getSceneId());
        int  count = sceneVersionService.selectCount(wrapperCount);
        //获取最大版本号 以0.1 为单位递增
        double maxVersion = 1.0 + count*0.1;
        version.setVersion(maxVersion+"");
        //获取 规则字符串
        String ruleDrlStr = droolsRuleRpc.getDroolsVersion(sceneInfo.getSceneId()+"");
        sceneInfo.setSceneId(null);
        version.setCreTime(new Date());
        version.setRuleDrl(ruleDrlStr);
        //version.setCreUserId();
        sceneVersionService.insert(version);

        return Result.success(0);
    }
}

