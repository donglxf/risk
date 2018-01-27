package com.ht.risk.rule.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ht.risk.common.result.PageResult;
import com.ht.risk.rule.entity.SceneVersion;
import com.ht.risk.rule.service.RuleHisVersionService;
import com.ht.risk.rule.service.SceneVersionService;
import com.ht.risk.rule.vo.RuleHisVersionVo;
import com.ht.risk.rule.vo.VariableBindVo;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/strategy/")
public class StrategyController {
    @Autowired
    private SceneVersionService sceneVersionService;

    @Autowired
    private RuleHisVersionService ruleHisVersionService;

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

    @GetMapping("manuaRuleMatchResult/{logId}/{versionId}")
    @ApiOperation(value = "获取规则验证结果")
    public PageResult<List<RuleHisVersionVo>> manuaRuleMatchResult(@PathVariable("logId") String logId, @PathVariable("versionId") String versionId) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", logId);
        paramMap.put("senceVersionId", versionId);
        List<RuleHisVersionVo> list = ruleHisVersionService.getRuleValidationResult(paramMap);
        return PageResult.success(list, 0);
    }

    @GetMapping("ruleMatchResult/{logId}/{versionId}")
    @ApiOperation(value = "获取规则验证结果")
    public PageResult<Map<String,Object>> ruleMatchResult(@PathVariable("logId") String logId, @PathVariable("versionId") String versionId) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", logId);
        paramMap.put("senceVersionId", versionId);
        PageResult<List<RuleHisVersionVo>> list = manuaRuleMatchResult(logId,versionId);

        List<Map<String,Object>> listMap=ruleHisVersionService. getRuleBatchValidationResult(paramMap);
        List<RuleHisVersionVo> paramList=new ArrayList<RuleHisVersionVo>();
        for (Map<String,Object> ma:listMap){
            JSONObject json=JSONObject.parseObject((String)ma.get("IN_PARAMTER")).getJSONObject("data"); ;
            RuleHisVersionVo vo=new RuleHisVersionVo();
            vo.setVariableName((String)ma.get("VARIABLE_NAME"));
            vo.setVariableValue((String)json.get(ma.get("VARIABLE_CODE")));
            paramList.add(vo);
        }
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("ruleResult",list.getData());
        map.put("dataResult",paramList);
        return PageResult.success(map, 0);
    }


    @PostMapping("variablePass")
    @ApiOperation(value = "规则验证通过")
    public PageResult<Integer> variablePass(VariableBindVo entityInfo) {
        Wrapper<SceneVersion> wrapper =new EntityWrapper<>();
        wrapper.eq("version_id",entityInfo.getSenceVersionId());
        SceneVersion scene= sceneVersionService.selectOne(wrapper);
        scene.setTestStatus(1);
        sceneVersionService.updateById(scene);
        return PageResult.success(0);
    }
}
