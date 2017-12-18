package com.ht.risk.rule.controller;


import com.ht.risk.common.result.Result;
import com.ht.risk.rule.entity.EntityInfo;
import com.ht.risk.rule.entity.Variable;
import com.ht.risk.rule.service.EntityInfoService;
import com.ht.risk.rule.service.VariableService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
public class EntityInfoController {

    @Autowired
    private EntityInfoService entityInfoService;
    @GetMapping("getAll")
    @ApiOperation(value = "查询所有的对象")
    public Result<List<EntityInfo>> getAll(){
        List<EntityInfo> list = entityInfoService.selectList(null);

        return Result.success(list);
    }
}

