package com.ht.risk.rule.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ht.risk.common.result.PageResult;
import com.ht.risk.common.result.Result;
import com.ht.risk.common.util.DateUtils;
import com.ht.risk.rule.entity.RuleHisVersion;
import com.ht.risk.rule.entity.SceneInfo;
import com.ht.risk.rule.entity.SceneVersion;
import com.ht.risk.rule.entity.Variable;
import com.ht.risk.rule.service.RuleHisVersionService;
import com.ht.risk.rule.service.SceneInfoService;
import com.ht.risk.rule.service.SceneVersionService;
import com.ht.risk.rule.util.StringUtil;
import com.ht.risk.rule.vo.RuleHisVersionVo;
import com.ht.risk.rule.vo.SceneInfoVo;
import com.ht.risk.rule.vo.VariableBindVo;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;


@RestController
@RequestMapping("/ruleReport/")
public class RuleReportController {

    protected static final Logger log = LoggerFactory.getLogger(RuleReportController.class);

    @Autowired
    private SceneVersionService sceneVersionService;

    @Autowired
    private RuleHisVersionService ruleHisVersionService;

    @Autowired
    private SceneInfoService sceneInfoService;

    @GetMapping("getAll")
    @ApiOperation(value = "查询所有的常量")
    public Result<List<SceneInfoVo>> getAll() {
        List<SceneInfo> list = sceneInfoService.selectList(null);
        List<SceneInfoVo> voList = new ArrayList<SceneInfoVo>();
        for (SceneInfo vo : list) {
            SceneInfoVo svo = new SceneInfoVo();
            svo.setName(vo.getSceneName());
            svo.setValue(String.valueOf(vo.getSceneId()));
            voList
                    .add(svo);
        }
        return Result.success(voList);
    }

    @PostMapping("statistic")
    @ApiOperation(value = "规则统计图")
    public Result<Map<String, Object>> statistic(HttpServletRequest request) {
        Map<String, String[]> paramMap = request.getParameterMap();
        Map<String, Object> map = new HashMap<>();
        if(com.ht.risk.common.util.ObjectUtils.isNotEmpty(paramMap)) {
            String startTime = StringUtil.strIsNotNull(paramMap.get("startDate")[0]) ? paramMap.get("startDate")[0]  : null;
            String endTime = StringUtil.strIsNotNull(paramMap.get("endDate")[0]) ? paramMap.get("endDate")[0] : null;
            String getWay = paramMap.get("getWay")[0];

            map.put("startTime", startTime);
            map.put("endTime", endTime);
            map.put("getWay", Integer.parseInt(getWay));
        }else{
            Date curentDate= DateUtils.getDate("yyyy-MM-dd");
            Date beforeDate=DateUtils.addDays(curentDate,-30);
            log.info(DateUtils.getDateString(beforeDate)+">>>>>>>>>>>>.");
            map.put("startTime", DateUtils.getDateString(beforeDate));
            map.put("endTime", DateUtils.getDateString(curentDate));
        }
        Map<String, Object> resultMap = sceneVersionService.staticRuleExecuteInfo(map); // 平均响应时间
        List<Map<String,Object>> ls= sceneVersionService.staticRuleExecuteTotal(map); // 规则量统计
        resultMap.put("ruleExecuteTotal",ls);
        return Result.success(resultMap);
    }

    @GetMapping("formatRuleName")
    @ApiOperation(value = "查询所有的常量")
    public Result<RuleHisVersion> formatRuleName(@RequestParam("ruleName") String ruleName) {
        Wrapper<RuleHisVersion> wrapper=new EntityWrapper<>();
        wrapper.eq("rule_name",ruleName);
        RuleHisVersion rh = ruleHisVersionService.selectOne(wrapper);
        return Result.success(rh);
    }



}
