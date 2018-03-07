package com.ht.risk.eip.controller;

import com.ht.risk.api.feign.eip.NewsRpc;
import com.ht.risk.api.model.eip.NegativeSearchDtoIn;
import com.ht.risk.api.model.eip.NegativeSearchDtoOut;
import com.ht.ussp.core.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


/**
 * <p>
 * demo
 * </p>
 * @author 张鹏
 * @since 2017-12-15
 */
@RestController
@RequestMapping("/news")
@Api(tags = "BlackController", description = "天行数科", hidden = true)
public class NewsController {

    @Autowired
    private NewsRpc tcRpc;

    @PostMapping("/negativeSearch")
    @ApiOperation(value = "天行数科犯罪信息",httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Result<NegativeSearchDtoOut> negativeSearch(@RequestBody NegativeSearchDtoIn input) throws Exception{
        Result<NegativeSearchDtoOut> result =  tcRpc.negativeSearch(input);
        return result;
    }





}

