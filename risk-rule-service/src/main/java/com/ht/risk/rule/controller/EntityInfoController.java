package com.ht.risk.rule.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ht.risk.common.controller.BaseController;
import com.ht.risk.common.result.PageResult;
import com.ht.risk.common.result.Result;
import com.ht.risk.rule.entity.EntityInfo;
import com.ht.risk.rule.entity.EntityItemInfo;
import com.ht.risk.rule.service.ConstantInfoService;
import com.ht.risk.rule.service.EntityInfoService;
import com.ht.risk.rule.service.EntityItemInfoService;
import com.ht.risk.rule.util.anno.OperationDelete;
import com.ht.risk.rule.vo.EntitySelectVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
public class EntityInfoController extends BaseController {

    @Autowired
    private EntityInfoService entityInfoService;

    @Autowired
    private EntityItemInfoService itemInfoService;
    @Autowired
    private ConstantInfoService constantInfoService;
    @GetMapping("/getAll")
    @ApiOperation(value = "查询所有的对象")
    public Result<List<EntityInfo>> getAll(){
        List<EntityInfo> list = entityInfoService.selectList(null);
       // Page<EntityInfo> page = new Page<>();
       // page = entityInfoService.selectPage(page);

        return Result.success(list);
    }

    @GetMapping("/getEntitysOld")
    @ApiOperation(value = "查询所有的对象和变量的集合")
    public Result<List<EntitySelectVo>> getEntitysOld(String entityIds){
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
    @GetMapping("/getEntitysByScene")
    @ApiOperation(value = "查询所有的对象和变量的集合")
    public Result<List<EntitySelectVo>> getEntitysByScene(Long sceneId){

        List<EntityInfo> list = entityInfoService.findRuleEntityBySceneId(sceneId);

        List<EntitySelectVo> vos = new ArrayList<>();
        for (EntityInfo info : list){
            EntitySelectVo vo = new EntitySelectVo();
            vo.setValue(info.getEntityId().toString());
            vo.setText(info.getEntityName());
            vo.setKey(info.getEntityIdentify());
            //设置子集
            List<EntitySelectVo> sons = new ArrayList<>();
            List<EntityItemInfo> itemInfos = info.getItems();

            for(EntityItemInfo item : itemInfos){
                EntitySelectVo itemvo = new EntitySelectVo();

                itemvo.setValue(item.getItemId().toString());
                itemvo.setText(item.getItemName());
                itemvo.setKey(item.getItemIdentify());
                //设置常量子集
                itemvo  = constantInfoService.setItemConstants(itemvo,item);
                sons.add(itemvo);
            }
            vo.setSons(sons);
            //info.setItems(itemInfos);
            vos.add(vo);
        }
        return Result.success(vos);
    }
    @GetMapping("/getEntitysAll")
    @ApiOperation(value = "查询所有的对象和变量的集合")
    public Result<List<EntitySelectVo>> getEntitysAll(Long businessId) {

        List<EntityInfo> list = entityInfoService.findRuleEntityAll(businessId);

        List<EntitySelectVo> vos = new ArrayList<>();
        for (EntityInfo info : list) {
            EntitySelectVo vo = new EntitySelectVo();
            vo.setValue(info.getEntityId().toString());
            vo.setText(info.getEntityName());
            vo.setKey(info.getEntityIdentify());
            //设置子集
            List<EntitySelectVo> sons = new ArrayList<>();
            List<EntityItemInfo> itemInfos = info.getItems();

            for (EntityItemInfo item : itemInfos) {
                EntitySelectVo itemvo = new EntitySelectVo();

                itemvo.setValue(item.getItemId().toString());
                itemvo.setText(item.getItemName());
                itemvo.setKey(item.getItemIdentify());
                //设置常量子集
                itemvo  = constantInfoService.setItemConstants(itemvo,item);
                sons.add(itemvo);
            }
            vo.setSons(sons);
            //info.setItems(itemInfos);
            vos.add(vo);
        }
        return Result.success(vos);
    }

    @GetMapping("/getEntitysByIds")
    @ApiOperation(value = "查询所有的对象和变量的集合")
    public Result<List<EntitySelectVo>> getEntitysByIds(String ids){
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

    @GetMapping("/page")
    @ApiOperation(value = "分页查询")
    public PageResult<List<EntityInfo>> page(String key ,Long businessId, Integer page , Integer limit){
        Wrapper<EntityInfo> wrapper = new EntityWrapper<>();
        if(StringUtils.isNotBlank(key)){
            wrapper.like("entity_name",key);
            wrapper.or().like("entity_desc",key);
            wrapper.or().like("entity_identify",key);
        }
        if(businessId != null ){
            wrapper.andNew().eq("business_id",businessId);
        }
         Page<EntityInfo> pages = new Page<>();
         pages.setCurrent(page);
         pages.setSize(limit);
        pages = entityInfoService.selectPage(pages,wrapper);
        return PageResult.success(pages.getRecords(),pages.getTotal());
    }
    @PostMapping("/edit")
    @ApiOperation(value = "新增")
    @Transactional()
    public Result<Integer> edit(EntityInfo entityInfo){
        entityInfo.setCreTime(new Date());
        entityInfo.setIsEffect(1);
        entityInfo.setCreUserId(this.getUserId());
        entityInfoService.insertOrUpdate(entityInfo);
        return Result.success(0);
    }

    @GetMapping("/getInfoById")
    @ApiOperation(value = "通过id查询详细信息")
    public Result<EntityInfo> getDateById(Long id){
        EntityInfo entityInfo = entityInfoService.selectById(id);
        return Result.success(entityInfo);
    }
    @PostMapping ("forbidden")
    @ApiOperation(value = "禁用")
    @Transactional(rollbackFor = RuntimeException.class)
    public Result<Integer> forbidden(Integer status ,Long id){

        if(id != null ){
            EntityInfo entityInfo =  entityInfoService.selectById(id);
            entityInfo.setIsEffect(status);
            entityInfoService.updateById(entityInfo);
        }else{
            Result.success(-1);
        }
        return Result.success(0);
    }
    @GetMapping("/delete")
    @ApiOperation(value = "通过id删除信息")
    @OperationDelete(tableColumn = {"rule_entity_item_info&entity_id","rule_scene_entity_rel&entity_id"},idVal = "#id")
    public Result<Integer> delete( Long id){
         entityInfoService.deleteById(id);
        return Result.success(0);
    }


}

