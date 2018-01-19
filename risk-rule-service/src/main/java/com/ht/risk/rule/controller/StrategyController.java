package com.ht.risk.rule.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ht.risk.common.result.PageResult;
import com.ht.risk.rule.entity.SceneVersion;
import com.ht.risk.rule.service.RuleHisVersionService;
import com.ht.risk.rule.service.SceneVersionService;
import com.ht.risk.rule.vo.RuleHisVersionVo;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("ruleMatchResult/{logId}/{versionId}")
    @ApiOperation(value = "获取规则验证结果")
    public PageResult<List<RuleHisVersionVo>> ruleMatchResult(@PathVariable("logId") String logId, @PathVariable("versionId") String versionId) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", logId);
        paramMap.put("senceVersionId", versionId);
        List<RuleHisVersionVo> list = ruleHisVersionService.getRuleValidationResult(paramMap);
        return PageResult.success(list, 0);
    }
}
