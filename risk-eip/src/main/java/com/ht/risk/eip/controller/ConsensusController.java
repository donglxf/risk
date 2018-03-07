package com.ht.risk.eip.controller;

import com.alibaba.fastjson.JSON;
import com.ht.risk.api.feign.eip.ConsensusRpc;
import com.ht.risk.api.model.eip.ConsensusReqDto;
import com.ht.risk.api.model.eip.ConsensusRespDto;
import com.ht.ussp.core.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @Author dyb
 * @Description
 * @Date 2018/2/9 15:02
 */
@RestController
@RequestMapping("/consensus")
@Api(tags = "ConsensusController", description = "舆情查询", hidden = true)
public class ConsensusController {

    @Autowired
    ConsensusRpc consensusRpc;


    @PostMapping("/")
    @ApiOperation(value = "舆情查询", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Result<ConsensusRespDto> contactFast(@RequestBody ConsensusReqDto input) throws Exception {
        Result<ConsensusRespDto> result = consensusRpc.consensus(input);
        System.out.println("<<<<<<<<<<<"+ JSON.toJSONString(result));
        return result;
    }
}
