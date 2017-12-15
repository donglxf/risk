package com.ht.risk.rule.controller;


import com.ht.risk.common.result.Result;
import com.ht.risk.rule.entity.Variable;
import com.ht.risk.rule.service.VariableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 规则引擎常用变量 前端控制器
 * </p>
 *
 * @author 张鹏
 * @since 2017-12-15
 */
@RestController
@RequestMapping("/variable")
public class VariableController {
    @Autowired
    private VariableService variableService;

    @GetMapping("getAllVariable")
    public Result<List<Variable>> getAll(){
        List<Variable> list = variableService.selectList(null);

        return Result.success(list);
    }
}

