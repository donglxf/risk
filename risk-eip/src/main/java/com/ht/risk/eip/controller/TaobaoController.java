package com.ht.risk.eip.controller;


import com.alibaba.fastjson.JSON;
import com.ht.risk.api.feign.eip.TaobaoRpc;
import com.ht.risk.api.model.eip.TaobaoJudicialAuctionReqDto;
import com.ht.risk.api.model.eip.TaobaoJudicialAuctionRespDto;
import com.ht.ussp.core.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author dyb
 * @Description
 * @Date 2018/2/9 15:02
 */
@RestController
@RequestMapping("/taobao")
@Api(tags = "TaobaoController", description = "淘宝", hidden = true)
public class TaobaoController {

    @Autowired
    TaobaoRpc taobaoRpc;

    @PostMapping("/judicialSale")
    @ApiOperation(value = "淘宝司法拍卖", httpMethod = "POST")
    @ResponseBody
    public Result<TaobaoJudicialAuctionRespDto[]> negativeSearch(@RequestBody  TaobaoJudicialAuctionReqDto input) throws Exception {
        System.out.println(">>>>>>>>>");
        Result<TaobaoJudicialAuctionRespDto[]> result = taobaoRpc.judicialSale(input);
        System.out.println("<<<<<<<<<<<"+JSON.toJSONString(result));
        return result;
    }


}
