package com.ht.risk.rule.controller;


import com.ht.risk.common.result.Result;
import com.ht.risk.rule.facade.CheckKeyFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 规则引擎常用变量 前端控制器
 * </p>
 *
 * @author 张鹏
 * @since 2017-12-15
 */
@RestController
@RequestMapping("/check")
@Api(tags = "CheckController", description = "验证", hidden = true)
public class CheckController {
    @Autowired
    private CheckKeyFacade checkKeyFacade;


    @GetMapping("key")
    @ApiOperation(value = "查询所有的常量")
    public Result<Integer> getAll(String key,Integer type,String other){

        boolean flag = checkKeyFacade.checkKey(key,type,other);
        if(flag){
            return Result.error(-1,"保存失败，该标识已存在！");
        }else{
            return Result.success(1);
        }
    }


}

