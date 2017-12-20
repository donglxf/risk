package com.ht.risk.rule.controller;


import com.baomidou.mybatisplus.plugins.Page;
import com.ht.risk.common.result.Result;
import com.ht.risk.rule.entity.ActReModel;
import com.ht.risk.rule.service.ActReModelService;
import com.ht.risk.rule.service.EntityInfoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 张鹏
 * @since 2017-12-19
 */
@RestController
@RequestMapping("/model")
public class ActReModelController {


    @Autowired
    private ActReModelService actReModelService;

    @GetMapping("list")
    @ApiOperation(value = "查询所有的对象")
    public Result<List<ActReModel>> list(Page<ActReModel> page) {
        Page<ActReModel> pages =  actReModelService.selectPage(page);
        Result<List<ActReModel>> result = Result.build("0","",pages.getRecords(),pages.getTotal());
        return result;
    }

}

