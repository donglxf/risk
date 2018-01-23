package com.ht.risk.rule.controller;

import java.util.*;
import java.util.concurrent.ForkJoinPool;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONArray;
import com.ht.risk.rule.entity.*;
import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ht.risk.common.result.PageResult;
import com.ht.risk.common.result.Result;
import com.ht.risk.common.util.ObjectUtils;
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

    @GetMapping("getInfoById/{id}")
    @ApiOperation(value = "通过id查询详细信息")
    public Result<SceneVersion> getDateById(@PathVariable(name = "id") Long id) {
        SceneVersion entityInfo = sceneVersionService.selectById(id);
        return Result.success(entityInfo);
    }

    @GetMapping("getVariableBind")
    @ApiOperation(value = "根据版本查询规则绑定变量")
    public PageResult<List<VariableBind>> getVariableBind(@RequestParam(name = "sceneId") String sceneId, String versionId) {
        Wrapper<VariableBind> wrapper = new EntityWrapper<>();
        wrapper.eq("sence_version_id",sceneId);
        List<VariableBind> list=variableBindService.selectList(wrapper);
        return PageResult.success(list, 0);
    }


    @GetMapping("getAll")
    @ApiOperation(value = "手动验证时，根据版本查询规则绑定变量")
    public ActProcRelease getAll(@RequestParam(name = "sceneId") String sceneId, String versionId) {
        Wrapper<VariableBind> wrapper = new EntityWrapper<>();
        wrapper.eq("sence_version_id",sceneId);
        List<VariableBind> list=variableBindService.selectList(wrapper);
        ModelSence sence=new ModelSence();
        sence.setData(list);
        sence.setSceneName("asdf");
        List<ModelSence> model=new ArrayList<ModelSence>();
        model.add(sence);
        ActProcRelease result = new ActProcRelease();
        result.setVariableMap(model);
        return result;
    }

    @PostMapping("edit")
    @ApiOperation(value = "新增")
    @Transactional()
    public Result<Integer> edit(VariableBind entityInfo,HttpServletRequest request) {
        Map<String,String[]> map=request.getParameterMap();
        Wrapper<VariableBind> wrapper = new EntityWrapper<>();
        wrapper.eq("sence_version_id",map.get("senceVersionid")[0]);
        List<VariableBind> list= variableBindService.selectList(wrapper);
        for (VariableBind var:list){
            String varCode=var.getVariableCode();
            entityInfo.setId(Long.parseLong(map.get(varCode+"_bind")[0]));
            entityInfo.setBindTable(map.get(varCode+"_tableName")[0]);
            entityInfo.setBindColumn(map.get(varCode+"_column")[0]);
            entityInfo.setCreateTime(new Date());
            entityInfo.setIsEffect("1");
            variableBindService.insertOrUpdate(entityInfo);
        }
        return Result.success(0);
    }

    @PostMapping("manualVariable")
    @ApiOperation(value = "规则手动验证")
    public PageResult<Map<String,Object>> manualVariable(VariableBindVo entityInfo, HttpServletRequest request) {
        Map<String, Object> data = new HashMap<String, Object>();
        DroolsParamter drools = new DroolsParamter();
        ActProcRelease act = getAll(String.valueOf(entityInfo.getSenceVersionId()),"");
        List<ModelSence> map=act.getVariableMap();
        for (ModelSence sence:map){
            List<VariableBind> li=sence.getData();
            for (VariableBind ls:li){
                data.put(ls.getVariableCode(), ls.getTmpValue());
            }
        }
        drools.setVersion(String.valueOf(entityInfo.getSenceVersionId()));
        drools.setSence(entityInfo.getSceneIdentify());
        drools.setData(data);
        String res = droolsRuleRpc.excuteDroolsSceneValidation(drools);
        JSONObject obj = JSON.parseObject(res);
        JSONObject o=obj.getJSONObject("data").getJSONObject("globalMap");
        JSONArray dataArr=  o.getJSONArray ("logIdList");
        String[] arg=dataArr.toArray(new String[dataArr.size()]);
        Map<String,Object> resultMap=new HashMap<String,Object>();
        resultMap.put("logId",arg[0]);
        resultMap.put("versionId",entityInfo.getSenceVersionId());
        if (obj.getInteger("code") == 0) {
            return PageResult.success(resultMap,0);
        }else{
            return PageResult.error(1,"验证失败");
        }

    }

    @PostMapping("getVariableBindBySenceVersionId")
    @ApiOperation(value = "根据规则版本获取规则变量绑定信息")
    public List<VariableBind> getVariableBindBySenceVersionId(VariableBindVo entityInfo){
        // 查询变量绑定字段信息
        Map<String, Object> columnMap = new HashMap<String, Object>();
        columnMap.put("SENCE_VERSION_ID", entityInfo.getSenceVersionId());
        columnMap.put("IS_EFFECT", "1");
        List<VariableBind> bindList = variableBindService.selectByMap(columnMap);
        return bindList;
    }

    @PostMapping("getAutoValidaionData")
    @ApiOperation(value = "根据规则id获取需要验证的数据")
    public List<Map<String, Object>> getAutoValidaionData(VariableBindVo entityInfo){
        //根据页面输入验证次数查找第三方表记录数
        Wrapper<TempDataContains> wrapper = new EntityWrapper<>();
        wrapper.eq("scene_id", entityInfo.getSenceId());// 规则id
        Page<TempDataContains> pages = new Page<>();
        pages.setCurrent(0);
        pages.setSize(entityInfo.getExcuteTotal()); // 执行条数，获取数据记录数
        Page<Map<String, Object>> pageMap = tempDataContainsService.selectMapsPage(pages, wrapper);
        List<Map<String, Object>> recordMap = pageMap.getRecords();
        return recordMap;
    }

    @PostMapping("autoVariable")
    @ApiOperation(value = "规则自动验证")
    public PageResult<List<Map<String,Object>>> autoVariable(VariableBindVo entityInfo, HttpServletRequest request) {
        int success = 0, fail = 0;
        List<VariableBind> bindList=getVariableBindBySenceVersionId(entityInfo);
        List<Map<String, Object>> recordMap = getAutoValidaionData(entityInfo); // 查询所需验证数据
        List<DroolsParamter> list=new ArrayList<DroolsParamter>();
        for (Map<String, Object> map2 : recordMap) {
            Map<String, Object> data = new HashMap<String, Object>();
            DroolsParamter drools = new DroolsParamter();
            for (VariableBind vBind : bindList) {
                data.put(vBind.getVariableCode(), map2.get(vBind.getVariableCode()));
            }
            drools.setVersion(String.valueOf(entityInfo.getSenceVersionId()));
            drools.setSence(entityInfo.getSceneIdentify());
            drools.setData(data);
            list.add(drools);
        }
        List<Map<String,Object>> resultList=new ArrayList<Map<String,Object>>();
        String res = droolsRuleRpc.batchExcuteRuleValidation(list);
        JSONArray mainArray=JSONArray.parseArray(res);
        for (int i=0;i<mainArray.size();i++){
            JSONObject obj= mainArray.getJSONObject(i);
            JSONObject o=obj.getJSONObject("data").getJSONObject("globalMap");
            JSONArray dataArr=  o.getJSONArray ("logIdList");
            Integer count = (Integer) o.getJSONObject ("_result").getJSONObject("map").get("count");
            String[] arg=dataArr.toArray(new String[dataArr.size()]);
            Map<String,Object> resultMap=new HashMap<String,Object>();
            resultMap.put("logId",arg[0]);
            resultMap.put("versionId",entityInfo.getSenceVersionId());
            resultMap.put("count",count);
            resultList.add(resultMap);
        }
        return PageResult.success(resultList,0);

    }

    @PostMapping("development")
    @ApiOperation(value = "发布正式版")
    @Transactional()
    public Result<Integer> development(VariableBindVo bindInfo) {
        SceneVersion scene = sceneVersionService.selectById(bindInfo.getSenceVersionId()); // 当前要发布的测试版本记录
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
        scene.setOfficialVersion(String.valueOf(maxVersion));
        sceneVersionService.insertOrUpdate(scene);


        /*SceneVersion entityInfo = new SceneVersion();
        entityInfo.setStatus(1);
        entityInfo.setVersion(String.valueOf(maxVersion));
        entityInfo.setType(1);
        entityInfo.setTitle(scene.getTitle());
        entityInfo.setComment(scene.getComment());
        entityInfo.setSceneIdentify(scene.getSceneIdentify());
        entityInfo.setSceneId(scene.getSceneId());
        entityInfo.setRuleDiv(scene.getRuleDiv());
        entityInfo.setRuleDrl(scene.getRuleDrl());
        sceneVersionService.insertOrUpdate(entityInfo);*/
        return Result.success(0);
    }


}
