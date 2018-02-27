package com.ht.risk.eip.controller;


import com.alibaba.fastjson.JSON;
import com.ht.risk.api.feign.eip.ZhengxinRpc;
import com.ht.risk.api.model.eip.wanda.WDEnterpriseDetailReqDto;
import com.ht.risk.api.model.eip.wanda.WDEnterpriseDetailRespDtoOut;
import com.ht.ussp.core.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author dyb
 * @Description
 * @Date 2018/2/27 15:02
 */
@RestController
@RequestMapping("/zhengxin")
@Api(tags = "ZhengxinController", description = "", hidden = true)
public class ZhengxinController {

    @Autowired
    ZhengxinRpc zhengxinRpc;

    @PostMapping("/wanda")
    @ApiOperation(value = "万达征信", httpMethod = "POST")
    @ResponseBody
    public Result<WDEnterpriseDetailRespDtoOut> negativeSearch(@RequestBody WDEnterpriseDetailReqDto input) throws Exception {
        System.out.println(">>>>>>>>>");
        Result<WDEnterpriseDetailRespDtoOut> result = zhengxinRpc.collectionMin(input);
        System.out.println("<<<<<<<<<<<"+JSON.toJSONString(result));
        return result;
    }


}
