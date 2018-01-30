package com.ht.risk.rule.controller;

import java.util.*;
import java.util.concurrent.ForkJoinPool;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONArray;
import com.ht.risk.api.comment.VerficationTypeEnum;
import com.ht.risk.api.constant.rule.RuleConstant;
import com.ht.risk.api.model.drools.DroolsParamter;
import com.ht.risk.rule.entity.*;
import com.ht.risk.rule.service.*;
import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.ht.risk.rule.rpc.DroolsRuleRpc;
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

    protected static final Logger log = LoggerFactory.getLogger(VariableBindController.class);

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

    @Autowired
    private SenceVerficationBatchService senceVerficationBatchService;


    @GetMapping("page")
    @ApiOperation(value = "分页查询")
    public PageResult<List<SceneVersion>> rulePage(String key,String isBusinessLine, String businessType, Integer page, Integer limit) {
        Map<String,Object> paramMap=new HashMap<String,Object>();
        Wrapper<SceneVersion> wrapper = new EntityWrapper<>();
        if (StringUtils.isNotBlank(key)) {
//            wrapper.like("title", key);
//            wrapper.or().like("comment", key);
//            wrapper.or().like("version", key);
            paramMap.put("title",key);
            paramMap.put("is_bind_var",key);
            paramMap.put("test_status",key);
            paramMap.put("version",key);
        }
        if(StringUtils.isNotBlank(isBusinessLine)){
            paramMap.put("business_id",isBusinessLine);
//            wrapper.eq("rb.business_id",isBusinessLine);
        }
        if(StringUtils.isNotBlank(businessType)){
//            wrapper.eq("rs.scene_type",businessType);
            paramMap.put("scene_type",businessType);
        }

        Page<SceneVersion> pages = new Page<>();
        pages.setCurrent(page);
        pages.setSize(limit);
        pages = sceneVersionService.getNoBindVariableRecord(pages, wrapper,paramMap);
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
    public PageResult<List<VariableBind>> getVariableBind(@RequestParam(name = "versionId") String versionId) {
        Wrapper<VariableBind> wrapper = new EntityWrapper<>();
        wrapper.eq("sence_version_id", versionId);
        List<VariableBind> list = variableBindService.selectList(wrapper);
        return PageResult.success(list, 0);
    }


    @GetMapping("getAll")
    @ApiOperation(value = "手动验证时，根据版本查询规则绑定变量")
    public ActProcRelease getAll(@RequestParam(name = "versionId") String versionId) {
        Wrapper<VariableBind> wrapper = new EntityWrapper<>();
        wrapper.eq("sence_version_id", versionId);
        List<VariableBind> list = variableBindService.selectList(wrapper);
        SceneVersion version= sceneVersionService.selectById(versionId);
        ModelSence sence = new ModelSence();
        sence.setData(list);
        sence.setSceneName(version.getTitle());
        List<ModelSence> model = new ArrayList<ModelSence>();
        model.add(sence);
        ActProcRelease result = new ActProcRelease();
        result.setVariableMap(model);
        return result;
    }

    @PostMapping("edit")
    @ApiOperation(value = "新增")
    @Transactional()
    public Result<Integer> edit(VariableBind entityInfo, HttpServletRequest request) {
        Map<String, String[]> map = request.getParameterMap();
        Wrapper<VariableBind> wrapper = new EntityWrapper<>();
        wrapper.eq("sence_version_id", map.get("senceVersionid")[0]);
        List<VariableBind> list = variableBindService.selectList(wrapper);
        for (VariableBind var : list) {
            String varCode = var.getVariableCode();
            entityInfo.setId(Long.parseLong(map.get(varCode + "_bind")[0]));
            entityInfo.setBindTable(map.get(varCode + "_tableName")[0]);
            entityInfo.setBindColumn(map.get(varCode + "_column")[0]);
            entityInfo.setCreateTime(new Date());
            entityInfo.setIsEffect("1");
            variableBindService.insertOrUpdate(entityInfo);
        }
        // 更新版本信息表 绑定状态
        SceneVersion sc= sceneVersionService.selectById(map.get("senceVersionid")[0]);
        sc.setIsBindVar("1");
        sceneVersionService.insertOrUpdate(sc);
        return Result.success(0);
    }

    @PostMapping("manualVariable")
    @ApiOperation(value = "规则手动验证")
    public PageResult<Map<String, Object>> manualVariable(VariableBindVo entityInfo, HttpServletRequest request) {
        Map<String, Object> data = new HashMap<String, Object>();
        DroolsParamter drools = new DroolsParamter();
        // 插入一笔记录到批次表
        SenceVerficationBatch batch=new SenceVerficationBatch();
        batch.setBatchSize(1);
        batch.setSenceVersionId(String.valueOf(entityInfo.getSenceVersionId()));
        batch.setVerficationType(String.valueOf(VerficationTypeEnum.manu.getValue()));
        senceVerficationBatchService.insert(batch);

        //封装规则验证所需数据
        ActProcRelease act = getAll(String.valueOf(entityInfo.getSenceVersionId()));
        List<ModelSence> map = act.getVariableMap();
        for (ModelSence sence : map) {
            List<VariableBind> li = sence.getData();
            for (VariableBind ls : li) {
                data.put(ls.getVariableCode(), ls.getTmpValue());
            }
        }
        drools.setVersion(String.valueOf(entityInfo.getSenceVersionId())); // 版本号
        drools.setSence(entityInfo.getSceneIdentify());
        drools.setBatchId(String.valueOf(batch.getId()));
        drools.setData(data);


        // 规则验证返回结果处理
        String res = droolsRuleRpc.excuteDroolsSceneValidation(drools);
        Map<String, Object> resultMap = new HashMap<String, Object>();
        JSONObject obj = JSON.parseObject(res);
        String code = obj.getString("code");
        if (obj.getInteger("code") == 0) {
            JSONObject o = obj.getJSONObject("data").getJSONObject("globalMap");
            JSONArray dataArr = o.getJSONArray("logIdList");
            String[] arg = dataArr.toArray(new String[dataArr.size()]);
            resultMap.put("logId", arg[0]);
            resultMap.put("versionId", entityInfo.getSenceVersionId());
            return PageResult.success(resultMap, 0);
        } else {
            return PageResult.error(1, obj.getString("msg"));
        }

    }

    @PostMapping("getVariableBindBySenceVersionId")
    @ApiOperation(value = "根据规则版本获取规则变量绑定信息")
    public List<VariableBind> getVariableBindBySenceVersionId(VariableBindVo entityInfo) {
        // 查询变量绑定字段信息
        Map<String, Object> columnMap = new HashMap<String, Object>();
        columnMap.put("SENCE_VERSION_ID", entityInfo.getSenceVersionId());
        columnMap.put("IS_EFFECT", "1");
        List<VariableBind> bindList = variableBindService.selectByMap(columnMap);
        return bindList;
    }

    public List<Map<String, Object>> getAutoValidaionData(List<VariableBind> bindList,VariableBindVo entityInfo) {
        StringBuffer buf = new StringBuffer(RuleConstant.SELECT + " ");
        String column = "", table = "";
        String getWay = entityInfo.getGetWay();
        for (VariableBind vb : bindList) {
            column += vb.getBindColumn() + " as "+vb.getVariableCode()+",";
            table = vb.getBindTable();
        }
        buf.append(column.substring(0, column.length() - 1)).append(" FROM ").append(table).append(" where ")
                .append(RuleConstant.SCENE_ID).append("='").append(entityInfo.getSenceId()).append("' ");
        if("0".equals(getWay)){ // 随机取值
            buf.append(" AND rand() ");
        }else {
            buf.append(" AND 1=1 ");
        }
        buf.append(" limit "+entityInfo.getExcuteTotal());
        log.info("自动测试拼装sql================" + buf.toString());

        List<Map<String,Object>> obj = tempDataContainsService.getAutoValidaionData(buf.toString());
        log.info("getAutoValidaionData=========================" + JSON.toJSONString(obj));

        return obj;
    }

    @PostMapping("getAutoValidaionData")
    @ApiOperation(value = "根据规则id获取需要验证的数据")
    public List<Map<String, Object>> getAutoValidaionData(VariableBindVo entityInfo) {
        List<VariableBind> bindList = getVariableBindBySenceVersionId(entityInfo);
        List<Map<String, Object>> recordMap = getAutoValidaionData(bindList,entityInfo); // 查询所需验证数据

        return recordMap;
    }

    @PostMapping("autoVariable")
    @ApiOperation(value = "规则自动验证")
    public PageResult<List<Map<String, Object>>> autoVariable(VariableBindVo entityInfo, HttpServletRequest request) {
        int success = 0, fail = 0;
        List<Map<String, Object>> recordMap = getAutoValidaionData(entityInfo); // 查询所需验证数据
        // 插入一笔记录到批次表
        SenceVerficationBatch batch=new SenceVerficationBatch();
        batch.setBatchSize(recordMap.size());
        batch.setSenceVersionId(String.valueOf(entityInfo.getSenceVersionId()));
        batch.setVerficationType(String.valueOf(VerficationTypeEnum.auto.getValue()));
        senceVerficationBatchService.insertOrUpdate(batch);

        List<DroolsParamter> list = new ArrayList<DroolsParamter>();
        for (Map<String, Object> map2 : recordMap) {
            Map<String, Object> data = new HashMap<String, Object>();
            for (Map.Entry<String,Object> ma:map2.entrySet()){
                data.put(ma.getKey(),ma.getValue());
            }
            DroolsParamter drools = new DroolsParamter();
            drools.setVersion(String.valueOf(entityInfo.getSenceVersionId()));
            drools.setSence(entityInfo.getSceneIdentify());
            drools.setData(data);
            drools.setBatchId(String.valueOf(batch.getId()));
            list.add(drools);
        }

        // 处理规定调用返回结果
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        String res = droolsRuleRpc.batchExcuteRuleValidation(list);
        JSONArray mainArray = JSONArray.parseArray(res);
        for (int i = 0; i < mainArray.size(); i++) {
            JSONObject obj = mainArray.getJSONObject(i);
            JSONObject o = obj.getJSONObject("data").getJSONObject("globalMap");
            Map<String, Object> variableMap = (Map<String, Object>) o.get("variableMap");
            JSONArray dataArr = o.getJSONArray("logIdList");
//            Integer count = (Integer) o.getJSONObject("_result").getJSONObject("map").get("count");
            JSONArray count = (JSONArray) o.getJSONObject("_result").getJSONObject("map").get("ruleList");
            List<String> ruleList=new ArrayList<String>();
            String[] ruleArr = count.toArray(new String[count.size()]);
            Set set = new HashSet();
            for (int j=0;j<ruleArr.length;j++){
                set.add(ruleArr[j]);
            }
            ruleList.addAll(set);
            String[] arg = dataArr.toArray(new String[dataArr.size()]);
            Map<String, Object> resultMap = new HashMap<String, Object>();
            resultMap.put("logId", arg[0]);
            resultMap.put("versionId", entityInfo.getSenceVersionId());
            resultMap.put("count", ruleList.size());
            resultMap.put("variableMap", variableMap);
            resultList.add(resultMap);
        }
        return PageResult.success(resultList, 0);

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
