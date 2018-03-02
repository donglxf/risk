package com.ht.risk.rule.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.ht.risk.common.controller.BaseController;
import com.ht.risk.common.result.Result;
import com.ht.risk.common.util.DateUtils;
import com.ht.risk.rule.entity.*;
import com.ht.risk.rule.facade.SceneRuleFacade;
import com.ht.risk.rule.service.*;
import com.ht.risk.rule.util.RuleUtils;
import com.ht.risk.rule.util.StringUtil;
import com.ht.risk.rule.vo.RuleFormVo;
import com.ht.risk.rule.vo.RuleGradeFormVo;
import com.ht.risk.rule.vo.RuleItemTable;
import com.ht.risk.rule.vo.RuleSubmitVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
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
public class RuleInfoController extends BaseController {

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
    private EntityItemInfoService entityItemInfoService;

    @Autowired
    private ActionRuleRelService actionRuleRelService;

    @Autowired
    private RuleGroupService ruleGroupService;

    @Autowired
    private SceneRuleFacade sceneRuleFacade;

    @GetMapping("getAll")
    @ApiOperation(value = "查询所有的规则")
    @Cacheable(value = "risk-rule",key = "'getRules:'+#sceneId")
    public Result< Map<String ,Object >> getAll(Long sceneId) throws  Exception{
        Map<String ,Object > result = new HashMap<>();
      //  SceneInfo sceneInfo = sceneInfoService.selectById(sceneId);
        //获取该场景的规则
        Wrapper<Info> wrapper = new EntityWrapper<>();
        wrapper.eq("scene_id",sceneId);
        List<Info> list = infoService.selectList(wrapper);
        if(list == null || list.size() < 1){
            return Result.error(-1,"暂无数据");
        }
        //规则实际赋值
        for (Info rule : list){
        //条件
        List<ConditionInfo> conts = conditionInfoService.findRuleConditionInfoByRuleId(rule.getRuleId()) ;
        //转化
            for (ConditionInfo conditionInfo : conts){
                String itemIds = RuleUtils.getConditionParamBetweenChar(conditionInfo.getConditionExpression()).get(0);
                //设置运算符
               // conditionInfo.setYsf(RuleUtils.getCondition(conditionInfo.getConditionExpression(),conditionInfo.getVal()));
                //中文描述
                conditionInfo.setValText(conditionInfo.getVal());
                String val = RuleUtils.getConditionOfVariable(conditionInfo.getConditionExpression());


                //设置运算符
                conditionInfo.setYsf(RuleUtils.getCondition(conditionInfo.getConditionExpression(),val));
                //转换去#符合
                if(val.indexOf("#") >= 0){
                    conditionInfo.setClazz("conditionEntity");
                    val = val.replaceAll("#","");
                }
                conditionInfo.setVal(val);

                //设置中文名 运算符
                conditionInfo.setYsfText(RuleUtils.getCondition(conditionInfo.getConditionDesc(),conditionInfo.getValText()));
                //获取变量
                RuleItemTable itemTable = entityItemInfoService.findRuleItemTableById(Long.parseLong(itemIds));
                conditionInfo.setItemTable(itemTable);
            }
            // 动作集合
         //   List<ActionInfo> actionInfos = actionInfoService.findRuleActionListByRule(rule.getRuleId());
            //获取 动作中间表和相关的动作信息及参数信息，及值
            List<ActionRuleRel> rels = actionRuleRelService.findActionVals(rule.getRuleId());
            rule.setCons(conts);
            rule.setActionRels(rels);
        }
        result.put("rules",list);
        //动作个数
        return Result.success(result);
    }

