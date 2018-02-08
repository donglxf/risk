package com.ht.risk.eip.controller;

import com.ht.risk.api.feign.eip.LawxpRpc;
import com.ht.risk.api.model.eip.LawxpPersonClassifyDtoIn;
import com.ht.risk.api.model.eip.LawxpPersonClassifyDtoOut;
import com.ht.risk.api.model.eip.LawxpWebankDtoIn;
import com.ht.risk.api.model.eip.LawxpWebankDtoOut;
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
@RequestMapping("/lawxp")
@Api(tags = "Lawxp", description = "汇法网", hidden = true)
public class LawxpController {

    @Autowired
    private LawxpRpc lawxpRpc;

    @PostMapping("/personClassify")
    @ApiOperation(value = "个人分类统计查询", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<LawxpPersonClassifyDtoOut> personClassify(@RequestBody LawxpPersonClassifyDtoIn input) {
        Result<LawxpPersonClassifyDtoOut> result = lawxpRpc.personClassify(input);
        return result;
    }

    @PostMapping("/webank")
    @ApiOperation(value = "微众(法院信息)", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<LawxpWebankDtoOut> webank(@RequestBody LawxpWebankDtoIn input) {
        Result<LawxpWebankDtoOut> result = lawxpRpc.webank(input);
        return result;
    }

    @PostMapping("/fullText")
    @ApiOperation(value = "全文检索", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
    public Result<LawxpWebankDtoOut> fullText(@RequestBody LawxpWebankDtoIn input) {
        Result<LawxpWebankDtoOut> result = lawxpRpc.webank(input);
        return result;
    }

}
