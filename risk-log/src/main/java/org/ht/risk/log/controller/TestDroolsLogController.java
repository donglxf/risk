package org.ht.risk.log.controller;


import io.swagger.annotations.ApiOperation;
import org.ht.risk.log.entity.DroolsLog;
import org.ht.risk.log.entity.TestDroolsLog;
import org.ht.risk.log.service.DroolsLogService;
import org.ht.risk.log.service.TestDroolsLogService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author dyb
 * @since 2018-01-18
 */
@RestController
@RequestMapping("/testDroolsLog")
public class TestDroolsLogController {
    @Resource
    private TestDroolsLogService testDroolsLogService ;

    @RequestMapping("/save")
    @ApiOperation(value = "新增日志")
    @ResponseBody
    @Transactional()
    public String save(@RequestBody TestDroolsLog entity){
        String str=null;
        try {
            testDroolsLogService.insertOrUpdate(entity);
            return  entity.getId().toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return str;
    }
}