    @GetMapping("getGradeCardAll")
    @ApiOperation(value = "查询所有的规则")
    public Result< Map<String ,Object >> getGradeCardAll(Long sceneId) throws  Exception{
        Map<String ,Object > result = new HashMap<>();
        result = sceneRuleFacade.getScene4Grade(sceneId);
        if(result == null ){
            return Result.error(-1,"暂无数据");
        }else{
            return Result.success(result);
        }

    }
    @PostMapping("save")
    @ApiOperation(value = "规则保存")
    @Transactional(rollbackFor = RuntimeException.class)
    @CacheEvict(value = "risk-rule",key = "'getRules:'+#ruleFormVo.sceneId")
    public Result<Integer> save(@RequestBody @Validated RuleFormVo ruleFormVo){
        Long sceneId = ruleFormVo.getSceneId() ;
        List<String> entityS = ruleFormVo.getEntityIds();
        List<String > items = ruleFormVo.getItemVals();
        //去重复
        entityS = StringUtil.removeRe(entityS);
        items = StringUtil.removeRe(items);

        long creUid = 111;
        //先清除 规则 ，及相关联的值
        infoService.clearBySceneId(sceneId);
        SceneInfo sceneInfo = sceneInfoService.selectById(sceneId);
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
            rel.setCreTime(DateUtils.getDate());
            sceneItemRelService.insert(rel);
            i++;
        }
        //添加规则
        int j = 1;
        for (RuleSubmitVo vo : ruleFormVo.getVos() ){
            String ruleRemark = "";
            //添加规则
            Info rule = infoService.addRule(sceneInfo,j);
            Long ruleId = rule.getRuleId();
            /**
             * 添加条件
             */
            for(ConditionInfo conditionInfo : vo.getConditionInfos()){
                conditionInfo =  conditionInfoService.add(conditionInfo,ruleId);
                if(StringUtils.isBlank(ruleRemark)){
                    ruleRemark += "如果"+conditionInfo.getConditionDesc();
                }else{
                    ruleRemark += "，并且"+conditionInfo.getConditionDesc();
                }
            }
            String actionRemark = "";
            /**
             * 添加动作集合了
             */
            for(ActionParamValueInfo actionValue : vo.getActionInfos()){
                actionParamValueInfoService.add(actionValue,ruleId);
                if(StringUtils.isBlank(actionRemark)){
                    actionRemark = "那么=>"+actionValue.getRemark();
                }else{
                    String paramVal = ","+actionValue.getRemark().substring(actionValue.getRemark().indexOf(":")+1);
                    actionRemark += paramVal;
                }
            }
            //结果
            ruleRemark += actionRemark;
            /**
             * 规则描述文件
             */
            rule.setRuleDesc(ruleRemark);
            rule.setRemark(ruleRemark);
            infoService.updateById(rule);
            j++;
        }
        return Result.success(0);
    }

    @PostMapping("saveGrade")
    @ApiOperation(value = "规则保存")
    @Transactional(rollbackFor = RuntimeException.class)
    @CacheEvict(value = "risk-rule",key = "'getRules:'+#ruleFormVo.sceneId")
    public Result<Integer> saveGrade(@RequestBody @Validated RuleGradeFormVo ruleFormVo){
        Long sceneId = ruleFormVo.getSceneId() ;
        List<String> entityS = ruleFormVo.getEntityIds();
        List<String > items = ruleFormVo.getItemVals();
        //去重复
        entityS = StringUtil.removeRe(entityS);
        items = StringUtil.removeRe(items);

        long creUid = 111;
        //先清除 规则 ，及相关联的值
        infoService.clearBySceneId(sceneId);
        SceneInfo sceneInfo = sceneInfoService.selectById(sceneId);
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
            rel.setCreTime(DateUtils.getDate());
            sceneItemRelService.insert(rel);
            i++;
        }
        //添加规则
        int j = 1;
        for (RuleSubmitVo vo : ruleFormVo.getVos() ){
            String ruleRemark = "";
            //添加规则
            Info rule = infoService.addRule(sceneInfo,j);
            Long ruleId = rule.getRuleId();
            /**
             * 添加分组
             */
            RuleGroup group = vo.getGroup();
            group.setRuleId(ruleId);
            group.setSceneId(sceneId);
            ruleGroupService.insert(group);
            /**
             * 添加条件
             */
            for(ConditionInfo conditionInfo : vo.getConditionInfos()){
                conditionInfo =  conditionInfoService.add(conditionInfo,ruleId);
                if(StringUtils.isBlank(ruleRemark)){
                    ruleRemark += "如果"+conditionInfo.getConditionDesc();
                }else{
                    ruleRemark += "，并且"+conditionInfo.getConditionDesc();
                }
            }
            String actionRemark = "";
            /**
             * 添加动作集合了
             */
            for(ActionParamValueInfo actionValue : vo.getActionInfos()){
                 actionParamValueInfoService.add(actionValue,ruleId);
                 if(StringUtils.isBlank(actionRemark)){
                     actionRemark = "那么=>"+actionValue.getRemark();
                 }else{
                    String paramVal = ","+actionValue.getRemark().substring(actionValue.getRemark().indexOf(":")+1);
                     actionRemark += paramVal;
                 }
            }
            //结果
            ruleRemark += actionRemark;
            /**
             * 规则描述文件
             */
            rule.setRuleDesc(ruleRemark);
            rule.setRemark(ruleRemark);
            infoService.updateById(rule);
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

