package com.ht.risk.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.ht.risk.api.model.drools.RpcDroolsLog;
import com.ht.risk.api.model.log.RpcHitRuleInfo;
import com.ht.risk.common.result.PageResult;
import com.ht.risk.common.result.Result;
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
	public PageResult<List<DroolsLog>> page(String date, String endDate, String logId,Integer page, Integer limit) {

		Wrapper<DroolsLog> wrapper = new EntityWrapper<>();
		if (StringUtils.isNotBlank(date)) {
			wrapper.and().ge("create_time", date);
		}
		if (StringUtils.isNotBlank(endDate)) {
			wrapper.and().le("create_time", endDate);
		}
		if(StringUtils.isNotBlank(logId)){
			wrapper.and().like("id",logId);
		}
		wrapper.orderBy("create_time",false);
		Page<DroolsLog> pages = new Page<>();
		pages.setCurrent(page);
		pages.setSize(limit);
		pages = droolsLogService.selectPage(pages, wrapper);

		return PageResult.success(pages.getRecords(), pages.getTotal());
	}


	@RequestMapping("/getHitRuleInfo")
	public Result<List<RpcHitRuleInfo>> getHitRuleInfo(@RequestBody String procInstId){
		LOGGER.info("getHitRuleInfo mothod invoke,paramter:"+ procInstId);
		Result<List<RpcHitRuleInfo>> result = null;
		if(procInstId == null){
			LOGGER.error("getHitRuleInfo mothod invoke,paramter null exception");
			result =Result.error(1,"参数异常！");
			return  result;
		}
		try {
			List<RpcHitRuleInfo> tasks = droolsLogService.queryHitRuleInfoByProcInstId(procInstId);
			result = Result.success(tasks);
		}catch (Exception e){
			e.printStackTrace();
			result = Result.error(2,"getHitRuleInfo mothod excute exception,"+e);
			return  result;
		}
		LOGGER.info("getHitRuleInfo mothod invoke end,reustl:"+ JSON.toJSONString(result));
		return result;
	}

	@RequestMapping("/countHitRuleInfo")
	public Result<List<RpcHitRuleInfo>> countHitRuleInfo(@RequestBody List<String> procInstId){
		LOGGER.info("queryModelProcInstIdByBatchId mothod invoke,paramter:"+ procInstId);
		Result<List<RpcHitRuleInfo>> result = null;
		if(procInstId == null){
			LOGGER.error("queryModelProcInstIdByBatchId mothod invoke,paramter null exception");
			result =Result.error(1,"参数异常！");
			return  result;
		}
		try {
			List<RpcHitRuleInfo> tasks = droolsLogService.countHitRuleInfo(procInstId);
			result = Result.success(tasks);
		}catch (Exception e){
			e.printStackTrace();
			result = Result.error(2,"queryModelProcInstIdByBatchId mothod excute exception,"+e);
			return  result;
		}
		LOGGER.info("resultPage mothod invoke,reustl:"+ JSON.toJSONString(result));
		return result;
	}


	@RequestMapping("/queryModelDroolsLogs")
	public Result<List<RpcDroolsLog>> queryTestModelDroolsLogs(@RequestBody RpcDroolsLog rpcDroolsLog){
		LOGGER.info("queryModelDroolsLogs mothod invoke,paramter:"+ JSON.toJSONString(rpcDroolsLog));
		Result<List<RpcDroolsLog>> result = null;
		if(rpcDroolsLog == null || rpcDroolsLog.getProcinstId() == null){
			LOGGER.error("queryModelDroolsLogs mothod invoke,paramter null exception");
			result =Result.error(1,"参数异常！");
			return  result;
		}
		try {
			List<RpcDroolsLog> logs = droolsLogService.queryModelDroolsLogs(rpcDroolsLog.getProcinstId());
			result = Result.success(logs);
		}catch (Exception e){
			e.printStackTrace();
			result = Result.error(2,"queryModelDroolsLogs mothod excute exception,"+e);
			return  result;
		}
		LOGGER.info("queryModelDroolsLogs mothod invoke end,reustl:"+ JSON.toJSONString(result));
		return result;
	}


}
