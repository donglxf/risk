package com.ht.risk.rule.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ht.risk.common.result.PageResult;
import com.ht.risk.common.result.Result;
import com.ht.risk.rule.entity.SceneInfo;
import com.ht.risk.rule.service.InfoService;
import com.ht.risk.rule.service.SceneInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 规则引擎使用场景 前端控制器
 * </p>
 *
 * @author 张鹏
 * @since 2017-12-15
 */
@RestController
@RequestMapping("/sceneInfo")
@Api(tags = "SceneInfoController", description = "场景相关api描述", hidden = true)
public class SceneInfoController {
    @Autowired
    private SceneInfoService sceneInfoService;

    @Autowired
    private InfoService infoService;


    @GetMapping("page")
    @ApiOperation(value = "分页查询")
    public PageResult<List<SceneInfo>> page(String key , Integer sceneType,Integer page , Integer limit){
        Wrapper<SceneInfo> wrapper = new EntityWrapper<>();
        if(StringUtils.isNotBlank(key)){
            wrapper.like("scene_name",key);
            wrapper.or().like("scene_desc",key);
            wrapper.or().like("scene_identify",key);
        }
        wrapper.eq("scene_type",sceneType);
        Page<SceneInfo> pages = new Page<>();
        pages.setCurrent(page);
        pages.setSize(limit);
        pages = sceneInfoService.selectPage(pages,wrapper);
        return PageResult.success(pages.getRecords(),pages.getTotal());
    }

    @PostMapping("edit")
    @ApiOperation(value = "新增")
    @Transactional()
    public Result<Integer> edit(SceneInfo sceneInfo){
        sceneInfo.setCreTime(new Date());
        sceneInfo.setIsEffect(1);
        long a = 11;
        sceneInfo.setCreUserId(a);
        sceneInfoService.insertOrUpdate(sceneInfo);
        return Result.success(0);
    }

    @GetMapping("getInfoById/{id}")
    @ApiOperation(value = "通过id查询详细信息")
    public Result<SceneInfo> getDateById(@PathVariable(name = "id") Long id){
        SceneInfo sceneInfo = sceneInfoService.selectById(id);
        return Result.success(sceneInfo);
    }

    @GetMapping("delete/{id}")
    @ApiOperation(value = "通过id删除信息")
    public Result<Integer> delete(@PathVariable(name = "id") Long id){
        sceneInfoService.deleteById(id);
        infoService.clearBySceneId(id);
        return Result.success(0);
    }

    @GetMapping("push/{id}")
    @ApiOperation(value = "版本发布")
    @Transactional(rollbackFor = RuntimeException.class)
    public Result<Integer> push(@PathVariable(name = "id") Long id){

        SceneInfo sceneInfo = sceneInfoService.selectById(id);

        if(sceneInfo == null ){
            return Result.error(-1,"不存在");
        }
        //获取当前版本号
        String version = sceneInfo.getVersion();
        Double vd = Double.parseDouble(version);


        //新增版本号
        return Result.success(0);
    }

}

