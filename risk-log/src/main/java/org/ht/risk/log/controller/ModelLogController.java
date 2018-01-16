package org.ht.risk.log.controller;

import javax.annotation.Resource;

import org.ht.risk.log.entity.ModelLog;
import org.ht.risk.log.service.ModelLogService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/modelLog")
public class ModelLogController {
	
	@Resource
	private ModelLogService modelLogService ;
	
	@RequestMapping("/save")
	@ApiOperation(value = "模型新增日志")
	@ResponseBody
	@Transactional()
	public String save(@RequestBody ModelLog entity){
		String str=null;
        try {
        	modelLogService.insertOrUpdate(entity);
        	System.out.println(entity.getId()+">>>>>>>>>");
        	return  entity.getId().toString();
        }catch (Exception e){
        	e.printStackTrace();
        }
        return str;
    }
}
