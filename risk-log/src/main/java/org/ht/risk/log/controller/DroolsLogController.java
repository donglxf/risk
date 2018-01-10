package org.ht.risk.log.controller;

import javax.annotation.Resource;

import org.ht.risk.log.entity.DroolsLog;
import org.ht.risk.log.service.DroolsLogService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/droolsLog")
public class DroolsLogController {
	
	@Resource
	private DroolsLogService droolsLogService ;
	
	@RequestMapping("/save")
	@ApiOperation(value = "新增日志")
	@ResponseBody
	@Transactional()
	public String save(@RequestBody DroolsLog entity){
		String str=null;
        try {
        	droolsLogService.insertOrUpdate(entity);
        	System.out.println(entity.getId()+">>>>>>>>>");
        	return  entity.getId().toString();
        }catch (Exception e){
        	e.printStackTrace();
        }
        return str;
    }
}
