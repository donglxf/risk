package com.ht.risk.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.ht.risk.common.result.PageResult;
import com.ht.risk.common.result.Result;
import com.ht.risk.model.DroolsDetailLog;
import com.ht.risk.model.DroolsLog;
import com.ht.risk.service.DroolsDetailLogService;
import io.swagger.annotations.ApiOperation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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

	@GetMapping("getById")
	@ResponseBody
	@ApiOperation(value = "分页查询")
	public Result<List<DroolsDetailLog>> getById(@RequestParam("logId") String id) {
		Wrapper<DroolsDetailLog> wrapper = new EntityWrapper<>();
		wrapper.eq("drools_logid",id);
		List<DroolsDetailLog> list=droolsProcessLogService.selectList(wrapper);
		System.out.println("dfdfdf<<<<<");
		return Result.success(list);
	}
}

