package com.ht.risk.rule.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.ht.risk.common.result.Result;
import com.ht.risk.rule.entity.*;
import com.ht.risk.rule.service.*;
import com.ht.risk.rule.util.RuleUtils;
import com.ht.risk.rule.util.StringUtil;
import com.ht.risk.rule.vo.RuleFormVo;
import com.ht.risk.rule.vo.RuleItemTable;
import com.ht.risk.rule.vo.RuleSubmitVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 规则信息 前端控制器
 * </p>
 *
 * @author 张鹏
 * @since 2017-12-15
 */
@RestController
@RequestMapping("/rule")
@Api(tags = "RuleInfoController", description = "规则相关api描述", hidden = true)
public class RuleInfoController {

    @Autowired
    private InfoService infoService;

    @Autowired
    private SceneInfoService sceneInfoService;


    @Autowired
    private ActionParamValueInfoService actionParamValueInfoService;

    @Autowired
    private ConditionInfoService conditionInfoService;

    @Autowired
    private SceneItemRelService sceneItemRelService;

    @Autowired
    private SceneEntityRelService sceneEntityRelService;


    @Autowired
    private ActionInfoService actionInfoService;

    @Autowired
    private ActionRuleRelService actionRuleRelService;


    @GetMapping("getAll")
    @ApiOperation(value = "查询所有的规则")
    public Result< Map<String ,Object >> getAll(Long sceneId) throws  Exception{
        Map<String ,Object > result = new HashMap<>();
        //动作次数
        int actionCounts = 0;
        SceneInfo sceneInfo = sceneInfoService.selectById(sceneId);
        //获取该场景的规则
        Wrapper<Info> wrapper = new EntityWrapper<>();
        wrapper.eq("scene_id",sceneId);
        List<Info> list = infoService.selectList(wrapper);
        if(list == null || list.size() < 1){
            return Result.error(-1,"暂无数据");
        }
        //获取实体类集合
      //  List<EntityInfo> entityInfos = sceneEntityRelService.findBaseRuleEntityListByScene(sceneInfo);
        //获取变量集合
        List<RuleItemTable> itemTables =  sceneItemRelService.findItemTables(sceneId);

        //规则实际赋值
        for (Info rule : list){
        //条件
        List<ConditionInfo> conts = conditionInfoService.findRuleConditionInfoByRuleId(rule.getRuleId()) ;
        //转化
            for (ConditionInfo conditionInfo : conts){
                //设置运算符
                conditionInfo.setYsf(RuleUtils.getCondition(conditionInfo.getConditionExpression(),conditionInfo.getVal()));
                //设置中文名 运算符
                conditionInfo.setYsfText(RuleUtils.getCondition(conditionInfo.getConditionDesc(),conditionInfo.getVal()));
            }
            // 动作集合
         //   List<ActionInfo> actionInfos = actionInfoService.findRuleActionListByRule(rule.getRuleId());
            //获取 动作中间表和相关的动作信息及参数信息，及值
            List<ActionRuleRel> rels = actionRuleRelService.findActionVals(rule.getRuleId());
            actionCounts = rels.size();
            rule.setCons(conts);
            rule.setActionRels(rels);
        }
       // result.put("entityInfos",entityInfos);
        result.put("itemTables",itemTables);
        result.put("rules",list);
        //动作个数
        result.put("actionCounts",actionCounts);
        return Result.success(result);
    }
    @PostMapping("save")
    @ApiOperation(value = "规则保存")
    @Transactional(rollbackFor = RuntimeException.class)
    public Result<Integer> save(@RequestBody RuleFormVo ruleFormVo){
        System.out.print(ruleFormVo);
        Long sceneId = ruleFormVo.getSceneId() ;
        List<String> entityS = ruleFormVo.getEntityIds();
        List<String > items = ruleFormVo.getItemVals();
        //去重复
        entityS = StringUtil.removeRe(entityS);
        long creUid = 111;
        //先清除 规则 ，及相关联的值
        infoService.clearBySceneId(sceneId);
        //添加场景 实体中间表
        for (String entityId : entityS){
            SceneEntityRel sceneEntityRel = new SceneEntityRel();
            sceneEntityRel.setEntityId(Long.parseLong(entityId));
            sceneEntityRel.setSceneId(sceneId);
            sceneEntityRelService.insert(sceneEntityRel);
        }
        //添加场景 变量中间表
        int i = 1;
        for (String itme : items){
            SceneItemRel rel = new SceneItemRel() ;
            rel.setSceneId(sceneId);
            rel.setEntityItemId(Long.parseLong(itme));
            rel.setSort(i);
            sceneItemRelService.insert(rel);
            i++;
        }
        //添加规则
        int j = 1;
        for (RuleSubmitVo vo : ruleFormVo.getVos() ){
            //添加规则
            Info rule = infoService.addRule(sceneId,j);
            Long ruleId = rule.getRuleId();
            /**
             * 添加条件
             */
            for(ConditionInfo conditionInfo : vo.getConditionInfos()){
                conditionInfoService.add(conditionInfo,ruleId);
            }
            /**
             * 添加动作集合了
             */
            for(ActionParamValueInfo actionValue : vo.getActionInfos()){
                actionParamValueInfoService.add(actionValue,ruleId);
            }
            j++;
        }
        return Result.success(0);
    }

    @GetMapping("delete")
    @ApiOperation(value = "规则全部删除")
    @Transactional(rollbackFor = RuntimeException.class)
    public Result<Integer> delete(Long sceneId){
        infoService.clearBySceneId(sceneId);
        return Result.success(1);
    }

}

