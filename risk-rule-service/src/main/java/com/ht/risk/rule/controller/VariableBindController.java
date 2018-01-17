package com.ht.risk.rule.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ht.risk.common.result.PageResult;
import com.ht.risk.common.result.Result;
import com.ht.risk.common.util.ObjectUtils;
import com.ht.risk.rule.entity.EntityItemInfo;
import com.ht.risk.rule.entity.SceneVersion;
import com.ht.risk.rule.entity.TempDataContains;
import com.ht.risk.rule.entity.VariableBind;
import com.ht.risk.rule.model.DroolsParamter;
import com.ht.risk.rule.rpc.DroolsRuleRpc;
import com.ht.risk.rule.service.EntityItemInfoService;
import com.ht.risk.rule.service.SceneVersionService;
import com.ht.risk.rule.service.TempDataContainsService;
import com.ht.risk.rule.service.VariableBindService;
import com.ht.risk.rule.vo.VariableBindVo;

import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author dyb
 * @since 2018-01-10
 */
@RestController
@RequestMapping("/variableBind/")
public class VariableBindController {

    @Autowired
    private VariableBindService variableBindService;

    @Autowired
    private SceneVersionService sceneVersionService;

    @Autowired
    private DroolsRuleRpc droolsRuleRpc;

    @Autowired
    private EntityItemInfoService entityItemInfoService;

    @Autowired
    private TempDataContainsService tempDataContainsService;


    @GetMapping("page")
    @ApiOperation(value = "分页查询")
    public PageResult<List<SceneVersion>> rulePage(String key, Integer page, Integer limit) {
        Wrapper<SceneVersion> wrapper = new EntityWrapper<>();
        if (StringUtils.isNotBlank(key)) {
            wrapper.like("title", key);
            wrapper.or().like("comment", key);
            wrapper.or().like("version", key);
        }

        Page<SceneVersion> pages = new Page<>();
        pages.setCurrent(page);
        pages.setSize(limit);
        pages = sceneVersionService.getNoBindVariableRecord(pages, wrapper);
        return PageResult.success(pages.getRecords(), pages.getTotal());
    }

    @GetMapping("getAll")
    @ApiOperation(value = "查询所有的对象字段")
    public PageResult<List<EntityItemInfo>> getAll(@RequestParam(name = "sceneId") String sceneId, String versionId) {
        System.out.println(sceneId + ">>>>>>>>" + versionId);
        List<EntityItemInfo> list = entityItemInfoService.findEntityItemBySceneId(sceneId);
        return PageResult.success(list, 10);
    }

    @PostMapping("edit")
    @ApiOperation(value = "新增")
    @Transactional()
    public Result<Integer> edit(VariableBind entityInfo) {
        entityInfo.setCreateTime(new Date());
        entityInfo.setIsEffect("1");
        variableBindService.insertOrUpdate(entityInfo);
        return Result.success(0);
    }

    @PostMapping("manualVariable")
    @ApiOperation(value = "规则手动验证")
    public PageResult<Integer> manualVariable(VariableBindVo entityInfo, HttpServletRequest request) {
        Map<String, Object> data = new HashMap<String, Object>();
        DroolsParamter drools = new DroolsParamter();
        Map<String, String[]> map = request.getParameterMap();
        List<EntityItemInfo> list = entityItemInfoService.findEntityItemBySceneId(entityInfo.getSenceId());
        for (EntityItemInfo entityItemInfo : list) {
            String str = map.get(entityItemInfo.getItemIdentify())[0];
            data.put(entityItemInfo.getItemIdentify(), str);
        }
        drools.setVersion(entityInfo.getSenceVersionid());
        drools.setSence(entityInfo.getSceneIdentify());
        drools.setData(data);
        String res = droolsRuleRpc.excuteDroolsSceneValidation(drools);
        JSONObject obj = JSON.parseObject(res);
        if (obj.getInteger("code") == 0) {
            return PageResult.success(0);
        }else{
            return PageResult.error(1,"验证失败");
        }

    }

    @PostMapping("autoVariable")
    @ApiOperation(value = "规则自动验证")
    public PageResult<Map<String, Object>> autoVariable(VariableBindVo entityInfo, HttpServletRequest request) {
        Map<String, Object> data = new HashMap<String, Object>();
        DroolsParamter drools = new DroolsParamter();
        int success = 0, fail = 0;
        // 查询变量绑定字段信息
        Map<String, Object> columnMap = new HashMap<String, Object>();
        columnMap.put("SENCE_VERSIONID", entityInfo.getSenceVersionid());
        columnMap.put("IS_EFFECT", "1");
        List<VariableBind> bindList = variableBindService.selectByMap(columnMap);

        //根据页面输入验证次数查找第三方表记录数
        Wrapper<TempDataContains> wrapper = new EntityWrapper<>();
        wrapper.eq("scene_id", entityInfo.getSenceId());
        Page<TempDataContains> pages = new Page<>();
        pages.setCurrent(0);
        pages.setSize(entityInfo.getExcuteTotal());
        Page<Map<String, Object>> pageMap = tempDataContainsService.selectMapsPage(pages, wrapper);
        List<Map<String, Object>> recordMap = pageMap.getRecords();
        for (Map<String, Object> map2 : recordMap) {
            data.clear();
            for (VariableBind vBind : bindList) {
                data.put(vBind.getVariableCode(), map2.get(vBind.getVariableCode()));
            }
            drools.setVersion(entityInfo.getSenceVersionid());
            drools.setSence(entityInfo.getSceneIdentify());
            drools.setData(data);
            String res = droolsRuleRpc.excuteDroolsSceneValidation(drools);
            JSONObject obj = JSON.parseObject(res);
            if (obj.getInteger("code") == 0) {
                success += 1;
            } else {
                fail += 1;
            }
//			System.out.println(res);
        }


//		String tabName=null;
//		for (VariableBind vBind : bindList) {
//			tabName=vBind.getBindTable();
//			break ;
//		}
//		tempDataContainsService.

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("success", success);
        resultMap.put("fail", fail);
        return PageResult.success(resultMap, 0);
    }

    @PostMapping("development")
    @ApiOperation(value = "发布正式版")
    @Transactional()
    public Result<Integer> development(VariableBindVo bindInfo) {
        SceneVersion scene = sceneVersionService.selectById(bindInfo.getSenceVersionid()); // 当前要发布的测试版本记录
        // 最大正式版本号
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("sceneId", bindInfo.getSenceId());
        map.put("sceneIdentify", bindInfo.getSceneIdentify());
        map.put("type", 1);
        Map<String, Object> maxVersionMap = sceneVersionService.getMaxTestVersion(map);
        float maxVersion = 1.0f;
        if (ObjectUtils.isNotEmpty(maxVersionMap)) {
            maxVersion = Float.parseFloat(((String) maxVersionMap.get("maxVersion"))) + 0.1f;
        }

        SceneVersion entityInfo = new SceneVersion();
        entityInfo.setStatus(1);
        entityInfo.setVersion(String.valueOf(maxVersion));
        entityInfo.setType(1);
        entityInfo.setTitle(scene.getTitle());
        entityInfo.setComment(scene.getComment());
        entityInfo.setSceneIdentify(scene.getSceneIdentify());
        entityInfo.setSceneId(scene.getSceneId());
        entityInfo.setRuleDiv(scene.getRuleDiv());
        entityInfo.setRuleDrl(scene.getRuleDrl());
        sceneVersionService.insertOrUpdate(entityInfo);
        return Result.success(0);
    }


}
