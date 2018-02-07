package com.ht.risk.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ht.risk.common.result.PageResult;
import com.ht.risk.common.util.StringUtils;
import com.ht.risk.model.DroolsDetailLog;
import com.ht.risk.model.DroolsLog;
import com.ht.risk.service.DroolsLogService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/droolsLog")
public class DroolsLogController {

	protected static final Logger LOGGER = LoggerFactory.getLogger(DroolsLogController.class);
	
	@Resource
	private DroolsLogService droolsLogService ;
	
	@RequestMapping("save")
	@ApiOperation(value = "新增日志")
	@ResponseBody
	@Transactional()
	public String save(@RequestBody DroolsLog entity){
		String str=null;
        try {
        	droolsLogService.insertOrUpdate(entity);
        	return  entity.getId().toString();
        }catch (Exception e){
        	e.printStackTrace();
        }
        return str;
    }

	@GetMapping("page")
	@ApiOperation(value = "分页查询")
	public PageResult<List<DroolsLog>> page(String date, String logId,Integer page, Integer limit) {

		Wrapper<DroolsLog> wrapper = new EntityWrapper<>();
		if (StringUtils.isNotBlank(date)) {
			wrapper.or().ge("create_time", date);

		}
		if(StringUtils.isNotBlank(logId)){
			wrapper.eq("id",logId);
		}
		Page<DroolsLog> pages = new Page<>();
		pages.setCurrent(page);
		pages.setSize(limit);
		pages = droolsLogService.selectPage(pages, wrapper);

		return PageResult.success(pages.getRecords(), pages.getTotal());
	}


}
