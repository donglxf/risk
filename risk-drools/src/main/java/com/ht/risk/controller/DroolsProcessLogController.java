package com.ht.risk.controller;


import com.ht.risk.model.DroolsDetailLog;
import com.ht.risk.service.DroolsDetailLogService;
import io.swagger.annotations.ApiOperation;
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
 * @since 2018-01-05
 */
@RestController
@RequestMapping("/droolsProcessLog")
public class DroolsProcessLogController {
	@Resource
	private DroolsDetailLogService droolsProcessLogService ;
	
	@RequestMapping("/save")
	@ApiOperation(value = "新增日志")
	@ResponseBody
	@Transactional()
	public String save(@RequestBody DroolsDetailLog entity){
		String str=null;
        try {
        	droolsProcessLogService.insertOrUpdate(entity);
        	System.out.println(entity.getId()+">>>>>>>>>");
        	return  entity.getId().toString();
        }catch (Exception e){
        	e.printStackTrace();
        }
        return str;
    }
}

