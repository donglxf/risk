package com.ht.risk.eip.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.ht.risk.api.feign.eip.DianhuaRpc;
import com.ht.risk.api.model.eip.*;
import com.ht.ussp.core.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author dyb
 * @Description
 * @Date 2018/2/9 15:02
 */
@RestController
@RequestMapping("/dianhua")
@Api(tags = "DianhuaController", description = "", hidden = true)
public class DianhuaController {

    @Autowired
    DianhuaRpc dianhuaRpc;

    @PostMapping("/collectionMin")
    @ApiOperation(value = "电话邦催收分", httpMethod = "POST")
    @ResponseBody
    public Result<DianhuaCollectionMinDtoOut> negativeSearch(@RequestBody  DianhuaCollectionMinDtoIn input) throws Exception {
        System.out.println(">>>>>>>>>");
        Result<DianhuaCollectionMinDtoOut> result = dianhuaRpc.collectionMin(input);
        System.out.println("<<<<<<<<<<<"+JSON.toJSONString(result));
        return result;
    }

    @PostMapping("/contactFast")
    @ApiOperation(value = "邦秒配接口查询", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Result<List<ContactFastRespDtoOut>> contactFast(@RequestBody ContactFastReqDto input) throws Exception {
        Result<List<ContactFastRespDtoOut>> result = dianhuaRpc.contactFast(input);
        System.out.println("<<<<<<<<<<<"+JSON.toJSONString(result));
        return result;
    }


}
