package com.ht.risk.eip.controller;

import com.ht.risk.api.feign.eip.BlackRpc;
import com.ht.risk.api.model.eip.NegativeSearchDtoIn;
import com.ht.risk.api.model.eip.NegativeSearchDtoOut;
import com.ht.ussp.core.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 * demo
 * </p>
 * @author 张鹏
 * @since 2017-12-15
 */
@RestController
@RequestMapping("/news")
@Api(tags = "BlackController", description = "新闻消息接口", hidden = true)
public class NewsController {
    @Autowired
    private BlackRpc tcRpc;

    @PostMapping("/negativeSearch")
    @ApiOperation(value = "负面消息查询",httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<NegativeSearchDtoOut> negativeSearch(NegativeSearchDtoIn input) throws Exception{
        Result<NegativeSearchDtoOut> result =  tcRpc.negativeSearch(input);
        return result;
    }





}

