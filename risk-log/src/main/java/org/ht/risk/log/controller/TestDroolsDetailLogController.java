package org.ht.risk.log.controller;


import io.swagger.annotations.ApiOperation;
import org.ht.risk.log.entity.DroolsDetailLog;
import org.ht.risk.log.entity.TestDroolsDetailLog;
import org.ht.risk.log.service.DroolsDetailLogService;
import org.ht.risk.log.service.TestDroolsDetailLogService;
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
@RequestMapping("/testDroolsDetailLog")
public class TestDroolsDetailLogController {
    @Resource
    private TestDroolsDetailLogService testDroolsDetailLogService ;

    @RequestMapping("/save")
    @ApiOperation(value = "新增日志")
    @ResponseBody
    @Transactional()
    public String save(@RequestBody TestDroolsDetailLog entity){
        String str=null;
        try {
            testDroolsDetailLogService.insertOrUpdate(entity);
            return  entity.getId().toString();
        }catch (Exception e){
            e.printStackTrace();
        }
        return str;
    }
}

