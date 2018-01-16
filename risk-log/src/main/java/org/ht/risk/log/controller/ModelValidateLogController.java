package org.ht.risk.log.controller;


import javax.annotation.Resource;

import org.ht.risk.log.entity.ModelValidateLog;
import org.ht.risk.log.service.ModelValidateLogService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author dyb
 * @since 2018-01-05
 */
@RestController
@RequestMapping("/modelValidateLog")
public class ModelValidateLogController {
	@Resource
	private ModelValidateLogService modelvalidateLogService ;
	
	@RequestMapping("/save")
	@ApiOperation(value = "模型新增详细日志")
	@ResponseBody
	@Transactional()
	public String save(@RequestBody ModelValidateLog entity){
		String str=null;
        try {
        	modelvalidateLogService.insertOrUpdate(entity);
        	System.out.println(entity.getId()+">>>>>>>>>");
        	return  entity.getId().toString();
        }catch (Exception e){
        	e.printStackTrace();
        }
        return str;
    }
}

