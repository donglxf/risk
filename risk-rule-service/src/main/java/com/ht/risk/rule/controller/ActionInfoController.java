package com.ht.risk.rule.controller;


import com.ht.risk.common.result.Result;
import com.ht.risk.rule.service.ActionInfoService;
import com.ht.risk.rule.vo.ActionInfoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


//import java.util.List;


/**
 * <p>
 * 规则动作定义信息 前端控制器
 * </p>
 *
 * @author 张鹏
 * @since 2017-12-15
 */
@RestController
@RequestMapping("/actionInfo")
@Api(tags = "ActionInfoController", description = "动作库相关api描述", hidden = true)
public class ActionInfoController {
    @Autowired
    private ActionInfoService actionInfoService;

    @GetMapping("/getByIds")
    @ApiOperation(value = "通过选中的id查询动作库")
    public Result<List<ActionInfoVo>> getByIds(String ids){
      //  List<ActionInfo> sss = actionInfoService.selectList(null);
        List<ActionInfoVo> list = actionInfoService.findByIds(ids);
        return Result.success(list);

    }
}

