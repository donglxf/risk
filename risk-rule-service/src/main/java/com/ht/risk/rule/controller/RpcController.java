package com.ht.risk.rule.controller;

import com.ht.risk.rule.service.TempDataContainsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 提供模型获取数据接口
 * </p>
 *
 * @author dyb
 * @since 2018-02-26
 */
@RestController
@RequestMapping("/rpc/")
public class RpcController {

    @Autowired
    private TempDataContainsService tempDataContainsService;

    @PostMapping("get")
    @ApiOperation(value = "房速贷准入流程获取数据接口")
    public List<Map<String, Object>> getVariableBindBySenceVersionId() {
        Map<String, Object> columnMap = new HashMap<String, Object>();
        List<Map<String, Object>> map = tempDataContainsService.getAutoValidaionData("");
        return map;
    }
}
