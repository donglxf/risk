package com.ht.risk.rule.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ht.risk.common.result.PageResult;
import com.ht.risk.common.result.Result;
import com.ht.risk.rule.entity.EntityInfo;
import com.ht.risk.rule.entity.EntityItemInfo;
import com.ht.risk.rule.entity.Variable;
import com.ht.risk.rule.service.EntityInfoService;
import com.ht.risk.rule.service.EntityItemInfoService;
import com.ht.risk.rule.service.VariableService;
import com.ht.risk.rule.vo.EntitySelectVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 规则引擎实体信息表 前端控制器
 * </p>
 *
 * @author 张鹏
 * @since 2017-12-15
 */
@RestController
@RequestMapping("/entityInfo")
@Api(tags = "EntityInfoController", description = "变量对象相关api描述", hidden = true)
public class EntityInfoController {

    @Autowired
    private EntityInfoService entityInfoService;

    @Autowired
    private EntityItemInfoService itemInfoService;
    @GetMapping("getAll")
    @ApiOperation(value = "查询所有的对象")
    public Result<List<EntityInfo>> getAll(){
        List<EntityInfo> list = entityInfoService.selectList(null);
       // Page<EntityInfo> page = new Page<>();
       // page = entityInfoService.selectPage(page);

        return Result.success(list);
    }

    @GetMapping("getEntitys")
    @ApiOperation(value = "查询所有的对象和变量的集合")
    public Result<List<EntitySelectVo>> getEntitys(String entityIds){
        List<EntityInfo> list = entityInfoService.selectList(null);

        List<EntitySelectVo> vos = new ArrayList<>();
        for (EntityInfo info : list){
            EntitySelectVo vo = new EntitySelectVo();
            vo.setValue(info.getEntityId().toString());
            vo.setText(info.getEntityName());
            //设置子集
            List<EntitySelectVo> sons = new ArrayList<>();
            Wrapper<EntityItemInfo> wrapper = new EntityWrapper<>();
            wrapper.eq("entity_id",info.getEntityId());
            List<EntityItemInfo> itemInfos = itemInfoService.selectList(wrapper);
            for(EntityItemInfo item : itemInfos){
                EntitySelectVo itemvo = new EntitySelectVo();
                itemvo.setValue(item.getItemId().toString());
                itemvo.setText(item.getItemName());
                sons.add(itemvo);
            }
            vo.setSons(sons);
            //info.setItems(itemInfos);
            vos.add(vo);
        }
        return Result.success(vos);
    }

    @GetMapping("page")
    @ApiOperation(value = "分页查询")
    public PageResult<List<EntityInfo>> page(String key , Integer page , Integer limit){
        Wrapper<EntityInfo> wrapper = new EntityWrapper<>();
        if(StringUtils.isNotBlank(key)){
            wrapper.like("entity_name",key);
            wrapper.or().like("entity_desc",key);
            wrapper.or().like("entity_identify",key);
        }
         Page<EntityInfo> pages = new Page<>();
         pages.setCurrent(page);
         pages.setSize(limit);
        pages = entityInfoService.selectPage(pages,wrapper);
        return PageResult.success(pages.getRecords(),pages.getTotal());
    }
    @PostMapping("edit")
    @ApiOperation(value = "新增")
    @Transactional()
    public Result<Integer> edit(EntityInfo entityInfo){
        entityInfo.setCreTime(new Date());
        entityInfo.setIsEffect(1);
        entityInfoService.insertOrUpdate(entityInfo);
        return Result.success(0);
    }

    @GetMapping("getInfoById/{id}")
    @ApiOperation(value = "通过id查询详细信息")
    public Result<EntityInfo> getDateById(@PathVariable(name = "id") Long id){
        EntityInfo entityInfo = entityInfoService.selectById(id);
        return Result.success(entityInfo);
    }

    @GetMapping("delete/{id}")
    @ApiOperation(value = "通过id删除信息")
    public Result<Integer> delete(@PathVariable(name = "id") Long id){
         entityInfoService.deleteById(id);
        return Result.success(0);
    }


}

