package com.ht.risk.rule.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.ht.risk.common.result.PageResult;
import com.ht.risk.common.result.Result;
import com.ht.risk.rule.entity.EntityItemInfo;
import com.ht.risk.rule.service.EntityItemInfoService;
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
 * 实体属性信息 前端控制器
 * </p>
 *
 * @author 张鹏
 * @since 2017-12-15
 */
@RestController
@RequestMapping("/entityItemInfo")
@Api(tags = "EntityInfoController", description = "变量相关api描述")
public class EntityItemInfoController {


    @Autowired
    private EntityItemInfoService itemInfoService;
    @GetMapping("/getAll")
    @ApiOperation(value = "查询所有的对象字段")
    public PageResult<List<EntityItemInfo>> getAll(Long entityId,String key){

        Wrapper<EntityItemInfo> wrapper = new EntityWrapper<>();
        if(StringUtils.isNotBlank(key)){
            wrapper.like("item_name",key);
            wrapper.or().like("item_desc",key);
            wrapper.or().like("item_identify",key);
        }

        wrapper.eq("entity_id",entityId);
        List<EntityItemInfo> list = itemInfoService.selectList(wrapper);
        return PageResult.success(list,10);
    }

    @GetMapping("/getInfoById")
    @ApiOperation(value = "通过id查询详细信息")
    public Result<EntityItemInfo> getDateById(Long id){
        EntityItemInfo entityInfo = itemInfoService.selectById(id);
        return Result.success(entityInfo);
    }

    @PostMapping("/edit")
    @ApiOperation(value = "新增")
    @Transactional(rollbackFor = RuntimeException.class)
    public Result<Integer> edit(EntityItemInfo itemInfo){
        itemInfo.setCreTime(new Date());
        itemInfo.setIsEffect(1);
        itemInfo.setCreUserId(1L);
        itemInfoService.insertOrUpdate(itemInfo);
        return Result.success(1);
    }


    @GetMapping("/delete")
    @ApiOperation(value = "通过id删除信息")
    public Result<Integer> delete(Long id){
        itemInfoService.deleteById(id);
        return Result.success(0);
    }
}

