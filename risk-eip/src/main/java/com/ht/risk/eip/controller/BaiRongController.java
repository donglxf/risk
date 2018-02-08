package com.ht.risk.eip.controller;

import com.ht.risk.api.feign.eip.BairongRpc;
import com.ht.risk.api.feign.eip.LawxpRpc;
import com.ht.risk.api.model.eip.*;
import com.ht.ussp.core.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Huang.zengmeng
 * @Description
 * @Date 2018/2/7 15:02
 */
@RestController
@RequestMapping("/bairong")
@Api(tags = "Bairong", description = "百融", hidden = true)
public class BaiRongController {

    @Autowired
    private BairongRpc bairongRpc;

    @PostMapping("/moreCheck")
    @ApiOperation(value = "个人分类统计查询", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<BairongMoreCheckDtoOut> personClassify(@RequestBody BairongMoreCheckDtoIn input) {
        Result<BairongMoreCheckDtoOut> result = bairongRpc.moreCheck(input);
        return result;
    }

}
